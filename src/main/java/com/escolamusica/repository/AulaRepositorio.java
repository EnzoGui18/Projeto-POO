package com.escolamusica.repository;

import com.escolamusica.model.Aula;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciar aulas.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class AulaRepositorio extends RepositorioGenerico<Aula, String> {
    
    @Override
    protected String extrairId(Aula entidade) {
        return entidade.getId();
    }
    
    /**
     * Busca aulas de um professor.
     * 
     * @param professorId ID do professor
     * @return Lista de aulas do professor
     */
    public List<Aula> buscarPorProfessor(String professorId) {
        return dados.values().stream()
                .filter(aula -> aula.getProfessor().getId().equals(professorId))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca aulas de uma sala.
     * 
     * @param salaId ID da sala
     * @return Lista de aulas da sala
     */
    public List<Aula> buscarPorSala(String salaId) {
        return dados.values().stream()
                .filter(aula -> aula.getSala().getId().equals(salaId))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca aulas em um período.
     * 
     * @param dataInicio Data/hora inicial
     * @param dataFim Data/hora final
     * @return Lista de aulas no período
     */
    public List<Aula> buscarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return dados.values().stream()
                .filter(aula -> !aula.getDataHora().isBefore(dataInicio) && 
                               !aula.getDataHora().isAfter(dataFim))
                .collect(Collectors.toList());
    }
    
    /**
     * Lista aulas realizadas.
     * 
     * @return Lista de aulas realizadas
     */
    public List<Aula> listarRealizadas() {
        return dados.values().stream()
                .filter(Aula::isRealizada)
                .collect(Collectors.toList());
    }
    
    /**
     * Lista aulas pendentes.
     * 
     * @return Lista de aulas não realizadas
     */
    public List<Aula> listarPendentes() {
        return dados.values().stream()
                .filter(aula -> !aula.isRealizada())
                .collect(Collectors.toList());
    }
}
