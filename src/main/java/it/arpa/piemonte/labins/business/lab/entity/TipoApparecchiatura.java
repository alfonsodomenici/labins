/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author utente
 */
@Entity
@Table(name = "tipo_apparecchiatura")
public class TipoApparecchiatura extends AbstractEntity {

    @NotEmpty(message = "Il campo descrizione è obbligatorio")
    @Column(nullable = false)
    @Size(message = "Il campo descrizione può avere al max 255 caratteri")
    private String descrizione;

    @ManyToOne
    private Laboratorio laboratorio;

    public TipoApparecchiatura() {
    }

    public TipoApparecchiatura(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    @Override
    public String toString() {
        return this.descrizione;
    }
    
    public static JsonObject convertMinimal(TipoApparecchiatura tipo) {
        return Json.createObjectBuilder()
                .add("id", tipo.getId())
                .add("denominazione", tipo.getDescrizione())
                .build();
    }

}
