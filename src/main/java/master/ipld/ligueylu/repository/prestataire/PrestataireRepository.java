package master.ipld.ligueylu.repository.prestataire;

import master.ipld.ligueylu.model.Prestataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PrestataireRepository extends JpaRepository<Prestataire, Long>
{
    Optional<Prestataire> findByEmail(String email);
    @Query("SELECT s.libelle, COUNT(p) FROM Prestataire p JOIN p.specialites s GROUP BY s.libelle")
    List<Object[]> countPrestataireBySpecialites();
}
