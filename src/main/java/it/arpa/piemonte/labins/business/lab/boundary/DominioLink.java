/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.entity.Dominio;
import javax.ws.rs.core.Link;

/**
 *
 * @author alfonso
 */
public class DominioLink {

    public Long id;
    
    public Link link;

    public String denominazione;
    
    public DominioLink(Dominio e) {
        this.id = e.getId();
        this.denominazione = e.getDenominazione();
    }

}
