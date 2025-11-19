package com.escolamusica.exception;

/**
 * Exceção base para erros da aplicação Escola de Música.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class EscolaMusicaException extends Exception {
    
    /**
     * Construtor com mensagem.
     * 
     * @param mensagem Mensagem de erro
     */
    public EscolaMusicaException(String mensagem) {
        super(mensagem);
    }
    
    /**
     * Construtor com mensagem e causa.
     * 
     * @param mensagem Mensagem de erro
     * @param causa Causa do erro
     */
    public EscolaMusicaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
