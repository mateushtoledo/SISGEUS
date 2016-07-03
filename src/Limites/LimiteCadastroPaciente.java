package Limites;

import Controladores.ControlePaciente;
import Entidades.Paciente;
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

public class LimiteCadastroPaciente extends JFrame implements ActionListener {

    private ControlePaciente controlador;
    private JPanel painel;
    private Container contentPane;
    private JTextField nomeTF, numBeneficiarioTF, enderecoTF, telefoneTF, anoTF;
    private JLabel nomeJL, numBeneficiarioJL, enderecoJL, telefoneJL, nascimentoJL, sexoJL, barra1JL, barra2JL;
    private JComboBox dia, mes, ano, sexo;
    private JButton cadastrar;

    public LimiteCadastroPaciente(ControlePaciente pCtrl) {
        super("Cadastro de paciente");
        controlador = pCtrl;
        //Criar Label's
        nomeJL = new JLabel("Nome: ");
        numBeneficiarioJL = new JLabel("Nº de Beneficiário: ");
        enderecoJL = new JLabel("Endereço: ");
        telefoneJL = new JLabel("Telefone: ");
        nascimentoJL = new JLabel("Data de Nascimento: ");
        sexoJL = new JLabel("Sexo: ");
        barra1JL = new JLabel("/");
        barra2JL = new JLabel("/");

        //Criar todos os objetos do tipo ComboBox
        dia = new JComboBox();
        for (int i = 1; i <= 31; i++) {
            dia.addItem(+i);
        }

        mes = new JComboBox();
        for (int i = 1; i <= 12; i++) {
            mes.addItem(+i);
        }

        ano = new JComboBox();
        for (int i = 1900; i <= 2100; i++) {
            ano.addItem(+i);
        }
        ano.setSelectedItem(2000);

        sexo = new JComboBox();
        sexo.addItem("Masculino");
        sexo.addItem("Feminino");

        //Criar todos os objetos do tipo TextField
        nomeTF = new JTextField(38);
        numBeneficiarioTF = new JTextField(9);
        enderecoTF = new JTextField(38);
        telefoneTF = new JTextField(16);
        //anoTF = new JTextField(4);

        //Criar todos os objetos do tipo Button
        cadastrar = new JButton("Cadastrar paciente");
        //cadastrar.setBackground(Color.cyan);
        cadastrar.addActionListener(this);

        //criar Painel a adicionar layout mananger a ele
        SpringLayout spring = new SpringLayout();
        painel = new JPanel(spring);

        //Adicionar componentes ao painel
        int x = 20, y = 40;

        JPanel line = new JPanel();
        //line.add(new JLabel("\n"));
        line.setBackground(new Color(81, 98, 67));
        painel.add(line);
        spring.putConstraint(SpringLayout.NORTH, line, 0, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, line, 0, SpringLayout.WEST, painel);
        spring.putConstraint(SpringLayout.EAST, line, 0, SpringLayout.EAST, painel);

        spring.putConstraint(SpringLayout.NORTH, nomeJL, y + 10, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, nomeJL, x + 20, SpringLayout.WEST, painel);
        painel.add(nomeJL);
        spring.putConstraint(SpringLayout.NORTH, nomeJL, y + 10, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, nomeJL, x + 20, SpringLayout.WEST, painel);
        painel.add(nomeTF);
        spring.putConstraint(SpringLayout.NORTH, nomeTF, y + 10, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, nomeTF, x + 140, SpringLayout.WEST, painel);
        painel.add(sexoJL);
        spring.putConstraint(SpringLayout.NORTH, sexoJL, y + 40, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, sexoJL, x + 20, SpringLayout.WEST, painel);
        painel.add(sexo);
        spring.putConstraint(SpringLayout.NORTH, sexo, y + 40, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, sexo, x + 140, SpringLayout.WEST, painel);
        painel.add(numBeneficiarioJL);
        spring.putConstraint(SpringLayout.NORTH, numBeneficiarioJL, y + 80, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, numBeneficiarioJL, x + 20, SpringLayout.WEST, painel);
        painel.add(numBeneficiarioTF);
        spring.putConstraint(SpringLayout.NORTH, numBeneficiarioTF, y + 80, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, numBeneficiarioTF, x + 140, SpringLayout.WEST, painel);
        painel.add(telefoneJL);
        spring.putConstraint(SpringLayout.NORTH, enderecoJL, y + 110, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, enderecoJL, x + 20, SpringLayout.WEST, painel);
        painel.add(telefoneTF);
        spring.putConstraint(SpringLayout.NORTH, enderecoTF, y + 110, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, enderecoTF, x + 140, SpringLayout.WEST, painel);
        painel.add(enderecoJL);
        spring.putConstraint(SpringLayout.NORTH, telefoneJL, y + 80, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, telefoneJL, x + 250, SpringLayout.WEST, painel);
        painel.add(enderecoTF);
        spring.putConstraint(SpringLayout.NORTH, telefoneTF, y + 80, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, telefoneTF, x + 380, SpringLayout.WEST, painel);
        painel.add(nascimentoJL);
        spring.putConstraint(SpringLayout.NORTH, nascimentoJL, y + 40, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, nascimentoJL, x + 250, SpringLayout.WEST, painel);
        painel.add(dia);
        spring.putConstraint(SpringLayout.NORTH, dia, y + 40, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, dia, x + 380, SpringLayout.WEST, painel);
        painel.add(barra1JL);
        spring.putConstraint(SpringLayout.NORTH, barra1JL, y + 40, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, barra1JL, x + 430, SpringLayout.WEST, painel);
        painel.add(mes);
        spring.putConstraint(SpringLayout.NORTH, mes, y + 40, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, mes, x + 440, SpringLayout.WEST, painel);
        painel.add(barra2JL);
        spring.putConstraint(SpringLayout.NORTH, barra2JL, y + 40, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, barra2JL, x + 490, SpringLayout.WEST, painel);
        painel.add(ano);
        spring.putConstraint(SpringLayout.NORTH, ano, y + 40, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, ano, x + 500, SpringLayout.WEST, painel);
        painel.add(cadastrar);
        spring.putConstraint(SpringLayout.NORTH, cadastrar, y + 220, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, cadastrar, x + 420, SpringLayout.WEST, painel);

        //Colocar esse painel no painel padrao da JFrame (Para utilizar BorderLayout)
        contentPane = super.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        contentPane.add(Box.createHorizontalGlue());
        contentPane.add(painel);
        contentPane.add(Box.createHorizontalGlue());

        //Setar configuracoes da JFrame;
        super.setSize(650, 350);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);;
        super.setAlwaysOnTop(true);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Esse metodo sera acionado quando o usuario clicar no botao para cadastrar paciente
        String nome, endereco, numBene, telefone, ano, pSexo;
        int diaI, mesI, anoI;

