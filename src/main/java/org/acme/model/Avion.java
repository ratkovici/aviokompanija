package org.acme.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "avion")
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String registracija;
    @ManyToOne
    @JoinColumn(name = "marka_id")
    public Marka marka;
    @ManyToOne
    @JoinColumn(name = "aviokompanija_id")
    public Aviokompanija aviokompanija;
    @OneToMany(mappedBy = "avion")
    public List<Let> letovi;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Avion avion = (Avion) o;
        return id == avion.id && Objects.equals(registracija, avion.registracija) && Objects.equals(marka, avion.marka) && Objects.equals(aviokompanija, avion.aviokompanija) && Objects.equals(letovi, avion.letovi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registracija, marka, aviokompanija, letovi);
    }

    @Override
    public String toString() {
        return "Avion{" +
                "id=" + id +
                ", registracija='" + registracija + '\'' +
                ", marka=" + marka +
                ", aviokompanija=" + aviokompanija +
                ", letovi=" + letovi +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public Aviokompanija getAviokompanija() {
        return aviokompanija;
    }

    public void setAviokompanija(Aviokompanija aviokompanija) {
        this.aviokompanija = aviokompanija;
    }

    public List<Let> getLetovi() {
        return letovi;
    }

    public void setLetovi(List<Let> letovi) {
        this.letovi = letovi;
    }
}