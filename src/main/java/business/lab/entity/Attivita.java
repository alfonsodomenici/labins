/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

/**
 *
 * @author utente
 */
public class Attivita extends AbstractEntity {

    public static enum Tipo {
        MANUTENZIONE, TARATURA, FS_STRAORDINARIO
    }

    public static enum Esito {
        POSITIVO, NEGATIVO
    }
}
