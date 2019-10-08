/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.entity.FuoriServizio;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author alfonso
 */
public class MotivoFuoriServizioLinkAdapter implements JsonbAdapter<FuoriServizio.Motivo, JsonObject> {

    @Override
    public JsonObject adaptToJson(FuoriServizio.Motivo obj) throws Exception {
        return Json.createObjectBuilder()
                .add("id", obj.ordinal())
                .add("denominazione", obj.name())
                .build();
    }

    @Override
    public FuoriServizio.Motivo adaptFromJson(JsonObject obj) throws Exception {
        return obj.getInt("id")== -1 ? null : FuoriServizio.Motivo.values()[obj.getInt("id")];
    }
    
}
