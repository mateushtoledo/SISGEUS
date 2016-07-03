package Limites;

import Controladores.ControlePrincipal;
import Entidades.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LimiteHistoricoConsultas extends JFrame implements ActionListener {

    private ControlePrincipal objControlador;
    private Prontuario pron;
    private ArrayList<Consulta> consultas;
    private CardLayout card;
    private DefaultListModel dadosConsultas;
    private JList listaConsultas;
    private Container contentPane;
    private JPanel painel1, painel2, sub1, sub2, sub3;
    private JLabel nomePacienteJL, numBeneJL;
    private JTextField numBeneTF;
    private JTextArea queixasTA, resumoDiagnosticoTA, resumoExameTA, tratamentoTA;
    private JScrollPane consultasSP, queixasSP, resumoDiagnosticoSP, resumoExameSP, tratamentoSP;
    private JButton buscar, voltar;

    public final String PAINEL1 = "SELECAO";
    public final String PAINEL2 = "EXIBICAO";

    public LimiteHistoricoConsultas(ControlePrincipal pCtrl) {
        objControlador = pCtrl;

        //Criar os objetos do tipo JLabel
        nomePacienteJL = new JLabel("Você ainda não buscou por nenhum paciente!");
        nomePacienteJL.setForeground(Color.red);
        numBeneJL = new JLabel("Número de beneficiário:");

        //Criar objeto do tipo JTextField
        numBeneTF = new JTextField(22);

        //Criar o objeto do tipo DefaultListModel
        dadosConsultas = new DefaultListModel();

        //Criar o objeto do tipo JList
        listaConsultas = new JList(dadosConsultas);
        listaConsultas.setFixedCellWidth(500);
        listaConsultas.setFixedCellHeight(20);
        listaConsultas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int indice = listaConsultas.getSelectedIndex();

                if (indice >= 0) {
                    String desc = (String) listaConsultas.getSelectedValue();

                    if (!desc.equals("Esse paciente não possui consultas marcadas no sistema")) {
                        pron = objControlador.getCtrlProntuario().getProntuario(consultas.get(indice).getNumBeneficiarioPaciente(), consultas.get(indice).getData());

                        if (pron == null) {
                            JOptionPane.showMessageDialog(contentPane, "Essa consulta não foi realizada ainda!");
                        } else {
                            queixasTA.setText(pron.getQueixas());
                            resumoExameTA.setText(pron.getResumoExame());
                            resumoDiagnosticoTA.setText(pron.getResumoDiagnostico());
                            tratamentoTA.setText(pron.getTratamentos());
                            card.show(contentPane, PAINEL2);
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        //Criar os objetos do tipo JButton e adicionar Listener a eles
        buscar = new JButton("Buscar paciente");
        buscar.setForeground(Color.red);
        buscar.addActionListener(this);
        voltar = new JButton("Voltar para visualização de consultas");
        voltar.setForeground(Color.red);
        voltar.addActionListener(this);

        //Criar os objetos do tipo JTextArea
        queixasTA = new JTextArea(3, 40);
        queixasTA.setEditable(false);
        resumoDiagnosticoTA = new JTextArea(3, 40);
        resumoDiagnosticoTA.setEditable(false);
        resumoExameTA = new JTextArea(3, 40);
        resumoExameTA.setEditable(false);
        tratamentoTA = new JTextArea(3, 40);
        tratamentoTA.setEditable(false);

        //Criar os objetos do tipo JScrollPane e configurar barra de rolagem
        //Serao utilizadas bordas com titulo ao inves de JLabel's
        consultasSP = new JScrollPane();
        consultasSP.setPreferredSize(new Dimension(600, 400));
        consultasSP.setMinimumSize(new Dimension(320, 240));
        consultasSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        consultasSP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Histórico de consultas:"));
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
        consultasSP.getViewport().add(listaConsultas);
        queixasSP.getViewport().add(queixasTA);
        resumoDiagnosticoSP.getViewport().add(resumoDiagnosticoTA);
        resumoExameSP.getViewport().add(resumoExameTA);
        tratamentoSP.getViewport().add(tratamentoTA);

        //Criar o gerenciador de Layout
        SpringLayout spring1 = new SpringLayout();

        //Criar o painel de cadastro de funcionário e adicionar componentes a ele
        //Criar os objetos do tipo JPanel
        painel1 = new JPanel();
        painel1.setLayout(spring1);

        int x = 20, y = 20;

        JPanel line = new JPanel();
        //line.add(new JLabel("\n"));
        line.setBackground(new Color(81, 98, 67));
        painel1.add(line);
        spring1.putConstraint(SpringLayout.NORTH, line, 0, SpringLayout.NORTH, painel1);
        spring1.putConstraint(SpringLayout.WEST, line, 0, SpringLayout.WEST, painel1);
        spring1.putConstraint(SpringLayout.EAST, line, 0, SpringLayout.EAST, painel1);

        spring1.putConstraint(SpringLayout.NORTH, numBeneJL, y + 20, SpringLayout.NORTH, painel1);
        spring1.putConstraint(SpringLayout.WEST, numBeneJL, x + 20, SpringLayout.WEST, painel1);
        painel1.add(numBeneJL);
        spring1.putConstraint(SpringLayout.NORTH, numBeneTF, y + 20, SpringLayout.NORTH, painel1);
        spring1.putConstraint(SpringLayout.WEST, numBeneTF, x + 220, SpringLayout.WEST, painel1);
        painel1.add(numBeneTF);
        spring1.putConstraint(SpringLayout.NORTH, buscar, y + 18, SpringLayout.NORTH, painel1);
        spring1.putConstraint(SpringLayout.WEST, buscar, x + 490, SpringLayout.WEST, painel1);
        painel1.add(buscar);
        spring1.putConstraint(SpringLayout.NORTH, nomePacienteJL, y + 70, SpringLayout.NORTH, painel1);
        spring1.putConstraint(SpringLayout.WEST, nomePacienteJL, x + 210, SpringLayout.WEST, painel1);
        painel1.add(nomePacienteJL);
        spring1.putConstraint(SpringLayout.NORTH, consultasSP, y + 110, SpringLayout.NORTH, painel1);
        spring1.putConstraint(SpringLayout.WEST, consultasSP, x + 20, SpringLayout.WEST, painel1);
        painel1.add(consultasSP);

        //Criar gerenciador de Layout para o painel que exibe os dados
        //Medicos
        painel2 = new JPanel();
        SpringLayout spring = new SpringLayout();
        painel2.setLayout(spring);

        JPanel line2 = new JPanel();
        //line.add(new JLabel("\n"));
        line2.setBackground(new Color(81, 98, 67));
        painel2.add(line2);
        spring.putConstraint(SpringLayout.NORTH, line2, 0, SpringLayout.NORTH, painel2);
        spring.putConstraint(SpringLayout.WEST, line2, 0, SpringLayout.WEST, painel2);
        spring.putConstraint(SpringLayout.EAST, line2, 0, SpringLayout.EAST, painel2);
        //Adicionar os componentes ao painel que exibe os dados medicos
        //Adicionar componentes ao painel de cadastro
        painel2.add(queixasSP);
        spring.putConstraint(SpringLayout.NORTH, queixasSP, y + 55, SpringLayout.NORTH, painel2);
        spring.putConstraint(SpringLayout.WEST, queixasSP, x+20, SpringLayout.WEST, painel2);
        painel2.add(resumoExameSP);
        spring.putConstraint(SpringLayout.NORTH, resumoExameSP, y +  155, SpringLayout.NORTH, painel2);
        spring.putConstraint(SpringLayout.WEST, resumoExameSP, x+20, SpringLayout.WEST, painel2);
        painel2.add(resumoDiagnosticoSP);
        spring.putConstraint(SpringLayout.NORTH, resumoDiagnosticoSP, y + 255, SpringLayout.NORTH, painel2);
        spring.putConstraint(SpringLayout.WEST, resumoDiagnosticoSP, x+20, SpringLayout.WEST, painel2);
        painel2.add(tratamentoSP);
        spring.putConstraint(SpringLayout.NORTH, tratamentoSP, y + 355, SpringLayout.NORTH, painel2);
        spring.putConstraint(SpringLayout.WEST, tratamentoSP, x+20, SpringLayout.WEST, painel2);
        painel2.add(voltar);
        spring.putConstraint(SpringLayout.NORTH, voltar, y + 475, SpringLayout.NORTH, painel2);
        spring.putConstraint(SpringLayout.WEST, voltar, x+20, SpringLayout.WEST, painel2);

        //Obter container da JFrame e adicionar componentes a ele
        card = new CardLayout();
        contentPane = super.getContentPane();
        contentPane.setBackground(Color.red);
        contentPane.setLayout(card);
        contentPane.add(painel1, PAINEL1);
        contentPane.add(painel2, PAINEL2);

        //Definir configuracoes da JFrame
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setSize(680, 600);
        super.setLocation(310, 70);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setTitle("Histório de consultas e dados médicos");
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (e.getSource().equals(voltar)) {
                card.show(contentPane, PAINEL1);
            } else {
                String bene = numBeneTF.getText();

                if (bene.isEmpty()) {
                    nomePacienteJL.setText("Você deve informar um número de beneficiário!");
                    dadosConsultas.removeAllElements();
                } else {
                    Paciente pac = objControlador.getCtrlPaciente().getPaciente(bene);

                    if (pac == null) {
                        nomePacienteJL.setText("Nenhum paciente encontrado!");
                        dadosConsultas.removeAllElements();
                    } else {
                        consultas = objControlador.getCtrlConsulta().getConsultasPaciente(bene);
                        nomePacienteJL.setText("Paciente: " + pac.getNome());
                        if (consultas.isEmpty()) {
                            dadosConsultas.removeAllElements();
                            dadosConsultas.addElement("Esse paciente não possui consultas marcadas no sistema");
                        } else {
                            dadosConsultas.removeAllElements();
                            Object obj[];
                            obj = objControlador.getCtrlConsulta().getDescricaoConsultas(consultas);

                            for (Object o : obj) {
                                dadosConsultas.addElement(o);
                            }
                        }
                    }
                }
            }
        }
    }
}
