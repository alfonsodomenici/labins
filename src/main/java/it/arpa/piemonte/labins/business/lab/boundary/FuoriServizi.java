/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import java.util.List;
import javax.ws.rs.core.Link;

/**
 *
 * @author alfonso
 */
public class FuoriServizi {
    
    public  Integer size;
    
    public Link link;
    
    public List<FuoriServizioLink> fuoriServizi;

    public FuoriServizi(List<FuoriServizioLink> fuoriServizi) {
        this.size = fuoriServizi.size();
        this.fuoriServizi = fuoriServizi;
    }
    
    
}
