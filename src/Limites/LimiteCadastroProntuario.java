package Limites;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controladores.*;
import Entidades.*;

public class LimiteCadastroProntuario extends JFrame implements ActionListener{
    private ControlePrincipal objControle;
    private Container contentPane;
    private JPanel validacao,cadastro;
    private CardLayout card;
    private JLabel horarioJL,dataJL,pacienteJL,b1,b2,pt;
    private JComboBox dia,mes,ano,horas,minutos,pacientes;
    private JTextArea queixasTA,resumoDiagnosticoTA,resumoExameTA,tratamentoTA;
    private JScrollPane queixasSP,resumoDiagnosticoSP,resumoExameSP,tratamentoSP;
    private JMenuBar barraMenu;
    private JMenuItem encerrar;
    private JButton cadastrar,confirmar;
    
    public static final String CADASTRO = "CADASTRO DE PRONTUÁRIO";
    public static final String VALIDACA0 = "VALIDAÇÃO DE CONSULTA";
    public final ImageIcon icone = new ImageIcon("encerrar.png");

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
        queixasTA = new JTextArea(3,40);
        queixasTA.setToolTipText("Neste campo devem ser registradas as queixas informadas pelo paciente");
        resumoDiagnosticoTA = new JTextArea(3,40);
        resumoDiagnosticoTA.setToolTipText("Neste campo deve ser registrado o resumo do diagnóstico");
        resumoExameTA = new JTextArea(3,40);
        resumoExameTA.setToolTipText("Neste campo deve ser registrado o resumo do exame clínico");
        tratamentoTA = new JTextArea(3,40);
        tratamentoTA.setToolTipText("Neste campo deve ser registrado(s) o(s) tratamento(s) prescrito(s)");
        
        //Criar os objetos do tipo JComboBox
        dia = new JComboBox();
        mes = new JComboBox();
        ano = new JComboBox();
        horas = new JComboBox();
        minutos = new JComboBox();
        pacientes = new JComboBox(objControle.getCtrlPaciente().getDescricaoPacientes());
        
        //Adicionar itens aos objetos do tipo ComboBox
        for(int i=1 ; i<=30 ; i++)
        {
            dia.addItem(i);
        }
        for(int i=1 ; i<=12 ; i++)
        {
            mes.addItem(i);
        }
        ano.addItem(2016);
        ano.addItem(2017);
        ano.addItem(2018);
        for(int i=8 ; i<=17 ; i++)
        {
            horas.addItem(i);
            
            if(i == 11)
                i+=2;
        }
        minutos = new JComboBox();
        minutos.addItem("00");
        minutos.addItem("30");
        
        //Criar JButton e adicionar listener a ele
        cadastrar = new JButton("Cadastrar dados médicos");
        cadastrar.addActionListener(this);
        confirmar = new JButton("Buscar consulta");
        confirmar.addActionListener(this);
        
        //Criar JMenuBar e JMenuItem
        barraMenu = new JMenuBar();
        encerrar = new JMenuItem("Encerrar o cadastro e sair",icone);
        encerrar.addActionListener(this);
        barraMenu.add(encerrar);
        
        //Criar os objetos do tipo JScrollPane e configurar barra de rolagem
        //Serao utilizadas bordas com titulo ao inves de JLabel's
        queixasSP = new JScrollPane();
        queixasSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        queixasSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Queixas:"));
        resumoDiagnosticoSP = new JScrollPane();
        resumoDiagnosticoSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resumoDiagnosticoSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Resumo do diagnóstico:"));
        resumoExameSP = new JScrollPane();
        resumoExameSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resumoExameSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Resumo do Exame:"));
        tratamentoSP = new JScrollPane();
        tratamentoSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tratamentoSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Tratamento:"));
        
