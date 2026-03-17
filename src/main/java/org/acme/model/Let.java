package org.acme.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "let")
public class Let {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String brojLeta;
    public String polaziste;
    public String odrediste;
    @ManyToOne
    @JoinColumn(name = "avion_id")
    public Avion avion;
    @ManyToMany
    @JoinTable(
            name = "let_radnik",
            joinColumns = @JoinColumn(name = "let_id"),
            inverseJoinColumns = @JoinColumn(name = "radnik_id")
    )
    public List<Radnik> posada;
    @OneToMany(mappedBy = "let")
    public List<Karta> karte;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrojLeta() {
        return brojLeta;
    }

    public void setBrojLeta(String brojLeta) {
        this.brojLeta = brojLeta;
    }

    public String getPolaziste() {
        return polaziste;
    }

    public void setPolaziste(String polaziste) {
        this.polaziste = polaziste;
    }

    public String getOdrediste() {
        return odrediste;
    }

    public void setOdrediste(String odrediste) {
        this.odrediste = odrediste;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public List<Radnik> getPosada() {
        return posada;
    }

    public void setPosada(List<Radnik> posada) {
        this.posada = posada;
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
        Let let = (Let) o;
        return Objects.equals(id, let.id) && Objects.equals(brojLeta, let.brojLeta) && Objects.equals(polaziste, let.polaziste) && Objects.equals(odrediste, let.odrediste) && Objects.equals(avion, let.avion) && Objects.equals(posada, let.posada) && Objects.equals(karte, let.karte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brojLeta, polaziste, odrediste, avion, posada, karte);
    }
}