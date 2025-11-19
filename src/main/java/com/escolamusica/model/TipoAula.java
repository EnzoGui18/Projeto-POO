package com.escolamusica.model;

/**
 * Enumeração que representa os tipos de aula disponíveis na escola.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public enum TipoAula {
    INDIVIDUAL("Individual", 1, 1),
    GRUPO("Grupo", 1, 8),
    TURMA("Turma", 1, 15);
    
    private final String descricao;
    private final int minimoAlunos;
    private final int maximoAlunos;
    
    /**
     * Construtor do enum TipoAula.
     * 
     * @param descricao Descrição do tipo de aula
     * @param minimoAlunos Número mínimo de alunos
     * @param maximoAlunos Número máximo de alunos
     */
    TipoAula(String descricao, int minimoAlunos, int maximoAlunos) {
        this.descricao = descricao;
        this.minimoAlunos = minimoAlunos;
        this.maximoAlunos = maximoAlunos;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public int getMinimoAlunos() {
        return minimoAlunos;
    }
    
    public int getMaximoAlunos() {
        return maximoAlunos;
    }
}
