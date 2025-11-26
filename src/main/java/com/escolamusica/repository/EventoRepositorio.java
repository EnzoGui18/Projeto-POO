package com.escolamusica.repository;

import com.escolamusica.model.Evento;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciar eventos, recitais e apresentações.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class EventoRepositorio extends RepositorioGenerico<Evento, String> {
    
    @Override
    protected String extrairId(Evento entidade) {
        return entidade.getId();
    }
    
    /**
     * Busca eventos por período.
     * 
     * @param dataInicio Data inicial
     * @param dataFim Data final
     * @return Lista de eventos no período
     */
    public List<Evento> buscarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return dados.values().stream()
                .filter(evento -> !evento.getDataHora().isBefore(dataInicio) && 
                                 !evento.getDataHora().isAfter(dataFim))
                .collect(Collectors.toList());
    }
    
    /**
     * Lista eventos futuros.
     * 
     * @return Lista de eventos futuros
     */
    public List<Evento> listarFuturos() {
        LocalDateTime agora = LocalDateTime.now();
        return dados.values().stream()
                .filter(evento -> evento.getDataHora().isAfter(agora))
                .sorted((e1, e2) -> e1.getDataHora().compareTo(e2.getDataHora()))
                .collect(Collectors.toList());
    }
    
    /**
     * Lista eventos passados.
     * 
     * @return Lista de eventos passados
     */
    public List<Evento> listarPassados() {
        LocalDateTime agora = LocalDateTime.now();
        return dados.values().stream()
                .filter(evento -> evento.getDataHora().isBefore(agora))
                .sorted((e1, e2) -> e2.getDataHora().compareTo(e1.getDataHora()))
                .collect(Collectors.toList());
    }
    
    /**
     * Lista eventos com vagas disponíveis.
     * 
     * @return Lista de eventos com vagas
     */
    public List<Evento> listarComVagas() {
        return dados.values().stream()
                .filter(evento -> evento.getParticipantes().size() < evento.getCapacidadeTotal())
                .collect(Collectors.toList());
    }
}
