package Controladores;

import java.util.*;
import java.io.*;
import Entidades.*;
import Limites.*;

public class ControlePaciente {
    private ArrayList<Paciente> objPacientes;
    private ArrayList<Paciente> objFalecidos;

    public ControlePaciente() {
        objPacientes = new ArrayList<>();
        objFalecidos = new ArrayList<>();
    }
    
    //Metodo que adiciona paciente ao sistema
    public void cadastrarPaciente(String pNome,String pNumBeneficiaro,String pSexo,String pEndereco,String pTelefone,Date pNascimento)
    {
        objPacientes.add(new Paciente(pNome, pNumBeneficiaro, pSexo, pEndereco, pTelefone, pNascimento));
    }
    
    //Metodo que cadastra o falecimento de determinado paciente.
    //O falecimento nao implica na exclusao dos dados, mas sim em salva-los
    //separados dos demais.
    public void cadastrarFalecimento(String pNumBeneficiario)
    {
        for(int i=0 ; i<objPacientes.size() ; i++)
        {
            if(objPacientes.get(i).getNumBeneficiario().equalsIgnoreCase(pNumBeneficiario))
            {
                objFalecidos.add(objPacientes.get(i));
                objPacientes.remove(i);
            }
        }
    }
    
    //Metodo que realiza a alteracao do endereco do paciente
    public void alterarEndereco(Paciente pac,String pEndereco)
    {
        pac.setEndereco(pEndereco);
    }
    
    //Metodo que realiza a alteracao do telefone do paciente
    public void alterarTelefone(Paciente pac,String pTelefone)
    {
        pac.setTelefone(pTelefone);
    }
    
    //Metodo que retorna a lista de pacientes salva no sistema
    public ArrayList<Paciente> getListaPacientes()
    {
        return objPacientes;
    }
    
    //Metodo que retorna uma descricao dos pacientes contidos no sistema em 
    //Vetor de objetos.
    //Utilizado para criar objetos do tipo combo Box nas interfaces
    public Object[] getDescricaoPacientes()
    {
        Object itens[] = new Object[10000];
        int i=0;
        
        for(Paciente pac : objPacientes)
        {
            String p = pac.getNumBeneficiario()+" - "+pac.getNome();
            itens[i] = p;
            i++;
        }
        
        Object desc[] = new Object[i];
        
        System.arraycopy(itens, 0, desc, 0, i);
        
        return desc;
    }
    
    //Retorna o paciente que contem o numero de beneficiario passado (ou nulo)
    public Paciente getPaciente(String pNumBeneficiario)
    {
        for(Paciente pac : objPacientes)
        {
            if(pac.getNumBeneficiario().equals(pNumBeneficiario))
                return pac;
        }
        
        return null;
    }
    
    //Metodo que exibe interface para alteracao dos dados de contato do paciente
    public void interfaceAlteracaoDadosContato()
    {
        new LimiteAltDadosPaciente(this);
    }
    
    //Metodo que exibe interface para realizar cadastro de paciente
    public void interfaceCadastroPaciente()
    {
        new LimiteCadastroPaciente(this);
    }
    
    //Metodo que salva a lista de pacientes em arquivo de texto
    public void salvarPacientes() throws Exception
    {
        FileOutputStream fileOs = new FileOutputStream("pacientes.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objPacientes);
        
        fileOs = new FileOutputStream("pacientesfalecidos.dat");
        objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objFalecidos);
        
        objOs.flush();
        objOs.close();
    }
    
    //Metodo que recupera a lista de pacientes do sistema salva em arquivo de texto
    public void recuperarPacientes() throws Exception
    {
        File arquivo = new File("pacientes.dat");
        
        //Recuperar Dados dos pacientes ativos no sistema
        if(arquivo.exists())
        {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objPacientes = (ArrayList<Paciente>)objIs.readObject();
            objIs.close();
        }
        
        //Recuperar dados dos pacientes falecidos
        arquivo = new File("pacientesfalecidos.dat");
        if(arquivo.exists())
        {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objFalecidos = (ArrayList<Paciente>)objIs.readObject();
            objIs.close();
        }
    }
}
