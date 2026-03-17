package org.acme.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "karta")
public class Karta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public double cijena;
    public String sediste;

    @ManyToOne
    @JoinColumn(name = "let_id")
    public Let let;

    @ManyToOne
    @JoinColumn(name = "putnik_id")
    public Putnik putnik;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public String getSediste() {
        return sediste;
    }

    public void setSediste(String sediste) {
        this.sediste = sediste;
    }

    public Let getLet() {
        return let;
    }

    public void setLet(Let let) {
        this.let = let;
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
        Karta karta = (Karta) o;
        return id == karta.id && Double.compare(cijena, karta.cijena) == 0 && Objects.equals(sediste, karta.sediste) && Objects.equals(let, karta.let) && Objects.equals(putnik, karta.putnik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cijena, sediste, let, putnik);
    }
}