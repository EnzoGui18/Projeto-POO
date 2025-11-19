package com.escolamusica.util;

/**
 * Classe utilitária para validação de CPF.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class ValidadorCPF {
    
    /**
     * Valida um CPF brasileiro.
     * 
     * @param cpf CPF a ser validado (com ou sem formatação)
     * @return true se válido, false caso contrário
     */
    public static boolean validar(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }
        
        // Remove formatação
        cpf = cpf.replaceAll("[^0-9]", "");
        
        // Verifica tamanho
        if (cpf.length() != 11) {
            return false;
        }
        
        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        try {
            // Calcula primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 > 9) digito1 = 0;
            
            // Calcula segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 > 9) digito2 = 0;
            
            // Verifica dígitos
            return Character.getNumericValue(cpf.charAt(9)) == digito1 &&
                   Character.getNumericValue(cpf.charAt(10)) == digito2;
                   
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Formata um CPF.
     * 
     * @param cpf CPF sem formatação
     * @return CPF formatado (xxx.xxx.xxx-xx)
     */
    public static String formatar(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return cpf;
        }
        
        cpf = cpf.replaceAll("[^0-9]", "");
        
        if (cpf.length() != 11) {
            return cpf;
        }
        
        return cpf.substring(0, 3) + "." + 
               cpf.substring(3, 6) + "." + 
               cpf.substring(6, 9) + "-" + 
               cpf.substring(9, 11);
    }
}
