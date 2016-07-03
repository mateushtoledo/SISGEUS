package Limites;

import Entidades.*;
import Controladores.ControlePrincipal;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class LimiteMarcarConsulta extends JFrame implements ActionListener {

    private ControlePrincipal objControlador;
    private JPanel painel;
    private JComboBox dia, mes, ano, horas, minutos;
    private JLabel dataJL, horarioJL, nomePacienteJL, especialidadeJL, motivoJL, barra1JL, barra2JL, pontosJL;
    private JTextField nomePacienteTF, especialidadeTF, motivoTF;
    private JButton marcarConsulta;

    public LimiteMarcarConsulta(ControlePrincipal pCtrl) {
        objControlador = pCtrl;

        //Criar objetos do tipo JLabel
        dataJL = new JLabel("Data da consulta:");
        horarioJL = new JLabel("Horário da consulta:");
        nomePacienteJL = new JLabel("Nº do beneficiário:");
        especialidadeJL = new JLabel("Especialidade da consulta :");
        motivoJL = new JLabel("Motivo da consulta :");
        barra1JL = new JLabel("/");
        barra2JL = new JLabel("/");
        pontosJL = new JLabel(":");

        //Criar objetos do tipo ComboBox
        dia = new JComboBox();
        for (int i = 1; i <= 30; i++) {
            dia.addItem(i);
        }
        mes = new JComboBox();
        for (int i = 1; i <= 12; i++) {
            mes.addItem(i);
        }
        ano = new JComboBox();
        ano.addItem(2016);
        ano.addItem(2017);
        ano.addItem(2018);
        horas = new JComboBox();
        for (int i = 8; i <= 17; i++) {
            horas.addItem(i);

            if (i == 11) {
                i += 2;
            }
        }
        minutos = new JComboBox();
        minutos.addItem("00");
        minutos.addItem("30");

        //Criar ojetos do tipo TextField
        nomePacienteTF = new JTextField(24);
        especialidadeTF = new JTextField(24);
        motivoTF = new JTextField(24);

        //Criar Botao e adicionar listener a ele
        marcarConsulta = new JButton("Marcar consulta");
        //marcarConsulta.setForeground(Color.red);
        marcarConsulta.addActionListener(this);

        //Criar painel
        painel = new JPanel();

        //Criar gerenciador de Layout e adiciona-lo ao painel
        SpringLayout spring = new SpringLayout();
        painel.setLayout(spring);

        //Adicionar componentes ao painel
        int x = 50, y = 40;
        //Adicionar componentes ao painel        line.setBackground(new Color(81, 98, 67));
        JPanel line = new JPanel();
        line.setBackground(new Color(81, 98, 67));
        spring.putConstraint(SpringLayout.NORTH, line, 0, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, line, 0, SpringLayout.WEST, painel);
        spring.putConstraint(SpringLayout.EAST, line, 0, SpringLayout.EAST, painel);
        painel.add(line);

        spring.putConstraint(SpringLayout.NORTH, nomePacienteJL, y + 10, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, nomePacienteJL, x - 20, SpringLayout.WEST, painel);
        painel.add(nomePacienteJL);
        spring.putConstraint(SpringLayout.NORTH, nomePacienteTF, y + 10, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, nomePacienteTF, x + 140, SpringLayout.WEST, painel);
        painel.add(nomePacienteTF);

        spring.putConstraint(SpringLayout.NORTH, motivoJL, y + 40, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, motivoJL, x - 20, SpringLayout.WEST, painel);
        painel.add(motivoJL);
        spring.putConstraint(SpringLayout.NORTH, motivoTF, y + 40, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, motivoTF, x + 140, SpringLayout.WEST, painel);
        painel.add(motivoTF);

        painel.add(dataJL);
        spring.putConstraint(SpringLayout.NORTH, dataJL, y + 73, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, dataJL, x - 20, SpringLayout.WEST, painel);
        painel.add(dia);
        spring.putConstraint(SpringLayout.NORTH, dia, y + 70, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, dia, x + 140, SpringLayout.WEST, painel);
        painel.add(barra1JL);
        spring.putConstraint(SpringLayout.NORTH, barra1JL, y + 73, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, barra1JL, x + 190, SpringLayout.WEST, painel);
        painel.add(mes);
        spring.putConstraint(SpringLayout.NORTH, mes, y + 70, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, mes, x + 200, SpringLayout.WEST, painel);
        painel.add(barra2JL);
        spring.putConstraint(SpringLayout.NORTH, barra2JL, y + 73, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, barra2JL, x + 250, SpringLayout.WEST, painel);
        painel.add(ano);
        spring.putConstraint(SpringLayout.NORTH, ano, y + 70, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, ano, x + 260, SpringLayout.WEST, painel);
        painel.add(horarioJL);
        spring.putConstraint(SpringLayout.NORTH, horarioJL, y + 113, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, horarioJL, x - 20, SpringLayout.WEST, painel);
        painel.add(horas);
        spring.putConstraint(SpringLayout.NORTH, horas, y + 110, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, horas, x + 140, SpringLayout.WEST, painel);
        painel.add(pontosJL);
        spring.putConstraint(SpringLayout.NORTH, pontosJL, y + 113, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, pontosJL, x + 190, SpringLayout.WEST, painel);
        painel.add(minutos);
        spring.putConstraint(SpringLayout.NORTH, minutos, y + 110, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, minutos, x + 200, SpringLayout.WEST, painel);
        painel.add(especialidadeJL);
        spring.putConstraint(SpringLayout.NORTH, especialidadeJL, y + 140, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, especialidadeJL, x - 20, SpringLayout.WEST, painel);
        painel.add(especialidadeTF);
        spring.putConstraint(SpringLayout.NORTH, especialidadeTF, y + 140, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, especialidadeTF, x + 140, SpringLayout.WEST, painel);
        painel.add(marcarConsulta);
        spring.putConstraint(SpringLayout.NORTH, marcarConsulta, y + 220, SpringLayout.NORTH, painel);
        spring.putConstraint(SpringLayout.WEST, marcarConsulta, x + 280, SpringLayout.WEST, painel);

        //Definir configruacoes da JFrame e adicionar painel
        super.setTitle("Marcar consulta");
        super.add(painel);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setSize(500, 350);
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        super.setAlwaysOnTop(true);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Paciente pac;
        Medico doctor;
        String pNumBeneficiario, pMotivo, pEspecialidade, pNumFuncional;
        int pDia, pMes, pAno, pHoras, pMinutos, escolha;
        Date pData;

        pNumBeneficiario = nomePacienteTF.getText();
        pMotivo = motivoTF.getText();
        pEspecialidade = especialidadeTF.getText();

        if (pNumBeneficiario.isEmpty() || pMotivo.isEmpty() || pEspecialidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Você deve preencher todos os campos!");
        } else {
            pac = objControlador.getCtrlPaciente().getPaciente(pNumBeneficiario);

            if (pac == null) {
                JOptionPane.showMessageDialog(this, "Não foi encontrado nenhum paciente\n"
                        + "cadastrado com esse número de eneficiário!");
            } else {
                //Ler os dados restantes necessarios para marcar consulta
                //Data e horario
                //Identificacao do funcionario que marcou
                //Data da marcacao (new Date == data atual do SO)
                pDia = dia.getSelectedIndex() + 1;
                pMes = mes.getSelectedIndex() + 1;

                if (ano.getSelectedIndex() == 0) {
                    pAno = 2016;
                } else if (ano.getSelectedIndex() == 1) {
                    pAno = 2017;
                } else {
                    pAno = 2018;
                }
                pNumFuncional = objControlador.getIdentificacaoFuncionario();
                //Como adicionei os itens no comboBox como inteiros
                //Basta fazer o Typecast
                pHoras = (int) horas.getItemAt(horas.getSelectedIndex());

                if (minutos.getSelectedIndex() == 0) {
                    pMinutos = 0;
                } else {
                    pMinutos = 30;
                }
                pData = new Date(pAno, pMes, pDia, pHoras, pMinutos);
                //Verificar se foi localizado um medico e marcada a consulta na agenda dele
                doctor = objControlador.getCtrFuncionario().marcarConsulta(pEspecialidade, pNumBeneficiario, pMotivo, pNumFuncional, pData, new Date());

                if (doctor == null) {
                    JOptionPane.showMessageDialog(this, "Não há nenhum médico disponível para essa consulta!");
                } else {
                    //objControlador.getCtrlConsulta().adicionarConsulta(pEspecialidade, pNumBeneficiario, pMotivo, pNumFuncional, pData, new Date());
                    JOptionPane.showMessageDialog(this, "Consulta marcada com o médico(a) " + doctor.getNome() + "!");
                    //Limpar os campos de entrada de texto
                    nomePacienteTF.setText("");
                    motivoTF.setText("");
                    especialidadeTF.setText("");
                }
            }
        }

    }

}
