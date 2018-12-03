/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "abilitazione")
public class Abilitazione extends BaseEntity {

    public static enum Livello {
        NO_ACCESS, LETTURA, MODIFICA
    }

    @NotEmpty(message = "L'utente è obbligatorio")
    @Size(message = "Il campo utente può avere al max 255 caratteri")
    private String utente;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Laboratorio laboratorio;
    
    @Enumerated(EnumType.STRING)
    private Livello livello;
}
