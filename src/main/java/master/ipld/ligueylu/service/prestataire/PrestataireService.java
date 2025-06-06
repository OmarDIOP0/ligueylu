package master.ipld.ligueylu.service.prestataire;

import lombok.RequiredArgsConstructor;
import master.ipld.ligueylu.dto.UserDto;
import master.ipld.ligueylu.exception.ResourceNotFoundException;
import master.ipld.ligueylu.model.Prestataire;
import master.ipld.ligueylu.repository.prestataire.PrestataireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestataireService implements IPrestataireService {
    private final PrestataireRepository prestataireRepository;

    @Override
    public List<Prestataire> getAllPrestataire() {
        return prestataireRepository.findAll();
    }

    @Override
    public Prestataire getPrestataireByEmail(String email) {
        //        return UserDto.builder()
//                .id(prestataire.getId())
//                .email(prestataire.getEmail())
//                .nomComplet(prestataire.getNomComplet())
//                .role(prestataire.getRole().name())
//                .build();
        return prestataireRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Prestataire introuvable"));
    }

    @Override
    public Prestataire getPrestataireById(Long id) {
        return prestataireRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Prestataire introuvable"));
    }
}
