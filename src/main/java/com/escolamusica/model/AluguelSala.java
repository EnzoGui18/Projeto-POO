package com.escolamusica.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe que representa o aluguel de uma sala ou estúdio.
 * Gerencia reservas e pagamentos de aluguel.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class AluguelSala {
    private String id;
    private Sala sala;
    private Aluno aluno;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private double valorTotal;
    private double valorPago;
    private boolean pago;
    private String finalidade;
    
    /**
     * Construtor padrão da classe AluguelSala.
     */
    public AluguelSala() {
        this.pago = false;
        this.valorPago = 0.0;
    }
    
    /**
     * Construtor com parâmetros da classe AluguelSala.
     * 
     * @param id Identificador único do aluguel
     * @param sala Sala alugada
     * @param aluno Aluno que alugou
     * @param dataHoraInicio Data e hora de início
     * @param dataHoraFim Data e hora de término
     * @param finalidade Finalidade do aluguel
     */
    public AluguelSala(String id, Sala sala, Aluno aluno, LocalDateTime dataHoraInicio,
                       LocalDateTime dataHoraFim, String finalidade) {
        this.id = id;
        this.sala = sala;
        this.aluno = aluno;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.finalidade = finalidade;
        this.valorTotal = calcularValorTotal();
        this.pago = false;
        this.valorPago = 0.0;
    }
    
    // Getters e Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Sala getSala() {
        return sala;
    }
    
    public void setSala(Sala sala) {
        this.sala = sala;
        this.valorTotal = calcularValorTotal();
    }
    
    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        this.valorTotal = calcularValorTotal();
    }
    
    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }
    
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
        this.valorTotal = calcularValorTotal();
    }
    
    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }
    
    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
        this.valorTotal = calcularValorTotal();
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public double getValorPago() {
        return valorPago;
    }
    
    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
    
    public boolean isPago() {
        return pago;
    }
    
    public void setPago(boolean pago) {
        this.pago = pago;
    }
    
    public String getFinalidade() {
        return finalidade;
    }
    
    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }
    
    /**
     * Calcula a duração do aluguel em horas.
     * 
     * @return Duração em horas
     */
    public long calcularDuracaoHoras() {
        if (dataHoraInicio == null || dataHoraFim == null) {
            return 0;
        }
        return java.time.Duration.between(dataHoraInicio, dataHoraFim).toHours();
    }
    
    /**
     * Calcula o valor total do aluguel baseado na duração e valor da sala.
     * Aplica desconto se o aluno for VIP.
     * 
     * @return Valor total do aluguel
     */
    public double calcularValorTotal() {
        if (sala == null || dataHoraInicio == null || dataHoraFim == null) {
            return 0.0;
        }
        
        long horas = calcularDuracaoHoras();
        double valorBase = horas * sala.getValorHoraAluguel();
        
        // Aplica desconto VIP se aplicável
        if (aluno != null && aluno.isVIP()) {
            double desconto = aluno.calcularDesconto();
            valorBase = valorBase * (1 - desconto);
        }
        
        return valorBase;
    }
    
    /**
     * Registra um pagamento para o aluguel.
     * 
     * @param valor Valor pago
     * @return true se o pagamento foi registrado, false caso contrário
     */
    public boolean registrarPagamento(double valor) {
        if (valor <= 0) {
            return false;
        }
        
        this.valorPago += valor;
        
        if (this.valorPago >= this.valorTotal) {
            this.pago = true;
        }
        
        return true;
    }
    
    /**
     * Calcula o saldo restante a pagar.
     * 
     * @return Valor restante
     */
    public double calcularSaldoRestante() {
        return Math.max(0, valorTotal - valorPago);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AluguelSala that = (AluguelSala) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "AluguelSala{" +
                "id='" + id + '\'' +
                ", sala=" + (sala != null ? sala.getNumero() : "N/A") +
                ", aluno=" + (aluno != null ? aluno.getNome() : "N/A") +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", duracaoHoras=" + calcularDuracaoHoras() +
                ", valorTotal=R$ " + String.format("%.2f", valorTotal) +
                ", pago=" + pago +
                ", saldoRestante=R$ " + String.format("%.2f", calcularSaldoRestante()) +
                '}';
    }
}
