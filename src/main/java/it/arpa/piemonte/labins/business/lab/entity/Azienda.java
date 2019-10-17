/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "azienda")
public class Azienda extends BaseEntity {

    @NotEmpty(message = "Il campo denominazione è obbligatorio")
    @Column(nullable = false)
    @Size(message = "Il campo denominazione può avere al max 255 caratteri")
    private String denominazione;
    @Size(message = "Il campo contatto può avere al max 255 caratteri")
    private String contatto;
    @Size(message = "Il campo telefono può avere al max 255 caratteri")
    private String telefono;
    @Email(message = "Il campo email non è valido")
    @Size(message = "Il campo email può avere al max 255 caratteri")
    private String email;
    @Size(message = "Il campo note può avere al max 255 caratteri")
    private String note;

    private boolean costruttore = false;
    private boolean taratore = false;
    private boolean manutentore = false;
    private boolean distributore = false;

    public Azienda() {
    }

    public Azienda(Long id) {
        this.id = id;
    }

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
