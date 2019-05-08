/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "gestione_temporale")
public class GestioneTemporale extends BaseEntity {

    private LocalDate scadenza;

    private int freq;

    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return scadenza.toString();
    }
}
