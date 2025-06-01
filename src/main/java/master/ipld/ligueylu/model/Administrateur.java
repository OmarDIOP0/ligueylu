package master.ipld.ligueylu.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import master.ipld.ligueylu.model.abstracts.Utilisateur;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Administrateur extends Utilisateur {

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "administrateur" , orphanRemoval = true)
    private List<Prestataire> prestataires;
}
