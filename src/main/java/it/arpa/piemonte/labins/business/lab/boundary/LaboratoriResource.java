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
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.json.Json;
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
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 * @author utente
 */
@Path("/laboratori")
@PermitAll
public class LaboratoriResource {

    @Inject
    LaboratorioStore store;

    @Context
    ResourceContext resource;

    @Context
    UriInfo uriInfo;

    @Inject
    JsonWebToken jwt;

    @Inject
    @Claim(standard = Claims.upn)
    private String upn;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Laboratorio e, @Context UriInfo uriInfo) {
        Laboratorio saved = store.save(e);
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
    public Response all() {
        System.out.println("----------------- all laboratori ------------------------------------------- " + upn);
        List<LaboratorioLink> db = store.allLink();
        Laboratori laboratori = new Laboratori(db);
        laboratori.link = Link.fromUri(uriInfo.getPath()).rel("self").build();
        db.stream().forEach(e -> e.link = Link.fromUri(uriInfo.getPath() + "/" + e.id).rel("self").build());
        return Response.ok(laboratori).build();
    }

    @Path("{id}")
    public LaboratorioResource find(@PathParam("id") Long id, @Context Request req) {
        System.out.println(req.getMethod());
        LaboratorioResource sub = resource.getResource(LaboratorioResource.class);
        sub.setId(id);
        return sub;
    }

}
