/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "tipo_apparecchiatura")
public class TipoApparecchiatura extends AbstractEntity {

    @NotEmpty(message = "Il campo codice è obbligatorio")
    @Size(message = "Il campo codice può avere al max 255 caratteri")
    @Column(nullable = false)
    private String codice;

    @NotEmpty(message = "Il campo descrizione è obbligatorio")
    @Column(nullable = false)
    @Size(message = "Il campo descrizione può avere al max 255 caratteri")
    private String descrizione;

    @ManyToMany
    @JoinTable(name = "tipo_apparecchiatura_laboratorio",
            joinColumns = @JoinColumn(name = "tipo_apparecchiatura_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "laboratorio_id", referencedColumnName = "id")
    )
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