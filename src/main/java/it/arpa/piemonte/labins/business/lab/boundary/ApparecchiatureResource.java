/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.ApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.control.LaboratorioStore;
import it.arpa.piemonte.labins.business.lab.entity.Apparecchiatura;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
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
public class ApparecchiatureResource {

    @Inject
    ApparecchiaturaStore store;

    @Inject
    LaboratorioStore laboratorioStore;

    @Context
    ResourceContext resource;

    @Context
    UriInfo uriInfo;

    private Long idLab;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Apparecchiatura e, @Context UriInfo uriInfo) {
        e.setLaboratorio(laboratorioStore.find(idLab));
        Apparecchiatura saved = store.save(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + saved.getId()).build();
        return Response.status(Response.Status.CREATED).entity(
                Json.createObjectBuilder()
                        .add("id", saved.getId())
                        .add("uri", uri.toString())
                        .build()
        ).build();
    }

    @GET
    @Path("/riferimento")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray findDiRiferimento() {
        return store.findDiRiferimentoAsJson(idLab);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Apparecchiature search(
            @QueryParam("idDom") Long idDom,
            @QueryParam("idTipo") Long idTipo,
            @QueryParam("idAz") Long idAz,
            @QueryParam("idDistr") Long idDistr,
            @QueryParam("idMan") Long idMan,
            @QueryParam("idTar") Long idTar,
            @QueryParam("nascosto") Boolean nascosto,
            @QueryParam("start") Integer start,
            @QueryParam("page-size") Integer pageSize
    ) {
        List<ApparecchiaturaLink> db = store.searchLink(idLab, idDom, idTipo, idAz, idDistr, idMan, idTar, nascosto, start, pageSize);
        Apparecchiature apparecchiature = new Apparecchiature(db);
        apparecchiature.link = Link.fromUri(uriInfo.getPath()).rel("self").build();
        db.stream().forEach(e -> e.link = Link.fromUri(uriInfo.getPath() + "/" + e.id).rel("self").build());
        apparecchiature.size = store.searchCount(idLab, idDom, idTipo, idAz, idDistr, idMan, idTar, nascosto);
        return apparecchiature;
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
