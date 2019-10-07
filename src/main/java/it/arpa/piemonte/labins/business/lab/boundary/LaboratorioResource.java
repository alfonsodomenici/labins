/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.LaboratorioStore;
import it.arpa.piemonte.labins.business.lab.entity.Laboratorio;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author utente
 */
public class LaboratorioResource {

    @Inject
    LaboratorioStore store;

    @Context
    ResourceContext resource;

    private Long id;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Laboratorio find() {
        return store.find(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Laboratorio update(Laboratorio e) {
        e.setId(id);
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

    @Path("apparecchiature")
    public ApparecchiatureResource apparecchiature() {
        ApparecchiatureResource sub = resource.getResource(ApparecchiatureResource.class);
        sub.setIdLab(id);
        return sub;
    }

    @Path("domini")
    public DominiResource domini() {
        DominiResource sub = resource.getResource(DominiResource.class);
        sub.setIdLab(id);
        return sub;
    }
    
    @Path("tipi-apparecchiatura")
    public TipiApparecchiaturaResource tipiApparecchiatura() {
        TipiApparecchiaturaResource sub = resource.getResource(TipiApparecchiaturaResource.class);
        sub.setIdLab(id);
        return sub;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
