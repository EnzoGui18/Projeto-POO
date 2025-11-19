package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.ValidacaoException;
import com.escolamusica.model.Curso;
import com.escolamusica.model.TipoInstrumento;
import com.escolamusica.repository.CursoRepositorio;
import java.util.List;
import java.util.UUID;

/**
 * Serviço de gerenciamento de cursos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class CursoService {
    
    private CursoRepositorio cursoRepositorio;
    
    public CursoService(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }
    
    /**
     * Cadastra um novo curso.
     * 
     * @param curso Curso a ser cadastrado
     * @return Curso cadastrado
     * @throws ValidacaoException Se dados inválidos
     */
    public Curso cadastrar(Curso curso) throws ValidacaoException {
        validarCurso(curso);
        
        // Gera ID se não fornecido
        if (curso.getId() == null || curso.getId().isEmpty()) {
            curso.setId(UUID.randomUUID().toString());
        }
        
        return cursoRepositorio.salvar(curso);
    }
    
    /**
     * Busca curso por ID.
     * 
     * @param id ID do curso
     * @return Curso encontrado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public Curso buscarPorId(String id) throws EntidadeNaoEncontradaException {
        return cursoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Curso", id));
    }
    
    /**
     * Busca cursos por instrumento.
     * 
     * @param instrumento Tipo de instrumento
     * @return Lista de cursos
     */
    public List<Curso> buscarPorInstrumento(TipoInstrumento instrumento) {
        return cursoRepositorio.buscarPorInstrumento(instrumento);
    }
    
    /**
     * Busca cursos por nível.
     * 
     * @param nivel Nível do curso
     * @return Lista de cursos
     */
    public List<Curso> buscarPorNivel(String nivel) {
        return cursoRepositorio.buscarPorNivel(nivel);
    }
    
    /**
     * Lista todos os cursos.
     * 
     * @return Lista de cursos
     */
    public List<Curso> listarTodos() {
        return cursoRepositorio.listarTodos();
    }
    
    /**
     * Lista cursos ativos.
     * 
     * @return Lista de cursos ativos
     */
    public List<Curso> listarAtivos() {
        return cursoRepositorio.listarAtivos();
    }
    
    /**
     * Atualiza um curso.
     * 
     * @param id ID do curso
     * @param cursoAtualizado Dados atualizados
     * @return Curso atualizado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     * @throws ValidacaoException Se dados inválidos
     */
    public Curso atualizar(String id, Curso cursoAtualizado) 
            throws EntidadeNaoEncontradaException, ValidacaoException {
        Curso curso = buscarPorId(id);
        
        validarCurso(cursoAtualizado);
        
        curso.setNome(cursoAtualizado.getNome());
        curso.setDescricao(cursoAtualizado.getDescricao());
        curso.setValorMensal(cursoAtualizado.getValorMensal());
        curso.setDuracaoMeses(cursoAtualizado.getDuracaoMeses());
        curso.setNivel(cursoAtualizado.getNivel());
        curso.setAtivo(cursoAtualizado.isAtivo());
        
        return cursoRepositorio.salvar(curso);
    }
    
    /**
     * Remove um curso.
     * 
     * @param id ID do curso
     * @return true se removido
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public boolean remover(String id) throws EntidadeNaoEncontradaException {
        buscarPorId(id);
        return cursoRepositorio.remover(id);
    }
    
    /**
     * Valida dados do curso.
     * 
     * @param curso Curso a validar
     * @throws ValidacaoException Se dados inválidos
     */
    private void validarCurso(Curso curso) throws ValidacaoException {
        if (curso == null) {
            throw new ValidacaoException("Curso não pode ser nulo");
        }
        
        if (curso.getNome() == null || curso.getNome().trim().isEmpty()) {
            throw new ValidacaoException("Nome do curso é obrigatório");
        }
        
        if (curso.getInstrumento() == null) {
            throw new ValidacaoException("Instrumento é obrigatório");
        }
        
        if (curso.getValorMensal() <= 0) {
            throw new ValidacaoException("Valor mensal deve ser maior que zero");
        }
        
        if (curso.getDuracaoMeses() <= 0) {
            throw new ValidacaoException("Duração deve ser maior que zero");
        }
    }
}
