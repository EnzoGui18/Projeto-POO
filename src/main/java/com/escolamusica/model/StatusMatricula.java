package com.escolamusica.model;

/**
 * Enumeração que representa os possíveis status de uma matrícula.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public enum StatusMatricula {
    ATIVA("Ativa"),
    SUSPENSA("Suspensa"),
    CANCELADA("Cancelada"),
    CONCLUIDA("Concluída");
    
    private final String descricao;
    
    /**
     * Construtor do enum StatusMatricula.
     * 
     * @param descricao Descrição do status
     */
    StatusMatricula(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
