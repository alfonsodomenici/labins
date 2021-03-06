/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.ApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.control.FuoriServizioStore;
import it.arpa.piemonte.labins.business.lab.entity.FuoriServizio;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
public class FuoriServiziResource {

    @Inject
    FuoriServizioStore store;

    @Inject
    ApparecchiaturaStore apparecchiaturaStore;

    @Context
    ResourceContext resource;

    @Context
    UriInfo uriInfo;

    private Long idApparecchiatura;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(FuoriServizio e, @Context UriInfo uriInfo) {
        e.setApparecchiatura(apparecchiaturaStore.find(idApparecchiatura));
        FuoriServizio saved = store.save(e);
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
    @Path("/status")
    public JsonObject status() {
        return store.status(idApparecchiatura);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FuoriServizi search(
            @QueryParam("storico") Boolean storico,
            @QueryParam("fs") Boolean fs,
            @QueryParam("vi") Boolean vi,
            @QueryParam("last") Boolean last,
            @QueryParam("start") Integer start,
            @QueryParam("page-size") Integer pageSize
    ) {
        List<FuoriServizioLink> db = store.searchLink(idApparecchiatura, storico, fs, vi, last, start, pageSize);
        FuoriServizi fuoriServizi = new FuoriServizi(db);
        db.stream().forEach(e -> e.link = Link.fromUri(uriInfo.getPath() + "/" + e.id).rel("self").build());
        fuoriServizi.size = store.searchCount(idApparecchiatura, storico, fs, vi, last);
        return fuoriServizi;
    }

    @Path("{id}")
    public FuoriServizioResource find(@PathParam("id") Long id) {
        FuoriServizioResource sub = resource.getResource(FuoriServizioResource.class);
        sub.setId(id);
        sub.setIdApparecchiatura(idApparecchiatura);
        return sub;
    }

    public Long getIdApparecchiatura() {
        return idApparecchiatura;
    }

    public void setIdApparecchiatura(Long idApparecchiatura) {
        this.idApparecchiatura = idApparecchiatura;
    }

}
