/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "gestione_temporale")
public class GestioneTemporale extends BaseEntity {
    
    
    private LocalDate scadenza;
    
    private int freq;
    
}
