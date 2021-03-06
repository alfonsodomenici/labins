/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.GrandezzaStore;
import it.arpa.piemonte.labins.business.lab.entity.Grandezza;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author utente
 */
@Path("/grandezze")
public class GrandezzeResource {

    @Inject
    GrandezzaStore store;

    @Context
    ResourceContext resource;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Grandezza e, @Context UriInfo uriInfo) {
        Grandezza saved = store.save(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + saved.getId()).build();
        return Response.status(Response.Status.CREATED).entity(
                Json.createObjectBuilder()
                        .add("id", saved.getId())
                        .add("uri", uri.toString())
                        .build()
        ).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Grandezza> all() {
        return store.all();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Grandezza find(@PathParam("id") Long id) {
        return store.find(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Grandezza update(Grandezza e, @PathParam("id") Long id) {
        e.setId(id);
        return store.save(e);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        store.remove(id);
        return Response.ok("resource removed " + uriInfo.getAbsolutePathBuilder().build().toString())
                .build();
    }
}
