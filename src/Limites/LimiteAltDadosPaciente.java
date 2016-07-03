package Limites;

import Controladores.*;
import Entidades.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimiteAltDadosPaciente extends JFrame implements ActionListener {

    private ControlePaciente controlador;
    private JTextField telefoneTF, enderecoTF, numBeneTF;
    private JLabel telefoneJL, enderecoJL, numeroBeneJL, mensagem;
    private JPanel painel;
    private JButton buscar, alterar;

    public LimiteAltDadosPaciente(ControlePaciente pCtrl) {
        controlador = pCtrl;

        //Criar os Label's
        telefoneJL = new JLabel("Telefone : ");
        enderecoJL = new JLabel("Endereço : ");
        numeroBeneJL = new JLabel("Número de beneficiário");

        //Criar os TextField's
        telefoneTF = new JTextField(20);
        enderecoTF = new JTextField(20);
        numBeneTF = new JTextField(12);
        mensagem = new JLabel("  Se você desejar alterar apenas um dos campos de \ncontato basta deixar o outro vazio!  ");
        mensagem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Aviso:"));

        //Criar o JButton
        alterar = new JButton(" Alterar dados ");
        alterar.addActionListener(this);

        //Criar o gerenciador de Layout
        SpringLayout spring = new SpringLayout();
        //Criar o painel de cadastro de funcionário e adicionar componentes a ele
        JPanel painelFuncionario = new JPanel();
        painelFuncionario.setLayout(spring);

        int x = 20, y = 40;
        JPanel line = new JPanel();
        //line.add(new JLabel("\n"));
        line.setBackground(new Color(81, 98, 67));
        spring.putConstraint(SpringLayout.NORTH, line, 0, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, line, 0, SpringLayout.WEST, painelFuncionario);
        spring.putConstraint(SpringLayout.EAST, line, 0, SpringLayout.EAST, painelFuncionario);
        painelFuncionario.add(line);

        spring.putConstraint(SpringLayout.NORTH, mensagem, y - 10, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, mensagem, x + 20, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(mensagem);

        spring.putConstraint(SpringLayout.NORTH, numeroBeneJL, y + 60, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, numeroBeneJL, x + 20, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(numeroBeneJL);

        spring.putConstraint(SpringLayout.NORTH, numBeneTF, y + 60, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, numBeneTF, x + 160, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(numBeneTF);

        spring.putConstraint(SpringLayout.NORTH, enderecoJL, y + 90, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, enderecoJL, x + 20, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(enderecoJL);

        spring.putConstraint(SpringLayout.NORTH, enderecoTF, y + 90, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, enderecoTF, x + 160, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(enderecoTF);

        spring.putConstraint(SpringLayout.NORTH, telefoneJL, y + 120, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, telefoneJL, x + 20, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(telefoneJL);

        spring.putConstraint(SpringLayout.NORTH, telefoneTF, y + 120, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, telefoneTF, x + 160, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(telefoneTF);

        spring.putConstraint(SpringLayout.NORTH, alterar, y + 190, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, alterar, x + 410, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(alterar);

        add(painelFuncionario);
        //Setar configuracoes da JFrame
        setTitle("Alteração dos dados de contato de paciente");
        setSize(600, 330);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setAlwaysOnTop(true);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Paciente pac;
        String numBeneficiario, pEndereco, pTelefone;

        numBeneficiario = numBeneTF.getText();

        if (numBeneficiario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Você deve informar o nome de beneficiário!");
        } else {
            pac = controlador.getPaciente(numBeneficiario);

            if (pac == null) {
                JOptionPane.showMessageDialog(this, "Nenhum paciente cadastrado com esse número de beneficiário!");
            } else {
                pEndereco = enderecoTF.getText();
                pTelefone = telefoneTF.getText();

                if (pEndereco.isEmpty() && pTelefone.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Você não informou que dado deve ser alterado!");
                } else if (pEndereco.isEmpty()) {
                    //Se ele nao informou o endereco devo alterar apenas o telefone
                    controlador.alterarTelefone(pac, pTelefone);
                    JOptionPane.showMessageDialog(this, "Telefone alterado!");
                    super.dispose();
                } else if (pTelefone.isEmpty()) {
                    //Se ele nao informou o telefone devo alterar apenas o endereco
                    controlador.alterarEndereco(pac, pEndereco);
                    JOptionPane.showMessageDialog(this, "Endereço alterado!");
                    super.dispose();
                } else {
                    //Se ele informou os dois campos devo alterar as duas informacoes
                    controlador.alterarTelefone(pac, pTelefone);
                    controlador.alterarEndereco(pac, pEndereco);
                    JOptionPane.showMessageDialog(this, "Dados de contato alterados!");
                    super.dispose();
                }
            }
        }
    }
}
