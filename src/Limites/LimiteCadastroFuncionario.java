package Limites;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import Entidades.*;
import Controladores.ControleFuncionario;

public class LimiteCadastroFuncionario extends JFrame implements ActionListener {

    private JPanel painelFuncionario;
    private JMenuBar barraMenu;
    private JMenuItem sair;
    private JLabel nomeJL, numFuncionalJL, funcaoJL, especializacaoJL, aviso;
    private JTextField nomeTF, numFuncionalTF, especializacaoTF;
    private JComboBox funcao;
    private JButton cadFuncionario;
    private ControleFuncionario objController;
    private Container contentPane;

    public LimiteCadastroFuncionario(ControleFuncionario pControle) {
        objController = pControle;

        //Criar os objetos do tipo JLabel
        nomeJL = new JLabel("Nome :");
        numFuncionalJL = new JLabel("Número funcional :");
        funcaoJL = new JLabel("Funcão :");
        especializacaoJL = new JLabel("Especialização :");
        especializacaoJL.setVisible(false);
        //Criar JLabel com aviso para o usuario
        aviso = new JLabel("Para cadastrar um médico deve ser informada sua especialização");
        aviso.setVisible(false);
        aviso.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Aviso :"));

        //Criar os objetos do tipo TextField
        nomeTF = new JTextField(20);
        numFuncionalTF = new JTextField(12);
        especializacaoTF = new JTextField(12);
        especializacaoTF.setVisible(false);

        //Criar JMenuBar, JMenuItem e configura-los
        barraMenu = new JMenuBar();
        barraMenu.setForeground(Color.DARK_GRAY);
        sair = new JMenuItem("Salvar alterações e sair");
        sair.addActionListener(this);
        barraMenu.add(sair);

        //Criar o comboBox
        funcao = new JComboBox();
        funcao.addItem(Funcionario.ATENDENTE);
        funcao.addItem(Funcionario.ENFERMEIRO);
        funcao.addItem(Funcionario.MEDICO);

        //Criar ActionListener especifico para o comboBox
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (funcao.getSelectedIndex() == 2) {
                    especializacaoTF.setVisible(true);
                    especializacaoJL.setVisible(true);
                    aviso.setVisible(true);
                } else {
                    especializacaoTF.setVisible(false);
                    especializacaoTF.setText("");
                    especializacaoJL.setVisible(false);
                    aviso.setVisible(false);
                }
            }
        };

        funcao.addActionListener(action);

        //Criar os objetos do tipo JButton
        cadFuncionario = new JButton("Cadastrar Funcionário");
        cadFuncionario.addActionListener(this);

        //Criar o gerenciador de Layout
        SpringLayout spring = new SpringLayout();

        //Criar o painel de cadastro de funcionário e adicionar componentes a ele
        painelFuncionario = new JPanel();
        painelFuncionario.setLayout(spring);
        painelFuncionario.add(aviso);
        spring.putConstraint(SpringLayout.NORTH, aviso, 20, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, aviso, 160, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(nomeJL);
        spring.putConstraint(SpringLayout.NORTH, nomeJL, 170, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, nomeJL, 130, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(nomeTF);
        spring.putConstraint(SpringLayout.NORTH, nomeTF, 170, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, nomeTF, 195, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(funcaoJL);
        spring.putConstraint(SpringLayout.NORTH, funcaoJL, 170, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, funcaoJL, 480, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(funcao);
        spring.putConstraint(SpringLayout.NORTH, funcao, 167, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, funcao, 550, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(numFuncionalJL);
        spring.putConstraint(SpringLayout.NORTH, numFuncionalJL, 220, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, numFuncionalJL, 130, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(numFuncionalTF);
        spring.putConstraint(SpringLayout.NORTH, numFuncionalTF, 220, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, numFuncionalTF, 277, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(especializacaoJL);
        spring.putConstraint(SpringLayout.NORTH, especializacaoJL, 270, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, especializacaoJL, 130, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(especializacaoTF);
        spring.putConstraint(SpringLayout.NORTH, especializacaoTF, 270, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, especializacaoTF, 277, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(cadFuncionario);
        spring.putConstraint(SpringLayout.NORTH, cadFuncionario, 340, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, cadFuncionario, 300, SpringLayout.WEST, painelFuncionario);

        super.setTitle("Cadastro de funcionários");
        super.setJMenuBar(barraMenu);
        super.add(painelFuncionario);
        super.setAlwaysOnTop(true);
        super.setLocation(450, 70);
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.setSize(800, 600);
        super.setResizable(false);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            String pNome, pNumFuncional, pEspecialidade;

            pNome = nomeTF.getText();
            pNumFuncional = numFuncionalTF.getText();

            //Entra  no cadastro de medico
            if (funcao.getSelectedIndex() == 2) {
                pEspecialidade = especializacaoTF.getText();

                if (pNome.isEmpty() || pNumFuncional.isEmpty() || pEspecialidade.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Você deve informar todos os campos!");
                } else {
                    objController.cadastrarMedico(pNome, pNumFuncional, Funcionario.MEDICO, pEspecialidade);
                    JOptionPane.showMessageDialog(this, "Médico cadastrado!");

                    //Limpar campos de entrada de texto
                    nomeTF.setText("");
                    numFuncionalTF.setText("");
                    especializacaoTF.setText("");
                }
            } else if (pNome.isEmpty() || pNumFuncional.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Você deve preencher todos os campos!");
            } else {
                if (funcao.getSelectedIndex() == 0) {
                    objController.cadastrarFuncionario(pNome, pNumFuncional, Funcionario.ATENDENTE);
                } else {
                    objController.cadastrarFuncionario(pNome, pNumFuncional, Funcionario.ENFERMEIRO);
                }

                //Exibir mensagem ao usuario e Limpar os Campos de entrada de texto
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado!");
                nomeTF.setText("");
                numFuncionalTF.setText("");
            }
        } else {
            int op = JOptionPane.showConfirmDialog(this, "Deseja encerrar o cadastro?");

            if (op == JOptionPane.YES_OPTION) {
                try {
                    objController.salvarFuncionarios();
                    super.dispose();
                } catch (Exception exc) {
                    System.out.println("O sistema falhou ao tentar salvar os dados referentes à funcionários!");
                }
            }
        }
    }
}
