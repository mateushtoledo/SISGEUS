package Limites;

import Controladores.ControleConsulta;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LimiteAgendaPorEspecialidade extends JFrame implements ActionListener {

    private ControleConsulta objControlador;
    private JComboBox diaInicial, diaFinal, mesInicial, mesFinal, anoInicial, anoFinal;
    private JLabel dataInicial, dataFinal, icone, b1, b2, b3, b4;
    private JTextArea exibicao;
    private JScrollPane painelRolagem;
    private JButton exibirEventos;
    private final ImageIcon busca = new ImageIcon("imgs/busca.png");

    public LimiteAgendaPorEspecialidade(ControleConsulta pCtrl) {
        objControlador = pCtrl;

        //Criar os objetos do tipo JLabel
        dataInicial = new JLabel("Data Inicial:");
        dataFinal = new JLabel("Data Final:");
        b1 = new JLabel("/");
        b2 = new JLabel("/");
        b3 = new JLabel("/");
        b4 = new JLabel("/");
        icone = new JLabel(busca);

        //Criar os objetos do tipo JComboBox e adicionar seus respectivos itens
        diaInicial = new JComboBox();
        for (int i = 1; i <= 30; i++) {
            diaInicial.addItem(i);
        }
        diaFinal = new JComboBox();
        for (int i = 1; i <= 30; i++) {
            diaFinal.addItem(i);
        }
        mesInicial = new JComboBox();
        for (int i = 1; i <= 12; i++) {
            mesInicial.addItem(i);
        }
        mesFinal = new JComboBox();
        for (int i = 1; i <= 12; i++) {
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
        exibicao = new JTextArea(20, 60);
        exibicao.setText("Você não buscou ainda!");
        exibicao.setEditable(false);
        painelRolagem = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        painelRolagem.getViewport().add(exibicao);
        painelRolagem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consultas no período: "));

        //Criar botao e adicionar Listener a ele
        exibirEventos = new JButton("Listar consultas");
        exibirEventos.setForeground(Color.red);
        exibirEventos.addActionListener(this);

        //Criar gerenciador de Layout e "painel" onde serao adicionados os componentes
        SpringLayout spring = new SpringLayout();
        Container contentPane = super.getContentPane();
        contentPane.setLayout(spring);

        int x = -10, y = -30;
        JPanel line = new JPanel();
        //line.add(new JLabel("\n"));
        line.setBackground(new Color(81, 98, 67));
        spring.putConstraint(SpringLayout.NORTH, line, 0, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, line, 0, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.EAST, line, 0, SpringLayout.EAST, contentPane);
        contentPane.add(line);

        //Adicionar componentes ao "painel" (Container padrao da JFrame)
        contentPane.add(icone);
        spring.putConstraint(SpringLayout.NORTH, icone, y + 50, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, icone, x +30, SpringLayout.WEST, contentPane);
        contentPane.add(dataInicial);
        spring.putConstraint(SpringLayout.NORTH, dataInicial, y + 63, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, dataInicial, x + 150, SpringLayout.WEST, contentPane);
        contentPane.add(diaInicial);
        spring.putConstraint(SpringLayout.NORTH, diaInicial, y + 60, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, diaInicial, x + 240, SpringLayout.WEST, contentPane);
        contentPane.add(b1);
        spring.putConstraint(SpringLayout.NORTH, b1, y + 63, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, b1, x + 290, SpringLayout.WEST, contentPane);
        contentPane.add(mesInicial);
        spring.putConstraint(SpringLayout.NORTH, mesInicial, y + 60, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, mesInicial, x + 300, SpringLayout.WEST, contentPane);
        contentPane.add(b2);
        spring.putConstraint(SpringLayout.NORTH, b2, y + 63, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, b2, x + 350, SpringLayout.WEST, contentPane);
        contentPane.add(anoInicial);
        spring.putConstraint(SpringLayout.NORTH, anoInicial, y + 60, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, anoInicial, x + 360, SpringLayout.WEST, contentPane);
        contentPane.add(dataFinal);
        spring.putConstraint(SpringLayout.NORTH, dataFinal, y + 63, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, dataFinal, x + 440, SpringLayout.WEST, contentPane);
        contentPane.add(diaFinal);
        spring.putConstraint(SpringLayout.NORTH, diaFinal, y + 60, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, diaFinal, x + 520, SpringLayout.WEST, contentPane);
        contentPane.add(b3);
        spring.putConstraint(SpringLayout.NORTH, b3, y + 63, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, b3, x + 570, SpringLayout.WEST, contentPane);
        contentPane.add(mesFinal);
        spring.putConstraint(SpringLayout.NORTH, mesFinal, y + 60, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, mesFinal, x + 580, SpringLayout.WEST, contentPane);
        contentPane.add(b4);
        spring.putConstraint(SpringLayout.NORTH, b4, y + 63, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, b4, x + 630, SpringLayout.WEST, contentPane);
        contentPane.add(anoFinal);
        spring.putConstraint(SpringLayout.NORTH, anoFinal, y + 60, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, anoFinal, x + 640, SpringLayout.WEST, contentPane);
        contentPane.add(exibirEventos);
        spring.putConstraint(SpringLayout.NORTH, exibirEventos, y + 60, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, exibirEventos, x + 730, SpringLayout.WEST, contentPane);

        contentPane.add(painelRolagem);
        spring.putConstraint(SpringLayout.NORTH, painelRolagem, y + 100, SpringLayout.NORTH, contentPane);
        spring.putConstraint(SpringLayout.WEST, painelRolagem, x + 150, SpringLayout.WEST, contentPane);
        //Definir algumas configuracoes da JFrame
        super.setTitle("Agenda da unidade ordenada por especialidade");
        super.setSize(870, 470);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setAlwaysOnTop(true);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int pDiaI, pMesI, pAnoI, pDiaF, pMesF, pAnoF;
        Date ini, fim;

        pDiaI = (int) diaInicial.getItemAt(diaInicial.getSelectedIndex());
        pMesI = (int) mesInicial.getItemAt(mesInicial.getSelectedIndex());
        pAnoI = (int) anoInicial.getItemAt(anoInicial.getSelectedIndex());
        pDiaF = (int) diaFinal.getItemAt(diaFinal.getSelectedIndex());
        pMesF = (int) mesFinal.getItemAt(mesFinal.getSelectedIndex());
        pAnoF = (int) anoFinal.getItemAt(anoFinal.getSelectedIndex());

        ini = new Date(pAnoI, pMesI, pDiaI);
        fim = new Date(pAnoF, pMesF, pDiaF);

        if (ini.compareTo(fim) >= 0) {
            JOptionPane.showMessageDialog(this, "Período de busca informado inválido!");
        } else {
            String p = objControlador.getConsultasPeriodoAgrupadas(ini, fim);

            if (p.isEmpty()) {
                exibicao.setText("Nenhuma consulta no período informado");
            } else {
                exibicao.setText(p);
            }
        }
    }
}
