/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.control;

import it.arpa.piemonte.labins.business.lab.entity.Documento;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alfonso
 */
@Stateless
public class DocumentoStore {

    public final String UPLOAD_PATH = "/var/webapps/labins/";

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public List<Documento> findByApparecchiatura(Long id) {
        return em.createQuery("select e from Documento e where e.apparecchiatura IS NOT NULL and e.apparecchiatura.id = :apparecchiatura_id",Documento.class)
                        .setParameter("apparecchiatura_id", id)
                        .getResultList();
    }

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
}
