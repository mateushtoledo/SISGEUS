package Limites;

import Controladores.ControleFuncionario;
import Entidades.Funcionario;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class limiteFuncionario extends JFrame implements ActionListener {

    private JPanel painelFuncionario;
    private JMenuBar barraMenu;
    private JMenuItem sair;
    private JLabel nomeJL, numFuncionalJL, funcaoJL, especializacaoJL, aviso;
    private JTextField nomeTF, numFuncionalTF, especializacaoTF;
    private JComboBox funcao;
    private JButton cadFuncionario;
    private ControleFuncionario objController;
    private Container contentPane;

    public limiteFuncionario(ControleFuncionario pControle) {
        super("Cadastro de funcionários");
        objController = pControle;

        //Criar os objetos do tipo JLabel
        nomeJL = new JLabel("Nome :");
        numFuncionalJL = new JLabel("Número funcional :");
        funcaoJL = new JLabel("Funcão :");
        especializacaoJL = new JLabel("Especialização :");
        especializacaoJL.setVisible(false);
        //Criar JLabel com aviso para o usuario
        aviso = new JLabel("  Para cadastrar um médico deve ser informada sua especialização");
        aviso.setVisible(false);
        aviso.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Aviso :"));

        //Criar os objetos do tipo TextField
        nomeTF = new JTextField(25);
        numFuncionalTF = new JTextField(12);
        especializacaoTF = new JTextField(12);
        especializacaoTF.setVisible(false);

        //Criar JMenuBar, JMenuItem e configura-los
        barraMenu = new JMenuBar();
        barraMenu.setForeground(Color.DARK_GRAY);
        sair = new JMenuItem("Sair e Salvar ");
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
        int x = 20, y = 20;

        JPanel line = new JPanel();
        //line.add(new JLabel("\n"));
        line.setBackground(new Color(81, 98, 67));
        painelFuncionario.add(line);
        spring.putConstraint(SpringLayout.NORTH, line, 0, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, line, 0, SpringLayout.WEST, painelFuncionario);
        spring.putConstraint(SpringLayout.EAST, line, 0, SpringLayout.EAST, painelFuncionario);

        spring.putConstraint(SpringLayout.NORTH, aviso, y + 10, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, aviso, x + 20, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(nomeJL);
        spring.putConstraint(SpringLayout.NORTH, nomeJL, y + 60, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, nomeJL, x + 20, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(nomeTF);
        spring.putConstraint(SpringLayout.NORTH, nomeTF, y + 60, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, nomeTF, x + 140, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(funcaoJL);
        spring.putConstraint(SpringLayout.NORTH, funcaoJL, y + 90, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, funcaoJL, x + 20, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(funcao);
        spring.putConstraint(SpringLayout.NORTH, funcao, y + 90, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, funcao, x + 140, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(numFuncionalJL);
        spring.putConstraint(SpringLayout.NORTH, numFuncionalJL, y + 120, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, numFuncionalJL, x + 20, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(numFuncionalTF);
        spring.putConstraint(SpringLayout.NORTH, numFuncionalTF, y + 120, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, numFuncionalTF, x + 140, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(especializacaoJL);
        spring.putConstraint(SpringLayout.NORTH, especializacaoJL, y + 150, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, especializacaoJL, x + 20, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(especializacaoTF);
        spring.putConstraint(SpringLayout.NORTH, especializacaoTF, y + 150, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, especializacaoTF, x + 140, SpringLayout.WEST, painelFuncionario);
        painelFuncionario.add(cadFuncionario);
        spring.putConstraint(SpringLayout.NORTH, cadFuncionario, y + 205, SpringLayout.NORTH, painelFuncionario);
        spring.putConstraint(SpringLayout.WEST, cadFuncionario, x + 255, SpringLayout.WEST, painelFuncionario);

        setJMenuBar(barraMenu);
        add(painelFuncionario);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(520, 330);
        setLocationRelativeTo(null);
        setResizable(false);
        //setUndecorated(true);
        //getRootPane().setBorder(BorderFactory.createLineBorder(new Color(81, 98, 67)));
        // getRootPane().setBorder(BorderFactory.createEtchedBorder());
        setVisible(true);
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
            int op = JOptionPane.showConfirmDialog(this, "Deseja encerrar o cadastro?", "Encerrar o cadastro",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (op == JOptionPane.YES_OPTION) {
                try {
                    objController.salvarFuncionarios();
                    super.dispose();
                } catch (Exception exc) {
                    System.out.println("Falha ao salvar os dados!");
                }
            }
        }
    }
}
