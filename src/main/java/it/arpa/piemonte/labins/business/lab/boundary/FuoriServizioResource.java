/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.ApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.control.FuoriServizioStore;
import it.arpa.piemonte.labins.business.lab.entity.FuoriServizio;
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
public class FuoriServizioResource {

    @Inject
    ApparecchiaturaStore apparecchiaturaStore;
    @Inject
    FuoriServizioStore store;

    @Context
    ResourceContext resource;

    @Context
    UriInfo uriInfo;

    private Long idApparecchiatura;
    private Long id;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FuoriServizio find() {
        return store.find(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FuoriServizio update(FuoriServizio p) {
        p.setId(id);
        p.setApparecchiatura(apparecchiaturaStore.find(idApparecchiatura));
        //TODO se non esiste id non inserire
        return store.save(p);
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response remove(@Context UriInfo uriInfo) {
        store.remove(id);
        return Response.ok("resource removed " + uriInfo.getAbsolutePathBuilder().build().toString())
                .build();
    }

    public Long getIdApparecchiatura() {
        return idApparecchiatura;
    }

    public void setIdApparecchiatura(Long idApparecchiatura) {
        this.idApparecchiatura = idApparecchiatura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
