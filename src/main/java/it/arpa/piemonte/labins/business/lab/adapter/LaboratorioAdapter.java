/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.adapter;

import it.arpa.piemonte.labins.business.lab.control.LaboratorioStore;
import it.arpa.piemonte.labins.business.lab.entity.Laboratorio;
import javax.inject.Inject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author utente
 */
public class LaboratorioAdapter implements JsonbAdapter<Laboratorio, Long> {

    //@Inject
    //LaboratorioStore store;
    
    @Override
    public Long adaptToJson(Laboratorio e) throws Exception {
        return e == null ? null :  e.getId();
    }

    @Override
    public Laboratorio adaptFromJson(Long id) throws Exception {
        return null;
    }
    
}
