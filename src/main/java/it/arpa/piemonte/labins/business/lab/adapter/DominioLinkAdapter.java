/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.DominioStore;
import it.arpa.piemonte.labins.business.lab.entity.Dominio;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class DominioLinkAdapter implements JsonbAdapter<Dominio, JsonObject> {

    @Inject
    DominioStore store;

    @Override
    public JsonObject adaptToJson(Dominio obj) throws Exception {
        return Json.createObjectBuilder()
                .add("id", obj.getId())
                .add("denominazione", obj.getDenominazione())
                .build();
    }

    @Override
    public Dominio adaptFromJson(JsonObject obj) throws Exception {
        return store.find(Long.valueOf(obj.getInt("id")));
    }

}
