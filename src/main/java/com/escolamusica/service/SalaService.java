package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.ValidacaoException;
import com.escolamusica.model.Sala;
import com.escolamusica.repository.SalaRepositorio;
import java.util.List;
import java.util.UUID;

/**
 * Serviço de gerenciamento de salas e estúdios.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class SalaService {
    
    private SalaRepositorio salaRepositorio;
    
    public SalaService(SalaRepositorio salaRepositorio) {
        this.salaRepositorio = salaRepositorio;
    }
    
    /**
     * Cadastra uma nova sala.
     * 
     * @param sala Sala a ser cadastrada
     * @return Sala cadastrada
     * @throws ValidacaoException Se dados inválidos
     */
    public Sala cadastrar(Sala sala) throws ValidacaoException {
        validarSala(sala);
        
        if (sala.getId() == null || sala.getId().isEmpty()) {
            sala.setId(UUID.randomUUID().toString());
        }
        
        return salaRepositorio.salvar(sala);
    }
    
    /**
     * Busca sala por ID.
     * 
     * @param id ID da sala
     * @return Sala encontrada
     * @throws EntidadeNaoEncontradaException Se não encontrada
     */
    public Sala buscarPorId(String id) throws EntidadeNaoEncontradaException {
        return salaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Sala", id));
    }
    
    /**
     * Busca sala por número.
     * 
     * @param numero Número da sala
     * @return Sala encontrada
     */
    public Sala buscarPorNumero(String numero) {
        return salaRepositorio.buscarPorNumero(numero);
    }
    
    /**
     * Busca salas por tipo.
     * 
     * @param tipo Tipo da sala
     * @return Lista de salas
     */
    public List<Sala> buscarPorTipo(String tipo) {
        return salaRepositorio.buscarPorTipo(tipo);
    }
    
    /**
     * Busca salas por capacidade.
     * 
     * @param capacidadeMinima Capacidade mínima
     * @return Lista de salas
     */
    public List<Sala> buscarPorCapacidade(int capacidadeMinima) {
        return salaRepositorio.buscarPorCapacidade(capacidadeMinima);
    }
    
    /**
     * Lista todas as salas.
     * 
     * @return Lista de salas
     */
    public List<Sala> listarTodas() {
        return salaRepositorio.listarTodos();
    }
    
    /**
     * Lista salas disponíveis.
     * 
     * @return Lista de salas disponíveis
     */
    public List<Sala> listarDisponiveis() {
        return salaRepositorio.listarDisponiveis();
    }
    
    /**
     * Atualiza uma sala.
     * 
     * @param id ID da sala
     * @param salaAtualizada Dados atualizados
     * @return Sala atualizada
     * @throws EntidadeNaoEncontradaException Se não encontrada
     * @throws ValidacaoException Se dados inválidos
     */
    public Sala atualizar(String id, Sala salaAtualizada) 
            throws EntidadeNaoEncontradaException, ValidacaoException {
        Sala sala = buscarPorId(id);
        
        validarSala(salaAtualizada);
        
        sala.setNumero(salaAtualizada.getNumero());
        sala.setCapacidade(salaAtualizada.getCapacidade());
        sala.setTipo(salaAtualizada.getTipo());
        sala.setValorHoraAluguel(salaAtualizada.getValorHoraAluguel());
        sala.setDisponivel(salaAtualizada.isDisponivel());
        sala.setEquipamentos(salaAtualizada.getEquipamentos());
        
        return salaRepositorio.salvar(sala);
    }
    
    /**
     * Remove uma sala.
     * 
     * @param id ID da sala
     * @return true se removida
     * @throws EntidadeNaoEncontradaException Se não encontrada
     */
    public boolean remover(String id) throws EntidadeNaoEncontradaException {
        buscarPorId(id);
        return salaRepositorio.remover(id);
    }
    
    /**
     * Valida dados da sala.
     * 
     * @param sala Sala a validar
     * @throws ValidacaoException Se dados inválidos
     */
    private void validarSala(Sala sala) throws ValidacaoException {
        if (sala == null) {
            throw new ValidacaoException("Sala não pode ser nula");
        }
        
        if (sala.getNumero() == null || sala.getNumero().trim().isEmpty()) {
            throw new ValidacaoException("Número da sala é obrigatório");
        }
        
        if (sala.getCapacidade() <= 0) {
            throw new ValidacaoException("Capacidade deve ser maior que zero");
        }
        
        if (sala.getValorHoraAluguel() < 0) {
            throw new ValidacaoException("Valor de aluguel não pode ser negativo");
        }
    }
}
