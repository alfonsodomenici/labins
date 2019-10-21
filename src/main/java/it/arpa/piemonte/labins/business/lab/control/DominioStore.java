/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.boundary.DominioLink;
import it.arpa.piemonte.labins.business.lab.boundary.LaboratorioLink;
import it.arpa.piemonte.labins.business.lab.entity.Dominio;
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
public class DominioStore {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public Dominio save(Dominio e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        Dominio e = this.find(id);
        e.setNascosto(true);
        save(e);
    }

    public Dominio find(Long id) {
        return em.find(Dominio.class, id);
    }

    public List<Dominio> all(Long idLab) {
        return em.createQuery("select e from Dominio e where e.nascosto=false and e.laboratorio.id = :idLab order by e.denominazione", Dominio.class)
                .setParameter("idLab", idLab)
                .getResultList();
    }

    public List<DominioLink> allLink(Long idLab) {
        return all(idLab).stream().map(DominioLink::new).collect(Collectors.toList());
    }
}
