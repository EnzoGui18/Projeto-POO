package com.escolamusica.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa uma aula na escola de música.
 * Relaciona professor, alunos, curso, horário e sala.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class Aula {
    private String id;
    private Curso curso;
    private Professor professor;
    private TipoAula tipoAula;
    private List<Aluno> alunos;
    private Sala sala;
    private LocalDateTime dataHora;
    private int duracaoMinutos;
    private String conteudoProgramatico;
    private boolean realizada;
    private String observacoes;
    
    /**
     * Construtor padrão da classe Aula.
     */
    public Aula() {
        this.alunos = new ArrayList<>();
        this.realizada = false;
    }
    
    /**
     * Construtor com parâmetros da classe Aula.
     * 
     * @param id Identificador único da aula
     * @param curso Curso da aula
     * @param professor Professor responsável
     * @param tipoAula Tipo de aula (individual/grupo)
     * @param sala Sala onde ocorrerá a aula
     * @param dataHora Data e hora da aula
     * @param duracaoMinutos Duração em minutos
     */
    public Aula(String id, Curso curso, Professor professor, TipoAula tipoAula,
                Sala sala, LocalDateTime dataHora, int duracaoMinutos) {
        this.id = id;
        this.curso = curso;
        this.professor = professor;
        this.tipoAula = tipoAula;
        this.sala = sala;
        this.dataHora = dataHora;
        this.duracaoMinutos = duracaoMinutos;
        this.alunos = new ArrayList<>();
        this.realizada = false;
    }
    
    // Getters e Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public Professor getProfessor() {
        return professor;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public TipoAula getTipoAula() {
        return tipoAula;
    }
    
    public void setTipoAula(TipoAula tipoAula) {
        this.tipoAula = tipoAula;
    }
    
    public List<Aluno> getAlunos() {
        return alunos;
    }
    
    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public Sala getSala() {
        return sala;
    }
    
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
    
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
    
    /**
     * Alias para getDuracaoMinutos().
     * 
     * @return Duração em minutos
     */
    public int getDuracao() {
        return duracaoMinutos;
    }
    
    /**
     * Alias para setDuracaoMinutos().
     * 
     * @param duracao Duração em minutos
     */
    public void setDuracao(int duracao) {
        this.duracaoMinutos = duracao;
    }
    
    public String getConteudoProgramatico() {
        return conteudoProgramatico;
    }
    
    public void setConteudoProgramatico(String conteudoProgramatico) {
        this.conteudoProgramatico = conteudoProgramatico;
    }
    
    public boolean isRealizada() {
        return realizada;
    }
    
    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }
    
    public String getObservacoes() {
        return observacoes;
    }
    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    /**
     * Adiciona um aluno à aula se houver vaga disponível.
     * 
     * @param aluno Aluno a ser adicionado
     * @return true se adicionado com sucesso, false caso contrário
     */
    public boolean adicionarAluno(Aluno aluno) {
        if (aluno == null) {
            return false;
        }
        
        if (alunos.size() >= tipoAula.getMaximoAlunos()) {
            return false;
        }
        
        if (!alunos.contains(aluno)) {
            return alunos.add(aluno);
        }
        
        return false;
    }
    
    /**
     * Remove um aluno da aula.
     * 
     * @param aluno Aluno a ser removido
     * @return true se removido com sucesso, false caso contrário
     */
    public boolean removerAluno(Aluno aluno) {
        return alunos.remove(aluno);
    }
    
    /**
     * Verifica se a aula possui vagas disponíveis.
     * 
     * @return true se houver vagas, false caso contrário
     */
    public boolean possuiVagasDisponiveis() {
        return alunos.size() < tipoAula.getMaximoAlunos();
    }
    
    /**
     * Calcula o número de vagas disponíveis.
     * 
     * @return Número de vagas disponíveis
     */
    public int calcularVagasDisponiveis() {
        return tipoAula.getMaximoAlunos() - alunos.size();
    }
    
    /**
     * Marca a aula como realizada.
     */
    public void marcarComoRealizada() {
        this.realizada = true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(id, aula.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Aula{" +
                "id='" + id + '\'' +
                ", curso=" + (curso != null ? curso.getNome() : "N/A") +
                ", professor=" + (professor != null ? professor.getNome() : "N/A") +
                ", tipoAula=" + tipoAula +
                ", sala=" + (sala != null ? sala.getNumero() : "N/A") +
                ", dataHora=" + dataHora +
                ", alunos=" + alunos.size() + "/" + tipoAula.getMaximoAlunos() +
                ", realizada=" + realizada +
                '}';
    }
}
