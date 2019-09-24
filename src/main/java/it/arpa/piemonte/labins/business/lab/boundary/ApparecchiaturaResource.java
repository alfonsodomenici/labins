/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.ApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.control.LaboratorioStore;
import it.arpa.piemonte.labins.business.lab.entity.Apparecchiatura;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author utente
 */
public class ApparecchiaturaResource {
    
    @Inject
    ApparecchiaturaStore store;
    @Inject
    LaboratorioStore labStore;
    
    private Long idLab;
    private Long id;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Apparecchiatura find() {
        return store.find(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Apparecchiatura update(Apparecchiatura p) {
        p.setId(id);
        p.setLaboratorio(labStore.find(idLab));
        //TODO se non esiste id non inserire
        return store.save(p);
    }

    @DELETE
    public void remove() {
        System.out.println("remove apparecchiatura...");
        store.remove(id);
    }
    
    public Long getIdLab() {
        return idLab;
    }

    public void setIdLab(Long idLab) {
        this.idLab = idLab;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
