package org.acme.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "uloga_radnika")
public class UlogaRadnika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String nazivUloge;
    @OneToMany(mappedBy = "uloga")
    public List<Radnik> radnici;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazivUloge() {
        return nazivUloge;
    }

    public void setNazivUloge(String nazivUloge) {
        this.nazivUloge = nazivUloge;
    }

    public List<Radnik> getRadnici() {
        return radnici;
    }

    public void setRadnici(List<Radnik> radnici) {
        this.radnici = radnici;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UlogaRadnika that = (UlogaRadnika) o;
        return Objects.equals(id, that.id) && Objects.equals(nazivUloge, that.nazivUloge) && Objects.equals(radnici, that.radnici);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazivUloge, radnici);
    }
}