/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author utente
 */
public class Azienda extends AbstractEntity {

    @NotEmpty(message = "Il campo denominazione è obbligatorio")
    @Column(nullable = false)
    private String denominazione;
    private String contatto;
    private String telefono;
    @Email(message = "Il campo email non è valido")
    private String email;
    private String note;

    private boolean costruttore;
    private boolean taratore;
    private boolean manutentore;
    private boolean distributore;

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getContatto() {
        return contatto;
    }

    public void setContatto(String contatto) {
        this.contatto = contatto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isCostruttore() {
        return costruttore;
    }

    public void setCostruttore(boolean costruttore) {
        this.costruttore = costruttore;
    }

    public boolean isTaratore() {
        return taratore;
    }

    public void setTaratore(boolean taratore) {
        this.taratore = taratore;
    }

    public boolean isManutentore() {
        return manutentore;
    }

    public void setManutentore(boolean manutentore) {
        this.manutentore = manutentore;
    }

    public boolean isDistributore() {
        return distributore;
    }

    public void setDistributore(boolean distributore) {
        this.distributore = distributore;
    }

    @Override
    public String toString() {
        return denominazione;
    }

}
