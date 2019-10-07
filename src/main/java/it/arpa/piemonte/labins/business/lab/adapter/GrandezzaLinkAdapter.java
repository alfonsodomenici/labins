/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.GrandezzaStore;
import it.arpa.piemonte.labins.business.lab.control.TipoApparecchiaturaStore;
import it.arpa.piemonte.labins.business.lab.entity.Grandezza;
import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class GrandezzaLinkAdapter implements JsonbAdapter<Grandezza, JsonObject> {

    @Inject
    GrandezzaStore store;

    @Override
    public JsonObject adaptToJson(Grandezza obj) throws Exception {
        return Json.createObjectBuilder()
                .add("id", obj.getId())
                .add("denominazione", obj.getDenominazione())
                .build();
    }

    @Override
    public Grandezza adaptFromJson(JsonObject obj) throws Exception {
        return store.find(new Long(obj.getInt("id")));
    }

}
