/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.entity.FuoriServizio;
import java.time.LocalDate;
import javax.ws.rs.core.Link;

/**
 *
 * @author alfonso
 */
public class FuoriServizioLink {

    public Long id;
    
    public Link link;
    
    public FuoriServizio.Motivo motivo;
    public FuoriServizio.Esito esito;
    public LocalDate inizio;
    public LocalDate fine;
    public String denominazione;
    
    public FuoriServizioLink(FuoriServizio e) {
        this.id = e.getId();
        this.motivo = e.getMotivo();
        this.esito=e.getEsito();
        this.inizio = e.getInizio();
        this.fine = e.getFine();
        this.denominazione = e.getMotivo().name() + " " + e.getInizio() + " - " + e.getFine();
    }

}
