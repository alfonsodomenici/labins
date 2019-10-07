/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.CatenaMisuraStore;
import it.arpa.piemonte.labins.business.lab.control.DominioStore;
import it.arpa.piemonte.labins.business.lab.entity.CatenaMisura;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author utente
 */
public class CatenaMisuraResource {

    @Inject
    CatenaMisuraStore store;

    @Inject
    DominioStore domStore;

    @Context
    ResourceContext resource;

    private Long id;
    private Long idDom;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CatenaMisura find() {
        return store.find(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CatenaMisura update(CatenaMisura e) {
        e.setId(id);
        e.setDominio(domStore.find(idDom));
        //TODO se non esiste id non inserire
        return store.save(e);
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response remove(@Context UriInfo uriInfo) {
        store.remove(id);
        return Response.ok("resource removed " + uriInfo.getAbsolutePathBuilder().build().toString())
                .build();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdDom(Long idDom) {
        this.idDom = idDom;
    }

}
