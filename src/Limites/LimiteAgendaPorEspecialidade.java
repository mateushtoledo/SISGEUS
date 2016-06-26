package Limites;

import Controladores.ControleConsulta;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LimiteAgendaPorEspecialidade extends JFrame implements ActionListener{
    private ControleConsulta objControlador;
    private JComboBox diaInicial,diaFinal,mesInicial,mesFinal,anoInicial,anoFinal;
    private JLabel dataInicial,dataFinal,icone,b1,b2,b3,b4;
    private JTextArea exibicao;
    private JScrollPane painelRolagem;
    private JButton exibirEventos;
    private final ImageIcon busca = new ImageIcon("busca.png");

    public LimiteAgendaPorEspecialidade(ControleConsulta pCtrl) {
        objControlador = pCtrl;
        
        //Criar os JLabel's
        dataInicial = new JLabel("Data inicial:");
        dataFinal = new JLabel("Data final:");
        b1 = new JLabel("/");
        b2 = new JLabel("/");
        b3 = new JLabel("/");
        b4 = new JLabel("/");
        icone = new JLabel(busca);
        
        //Criar os comboBox e adicionar seus devidos itens
        diaInicial = new JComboBox();
        for(int i=1 ; i<=30 ; i++)
        {
            diaInicial.addItem(i);
        }
        diaFinal = new JComboBox();
        for(int i=1 ; i<=30 ; i++)
        {
            diaFinal.addItem(i);
        }
        mesInicial = new JComboBox();
        for(int i=1 ; i<=12 ; i++)
        {
            mesInicial.addItem(i);
        }
        mesFinal = new JComboBox();
        for(int i=1 ; i<=12 ; i++)
        {
            mesFinal.addItem(i);
        }
        anoInicial = new JComboBox();
        anoInicial.addItem(2016);
        anoInicial.addItem(2017);
        anoInicial.addItem(2018);
        anoFinal = new JComboBox();
        anoFinal.addItem(2016);
        anoFinal.addItem(2017);
        anoFinal.addItem(2018);
        
        //Criar ScrollPane onde serao exibidas as consultas
        exibicao = new JTextArea(20,50);
        exibicao.setText("Você não buscou ainda!");
        exibicao.setEditable(false);
        painelRolagem = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        painelRolagem.getViewport().add(exibicao);
        painelRolagem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consultas no período: "));
        
        //Criar botao e adicionar Listener a ele
        exibirEventos = new JButton("Listar consultas");
        exibirEventos.setForeground(Color.red);
        exibirEventos.addActionListener(this);
        
        //Criar gerenciador de Layout e painel onde serao adicionados os componentes
        SpringLayout spring = new SpringLayout();
        Container contentPane = super.getContentPane();
        contentPane.setLayout(spring);
        
        //Adicionar componentes ao painel (Container padrao da JFrame)
        contentPane.add(painelRolagem);
        spring.putConstraint(SpringLayout.NORTH,painelRolagem,200,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,painelRolagem,130,SpringLayout.WEST,contentPane);
        contentPane.add(icone);
        spring.putConstraint(SpringLayout.NORTH,icone,40,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,icone,130,SpringLayout.WEST,contentPane);
        contentPane.add(dataInicial);
        spring.putConstraint(SpringLayout.NORTH,dataInicial,65,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,dataInicial,270,SpringLayout.WEST,contentPane);
        contentPane.add(diaInicial);
        spring.putConstraint(SpringLayout.NORTH,diaInicial,63,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,diaInicial,390,SpringLayout.WEST,contentPane);
        contentPane.add(b1);
        spring.putConstraint(SpringLayout.NORTH,b1,65,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,b1,445,SpringLayout.WEST,contentPane);
        contentPane.add(mesInicial);
        spring.putConstraint(SpringLayout.NORTH,mesInicial,63,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,mesInicial,460,SpringLayout.WEST,contentPane);
        contentPane.add(b2);
        spring.putConstraint(SpringLayout.NORTH,b2,65,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,b2,510,SpringLayout.WEST,contentPane);
        contentPane.add(anoInicial);
        spring.putConstraint(SpringLayout.NORTH,anoInicial,63,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,anoInicial,525,SpringLayout.WEST,contentPane);
        contentPane.add(dataFinal);
        spring.putConstraint(SpringLayout.NORTH,dataFinal,100,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,dataFinal,270,SpringLayout.WEST,contentPane);
        contentPane.add(diaFinal);
        spring.putConstraint(SpringLayout.NORTH,diaFinal,97,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,diaFinal,390,SpringLayout.WEST,contentPane);
        contentPane.add(b3);
        spring.putConstraint(SpringLayout.NORTH,b3,100,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,b3,445,SpringLayout.WEST,contentPane);
        contentPane.add(mesFinal);
        spring.putConstraint(SpringLayout.NORTH,mesFinal,97,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,mesFinal,460,SpringLayout.WEST,contentPane);
        contentPane.add(b4);
        spring.putConstraint(SpringLayout.NORTH,b4,100,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,b4,510,SpringLayout.WEST,contentPane);
        contentPane.add(anoFinal);
        spring.putConstraint(SpringLayout.NORTH,anoFinal,97,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,anoFinal,525,SpringLayout.WEST,contentPane);
        contentPane.add(exibirEventos);
        spring.putConstraint(SpringLayout.NORTH,exibirEventos,145,SpringLayout.NORTH,contentPane);
        spring.putConstraint(SpringLayout.WEST,exibirEventos,435,SpringLayout.WEST,contentPane);
        
        
        super.setTitle("Agenda da unidade ordenada por especialidade");
        super.setSize(800, 600);
        super.setLocation(450, 70);
        super.setAlwaysOnTop(true);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int pDiaI,pMesI,pAnoI,pDiaF,pMesF,pAnoF;
        Date ini,fim;
        
        pDiaI = (int) diaInicial.getItemAt(diaInicial.getSelectedIndex());
        pMesI = (int) mesInicial.getItemAt(mesInicial.getSelectedIndex());
        pAnoI = (int) anoInicial.getItemAt(anoInicial.getSelectedIndex());
        pDiaF = (int) diaFinal.getItemAt(diaFinal.getSelectedIndex());
        pMesF = (int) mesFinal.getItemAt(mesFinal.getSelectedIndex());
        pAnoF = (int) anoFinal.getItemAt(anoFinal.getSelectedIndex());
        
        ini = new Date(pAnoI,pMesI,pDiaI);
        fim = new Date(pAnoF,pMesF,pDiaF);
        
        if(ini.compareTo(fim) >=0)
            JOptionPane.showMessageDialog(this, "Período de busca informado inválido!");
        else
        {
            String p = objControlador.getConsultasPeriodoAgrupadas(ini, fim);
            
            if(p.isEmpty())
                exibicao.setText("Nenhuma consulta no período informado");
            else
                exibicao.setText(p);
        }
    }
}
