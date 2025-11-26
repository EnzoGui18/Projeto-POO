package com.escolamusica.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa uma sala de aula ou estúdio na escola de música.
 * Pode ser utilizada para aulas regulares ou alugada para prática individual.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class Sala {
    private String id;
    private String numero;
    private String nome;
    private int capacidade;
    private List<String> equipamentos;
    private boolean disponivel;
    private double valorHoraAluguel;
    private String tipo; // Aula, Prática, Estúdio
    
    /**
     * Construtor padrão da classe Sala.
     */
    public Sala() {
        this.equipamentos = new ArrayList<>();
        this.disponivel = true;
    }
    
    /**
     * Construtor com parâmetros da classe Sala.
     * 
     * @param id Identificador único da sala
     * @param numero Número da sala
     * @param nome Nome da sala
     * @param capacidade Capacidade de pessoas
     * @param valorHoraAluguel Valor por hora de aluguel
     * @param tipo Tipo de sala
     */
    public Sala(String id, String numero, String nome, int capacidade,
                double valorHoraAluguel, String tipo) {
        this.id = id;
        this.numero = numero;
        this.nome = nome;
        this.capacidade = capacidade;
        this.valorHoraAluguel = valorHoraAluguel;
        this.tipo = tipo;
        this.equipamentos = new ArrayList<>();
        this.disponivel = true;
    }
    
    // Getters e Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getCapacidade() {
        return capacidade;
    }
    
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    
    public List<String> getEquipamentos() {
        return equipamentos;
    }
    
    public void setEquipamentos(List<String> equipamentos) {
        this.equipamentos = equipamentos;
    }
    
    public boolean isDisponivel() {
        return disponivel;
    }
    
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    public double getValorHoraAluguel() {
        return valorHoraAluguel;
    }
    
    public void setValorHoraAluguel(double valorHoraAluguel) {
        this.valorHoraAluguel = valorHoraAluguel;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Adiciona um equipamento à sala.
     * 
     * @param equipamento Nome do equipamento
     */
    public void adicionarEquipamento(String equipamento) {
        if (equipamento != null && !equipamentos.contains(equipamento)) {
            equipamentos.add(equipamento);
        }
    }
    
    /**
     * Remove um equipamento da sala.
     * 
     * @param equipamento Nome do equipamento
     */
    public void removerEquipamento(String equipamento) {
        equipamentos.remove(equipamento);
    }
    
    /**
     * Verifica se a sala possui determinado equipamento.
     * 
     * @param equipamento Nome do equipamento
     * @return true se possui, false caso contrário
     */
    public boolean possuiEquipamento(String equipamento) {
        return equipamentos.contains(equipamento);
    }
    
    /**
     * Calcula o valor de aluguel com desconto VIP.
     * 
     * @param percentualDesconto Percentual de desconto (0.0 a 1.0)
     * @return Valor com desconto aplicado
     */
    public double calcularValorComDesconto(double percentualDesconto) {
        if (percentualDesconto < 0 || percentualDesconto > 1) {
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 1");
        }
        return valorHoraAluguel * (1 - percentualDesconto);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return Objects.equals(id, sala.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Sala{" +
                "id='" + id + '\'' +
                ", numero='" + numero + '\'' +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", capacidade=" + capacidade +
                ", valorHoraAluguel=R$ " + String.format("%.2f", valorHoraAluguel) +
                ", disponivel=" + disponivel +
                ", equipamentos=" + equipamentos.size() +
                '}';
    }
}
