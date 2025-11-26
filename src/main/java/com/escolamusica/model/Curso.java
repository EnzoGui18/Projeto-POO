package com.escolamusica.model;

import java.util.Objects;

/**
 * Classe que representa um curso oferecido pela escola de música.
 * Um curso é relacionado a um instrumento específico e pode ter diferentes tipos de aula.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class Curso {
    private String id;
    private String nome;
    private TipoInstrumento instrumento;
    private String descricao;
    private int cargaHoraria; // em horas
    private double valorMensal;
    private int duracaoMeses;
    private String nivel; // Iniciante, Intermediário, Avançado
    private boolean ativo;
    
    /**
     * Construtor padrão da classe Curso.
     */
    public Curso() {
        this.ativo = true;
    }
    
    /**
     * Construtor com parâmetros da classe Curso.
     * 
     * @param id Identificador único do curso
     * @param nome Nome do curso
     * @param instrumento Instrumento do curso
     * @param descricao Descrição do curso
     * @param cargaHoraria Carga horária total
     * @param valorMensal Valor mensal do curso
     * @param duracaoMeses Duração em meses
     * @param nivel Nível do curso
     */
    public Curso(String id, String nome, TipoInstrumento instrumento, String descricao,
                 int cargaHoraria, double valorMensal, int duracaoMeses, String nivel) {
        this.id = id;
        this.nome = nome;
        this.instrumento = instrumento;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.valorMensal = valorMensal;
        this.duracaoMeses = duracaoMeses;
        this.nivel = nivel;
        this.ativo = true;
    }
    
    // Getters e Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public TipoInstrumento getInstrumento() {
        return instrumento;
    }
    
    public void setInstrumento(TipoInstrumento instrumento) {
        this.instrumento = instrumento;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getCargaHoraria() {
        return cargaHoraria;
    }
    
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    
    public double getValorMensal() {
        return valorMensal;
    }
    
    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }
    
    public int getDuracaoMeses() {
        return duracaoMeses;
    }
    
    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }
    
    public String getNivel() {
        return nivel;
    }
    
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    /**
     * Calcula o valor total do curso.
     * 
     * @return Valor total (valorMensal * duracaoMeses)
     */
    public double calcularValorTotal() {
        return valorMensal * duracaoMeses;
    }
    
    /**
     * Aplica um desconto ao valor mensal do curso.
     * 
     * @param percentual Percentual de desconto (0.0 a 1.0)
     * @return Novo valor mensal com desconto
     */
    public double aplicarDesconto(double percentual) {
        if (percentual < 0 || percentual > 1) {
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 1");
        }
        return valorMensal * (1 - percentual);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Curso{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", instrumento=" + instrumento +
                ", nivel='" + nivel + '\'' +
                ", cargaHoraria=" + cargaHoraria + "h" +
                ", valorMensal=R$ " + String.format("%.2f", valorMensal) +
                ", duracaoMeses=" + duracaoMeses +
                ", valorTotal=R$ " + String.format("%.2f", calcularValorTotal()) +
                '}';
    }
}
