package Limites;

import Controladores.ControlePrincipal;
import Entidades.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LimiteCadastroFalecimento extends JFrame implements ActionListener {

    private ControlePrincipal objControlador;
    private Container contentPane;
    private JPanel selecionarPac, informarData, informarMotivo, botaoCadastro, avisoP;
    private JLabel pacienteJL, data, motivo, aviso, b1, b2;
    private JTextField motivoTF;
    private JComboBox dia, mes, ano, pacientes;
    private JButton cadastrar;

    public LimiteCadastroFalecimento(ControlePrincipal pCtrl) {
        objControlador = pCtrl;

        //Criar os objetos do tipo JLabel
        b1 = new JLabel("/");
        b2 = new JLabel("/");
        pacienteJL = new JLabel("Selecione o paciente falecido:");
        data = new JLabel("Data do falecimento:");
        motivo = new JLabel("Motivo:");
        aviso = new JLabel("O cadastro do falecimento implica na anulação de todas as consultas do paciente");
        aviso.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Aviso: "));

        //Criar os objetos do tipo comboBox
        dia = new JComboBox();
        mes = new JComboBox();
        ano = new JComboBox();
        pacientes = new JComboBox(objControlador.getCtrlPaciente().getDescricaoPacientes());

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

        //Criar JButton
        cadastrar = new JButton("Cadastrar falecimento");
        cadastrar.addActionListener(this);

        //Criar JTextField
        motivoTF = new JTextField(26);

        //Criar objetos do tipo JPanel
        avisoP = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        selecionarPac = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        informarData = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        informarMotivo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botaoCadastro = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Obter Container padrao da JFrame e definir gerenciador de Layout
        contentPane = super.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        //Adicionar componentes aos seus devidos paineis
        avisoP.add(aviso);
        selecionarPac.add(pacienteJL);
        selecionarPac.add(pacientes);
        informarData.add(data);
        informarData.add(dia);
        informarData.add(b1);
        informarData.add(mes);
        informarData.add(b2);
        informarData.add(ano);
        informarMotivo.add(motivo);
        informarMotivo.add(motivoTF);
        botaoCadastro.add(cadastrar);
        contentPane.add(avisoP);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(selecionarPac);
        contentPane.add(informarData);
        contentPane.add(informarMotivo);
        contentPane.add(botaoCadastro);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());

        super.setTitle("Cadastrar falecimento de paciente");
        super.setSize(800, 600);
        super.setLocation(450, 70);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int op = JOptionPane.showConfirmDialog(this, "Deseja cadastrar om falecimento desse paciente?");

        if (op == JOptionPane.YES_OPTION) {
            String motivo = motivoTF.getText();
            String codigo = "", aux;
            int pDia, pMes, pAno;

            pDia = (int) dia.getItemAt(dia.getSelectedIndex());
            pMes = (int) mes.getItemAt(mes.getSelectedIndex());
            pAno = (int) ano.getItemAt(ano.getSelectedIndex());

            aux = (String) pacientes.getItemAt(pacientes.getSelectedIndex());
            int pos = aux.indexOf("-");
            for (int i = 0; i < pos - 1; i++) {
                codigo += aux.charAt(i);
            }

            //Passo 1: Obter consultas desse paciente cadastradas
            ArrayList<Consulta> eventos = objControlador.getCtrlConsulta().getConsultasPaciente(codigo);

            if (!eventos.isEmpty()) {
                //Nesse caso devo remover as consultas da agenda do medico
                for (Consulta c : eventos) {
                    objControlador.getCtrlFuncionario().anularConsultaDaAgendaDoMedico(c);
                }
                //Devo cadastrar a anulacao de todas as consultas desse paciente
                //O motivo da anulacao sera a morte do paciente
                for (Consulta c : eventos) {
                    objControlador.getCtrlAnulacao().cadastrarAnulacao(c, new Date(pAno, pMes, pDia), "O paciente faleceu");
                }
                //Devo remover todas as consultas desse paciente da lista de consultas do sistema
                for (Consulta c : eventos) {
                    //Testar se esse metodo remove realmente funciona
                    objControlador.getCtrlConsulta().anularConsulta(c);
                }
            }
            objControlador.getCtrlPaciente().cadastrarFalecimentoNumBene(codigo);
            //Devo cadastrar o falecimento dele

            if (motivo.isEmpty()) {
                objControlador.getCtrlFalecimento().cadastrarFalecimento(pDia, pMes, pAno);
                JOptionPane.showMessageDialog(this, "Falecimento cadastrado!");
                super.dispose();
            } else {
                objControlador.getCtrlFalecimento().cadastrarFalecimento(pDia, pMes, pAno, motivo);
                JOptionPane.showMessageDialog(this, "Falecimento cadastrado!");
                super.dispose();
            }
        }
    }
}
