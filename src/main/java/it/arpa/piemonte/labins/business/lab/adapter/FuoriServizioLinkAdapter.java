/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.FuoriServizioStore;
import it.arpa.piemonte.labins.business.lab.entity.FuoriServizio;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class FuoriServizioLinkAdapter implements JsonbAdapter<FuoriServizio, JsonObject> {

    @Inject
    FuoriServizioStore store;

    @Override
    public JsonObject adaptToJson(FuoriServizio obj) throws Exception {
        return Json.createObjectBuilder()
                .add("id", obj.getId())
                .add("denominazione", obj.getMotivo().name() + " " + obj.getInizio() + " - " + obj.getFine())
                .build();
    }

    @Override
    public FuoriServizio adaptFromJson(JsonObject obj) throws Exception {
        return store.find(Long.valueOf(obj.getInt("id")));
    }

}
