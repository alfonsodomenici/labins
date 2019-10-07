/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.LaboratorioStore;
import it.arpa.piemonte.labins.business.lab.entity.Laboratorio;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class LaboratorioLinkAdapter implements JsonbAdapter<Laboratorio, JsonObject> {

    @Inject
    LaboratorioStore store;

    @Override
    public JsonObject adaptToJson(Laboratorio obj) throws Exception {
        return Json.createObjectBuilder()
                .add("id", obj.getId())
                .add("denominazione", obj.getDenominazione())
                .build();
    }

    @Override
    public Laboratorio adaptFromJson(JsonObject obj) throws Exception {
        return store.find(new Long(obj.getInt("id")));
    }

     
}
