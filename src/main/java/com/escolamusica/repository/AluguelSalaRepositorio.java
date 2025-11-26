package com.escolamusica.repository;

import com.escolamusica.model.AluguelSala;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciar aluguéis de salas.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class AluguelSalaRepositorio extends RepositorioGenerico<AluguelSala, String> {
    
    @Override
    protected String extrairId(AluguelSala entidade) {
        return entidade.getId();
    }
    
    /**
     * Busca aluguéis por sala.
     * 
     * @param salaId ID da sala
     * @return Lista de aluguéis
     */
    public List<AluguelSala> buscarPorSala(String salaId) {
        return dados.values().stream()
                .filter(aluguel -> aluguel.getSala().getId().equals(salaId))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca aluguéis por aluno.
     * 
     * @param alunoId ID do aluno
     * @return Lista de aluguéis
     */
    public List<AluguelSala> buscarPorAluno(String alunoId) {
        return dados.values().stream()
                .filter(aluguel -> aluguel.getAluno().getId().equals(alunoId))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca aluguéis em um período.
     * 
     * @param inicio Data/hora de início
     * @param fim Data/hora de fim
     * @return Lista de aluguéis
     */
    public List<AluguelSala> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return dados.values().stream()
                .filter(aluguel -> {
                    LocalDateTime inicioAluguel = aluguel.getDataHoraInicio();
                    LocalDateTime fimAluguel = aluguel.getDataHoraFim();
                    return inicioAluguel.isBefore(fim) && fimAluguel.isAfter(inicio);
                })
                .collect(Collectors.toList());
    }
    
    /**
     * Busca aluguéis de uma sala em um período específico.
     * 
     * @param salaId ID da sala
     * @param inicio Data/hora de início
     * @param fim Data/hora de fim
     * @return Lista de aluguéis
     */
    public List<AluguelSala> buscarPorSalaEPeriodo(String salaId, LocalDateTime inicio, LocalDateTime fim) {
        return dados.values().stream()
                .filter(aluguel -> aluguel.getSala().getId().equals(salaId))
                .filter(aluguel -> {
                    LocalDateTime inicioAluguel = aluguel.getDataHoraInicio();
                    LocalDateTime fimAluguel = aluguel.getDataHoraFim();
                    return inicioAluguel.isBefore(fim) && fimAluguel.isAfter(inicio);
                })
                .collect(Collectors.toList());
    }
}
