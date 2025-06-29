package com.kazie.kazie.controllers;

import com.kazie.kazie.models.dtos.requests.MetierRequest;
import com.kazie.kazie.services.interfaces.MetierServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/metiers")

public class MetierController {
    public MetierController(MetierServiceInterface metierServiceInterface) {
        this.metierServiceInterface = metierServiceInterface;
    }

    //injection de dépendance par constructeur
    private final MetierServiceInterface metierServiceInterface;
    //implementations end-point CRUD
    @PostMapping
    public ResponseEntity<?> ajouter(@RequestBody MetierRequest metierRequest){
        return ResponseEntity.ok(metierServiceInterface.ajouter(metierRequest));
    }
    @GetMapping
    public ResponseEntity<?> lister(){
        return ResponseEntity.ok(metierServiceInterface.lister());
    }
    @GetMapping("/{nom}")
    public ResponseEntity<?> rechercherParNom(@PathVariable String nom){
        return ResponseEntity.ok(metierServiceInterface.rechercherParNom(nom));
    }
    @PutMapping("/{nom}")
    public ResponseEntity<?> editerParNom(@PathVariable String nom, @RequestBody MetierRequest metierRequest){
        return ResponseEntity.ok(metierServiceInterface.editerParNom(nom,metierRequest));
    }
    @DeleteMapping("/{nom}")
    public void supprimerParNom(@PathVariable String nom){
         metierServiceInterface.supprimerParNom(nom);
    }

    //logique metier
    @GetMapping("/categorie/{nomCategorie}")
    public ResponseEntity<?> listerParCategories( @PathVariable String nomCategorie){
        return ResponseEntity.ok(metierServiceInterface.listerParCategorie(nomCategorie));
    }

    @PutMapping("/vue/{nomMetier}")
    public void vue(@PathVariable String nomMetier){
        metierServiceInterface.voirMetier(nomMetier);
    }

    @GetMapping("/search")
    public ResponseEntity<?> rechercherParMotCle(@RequestParam String query) {
        return ResponseEntity.ok(metierServiceInterface.rechercherParMotCle(query));
    }

}
