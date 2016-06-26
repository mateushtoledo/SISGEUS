package Controladores;

import java.util.*;
import java.io.*;
import Limites.LimiteCadastroFalecimento;
import Entidades.Falecimento;

public class ControleFalecimento {
    private ControlePrincipal objControlador;
    private ArrayList<Falecimento> objFalecimentos;

    public ControleFalecimento(ControlePrincipal pCtrl) {
        objControlador = pCtrl;
        objFalecimentos = new ArrayList<>();
    }
    
    public void cadastrarFalecimento(int pDia,int pMes,int pAno, String pCausa)
    {
        objFalecimentos.add(new Falecimento(new Date(pAno,pMes,pDia), pCausa));
    }
    
    public void cadastrarFalecimento(int pDia,int pMes,int pAno)
    {
        objFalecimentos.add(new Falecimento(new Date(pAno,pMes,pDia)));
    }
    
    public void interfaceCadastroFalecimento()
    {
        new LimiteCadastroFalecimento(objControlador);
    }
    
    public void salvarFalecimentos() throws Exception
    {
        FileOutputStream fileOs = new FileOutputStream("falecimentos.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objFalecimentos);
        objOs.flush();
        objOs.close();
    }
    
    public void recuperarFalecimentos() throws Exception
    {
        File arquivo = new File("falecimentos.dat");
        
        if(arquivo.exists())
        {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objFalecimentos = (ArrayList<Falecimento>)objIs.readObject();
            objIs.close();
        }
    }
}
