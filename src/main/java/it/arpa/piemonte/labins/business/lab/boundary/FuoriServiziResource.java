/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.ApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.control.FuoriServizioStore;
import it.arpa.piemonte.labins.business.lab.control.LaboratorioStore;
import it.arpa.piemonte.labins.business.lab.entity.Apparecchiatura;
import it.arpa.piemonte.labins.business.lab.entity.FuoriServizio;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(FuoriServizio e, @Context UriInfo uriInfo) {
        System.out.println("create...");
        e.setApparecchiatura(apparecchiaturaStore.find(idApparecchiatura));
        FuoriServizio saved = store.save(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + saved.getId()).build();
        return Response.status(Response.Status.CREATED).entity(uri.toString()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FuoriServizi search(
            @QueryParam("start") Integer start,
            @QueryParam("page-size") Integer pageSize
    ) {
        List<FuoriServizioLink> db = store.searchLink(idApparecchiatura, start, pageSize);
        FuoriServizi fuoriServizi = new FuoriServizi(db);
        db.stream().forEach(e -> e.link = Link.fromUri(uriInfo.getPath() + "/" + e.id).rel("self").build());
        fuoriServizi.size = store.searchCount(idApparecchiatura);
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
