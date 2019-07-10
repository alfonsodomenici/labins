/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.AziendaStore;
import it.arpa.piemonte.labins.business.lab.entity.Azienda;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author utente
 */
@Path("/aziende")
public class AziendeResource {

    @Inject
    AziendaStore store;

    @Context
    ResourceContext resource;

    @Context
    UriInfo uriInfo;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Azienda e, @Context UriInfo uriInfo) {
        Azienda saved = store.save(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + saved.getId()).build();
        return Response.status(Response.Status.CREATED).entity(uri.toString()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Aziende all() {
        List<AziendaLink> db = store.allLink();
        Aziende aziende = new Aziende(db);
        aziende.link = Link.fromUri(uriInfo.getPath()).rel("uri").build();
        db.stream().forEach(e -> e.link = Link.fromUri(uriInfo.getPath() + "/" + e.id).rel("self").build());
        return aziende;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Azienda find(@PathParam("id") Long id) {
        return store.find(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Azienda update(Azienda e, @PathParam("id") Long id) {
        e.setId(id);
        return store.save(e);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        store.remove(id);
    }
}
