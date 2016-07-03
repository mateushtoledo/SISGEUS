package Controladores;

import java.util.*;
import java.io.*;
import Entidades.*;
import Limites.*;

public class ControlePaciente {

    private ArrayList<Paciente> objPacientes;
    private ArrayList<Paciente> objFalecidos;

    public ControlePaciente() {
        objPacientes = new ArrayList<Paciente>();
        objFalecidos = new ArrayList<Paciente>();
    }

    public void interfaceAlteracaoDadosContato() {
        new LimiteAltDadosPaciente(this);
    }

    public void interfaceCadastroPaciente() {
        new LimiteCadastroPaciente(this);
    }

    public void cadastrarPaciente(String pNome, String pNumBeneficiaro, String pSexo, String pEndereco, String pTelefone, Date pNascimento) {
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


    public void alterarEndereco(Paciente pac, String pEndereco) {
        pac.setEndereco(pEndereco);
    }

    public void alterarTelefone(Paciente pac, String pTelefone) {
        pac.setTelefone(pTelefone);
    }

    public ArrayList<Paciente> getListaPacientes() {
        return objPacientes;
    }

    public Object[] getDescricaoPacientes() {
        Object itens[] = new Object[10000];
        int i = 0;

        for (Paciente pac : objPacientes) {
            String p = pac.getNumBeneficiario() + " - " + pac.getNome();
            itens[i] = p;
            i++;
        }

        Object desc[] = new Object[i];

        System.arraycopy(itens, 0, desc, 0, i);

        return desc;
    }

    public Paciente getPaciente(String pNumBeneficiario) {
        for (Paciente pac : objPacientes) {
            if (pac.getNumBeneficiario().equals(pNumBeneficiario)) {
                return pac;
            }
        }

        return null;
    }

    public void salvarPacientes() throws Exception {
        FileOutputStream fileOs = new FileOutputStream("data/pacientes.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objPacientes);

        fileOs = new FileOutputStream("data/pacientesfalecidos.dat");
        objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objFalecidos);

        objOs.flush();
        objOs.close();
    }

    public void recuperarPacientes() throws Exception {
        File arquivo = new File("data/pacientes.dat");

        //Recuperar Dados dos pacientes ativos no sistema
        if (arquivo.exists()) {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objPacientes = (ArrayList<Paciente>) objIs.readObject();
            objIs.close();
        }

        //Recuperar dados dos pacientes falecidos
        arquivo = new File("data/pacientesfalecidos.dat");
        if (arquivo.exists()) {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objFalecidos = (ArrayList<Paciente>) objIs.readObject();
            objIs.close();
        }
    }

}
