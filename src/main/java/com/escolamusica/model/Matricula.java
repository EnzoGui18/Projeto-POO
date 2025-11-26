package com.escolamusica.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que representa uma matrícula de aluno em um curso.
 * Controla o vínculo entre aluno e curso, incluindo status e datas.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class Matricula {
    private String id;
    private Aluno aluno;
    private Curso curso;
    private TipoAula tipoAula;
    private LocalDate dataMatricula;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private StatusMatricula status;
    private double valorMensal;
    private String observacoes;
    
    /**
     * Construtor padrão da classe Matricula.
     */
    public Matricula() {
        this.dataMatricula = LocalDate.now();
        this.status = StatusMatricula.ATIVA;
    }
    
    /**
     * Construtor com parâmetros da classe Matricula.
     * 
     * @param id Identificador único da matrícula
     * @param aluno Aluno matriculado
     * @param curso Curso da matrícula
     * @param tipoAula Tipo de aula escolhido
     * @param dataInicio Data de início do curso
     */
    public Matricula(String id, Aluno aluno, Curso curso, TipoAula tipoAula, LocalDate dataInicio) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
        this.tipoAula = tipoAula;
        this.dataMatricula = LocalDate.now();
        this.dataInicio = dataInicio;
        this.dataTermino = dataInicio.plusMonths(curso.getDuracaoMeses());
        this.status = StatusMatricula.ATIVA;
        this.valorMensal = calcularValorMensal();
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
        if (curso != null && dataInicio != null) {
            this.dataTermino = dataInicio.plusMonths(curso.getDuracaoMeses());
        }
        this.valorMensal = calcularValorMensal();
    }
    
    public TipoAula getTipoAula() {
        return tipoAula;
    }
    
    public void setTipoAula(TipoAula tipoAula) {
        this.tipoAula = tipoAula;
    }
    
    public LocalDate getDataMatricula() {
        return dataMatricula;
    }
    
    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }
    
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        if (curso != null) {
            this.dataTermino = dataInicio.plusMonths(curso.getDuracaoMeses());
        }
    }
    
    public LocalDate getDataTermino() {
        return dataTermino;
    }
    
    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }
    
    public StatusMatricula getStatus() {
        return status;
    }
    
    public void setStatus(StatusMatricula status) {
        this.status = status;
    }
    
    public double getValorMensal() {
        return valorMensal;
    }
    
    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }
    
    public String getObservacoes() {
        return observacoes;
    }
    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    /**
     * Calcula o valor mensal da matrícula.
     * Aplica descontos para alunos VIP e ajustes por tipo de aula.
     * 
     * @return Valor mensal calculado
     */
    public double calcularValorMensal() {
        if (curso == null) {
            return 0.0;
        }
        
        double valor = curso.getValorMensal();
        
        // Ajusta valor baseado no tipo de aula
        if (tipoAula == TipoAula.INDIVIDUAL) {
            valor *= 1.5; // Aula individual custa 50% a mais
        } else if (tipoAula == TipoAula.TURMA) {
            valor *= 0.7; // Aula em turma tem 30% de desconto
        }
        
        // Aplica desconto VIP
        if (aluno != null && aluno.isVIP()) {
            double desconto = aluno.calcularDesconto();
            valor = valor * (1 - desconto);
        }
        
        return valor;
    }
    
    /**
     * Calcula o valor total da matrícula (todos os meses).
     * 
     * @return Valor total
     */
    public double calcularValorTotal() {
        if (curso == null) {
            return 0.0;
        }
        return valorMensal * curso.getDuracaoMeses();
    }
    
    /**
     * Suspende a matrícula.
     */
    public void suspender() {
        this.status = StatusMatricula.SUSPENSA;
    }
    
    /**
     * Cancela a matrícula.
     */
    public void cancelar() {
        this.status = StatusMatricula.CANCELADA;
    }
    
    /**
     * Reativa a matrícula.
     */
    public void reativar() {
        if (status == StatusMatricula.SUSPENSA) {
            this.status = StatusMatricula.ATIVA;
        }
    }
    
    /**
     * Conclui a matrícula.
     */
    public void concluir() {
        this.status = StatusMatricula.CONCLUIDA;
    }
    
    /**
     * Verifica se a matrícula está ativa.
     * 
     * @return true se ativa, false caso contrário
     */
    public boolean isAtiva() {
        return status == StatusMatricula.ATIVA;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(id, matricula.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Matricula{" +
                "id='" + id + '\'' +
                ", aluno=" + (aluno != null ? aluno.getNome() : "N/A") +
                ", curso=" + (curso != null ? curso.getNome() : "N/A") +
                ", tipoAula=" + tipoAula +
                ", status=" + status +
                ", valorMensal=R$ " + String.format("%.2f", valorMensal) +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                '}';
    }
}
