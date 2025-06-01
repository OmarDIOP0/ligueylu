package master.ipld.ligueylu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(
            regexp = "\\d{7,9}",
            message = "Le numéro doit contenir entre 7 et 9 chiffres.")
    private String numero;

    @NotBlank
    private String ville;
    private String rue;
    private String codePostal;
    private String pays;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adresse", orphanRemoval = true)
    private List<Client> clients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adresse", orphanRemoval = true)
    private List<Prestataire> prestataires;
}
