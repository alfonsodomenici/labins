/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.boundary.LaboratorioLink;
import it.arpa.piemonte.labins.business.lab.entity.Laboratorio;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class LaboratorioLinkAdapter implements JsonbAdapter<Laboratorio, LaboratorioLink> {

    @Override
    public LaboratorioLink adaptToJson(Laboratorio obj) throws Exception {
        return new LaboratorioLink(obj);
    }

    @Override
    public Laboratorio adaptFromJson(LaboratorioLink obj) throws Exception {
        return new Laboratorio(obj.id);
    }

     
}
