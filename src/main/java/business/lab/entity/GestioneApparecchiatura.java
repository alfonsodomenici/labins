/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

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
public class GestioneApparecchiatura extends BaseEntity {

    public static enum Tipo {
        TEMPORALE, DESCRITTIVA, PRIMA_USO
    }

    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    
    @OneToOne
    private GestioneTemporale temporale;
    
    @OneToOne
    private GestioneDescrittiva descrittiva;
    
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

    public GestioneTemporale getTemporale() {
        return temporale;
    }

    public void setTemporale(GestioneTemporale temporale) {
        this.temporale = temporale;
    }

    public GestioneDescrittiva getDescrittiva() {
        return descrittiva;
    }

    public void setDescrittiva(GestioneDescrittiva descrittiva) {
        this.descrittiva = descrittiva;
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
    
    @Override
    public String toString() {
        return tipo.toString();
    }
}
