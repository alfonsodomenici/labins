/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "deroga")
public class Deroga extends BaseEntity{
    
    @Temporal(TemporalType.DATE)
    @Column(name = "deroga_scadenza")
    private LocalDate derogaScadenza;
    
    @Column(length = 2048)
    @Size(message = "Il campo motivazione può avere al max 2048 caratteri")
    private String motivazione;
    
    @ManyToOne
    private GestioneTemporale gestione;

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

    public GestioneTemporale getGestione() {
        return gestione;
    }

    public void setGestione(GestioneTemporale gestione) {
        this.gestione = gestione;
    }
    
    
    
}
