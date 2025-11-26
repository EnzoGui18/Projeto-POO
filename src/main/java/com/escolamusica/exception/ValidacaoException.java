package com.escolamusica.exception;

/**
 * Exceção lançada quando ocorre um erro de validação.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class ValidacaoException extends EscolaMusicaException {
    
    /**
     * Construtor com mensagem.
     * 
     * @param mensagem Mensagem de erro de validação
     */
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
