package Entidades;

import java.io.Serializable;
import java.util.Date;

public class Anulacao implements Serializable {

    private Consulta consultaAnulada;
    private Date data;
    private String motivo;

    public Anulacao(Consulta pConsultaAnulada, Date pData, String pMotivo) {
        consultaAnulada = pConsultaAnulada;
        data = pData;
        motivo = pMotivo;
    }

    public void setConsultaAnulada(Consulta consultaAnulada) {
        this.consultaAnulada = consultaAnulada;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Consulta getConsultaAnulada() {
        return consultaAnulada;
    }

    public Date getData() {
        return data;
    }

    public String getMotivo() {
        return motivo;
    }

}
