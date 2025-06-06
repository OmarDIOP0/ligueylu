package master.ipld.ligueylu.repository.prestataire;

import master.ipld.ligueylu.model.Prestataire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrestataireRepository extends JpaRepository<Prestataire, Long>
{
    Optional<Prestataire> findByEmail(String email);
}
