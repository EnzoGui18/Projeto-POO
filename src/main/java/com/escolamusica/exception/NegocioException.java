package com.escolamusica.exception;

/**
 * Exceção lançada quando uma operação de negócio falha.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class NegocioException extends EscolaMusicaException {
    
    /**
     * Construtor com mensagem.
     * 
     * @param mensagem Mensagem de erro de negócio
     */
    public NegocioException(String mensagem) {
        super(mensagem);
    }
}
