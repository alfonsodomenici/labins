/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business.lab.entity;

import it.arpa.piemonte.labins.business.lab.adapter.AziendaLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.CateneMisuraAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.DominioLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.GrandezzaLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.LaboratorioLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.TipoApparecchiaturaLinkAdapter;
import it.arpa.piemonte.labins.business.lab.adapter.UnitaMisuraLinkAdapter;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author utente
 */
@NamedQueries({
    @NamedQuery(name = Apparecchiatura.FIND_DI_RIFERIMENTO,
            query = "select e from Apparecchiatura e where e.laboratorio.id= :idLab and e.riferimento = true ORDER BY e.descrizione")
})

@Entity
@Table(name = "apparecchiatura")
public class Apparecchiatura extends BaseEntity {

    public static final String FIND_DI_RIFERIMENTO = "Apparecchiatura.findDiRiferimento";

    @NotEmpty(message = "Il campo modello è obbligatorio")
    @Column(nullable = false)
    private String modello;

    @NotEmpty(message = "Il campo matricola è obbligatorio")
    @Column(nullable = false)
    private String matricola;

    private String codice;

    private String firmware;

    @NotEmpty(message = "Il campo descrizione è obbligatorio")
    @Column(nullable = false)
    private String descrizione;

    @JsonbTypeAdapter(LaboratorioLinkAdapter.class)
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Laboratorio laboratorio;

    @JsonbTypeAdapter(DominioLinkAdapter.class)
    @ManyToOne
    private Dominio dominio;

    @JsonbTypeAdapter(CateneMisuraAdapter.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "apparecchiatura_catena_misura",
            joinColumns = @JoinColumn(name = "apparecchiatura_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "catena_misura_id", referencedColumnName = "id")
    )
    private Set<CatenaMisura> cateneMisura;

    @JsonbTypeAdapter(TipoApparecchiaturaLinkAdapter.class)
    @ManyToOne
    private TipoApparecchiatura tipologia;

    @JsonbTypeAdapter(AziendaLinkAdapter.class)
    @ManyToOne
    private Azienda costruttore;

    @JsonbTypeAdapter(AziendaLinkAdapter.class)
    @ManyToOne
    private Azienda distributore;

    @JsonbTypeAdapter(AziendaLinkAdapter.class)
    @ManyToOne
    private Azienda taratore;

    @JsonbTypeAdapter(AziendaLinkAdapter.class)
    @ManyToOne
    private Azienda manutentore; //deve esserci ???

    private boolean riferimento;

    @JsonbTypeAdapter(GrandezzaLinkAdapter.class)
    @ManyToOne
    private Grandezza grandezza;

    @JsonbTypeAdapter(UnitaMisuraLinkAdapter.class)
    @ManyToOne
    private UnitaMisura um;

    @Column(name = "campo_min")
    private Double campoMin;

    @Column(name = "campo_max")
    private Double campoMax;

    private String incertezza;

    @Column(name = "campo_operativo")
    private String campoOperativo;

    @Column(name = "criterio_accettazione")
    private String criterioAccettazione;

    @Column(name = "data_fabbricazione")
    private LocalDate fabbricatoIl;

    @Column(name = "data_acquisto")
    private LocalDate acquistatoIl;

    @Column(name = "data_entrata_funzione")
    private LocalDate inFunzioneDal;

    private Boolean taratura = false;
    private Boolean manutenzione = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gestione_taratura_id")
    private GestioneApparecchiatura gestioneTaratura;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gestione_manutenzione_id")
    private GestioneApparecchiatura gestioneManutenzione;

    private String responsabile;
    
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Dominio getDominio() {
        return dominio;
    }

    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }

    public Set<CatenaMisura> getCateneMisura() {
        return cateneMisura;
    }

    public void setCateneMisura(Set<CatenaMisura> cateneMisura) {
        this.cateneMisura = cateneMisura;
    }

    public TipoApparecchiatura getTipologia() {
        return tipologia;
    }

    public void setTipologia(TipoApparecchiatura tipologia) {
        this.tipologia = tipologia;
    }

    public Azienda getCostruttore() {
        return costruttore;
    }

    public void setCostruttore(Azienda costruttore) {
        this.costruttore = costruttore;
    }

    public Azienda getDistributore() {
        return distributore;
    }

    public void setDistributore(Azienda distributore) {
        this.distributore = distributore;
    }

    public Azienda getTaratore() {
        return taratore;
    }

    public void setTaratore(Azienda taratore) {
        this.taratore = taratore;
    }

    public Azienda getManutentore() {
        return manutentore;
    }

