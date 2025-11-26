package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.ValidacaoException;
import com.escolamusica.model.Aluno;
import com.escolamusica.model.Curso;
import com.escolamusica.model.Desempenho;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Serviço de gerenciamento de desempenho dos alunos.
 * Registra notas, progressos e gera relatórios.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class DesempenhoService {
    
    private AlunoService alunoService;
    private CursoService cursoService;
    
    public DesempenhoService(AlunoService alunoService, CursoService cursoService) {
        this.alunoService = alunoService;
        this.cursoService = cursoService;
    }
    
    /**
     * Registra um desempenho para um aluno.
     * 
     * @param alunoId ID do aluno
     * @param cursoId ID do curso
     * @param nota Nota (0-10)
     * @param nivel Nível atual
     * @param observacoes Observações gerais
     * @param pontosFortes Pontos fortes do aluno
     * @param pontosAMelhorar Pontos a melhorar
     * @return Desempenho registrado
     * @throws EntidadeNaoEncontradaException Se entidade não encontrada
     * @throws ValidacaoException Se dados inválidos
     */
    public Desempenho registrarDesempenho(String alunoId, String cursoId, 
                                         double nota, String nivel, 
                                         String observacoes,
                                         String pontosFortes, 
                                         String pontosAMelhorar) 
            throws EntidadeNaoEncontradaException, ValidacaoException {
        
        Aluno aluno = alunoService.buscarPorId(alunoId);
        Curso curso = cursoService.buscarPorId(cursoId);
        
        // Valida nota
        if (nota < 0 || nota > 10) {
            throw new ValidacaoException("Nota deve estar entre 0 e 10");
        }
        
        // Cria desempenho
        Desempenho desempenho = new Desempenho();
        desempenho.setId(UUID.randomUUID().toString());
        desempenho.setAluno(aluno);
        desempenho.setCurso(curso);
        desempenho.setNota(nota);
        desempenho.setDataAvaliacao(LocalDate.now());
        desempenho.setNivel(nivel);
        desempenho.setObservacoes(observacoes);
        desempenho.setPontosFortes(pontosFortes);
        desempenho.setPontosAMelhorar(pontosAMelhorar);
        
        // Adiciona ao aluno
        aluno.adicionarDesempenho(desempenho);
        
        return desempenho;
    }
    
    /**
     * Lista desempenhos de um aluno.
     * 
     * @param alunoId ID do aluno
     * @return Lista de desempenhos
     * @throws EntidadeNaoEncontradaException Se aluno não encontrado
     */
    public List<Desempenho> listarPorAluno(String alunoId) 
            throws EntidadeNaoEncontradaException {
        Aluno aluno = alunoService.buscarPorId(alunoId);
        return aluno.getDesempenhos();
    }
    
    /**
     * Lista desempenhos de um aluno em um curso específico.
     * 
     * @param alunoId ID do aluno
     * @param cursoId ID do curso
     * @return Lista de desempenhos
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public List<Desempenho> listarPorAlunoECurso(String alunoId, String cursoId) 
            throws EntidadeNaoEncontradaException {
        Aluno aluno = alunoService.buscarPorId(alunoId);
        
        return aluno.getDesempenhos().stream()
                .filter(d -> d.getCurso().getId().equals(cursoId))
                .toList();
    }
    
    /**
     * Gera relatório de progresso de um aluno.
     * 
     * @param alunoId ID do aluno
     * @return Relatório formatado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public String gerarRelatorioProgresso(String alunoId) 
            throws EntidadeNaoEncontradaException {
        Aluno aluno = alunoService.buscarPorId(alunoId);
        List<Desempenho> desempenhos = aluno.getDesempenhos();
        
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("╔════════════════════════════════════════════════════════════╗\n");
        relatorio.append("║         RELATÓRIO DE PROGRESSO DO ALUNO                   ║\n");
        relatorio.append("╚════════════════════════════════════════════════════════════╝\n\n");
        
        relatorio.append("ALUNO: ").append(aluno.getNome()).append("\n");
        relatorio.append("MATRÍCULA: ").append(aluno.getMatricula()).append("\n");
        relatorio.append("TIPO: ").append(aluno.isVIP() ? "VIP" : "Regular").append("\n");
        relatorio.append("─".repeat(60)).append("\n\n");
        
        if (desempenhos.isEmpty()) {
            relatorio.append("Nenhuma avaliação registrada ainda.\n");
            return relatorio.toString();
        }
        
        // Agrupa por curso
        var porCurso = desempenhos.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        d -> d.getCurso().getNome()));
        
        for (var entry : porCurso.entrySet()) {
            String nomeCurso = entry.getKey();
            List<Desempenho> avaliacoes = entry.getValue();
            
            relatorio.append("📚 CURSO: ").append(nomeCurso).append("\n");
            relatorio.append("   Total de avaliações: ").append(avaliacoes.size()).append("\n");
            
            // Calcula média
            double media = avaliacoes.stream()
                    .mapToDouble(Desempenho::getNota)
                    .average()
                    .orElse(0.0);
            
            relatorio.append("   Média geral: ").append(String.format("%.2f", media));
            relatorio.append(" (").append(obterConceitoPorNota(media)).append(")\n");
            
            // Última avaliação
            Desempenho ultima = avaliacoes.get(avaliacoes.size() - 1);
            relatorio.append("   Última avaliação: ").append(ultima.getDataAvaliacao()).append("\n");
            relatorio.append("   Nota: ").append(ultima.getNota());
            relatorio.append(" (").append(ultima.getConceito()).append(")\n");
            relatorio.append("   Nível atual: ").append(ultima.getNivel()).append("\n");
            
            if (ultima.getPontosFortes() != null && !ultima.getPontosFortes().isEmpty()) {
                relatorio.append("   ✓ Pontos fortes: ").append(ultima.getPontosFortes()).append("\n");
            }
            
            if (ultima.getPontosAMelhorar() != null && !ultima.getPontosAMelhorar().isEmpty()) {
                relatorio.append("   ⚠ A melhorar: ").append(ultima.getPontosAMelhorar()).append("\n");
            }
            
            relatorio.append("\n");
        }
        
        // Desempenho geral
        double mediaGeral = desempenhos.stream()
                .mapToDouble(Desempenho::getNota)
                .average()
                .orElse(0.0);
        
        relatorio.append("─".repeat(60)).append("\n");
        relatorio.append("DESEMPENHO GERAL: ").append(String.format("%.2f", mediaGeral));
        relatorio.append(" (").append(obterConceitoPorNota(mediaGeral)).append(")\n");
        
        long aprovados = desempenhos.stream()
                .filter(Desempenho::isAprovado)
                .count();
        
        relatorio.append("Taxa de aprovação: ")
                .append(String.format("%.1f%%", (aprovados * 100.0) / desempenhos.size()))
                .append("\n");
        
        return relatorio.toString();
    }
    
    /**
     * Gera relatório detalhado de uma avaliação.
     * 
     * @param desempenho Desempenho a detalhar
     * @return Relatório formatado
     */
    public String gerarRelatorioDetalhado(Desempenho desempenho) {
        return desempenho.gerarRelatorio();
    }
    
    /**
     * Calcula média de notas de um aluno.
     * 
     * @param alunoId ID do aluno
     * @return Média das notas
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public double calcularMedia(String alunoId) throws EntidadeNaoEncontradaException {
        Aluno aluno = alunoService.buscarPorId(alunoId);
        
        return aluno.getDesempenhos().stream()
                .mapToDouble(Desempenho::getNota)
                .average()
                .orElse(0.0);
    }
    
    /**
     * Obtém conceito por nota.
     * 
     * @param nota Nota
     * @return Conceito
     */
    private String obterConceitoPorNota(double nota) {
        if (nota >= 9.0) return "A - Excelente";
        if (nota >= 7.0) return "B - Bom";
        if (nota >= 6.0) return "C - Regular";
        if (nota >= 4.0) return "D - Insuficiente";
        return "F - Reprovado";
    }
}
