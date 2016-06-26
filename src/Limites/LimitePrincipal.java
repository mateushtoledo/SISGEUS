package Limites;

import Controladores.ControlePrincipal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LimitePrincipal extends JFrame implements ActionListener {

    private ControlePrincipal objControlador;
    private JPanel principal, painel, aux;
    private JLabel nome, senha, logotipo;
    private JTextField nomeTF;
    private JPasswordField senhaPF;
    private JButton entrar;
    private Container contentPane;
    private int width, height;
    private final ImageIcon imagem = new ImageIcon("logo.jpg");

    public LimitePrincipal(ControlePrincipal pCtrl) {
        objControlador = pCtrl;

        // FUNCAO QUE ADQUIRE O TAMANHO DA RESOLUCAÇÃO DA TELA
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight();
        height -= 30;

        //Criar os JLabels
        logotipo = new JLabel(imagem);
        nome = new JLabel("Nome: ");
        nome.setForeground(Color.white);
        senha = new JLabel("       Núm. funcional: ");
        senha.setForeground(Color.white);

        //Criar o TextField e o PasswordField
        nomeTF = new JTextField(20);
        senhaPF = new JPasswordField(10);

        //Criar o botao
        entrar = new JButton("Entrar");
        entrar.addActionListener(this);
        entrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Criar os paineis
        painel = new JPanel();
        painel.setBackground(Color.DARK_GRAY);
        aux = new JPanel();
        aux.setBackground(Color.DARK_GRAY);
        painel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
        principal = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        principal.setBackground(Color.white);
        principal.setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20));
        contentPane = super.getContentPane();
        contentPane.setBackground(Color.white);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        //Adicionar componentes aos paineis
        painel.add(nome);
        painel.add(nomeTF);
        painel.add(senha);
        painel.add(senhaPF);
        painel.add(new JLabel("   "));
        painel.add(entrar);
        painel.add(new JLabel("   "));
        principal.add(logotipo);
        contentPane.add(aux);
        contentPane.add(painel);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(principal);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(new JLabel("    UNIFEI © 2016 - "
                + "Sistema de Gestão de Unidade de saúde - SISGEUS"
                + " | Develped by: Jean Carlos, Mateus Henrique & Rodrigo Roque."));

        //Configurar JFrame
        super.setTitle("Entrar no sistema de gestão de unidade de saúde");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setAlwaysOnTop(true);
        super.setSize(width, height);
        super.setResizable(true);
        super.setVisible(true);
    }

    /*
    *   Esse método retorna o número funcional do funcionário que está logado. É utilizado na marcação de consultas
     */
    public String getNumFuncional() {
        return senhaPF.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = nomeTF.getText();
        String numFunc = senhaPF.getText();

        if (nome.isEmpty() || numFunc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Você deve preencher todos os campos!");
        } else {
            boolean log = false;

            log = objControlador.getCtrlFuncionario().loginAtendente(nome, numFunc);

            if (log) {
                //Devo chamar a interface que os atendentes utilizam
                JOptionPane.showMessageDialog(this, "Você entrou como atendente!");
                super.setExtendedState(JFrame.ICONIFIED);
                senhaPF.setText("");
                nomeTF.setText("");
                objControlador.interfacePrincipalAtendente();
            } else {
                log = objControlador.getCtrlFuncionario().loginMedico(nome, numFunc);

                if (log) {
                    //Devo chamar a interface que os medicos utilizam
                    //para cadastrar prontuarios
                    JOptionPane.showMessageDialog(this, "Você entrou como Médico!");
                    super.setExtendedState(JFrame.ICONIFIED);
                    senhaPF.setText("");
                    nomeTF.setText("");
                    objControlador.getCtrlProntuario().interfaceCadastroProntuario();
                } else {
                    log = objControlador.getCtrlFuncionario().loginResponsavelPorPessoal(nome, numFunc);

                    if (log) {
                        //Devo chamar a interface de cadastro de pessoal
                        JOptionPane.showMessageDialog(this, "Você entrou como responsável por pessoal da unidade!");
                        super.setExtendedState(JFrame.ICONIFIED);
                        senhaPF.setText("");
                        nomeTF.setText("");
                        objControlador.getCtrlFuncionario().interfaceCadastroFuncionario();
                    } else {
                        JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado!");
                    }
                }
            }
        }
    }
}
