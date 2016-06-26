package Limites;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Controladores.ControlePrincipal;

public class LimiteInterfaceAtendente extends JFrame implements ActionListener {

    private ControlePrincipal objController;
    private JPanel painel;
    private JMenuBar barraMenu;
    private JMenu menu, paciente, consulta, SIP;
    private JMenuItem cadPaciente, altDadosPaciente, cadFalecimento, histConsultas;
    private JMenuItem marcarConsulta, anularConsulta, eventosEspecialidade, sair;
    private final ImageIcon logotipo = new ImageIcon("logotipo.png");
    private final ImageIcon pacienteIcone = new ImageIcon("pac.png");
    private final ImageIcon consultaIcone = new ImageIcon("con.png");
    private final ImageIcon sipIcone = new ImageIcon("info.png");
    private final ImageIcon cadPacIcone = new ImageIcon("cad.png");
    private final ImageIcon cadFaleIcone = new ImageIcon("fal.png");
    private final ImageIcon marcarIcone = new ImageIcon("conAdd.png");
    private final ImageIcon menuIcone = new ImageIcon("menu.png");
    private final ImageIcon periodoIcon = new ImageIcon("per.png");
    private final ImageIcon contatoIcon = new ImageIcon("contatos.png");
    private final ImageIcon dadosIcon = new ImageIcon("dados.png");
    private final ImageIcon anular = new ImageIcon("anular.png");
    private final ImageIcon exit = new ImageIcon("sair.png");

    public LimiteInterfaceAtendente(ControlePrincipal pCtrl) {
        objController = pCtrl;

        //Criar os objetos do tipo JMenuItem
        sair = new JMenuItem("Sair do sistema");
        sair.setIcon(exit);
        cadPaciente = new JMenuItem("Cadastrar");
        cadPaciente.setIcon(cadPacIcone);
        altDadosPaciente = new JMenuItem("Alterar dados de contato");
        altDadosPaciente.setIcon(contatoIcon);
        cadFalecimento = new JMenuItem("Cadastrar falecimento");
        cadFalecimento.setIcon(cadFaleIcone);
        marcarConsulta = new JMenuItem("Marcar consulta");
        marcarConsulta.setIcon(marcarIcone);
        anularConsulta = new JMenuItem("Anular consulta");
        anularConsulta.setIcon(anular);
        eventosEspecialidade = new JMenuItem("Exibir eventos de determinado período por especialidade");
        eventosEspecialidade.setIcon(periodoIcon);
        histConsultas = new JMenuItem("Visualizar histórico de consultas e dados médicos");
        histConsultas.setIcon(dadosIcon);

        //Criar os Objetos do tipo JMenu
        menu = new JMenu("Menu");
        menu.setIcon(menuIcone);
        paciente = new JMenu("Paciente");
        paciente.setIcon(pacienteIcone);
        consulta = new JMenu("Consultas");
        consulta.setIcon(consultaIcone);
        SIP = new JMenu("Sistema de informação ao paciente");
        SIP.setIcon(sipIcone);

        //Criar JMenuBAr
        barraMenu = new JMenuBar();

        //Adicionar ActionListener individual aos JMenuItems
        sair.addActionListener(this);
        cadPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui deve ser chamada a interface que realiza o cadastro do paciente
                objController.getCtrlPaciente().interfaceCadastroPaciente();
            }
        });
        altDadosPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui deve ser chamada a interface que realiza alteracao dos dados do paciente
                objController.getCtrlPaciente().interfaceAlteracaoDadosContato();
            }
        });
        cadFalecimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui deve ser chamada a interface que realiza o cadastro de falecimento de funcionario
                objController.getCtrlFalecimento().interfaceCadastroFalecimento();
            }
        });
        marcarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui deve ser chamada a interface que realiza a marcacao de copnsultas
                objController.getCtrlConsulta().interfaceMarcarConsulta();
            }
        });
        anularConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui deve ser chamada a interface que realiza anulacao de consultas
                objController.getCtrlConsulta().interfaceAnularConsulta();
            }
        });
        eventosEspecialidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui deve ser chamada a interface que realiza a exibicao de eventos por especialidade
                objController.getCtrlConsulta().interfaceAgendaDaUnidadePorEspecialidade();
            }
        });
        histConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui deve ser chamada a interface que realiza a exibicao do historico de consultas
                System.out.println("Historico de consultas");
            }
        });

        //Adicionar JMenuItems aos seus respectivos menus
        paciente.add(cadPaciente);
        paciente.add(new JSeparator(JSeparator.HORIZONTAL));
        paciente.add(cadFalecimento);
        consulta.add(marcarConsulta);
        consulta.add(new JSeparator(JSeparator.HORIZONTAL));
        consulta.add(anularConsulta);
        consulta.add(new JSeparator(JSeparator.HORIZONTAL));
        consulta.add(eventosEspecialidade);
        SIP.add(altDadosPaciente);
        SIP.add(new JSeparator(JSeparator.HORIZONTAL));
        SIP.add(histConsultas);

        //Adicionar JMenus ao JMenu principal
        menu.add(paciente);
        menu.add(new JSeparator(JSeparator.HORIZONTAL));
        menu.add(consulta);
        menu.add(new JSeparator(JSeparator.HORIZONTAL));
        menu.add(SIP);

        //Adicionar componentes a barra de menu
        barraMenu.add(menu);
        barraMenu.add(sair);

        //Criar Painel
        painel = new JPanel(new BorderLayout());
        painel.setBackground(Color.white);
        painel.add(new JLabel(logotipo), BorderLayout.CENTER);

        //Adicionar componentes e configurar JFrame
        super.setTitle("Menu de opções para atendente");
        super.setJMenuBar(barraMenu);
        super.add(painel);
        super.setLocation(450, 70);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.setSize(800, 600);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int op = JOptionPane.showConfirmDialog(this, "Deseja sair do menu de atendente?", "Verificação de segurança", JOptionPane.YES_NO_OPTION);

        if (op == JOptionPane.YES_OPTION) {
            try {
                objController.salvarDados();
            } catch (Exception exc) {
                System.out.println("O sistema falhou em salvar os dados!");
            }
            super.dispose();
        }
    }
}
