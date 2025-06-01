package master.ipld.ligueylu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "le libelle est obligatoire")
    private String libelle;
    @Lob
    private String description;
    @Min(value = 0, message = "L'annee d'experience doit etre positive")
    private int anneeExperience;

    @Lob
    private byte[] certification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prestataire_id")
    private Prestataire prestataire;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    @JsonIgnore
    private Reservation reservation;

    @OneToOne(mappedBy = "service", cascade = CascadeType.ALL,orphanRemoval = true)
    private Paiement paiement;

    @OneToOne(mappedBy = "service" , cascade = CascadeType.ALL,orphanRemoval = true)
    private Evaluation evaluation;
}
