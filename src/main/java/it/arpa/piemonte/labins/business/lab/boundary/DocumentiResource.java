/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;

import it.arpa.piemonte.labins.business.lab.control.DocumentoStore;
import it.arpa.piemonte.labins.business.lab.entity.Documento;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author alfonso
 */
@Path("/documenti")
public class DocumentiResource {

    @Inject
    DocumentoStore store;

    @POST
    @Path("/upload1")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile1(@MultipartForm DocumentoUploadForm form) {

        Documento tosave = new Documento();
        tosave.setDenominazione(form.getFileName());
        tosave.setFile(form.getFileName());
        tosave.setTipo(Documento.Tipo.CERTIFICATO);
        Documento saved = store.save(tosave, new ByteArrayInputStream(form.getFileData()));
        return Response.status(200)
                .entity(Json.createObjectBuilder().add("id", saved.getId()).build()).build();

    }

    @GET
    @Path("download/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("id") Long id,
            @QueryParam("usr") String usr) {

        Documento doc = store.find(id);
        Response.ResponseBuilder response = Response.ok(store.getFile(doc.getFile()));
        response.header("Content-Disposition", "attachment; filename=\"" + doc.getFile() + "\"");
        response.header("Content-Type", "application/pdf");
        return response.build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Documento> allApparecchiatura(@PathParam("id") Long apparecchiatura_id,
            @QueryParam("usr") String usr) {
        return store.search(apparecchiatura_id,null,null,null);
    }
    
    public String getMimeType(InputStream is) {

        try {
            String mimeType;
            mimeType = URLConnection.guessContentTypeFromStream(is);
            return mimeType;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    public Response uploadFile(MultipartFormDataInput input) {

        String fileName = "";

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("uploadedFile");

        for (InputPart inputPart : inputParts) {

            try {

                MultivaluedMap<String, String> header = inputPart.getHeaders();
                fileName = getFileName(header);

                //convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);

                Documento tosave = new Documento();
                tosave.setDenominazione(fileName);
                tosave.setFile(fileName);
                tosave.setTipo(Documento.Tipo.CERTIFICATO);
                store.save(tosave, inputStream);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

        return Response.status(200)
                .entity("uploadFile is called, Uploaded file name : " + fileName).build();

    }

    /**
     * header sample { Content-Type=[image/png], Content-Disposition=[form-data;
     * name="file"; filename="filename.extension"] }
     *
     */
    //get uploaded filename, is there a easy way in RESTEasy?
    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");

                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }
}
