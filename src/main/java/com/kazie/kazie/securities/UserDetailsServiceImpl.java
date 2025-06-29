package com.kazie.kazie.securities;

import com.kazie.kazie.models.entities.Utilisateur;
import com.kazie.kazie.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UtilisateurRepository utilisateurDao;

    public UserDetailsServiceImpl(UtilisateurRepository utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return org.springframework.security.core.userdetails.User
                .withUsername(utilisateur.getEmail())
                .password(utilisateur.getMotDePasse())
                .authorities("ROLE_"+utilisateur.getUtilisateurRole().name()) // rôle exemple : "CLIENT"
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!utilisateur.isCompteValide())
                .build();
    }
}
