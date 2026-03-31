package org.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "marka")
public class Marka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String naziv;
    @OneToMany(mappedBy = "marka")
    @JsonIgnore
    public List<Avion> avioni;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Marka marka = (Marka) o;
        return id == marka.id && Objects.equals(naziv, marka.naziv) && Objects.equals(avioni, marka.avioni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, avioni);
    }

    @Override
    public String toString() {
        return "Marka{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", avioni=" + avioni +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Avion> getAvioni() {
        return avioni;
    }

    public void setAvioni(List<Avion> avioni) {
        this.avioni = avioni;
    }
}