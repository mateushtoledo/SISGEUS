package Limites;

import Controladores.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaPrincipal extends JFrame implements ActionListener, WindowListener {

    ControlePrincipal controle;

    private int width, height, nivelAcess;
    private JMenuBar menuBar;
    private JMenuItem save, sair, regFunc, regPac, altPac, consulMarc, consulCon, consulCanc, pacFalec, pacPront, consulImp;
    private JTextField nomeTF;
    private JLabel showName, showFuncao;
    private JPasswordField senhaPF;
    private JFrame login;
    private JButton entrar, fechar;
    private String nome, senha;

    public TelaPrincipal(ControlePrincipal pControle) {
        super("Sistema de Gestão de Consultas Médicas - SUS");
        controle = pControle;

        setLayout(new BorderLayout());
        this.addWindowListener(this);

        // FUNCAO QUE ADQUIRE O TAMANHO DA RESOLUCAÇÃO DA TELA
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight();
        height -= 30;

        JPanel painel = new JPanel();
        painel.setBackground(new Color(81, 98, 67));
        painel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
        showName = new JLabel();
        showName.setForeground(Color.white);
        painel.add(showName);
        showFuncao = new JLabel();
        showFuncao.setForeground(Color.white);
        painel.add(showFuncao);
        add(painel, BorderLayout.PAGE_START);

        // IMAGEM BACKGROUND   
        JPanel bg = new JPanel();
        bg.setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("imgs/logo_.png");
        bg.add(new JLabel(imageIcon), BorderLayout.CENTER);
        add(bg, BorderLayout.CENTER);

        // CREDITOS
        JPanel creditos = new JPanel();
        creditos.setLayout(new BoxLayout(creditos, BoxLayout.X_AXIS));
        creditos.add(Box.createHorizontalGlue());
        creditos.add(new JLabel("UNIFEI © 2016 - "
                + "Sistema de Gestão de Consultas Médicas - SUS"
                + " | Develped by: Jean Carlos, Mateus Henrique & Rodrigo Roque."));

        creditos.add(Box.createHorizontalGlue());
        add(creditos, BorderLayout.PAGE_END);
        //setResizable(false); // IMPEDE DE REDIMENSIONAR
        setBackground(new Color(229, 239, 202));
        //setUndecorated(true); // remove as barras 
        setVisible(false);
        setAlwaysOnTop(true);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH); // TELA INICIA MAXIMIZADA
        setLocationRelativeTo(null);

        loginScreen();
    }

    //Esse método retorna o número funcional do funcionário que está logado. 
    //É utilizado na marcação de consultas.
    public String getNumFuncional() {
        return senha;
    }

    private void topMenuBar() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // MENU ARQUIVO;
        JMenu arq = new JMenu("Arquivo", false);
        arq.setMnemonic(KeyEvent.VK_A);
        menuBar.add(arq);
        save = new JMenuItem("Salvar");
        save.setMnemonic(KeyEvent.VK_S);
        save.addActionListener(this);
        save.setIcon(new ImageIcon("imgs/save.png"));
        arq.add(save);

        arq.add(new JSeparator());
        sair = new JMenuItem("Logout");
        sair.setMnemonic(KeyEvent.VK_L);
        sair.addActionListener(this);
        sair.setIcon(new ImageIcon("imgs/sair.png"));
        arq.add(sair);

        // MENU REGISTRAR
        JMenu registrar = new JMenu("Cadastros", true);
        registrar.setMnemonic(KeyEvent.VK_C);
        menuBar.add(registrar);

        regFunc = new JMenuItem("Funcionários");
        regFunc.setMnemonic(KeyEvent.VK_F);
        regFunc.addActionListener(this);
        regFunc.setIcon(new ImageIcon("imgs/cad.png"));
        registrar.add(regFunc);

        regPac = new JMenuItem("Pacientes");
        regPac.setMnemonic(KeyEvent.VK_P);
        regPac.addActionListener(this);
        regPac.setIcon(new ImageIcon("imgs/pac.png"));
        registrar.add(regPac);

        altPac = new JMenuItem("Alterar Pacientes");
        altPac.setMnemonic(KeyEvent.VK_T);
        altPac.addActionListener(this);
        altPac.setIcon(new ImageIcon("imgs/pacalt.png"));
        registrar.add(altPac);

        // MENU CONSULTAS
        JMenu consultas = new JMenu("Consultas", true);
        consultas.setMnemonic(KeyEvent.VK_C);
        consultas.addActionListener(this);
        menuBar.add(consultas);

        consulMarc = new JMenuItem("Marcar");
        consulMarc.setMnemonic(KeyEvent.VK_M);
        consulMarc.addActionListener(this);
        consulMarc.setIcon(new ImageIcon("imgs/conAdd.png"));
        consultas.add(consulMarc);

        consulCon = new JMenuItem("Agenda/Espec.");
        consulCon.setMnemonic(KeyEvent.VK_S);
        consulCon.addActionListener(this);
        consulCon.setIcon(new ImageIcon("imgs/menu.png"));
        consultas.add(consulCon);

        consulCanc = new JMenuItem("Cancelar");
        consulCanc.setMnemonic(KeyEvent.VK_C);
        consulCanc.addActionListener(this);
        consulCanc.setIcon(new ImageIcon("imgs/anular.png"));
        consultas.add(consulCanc);

        pacPront = new JMenuItem("Prontuários");
        pacPront.setMnemonic(KeyEvent.VK_P);
        pacPront.addActionListener(this);
        pacPront.setIcon(new ImageIcon("imgs/menu.png"));
        consultas.add(pacPront);

        pacFalec = new JMenuItem("Cad. Falecimento");
        pacFalec.setMnemonic(KeyEvent.VK_F);
        pacFalec.addActionListener(this);
        pacFalec.setIcon(new ImageIcon("imgs/Falec.png"));
        consultas.add(pacFalec);

        consulImp = new JMenuItem("Historico");
        consulImp.setMnemonic(KeyEvent.VK_H);
        consulImp.addActionListener(this);
        consulImp.setIcon(new ImageIcon("imgs/imp.png"));
        consultas.add(consulImp);

        if (nivelAcess == 0) {
            pacPront.setEnabled(false);
        } else if (nivelAcess == 1) {
            //consultas.setEnabled(false);
            regFunc.setEnabled(false);
            regPac.setEnabled(false);
            altPac.setEnabled(false);
            consulCon.setEnabled(false);
            consulCanc.setEnabled(false);
            pacFalec.setEnabled(false);
            consulImp.setEnabled(false);
            consulMarc.setEnabled(false);
        }
        System.out.println("Codi errrado: " + nivelAcess);
    }

    public void loginScreen() {
        login = new JFrame();
        JPanel pane = new JPanel();

        nomeTF = new JTextField(20);
        senhaPF = new JPasswordField(20);
        senhaPF.addActionListener(this);

        entrar = new JButton("  Entrar  ");
        entrar.addActionListener(this);
        entrar.setBackground(new Color(190, 212, 131));
        entrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fechar = new JButton("  Sair  ");
        fechar.addActionListener(this);
        fechar.setBackground(new Color(190, 212, 131));
        fechar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel n = new JLabel("NOME: ");
        n.setForeground(Color.white);
        pane.add(n);
        pane.add(nomeTF);
        JLabel s = new JLabel("NÚM. FUNC.: ");
        s.setForeground(Color.white);
        pane.add(s);
        pane.add(senhaPF);
        pane.add(fechar);
        pane.add(entrar);
        pane.setBackground(new Color(81, 98, 67));

        SpringLayout layout = new SpringLayout();
        int x = 30, y = 35;
        layout.putConstraint(SpringLayout.WEST, n, x, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, n, y + 20, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nomeTF, x, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nomeTF, y + 40, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, s, x, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, s, y + 80, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, senhaPF, x, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, senhaPF, y + 100, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, entrar, x + 60, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, entrar, y + 140, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, fechar, x + 145, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, fechar, y + 140, SpringLayout.NORTH, this);

        // LOGO DO SISTEMA
        ImageIcon logo = new ImageIcon("imgs/logotipo.png");
        JLabel logotipo = new JLabel(logo);

        pane.setLayout(layout);
        login.setLayout(new GridLayout(1, 0));
        login.add(logotipo);
        login.add(pane);
        login.setUndecorated(true);
        login.setAlwaysOnTop(true);
        login.setVisible(true);
        login.setSize(580, 300);
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //login.getRootPane().setWindowDecorationStyle(10);
        //login.setExtendedState(MAXIMIZED_BOTH); // TELA INICIA MAXIMIZADA
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, "Error: " + msg);
    }

    public void actionPerformed(ActionEvent ae) {
        Object item = ae.getSource();

        if (item == save) {
            controle.openWindow(1);

        } else if (item == regFunc) {
            controle.openWindow(3);

        } else if (item == regPac) {
            controle.openWindow(4);

        } else if (item == altPac) {
            controle.openWindow(5);

        } else if (item == consulMarc) {
            controle.openWindow(6);

        } else if (item == consulCon) {
            controle.openWindow(7);

        } else if (item == consulCanc) {
            controle.openWindow(8);

        } else if (item == pacFalec) {
            controle.openWindow(9);

        } else if (item == pacPront) {
            controle.openWindow(10);

        } else if (item == consulImp) {
            controle.openWindow(11);

        } else if (item == entrar || item == senhaPF) {
            nome = nomeTF.getText();
            senha = senhaPF.getText();

            String st = controle.getCtrFuncionario().loginFuncionario(nome, senha);
            System.out.println("Arquivo " + st);
            if (!st.isEmpty()) {
                showName.setText("Bem vindo: " + nome + " ");
                showFuncao.setText(" |    Função: " + st + "   ");
                if (st == "Atendente") {
                    nivelAcess = 0;
                } else if (st == "Responsável") {
                    nivelAcess = -1;
                } else {
                    nivelAcess = 1;
                }

                // ADICIONA A MENU BARRA SUPERIOR COMFORME O USUARIO
                topMenuBar();

                login.dispose();
                this.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario ou senha inválidos");
            }

        } else if (item == fechar) {
            System.exit(0);

        } else if (item == sair) {
            if (JOptionPane.showConfirmDialog(this,
                    "Deseja efeturar o logout?",
                    "Sair", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE) == 0) {
                try {
                    //controle.saveDados();
                    this.dispose();
                    login.setVisible(true);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(this, "Falha ao salvar os dados");
                }
            }
        }
    }

    public void windowOpened(WindowEvent we) {
    }

    public void windowClosing(WindowEvent we) {
    }

    public void windowClosed(WindowEvent we) {
        System.out.println("Salvando");
        try {
            controle.salvarDados();
            System.out.println("Salvando");
        } catch (Exception exc) {

            System.out.println("erro");
            System.out.println("O sistema falhou salvar os dados em arquivo!");
            System.out.println(exc.getMessage());
        }
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {

    }

    public void windowActivated(WindowEvent we) {
    }

    public void windowDeactivated(WindowEvent we) {

    }
}
