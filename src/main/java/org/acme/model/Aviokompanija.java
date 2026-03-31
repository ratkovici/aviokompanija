package org.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "aviokompanija")
public class Aviokompanija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String naziv;
    @OneToMany(mappedBy = "aviokompanija")
    @JsonIgnore
    public List<Avion> avioni;

    public String getNaziv() {
        return naziv;
    }

    public int getId() {
        return id;
    }

    @JsonIgnore
    public List<Avion> getAvioni() {
        return avioni;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setAvioni(List<Avion> avioni) {
        this.avioni = avioni;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aviokompanija that = (Aviokompanija) o;
        return id == that.id && Objects.equals(naziv, that.naziv) && Objects.equals(avioni, that.avioni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, avioni);
    }


    @Override
    public String toString() {
        return "Aviokompanija{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}