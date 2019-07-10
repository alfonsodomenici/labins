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
import javax.persistence.criteria.Path;
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
            Integer start,
            Integer pageSize
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Apparecchiatura> query = cb.createQuery(Apparecchiatura.class);
        Root<Apparecchiatura> root = query.from(Apparecchiatura.class);
        Predicate cond = cb.conjunction();
        if (idLab != null) {
            cond = cb.and(cond, cb.equal(getPathExp(root, "laboratorio.id", Object.class), idLab));
        }
        if (idDom != null) {
            cond = cb.and(cond, cb.equal(getPathExp(root, "dominio.id", Object.class), idDom));
        }

        if (idTipo != null) {
            cond = cb.and(cond, cb.equal(getPathExp(root, "tipologia.id", Object.class), idTipo));
        }

        if (idAz != null) {
            cond = cb.and(cond, cb.equal(getPathExp(root, "costruttore.id", Object.class), idAz));
        }

        if (idDistr != null) {
            cond = cb.and(cond, cb.equal(getPathExp(root, "distributore.id", Object.class), idDistr));
        }

        if (idMan != null) {
            cond = cb.and(cond, cb.equal(root.get("manutentore.id"), idMan));
        }

        if (idTar != null) {
            cond = cb.and(cond, cb.equal(getPathExp(root, "taratore.id", Object.class), idTar));
        }

        query.select(root)
                .where(cond)
                .orderBy(cb.asc(root.get("id")));

        return em.createQuery(query)
                .setFirstResult(start == null ? 0 : start)
                .setMaxResults(pageSize == null ? 10 : pageSize)
                .getResultList();
    }
    
     protected <E> Path<E> getPathExp(Root<Apparecchiatura> rq, String field, Class<E> clazz) {
        if (field.indexOf('.') == -1) {
            return rq.<E>get(field);
        }

        String[] fields = field.split("\\.");
        Path<E> p = rq.<E>get(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            p = p.get(fields[i]);
        }
        return p;
    }

}
