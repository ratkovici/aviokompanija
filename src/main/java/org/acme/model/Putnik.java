package org.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "putnik")
@NamedQuery(name = Putnik.GET_ALL_PUTNICI, query = "Select p.id, p.ime, p.prezime from Putnik p")
@NamedQuery(name = Putnik.GET_PUTNIK_BY_NAME, query = "Select p from Putnik p where p.ime = :imeP")
public class Putnik {

    public static final String GET_ALL_PUTNICI = "GetAllPutnici";
    public static final String GET_PUTNIK_BY_NAME = "GetPutnikByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String ime;
    public String prezime;

    @OneToOne(mappedBy = "putnik", cascade = CascadeType.ALL)
    public Pasos pasos;


    @OneToOne(mappedBy = "putnik", cascade = CascadeType.ALL)
    public DetaljiPutnika detalji;

    @JsonIgnore
    @OneToMany(mappedBy = "putnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Karta> karte;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "putnik_uploaded",
            joinColumns = @JoinColumn(name = "putnik_id"),
            inverseJoinColumns = @JoinColumn(name = "uploaded_file_id")

    )
   // @JsonIgnore
    public List<UploadedFile> uploadedFiles = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pasos getPasos() {
        return pasos;
    }

    public void setPasos(Pasos pasos) {
        this.pasos = pasos;
    }

    public DetaljiPutnika getDetalji() {
        return detalji;
    }

    public void setDetalji(DetaljiPutnika detalji) {
        this.detalji = detalji;
    }

    public List<Karta> getKarte() {
        return karte;
    }

    public void setKarte(List<Karta> karte) {
        this.karte = karte;
    }

    public List<UploadedFile> getUploadedFiles() {
        return this.uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Putnik putnik = (Putnik) o;
        return id == putnik.id && Objects.equals(ime, putnik.ime) && Objects.equals(prezime, putnik.prezime) && Objects.equals(pasos, putnik.pasos) && Objects.equals(karte, putnik.karte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, pasos, karte);
    }
}