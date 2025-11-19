package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.NegocioException;
import com.escolamusica.model.Aluno;
import com.escolamusica.model.AluguelSala;
import com.escolamusica.model.Sala;
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
    
    private AulaRepositorio aulaRepositorio;
    private SalaService salaService;
    private AlunoService alunoService;
    
    public AluguelSalaService(AulaRepositorio aulaRepositorio, 
                             SalaService salaService, 
                             AlunoService alunoService) {
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
        
        // Verifica se a sala já está ocupada no horário solicitado
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
        
        return aluguel;
    }
    
    /**
     * Verifica conflito de horário para aluguel de sala.
     * 
     * @param salaId ID da sala
     * @param inicio Data/hora início
     * @param fim Data/hora fim
     * @return true se houver conflito
     */
    private boolean verificarConflito(String salaId, LocalDateTime inicio, LocalDateTime fim) {
        // Busca aulas agendadas para a sala
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
        
        return false;
    }
    
    /**
     * Lista horários disponíveis de uma sala em um dia.
     * 
     * @param salaId ID da sala
     * @param data Data para verificar
     * @return Mensagem com horários ocupados
     */
    public String listarHorariosDisponiveis(String salaId, LocalDateTime data) {
        LocalDateTime inicioDia = data.withHour(8).withMinute(0);
        LocalDateTime fimDia = data.withHour(22).withMinute(0);
        
        List<com.escolamusica.model.Aula> aulas = aulaRepositorio.buscarPorPeriodo(inicioDia, fimDia)
                .stream()
                .filter(a -> a.getSala().getId().equals(salaId))
                .toList();
        
        if (aulas.isEmpty()) {
            return "Sala disponível durante todo o dia (08:00 - 22:00)";
        }
        
        StringBuilder sb = new StringBuilder("Horários ocupados:\n");
        for (com.escolamusica.model.Aula aula : aulas) {
            LocalDateTime inicio = aula.getDataHora();
            LocalDateTime fim = inicio.plusMinutes(aula.getDuracao());
            sb.append(String.format("- %02d:%02d às %02d:%02d\n", 
                    inicio.getHour(), inicio.getMinute(),
                    fim.getHour(), fim.getMinute()));
        }
        
        return sb.toString();
    }
}
