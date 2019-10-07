/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.ApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.control.DocumentoStore;
import it.arpa.piemonte.labins.business.lab.entity.Documento;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

/**
 *
 * @author utente
 */
public class DocumentiApparecchiaturaResource {

    @Inject
    DocumentoStore store;

    @Inject
    ApparecchiaturaStore apparecchiaturaStore;

    @Context
    ResourceContext resource;

    @Context
    UriInfo uriInfo;

    private Long idApparecchiatura;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@MultipartForm DocumentoUploadForm form, @Context UriInfo uriInfo) {
        System.out.println("create documento " + form);
        Documento tosave = new Documento();
        tosave.setApparecchiatura(apparecchiaturaStore.find(idApparecchiatura));
        tosave.setDenominazione(form.getDenominazione());
        tosave.setFile(form.getFileName());
        tosave.setTipo(Documento.Tipo.values()[form.getTipo()]);
        tosave.setMediaType(form.getMediaType());
        Documento saved = store.save(tosave, new ByteArrayInputStream(form.getFileData()));
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
    public Documenti search(
            @QueryParam("start") Integer start,
            @QueryParam("page-size") Integer pageSize
    ) {
        System.out.println("find documenti for apparecchiatura " + idApparecchiatura);
        List<DocumentoLink> db = store.searchLink(idApparecchiatura, null, start, pageSize);
        Documenti documenti = new Documenti(db);
        db.stream().forEach(e -> e.link = Link.fromUri(uriInfo.getPath() + "/" + e.id).rel("self").build());
        documenti.size = store.searchCount(idApparecchiatura, null);
        return documenti;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Documento find(@PathParam("id") Long id) {
        return store.find(id);
    }

    @GET
    @Path("{id}/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("id") Long id,
            @QueryParam("usr") String usr) {

        Documento doc = store.find(id);
        Response.ResponseBuilder response = Response.ok(store.getFile(doc.getFile()));
        response.header("Content-Disposition", "attachment; filename=\"" + doc.getFile() + "\"");
        response.header("Content-Type", "application/pdf");
        return response.build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response remove(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        System.out.println(String.format("remove documento %s for apparecchiatura %s", id, idApparecchiatura));
        store.remove(id);
        return Response.ok("resource removed " + uriInfo.getAbsolutePathBuilder().build().toString())
                .build();
    }

    public Long getIdApparecchiatura() {
        return idApparecchiatura;
    }

    public void setIdApparecchiatura(Long idApparecchiatura) {
        this.idApparecchiatura = idApparecchiatura;
    }

}
