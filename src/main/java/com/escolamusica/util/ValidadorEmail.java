package com.escolamusica.util;

import java.util.regex.Pattern;

/**
 * Classe utilitária para validação de Email.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class ValidadorEmail {
    
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    
    /**
     * Valida um endereço de email.
     * 
     * @param email Email a ser validado
     * @return true se válido, false caso contrário
     */
    public static boolean validar(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        return pattern.matcher(email.trim()).matches();
    }
    
    /**
     * Normaliza um email (remove espaços e converte para minúsculas).
     * 
     * @param email Email a ser normalizado
     * @return Email normalizado
     */
    public static String normalizar(String email) {
        if (email == null || email.isEmpty()) {
            return email;
        }
        
        return email.trim().toLowerCase();
    }
}
