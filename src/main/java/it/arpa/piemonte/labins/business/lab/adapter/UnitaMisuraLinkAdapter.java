/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.UnitaMisuraStore;
import it.arpa.piemonte.labins.business.lab.entity.UnitaMisura;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class UnitaMisuraLinkAdapter implements JsonbAdapter<UnitaMisura, JsonObject> {

    @Inject
    UnitaMisuraStore store;

    @Override
    public JsonObject adaptToJson(UnitaMisura obj) throws Exception {
        return Json.createObjectBuilder()
                .add("id", obj.getId())
                .add("denominazione", obj.getDenominazione())
                .build();
    }

    @Override
    public UnitaMisura adaptFromJson(JsonObject obj) throws Exception {
        return store.find(new Long(obj.getInt("id")));
    }

}
