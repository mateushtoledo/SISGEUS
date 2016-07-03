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
    private JPanel painelFalecimento;
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
        aviso = new JLabel(" O cadastro do falecimento implica na anulação de todas as consultas do paciente ");
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
        cadastrar.setForeground(Color.red);
        cadastrar.addActionListener(this);

        //Criar JTextField
        motivoTF = new JTextField(30);

        //Criar o gerenciador de Layout
        SpringLayout spring = new SpringLayout();

        //Criar o painel de cadastro de funcionário e adicionar componentes a ele
        painelFalecimento = new JPanel();
        painelFalecimento.setLayout(spring);

        int x = 20, y = 20;

        JPanel line = new JPanel();
        //line.add(new JLabel("\n"));
        line.setBackground(new Color(81, 98, 67));
        painelFalecimento.add(line);
        spring.putConstraint(SpringLayout.NORTH, line, 0, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, line, 0, SpringLayout.WEST, painelFalecimento);
        spring.putConstraint(SpringLayout.EAST, line, 0, SpringLayout.EAST, painelFalecimento);

        spring.putConstraint(SpringLayout.NORTH, aviso, y + 10, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, aviso, x + 20, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(aviso);
        spring.putConstraint(SpringLayout.NORTH, pacienteJL, y + 63, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, pacienteJL, x + 20, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(pacienteJL);
        spring.putConstraint(SpringLayout.NORTH, pacientes, y + 60, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, pacientes, x + 220, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(pacientes);

        spring.putConstraint(SpringLayout.NORTH, data, y + 113, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, data, x + 20, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(data);
        spring.putConstraint(SpringLayout.NORTH, dia, y + 110, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, dia, x + 160, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(dia);
        spring.putConstraint(SpringLayout.NORTH, b1, y + 113, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, b1, x + 210, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(b1);
        spring.putConstraint(SpringLayout.NORTH, mes, y + 110, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, mes, x + 220, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(mes);
        spring.putConstraint(SpringLayout.NORTH, b2, y + 113, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, b2, x + 270, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(b2);
        spring.putConstraint(SpringLayout.NORTH, ano, y + 110, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, ano, x + 280, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(ano);
        spring.putConstraint(SpringLayout.NORTH, motivo, y + 160, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, motivo, x + 20, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(motivo);
        spring.putConstraint(SpringLayout.NORTH, motivoTF, y + 160, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, motivoTF, x + 160, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(motivoTF);
        spring.putConstraint(SpringLayout.NORTH, cadastrar, y + 250, SpringLayout.NORTH, painelFalecimento);
        spring.putConstraint(SpringLayout.WEST, cadastrar, x + 335, SpringLayout.WEST, painelFalecimento);
        painelFalecimento.add(cadastrar);

        add(painelFalecimento);
        //Definir configuracoes da JFrame
        super.setTitle("Cadastrar falecimento de paciente");
        super.setSize(560, 350);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setAlwaysOnTop(true);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int op = JOptionPane.showConfirmDialog(this, "Deseja cadastrar o falecimento desse paciente?");

        if (op == JOptionPane.YES_OPTION) {
            String pMotivo = motivoTF.getText();
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
                    objControlador.getCtrFuncionario().anularConsultaDaAgendaDoMedico(c);
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
            objControlador.getCtrlPaciente().cadastrarFalecimento(codigo);
            //Devo cadastrar o falecimento dele

            if (pMotivo.isEmpty()) {
                objControlador.getCtrlFalecimento().cadastrarFalecimento(pDia, pMes, pAno);
                JOptionPane.showMessageDialog(this, "Falecimento cadastrado!");
                super.dispose();
            } else {
                objControlador.getCtrlFalecimento().cadastrarFalecimento(pDia, pMes, pAno, pMotivo);
                JOptionPane.showMessageDialog(this, "Falecimento cadastrado!");
                super.dispose();
            }
        }
    }
}
