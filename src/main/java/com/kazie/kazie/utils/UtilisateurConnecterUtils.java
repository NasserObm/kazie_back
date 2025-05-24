package com.kazie.kazie.utils;
import org.springframework.security.core.context.SecurityContextHolder;

public class UtilisateurConnecterUtils {
    public static String utilsConnecter(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
