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
     * Aceita qualquer sequência de 11 dígitos numéricos.
     * 
     * @param cpf CPF a ser validado (com ou sem formatação)
     * @return true se válido, false caso contrário
     */
    public static boolean validar(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }
        
        // Remove formatação (pontos, traços, espaços)
        cpf = cpf.replaceAll("[^0-9]", "");
        
        // Verifica se tem exatamente 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Aceita qualquer sequência de 11 dígitos
        return true;
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
