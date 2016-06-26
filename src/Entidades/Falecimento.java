package Entidades;

import java.io.Serializable;
import java.util.Date;

public class Falecimento implements Serializable{
    private Date data;
    private String causa;

    public Falecimento(Date pData) {
        data = pData;
    }

    public Falecimento(Date pData, String pCausa) {
        data = pData;
        causa = pCausa;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public Date getData() {
        return data;
    }

    public String getCausa() {
        return causa;
    }
    
}
