package com.escolamusica.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilitária para formatação de datas.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class FormatadorData {
    
    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
    
    /**
     * Formata uma data no padrão brasileiro (dd/MM/yyyy).
     * 
     * @param data Data a ser formatada
     * @return Data formatada
     */
    public static String formatarData(LocalDate data) {
        if (data == null) {
            return "";
        }
        return data.format(FORMATO_DATA);
    }
    
    /**
     * Formata uma data e hora no padrão brasileiro (dd/MM/yyyy HH:mm).
     * 
     * @param dataHora Data e hora a ser formatada
     * @return Data e hora formatadas
     */
    public static String formatarDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            return "";
        }
        return dataHora.format(FORMATO_DATA_HORA);
    }
    
    /**
     * Formata apenas a hora (HH:mm).
     * 
     * @param dataHora Data e hora
     * @return Hora formatada
     */
    public static String formatarHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            return "";
        }
        return dataHora.format(FORMATO_HORA);
    }
    
    /**
     * Converte string para LocalDate.
     * 
     * @param data Data em formato dd/MM/yyyy
     * @return LocalDate
     */
    public static LocalDate parseData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return null;
        }
        return LocalDate.parse(data, FORMATO_DATA);
    }
    
    /**
     * Converte string para LocalDateTime.
     * 
     * @param dataHora Data e hora em formato dd/MM/yyyy HH:mm
     * @return LocalDateTime
     */
    public static LocalDateTime parseDataHora(String dataHora) {
        if (dataHora == null || dataHora.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(dataHora, FORMATO_DATA_HORA);
    }
}
