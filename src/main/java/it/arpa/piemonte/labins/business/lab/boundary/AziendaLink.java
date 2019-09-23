/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.entity.Azienda;
import javax.ws.rs.core.Link;

/**
 *
 * @author alfonso
 */

public class AziendaLink {

    public Long id;
    public boolean costruttore,distributore,manutentore,taratore;
    public Link link;

    public String denominazione;

    public AziendaLink() {
    }
    
    
    public AziendaLink(Azienda e) {
        this.id = e.getId();
        this.costruttore = e.isCostruttore();
        this.distributore = e.isDistributore();
        this.manutentore = e.isManutentore();
        this.taratore = e.isTaratore();
        this.denominazione = e.getDenominazione();
    }
    
}
