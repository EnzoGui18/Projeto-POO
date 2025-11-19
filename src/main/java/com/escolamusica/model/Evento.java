package com.escolamusica.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa um evento, recital ou apresentação da escola.
 * Gerencia participantes, vagas e benefícios para alunos VIP.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class Evento {
    private String id;
    private String nome;
    private String descricao;
    private LocalDateTime dataHora;
    private String local;
    private int capacidadeTotal;
    private int vagasReservadasVIP;
    private List<Aluno> participantes;
    private List<Aluno> listaEspera;
    private double valorIngresso;
    private String tipo; // Recital, Apresentação, Workshop, etc.
    private boolean aberto;
    
    /**
     * Construtor padrão da classe Evento.
     */
    public Evento() {
        this.participantes = new ArrayList<>();
        this.listaEspera = new ArrayList<>();
        this.aberto = true;
    }
    
    /**
     * Construtor com parâmetros da classe Evento.
     * 
     * @param id Identificador único do evento
     * @param nome Nome do evento
     * @param descricao Descrição do evento
     * @param dataHora Data e hora do evento
     * @param local Local do evento
     * @param capacidadeTotal Capacidade total de participantes
     * @param vagasReservadasVIP Vagas reservadas para VIP
     * @param valorIngresso Valor do ingresso
     * @param tipo Tipo de evento
     */
    public Evento(String id, String nome, String descricao, LocalDateTime dataHora,
                  String local, int capacidadeTotal, int vagasReservadasVIP,
                  double valorIngresso, String tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.local = local;
        this.capacidadeTotal = capacidadeTotal;
        this.vagasReservadasVIP = vagasReservadasVIP;
        this.valorIngresso = valorIngresso;
        this.tipo = tipo;
        this.participantes = new ArrayList<>();
        this.listaEspera = new ArrayList<>();
        this.aberto = true;
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
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
    public String getLocal() {
        return local;
    }
    
    public void setLocal(String local) {
        this.local = local;
    }
    
    public int getCapacidadeTotal() {
        return capacidadeTotal;
    }
    
    public void setCapacidadeTotal(int capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }
    
    public int getVagasReservadasVIP() {
        return vagasReservadasVIP;
    }
    
    public void setVagasReservadasVIP(int vagasReservadasVIP) {
        this.vagasReservadasVIP = vagasReservadasVIP;
    }
    
    public List<Aluno> getParticipantes() {
        return participantes;
    }
    
    public void setParticipantes(List<Aluno> participantes) {
        this.participantes = participantes;
    }
    
    public List<Aluno> getListaEspera() {
        return listaEspera;
    }
    
    public void setListaEspera(List<Aluno> listaEspera) {
        this.listaEspera = listaEspera;
    }
    
    public double getValorIngresso() {
        return valorIngresso;
    }
    
    public void setValorIngresso(double valorIngresso) {
        this.valorIngresso = valorIngresso;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public boolean isAberto() {
        return aberto;
    }
    
    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }
    
    /**
     * Inscreve um aluno no evento.
     * Alunos VIP têm prioridade nas vagas reservadas.
     * 
     * @param aluno Aluno a ser inscrito
     * @return true se inscrito com sucesso, false caso contrário
     */
    public boolean inscreverAluno(Aluno aluno) {
        if (aluno == null || !aberto) {
            return false;
        }
        
        if (participantes.contains(aluno)) {
            return false; // Já inscrito
        }
        
        // Verifica vagas VIP
        if (aluno.isVIP()) {
            long vagasVIPOcupadas = participantes.stream()
                    .filter(Aluno::isVIP)
                    .count();
            
            if (vagasVIPOcupadas < vagasReservadasVIP) {
                participantes.add(aluno);
                return true;
            }
        }
        
        // Verifica vagas regulares
        if (participantes.size() < capacidadeTotal) {
            participantes.add(aluno);
            return true;
        }
        
        // Lista de espera
        if (!listaEspera.contains(aluno)) {
            listaEspera.add(aluno);
        }
        
        return false;
    }
    
    /**
     * Cancela a inscrição de um aluno.
     * 
     * @param aluno Aluno a ser removido
     * @return true se removido com sucesso, false caso contrário
     */
    public boolean cancelarInscricao(Aluno aluno) {
        boolean removido = participantes.remove(aluno);
        
        if (removido && !listaEspera.isEmpty()) {
            // Promove aluno da lista de espera
            Aluno proximoAluno = listaEspera.remove(0);
            participantes.add(proximoAluno);
        }
        
        return removido;
    }
    
    /**
     * Calcula o número de vagas disponíveis.
     * 
     * @return Número de vagas disponíveis
     */
    public int calcularVagasDisponiveis() {
        return Math.max(0, capacidadeTotal - participantes.size());
    }
    
    /**
     * Calcula o número de vagas VIP disponíveis.
     * 
     * @return Número de vagas VIP disponíveis
     */
    public int calcularVagasVIPDisponiveis() {
        long vagasVIPOcupadas = participantes.stream()
                .filter(Aluno::isVIP)
                .count();
        return Math.max(0, (int)(vagasReservadasVIP - vagasVIPOcupadas));
    }
    
    /**
     * Verifica se o evento está lotado.
     * 
     * @return true se lotado, false caso contrário
     */
    public boolean isLotado() {
        return participantes.size() >= capacidadeTotal;
    }
    
    /**
     * Fecha as inscrições do evento.
     */
    public void fecharInscricoes() {
        this.aberto = false;
    }
    
    /**
     * Abre as inscrições do evento.
     */
    public void abrirInscricoes() {
        this.aberto = true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Evento{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", dataHora=" + dataHora +
                ", local='" + local + '\'' +
                ", participantes=" + participantes.size() + "/" + capacidadeTotal +
                ", vagasVIPDisponiveis=" + calcularVagasVIPDisponiveis() +
                ", listaEspera=" + listaEspera.size() +
                ", valorIngresso=R$ " + String.format("%.2f", valorIngresso) +
                ", aberto=" + aberto +
                '}';
    }
}
