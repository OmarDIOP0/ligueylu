package master.ipld.ligueylu.service.prestataire;

import master.ipld.ligueylu.model.Prestataire;

import java.util.List;

public interface IPrestataireService {
    List<Prestataire> getAllPrestataire();
    Prestataire getPrestataireByEmail(String email);
    Prestataire getPrestataireById(Long id);

}
