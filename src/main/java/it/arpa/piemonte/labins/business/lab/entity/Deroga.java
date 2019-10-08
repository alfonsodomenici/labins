/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.entity;

import it.arpa.piemonte.labins.business.lab.adapter.ApparecchiaturaLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.FuoriServizioLinkAdapter;
import java.time.LocalDate;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "deroga")
public class Deroga extends BaseEntity {

    @Column(name = "deroga_scadenza")
    private LocalDate derogaScadenza;

    @Column(length = 2048)
    @Size(message = "Il campo motivazione può avere al max 2048 caratteri")
    private String motivazione;

    @JsonbTypeAdapter(ApparecchiaturaLinkAdapter.class)
    @ManyToOne
    private Apparecchiatura apparecchiatura;

    @JsonbTypeAdapter(FuoriServizioLinkAdapter.class)
    @ManyToOne
    private FuoriServizio fs;

    public LocalDate getDerogaScadenza() {
        return derogaScadenza;
    }

    public void setDerogaScadenza(LocalDate derogaScadenza) {
        this.derogaScadenza = derogaScadenza;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public Apparecchiatura getApparecchiatura() {
        return apparecchiatura;
    }

    public void setApparecchiatura(Apparecchiatura apparecchiatura) {
        this.apparecchiatura = apparecchiatura;
    }

    public FuoriServizio getFs() {
        return fs;
    }

    public void setFs(FuoriServizio fs) {
        this.fs = fs;
    }

    @Override
    public String toString() {
        return motivazione;
    }
}
