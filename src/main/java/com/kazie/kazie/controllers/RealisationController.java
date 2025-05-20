package com.kazie.kazie.controllers;

import com.kazie.kazie.models.dtos.requests.RealistionRequest;
import com.kazie.kazie.models.dtos.responses.RealisationResponse;
import com.kazie.kazie.services.interfaces.RealisationServiceInterface;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/realisations")
public class RealisationController {
    private final RealisationServiceInterface realisationServiceInterface;

   @PostMapping
   public ResponseEntity<?> ajouter(@RequestBody RealistionRequest realistionRequest){
       RealisationResponse realisationResponse=realisationServiceInterface.ajouter(realistionRequest);
       return ResponseEntity.ok(realisationResponse);
   }
   @PutMapping("/{titre}")
    public ResponseEntity<?> editerParTitre(@PathVariable String titre,@RequestBody RealistionRequest realistionRequest){
       RealisationResponse realisationResponse=realisationServiceInterface.editerParTitre(titre,realistionRequest);
       return ResponseEntity.ok(realisationResponse);
    }
    @GetMapping
   public ResponseEntity<?> lister(){
       return ResponseEntity.ok(realisationServiceInterface.lister());
    }
    @DeleteMapping("/{titre}")
    public ResponseEntity<?> supprimerParTitre(@PathVariable String titre){
       realisationServiceInterface.supprimerParTitre(titre);
       return ResponseEntity.ok("Realisation supprimer avec succès");
    }
}
