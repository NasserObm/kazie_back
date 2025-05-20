    package com.kazie.kazie.services.implementations;

    import com.kazie.kazie.exceptions.ResourceNotFoundException;
    import com.kazie.kazie.models.entities.Utilisateur;
    import com.kazie.kazie.models.entities.Vue;
    import com.kazie.kazie.models.enums.TypeVue;
    import com.kazie.kazie.models.enums.UtilisateurRole;
    import com.kazie.kazie.repositories.UtilisateurRepository;
    import com.kazie.kazie.repositories.VueRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class VueServiceImpl {

        private final VueRepository vueRepository;
        private final UtilisateurRepository utilisateurRepository;

        public boolean ajouterVueSiNouvelle( TypeVue type, String cibleNom) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Utilisateur utilisateur=utilisateurRepository.findByEmail(email).orElseThrow(
                    ()-> new ResourceNotFoundException("Aucun utilisateur ne possède ce nom")
            );
            // Ne compter les vues que pour les CLIENTS
            if (!utilisateur.getUtilisateurRole().equals(UtilisateurRole.CLIENT)) {
                return false;
            }

            boolean dejaVu = vueRepository.existsByUtilisateurAndTypeAndCibleNom(utilisateur, type, cibleNom);

            if (!dejaVu) {
                Vue vue = new Vue();
                vue.setUtilisateur(utilisateur);
                vue.setType(type);
                vue.setCibleNom(cibleNom);
                vueRepository.save(vue);
                return true;
            }
            return false;
        }
    }
