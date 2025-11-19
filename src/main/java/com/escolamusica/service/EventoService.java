package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.NegocioException;
import com.escolamusica.model.Aluno;
import com.escolamusica.model.Evento;
import com.escolamusica.repository.EventoRepositorio;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Serviço de gerenciamento de eventos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class EventoService {
    
    private EventoRepositorio eventoRepositorio;
    private AlunoService alunoService;
    
    public EventoService(EventoRepositorio eventoRepositorio, AlunoService alunoService) {
        this.eventoRepositorio = eventoRepositorio;
        this.alunoService = alunoService;
    }
    
    /**
     * Cria um novo evento.
     * 
     * @param evento Evento a ser criado
     * @return Evento criado
     */
    public Evento criar(Evento evento) {
        if (evento.getId() == null || evento.getId().isEmpty()) {
            evento.setId(UUID.randomUUID().toString());
        }
        return eventoRepositorio.salvar(evento);
    }
    
    /**
     * Busca evento por ID.
     * 
     * @param id ID do evento
     * @return Evento encontrado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public Evento buscarPorId(String id) throws EntidadeNaoEncontradaException {
        return eventoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Evento", id));
    }
    
    /**
     * Inscreve um aluno em um evento.
     * 
     * @param eventoId ID do evento
     * @param alunoId ID do aluno
     * @return Evento atualizado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     * @throws NegocioException Se não for possível inscrever
     */
    public Evento inscreverAluno(String eventoId, String alunoId) 
            throws EntidadeNaoEncontradaException, NegocioException {
        Evento evento = buscarPorId(eventoId);
        Aluno aluno = alunoService.buscarPorId(alunoId);
        
        boolean inscrito = evento.inscreverAluno(aluno);
        
        if (!inscrito) {
            throw new NegocioException("Não foi possível inscrever o aluno. " +
                    "Verifique se há vagas disponíveis ou se o aluno já está inscrito.");
        }
        
        return eventoRepositorio.salvar(evento);
    }
    
    /**
     * Cancela inscrição de um aluno.
     * 
     * @param eventoId ID do evento
     * @param alunoId ID do aluno
     * @return Evento atualizado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public Evento cancelarInscricao(String eventoId, String alunoId) 
            throws EntidadeNaoEncontradaException {
        Evento evento = buscarPorId(eventoId);
        Aluno aluno = alunoService.buscarPorId(alunoId);
        
        evento.cancelarInscricao(aluno);
        return eventoRepositorio.salvar(evento);
    }
    
    /**
     * Lista eventos futuros.
     * 
     * @return Lista de eventos futuros
     */
    public List<Evento> listarFuturos() {
        return eventoRepositorio.listarFuturos();
    }
    
    /**
     * Lista eventos com vagas disponíveis.
     * 
     * @return Lista de eventos com vagas
     */
    public List<Evento> listarComVagas() {
        return eventoRepositorio.listarComVagas();
    }
    
    /**
     * Busca eventos por período.
     * 
     * @param dataInicio Data inicial
     * @param dataFim Data final
     * @return Lista de eventos
     */
    public List<Evento> buscarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return eventoRepositorio.buscarPorPeriodo(dataInicio, dataFim);
    }
}
