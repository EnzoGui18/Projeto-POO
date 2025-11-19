package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.NegocioException;
import com.escolamusica.model.Aluno;
import com.escolamusica.model.Pagamento;
import com.escolamusica.repository.PagamentoRepositorio;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Serviço de gerenciamento de pagamentos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class PagamentoService {
    
    private PagamentoRepositorio pagamentoRepositorio;
    private AlunoService alunoService;
    
    public PagamentoService(PagamentoRepositorio pagamentoRepositorio, AlunoService alunoService) {
        this.pagamentoRepositorio = pagamentoRepositorio;
        this.alunoService = alunoService;
    }
    
    /**
     * Gera um novo pagamento para um aluno.
     * 
     * @param alunoId ID do aluno
     * @param descricao Descrição do pagamento
     * @param valor Valor do pagamento
     * @param dataVencimento Data de vencimento
     * @return Pagamento gerado
     * @throws EntidadeNaoEncontradaException Se aluno não encontrado
     */
    public Pagamento gerarPagamento(String alunoId, String descricao, double valor, 
                                   LocalDate dataVencimento) 
            throws EntidadeNaoEncontradaException {
        Aluno aluno = alunoService.buscarPorId(alunoId);
        
        Pagamento pagamento = new Pagamento();
        pagamento.setId(UUID.randomUUID().toString());
        pagamento.setAluno(aluno);
        pagamento.setDescricao(descricao);
        pagamento.setValor(valor);
        pagamento.setDataVencimento(dataVencimento);
        pagamento.setPago(false);
        
        return pagamentoRepositorio.salvar(pagamento);
    }
    
    /**
     * Registra o pagamento.
     * 
     * @param id ID do pagamento
     * @param formaPagamento Forma de pagamento utilizada
     * @return Pagamento atualizado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     * @throws NegocioException Se já estiver pago
     */
    public Pagamento registrarPagamento(String id, String formaPagamento) 
            throws EntidadeNaoEncontradaException, NegocioException {
        Pagamento pagamento = pagamentoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pagamento", id));
        
        if (pagamento.isPago()) {
            throw new NegocioException("Pagamento já foi realizado");
        }
        
        pagamento.registrarPagamento(formaPagamento);
        return pagamentoRepositorio.salvar(pagamento);
    }
    
    /**
     * Lista pagamentos de um aluno.
     * 
     * @param alunoId ID do aluno
     * @return Lista de pagamentos
     */
    public List<Pagamento> listarPorAluno(String alunoId) {
        return pagamentoRepositorio.buscarPorAluno(alunoId);
    }
    
    /**
     * Lista pagamentos pendentes.
     * 
     * @return Lista de pagamentos pendentes
     */
    public List<Pagamento> listarPendentes() {
        return pagamentoRepositorio.listarPendentes();
    }
    
    /**
     * Lista pagamentos atrasados.
     * 
     * @return Lista de pagamentos atrasados
     */
    public List<Pagamento> listarAtrasados() {
        return pagamentoRepositorio.listarAtrasados();
    }
    
    /**
     * Busca pagamento por ID.
     * 
     * @param id ID do pagamento
     * @return Pagamento encontrado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public Pagamento buscarPorId(String id) throws EntidadeNaoEncontradaException {
        return pagamentoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pagamento", id));
    }
}
