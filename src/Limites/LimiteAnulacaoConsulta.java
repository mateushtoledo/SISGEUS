package Limites;

import Controladores.*;
import Entidades.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class LimiteAnulacaoConsulta extends JFrame implements ActionListener {

    private ControlePrincipal objController;
    private Container contentPane;
    private JPanel buscarConsulta, anularConsulta;
    private SpringLayout spring;
    private CardLayout card;
    private JLabel numBeneJL, dataJL, horarioJL, motivoJL, step1JL, step2JL, b1, b2, pt;
    private JTextField motivoTF, numBeneTF;
    private JComboBox diaCB, mesCB, anoCB, horasCB, minutosCB;
    private JButton buscarCon, anularCon;
    private Consulta c = null;

    //Constantes criadas para auxiliar na utilizacao do CardLayout
    public static final String ANULAR = "ANULAR";
    public static final String BUSCAR = "BUSCAR";

    public LimiteAnulacaoConsulta(ControlePrincipal pCtrl) {
        objController = pCtrl;

        //Criar os objetos do tipo JLabel
        numBeneJL = new JLabel("Número de beneficiário:");
        dataJL = new JLabel("Data da consulta:");
        horarioJL = new JLabel("Horário da consulta:");
        motivoJL = new JLabel("Informe o motivo da anulação:");
        step1JL = new JLabel("Passo 1 de 2: Buscar a consulta que será anulada");
        step1JL.setForeground(Color.red);
        step2JL = new JLabel("Passo 2 de 2: Obter motivo e registrar anulação");
        step2JL.setForeground(Color.red);
        b1 = new JLabel("/");
        b2 = new JLabel("/");
        pt = new JLabel(":");

        //Criar os objetos do tipo JTextField
        motivoTF = new JTextField(25);
        numBeneTF = new JTextField(21);

        //Criar os objetos do tipo JComboBox
        diaCB = new JComboBox();
        mesCB = new JComboBox();
        anoCB = new JComboBox();
        horasCB = new JComboBox();
        minutosCB = new JComboBox();

        //Criar os itens exibidos em cada ComboBox
        //ComboBox que contem os dias do mes (1 a 30)
        for (int i = 1; i <= 30; i++) {
            diaCB.addItem(i);
        }
        //ComboBox que contem os meses do ano (1 a 12)
        for (int i = 1; i <= 12; i++) {
            mesCB.addItem(i);
        }
        //ComboBox que contem os anos validos
        anoCB.addItem(2016);
        anoCB.addItem(2017);
        anoCB.addItem(2018);
        //ComboBox que contem as horas de consulta válidas
        for (int i = 8; i <= 17; i++) {
            horasCB.addItem(i);

            if (i == 11) {
                i += 2;
            }
        }
        //ComboBox que contem os minutos (que sao associados as horas)
        minutosCB.addItem(00);
        minutosCB.addItem(30);

        //Criar os objetos do tipo JButton e adicionar Listener a eles
        buscarCon = new JButton("Buscar consulta");
        buscarCon.setForeground(Color.red);
        buscarCon.addActionListener(this);
        anularCon = new JButton("Anular consulta");
        anularCon.setForeground(Color.red);
        anularCon.addActionListener(this);

        //Criar os objetos do tipo JPanel e utilizarContainer padrao da JFrame
        contentPane = super.getContentPane();
        buscarConsulta = new JPanel();
        anularConsulta = new JPanel();

        //Criar gerenciadores de Layout
        spring = new SpringLayout();
        card = new CardLayout();

        //Definir gerenciadores de Layout para cada painel
        contentPane.setLayout(card);
        buscarConsulta.setLayout(spring);
        anularConsulta.setLayout(spring);

        //Adicionar componentes ao painel
        int x = 20, y = 40;
        //Adicionar componentes ao painel        line.setBackground(new Color(81, 98, 67));
        JPanel line = new JPanel();
        line.setBackground(new Color(81, 98, 67));
        spring.putConstraint(SpringLayout.NORTH, line, 0, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, line, 0, SpringLayout.WEST, buscarConsulta);
        spring.putConstraint(SpringLayout.EAST, line, 0, SpringLayout.EAST, buscarConsulta);
        buscarConsulta.add(line);

        //Adicionar componentes ao painel que realiza busca por consulta y- 180 x-160
        buscarConsulta.add(step1JL);
        spring.putConstraint(SpringLayout.NORTH, step1JL, y - 10, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, step1JL, x + 20, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(numBeneJL);
        spring.putConstraint(SpringLayout.NORTH, numBeneJL, y + 40, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, numBeneJL, x + 20, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(numBeneTF);
        spring.putConstraint(SpringLayout.NORTH, numBeneTF, y + 40, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, numBeneTF, x + 190, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(dataJL);
        spring.putConstraint(SpringLayout.NORTH, dataJL, y + 83, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, dataJL, x + 20, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(diaCB);
        spring.putConstraint(SpringLayout.NORTH, diaCB, y + 80, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, diaCB, x + 190, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(b1);
        spring.putConstraint(SpringLayout.NORTH, b1, y + 80, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, b1, x + 240, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(mesCB);
        spring.putConstraint(SpringLayout.NORTH, mesCB, y + 80, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, mesCB, x + 250, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(b2);
        spring.putConstraint(SpringLayout.NORTH, b2, y + 80, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, b2, x + 300, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(anoCB);
        spring.putConstraint(SpringLayout.NORTH, anoCB, y + 80, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, anoCB, x + 310, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(horarioJL);
        spring.putConstraint(SpringLayout.NORTH, horarioJL, y + 120, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, horarioJL, x + 20, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(horasCB);
        spring.putConstraint(SpringLayout.NORTH, horasCB, y + 120, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, horasCB, x + 190, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(pt);
        spring.putConstraint(SpringLayout.NORTH, pt, y + 123, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, pt, x + 240, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(minutosCB);
        spring.putConstraint(SpringLayout.NORTH, minutosCB, y + 120, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, minutosCB, x + 250, SpringLayout.WEST, buscarConsulta);
        buscarConsulta.add(buscarCon);
        spring.putConstraint(SpringLayout.NORTH, buscarCon, y + 200, SpringLayout.NORTH, buscarConsulta);
        spring.putConstraint(SpringLayout.WEST, buscarCon, x + 320, SpringLayout.WEST, buscarConsulta);

        //Adicionar componentes ao painel que le os dados referentes a anulacao
        anularConsulta.add(step2JL);
        spring.putConstraint(SpringLayout.NORTH, step2JL, y - 10, SpringLayout.NORTH, anularConsulta);
        spring.putConstraint(SpringLayout.WEST, step2JL, x + 20, SpringLayout.WEST, anularConsulta);
        anularConsulta.add(motivoJL);
        spring.putConstraint(SpringLayout.NORTH, motivoJL, y + 40, SpringLayout.NORTH, anularConsulta);
        spring.putConstraint(SpringLayout.WEST, motivoJL, x + 20, SpringLayout.WEST, anularConsulta);
        anularConsulta.add(motivoTF);
        spring.putConstraint(SpringLayout.NORTH, motivoTF, y + 40, SpringLayout.NORTH, anularConsulta);
        spring.putConstraint(SpringLayout.WEST, motivoTF, x + 190, SpringLayout.WEST, anularConsulta);
        anularConsulta.add(anularCon);
        spring.putConstraint(SpringLayout.NORTH, anularCon, y + 200, SpringLayout.NORTH, anularConsulta);
        spring.putConstraint(SpringLayout.WEST, anularCon, x + 320, SpringLayout.WEST, anularConsulta);

        //Adicioar os 2 paineis ao painel principal (com card Layout)
        contentPane.add(buscarConsulta, BUSCAR);
        contentPane.add(anularConsulta, ANULAR);
        card.show(contentPane, BUSCAR);

        //Definir configuracoes da JFrame
        super.setTitle("Anular consulta");
        super.setAlwaysOnTop(true);
        super.setVisible(true);
        super.setSize(500, 350);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        JButton auxiliar = (JButton) e.getSource();

        if (auxiliar.equals(buscarCon)) {
            String numBene = numBeneTF.getText();

            if (numBene.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Você deve informar o número de beneficiário do paciente!");
            } else {
                String horas;
                int pDia, pMes, pAno, pHoras, pMinutos;

                pHoras = (int) horasCB.getItemAt(horasCB.getSelectedIndex());
                pMinutos = (int) minutosCB.getItemAt(minutosCB.getSelectedIndex());
                pDia = (int) diaCB.getItemAt(diaCB.getSelectedIndex());
                pMes = (int) mesCB.getItemAt(mesCB.getSelectedIndex());
                pAno = (int) anoCB.getItemAt(anoCB.getSelectedIndex());

                c = objController.getCtrlConsulta().getConsulta(numBene, new Date(pAno, pMes, pDia, pHoras, pMinutos));

                if (c == null) {
                    JOptionPane.showMessageDialog(this, "Nenhuma consulta encontrada!");
                } else {
                    pHoras = JOptionPane.showConfirmDialog(this, "Deseja anular a consulta da especialidade " + c.getEspecialidade() + " encontrada?");

                    if (pHoras == JOptionPane.YES_OPTION) {
                        card.show(contentPane, ANULAR);
                    }
                }
            }
        } else {
            String motivo = motivoTF.getText();

            if (motivo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Você deve informar o motivo da anulação da consulta!");
            } else {
                objController.getCtrlConsulta().anularConsulta(c);
                objController.getCtrFuncionario().anularConsultaDaAgendaDoMedico(c);
                objController.getCtrlAnulacao().cadastrarAnulacao(c, new Date(), motivo);
                JOptionPane.showMessageDialog(this, "Consulta anulada!");
                super.dispose();
            }
        }
    }
}
