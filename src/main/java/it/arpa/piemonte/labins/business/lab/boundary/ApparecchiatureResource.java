/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.ApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.entity.Apparecchiatura;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author utente
 */
public class ApparecchiatureResource {

    @Inject
    ApparecchiaturaStore store;

    @Context
    ResourceContext resource;
    private Long idLab;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Apparecchiatura e, @Context UriInfo uriInfo) {
        Apparecchiatura saved = store.save(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + saved.getId()).build();
        return Response.status(Response.Status.CREATED).entity(uri.toString()).build();
    }

    @GET
    public List<Apparecchiatura> search(
            @QueryParam("idDom") Long idDom,
            @QueryParam("idTipo") Long idTipo,
            @QueryParam("idAz") Long idAz,
            @QueryParam("idDistr") Long idDistr,
            @QueryParam("idMan") Long idMan,
            @QueryParam("idTar") Long idTar,
            @QueryParam("start") Integer start,
            @QueryParam("page-size") Integer pageSize
    ) {
        return store.search(idLab, idDom, idTipo, idAz, idDistr, idMan, idTar, start, pageSize);
    }

    @Path("{id}")
    public ApparecchiaturaResource find(@PathParam("id") Long id) {
        ApparecchiaturaResource sub = resource.getResource(ApparecchiaturaResource.class);
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