        diaI = dia.getSelectedIndex() + 1;
        mesI = mes.getSelectedIndex() + 1;

        nome = nomeTF.getText();
        endereco = enderecoTF.getText();
        numBene = numBeneficiarioTF.getText();
        telefone = telefoneTF.getText();
        ano = anoTF.getText();

        if (sexo.getSelectedIndex() == 0) {
            pSexo = Paciente.MASCULINO;
        } else {
            pSexo = Paciente.FEMININO;
        }

        if (nome.isEmpty() || endereco.isEmpty() || numBene.isEmpty() || telefone.isEmpty() || ano.isEmpty()) {
            JOptionPane.showMessageDialog(contentPane, "Você não preencheu algum campo!");
        } else {
            anoI = Integer.parseInt(ano);
            int esc = JOptionPane.showConfirmDialog(contentPane, "Você confirma a validade dos dados informados?", "Validação dos dados", JOptionPane.YES_NO_CANCEL_OPTION);

            if (esc == JOptionPane.YES_OPTION) {
                Date data = new Date(anoI, mesI, diaI);
                controlador.cadastrarPaciente(nome, numBene, pSexo, endereco, telefone, data);

                JOptionPane.showMessageDialog(contentPane, "Paciente cadastrado!");
                //Limpar campos de entrada de dados
                nomeTF.setText("");
                enderecoTF.setText("");
                anoTF.setText("");
                numBeneficiarioTF.setText("");
                dia.setSelectedIndex(0);
                mes.setSelectedIndex(0);
                sexo.setSelectedIndex(0);
            }
        }
    }
}
