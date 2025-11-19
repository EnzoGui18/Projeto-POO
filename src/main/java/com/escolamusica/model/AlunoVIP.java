package com.escolamusica.model;

import java.time.LocalDate;

/**
 * Classe que representa um aluno VIP da escola de música.
 * Alunos VIP possuem benefícios exclusivos como descontos,
 * acesso prioritário a estúdios de gravação e vagas em eventos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class AlunoVIP extends Aluno {
    private LocalDate dataInicioVIP;
    private double percentualDesconto;
    private boolean acessoPrioritarioEstudio;
    private boolean vagaPrioritariaEventos;
    private int pontosFidelidade;
    
    /**
     * Construtor padrão da classe AlunoVIP.
     */
    public AlunoVIP() {
        super();
        this.dataInicioVIP = LocalDate.now();
        this.percentualDesconto = 0.15; // 15% de desconto padrão
        this.acessoPrioritarioEstudio = true;
        this.vagaPrioritariaEventos = true;
        this.pontosFidelidade = 0;
    }
    
    /**
     * Construtor com parâmetros da classe AlunoVIP.
     * 
     * @param id Identificador único do aluno
     * @param nome Nome completo do aluno
     * @param cpf CPF do aluno
     * @param telefone Telefone de contato
     * @param email Email para contato
     * @param dataNascimento Data de nascimento
     * @param endereco Endereço residencial
     * @param matricula Número de matrícula
     * @param percentualDesconto Percentual de desconto VIP
     */
    public AlunoVIP(String id, String nome, String cpf, String telefone, 
                    String email, LocalDate dataNascimento, String endereco, 
                    String matricula, double percentualDesconto) {
        super(id, nome, cpf, telefone, email, dataNascimento, endereco, matricula);
        this.dataInicioVIP = LocalDate.now();
        this.percentualDesconto = percentualDesconto;
        this.acessoPrioritarioEstudio = true;
        this.vagaPrioritariaEventos = true;
        this.pontosFidelidade = 0;
    }
    
    // Getters e Setters
    
    public LocalDate getDataInicioVIP() {
        return dataInicioVIP;
    }
    
    public void setDataInicioVIP(LocalDate dataInicioVIP) {
        this.dataInicioVIP = dataInicioVIP;
    }
    
    public double getPercentualDesconto() {
        return percentualDesconto;
    }
    
    public void setPercentualDesconto(double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }
    
    public boolean isAcessoPrioritarioEstudio() {
        return acessoPrioritarioEstudio;
    }
    
    public void setAcessoPrioritarioEstudio(boolean acessoPrioritarioEstudio) {
        this.acessoPrioritarioEstudio = acessoPrioritarioEstudio;
    }
    
    public boolean isVagaPrioritariaEventos() {
        return vagaPrioritariaEventos;
    }
    
    public void setVagaPrioritariaEventos(boolean vagaPrioritariaEventos) {
        this.vagaPrioritariaEventos = vagaPrioritariaEventos;
    }
    
    public int getPontosFidelidade() {
        return pontosFidelidade;
    }
    
    public void setPontosFidelidade(int pontosFidelidade) {
        this.pontosFidelidade = pontosFidelidade;
    }
    
    /**
     * Adiciona pontos de fidelidade ao aluno VIP.
     * 
     * @param pontos Quantidade de pontos a adicionar
     */
    public void adicionarPontosFidelidade(int pontos) {
        this.pontosFidelidade += pontos;
    }
    
    /**
     * Calcula o tempo de VIP em meses.
     * 
     * @return Número de meses como VIP
     */
    public long calcularTempoVIPEmMeses() {
        if (dataInicioVIP == null) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.MONTHS.between(dataInicioVIP, LocalDate.now());
    }
    
    @Override
    public boolean isVIP() {
        return true;
    }
    
    @Override
    public double calcularDesconto() {
        // Desconto aumenta com o tempo de fidelidade
        long mesesVIP = calcularTempoVIPEmMeses();
        double descontoAdicional = Math.min(mesesVIP * 0.005, 0.10); // Máximo 10% adicional
        return Math.min(percentualDesconto + descontoAdicional, 0.30); // Máximo 30% total
    }
    
    @Override
    public String toString() {
        return "AlunoVIP{" +
                "matricula='" + getMatricula() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", percentualDesconto=" + (percentualDesconto * 100) + "%" +
                ", pontosFidelidade=" + pontosFidelidade +
                ", tempoVIP=" + calcularTempoVIPEmMeses() + " meses" +
                ", descontoTotal=" + (calcularDesconto() * 100) + "%" +
                '}';
    }
}
