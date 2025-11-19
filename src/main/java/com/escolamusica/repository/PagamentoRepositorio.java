package com.escolamusica.repository;

import com.escolamusica.model.Pagamento;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciar pagamentos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class PagamentoRepositorio extends RepositorioGenerico<Pagamento, String> {
    
    @Override
    protected String extrairId(Pagamento entidade) {
        return entidade.getId();
    }
    
    /**
     * Busca pagamentos de um aluno.
     * 
     * @param alunoId ID do aluno
     * @return Lista de pagamentos do aluno
     */
    public List<Pagamento> buscarPorAluno(String alunoId) {
        return dados.values().stream()
                .filter(pag -> pag.getAluno().getId().equals(alunoId))
                .collect(Collectors.toList());
    }
    
    /**
     * Lista pagamentos pendentes.
     * 
     * @return Lista de pagamentos não pagos
     */
    public List<Pagamento> listarPendentes() {
        return dados.values().stream()
                .filter(pag -> !pag.isPago())
                .collect(Collectors.toList());
    }
    
    /**
     * Lista pagamentos atrasados.
     * 
     * @return Lista de pagamentos atrasados
     */
    public List<Pagamento> listarAtrasados() {
        return dados.values().stream()
                .filter(Pagamento::isAtrasado)
                .collect(Collectors.toList());
    }
    
    /**
     * Busca pagamentos por período de vencimento.
     * 
     * @param dataInicio Data inicial
     * @param dataFim Data final
     * @return Lista de pagamentos no período
     */
    public List<Pagamento> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return dados.values().stream()
                .filter(pag -> !pag.getDataVencimento().isBefore(dataInicio) && 
                              !pag.getDataVencimento().isAfter(dataFim))
                .collect(Collectors.toList());
    }
}
