package com.escolamusica.repository;

import com.escolamusica.model.Sala;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciar salas e estúdios.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class SalaRepositorio extends RepositorioGenerico<Sala, String> {
    
    @Override
    protected String extrairId(Sala entidade) {
        return entidade.getId();
    }
    
    /**
     * Busca sala por número.
     * 
     * @param numero Número da sala
     * @return Sala encontrada ou null
     */
    public Sala buscarPorNumero(String numero) {
        return dados.values().stream()
                .filter(sala -> sala.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Busca salas por tipo.
     * 
     * @param tipo Tipo da sala
     * @return Lista de salas do tipo
     */
    public List<Sala> buscarPorTipo(String tipo) {
        return dados.values().stream()
                .filter(sala -> sala.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca salas por capacidade mínima.
     * 
     * @param capacidadeMinima Capacidade mínima
     * @return Lista de salas que atendem a capacidade
     */
    public List<Sala> buscarPorCapacidade(int capacidadeMinima) {
        return dados.values().stream()
                .filter(sala -> sala.getCapacidade() >= capacidadeMinima)
                .collect(Collectors.toList());
    }
    
    /**
     * Lista salas disponíveis.
     * 
     * @return Lista de salas disponíveis
     */
    public List<Sala> listarDisponiveis() {
        return dados.values().stream()
                .filter(Sala::isDisponivel)
                .collect(Collectors.toList());
    }
}