    public void setManutentore(Azienda manutentore) {
        this.manutentore = manutentore;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public boolean isRiferimento() {
        return riferimento;
    }

    public void setRiferimento(boolean riferimento) {
        this.riferimento = riferimento;
    }

    public Grandezza getGrandezza() {
        return grandezza;
    }

    public void setGrandezza(Grandezza grandezza) {
        this.grandezza = grandezza;
    }

    public UnitaMisura getUm() {
        return um;
    }

    public void setUm(UnitaMisura um) {
        this.um = um;
    }

    public Double getCampoMin() {
        return campoMin;
    }

    public void setCampoMin(Double campoMin) {
        this.campoMin = campoMin;
    }

    public Double getCampoMax() {
        return campoMax;
    }

    public void setCampoMax(Double campoMax) {
        this.campoMax = campoMax;
    }

    public String getIncertezza() {
        return incertezza;
    }

    public void setIncertezza(String incertezza) {
        this.incertezza = incertezza;
    }

    public String getCampoOperativo() {
        return campoOperativo;
    }

    public void setCampoOperativo(String campoOperativo) {
        this.campoOperativo = campoOperativo;
    }

    public String getCriterioAccettazione() {
        return criterioAccettazione;
    }

    public void setCriterioAccettazione(String criterioAccettazione) {
        this.criterioAccettazione = criterioAccettazione;
    }

    public LocalDate getFabbricatoIl() {
        return fabbricatoIl;
    }

    public void setFabbricatoIl(LocalDate fabbricatoIl) {
        this.fabbricatoIl = fabbricatoIl;
    }

    public LocalDate getAcquistatoIl() {
        return acquistatoIl;
    }

    public void setAcquistatoIl(LocalDate acquistatoIl) {
        this.acquistatoIl = acquistatoIl;
    }

    public LocalDate getInFunzioneDal() {
        return inFunzioneDal;
    }

    public void setInFunzioneDal(LocalDate inFunzioneDal) {
        this.inFunzioneDal = inFunzioneDal;
    }

    public Boolean isTaratura() {
        return taratura;
    }

    public void setTaratura(Boolean taratura) {
        this.taratura = taratura;
    }

    public Boolean isManutenzione() {
        return manutenzione;
    }

    public void setManutenzione(Boolean manutenzione) {
        this.manutenzione = manutenzione;
    }

    public GestioneApparecchiatura getGestioneTaratura() {
        return gestioneTaratura;
    }

    public void setGestioneTaratura(GestioneApparecchiatura gestioneTaratura) {
        this.gestioneTaratura = gestioneTaratura;
    }

    public GestioneApparecchiatura getGestioneManutenzione() {
        return gestioneManutenzione;
    }

    public void setGestioneManutenzione(GestioneApparecchiatura gestioneManutenzione) {
        this.gestioneManutenzione = gestioneManutenzione;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(String responsabile) {
        this.responsabile = responsabile;
    }

    
    @Override
    public String toString() {
        return "Apparecchiatura{" + "modello=" + modello + ", matricola=" + matricola + ", codice=" + codice + ", firmware=" + firmware + ", descrizione=" + descrizione + ", laboratorio=" + laboratorio + ", dominio=" + dominio + ", cateneMisura=" + cateneMisura + ", tipologia=" + tipologia + ", costruttore=" + costruttore + ", distributore=" + distributore + ", taratore=" + taratore + ", manutentore=" + manutentore + ", riferimento=" + riferimento + ", grandezza=" + grandezza + ", um=" + um + ", campoMin=" + campoMin + ", campoMax=" + campoMax + ", incertezza=" + incertezza + ", campoOperativo=" + campoOperativo + ", criterioAccettazione=" + criterioAccettazione + ", fabbricatoIl=" + fabbricatoIl + ", acquistatoIl=" + acquistatoIl + ", inFunzioneDal=" + inFunzioneDal + ", taratura=" + taratura + ", manutenzione=" + manutenzione + ", gestioneTaratura=" + gestioneTaratura + ", gestioneManutenzione=" + gestioneManutenzione + '}';
    }

    public LocalDate getDataPianificata() {
        Optional<LocalDate> dpTar = this.getGestioneTaratura() == null || this.getGestioneTaratura().getDataPianificata() == null
                ? Optional.empty() : Optional.of(this.getGestioneTaratura().getDataPianificata());
        Optional<LocalDate> dpMan = this.getGestioneManutenzione() == null || this.getGestioneManutenzione().getDataPianificata() == null
                ? Optional.empty() : Optional.of(this.getGestioneManutenzione().getDataPianificata());
        LocalDate d1 = dpTar.orElse(dpMan.orElse(LocalDate.MIN));
        LocalDate d2 = dpMan.orElse(dpTar.orElse(LocalDate.MIN));
        return d1 == d2 && d1 == LocalDate.MIN ? null : d1.isBefore(d2) ? d1 : d2;
    }

    public static JsonObject convertMinimal(Apparecchiatura apparecchiatura) {
        return Json.createObjectBuilder()
                .add("id", apparecchiatura.getId())
                .add("denominazione", apparecchiatura.getDescrizione())
                .build();
    }

}
