package com.escolamusica.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um aluno da escola de música.
 * Herda de Pessoa e adiciona atributos específicos de alunos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class Aluno extends Pessoa {
    private String matricula;
    private LocalDate dataMatricula;
    private List<Matricula> matriculas;
    private List<Desempenho> desempenhos;
    private boolean ativo;
    
    /**
     * Construtor padrão da classe Aluno.
     */
    public Aluno() {
        super();
        this.matriculas = new ArrayList<>();
        this.desempenhos = new ArrayList<>();
        this.ativo = true;
    }
    
    /**
     * Construtor com parâmetros da classe Aluno.
     * 
     * @param id Identificador único do aluno
     * @param nome Nome completo do aluno
     * @param cpf CPF do aluno
     * @param telefone Telefone de contato
     * @param email Email para contato
     * @param dataNascimento Data de nascimento
     * @param endereco Endereço residencial
     * @param matricula Número de matrícula
     */
    public Aluno(String id, String nome, String cpf, String telefone, 
                 String email, LocalDate dataNascimento, String endereco, String matricula) {
        super(id, nome, cpf, telefone, email, dataNascimento, endereco);
        this.matricula = matricula;
        this.dataMatricula = LocalDate.now();
        this.matriculas = new ArrayList<>();
        this.desempenhos = new ArrayList<>();
        this.ativo = true;
    }
    
    // Getters e Setters
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public LocalDate getDataMatricula() {
        return dataMatricula;
    }
    
    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }
    
    public List<Matricula> getMatriculas() {
        return matriculas;
    }
    
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
    
    public List<Desempenho> getDesempenhos() {
        return desempenhos;
    }
    
    public void setDesempenhos(List<Desempenho> desempenhos) {
        this.desempenhos = desempenhos;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    /**
     * Adiciona uma matrícula ao aluno.
     * 
     * @param matricula Matrícula a ser adicionada
     */
    public void adicionarMatricula(Matricula matricula) {
        if (matricula != null) {
            this.matriculas.add(matricula);
        }
    }
    
    /**
     * Adiciona um registro de desempenho ao aluno.
     * 
     * @param desempenho Desempenho a ser adicionado
     */
    public void adicionarDesempenho(Desempenho desempenho) {
        if (desempenho != null) {
            this.desempenhos.add(desempenho);
        }
    }
    
    /**
     * Verifica se o aluno é VIP.
     * 
     * @return true se for VIP, false caso contrário
     */
    public boolean isVIP() {
        return false;
    }
    
    /**
     * Calcula o desconto aplicável ao aluno.
     * 
     * @return Percentual de desconto (0.0 a 1.0)
     */
    public double calcularDesconto() {
        return 0.0;
    }
    
    @Override
    public String toString() {
        return "Aluno{" +
                "matricula='" + matricula + '\'' +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", ativo=" + ativo +
                ", VIP=" + isVIP() +
                '}';
    }
}
