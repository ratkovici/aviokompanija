package org.acme.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "putnik")
public class Putnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String ime;
    public String prezime;

    @OneToOne(mappedBy = "putnik")
    public Pasos pasos;

    @OneToMany(mappedBy = "putnik")
    public List<Karta> karte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Karta> getKarte() {
        return karte;
    }

    public void setKarte(List<Karta> karte) {
        this.karte = karte;
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