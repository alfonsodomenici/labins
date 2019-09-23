/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.entity.Grandezza;
import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import it.arpa.piemonte.labins.business.lab.entity.TipoApparecchiatura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author utente
 */
@Stateless
public class GrandezzaStore {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public Grandezza save(Grandezza e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        em.remove(find(id));
    }

    public Grandezza find(Long id) {
        return em.find(Grandezza.class, id);
    }

    public List<Grandezza> all(){
        return em.createQuery("select e from Grandezza e order by e.denominazione", Grandezza.class)
                .getResultList();
    }
    
}
