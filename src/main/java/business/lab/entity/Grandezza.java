/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author utente
 */
public class Grandezza extends AbstractEntity {

    @NotEmpty(message = "Il campo denominazione è obbligatorio")
    @Column(nullable = false)
    private String denominazione;

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    @Override
    public String toString() {
        return denominazione;
    }
}
