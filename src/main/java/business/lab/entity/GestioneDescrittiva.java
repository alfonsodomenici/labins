/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "gestione_descrittiva")
public class GestioneDescrittiva extends BaseEntity{
    
    private String descrizione;
}
