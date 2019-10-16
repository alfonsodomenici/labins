/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.ApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.control.LaboratorioStore;
import it.arpa.piemonte.labins.business.lab.entity.Apparecchiatura;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
public class ApparecchiaturaResource {

    @Inject
    ApparecchiaturaStore store;
    @Inject
    LaboratorioStore labStore;

    @Context
    ResourceContext resource;

    @Context
    UriInfo uriInfo;

    private Long idLab;
    private Long id;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Apparecchiatura find() {
        return store.find(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Apparecchiatura update(Apparecchiatura p) {
        p.setId(id);
        p.setLaboratorio(labStore.find(idLab));
        System.out.println(p.toString());
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

    @Path("fuori-servizi")
    public FuoriServiziResource fuoriServizi() {
        FuoriServiziResource sub = resource.getResource(FuoriServiziResource.class);
        sub.setIdApparecchiatura(id);
        return sub;
    }

    @Path("documenti")
    public DocumentiApparecchiaturaResource documenti() {
        DocumentiApparecchiaturaResource sub = resource.getResource(DocumentiApparecchiaturaResource.class);
        sub.setIdApparecchiatura(id);
        return sub;
    }

    public Long getIdLab() {
        return idLab;
    }

    public void setIdLab(Long idLab) {
        this.idLab = idLab;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
