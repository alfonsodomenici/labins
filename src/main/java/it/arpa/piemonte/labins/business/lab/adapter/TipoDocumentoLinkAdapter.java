/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.entity.Documento;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author alfonso
 */
public class TipoDocumentoLinkAdapter implements JsonbAdapter<Documento.Tipo, JsonObject> {

    @Override
    public JsonObject adaptToJson(Documento.Tipo obj) throws Exception {
        return Json.createObjectBuilder()
                .add("id", obj.ordinal())
                .add("denominazione", obj.name())
                .build();
    }

    @Override
    public Documento.Tipo adaptFromJson(JsonObject obj) throws Exception {
        return Documento.Tipo.values()[obj.getInt("id")];
    }
    
}
