package com.escolamusica.model;

/**
 * Enumeração que representa os tipos de instrumentos disponíveis na escola.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public enum TipoInstrumento {
    PIANO("Piano"),
    VIOLAO("Violão"),
    GUITARRA("Guitarra"),
    BATERIA("Bateria"),
    BAIXO("Baixo"),
    TECLADO("Teclado"),
    SAXOFONE("Saxofone"),
    FLAUTA("Flauta"),
    VIOLINO("Violino"),
    CANTO("Canto");
    
    private final String descricao;
    
    /**
     * Construtor do enum TipoInstrumento.
     * 
     * @param descricao Descrição do tipo de instrumento
     */
    TipoInstrumento(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * Retorna a descrição do tipo de instrumento.
     * 
     * @return Descrição do instrumento
     */
    public String getDescricao() {
        return descricao;
    }
}
