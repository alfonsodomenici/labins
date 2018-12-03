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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "tipo_apparecchiatura")
public class TipoApparecchiatura extends AbstractEntity {

    @NotEmpty(message = "Il campo codice è obbligatorio")
    @Column(nullable = false)
    private String codice;

    @NotEmpty(message = "Il campo descrizione è obbligatorio")
    @Column(nullable = false)
    private String descrizione;

    @ManyToMany
    private Set<Laboratorio> laboratori;

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Set<Laboratorio> getLaboratori() {
        return laboratori;
    }

    public void setLaboratori(Set<Laboratorio> laboratori) {
        this.laboratori = laboratori;
    }
    
    
}
