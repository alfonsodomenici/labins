/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.entity.Apparecchiatura;
import javax.ws.rs.core.Link;

/**
 *
 * @author alfonso
 */
public class ApparecchiaturaLink {

    public Long id;
    
    public Link link;

    public String descrizione;
    
    public ApparecchiaturaLink(Apparecchiatura e) {
        this.id = e.getId();
        this.descrizione = e.getDescrizione();
    }

}
