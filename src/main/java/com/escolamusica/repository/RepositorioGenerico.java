package com.escolamusica.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositório genérico em memória para gerenciar entidades.
 * Implementa operações CRUD básicas.
 * 
 * @param <T> Tipo da entidade
 * @param <ID> Tipo do identificador
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public abstract class RepositorioGenerico<T, ID> {
    
    protected Map<ID, T> dados;
    
    /**
     * Construtor que inicializa o mapa de dados.
     */
    public RepositorioGenerico() {
        this.dados = new HashMap<>();
    }
    
    /**
     * Salva ou atualiza uma entidade.
     * 
     * @param entidade Entidade a ser salva
     * @return Entidade salva
     */
    public T salvar(T entidade) {
        ID id = extrairId(entidade);
        dados.put(id, entidade);
        return entidade;
    }
    
    /**
     * Busca uma entidade por ID.
     * 
     * @param id Identificador da entidade
     * @return Optional contendo a entidade se encontrada
     */
    public Optional<T> buscarPorId(ID id) {
        return Optional.ofNullable(dados.get(id));
    }
    
    /**
     * Lista todas as entidades.
     * 
     * @return Lista de todas as entidades
     */
    public List<T> listarTodos() {
        return new ArrayList<>(dados.values());
    }
    
    /**
     * Remove uma entidade por ID.
     * 
     * @param id Identificador da entidade
     * @return true se removida, false caso contrário
     */
    public boolean remover(ID id) {
        return dados.remove(id) != null;
    }
    
    /**
     * Verifica se existe uma entidade com o ID informado.
     * 
     * @param id Identificador a verificar
     * @return true se existe, false caso contrário
     */
    public boolean existe(ID id) {
        return dados.containsKey(id);
    }
    
    /**
     * Conta o número total de entidades.
     * 
     * @return Quantidade de entidades
     */
    public int contar() {
        return dados.size();
    }
    
    /**
     * Remove todas as entidades.
     */
    public void limpar() {
        dados.clear();
    }
    
    /**
     * Método abstrato para extrair o ID de uma entidade.
     * Deve ser implementado pelas subclasses.
     * 
     * @param entidade Entidade da qual extrair o ID
     * @return ID da entidade
     */
    protected abstract ID extrairId(T entidade);
}
