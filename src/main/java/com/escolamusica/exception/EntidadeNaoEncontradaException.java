package com.escolamusica.exception;

/**
 * Exceção lançada quando uma entidade não é encontrada.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class EntidadeNaoEncontradaException extends EscolaMusicaException {
    
    /**
     * Construtor com mensagem padrão.
     * 
     * @param entidade Nome da entidade
     * @param id Identificador da entidade
     */
    public EntidadeNaoEncontradaException(String entidade, String id) {
        super(String.format("%s com ID '%s' não encontrado(a)", entidade, id));
    }
    
    /**
     * Construtor com mensagem personalizada.
     * 
     * @param mensagem Mensagem de erro
     */
    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
