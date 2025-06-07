package master.ipld.ligueylu.service.prestataire;

import lombok.RequiredArgsConstructor;
import master.ipld.ligueylu.dto.UserDto;
import master.ipld.ligueylu.exception.ResourceAlreadyExistException;
import master.ipld.ligueylu.exception.ResourceNotFoundException;
import master.ipld.ligueylu.model.Adresse;
import master.ipld.ligueylu.model.Prestataire;
import master.ipld.ligueylu.model.Reservation;
import master.ipld.ligueylu.model.Specialite;
import master.ipld.ligueylu.model.enums.Role;
import master.ipld.ligueylu.repository.prestataire.PrestataireRepository;
import master.ipld.ligueylu.request.AddPrestataireRequest;
import master.ipld.ligueylu.request.UpdatePrestataireRequest;
import org.hibernate.sql.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PrestataireService implements IPrestataireService {
    private final PrestataireRepository prestataireRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Prestataire addPrestataire(AddPrestataireRequest request) {
        boolean existedPrestataire = prestataireRepository.findByEmail(request.getEmail()).isPresent();
        if (existedPrestataire) {
            throw new ResourceAlreadyExistException("Prestataire " + request.getEmail() + " already exists");
        }

        return prestataireRepository.save(createPrestataire(request));
    }
    public Prestataire createPrestataire(AddPrestataireRequest request) {
        return new Prestataire(
                request.getEmail(),
                request.getNomComplet(),
                passwordEncoder.encode(request.getPassword()),
                request.getTelephone()
        );
    }

    @Override
    public List<Prestataire> getAllPrestataire() {
        return prestataireRepository.findAll();
    }

    @Override
    public Prestataire getPrestataireByEmail(String email) {
        return prestataireRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Prestataire introuvable"));
    }

    @Override
    public Prestataire getPrestataireById(Long id) {
        return prestataireRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Prestataire introuvable"));
    }

    @Override
    public Prestataire updatePrestataire(UpdatePrestataireRequest prestataire, Long id) {
        return prestataireRepository.findById(id)
                .map(existingPrestataire -> updateExistingPrestataire(existingPrestataire,prestataire))
                .map(prestataireRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Prestataire introuvable"));
    }

    public Prestataire updateExistingPrestataire(Prestataire existingPrestataire , UpdatePrestataireRequest request)
    {
        existingPrestataire.setEmail(request.getEmail());
        existingPrestataire.setNomComplet(request.getNomComplet());
        existingPrestataire.setPassword(passwordEncoder.encode(request.getPassword()));
        existingPrestataire.setTelephone(request.getTelephone());
        existingPrestataire.setRole(Role.PRESTATAIRE);
        return existingPrestataire;
    }

    @Override
    public void deletePrestataire(Long id) {
      prestataireRepository.findById(id)
              .ifPresentOrElse(prestataireRepository::delete,
                      () -> {throw new ResourceNotFoundException("Prestataire introuvable");});
    }

    @Override
    public boolean isPrestataireActif(Long prestataireId) {
        return false;
    }

    @Override
    public List<Prestataire> searchBySpecialite(String nomSpecialite) {
        return List.of();
    }

    @Override
    public List<Prestataire> findByAdresse(String villeOuRegion) {
        return List.of();
    }

    @Override
    public List<Prestataire> findByScoreGreaterThan(double minScore) {
        return List.of();
    }

    @Override
    public void updateScore(Long prestataireId, double newScore) {

    }

    @Override
    public double getScore(Long prestataireId) {
        return 0;
    }

    @Override
    public Adresse getAdresse(Long prestataireId) {
        return null;
    }

    @Override
    public void updateAdresse(Long prestataireId, Adresse adresse) {

    }

    @Override
    public Map<String, Long> countPrestatairesBySpecialite() {
        List<Object[]> results = prestataireRepository.countPrestataireBySpecialites();
        Map<String, Long> counts = new HashMap<>();
        for(Object[] result : results) {
            String specialite = (String) result[0];
            Long count = (Long) result[1];
            counts.put(specialite, count);
        }
        return counts;
    }

    @Override
    public Set<Specialite> getSpecialites(Long prestataireId) {
        return Set.of();
    }

    @Override
    public void addSpecialite(Long prestataireId, Specialite specialite) {

    }

    @Override
    public void removeSpecialite(Long prestataireId, Long specialiteId) {

    }

    @Override
    public List<master.ipld.ligueylu.model.Service> getServicesByPrestataire(Long prestataireId) {
        return List.of();
    }

    @Override
    public master.ipld.ligueylu.model.Service addServiceToPrestataire(Long prestataireId, master.ipld.ligueylu.model.Service service) {
        return null;
    }

    @Override
    public void removeServiceFromPrestataire(Long prestataireId, Long serviceId) {

    }

    @Override
    public List<Reservation> getReservationsByPrestataire(Long prestataireId) {
        return List.of();
    }

    @Override
    public void cancelReservation(Long prestataireId, Long reservationId) {

    }

    @Override
    public Reservation addReservationToPrestataire(Long prestataireId, Reservation reservation) {
        return null;
    }
}