        //Adicionar componentes aos Paineis com barras de rolagem
        queixasSP.getViewport().add(queixasTA);
        resumoDiagnosticoSP.getViewport().add(resumoDiagnosticoTA);
        resumoExameSP.getViewport().add(resumoExameTA);
        tratamentoSP.getViewport().add(tratamentoTA);
        
        //Criar Gerenciadores de Layout
        SpringLayout spring = new SpringLayout();
        card = new CardLayout();
        
        //Obter o painel padrao da JFrame e definir gerenciador de Layout
        contentPane = super.getContentPane();
        
        //Definir gerenciadores de layout para cada Pael ou container
        cadastro.setLayout(spring);
        validacao.setLayout(spring);
        contentPane.setLayout(card);
        
        //Adicionar componenets ao paineld e validacao
        validacao.add(dataJL);
        spring.putConstraint(SpringLayout.NORTH,dataJL,220,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,dataJL,205,SpringLayout.WEST,validacao);
        validacao.add(dia);
        spring.putConstraint(SpringLayout.NORTH,dia,217,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,dia,390,SpringLayout.WEST,validacao);
        validacao.add(b1);
        spring.putConstraint(SpringLayout.NORTH,b1,220,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,b1,445,SpringLayout.WEST,validacao);
        validacao.add(mes);
        spring.putConstraint(SpringLayout.NORTH,mes,217,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,mes,460,SpringLayout.WEST,validacao);
        validacao.add(b2);
        spring.putConstraint(SpringLayout.NORTH,b2,220,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,b2,515,SpringLayout.WEST,validacao);
        validacao.add(ano);
        spring.putConstraint(SpringLayout.NORTH,ano,217,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,ano,530,SpringLayout.WEST,validacao);
        validacao.add(horarioJL);
        spring.putConstraint(SpringLayout.NORTH,horarioJL,273,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,horarioJL,205,SpringLayout.WEST,validacao);
        validacao.add(horas);
        spring.putConstraint(SpringLayout.NORTH,horas,270,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,horas,390,SpringLayout.WEST,validacao);
        validacao.add(pt);
        spring.putConstraint(SpringLayout.NORTH,pt,273,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,pt,445,SpringLayout.WEST,validacao);
        validacao.add(minutos);
        spring.putConstraint(SpringLayout.NORTH,minutos,270,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,minutos,460,SpringLayout.WEST,validacao);
        validacao.add(confirmar);
        spring.putConstraint(SpringLayout.NORTH,confirmar,335,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,confirmar,325,SpringLayout.WEST,validacao);
        validacao.add(pacienteJL);
        spring.putConstraint(SpringLayout.NORTH,pacienteJL,100,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,pacienteJL,220,SpringLayout.WEST,validacao);
        validacao.add(pacientes);
        spring.putConstraint(SpringLayout.NORTH,pacientes,97,SpringLayout.NORTH,validacao);
        spring.putConstraint(SpringLayout.WEST,pacientes,390,SpringLayout.WEST,validacao);
        
        //Adicionar componentes ao painel de cadastro
        cadastro.add(queixasSP);
        spring.putConstraint(SpringLayout.NORTH,queixasSP,50,SpringLayout.NORTH,cadastro);
        spring.putConstraint(SpringLayout.WEST,queixasSP,170,SpringLayout.WEST,cadastro);
        cadastro.add(resumoExameSP);
        spring.putConstraint(SpringLayout.NORTH,resumoExameSP,150,SpringLayout.NORTH,cadastro);
        spring.putConstraint(SpringLayout.WEST,resumoExameSP,170,SpringLayout.WEST,cadastro);
        cadastro.add(resumoDiagnosticoSP);
        spring.putConstraint(SpringLayout.NORTH,resumoDiagnosticoSP,250,SpringLayout.NORTH,cadastro);
        spring.putConstraint(SpringLayout.WEST,resumoDiagnosticoSP,170,SpringLayout.WEST,cadastro);
        cadastro.add(tratamentoSP);
        spring.putConstraint(SpringLayout.NORTH,tratamentoSP,350,SpringLayout.NORTH,cadastro);
        spring.putConstraint(SpringLayout.WEST,tratamentoSP,170,SpringLayout.WEST,cadastro);
        cadastro.add(cadastrar);
        spring.putConstraint(SpringLayout.NORTH,cadastrar,450,SpringLayout.NORTH,cadastro);
        spring.putConstraint(SpringLayout.WEST,cadastrar,300,SpringLayout.WEST,cadastro);
        
