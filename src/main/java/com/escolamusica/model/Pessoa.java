package com.escolamusica.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe abstrata que representa uma pessoa no sistema da escola de música.
 * Serve como base para Aluno e Professor, implementando atributos e métodos comuns.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public abstract class Pessoa {
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private String endereco;
    
    /**
     * Construtor padrão da classe Pessoa.
     */
    public Pessoa() {
    }
    
    /**
     * Construtor com parâmetros da classe Pessoa.
     * 
     * @param id Identificador único da pessoa
     * @param nome Nome completo da pessoa
     * @param cpf CPF da pessoa
     * @param telefone Telefone de contato
     * @param email Email para contato
     * @param dataNascimento Data de nascimento
     * @param endereco Endereço residencial
     */
    public Pessoa(String id, String nome, String cpf, String telefone, 
                  String email, LocalDate dataNascimento, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
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
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    /**
     * Calcula a idade da pessoa baseado na data de nascimento.
     * 
     * @return Idade em anos
     */
    public int calcularIdade() {
        if (dataNascimento == null) {
            return 0;
        }
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
    
    @Override
    public String toString() {
        return "Pessoa{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
