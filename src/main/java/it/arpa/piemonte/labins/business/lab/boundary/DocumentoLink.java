/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.entity.Documento;
import javax.ws.rs.core.Link;

/**
 *
 * @author alfonso
 */
public class DocumentoLink {

    public Long id;
    
    public Link link;
    public String denominazione;
    public String file;
    
    public DocumentoLink(Documento e) {
        this.id = e.getId();
        this.denominazione = e.getDenominazione();
        this.file = e.getFile();
    }

    @Override
    public String toString() {
        return "DocumentoLink{" + "id=" + id + ", link=" + link + ", denominazione=" + denominazione + ", file=" + file + '}';
    }

    
}
