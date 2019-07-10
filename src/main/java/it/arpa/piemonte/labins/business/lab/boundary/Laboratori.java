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
public class Laboratori {
    
    public  Integer size;
    
    public Link link;
    
    public List<LaboratorioLink> laboratori;

    public Laboratori(List<LaboratorioLink> laboratori) {
        this.size = laboratori.size();
        this.laboratori = laboratori;
    }
    
    
}
