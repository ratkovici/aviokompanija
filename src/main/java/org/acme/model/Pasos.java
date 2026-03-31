package org.acme.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "pasos")
public class Pasos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "broj_pasosa")
    public String brojPasosa;

    public String drzava;

    @OneToOne
    @JoinColumn(name = "putnik_id")
    public Putnik putnik;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasos pasos = (Pasos) o;
        return id == pasos.id && Objects.equals(brojPasosa, pasos.brojPasosa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brojPasosa);
    }
}
