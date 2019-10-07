/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.boundary;


import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 *
 * @author alfonso
 */
public class DocumentoUploadForm {
    
    private byte[] fileData;
    private String fileName;
    private String denominazione;
    private String mediaType;
    private Integer tipo;
    
    public String getFileName() {
        return fileName;
    }
 
    @FormParam("fileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
 
    public byte[] getFileData() {
        return fileData;
    }
 
    @FormParam("uploadedFile")
    @PartType("application/octet-stream")
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getDenominazione() {
        return denominazione;
    }

    @FormParam("denominazione")
    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getMediaType() {
        return mediaType;
    }

    @FormParam("mediaType")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Integer getTipo() {
        return tipo;
    }

    @FormParam("tipo")
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "DocumentoUploadForm{" + "fileData=" + fileData + ", fileName=" + fileName + ", denominazione=" + denominazione + ", mediaType=" + mediaType + ", tipo=" + tipo + '}';
    }
    
    
}
