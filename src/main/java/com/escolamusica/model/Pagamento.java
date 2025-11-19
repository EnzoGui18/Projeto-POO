package com.escolamusica.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que representa um pagamento realizado na escola.
 * Controla pagamentos de mensalidades, aluguéis e eventos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class Pagamento {
    private String id;
    private Aluno aluno;
    private String descricao;
    private double valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private String formaPagamento; // Dinheiro, Cartão, PIX, Boleto
    private boolean pago;
    private String referencia; // ID da matrícula, aluguel ou evento
    private String tipoReferencia; // Mensalidade, Aluguel, Evento
    
    /**
     * Construtor padrão da classe Pagamento.
     */
    public Pagamento() {
        this.pago = false;
    }
    
    /**
     * Construtor com parâmetros da classe Pagamento.
     * 
     * @param id Identificador único do pagamento
     * @param aluno Aluno responsável pelo pagamento
     * @param descricao Descrição do pagamento
     * @param valor Valor a ser pago
     * @param dataVencimento Data de vencimento
     * @param tipoReferencia Tipo de pagamento
     * @param referencia Referência ao item pago
     */
    public Pagamento(String id, Aluno aluno, String descricao, double valor,
                     LocalDate dataVencimento, String tipoReferencia, String referencia) {
        this.id = id;
        this.aluno = aluno;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.tipoReferencia = tipoReferencia;
        this.referencia = referencia;
        this.pago = false;
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
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
    
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
    public String getFormaPagamento() {
        return formaPagamento;
    }
    
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    public boolean isPago() {
        return pago;
    }
    
    public void setPago(boolean pago) {
        this.pago = pago;
    }
    
    public String getReferencia() {
        return referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    public String getTipoReferencia() {
        return tipoReferencia;
    }
    
    public void setTipoReferencia(String tipoReferencia) {
        this.tipoReferencia = tipoReferencia;
    }
    
    /**
     * Registra o pagamento.
     * 
     * @param formaPagamento Forma de pagamento utilizada
     * @return true se registrado com sucesso, false caso contrário
     */
    public boolean registrarPagamento(String formaPagamento) {
        if (this.pago) {
            return false; // Já pago
        }
        
        this.pago = true;
        this.dataPagamento = LocalDate.now();
        this.formaPagamento = formaPagamento;
        
        return true;
    }
    
    /**
     * Verifica se o pagamento está em atraso.
     * 
     * @return true se atrasado, false caso contrário
     */
    public boolean isAtrasado() {
        if (pago || dataVencimento == null) {
            return false;
        }
        return LocalDate.now().isAfter(dataVencimento);
    }
    
    /**
     * Calcula a quantidade de dias em atraso.
     * 
     * @return Número de dias em atraso (0 se não atrasado)
     */
    public long calcularDiasAtraso() {
        if (!isAtrasado()) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(dataVencimento, LocalDate.now());
    }
    
    /**
     * Calcula multa por atraso (2% após vencimento + 0.033% ao dia).
     * 
     * @return Valor da multa
     */
    public double calcularMulta() {
        if (!isAtrasado()) {
            return 0.0;
        }
        
        long diasAtraso = calcularDiasAtraso();
        double multaFixa = valor * 0.02; // 2% de multa fixa
        double multaDiaria = valor * 0.00033 * diasAtraso; // 0.033% ao dia
        
        return multaFixa + multaDiaria;
    }
    
    /**
     * Calcula o valor total a pagar (valor + multa).
     * 
     * @return Valor total
     */
    public double calcularValorTotal() {
        return valor + calcularMulta();
    }
    
    /**
     * Cancela um pagamento ainda não realizado.
     * 
     * @return true se cancelado com sucesso, false caso contrário
     */
    public boolean cancelar() {
        if (pago) {
            return false; // Não pode cancelar pagamento já realizado
        }
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Pagamento{" +
                "id='" + id + '\'' +
                ", aluno=" + (aluno != null ? aluno.getNome() : "N/A") +
                ", descricao='" + descricao + '\'' +
                ", valor=R$ " + String.format("%.2f", valor) +
                ", dataVencimento=" + dataVencimento +
                ", pago=" + pago +
                (pago ? ", dataPagamento=" + dataPagamento : "") +
                (isAtrasado() ? ", ATRASADO (" + calcularDiasAtraso() + " dias)" : "") +
                (isAtrasado() ? ", multa=R$ " + String.format("%.2f", calcularMulta()) : "") +
                ", valorTotal=R$ " + String.format("%.2f", calcularValorTotal()) +
                '}';
    }
}
