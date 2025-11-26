package com.escolamusica.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe utilitária para formatação de valores monetários.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class FormatadorMoeda {
    
    private static final NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
    
    /**
     * Formata um valor monetário no padrão brasileiro (R$ x.xxx,xx).
     * 
     * @param valor Valor a ser formatado
     * @return Valor formatado
     */
    public static String formatar(double valor) {
        return formatter.format(valor);
    }
    
    /**
     * Converte string monetária para double.
     * 
     * @param valorStr Valor em formato string
     * @return Valor em double
     */
    public static double parse(String valorStr) {
        if (valorStr == null || valorStr.trim().isEmpty()) {
            return 0.0;
        }
        
        // Remove símbolos e converte vírgula para ponto
        valorStr = valorStr.replaceAll("[^0-9,]", "").replace(",", ".");
        
        try {
            return Double.parseDouble(valorStr);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
