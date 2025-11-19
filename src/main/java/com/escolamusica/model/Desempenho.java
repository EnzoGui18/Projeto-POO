package com.escolamusica.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que representa o desempenho de um aluno em um curso/instrumento.
 * Registra notas, progresso e observações sobre o desenvolvimento do aluno.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class Desempenho {
    private String id;
    private Aluno aluno;
    private Curso curso;
    private LocalDate dataAvaliacao;
    private double nota; // 0.0 a 10.0
    private String nivel; // Iniciante, Intermediário, Avançado
    private String pontosFortes;
    private String pontosAMelhorar;
    private String observacoes;
    private Professor avaliador;
    
    /**
     * Construtor padrão da classe Desempenho.
     */
    public Desempenho() {
        this.dataAvaliacao = LocalDate.now();
    }
    
    /**
     * Construtor com parâmetros da classe Desempenho.
     * 
     * @param id Identificador único do registro
     * @param aluno Aluno avaliado
     * @param curso Curso avaliado
     * @param nota Nota obtida
     * @param nivel Nível atual do aluno
     * @param avaliador Professor avaliador
     */
    public Desempenho(String id, Aluno aluno, Curso curso, double nota, 
                      String nivel, Professor avaliador) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
        this.dataAvaliacao = LocalDate.now();
        this.nota = Math.max(0.0, Math.min(10.0, nota)); // Garante nota entre 0 e 10
        this.nivel = nivel;
        this.avaliador = avaliador;
    }
    
    // Getters e Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }
    
    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
    
    public double getNota() {
        return nota;
    }
    
    public void setNota(double nota) {
        this.nota = Math.max(0.0, Math.min(10.0, nota));
    }
    
    public String getNivel() {
        return nivel;
    }
    
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    public String getPontosFortes() {
        return pontosFortes;
    }
    
    public void setPontosFortes(String pontosFortes) {
        this.pontosFortes = pontosFortes;
    }
    
    public String getPontosAMelhorar() {
        return pontosAMelhorar;
    }
    
    public void setPontosAMelhorar(String pontosAMelhorar) {
        this.pontosAMelhorar = pontosAMelhorar;
    }
    
    public String getObservacoes() {
        return observacoes;
    }
    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    public Professor getAvaliador() {
        return avaliador;
    }
    
    public void setAvaliador(Professor avaliador) {
        this.avaliador = avaliador;
    }
    
    /**
     * Determina o conceito baseado na nota.
     * 
     * @return Conceito (A, B, C, D, F)
     */
    public String getConceito() {
        if (nota >= 9.0) return "A";
        if (nota >= 7.0) return "B";
        if (nota >= 5.0) return "C";
        if (nota >= 3.0) return "D";
        return "F";
    }
    
    /**
     * Verifica se o aluno foi aprovado (nota >= 6.0).
     * 
     * @return true se aprovado, false caso contrário
     */
    public boolean isAprovado() {
        return nota >= 6.0;
    }
    
    /**
     * Gera um relatório textual do desempenho.
     * 
     * @return Relatório formatado
     */
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO DE DESEMPENHO ===\n");
        relatorio.append("Aluno: ").append(aluno != null ? aluno.getNome() : "N/A").append("\n");
        relatorio.append("Curso: ").append(curso != null ? curso.getNome() : "N/A").append("\n");
        relatorio.append("Data da Avaliação: ").append(dataAvaliacao).append("\n");
        relatorio.append("Nota: ").append(String.format("%.1f", nota)).append(" (").append(getConceito()).append(")\n");
        relatorio.append("Nível: ").append(nivel != null ? nivel : "N/A").append("\n");
        relatorio.append("Status: ").append(isAprovado() ? "APROVADO" : "REPROVADO").append("\n");
        
        if (pontosFortes != null && !pontosFortes.isEmpty()) {
            relatorio.append("\nPontos Fortes:\n").append(pontosFortes).append("\n");
        }
        
        if (pontosAMelhorar != null && !pontosAMelhorar.isEmpty()) {
            relatorio.append("\nPontos a Melhorar:\n").append(pontosAMelhorar).append("\n");
        }
        
        if (observacoes != null && !observacoes.isEmpty()) {
            relatorio.append("\nObservações:\n").append(observacoes).append("\n");
        }
        
        if (avaliador != null) {
            relatorio.append("\nAvaliador: ").append(avaliador.getNome()).append("\n");
        }
        
        relatorio.append("================================");
        return relatorio.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Desempenho that = (Desempenho) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Desempenho{" +
                "id='" + id + '\'' +
                ", aluno=" + (aluno != null ? aluno.getNome() : "N/A") +
                ", curso=" + (curso != null ? curso.getNome() : "N/A") +
                ", dataAvaliacao=" + dataAvaliacao +
                ", nota=" + String.format("%.1f", nota) +
                ", conceito=" + getConceito() +
                ", nivel='" + nivel + '\'' +
                ", aprovado=" + isAprovado() +
                '}';
    }
}
