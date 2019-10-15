/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.LaboratorioStore;
import it.arpa.piemonte.labins.business.lab.control.TipoApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import java.net.URI;
import java.util.List;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author utente
 */
public class TipiApparecchiaturaResource {

    @Inject
    LaboratorioStore labStore;

    @Inject
    TipoApparecchiaturaStore store;

    @Context
    ResourceContext resource;

    private Long idLab;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(TipoApparecchiatura e, @Context UriInfo uriInfo) {
        e.setLaboratorio(labStore.find(idLab));
        TipoApparecchiatura saved = store.save(e);
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
    public List<TipoApparecchiatura> all() {
        return store.all(idLab);
    }

    @Path("{id}")
    public TipoApparecchiaturaResource find(@PathParam("id") Long id) {
        TipoApparecchiaturaResource sub = resource.getResource(TipoApparecchiaturaResource.class);
        sub.setId(id);
        sub.setIdLab(idLab);
        return sub;
    }

    public Long getIdLab() {
        return idLab;
    }

    public void setIdLab(Long idLab) {
        this.idLab = idLab;
    }

}
