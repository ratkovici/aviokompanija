package org.acme.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "radnik")
public class Radnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String ime;
    public String prezime;
    @ManyToOne
    @JoinColumn(name = "uloga_id")
    public UlogaRadnika uloga;
    @ManyToMany(mappedBy = "posada")
    public List<Let> letovi;

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

    public UlogaRadnika getUloga() {
        return uloga;
    }

    public void setUloga(UlogaRadnika uloga) {
        this.uloga = uloga;
    }

    public List<Let> getLetovi() {
        return letovi;
    }

    public void setLetovi(List<Let> letovi) {
        this.letovi = letovi;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Radnik radnik = (Radnik) o;
        return id == radnik.id && Objects.equals(ime, radnik.ime) && Objects.equals(prezime, radnik.prezime) && Objects.equals(uloga, radnik.uloga) && Objects.equals(letovi, radnik.letovi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, uloga, letovi);
    }
}