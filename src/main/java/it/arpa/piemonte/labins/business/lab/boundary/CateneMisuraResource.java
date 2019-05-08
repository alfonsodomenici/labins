/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.CatenaMisuraStore;
import it.arpa.piemonte.labins.business.lab.control.DominioStore;
import it.arpa.piemonte.labins.business.lab.entity.CatenaMisura;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

public class CateneMisuraResource {

    
    @Inject
    CatenaMisuraStore store;
    
    @Inject
    DominioStore domStore;

    @Context
    ResourceContext resource;

    private Long idDom;
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(CatenaMisura e, @Context UriInfo uriInfo) {
        e.setDominio(domStore.find(idDom));
        CatenaMisura saved = store.save(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + saved.getId()).build();
        return Response.status(Response.Status.CREATED).entity(uri.toString()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CatenaMisura> all() {
        return store.all(idDom);
    }

    @Path("{id}")
    public CatenaMisuraResource find(@PathParam("id") Long id) {
        CatenaMisuraResource sub = resource.getResource(CatenaMisuraResource.class);
        sub.setId(id);
        sub.setIdDom(idDom);
        return sub;
    }

    public Long getIdDom() {
        return idDom;
    }

    public void setIdDom(Long idDom) {
        this.idDom = idDom;
    }

    
}
