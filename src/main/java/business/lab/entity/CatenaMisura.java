/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "catena_misura")
public class CatenaMisura extends BaseEntity {

    @NotEmpty(message = "Il campo denominazione è obbligatorio")
    @Column(nullable = false)
    @Size(message = "Il campo denominazione può avere al max 255 caratteri")
    private String denominazione;

    @ManyToOne
    private Dominio dominio;

    @ManyToMany(mappedBy = "cateneMisura")
    private Set<Apparecchiatura> apparecchiature;

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public Dominio getDominio() {
        return dominio;
    }

    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }

    public Set<Apparecchiatura> getApparecchiature() {
        return apparecchiature;
    }

    public void setApparecchiature(Set<Apparecchiatura> apparecchiature) {
        this.apparecchiature = apparecchiature;
    }

    @Override
    public String toString() {
        return denominazione;
    }
}
