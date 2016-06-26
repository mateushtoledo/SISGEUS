package Limites;

import Controladores.ControlePaciente;
import Entidades.Paciente;
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

public class LimiteCadastroPaciente extends JFrame implements ActionListener{
    private ControlePaciente controlador;
    private JPanel painel;
    private Container contentPane;
    private JTextField nomeTF,numBeneficiarioTF,enderecoTF,telefoneTF,anoTF;
    private JLabel nomeJL,numBeneficiarioJL,enderecoJL,telefoneJL,nascimentoJL,sexoJL,barra1JL,barra2JL;
    private JComboBox dia,mes,sexo;
    private JButton cadastrar;

    public LimiteCadastroPaciente(ControlePaciente pCtrl){
        controlador = pCtrl;
        
        //Criar Label's
        nomeJL = new JLabel("Nome : ");
        numBeneficiarioJL = new JLabel("Número de beneficiário : ");
        enderecoJL = new JLabel("Endereço : ");
        telefoneJL = new JLabel("Telefone : ");
        nascimentoJL = new JLabel("Data de nascimento (DD/MM/AAAA) : ");
        sexoJL = new JLabel("Sexo : ");
        barra1JL = new JLabel("/");
        barra2JL = new JLabel("/");
        
        //Criar todos os objetos do tipo ComboBox
        dia = new JComboBox();
        for(int i=1; i<=30 ; i++)
        {
            dia.addItem(+i);
        }
        
        mes = new JComboBox();
        for(int i=1 ; i<=12 ; i++)
        {
            mes.addItem(+i);
        }
        
        sexo = new JComboBox();
        sexo.addItem("Masculino");
        sexo.addItem("Feminino");
        
        //Criar todos os objetos do tipo TextField
        nomeTF = new JTextField(20);
        numBeneficiarioTF = new JTextField(8);
        enderecoTF = new JTextField(22);
        telefoneTF = new JTextField(8);
        anoTF = new JTextField(4);
        
        //Criar todos os objetos do tipo Button
        cadastrar = new JButton("Cadastrar paciente");
        cadastrar.setBackground(Color.cyan);
        cadastrar.addActionListener(this);
        
        //criar Painel a adicionar layout mananger a ele
        SpringLayout spring = new SpringLayout();
        painel = new JPanel(spring);
        
        //Adicionar componentes ao painel
        painel.add(nomeJL);
        spring.putConstraint(SpringLayout.NORTH,nomeJL,130,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,nomeJL,140,SpringLayout.WEST,painel);
        painel.add(nomeTF);
        spring.putConstraint(SpringLayout.NORTH,nomeTF,130,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,nomeTF,192,SpringLayout.WEST,painel);
        painel.add(sexoJL);
        spring.putConstraint(SpringLayout.NORTH,sexoJL,130,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,sexoJL,490,SpringLayout.WEST,painel);
        painel.add(sexo);
        spring.putConstraint(SpringLayout.NORTH,sexo,128,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,sexo,540,SpringLayout.WEST,painel);
        painel.add(numBeneficiarioJL);
        spring.putConstraint(SpringLayout.NORTH,numBeneficiarioJL,190,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,numBeneficiarioJL,140,SpringLayout.WEST,painel);
        painel.add(numBeneficiarioTF);
        spring.putConstraint(SpringLayout.NORTH,numBeneficiarioTF,190,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,numBeneficiarioTF,320,SpringLayout.WEST,painel);
        painel.add(telefoneJL);
        spring.putConstraint(SpringLayout.NORTH,telefoneJL,190,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,telefoneJL,470,SpringLayout.WEST,painel);
        painel.add(telefoneTF);
        spring.putConstraint(SpringLayout.NORTH,telefoneTF,190,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,telefoneTF,545,SpringLayout.WEST,painel);
        painel.add(enderecoJL);
        spring.putConstraint(SpringLayout.NORTH,enderecoJL,250,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,enderecoJL,140,SpringLayout.WEST,painel);
        painel.add(enderecoTF);
        spring.putConstraint(SpringLayout.NORTH,enderecoTF,250,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,enderecoTF,225,SpringLayout.WEST,painel);
        painel.add(nascimentoJL);
        spring.putConstraint(SpringLayout.NORTH,nascimentoJL,310,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,nascimentoJL,140,SpringLayout.WEST,painel);
        painel.add(dia);
        spring.putConstraint(SpringLayout.NORTH,dia,308,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,dia,410,SpringLayout.WEST,painel);
        painel.add(barra1JL);
        spring.putConstraint(SpringLayout.NORTH,barra1JL,310,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,barra1JL,460,SpringLayout.WEST,painel);
        painel.add(mes);
        spring.putConstraint(SpringLayout.NORTH,mes,308,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,mes,470,SpringLayout.WEST,painel);
        painel.add(barra2JL);
        spring.putConstraint(SpringLayout.NORTH,barra2JL,310,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,barra2JL,520,SpringLayout.WEST,painel);
        painel.add(anoTF);
        spring.putConstraint(SpringLayout.NORTH,anoTF,310,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,anoTF,530,SpringLayout.WEST,painel);
        painel.add(cadastrar);
        spring.putConstraint(SpringLayout.NORTH,cadastrar,390,SpringLayout.NORTH,painel);
        spring.putConstraint(SpringLayout.WEST,cadastrar,300,SpringLayout.WEST,painel);
        
        //Colocar esse painel no painel padrao da JFrame (Para utilizar BorderLayout)
        contentPane = super.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.X_AXIS));
        contentPane.add(Box.createHorizontalGlue());
        contentPane.add(painel);
        contentPane.add(Box.createHorizontalGlue());
        
        //Setar configuracoes da JFrame
        super.setTitle("Cadastro de paciente");
        super.setSize(800,600);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocation(450,70);
        super.setAlwaysOnTop(true);
        super.setVisible(true);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Esse metodo sera acionado quando o usuario clicar no botao para cadastrar paciente
        String nome,endereco,numBene,telefone,ano,pSexo;
        int diaI,mesI,anoI;
        
        diaI = dia.getSelectedIndex()+1;
        mesI = mes.getSelectedIndex()+1;
        
        nome = nomeTF.getText();
        endereco = enderecoTF.getText();
        numBene = numBeneficiarioTF.getText();
        telefone = telefoneTF.getText();
        ano = anoTF.getText();
        
        if(sexo.getSelectedIndex() == 0)
            pSexo = Paciente.MASCULINO;
        else
            pSexo = Paciente.FEMININO;
        
        if(nome.isEmpty() || endereco.isEmpty() || numBene.isEmpty() || telefone.isEmpty() || ano.isEmpty())
        {
            JOptionPane.showMessageDialog(contentPane,"Você não preencheu algum campo!");
        }
        else
        {
            anoI = Integer.parseInt(ano);
                int esc = JOptionPane.showConfirmDialog(contentPane,"Você confirma a validade dos dados informados?","Validação dos dados",JOptionPane.YES_NO_CANCEL_OPTION);
                
                if(esc == JOptionPane.YES_OPTION)
                {
                    Date data = new Date(anoI,mesI,diaI);
                    controlador.cadastrarPaciente(nome, numBene, pSexo, endereco, telefone, data);
                    
                    JOptionPane.showMessageDialog(contentPane,"Paciente cadastrado!");
                    //Limpar campos de entrada de dados
                    nomeTF.setText("");
                    enderecoTF.setText("");
                    anoTF.setText("");
                    numBeneficiarioTF.setText("");
                    dia.setSelectedIndex(0);
                    mes.setSelectedIndex(0);
                    sexo.setSelectedIndex(0);
                }
        }
    }
}
