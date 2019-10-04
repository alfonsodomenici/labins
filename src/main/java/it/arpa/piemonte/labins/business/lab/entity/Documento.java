/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.entity;

import it.arpa.piemonte.labins.business.lab.adapter.ApparecchiaturaLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.AziendaLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.FuoriServizioLinkAdapter;
import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "documento")
public class Documento extends AbstractEntity {

    public static enum Tipo {
        CERTIFICATO, MANUALE_TECNICO
    }

    @NotEmpty(message = "Il campo denominazione è obbligatorio")
    @Column(nullable = false)
    @Size(message = "Il campo denominazione può avere al max 255 caratteri")
    private String denominazione;

    @NotEmpty(message = "Il campo file è obbligatorio")
    @Column(nullable = false)
    @Size(message = "Il campo file può avere al max 255 caratteri")
    private String file;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "media_type")
    private String mediaType;

    
    @JsonbTypeAdapter(ApparecchiaturaLinkAdapter.class)
    @ManyToOne
    private Apparecchiatura apparecchiatura;

    @JsonbTypeAdapter(FuoriServizioLinkAdapter.class)
    @ManyToOne
    private FuoriServizio fs;

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Apparecchiatura getApparecchiatura() {
        return apparecchiatura;
    }

    public void setApparecchiatura(Apparecchiatura apparecchiatura) {
        this.apparecchiatura = apparecchiatura;
    }

    public FuoriServizio getFs() {
        return fs;
    }

    public void setFs(FuoriServizio fs) {
        this.fs = fs;
    }

    @Override
    public String toString() {
        return denominazione;
    }
}
