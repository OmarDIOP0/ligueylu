package master.ipld.ligueylu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import master.ipld.ligueylu.model.abstracts.Utilisateur;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prestataire extends Utilisateur {
    @DecimalMin(value = "0.0", inclusive = true, message = "Le score doit être positif")
    private double score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adresse_id")
    @JsonIgnore
    private Adresse adresse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private Administrateur administrateur;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "specialite_prestataire", joinColumns = {
            @JoinColumn(name = "specialite_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private Set<Specialite> specialites = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "prestataire",orphanRemoval = true)
    private List<Service> services;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "prestataire",orphanRemoval = true)
    private List<Reservation> reservations;
}
