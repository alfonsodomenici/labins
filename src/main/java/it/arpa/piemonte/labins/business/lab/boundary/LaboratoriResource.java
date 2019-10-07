/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.LaboratorioStore;
import it.arpa.piemonte.labins.business.lab.entity.Laboratorio;
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
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author utente
 */
@Path("/laboratori")
public class LaboratoriResource {

    @Inject
    LaboratorioStore store;

    @Context
    ResourceContext resource;

    @Context UriInfo uriInfo;
            
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Laboratorio e, @Context UriInfo uriInfo) {
        Laboratorio saved = store.save(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + saved.getId()).build();
        return Response.status(Response.Status.CREATED).entity(uri.toString()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Laboratori all() {
        List<LaboratorioLink> db = store.allLink();
        Laboratori laboratori = new Laboratori(db);
        laboratori.link = Link.fromUri(uriInfo.getPath()).rel("self").build();
        db.stream().forEach(e -> e.link = Link.fromUri(uriInfo.getPath() + "/" + e.id).rel("self").build());
        return laboratori;
    }

    @Path("{id}")
    public LaboratorioResource find(@PathParam("id") Long id) {
        LaboratorioResource sub = resource.getResource(LaboratorioResource.class);
        sub.setId(id);
        return sub;
    }

}
