package master.ipld.ligueylu.controller;

import lombok.RequiredArgsConstructor;
import master.ipld.ligueylu.exception.ResourceNotFoundException;
import master.ipld.ligueylu.model.Adresse;
import master.ipld.ligueylu.model.Prestataire;
import master.ipld.ligueylu.response.ApiResponse;
import master.ipld.ligueylu.service.prestataire.IPrestataireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/prestataires")
public class PrestataireController {
    private final IPrestataireService prestataireService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllAdresses() {
        try {
            List<Prestataire> prestataires = prestataireService.getAllPrestataire();
            return ResponseEntity.ok(new ApiResponse(
                    true,
                    "Found",
                    prestataires
            ));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
                    false,
                    "Error",
                    e.getMessage()
            ));
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> getPrestataireByEmail(@PathVariable String email) {
        try{
            Prestataire prestataire = prestataireService.getPrestataireByEmail(email);
            return ResponseEntity.ok(new ApiResponse(
                    true,
                    "Found",
                    prestataire
            ));
        }
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    false,
                    e.getMessage(),
                    null
            ));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPrestataireById(@PathVariable Long id) {
        try{
            Prestataire prestataire = prestataireService.getPrestataireById(id);
            return ResponseEntity.ok(new ApiResponse(
                    true,
                    "Found",
                    prestataire
            ));
        }
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(
                    false,
                    e.getMessage(),
                    null
            ));
        }
    }
}
