package Limites;

import Controladores.*;
import Entidades.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimiteAltDadosPaciente extends JFrame implements ActionListener{
    private ControlePaciente controlador;
    private JTextField telefoneTF,enderecoTF,numBeneTF,mensagem;
    private JLabel telefoneJL,enderecoJL,numeroBeneJL;
    private JPanel painel;
    private JButton buscar,alterar;
    

    public LimiteAltDadosPaciente(ControlePaciente pCtrl) {
        controlador = pCtrl;
        
        //Criar os Label's
        telefoneJL = new JLabel("Telefone : ");
        enderecoJL = new JLabel("Endereço : ");
        numeroBeneJL = new JLabel("Número de beneficiário");
        
        //Criar os TextField's
        telefoneTF = new JTextField(20);
        enderecoTF = new JTextField(20);
        numBeneTF = new JTextField(12);
        mensagem = new JTextField(50);
        mensagem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red),"Aviso:"));
        mensagem.setText("Se você desejar alterar apenas um dos campos de contato basta deixar o outro vazio!");
        mensagem.setEditable(false);
        
        //Criar o JButton
        alterar = new JButton("Alterar dados");
        alterar.addActionListener(this);
        
        //Criar os SubPainéis
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.add(numeroBeneJL);
        top.add(numBeneTF);
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1.add(enderecoJL);
        p1.add(enderecoTF);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(telefoneJL);
        p2.add(telefoneTF);
        JPanel sub = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sub.add(alterar);
        JPanel mini = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mini.add(mensagem);
        
        painel = new JPanel(new GridLayout(4,1,0, 5));
        painel.add(top);
        painel.add(p1);
        painel.add(p2);
        painel.add(sub);
        
        //
        Container contentPane = super.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(mini);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(painel);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(Box.createVerticalGlue());
        
        //Setar configuracoes da JFrame
        super.setTitle("Alteração dos dados de contato de paciente");
        super.setSize(800,600);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocation(450,70);
        super.setAlwaysOnTop(true);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Paciente pac;
        String numBeneficiario,pEndereco,pTelefone;
        
        numBeneficiario = numBeneTF.getText();
        
        if(numBeneficiario.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Você deve informar o nome de beneficiário!");
        }
        else
        {
            pac = controlador.getPaciente(numBeneficiario);
            
            if(pac == null)
                JOptionPane.showMessageDialog(this, "Nenhum paciente cadastrado com esse número de beneficiário!");
            else
            {
                pEndereco = enderecoTF.getText();
                pTelefone = telefoneTF.getText();
                
                if(pEndereco.isEmpty() && pTelefone.isEmpty())
                    JOptionPane.showMessageDialog(this, "Você não informou que dado deve ser alterado!");
                else
                {
                    if(pEndereco.isEmpty())
                    {
                        //Se ele nao informou o endereco devo alterar apenas o telefone
                        controlador.alterarTelefone(pac, pTelefone);
                        JOptionPane.showMessageDialog(this, "Telefone alterado!");
                        super.dispose();
                    }
                    else
                    {
                        if(pTelefone.isEmpty())
                        {
                            //Se ele nao informou o telefone devo alterar apenas o endereco
                            controlador.alterarEndereco(pac, pEndereco);
                            JOptionPane.showMessageDialog(this, "Endereço alterado!");
                            super.dispose();
                        }
                        else
                        {
                            //Se ele informou os dois campos devo alterar as duas informacoes
                            controlador.alterarTelefone(pac, pTelefone);
                            controlador.alterarEndereco(pac, pEndereco);
                            JOptionPane.showMessageDialog(this, "Dados de contato alterados!");
                            super.dispose();
                        }
                    }
                }
            }
        }
    }
}
