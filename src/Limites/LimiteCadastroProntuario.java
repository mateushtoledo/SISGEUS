package Limites;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controladores.*;
import Entidades.*;

public class LimiteCadastroProntuario extends JFrame implements ActionListener {

    private ControlePrincipal objControle;
    private Container contentPane;
    private JPanel validacao, cadastro;
    private CardLayout card;
    private JLabel horarioJL, dataJL, pacienteJL, b1, b2, pt;
    private JComboBox dia, mes, ano, horas, minutos, pacientes;
    private JTextArea queixasTA, resumoDiagnosticoTA, resumoExameTA, tratamentoTA;
    private JScrollPane queixasSP, resumoDiagnosticoSP, resumoExameSP, tratamentoSP;
    private JMenuBar barraMenu;
    private JMenuItem encerrar;
    private JButton cadastrar, confirmar;

    public static final String CADASTRO = "CADASTRO DE PRONTUÁRIO";
    public static final String VALIDACA0 = "VALIDAÇÃO DE CONSULTA";
    public final ImageIcon icone = new ImageIcon("sair.png");

    public LimiteCadastroProntuario(ControlePrincipal pCtrl) {
        objControle = pCtrl;

        //Criar os objetos do tipo JLabel
        horarioJL = new JLabel("Horário da consulta:");
        dataJL = new JLabel("Data da consulta:");
        pacienteJL = new JLabel("Selecione o paciente:");
        b1 = new JLabel("/");
        b2 = new JLabel("/");
        pt = new JLabel(":");

        //Criar os objetos do tipo JPanel
        validacao = new JPanel();
        cadastro = new JPanel();

        //Criar os objetos do tipo JTextArea
        queixasTA = new JTextArea(3, 40);
        queixasTA.setToolTipText("Neste campo devem ser registradas as queixas informadas pelo paciente");
        resumoDiagnosticoTA = new JTextArea(3, 40);
        resumoDiagnosticoTA.setToolTipText("Neste campo deve ser registrado o resumo do diagnóstico");
        resumoExameTA = new JTextArea(3, 40);
        resumoExameTA.setToolTipText("Neste campo deve ser registrado o resumo do exame clínico");
        tratamentoTA = new JTextArea(3, 40);
        tratamentoTA.setToolTipText("Neste campo deve ser registrado(s) o(s) tratamento(s) prescrito(s)");

        //Criar os objetos do tipo JComboBox
        dia = new JComboBox();
        mes = new JComboBox();
        ano = new JComboBox();
        horas = new JComboBox();
        minutos = new JComboBox();
        pacientes = new JComboBox(objControle.getCtrlPaciente().getDescricaoPacientes());

        //Adicionar itens aos objetos do tipo ComboBox
        for (int i = 1; i <= 30; i++) {
            dia.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            mes.addItem(i);
        }
        ano.addItem(2016);
        ano.addItem(2017);
        ano.addItem(2018);
        for (int i = 8; i <= 17; i++) {
            horas.addItem(i);

            if (i == 11) {
                i += 2;
            }
        }
        minutos = new JComboBox();
        minutos.addItem(00);
        minutos.addItem(30);

        //Criar objetos do tipo JButton e adicionar listener a eles
        cadastrar = new JButton("Cadastrar dados médicos");
        cadastrar.setForeground(Color.red);
        cadastrar.addActionListener(this);
        confirmar = new JButton("Buscar consulta");
        confirmar.addActionListener(this);
        confirmar.setForeground(Color.red);

        //Criar JMenuBar e JMenuItem
        barraMenu = new JMenuBar();
        encerrar = new JMenuItem("Encerrar o cadastro e sair", icone);
        encerrar.addActionListener(this);
        barraMenu.add(encerrar);

        //Criar os objetos do tipo JScrollPane e configurar barra de rolagem
        //Serao utilizadas bordas com titulo ao inves de JLabel's
        queixasSP = new JScrollPane();
        queixasSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        queixasSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Queixas:"));
        resumoDiagnosticoSP = new JScrollPane();
        resumoDiagnosticoSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resumoDiagnosticoSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Resumo do diagnóstico:"));
        resumoExameSP = new JScrollPane();
        resumoExameSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resumoExameSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Resumo do Exame:"));
        tratamentoSP = new JScrollPane();
        tratamentoSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tratamentoSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Tratamento:"));

        //Adicionar componentes aos Paineis com barras de rolagem
        queixasSP.getViewport().add(queixasTA);
        resumoDiagnosticoSP.getViewport().add(resumoDiagnosticoTA);
        resumoExameSP.getViewport().add(resumoExameTA);
        tratamentoSP.getViewport().add(tratamentoTA);

        //Criar Gerenciadores de Layout
        SpringLayout spring = new SpringLayout();
        card = new CardLayout();

        //Obter o Container padrao da JFrame e definir gerenciador de Layout
        contentPane = super.getContentPane();

        //Definir gerenciadores de layout para cada Painel ou container
        cadastro.setLayout(spring);
        validacao.setLayout(spring);
        contentPane.setLayout(card);

        int x = 20, y = 40;
        JPanel line = new JPanel();
        //line.add(new JLabel("\n"));
        line.setBackground(new Color(81, 98, 67));
        spring.putConstraint(SpringLayout.NORTH, line, 0, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, line, 0, SpringLayout.WEST, validacao);
        spring.putConstraint(SpringLayout.EAST, line, 0, SpringLayout.EAST, validacao);
        validacao.add(line);

        //Adicionar componenets ao painel de validacao
        validacao.add(pacienteJL);
        spring.putConstraint(SpringLayout.NORTH, pacienteJL, y + 3, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, pacienteJL, x + 20, SpringLayout.WEST, validacao);
        validacao.add(pacientes);
        spring.putConstraint(SpringLayout.NORTH, pacientes, y, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, pacientes, x + 180, SpringLayout.WEST, validacao);
        validacao.add(dataJL);
        spring.putConstraint(SpringLayout.NORTH, dataJL, y + 43, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, dataJL, x + 20, SpringLayout.WEST, validacao);
        validacao.add(dia);
        spring.putConstraint(SpringLayout.NORTH, dia, y + 40, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, dia, x + 180, SpringLayout.WEST, validacao);
        validacao.add(b1);
        spring.putConstraint(SpringLayout.NORTH, b1, y + 43, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, b1, x + 230, SpringLayout.WEST, validacao);
        validacao.add(mes);
        spring.putConstraint(SpringLayout.NORTH, mes, y + 40, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, mes, x + 240, SpringLayout.WEST, validacao);
        validacao.add(b2);
        spring.putConstraint(SpringLayout.NORTH, b2, y + 43, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, b2, x + 290, SpringLayout.WEST, validacao);
        validacao.add(ano);
        spring.putConstraint(SpringLayout.NORTH, ano, y + 40, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, ano, x + 300, SpringLayout.WEST, validacao);
        validacao.add(horarioJL);
        spring.putConstraint(SpringLayout.NORTH, horarioJL, y + 93, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, horarioJL, x + 20, SpringLayout.WEST, validacao);
        validacao.add(horas);
        spring.putConstraint(SpringLayout.NORTH, horas, y + 90, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, horas, x + 180, SpringLayout.WEST, validacao);
        validacao.add(pt);
        spring.putConstraint(SpringLayout.NORTH, pt, y + 90, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, pt, x + 230, SpringLayout.WEST, validacao);
        validacao.add(minutos);
        spring.putConstraint(SpringLayout.NORTH, minutos, y + 90, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, minutos, x + 240, SpringLayout.WEST, validacao);
        validacao.add(confirmar);
        spring.putConstraint(SpringLayout.NORTH, confirmar, y + 230, SpringLayout.NORTH, validacao);
        spring.putConstraint(SpringLayout.WEST, confirmar, x + 170, SpringLayout.WEST, validacao);

        //Adicionar componentes ao painel de cadastro
        JPanel line2 = new JPanel();
        //line.add(new JLabel("\n"));
        line2.setBackground(new Color(81, 98, 67));
        spring.putConstraint(SpringLayout.NORTH, line2, 0, SpringLayout.NORTH, cadastro);
        spring.putConstraint(SpringLayout.WEST, line2, 0, SpringLayout.WEST, cadastro);
        spring.putConstraint(SpringLayout.EAST, line2, 0, SpringLayout.EAST, cadastro);
        cadastro.add(line2);
        cadastro.add(queixasSP);
        spring.putConstraint(SpringLayout.NORTH, queixasSP, y + 10, SpringLayout.NORTH, cadastro);
        spring.putConstraint(SpringLayout.WEST, queixasSP, x + 20, SpringLayout.WEST, cadastro);
        cadastro.add(resumoExameSP);
        spring.putConstraint(SpringLayout.NORTH, resumoExameSP, y + 110, SpringLayout.NORTH, cadastro);
        spring.putConstraint(SpringLayout.WEST, resumoExameSP, x + 20, SpringLayout.WEST, cadastro);
        cadastro.add(resumoDiagnosticoSP);
        spring.putConstraint(SpringLayout.NORTH, resumoDiagnosticoSP, y + 210, SpringLayout.NORTH, cadastro);
        spring.putConstraint(SpringLayout.WEST, resumoDiagnosticoSP, x + 20, SpringLayout.WEST, cadastro);
        cadastro.add(tratamentoSP);
        spring.putConstraint(SpringLayout.NORTH, tratamentoSP, y + 310, SpringLayout.NORTH, cadastro);
        spring.putConstraint(SpringLayout.WEST, tratamentoSP, x + 20, SpringLayout.WEST, cadastro);
        cadastro.add(cadastrar);
        spring.putConstraint(SpringLayout.NORTH, cadastrar, y + 410, SpringLayout.NORTH, cadastro);
        spring.putConstraint(SpringLayout.WEST, cadastrar, x + 320, SpringLayout.WEST, cadastro);

        //Adicionar paineis ao Conainer com CardLayout
        contentPane.add(validacao, VALIDACA0);
        contentPane.add(cadastro, CADASTRO);

        //definir configuracoes da JFrame
        super.setTitle("Cadastro de dados médicos");
        super.setJMenuBar(barraMenu);
        super.setSize(500, 500);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setAlwaysOnTop(true);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (e.getSource().equals(confirmar)) {
                int pHoras, pMinutos, pDia, pMes, pAno;
                String pCod, codigo = "";

                pHoras = (int) horas.getItemAt(horas.getSelectedIndex());
                pMinutos = (int) minutos.getItemAt(minutos.getSelectedIndex());
                pDia = (int) dia.getItemAt(dia.getSelectedIndex());
                pMes = (int) mes.getItemAt(mes.getSelectedIndex());
                pAno = (int) ano.getItemAt(ano.getSelectedIndex());

                //Vou utilizar o codigo salvo no combo box
                //No combo Box cada item esta da seguite forma: 1234 - Nome do Paciente
                //Por isso irei "varrer" a string e pegar apenas o codigo
                pCod = (String) pacientes.getItemAt(pacientes.getSelectedIndex());
                int pos = pCod.indexOf("-");
                for (int i = 0; i < pos - 1; i++) {
                    codigo += pCod.charAt(i);
                }

                Consulta c = objControle.getCtrlConsulta().getConsulta(codigo, new Date(pAno, pMes, pDia, pHoras, pMinutos));
                if (c != null) {
                    card.show(contentPane, CADASTRO);
                } else {
                    JOptionPane.showMessageDialog(this, "O paciente não possui nenhuma consulta"
                            + "\nna data e horário indicados!");
                }
            } else {
                String queixas, tratamentos, resumoExame, resumoDiagnostico;
                int pHoras, pMinutos, pDia, pMes, pAno;
                String pCod, codigo = "";

                pHoras = (int) horas.getItemAt(horas.getSelectedIndex());
                pMinutos = (int) minutos.getItemAt(minutos.getSelectedIndex());
                pDia = (int) dia.getItemAt(dia.getSelectedIndex());
                pMes = (int) mes.getItemAt(mes.getSelectedIndex());
                pAno = (int) ano.getItemAt(ano.getSelectedIndex());

                queixas = queixasTA.getText();
                tratamentos = tratamentoTA.getText();
                resumoExame = resumoExameTA.getText();
                resumoDiagnostico = resumoDiagnosticoTA.getText();

                if (queixas.isEmpty() || tratamentos.isEmpty() || resumoExame.isEmpty() || resumoDiagnostico.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Você deve informar todos os dados solicitados!");
                } else {
                    //"Pegar o codigo" do paciente do TextField
                    pCod = (String) pacientes.getItemAt(pacientes.getSelectedIndex());
                    int pos = pCod.indexOf("-");
                    for (int i = 0; i < pos - 1; i++) {
                        codigo += pCod.charAt(i);
                    }

                    Paciente pac = objControle.getCtrlPaciente().getPaciente(codigo);

                    if (pac != null) {
                        //Cadastrar prontuario na lista de prontuarios
                        objControle.getCtrlProntuario().cadastrarProntuario(codigo, queixas, resumoExame, resumoDiagnostico, tratamentos, new Date(pAno, pMes, pDia, pHoras, pMinutos));
                        JOptionPane.showMessageDialog(this, "Dados médicos cadastrados!");

                        //Resetar entradas de dados para Default
                        pacientes.setSelectedIndex(0);
                        dia.setSelectedIndex(0);
                        mes.setSelectedIndex(0);
                        ano.setSelectedIndex(0);
                        horas.setSelectedIndex(0);
                        minutos.setSelectedIndex(0);
                        queixasTA.setText("");
                        resumoDiagnosticoTA.setText("");
                        resumoExameTA.setText("");
                        tratamentoTA.setText("");
                        card.show(contentPane, VALIDACA0);
                    } else {
                        JOptionPane.showMessageDialog(this, "Paciente não encontrado!");
                    }
                }
            }
        } else {
            int op = JOptionPane.showConfirmDialog(this, "Deseja encerrar o cadastro e sair?");

            if (op == JOptionPane.YES_OPTION) {
                try {
                    objControle.salvarDados();
                    super.dispose();
                } catch (Exception exc) {
                    System.out.println("O sistema falhou em salvar os dados cadastrados!");
                }
            }
        }
    }
}
