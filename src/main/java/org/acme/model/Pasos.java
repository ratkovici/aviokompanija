package org.acme.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pasos")
public class Pasos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String brojPasosa;
    public String drzava;

    @OneToOne
    @JoinColumn(name = "putnik_id")
    public Putnik putnik;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) {
        this.brojPasosa = brojPasosa;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public Putnik getPutnik() {
        return putnik;
    }

    public void setPutnik(Putnik putnik) {
        this.putnik = putnik;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pasos pasos = (Pasos) o;
        return id == pasos.id && Objects.equals(brojPasosa, pasos.brojPasosa) && Objects.equals(drzava, pasos.drzava) && Objects.equals(putnik, pasos.putnik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brojPasosa, drzava, putnik);
    }
}