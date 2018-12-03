/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.lab.entity;

import java.time.LocalDate;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author utente
 */
public class Pianificazione extends AbstractEntity {

    public static enum Tipo {
        TEMPORALE, DESCRITTIVA, PRIMA_USO
    }

    @Enumerated(EnumType.STRING)
    private Attivita.Tipo tipoAttivita;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Temporal(TemporalType.DATE)
    private LocalDate scadenza;

    private int freq;

    private String descrizione;

    private Azienda azienda;

    private String descrizioneAttivita;
}
