/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import it.arpa.piemonte.labins.business.lab.entity.UnitaMisura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author utente
 */
@Stateless
public class UnitaMisuraStore {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public UnitaMisura save(UnitaMisura e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        em.remove(find(id));
    }

    public UnitaMisura find(Long id) {
        return em.find(UnitaMisura.class, id);
    }

    public List<UnitaMisura> all(){
        return em.createQuery("select e from UnitaMisura e order by e.denominazione", UnitaMisura.class)
                .getResultList();
    }
    
}
