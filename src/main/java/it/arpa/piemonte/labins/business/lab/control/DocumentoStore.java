/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.boundary.AziendaLink;
import it.arpa.piemonte.labins.business.lab.boundary.DocumentoLink;
import it.arpa.piemonte.labins.business.lab.boundary.FuoriServizioLink;
import it.arpa.piemonte.labins.business.lab.entity.Documento;
import it.arpa.piemonte.labins.business.lab.entity.Documento;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author alfonso
 */
@Stateless
public class DocumentoStore extends Store<Documento> {

    public final String UPLOAD_PATH = "/var/webapps/labins/";

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public Documento find(Long id) {
        return em.find(Documento.class, id);
    }

    public Documento save(Documento d, InputStream is) {
        Documento saved = em.merge(d);
        try {
            Files.copy(is, documentPath(saved.getFile()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new EJBException("save document failed...");
        }
        return saved;
    }

    public void remove(Long id) {
        Documento saved = find(id);
        try {
            Files.delete(documentPath(saved.getFile()));
        } catch (IOException ex) {
            throw new EJBException("delete document failed...");
        }
        em.remove(saved);
    }

    private Path documentPath(String file) {
        return Paths.get(UPLOAD_PATH + file);
    }

    public File getFile(String fileName) {
        return documentPath(fileName).toFile();
    }

    private Predicate searchPredicate(
            CriteriaBuilder cb,
            Root<Documento> root,
            Long idApparecchiatura,
            Long idFuoriServizio) {

        Predicate cond = cb.conjunction();
        if (idApparecchiatura != null) {
            cond = cb.and(cond, cb.equal(getPathExp(root, "apparecchiatura.id", Object.class), idApparecchiatura));
        }
        if (idFuoriServizio != null) {
            cond = cb.and(cond, cb.equal(getPathExp(root, "fs.id", Object.class), idFuoriServizio));
        }

        return cond;
    }

    public List<Documento> search(
            Long idApparecchiatura,
            Long idFuoriServizio,
            Integer start,
            Integer pageSize) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Documento> query = cb.createQuery(Documento.class);
        Root<Documento> root = query.from(Documento.class);

        query.select(root)
                .where(searchPredicate(cb, root, idApparecchiatura, idFuoriServizio))
                .orderBy(cb.asc(root.get("denominazione")));

        TypedQuery<Documento> q = em.createQuery(query);
        if (start != null) {
            q.setFirstResult(start);
        }
        if (pageSize != null) {
            q.setMaxResults(pageSize);
        }
        return q.getResultList();
    }

    public List<DocumentoLink> searchLink(
            Long idApparecchiatura,
            Long idFuoriServizio,
            Integer start,
            Integer pageSize) {
        return search(idApparecchiatura,idFuoriServizio, start, pageSize).stream().map(DocumentoLink::new)
                .collect(Collectors.toList());
    }

    public int searchCount(
            Long idApparecchiatura,
            Long idFuoriServizio) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery();
        Root<Documento> root = query.from(Documento.class);

        query.select(cb.count(root))
                .where(searchPredicate(cb, root, idApparecchiatura, idFuoriServizio));

        return ((Long) em.createQuery(query)
                .getSingleResult()).intValue();
    }
}
