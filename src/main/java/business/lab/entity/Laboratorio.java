/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "laboratorio")
public class Laboratorio extends BaseEntity {

    @NotEmpty(message = "Il campo denominazione è obbligatorio")
    @Column(nullable = false)
    @Size(message = "Il campo denominazione può avere al max 255 caratteri")
    private String denominazione;

    @OneToMany(mappedBy = "laboratorio")
    private Set<Dominio> domini;

    @OneToMany(mappedBy = "laboratorio")
    private Set<TipoApparecchiatura> tipiApparecchiatura;
    
    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public Set<Dominio> getDomini() {
        return domini;
    }

    public void setDomini(Set<Dominio> domini) {
        this.domini = domini;
    }

    @Override
    public String toString() {
        return denominazione;
    }

}
