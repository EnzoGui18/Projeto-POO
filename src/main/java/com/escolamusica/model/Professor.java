package com.escolamusica.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um professor da escola de música.
 * Herda de Pessoa e adiciona atributos específicos de professores.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class Professor extends Pessoa {
    private String registro;
    private List<TipoInstrumento> especialidades;
    private double valorHoraAula;
    private LocalDate dataAdmissao;
    private boolean disponivel;
    private List<String> horariosDisponiveis;
    
    /**
     * Construtor padrão da classe Professor.
     */
    public Professor() {
        super();
        this.especialidades = new ArrayList<>();
        this.horariosDisponiveis = new ArrayList<>();
        this.disponivel = true;
    }
    
    /**
     * Construtor com parâmetros da classe Professor.
     * 
     * @param id Identificador único do professor
     * @param nome Nome completo do professor
     * @param cpf CPF do professor
     * @param telefone Telefone de contato
     * @param email Email para contato
     * @param dataNascimento Data de nascimento
     * @param endereco Endereço residencial
     * @param registro Registro profissional
     * @param valorHoraAula Valor por hora de aula
     */
    public Professor(String id, String nome, String cpf, String telefone, 
                     String email, LocalDate dataNascimento, String endereco,
                     String registro, double valorHoraAula) {
        super(id, nome, cpf, telefone, email, dataNascimento, endereco);
        this.registro = registro;
        this.valorHoraAula = valorHoraAula;
        this.dataAdmissao = LocalDate.now();
        this.especialidades = new ArrayList<>();
        this.horariosDisponiveis = new ArrayList<>();
        this.disponivel = true;
    }
    
    // Getters e Setters
    
    public String getRegistro() {
        return registro;
    }
    
    public void setRegistro(String registro) {
        this.registro = registro;
    }
    
    public List<TipoInstrumento> getEspecialidades() {
        return especialidades;
    }
    
    public void setEspecialidades(List<TipoInstrumento> especialidades) {
        this.especialidades = especialidades;
    }
    
    public double getValorHoraAula() {
        return valorHoraAula;
    }
    
    public void setValorHoraAula(double valorHoraAula) {
        this.valorHoraAula = valorHoraAula;
    }
    
    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }
    
    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    
    public boolean isDisponivel() {
        return disponivel;
    }
    
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    public List<String> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }
    
    public void setHorariosDisponiveis(List<String> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }
    
    /**
     * Adiciona uma especialidade ao professor.
     * 
     * @param instrumento Tipo de instrumento a adicionar
     */
    public void adicionarEspecialidade(TipoInstrumento instrumento) {
        if (instrumento != null && !especialidades.contains(instrumento)) {
            especialidades.add(instrumento);
        }
    }
    
    /**
     * Remove uma especialidade do professor.
     * 
     * @param instrumento Tipo de instrumento a remover
     */
    public void removerEspecialidade(TipoInstrumento instrumento) {
        especialidades.remove(instrumento);
    }
    
    /**
     * Verifica se o professor é especialista em determinado instrumento.
     * 
     * @param instrumento Tipo de instrumento a verificar
     * @return true se for especialista, false caso contrário
     */
    public boolean isEspecialistaEm(TipoInstrumento instrumento) {
        return especialidades.contains(instrumento);
    }
    
    /**
     * Adiciona um horário disponível para o professor.
     * 
     * @param horario Horário no formato "DiaSemana HH:mm-HH:mm"
     */
    public void adicionarHorarioDisponivel(String horario) {
        if (horario != null && !horariosDisponiveis.contains(horario)) {
            horariosDisponiveis.add(horario);
        }
    }
    
    /**
     * Calcula o tempo de casa do professor em anos.
     * 
     * @return Anos trabalhados na escola
     */
    public long calcularTempoDeServico() {
        if (dataAdmissao == null) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.YEARS.between(dataAdmissao, LocalDate.now());
    }
    
    @Override
    public String toString() {
        return "Professor{" +
                "registro='" + registro + '\'' +
                ", nome='" + getNome() + '\'' +
                ", especialidades=" + especialidades +
                ", valorHoraAula=R$ " + String.format("%.2f", valorHoraAula) +
                ", disponivel=" + disponivel +
                ", tempoServico=" + calcularTempoDeServico() + " anos" +
                '}';
    }
}
