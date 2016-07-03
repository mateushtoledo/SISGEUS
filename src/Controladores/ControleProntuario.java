package Controladores;

import java.util.*;
import java.io.*;
import Entidades.*;
import Limites.LimiteCadastroProntuario;

public class ControleProntuario {
    private ArrayList<Prontuario> objProntuarios;
    private ControlePrincipal objControle;

    public ControleProntuario(ControlePrincipal pControlador) {
        objControle = pControlador;
        objProntuarios = new ArrayList<>();
    }
    
    public void cadastrarProntuario(String pNumBeneficiario,String pQueixas,String pResumoExame,String pResumoDiagnostico,String pTratamentos,Date pData)
    {
        objProntuarios.add(new Prontuario(pNumBeneficiario,pQueixas, pResumoExame, pResumoDiagnostico, pTratamentos,pData));
    }
    
    //Retorna um prontuario de determinado paciente em determinada data
    public Prontuario getProntuario(String pNumBene,Date pData)
    {
        for(Prontuario p : objProntuarios)
        {
            if(p.getDataRegistro().equals(pData) && p.getNumBeneficiario().equals(pNumBene))
                return p;
        }
        
        return null;
    }
    
    //Metodo que exibe interface para realizar cadastro de prontuario
    public void interfaceCadastroProntuario()
    {
        new LimiteCadastroProntuario(objControle);
    }
    
    //Metodo que salva os dados de prontarios do sistema em arquivo de texto
    public void salvarProntuarios() throws Exception
    {
        FileOutputStream fileOs = new FileOutputStream("data/prontuarios.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objProntuarios);
        objOs.flush();
        objOs.close();
    }
    
    //Metodo que recupera os dados de prontuarios do sistema de arquivo de texto
    public void recuperarProntuarios() throws Exception
    {
        File arquivo = new File("data/prontuarios.dat");
        
        if(arquivo.exists())
        {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objProntuarios = (ArrayList<Prontuario>)objIs.readObject();
            objIs.close();
        }
    }
}
