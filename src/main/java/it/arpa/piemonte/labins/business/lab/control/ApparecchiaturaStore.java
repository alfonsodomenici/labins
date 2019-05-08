/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.entity.Apparecchiatura;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author utente
 */
@Stateless
public class ApparecchiaturaStore {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public Apparecchiatura save(Apparecchiatura e) {
        return em.merge(e);
    }

    public void remove(Long id) {
        Apparecchiatura e = this.find(id);
        e.setNascosto(true);
        save(e);
    }

    public Apparecchiatura find(Long id) {
        return em.find(Apparecchiatura.class, id);
    }

    public List<Apparecchiatura> search(
            Long idLab,
            Long idDom,
            Long idTipo,
            Long idAz,
            Long idDistr,
            Long idMan,
            Long idTar,
            int start,
            int pageSize
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Apparecchiatura> query = cb.createQuery(Apparecchiatura.class);
        Root<Apparecchiatura> root = query.from(Apparecchiatura.class);
        Predicate cond = cb.conjunction();
        if (idLab != null) {
            cond = cb.and(cond, cb.equal(root.get("laboratorio.id"), idLab));
        }
        if (idDom != null) {
            cond = cb.and(cond, cb.equal(root.get("dominio.id"), idDom));
        }

        if (idTipo != null) {
            cond = cb.and(cond, cb.equal(root.get("tipologia.id"), idTipo));
        }

        if (idAz != null) {
            cond = cb.and(cond, cb.equal(root.get("costruttore.id"), idAz));
        }

        if (idDistr != null) {
            cond = cb.and(cond, cb.equal(root.get("distributore.id"), idDistr));
        }

        if (idMan != null) {
            cond = cb.and(cond, cb.equal(root.get("manutentore.id"), idMan));
        }

        if (idTar != null) {
            cond = cb.and(cond, cb.equal(root.get("taratore.id"), idTar));
        }

        query.select(root)
                .where(cond)
                .orderBy(cb.asc(root.get("id")));

        return em.createQuery(query)
                .setFirstResult(start)
                .setMaxResults(pageSize)
                .getResultList();
    }

}
