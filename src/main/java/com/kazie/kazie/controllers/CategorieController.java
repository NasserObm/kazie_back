package com.kazie.kazie.controllers;

import com.kazie.kazie.models.dtos.requests.CategorieRequest;
import com.kazie.kazie.models.dtos.responses.CategorieResponse;
import com.kazie.kazie.services.interfaces.CategorieServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/categories")

public class CategorieController {
    //Injections de dépendances par constructeur
    private final CategorieServiceInterface categorieServiceInterface;

    public CategorieController(CategorieServiceInterface categorieServiceInterface) {
        this.categorieServiceInterface = categorieServiceInterface;
    }

    @PostMapping()
    public ResponseEntity<?> ajouter(@RequestBody CategorieRequest categorieRequest){
        CategorieResponse categorieResponse =categorieServiceInterface.ajouter(categorieRequest);
        return ResponseEntity.ok(categorieResponse);
    }
    @GetMapping
    public ResponseEntity<?> lister(){
        return ResponseEntity.ok(categorieServiceInterface.lister());
    }
    @GetMapping("/{nom}")
    public ResponseEntity<?> rechercherParNom(@PathVariable String nom){
        return  ResponseEntity.ok(categorieServiceInterface.rechercherParNom(nom));
    }
    @PutMapping("/{nom}")
    public ResponseEntity<?> editerParNom(@PathVariable String nom,@RequestBody CategorieRequest categorieRequest){
        return ResponseEntity.ok(categorieServiceInterface.editerParNom(nom,categorieRequest));
    }
    @DeleteMapping("/{nom}")
    public ResponseEntity<?> supprimerParNom(@PathVariable String nom){
        categorieServiceInterface.supprimerParNom(nom);
        return ResponseEntity.ok("Categorie supprimer avec succes!");
    }

    //logique metiers end-points
    @PutMapping("/voir/{nomCategorie}")
    public void voir(@PathVariable String nomCategorie){
        categorieServiceInterface.voir(nomCategorie);
    }
}
