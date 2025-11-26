package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.NegocioException;
import com.escolamusica.exception.ValidacaoException;
import com.escolamusica.model.*;
import com.escolamusica.repository.AulaRepositorio;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Serviço de gerenciamento de aulas.
 * Controla agendamento, disponibilidade e execução de aulas.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class AulaService {
    
    private AulaRepositorio aulaRepositorio;
    private ProfessorService professorService;
    private SalaService salaService;
    private CursoService cursoService;
    
    public AulaService(AulaRepositorio aulaRepositorio, ProfessorService professorService,
                       SalaService salaService, CursoService cursoService) {
        this.aulaRepositorio = aulaRepositorio;
        this.professorService = professorService;
        this.salaService = salaService;
        this.cursoService = cursoService;
    }
    
    /**
     * Agenda uma nova aula.
     * 
     * @param cursoId ID do curso
     * @param professorIdOuRegistro ID ou Registro do professor
     * @param salaId ID da sala
     * @param dataHora Data e hora da aula
     * @param tipoAula Tipo de aula
     * @param duracao Duração em minutos
     * @return Aula agendada
     * @throws EntidadeNaoEncontradaException Se entidade não encontrada
     * @throws NegocioException Se houver conflito de horário
     */
    public Aula agendarAula(String cursoId, String professorIdOuRegistro, String salaId,
                           LocalDateTime dataHora, TipoAula tipoAula, int duracao) 
            throws EntidadeNaoEncontradaException, NegocioException {
        
        Curso curso = cursoService.buscarPorId(cursoId);
        Professor professor = professorService.buscarPorIdOuRegistro(professorIdOuRegistro);
        Sala sala = salaService.buscarPorId(salaId);
        
        // Verifica disponibilidade do professor
        if (!professor.isDisponivel()) {
            throw new NegocioException("Professor não está disponível");
        }
        
        // Verifica disponibilidade da sala
        if (!sala.isDisponivel()) {
            throw new NegocioException("Sala não está disponível");
        }
        
        // Verifica conflitos de horário do professor (usa o ID real)
        if (verificarConflitoHorarioProfessor(professor.getId(), dataHora, duracao)) {
            throw new NegocioException("Professor já possui aula agendada neste horário");
        }
        
        // Verifica conflitos de horário da sala
        if (verificarConflitoHorarioSala(salaId, dataHora, duracao)) {
            throw new NegocioException("Sala já está reservada neste horário");
        }
        
        // Cria a aula
        Aula aula = new Aula();
        aula.setId(UUID.randomUUID().toString());
        aula.setCurso(curso);
        aula.setProfessor(professor);
        aula.setSala(sala);
        aula.setDataHora(dataHora);
        aula.setTipoAula(tipoAula);
        aula.setDuracao(duracao);
        
        return aulaRepositorio.salvar(aula);
    }
    
    /**
     * Busca aula por ID.
     * 
     * @param id ID da aula
     * @return Aula encontrada
     * @throws EntidadeNaoEncontradaException Se não encontrada
     */
    public Aula buscarPorId(String id) throws EntidadeNaoEncontradaException {
        return aulaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aula", id));
    }
    
    /**
     * Lista aulas de um professor.
     * 
     * @param professorId ID do professor
     * @return Lista de aulas
     */
    public List<Aula> listarPorProfessor(String professorId) {
        return aulaRepositorio.buscarPorProfessor(professorId);
    }
    
    /**
     * Lista aulas de uma sala.
     * 
     * @param salaId ID da sala
     * @return Lista de aulas
     */
    public List<Aula> listarPorSala(String salaId) {
        return aulaRepositorio.buscarPorSala(salaId);
    }
    
    /**
     * Lista aulas em um período.
     * 
     * @param dataInicio Data inicial
     * @param dataFim Data final
     * @return Lista de aulas
     */
    public List<Aula> listarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return aulaRepositorio.buscarPorPeriodo(dataInicio, dataFim);
    }
    
    /**
     * Lista aulas pendentes (não realizadas).
     * 
     * @return Lista de aulas pendentes
     */
    public List<Aula> listarPendentes() {
        return aulaRepositorio.listarPendentes();
    }
    
    /**
     * Marca uma aula como realizada.
     * 
     * @param id ID da aula
     * @return Aula atualizada
     * @throws EntidadeNaoEncontradaException Se não encontrada
     */
    public Aula marcarComoRealizada(String id) throws EntidadeNaoEncontradaException {
        Aula aula = buscarPorId(id);
        aula.marcarComoRealizada();
        return aulaRepositorio.salvar(aula);
    }
    
    /**
     * Adiciona aluno a uma aula.
     * 
     * @param aulaId ID da aula
     * @param aluno Aluno a ser adicionado
     * @return Aula atualizada
     * @throws EntidadeNaoEncontradaException Se não encontrada
     * @throws NegocioException Se não houver vagas
     */
    public Aula adicionarAluno(String aulaId, Aluno aluno) 
            throws EntidadeNaoEncontradaException, NegocioException {
        Aula aula = buscarPorId(aulaId);
        
        if (!aula.possuiVagasDisponiveis()) {
            throw new NegocioException("Aula não possui vagas disponíveis");
        }
        
        aula.adicionarAluno(aluno);
        return aulaRepositorio.salvar(aula);
    }
    
    /**
     * Cancela uma aula.
     * 
     * @param id ID da aula
     * @return true se cancelada
     * @throws EntidadeNaoEncontradaException Se não encontrada
     */
    public boolean cancelar(String id) throws EntidadeNaoEncontradaException {
        buscarPorId(id);
        return aulaRepositorio.remover(id);
    }
    
    /**
     * Verifica conflito de horário do professor.
     * 
     * @param professorId ID do professor
     * @param dataHora Data e hora
     * @param duracao Duração em minutos
     * @return true se houver conflito
     */
    private boolean verificarConflitoHorarioProfessor(String professorId, 
                                                      LocalDateTime dataHora, int duracao) {
        List<Aula> aulas = aulaRepositorio.buscarPorProfessor(professorId);
        LocalDateTime fimAulaNova = dataHora.plusMinutes(duracao);
        
        for (Aula aula : aulas) {
            if (aula.isRealizada()) continue;
            
            LocalDateTime inicioAula = aula.getDataHora();
            LocalDateTime fimAula = inicioAula.plusMinutes(aula.getDuracao());
            
            // Verifica sobreposição de horários
            if (dataHora.isBefore(fimAula) && fimAulaNova.isAfter(inicioAula)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Verifica conflito de horário da sala.
     * 
     * @param salaId ID da sala
     * @param dataHora Data e hora
     * @param duracao Duração em minutos
     * @return true se houver conflito
     */
    private boolean verificarConflitoHorarioSala(String salaId, 
                                                 LocalDateTime dataHora, int duracao) {
        List<Aula> aulas = aulaRepositorio.buscarPorSala(salaId);
        LocalDateTime fimAulaNova = dataHora.plusMinutes(duracao);
        
        for (Aula aula : aulas) {
            if (aula.isRealizada()) continue;
            
            LocalDateTime inicioAula = aula.getDataHora();
            LocalDateTime fimAula = inicioAula.plusMinutes(aula.getDuracao());
            
            // Verifica sobreposição de horários
            if (dataHora.isBefore(fimAula) && fimAulaNova.isAfter(inicioAula)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Lista todas as aulas.
     * 
     * @return Lista de aulas
     */
    public List<Aula> listarTodas() {
        return aulaRepositorio.listarTodos();
    }
}
