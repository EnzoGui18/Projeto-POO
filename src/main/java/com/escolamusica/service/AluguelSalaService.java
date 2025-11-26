package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.NegocioException;
import com.escolamusica.model.Aluno;
import com.escolamusica.model.AluguelSala;
import com.escolamusica.model.Sala;
import com.escolamusica.repository.AluguelSalaRepositorio;
import com.escolamusica.repository.AulaRepositorio;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Serviço de gerenciamento de aluguel de salas.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class AluguelSalaService {
    
    private AluguelSalaRepositorio aluguelSalaRepositorio;
    private AulaRepositorio aulaRepositorio;
    private SalaService salaService;
    private AlunoService alunoService;
    
    public AluguelSalaService(AluguelSalaRepositorio aluguelSalaRepositorio,
                             AulaRepositorio aulaRepositorio, 
                             SalaService salaService, 
                             AlunoService alunoService) {
        this.aluguelSalaRepositorio = aluguelSalaRepositorio;
        this.aulaRepositorio = aulaRepositorio;
        this.salaService = salaService;
        this.alunoService = alunoService;
    }
    
    /**
     * Registra um aluguel de sala.
     * 
     * @param alunoId ID do aluno
     * @param salaId ID da sala
     * @param dataHoraInicio Data/hora de início
     * @param dataHoraFim Data/hora de fim
     * @param finalidade Finalidade do aluguel
     * @return Aluguel registrado
     * @throws EntidadeNaoEncontradaException Se entidade não encontrada
     * @throws NegocioException Se sala não disponível
     */
    public AluguelSala alugarSala(String alunoId, String salaId, 
                                 LocalDateTime dataHoraInicio, 
                                 LocalDateTime dataHoraFim,
                                 String finalidade) 
            throws EntidadeNaoEncontradaException, NegocioException {
        
        Aluno aluno = alunoService.buscarPorId(alunoId);
        Sala sala = salaService.buscarPorId(salaId);
        
        // Verifica disponibilidade
        if (!sala.isDisponivel()) {
            throw new NegocioException("Sala não está disponível para aluguel");
        }
        
        // Verifica se a sala já está ocupada no horário solicitado (aulas e aluguéis)
        if (verificarConflito(salaId, dataHoraInicio, dataHoraFim)) {
            throw new NegocioException("Sala já está reservada neste horário");
        }
        
        // Cria o aluguel
        AluguelSala aluguel = new AluguelSala();
        aluguel.setId(UUID.randomUUID().toString());
        aluguel.setAluno(aluno);
        aluguel.setSala(sala);
        aluguel.setDataHoraInicio(dataHoraInicio);
        aluguel.setDataHoraFim(dataHoraFim);
        aluguel.setFinalidade(finalidade);
        
        // Salva o aluguel no repositório
        return aluguelSalaRepositorio.salvar(aluguel);
    }
    
    /**
     * Verifica conflito de horário para aluguel de sala.
     * Considera tanto aulas agendadas quanto aluguéis existentes.
     * 
     * @param salaId ID da sala
     * @param inicio Data/hora início
     * @param fim Data/hora fim
     * @return true se houver conflito
     */
    private boolean verificarConflito(String salaId, LocalDateTime inicio, LocalDateTime fim) {
        // Verifica conflito com aulas agendadas
        List<com.escolamusica.model.Aula> aulas = aulaRepositorio.buscarPorSala(salaId);
        
        for (com.escolamusica.model.Aula aula : aulas) {
            if (aula.isRealizada()) continue;
            
            LocalDateTime inicioAula = aula.getDataHora();
            LocalDateTime fimAula = inicioAula.plusMinutes(aula.getDuracao());
            
            // Verifica sobreposição
            if (inicio.isBefore(fimAula) && fim.isAfter(inicioAula)) {
                return true;
            }
        }
        
        // Verifica conflito com aluguéis existentes
        List<AluguelSala> alugueis = aluguelSalaRepositorio.buscarPorSalaEPeriodo(salaId, inicio, fim);
        if (!alugueis.isEmpty()) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Lista horários disponíveis de uma sala em um dia.
     * Mostra tanto aulas agendadas quanto aluguéis de sala.
     * 
     * @param salaId ID da sala
     * @param data Data para verificar
     * @return Mensagem com horários ocupados
     */
    public String listarHorariosDisponiveis(String salaId, LocalDateTime data) {
        LocalDateTime inicioDia = data.withHour(8).withMinute(0);
        LocalDateTime fimDia = data.withHour(22).withMinute(0);
        
        // Busca aulas agendadas
        List<com.escolamusica.model.Aula> aulas = aulaRepositorio.buscarPorPeriodo(inicioDia, fimDia)
                .stream()
                .filter(a -> a.getSala().getId().equals(salaId))
                .filter(a -> !a.isRealizada())
                .toList();
        
        // Busca aluguéis de sala
        List<AluguelSala> alugueis = aluguelSalaRepositorio.buscarPorSalaEPeriodo(salaId, inicioDia, fimDia);
        
        if (aulas.isEmpty() && alugueis.isEmpty()) {
            return "Sala disponível durante todo o dia (08:00 - 22:00)";
        }
        
        StringBuilder sb = new StringBuilder("Horários ocupados:\n");
        
        // Lista aulas
        if (!aulas.isEmpty()) {
            sb.append("\nAULAS AGENDADAS:\n");
            for (com.escolamusica.model.Aula aula : aulas) {
                LocalDateTime inicio = aula.getDataHora();
                LocalDateTime fim = inicio.plusMinutes(aula.getDuracao());
                sb.append(String.format("- %02d:%02d às %02d:%02d (Curso: %s, Prof: %s)\n", 
                        inicio.getHour(), inicio.getMinute(),
                        fim.getHour(), fim.getMinute(),
                        aula.getCurso().getNome(),
                        aula.getProfessor().getNome()));
            }
        }
        
        // Lista aluguéis
        if (!alugueis.isEmpty()) {
            sb.append("\nALUGUÉIS DE SALA:\n");
            for (AluguelSala aluguel : alugueis) {
                LocalDateTime inicio = aluguel.getDataHoraInicio();
                LocalDateTime fim = aluguel.getDataHoraFim();
                sb.append(String.format("- %02d:%02d às %02d:%02d (Aluno: %s, Finalidade: %s)\n", 
                        inicio.getHour(), inicio.getMinute(),
                        fim.getHour(), fim.getMinute(),
                        aluguel.getAluno().getNome(),
                        aluguel.getFinalidade()));
            }
        }
        
        return sb.toString();
    }
}
