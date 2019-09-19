/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.DominioStore;
import it.arpa.piemonte.labins.business.lab.control.LaboratorioStore;
import it.arpa.piemonte.labins.business.lab.control.TipoApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.entity.Dominio;
import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author utente
 */
public class TipoApparecchiaturaResource {

    @Inject
    LaboratorioStore labStore;
    
    @Inject
    TipoApparecchiaturaStore store;

    @Context
    ResourceContext resource;

    private Long id;
    private Long idLab;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TipoApparecchiatura find() {
        return store.find(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TipoApparecchiatura update(TipoApparecchiatura e) {
        e.setId(id);
        e.setLaboratorio(labStore.find(idLab));
        //TODO se non esiste id non inserire
        return store.save(e);
    }

    @DELETE
    public void remove() {
        store.remove(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdLab(Long idLab) {
        this.idLab = idLab;
    }

    
}
