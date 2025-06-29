package com.kazie.kazie.controllers;

import com.kazie.kazie.models.dtos.requests.DemandeConnexionRequest;
import com.kazie.kazie.models.dtos.requests.ProInscriptionRequest;
import com.kazie.kazie.models.dtos.requests.UtilisateurInscriptionRequest;
import com.kazie.kazie.models.dtos.responses.ConnexionReponse;
import com.kazie.kazie.services.interfaces.AuthenticationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    public AuthController(AuthenticationServiceInterface authenticationServiceInterface) {
        this.authenticationServiceInterface = authenticationServiceInterface;
    }

    //injection de dépandance par constructeur
    private final AuthenticationServiceInterface authenticationServiceInterface;
    @PostMapping("/client")
    public ResponseEntity<?> inscriptionClient(@RequestBody UtilisateurInscriptionRequest utilisateurInscriptionRequest){
        try {
            ConnexionReponse connexionReponse=authenticationServiceInterface.inscriptionClient(utilisateurInscriptionRequest);
            return ResponseEntity.ok(connexionReponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }
    @PostMapping("/admin")
    public ResponseEntity<?> inscriptionAdmin(@RequestBody UtilisateurInscriptionRequest utilisateurInscriptionRequest){
        try {
            ConnexionReponse connexionReponse=authenticationServiceInterface.inscriptionAdmin(utilisateurInscriptionRequest);
            return ResponseEntity.ok(connexionReponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }
    @PostMapping("/pro")
    public ResponseEntity<?> inscriptionPro(@RequestBody ProInscriptionRequest proInscriptionRequest){
        try {
            ConnexionReponse connexionReponse=authenticationServiceInterface.inscriptionPro(proInscriptionRequest);
            return ResponseEntity.ok(connexionReponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }
    @PostMapping
    public ResponseEntity<?> connecter(@RequestBody DemandeConnexionRequest demandeConnexionRequest){
        try {
            ConnexionReponse connexionReponse=authenticationServiceInterface.connecter(demandeConnexionRequest);
            return ResponseEntity.ok(connexionReponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }
}
