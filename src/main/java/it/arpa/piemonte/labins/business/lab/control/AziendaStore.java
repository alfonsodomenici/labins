/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.entity.Azienda;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author utente
 */
@Stateless
public class AziendaStore {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public Azienda save(Azienda e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        Azienda e = this.find(id);
        e.setNascosto(true);
        save(e);
    }

    public Azienda find(Long id) {
        return em.find(Azienda.class, id);
    }

    public List<Azienda> all(){
        return em.createQuery("select e from Azienda e where e.nascosto=false order by e.denominazione", Azienda.class)
                .getResultList();
    }
    
}
