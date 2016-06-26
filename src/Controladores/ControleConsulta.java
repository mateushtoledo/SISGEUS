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

    public void adicionarConsulta(String pEspecialidade, String pNumBeneficiarioPaciente, String pMotivo, String pNumFuncionalFuncionario, Date pData, Date pRegistro) {
        objConsultas.add(new Consulta(pEspecialidade, pNumBeneficiarioPaciente, pMotivo, pNumFuncionalFuncionario, pData, pRegistro));
    }

    public void anularConsulta(Consulta pConsulta) {
        objConsultas.remove(pConsulta);
    }

    public ArrayList<Consulta> getConsultas() {
        return objConsultas;
    }

    public Consulta getConsulta(String pNumBeneficiarioPaciente, Date pData) {
        for (Consulta con : objConsultas) {
            if ((con.getData().compareTo(pData) == 0) && (con.getNumBeneficiarioPaciente().equals(pNumBeneficiarioPaciente))) {
                return con;
            }
        }
        return null;
    }

    public ArrayList<Consulta> getConsultasPeriodoTempo(Date inicio, Date fim) {
        ArrayList<Consulta> eventos = new ArrayList<>();

        for (Consulta con : objConsultas) {
            if ((con.getData().compareTo(inicio) >= 0) && (con.getData().compareTo(fim) <= 0)) {
                eventos.add(con);
            }
        }

        return eventos;
    }

    /*
    *Retorna uma string contendo as consultas de determinado perido agrupadas por especialidade
     */
    public String getConsultasPeriodoAgrupadas(Date inicio, Date fim) {
        ArrayList<Consulta> aux = getConsultasPeriodoTempo(inicio, fim);
        String eventos = "";

        //Metodo Sort ordena o arrayList, seguindo o criterio de ordenacao criado na classe consulta
        Collections.sort(aux);

        for (Consulta c : aux) {
            eventos += "Data: " + c.getData().getDate() + "/" + c.getData().getMonth() + "/" + c.getData().getYear();
            eventos += " - Horário: " + c.getData().getHours() + ":" + c.getData().getMinutes() + " - Especialidade: " + c.getEspecialidade() + "\n";
        }

        return eventos;
    }

    public Object[] getDescricao(ArrayList<Consulta> pConsultas) {
        Object desc[] = new Object[pConsultas.size()];
        int i = 0;
        String aux = "";

        for (Consulta c : pConsultas) {
            aux = "Data: " + c.getData().getDate() + "/" + c.getData().getMonth() + "/" + c.getData().getYear();
            aux += "    Horário:" + c.getData().getHours() + ":" + c.getData().getMinutes();

            desc[i] = aux;
            i++;
        }

        return desc;
    }

    public ArrayList<Consulta> getConsultasPaciente(String pNumBeneficiario) {
        ArrayList<Consulta> auxArray = new ArrayList<>();

        for (Consulta c : objConsultas) {
            if (c.getNumBeneficiarioPaciente().compareTo(pNumBeneficiario) == 0) {
                auxArray.add(c);
            }
        }

        return auxArray;
    }

    public void interfaceAgendaDaUnidadePorEspecialidade() {
        new LimiteAgendaPorEspecialidade(this);
    }

    public void interfaceMarcarConsulta() {
        new LimiteMarcarConsulta(objControlador);
    }

    public void interfaceAnularConsulta() {
        new LimiteAnulacaoConsulta(objControlador);
    }

    public void salvarConsultas() throws Exception {
        FileOutputStream fileOs = new FileOutputStream("consultas.dat");
        ObjectOutputStream objOs = new ObjectOutputStream(fileOs);
        objOs.writeObject(objConsultas);
        objOs.flush();
        objOs.close();
    }

    public void recuperarConsultas() throws Exception {
        File arquivo = new File("consultas.dat");

        if (arquivo.exists()) {
            FileInputStream fileIs = new FileInputStream(arquivo);
            ObjectInputStream objIs = new ObjectInputStream(fileIs);
            objConsultas = (ArrayList<Consulta>) objIs.readObject();
            objIs.close();
        }
    }
}
