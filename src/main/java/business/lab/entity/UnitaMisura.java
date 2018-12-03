/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "unita_misura")
public class UnitaMisura extends AbstractEntity {

    @NotEmpty(message = "Il campo denominazione è obbligatorio")
    @Size(message = "Il campo denominazione può avere al max 255 caratteri")
    @Column(nullable = false)
    private String denominazione;

    @Size(message = "Il campo descrizione può avere al max 255 caratteri")
    private String descrizione;

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return denominazione;
    }
}
