package Controladores;

import Entidades.*;
import Limites.*;
import java.io.*;
import java.util.*;

public class ControleConsulta {
    private ArrayList<Consulta> objConsultas;
    private ControlePrincipal objControlador;
    
    public ControleConsulta(ControlePrincipal pCtrl) {
        objControlador = pCtrl;
        objConsultas = new ArrayList<>();
    }
    
    public void adicionarConsulta(String pEspecialidade, String pNumBeneficiarioPaciente, String pMotivo, String pNumFuncionalFuncionario, Date pData,Date pRegistro)
    {
        objConsultas.add(new Consulta(pEspecialidade, pNumBeneficiarioPaciente, pMotivo, pNumFuncionalFuncionario, pData, pRegistro));
    }
    
    public void anularConsulta(Consulta pConsulta)
    {
        objConsultas.remove(pConsulta);
    }
    
    //Retorna todas as consultas cadastradas
    public ArrayList<Consulta> getConsultas()
    {
        return objConsultas;
    }
    
    //Retorna determinada consulta (ou null se nao for encontrada)
    public Consulta getConsulta(String pNumBeneficiarioPaciente,Date pData)
    {
        for(Consulta con : objConsultas)
        {
            if((con.getData().compareTo(pData) == 0)&&(con.getNumBeneficiarioPaciente().equals(pNumBeneficiarioPaciente)))
                return con;
        }
        return null;
    }
    
    //Retorna consultas contidas no intervalo de tempo passado como parametro
    public ArrayList<Consulta> getConsultasPeriodoTempo(Date inicio,Date fim)
    {
        ArrayList<Consulta> eventos = new ArrayList<>();
        
        for(Consulta con : objConsultas)
        {
            if((con.getData().compareTo(inicio)>= 0)&&(con.getData().compareTo(fim)<= 0))
            {
                eventos.add(con);
            }
        }
        
        return eventos;
    }

    //Retorna uma string contendo as consultas de determinado perido agrupadas por especialidade
    public String getConsultasPeriodoAgrupadas(Date inicio,Date fim)
    {
        int dia,mes,ano,horas,minutos;
        ArrayList<Consulta> aux = getConsultasPeriodoTempo(inicio, fim);
        String eventos = "";
        
        //Metodo Sort ordena o arrayList, seguindo o criterio de ordenacao criado na classe consulta
        Collections.sort(aux);
        
        for(Consulta c : aux)
        {
            dia = c.getData().getDate();
            mes = c.getData().getMonth();
            ano = c.getData().getYear();
            horas = c.getData().getHours();
            minutos = c.getData().getMinutes();
            
            eventos += "Data: ";
            
            if(dia >= 10)
                eventos += dia+"/";
            else
                eventos+= "0"+dia+"/";
            
            if(mes >= 10)
                eventos += mes+"/";
            else
                eventos += "0"+mes+"/";
            
            eventos += ano+" - Horário: ";
            
            if(horas >= 10)
                eventos += horas+":";
            else
                eventos += "0"+horas+":";
            
            if(minutos == 0)
                eventos += "00 - Especialidade: ";
            else
                eventos += "30 - Especialidade: ";
            
            eventos += c.getEspecialidade()+"\n";
        }
        
        return eventos;
    }
    

    //Retorna todas as consultas de determinado paciente salvas no sistema
    public ArrayList<Consulta> getConsultasPaciente(String pNumBeneficiario)
    {
        ArrayList<Consulta> auxArray = new ArrayList<>();
        
        for(Consulta c : objConsultas)
        {
            if(c.getNumBeneficiarioPaciente().compareTo(pNumBeneficiario) == 0)
                auxArray.add(c);
        }
        
        return auxArray;
    }
    
    //Metodo que retorna um vetor contendo uma descricao das consultas do paciente
    public Object[] getDescricaoConsultas(ArrayList<Consulta> array)
    {
        Object obj[] = new Object[1000];
        int i=0;
        
        for(Consulta c : array)
        {
            String desc = c.getData().getDate()+"/"+c.getData().getMonth()+"/"+c.getData().getYear();
            desc += " - "+c.getData().getHours()+":"+c.getData().getMinutes()+" - "+c.getEspecialidade();
            obj[i] = desc;
            i++;
        }
        
        Object desc[] = new Object[i];
        System.arraycopy(obj, 0, desc, 0, i);
        
        return desc;
    }
    
    //Exibe a interface de Exibicao da agenda da unidade por especialidade
    public void interfaceAgendaDaUnidadePorEspecialidade()
    {
        new LimiteAgendaPorEspecialidade(this);
    }
    
    //Exibe a interface utilizada para marcar consultas
    public void interfaceMarcarConsulta()
    {
        new LimiteMarcarConsulta(objControlador);
    }
    
    //Exibe a interface para anular consultas
    public void interfaceAnularConsulta()
    {
        new LimiteAnulacaoConsulta(objControlador);
    }
    //Exibe a interface para anular consultas
    public void interfaceHistoricoConsultas()
    {
        new LimiteHistoricoConsultas(objControlador);
    }    
    //Salva as consultas do sistema em arquivo de texto (consultas.dat)
    public void salvarConsultas() throws Exception
    {
        FileOutputStream fileOs = new FileOutputStream("data/consultas.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objConsultas);
        objOs.flush();
        objOs.close();
    }
    
    //Recupera as consultas do sistema do arquivo de texto (consultas.dat)
    public void recuperarConsultas() throws Exception
    {
        File arquivo = new File("data/consultas.dat");
        
        if(arquivo.exists())
        {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objConsultas = (ArrayList<Consulta>)objIs.readObject();
            objIs.close();
        }
    }
}
