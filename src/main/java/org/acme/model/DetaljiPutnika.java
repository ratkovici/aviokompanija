package org.acme.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetaljiPutnika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adresa;
    private String brojTelefona;
    private String napomena;


    @OneToOne
    @JoinColumn(name = "putnik_id")
    @JsonIgnore
    private Putnik putnik;
}