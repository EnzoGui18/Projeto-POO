package com.escolamusica.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que representa um plano de fidelidade para alunos regulares e VIP.
 * Controla pontos acumulados e benefícios progressivos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class PlanoFidelidade {
    private String id;
    private Aluno aluno;
    private int pontosAcumulados;
    private LocalDate dataInicio;
    private String nivel; // Bronze, Prata, Ouro, Platina
    private double percentualDescontoAtual;
    
    // Constantes para níveis
    public static final int PONTOS_BRONZE = 0;
    public static final int PONTOS_PRATA = 100;
    public static final int PONTOS_OURO = 500;
    public static final int PONTOS_PLATINA = 1000;
    
    public static final double DESCONTO_BRONZE = 0.00;
    public static final double DESCONTO_PRATA = 0.05; // 5%
    public static final double DESCONTO_OURO = 0.10; // 10%
    public static final double DESCONTO_PLATINA = 0.15; // 15%
    
    /**
     * Construtor padrão da classe PlanoFidelidade.
     */
    public PlanoFidelidade() {
        this.pontosAcumulados = 0;
        this.dataInicio = LocalDate.now();
        this.nivel = "Bronze";
        this.percentualDescontoAtual = DESCONTO_BRONZE;
    }
    
    /**
     * Construtor com parâmetros da classe PlanoFidelidade.
     * 
     * @param id Identificador único do plano
     * @param aluno Aluno do plano
     */
    public PlanoFidelidade(String id, Aluno aluno) {
        this.id = id;
        this.aluno = aluno;
        this.pontosAcumulados = 0;
        this.dataInicio = LocalDate.now();
        this.nivel = "Bronze";
        this.percentualDescontoAtual = DESCONTO_BRONZE;
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
    
    public int getPontosAcumulados() {
        return pontosAcumulados;
    }
    
    public void setPontosAcumulados(int pontosAcumulados) {
        this.pontosAcumulados = pontosAcumulados;
        atualizarNivel();
    }
    
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public String getNivel() {
        return nivel;
    }
    
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    public double getPercentualDescontoAtual() {
        return percentualDescontoAtual;
    }
    
    public void setPercentualDescontoAtual(double percentualDescontoAtual) {
        this.percentualDescontoAtual = percentualDescontoAtual;
    }
    
    /**
     * Adiciona pontos ao plano de fidelidade.
     * Atualiza automaticamente o nível e desconto.
     * 
     * @param pontos Quantidade de pontos a adicionar
     */
    public void adicionarPontos(int pontos) {
        if (pontos > 0) {
            this.pontosAcumulados += pontos;
            atualizarNivel();
        }
    }
    
    /**
     * Remove pontos do plano de fidelidade.
     * Atualiza automaticamente o nível e desconto.
     * 
     * @param pontos Quantidade de pontos a remover
     * @return true se removido com sucesso, false se não houver pontos suficientes
     */
    public boolean removerPontos(int pontos) {
        if (pontos > 0 && pontosAcumulados >= pontos) {
            this.pontosAcumulados -= pontos;
            atualizarNivel();
            return true;
        }
        return false;
    }
    
    /**
     * Atualiza o nível e desconto baseado nos pontos acumulados.
     */
    private void atualizarNivel() {
        if (pontosAcumulados >= PONTOS_PLATINA) {
            nivel = "Platina";
            percentualDescontoAtual = DESCONTO_PLATINA;
        } else if (pontosAcumulados >= PONTOS_OURO) {
            nivel = "Ouro";
            percentualDescontoAtual = DESCONTO_OURO;
        } else if (pontosAcumulados >= PONTOS_PRATA) {
            nivel = "Prata";
            percentualDescontoAtual = DESCONTO_PRATA;
        } else {
            nivel = "Bronze";
            percentualDescontoAtual = DESCONTO_BRONZE;
        }
    }
    
    /**
     * Calcula quantos pontos faltam para o próximo nível.
     * 
     * @return Pontos necessários para próximo nível (0 se já está no máximo)
     */
    public int calcularPontosParaProximoNivel() {
        if (pontosAcumulados >= PONTOS_PLATINA) {
            return 0; // Já está no nível máximo
        } else if (pontosAcumulados >= PONTOS_OURO) {
            return PONTOS_PLATINA - pontosAcumulados;
        } else if (pontosAcumulados >= PONTOS_PRATA) {
            return PONTOS_OURO - pontosAcumulados;
        } else {
            return PONTOS_PRATA - pontosAcumulados;
        }
    }
    
    /**
     * Retorna o próximo nível disponível.
     * 
     * @return Nome do próximo nível ou "Máximo" se já está no topo
     */
    public String obterProximoNivel() {
        if (pontosAcumulados >= PONTOS_PLATINA) {
            return "Máximo";
        } else if (pontosAcumulados >= PONTOS_OURO) {
            return "Platina";
        } else if (pontosAcumulados >= PONTOS_PRATA) {
            return "Ouro";
        } else {
            return "Prata";
        }
    }
    
    /**
     * Calcula o tempo de fidelidade em meses.
     * 
     * @return Meses desde o início do plano
     */
    public long calcularMesesDeFidelidade() {
        if (dataInicio == null) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.MONTHS.between(dataInicio, LocalDate.now());
    }
    
    /**
     * Gera um relatório do plano de fidelidade.
     * 
     * @return Relatório formatado
     */
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== PLANO DE FIDELIDADE ===\n");
        relatorio.append("Aluno: ").append(aluno != null ? aluno.getNome() : "N/A").append("\n");
        relatorio.append("Nível Atual: ").append(nivel).append("\n");
        relatorio.append("Pontos Acumulados: ").append(pontosAcumulados).append("\n");
        relatorio.append("Desconto Atual: ").append((int)(percentualDescontoAtual * 100)).append("%\n");
        relatorio.append("Tempo de Fidelidade: ").append(calcularMesesDeFidelidade()).append(" meses\n");
        
        if (!nivel.equals("Platina")) {
            relatorio.append("\nPróximo Nível: ").append(obterProximoNivel()).append("\n");
            relatorio.append("Pontos Necessários: ").append(calcularPontosParaProximoNivel()).append("\n");
        } else {
            relatorio.append("\nVocê já alcançou o nível máximo! Parabéns!\n");
        }
        
        relatorio.append("===========================");
        return relatorio.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanoFidelidade that = (PlanoFidelidade) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "PlanoFidelidade{" +
                "id='" + id + '\'' +
                ", aluno=" + (aluno != null ? aluno.getNome() : "N/A") +
                ", nivel='" + nivel + '\'' +
                ", pontosAcumulados=" + pontosAcumulados +
                ", percentualDesconto=" + (int)(percentualDescontoAtual * 100) + "%" +
                ", tempoFidelidade=" + calcularMesesDeFidelidade() + " meses" +
                '}';
    }
}
