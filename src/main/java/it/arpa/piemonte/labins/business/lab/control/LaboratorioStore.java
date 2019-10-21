/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.boundary.AziendaLink;
import it.arpa.piemonte.labins.business.lab.boundary.LaboratorioLink;
import it.arpa.piemonte.labins.business.lab.entity.Laboratorio;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author utente
 */
@Stateless
public class LaboratorioStore {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public Laboratorio save(Laboratorio e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        Laboratorio e = this.find(id);
        e.setNascosto(true);
        save(e);
    }

    public Laboratorio find(Long id) {
        return em.find(Laboratorio.class, id);
    }

    public List<Laboratorio> all() {
        return em.createQuery("select e from Laboratorio e where e.nascosto=false order by e.denominazione", Laboratorio.class)
                .getResultList();
    }

    public List<LaboratorioLink> allLink() {
        return all().stream().map(LaboratorioLink::new).collect(Collectors.toList());
    }
}
