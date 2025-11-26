package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.NegocioException;
import com.escolamusica.exception.ValidacaoException;
import com.escolamusica.model.Professor;
import com.escolamusica.model.TipoInstrumento;
import com.escolamusica.repository.ProfessorRepositorio;
import java.util.List;
import java.util.UUID;

/**
 * Serviço de gerenciamento de professores.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class ProfessorService {
    
    private ProfessorRepositorio professorRepositorio;
    
    public ProfessorService(ProfessorRepositorio professorRepositorio) {
        this.professorRepositorio = professorRepositorio;
    }
    
    /**
     * Cadastra um novo professor.
     * 
     * @param professor Professor a ser cadastrado
     * @return Professor cadastrado
     * @throws ValidacaoException Se dados inválidos
     * @throws NegocioException Se CPF já cadastrado
     */
    public Professor cadastrar(Professor professor) throws ValidacaoException, NegocioException {
        validarProfessor(professor);
        
        // Verifica se CPF já está cadastrado
        if (professorRepositorio.listarTodos().stream()
                .anyMatch(p -> p.getCpf().equals(professor.getCpf()))) {
            throw new NegocioException("CPF já cadastrado no sistema");
        }
        
        // Gera ID se não fornecido
        if (professor.getId() == null || professor.getId().isEmpty()) {
            professor.setId(UUID.randomUUID().toString());
        }
        
        // Gera registro se não fornecido
        if (professor.getRegistro() == null || professor.getRegistro().isEmpty()) {
            professor.setRegistro(gerarRegistro());
        }
        
        return professorRepositorio.salvar(professor);
    }
    
    /**
     * Busca professor por ID.
     * 
     * @param id ID do professor
     * @return Professor encontrado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public Professor buscarPorId(String id) throws EntidadeNaoEncontradaException {
        return professorRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Professor", id));
    }
    
    /**
     * Busca professor por ID ou Registro.
     * Tenta primeiro por ID, depois por Registro.
     * 
     * @param idOuRegistro ID ou Registro do professor
     * @return Professor encontrado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public Professor buscarPorIdOuRegistro(String idOuRegistro) throws EntidadeNaoEncontradaException {
        // Primeiro tenta buscar por ID
        var professorOpt = professorRepositorio.buscarPorId(idOuRegistro);
        if (professorOpt.isPresent()) {
            return professorOpt.get();
        }
        
        // Se não encontrou por ID, tenta por registro
        Professor professor = professorRepositorio.buscarPorRegistro(idOuRegistro);
        if (professor != null) {
            return professor;
        }
        
        throw new EntidadeNaoEncontradaException("Professor", idOuRegistro);
    }
    
    /**
     * Busca professores por nome.
     * 
     * @param nome Nome ou parte do nome
     * @return Lista de professores encontrados
     */
    public List<Professor> buscarPorNome(String nome) {
        return professorRepositorio.buscarPorNome(nome);
    }
    
    /**
     * Busca professores por especialidade.
     * 
     * @param instrumento Instrumento
     * @return Lista de professores especializados
     */
    public List<Professor> buscarPorEspecialidade(TipoInstrumento instrumento) {
        return professorRepositorio.buscarPorEspecialidade(instrumento);
    }
    
    /**
     * Lista todos os professores.
     * 
     * @return Lista de professores
     */
    public List<Professor> listarTodos() {
        return professorRepositorio.listarTodos();
    }
    
    /**
     * Lista professores disponíveis.
     * 
     * @return Lista de professores disponíveis
     */
    public List<Professor> listarDisponiveis() {
        return professorRepositorio.listarDisponiveis();
    }
    
    /**
     * Atualiza dados de um professor.
     * 
     * @param id ID do professor
     * @param professorAtualizado Dados atualizados
     * @return Professor atualizado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     * @throws ValidacaoException Se dados inválidos
     */
    public Professor atualizar(String id, Professor professorAtualizado) 
            throws EntidadeNaoEncontradaException, ValidacaoException {
        Professor professor = buscarPorId(id);
        
        validarProfessor(professorAtualizado);
        
        professor.setNome(professorAtualizado.getNome());
        professor.setTelefone(professorAtualizado.getTelefone());
        professor.setEmail(professorAtualizado.getEmail());
        professor.setEndereco(professorAtualizado.getEndereco());
        professor.setEspecialidades(professorAtualizado.getEspecialidades());
        professor.setDisponivel(professorAtualizado.isDisponivel());
        
        return professorRepositorio.salvar(professor);
    }
    
    /**
     * Remove um professor.
     * 
     * @param id ID do professor
     * @return true se removido
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public boolean remover(String id) throws EntidadeNaoEncontradaException {
        buscarPorId(id); // Verifica se existe
        return professorRepositorio.remover(id);
    }
    
    /**
     * Valida dados do professor.
     * 
     * @param professor Professor a validar
     * @throws ValidacaoException Se dados inválidos
     */
    private void validarProfessor(Professor professor) throws ValidacaoException {
        if (professor == null) {
            throw new ValidacaoException("Professor não pode ser nulo");
        }
        
        if (professor.getNome() == null || professor.getNome().trim().isEmpty()) {
            throw new ValidacaoException("Nome do professor é obrigatório");
        }
        
        if (professor.getCpf() == null || professor.getCpf().trim().isEmpty()) {
            throw new ValidacaoException("CPF do professor é obrigatório");
        }
        
        if (professor.getEmail() == null || !professor.getEmail().contains("@")) {
            throw new ValidacaoException("Email inválido");
        }
        
        if (professor.getEspecialidades() == null || professor.getEspecialidades().isEmpty()) {
            throw new ValidacaoException("Professor deve ter pelo menos uma especialidade");
        }
    }
    
    /**
     * Gera registro único para professor.
     * 
     * @return Registro
     */
    private String gerarRegistro() {
        int numero = professorRepositorio.contar() + 1;
        return String.format("PROF%04d", numero);
    }
}
