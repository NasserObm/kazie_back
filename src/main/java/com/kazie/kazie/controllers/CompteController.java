package com.kazie.kazie.controllers;

import com.kazie.kazie.models.dtos.requests.DevenirProRequest;
import com.kazie.kazie.models.dtos.requests.ProfileUtilisateurRequest;
import com.kazie.kazie.services.interfaces.UtilisateurServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/v1/compte")
public class CompteController {
    private final UtilisateurServiceInterface utilisateurServiceInterface;

    public CompteController(UtilisateurServiceInterface utilisateurServiceInterface) {
        this.utilisateurServiceInterface = utilisateurServiceInterface;
    }

    @PutMapping
    public ResponseEntity<?> editerProfile(@RequestBody ProfileUtilisateurRequest utilisateurInscriptionRequest){
        utilisateurServiceInterface.editerCompteSimple(utilisateurInscriptionRequest);
        return ResponseEntity.ok("modification effectuée avec succès");
    }
    @GetMapping
    public ResponseEntity<?> voirProfile(){

     return ResponseEntity.ok(utilisateurServiceInterface.voirProfile());
    }
    @PutMapping("/motDePasse")
    public ResponseEntity<?> changerMotDePasse(){
        //Todo dans le serive
        return ResponseEntity.ok("fonctionnalité en maintenance");
    }

    @PutMapping("/client/pro")
    public ResponseEntity<?> devenirPro(@RequestBody DevenirProRequest devenirProRequest){
        utilisateurServiceInterface.devenirPro(devenirProRequest);
        return ResponseEntity.ok("Transition au compte pro réussie");
    }

    @PutMapping("/professionnel/metier/{nomMetier}")
    public ResponseEntity<?> changerMetier(@PathVariable String nomMetier){
        utilisateurServiceInterface.changerMetier(nomMetier);
        return ResponseEntity.ok("Modification réussie");
    }

    @PutMapping("/bloquer/{email}")
    public ResponseEntity<?> bloquerCompte(@PathVariable String email){
        utilisateurServiceInterface.bloquerCompte(email);
       return ResponseEntity.ok("Compte bloquer");
    }
    @PutMapping("/debloquer/{email}")
    public ResponseEntity<?> debloquerCompte(@PathVariable String email){
        utilisateurServiceInterface.debloquerCompte(email);
      return ResponseEntity.ok("Compte débloquer");
    }
    @PutMapping("/voir/{emailPro}")
    public void voir(@PathVariable String emailPro){
        utilisateurServiceInterface.voir(emailPro);
    }
}