        contentPane.add(validacao,VALIDACA0);
        contentPane.add(cadastro,CADASTRO);
        
        super.setTitle("Cadastro de dados médicos");
        super.setJMenuBar(barraMenu);
        super.setSize(800, 600);
        super.setLocation(450, 70);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton)
        {
            if(e.getSource().equals(confirmar))
            {
                int pHoras,pMinutos,pDia,pMes,pAno;
                String pCod,codigo = "";
                
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
                for(int i=0 ; i<pos-1 ; i++)
                {
                    codigo += pCod.charAt(i);
                }
                
                Consulta c = objControle.getCtrlConsulta().getConsulta(codigo,new Date(pAno,pMes,pDia,pHoras,pMinutos));
                if(c != null) {
                    card.show(contentPane, CADASTRO);
                } else
                    JOptionPane.showMessageDialog(this, "O paciente não possui nenhuma consulta"
                            + "\nna data e horário indicados!");
            }
            else
            {
                String queixas,tratamentos,resumoExame,resumoDiagnostico;
                int pHoras,pMinutos,pDia,pMes,pAno;
                String pCod,codigo = "";
                
                pHoras = (int) horas.getItemAt(horas.getSelectedIndex());
                pMinutos = (int) minutos.getItemAt(minutos.getSelectedIndex());
                pDia = (int) dia.getItemAt(dia.getSelectedIndex());
                pMes = (int) mes.getItemAt(mes.getSelectedIndex());
                pAno = (int) ano.getItemAt(ano.getSelectedIndex());
                
                queixas = queixasTA.getText();
                tratamentos = tratamentoTA.getText();
                resumoExame = resumoExameTA.getText();
                resumoDiagnostico = resumoDiagnosticoTA.getText();
                
                if(queixas.isEmpty() || tratamentos.isEmpty() || resumoExame.isEmpty() || resumoDiagnostico.isEmpty())
                    JOptionPane.showMessageDialog(this, "Você deve informar todos os dados solicitados!");
                else
                {
                    //"Pegar o codigo" do paciente do TextField
                    pCod = (String) pacientes.getItemAt(pacientes.getSelectedIndex());                
                    int pos = pCod.indexOf("-");                
                    for(int i=0 ; i<pos-1 ; i++)
                    {
                        codigo += pCod.charAt(i);
                    }
                    //Cadastrar o prontuario nos dados do paciente
                    boolean test = objControle.getCtrlPaciente().adicionarProntuario(codigo, queixas, resumoExame, resumoDiagnostico, tratamentos,new Date(pAno,pMes,pDia,pHoras,pMinutos));
                    
                    if(test)
                    {
                        //Cadastrar prontuario na lista de prontuarios
                        objControle.getCtrlProntuario().cadastrarProntuario(codigo,queixas, resumoExame, resumoDiagnostico, tratamentos,new Date(pAno,pMes,pDia,pHoras,pMinutos));
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
                        card.show(contentPane,VALIDACA0);
                    }
                }
            }
        }
        else
        {
            int op = JOptionPane.showConfirmDialog(this, "Deseja encerrar o cadastro e sair?");
            
            if(op == JOptionPane.YES_OPTION)
            {
                try{
                    objControle.salvarDados();
                    super.dispose();
                }catch(Exception exc){
                    System.out.println("O sistema falhou em salvar os dados cadastrados!");
                }
            }
        }
    }
}
