/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.entity;

import it.arpa.piemonte.labins.business.CrossCheck;
import it.arpa.piemonte.labins.business.ValidEntity;
import it.arpa.piemonte.labins.business.lab.adapter.AziendaLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.TipoGestioneApparecchiaturaAdapter;
import java.time.LocalDate;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "gestione_apparecchiatura")
@CrossCheck
public class GestioneApparecchiatura extends BaseEntity implements ValidEntity {

    @Override
    public boolean isValid() {
        switch (tipo) {
            case TEMPORALE:
                return freq != null && freq > 0;
            case DESCRITTIVA:
                return freq == null;
            default:
                return freq == null;
        }

    }

    public static enum Tipo {
        TEMPORALE, DESCRITTIVA, PRIMA_USO
    }

    @JsonbTypeAdapter(TipoGestioneApparecchiaturaAdapter.class)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "data_pianificata")
    private LocalDate dataPianificata;

    private Integer freq;

    private String descrizione;

    @JsonbTypeAdapter(AziendaLinkAdapter.class)
    @ManyToOne
    private Azienda azienda;

    @Size(message = "Il campo attività può avere al max 255 caratteri")
    private String attivita;

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public String getAttivita() {
        return attivita;
    }

    public void setAttivita(String attivita) {
        this.attivita = attivita;
    }

    public LocalDate getDataPianificata() {
        return dataPianificata;
    }

    public void setDataPianificata(LocalDate dataPianificata) {
        this.dataPianificata = dataPianificata;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return tipo.toString();
    }
}
