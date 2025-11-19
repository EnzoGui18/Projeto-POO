package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.NegocioException;
import com.escolamusica.exception.ValidacaoException;
import com.escolamusica.model.Aluno;
import com.escolamusica.model.AlunoVIP;
import com.escolamusica.repository.AlunoRepositorio;
import java.util.List;
import java.util.UUID;

/**
 * Serviço de gerenciamento de alunos.
 * Implementa as regras de negócio relacionadas a alunos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class AlunoService {
    
    private AlunoRepositorio alunoRepositorio;
    
    /**
     * Construtor do serviço.
     * 
     * @param alunoRepositorio Repositório de alunos
     */
    public AlunoService(AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }
    
    /**
     * Cadastra um novo aluno.
     * 
     * @param aluno Aluno a ser cadastrado
     * @return Aluno cadastrado
     * @throws ValidacaoException Se os dados do aluno forem inválidos
     * @throws NegocioException Se CPF já cadastrado
     */
    public Aluno cadastrar(Aluno aluno) throws ValidacaoException, NegocioException {
        validarAluno(aluno);
        
        // Verifica se CPF já está cadastrado
        if (alunoRepositorio.buscarPorCpf(aluno.getCpf()) != null) {
            throw new NegocioException("CPF já cadastrado no sistema");
        }
        
        // Gera ID se não fornecido
        if (aluno.getId() == null || aluno.getId().isEmpty()) {
            aluno.setId(UUID.randomUUID().toString());
        }
        
        // Gera matrícula se não fornecida
        if (aluno.getMatricula() == null || aluno.getMatricula().isEmpty()) {
            aluno.setMatricula(gerarMatricula());
        }
        
        return alunoRepositorio.salvar(aluno);
    }
    
    /**
     * Busca um aluno por ID.
     * 
     * @param id ID do aluno
     * @return Aluno encontrado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     */
    public Aluno buscarPorId(String id) throws EntidadeNaoEncontradaException {
        return alunoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno", id));
    }
    
    /**
     * Busca alunos por nome.
     * 
     * @param nome Nome ou parte do nome
     * @return Lista de alunos encontrados
     */
    public List<Aluno> buscarPorNome(String nome) {
        return alunoRepositorio.buscarPorNome(nome);
    }
    
    /**
     * Busca aluno por CPF.
     * 
     * @param cpf CPF do aluno
     * @return Aluno encontrado ou null
     */
    public Aluno buscarPorCpf(String cpf) {
        return alunoRepositorio.buscarPorCpf(cpf);
    }
    
    /**
     * Lista todos os alunos.
     * 
     * @return Lista de alunos
     */
    public List<Aluno> listarTodos() {
        return alunoRepositorio.listarTodos();
    }
    
    /**
     * Lista apenas alunos VIP.
     * 
     * @return Lista de alunos VIP
     */
    public List<Aluno> listarVIPs() {
        return alunoRepositorio.listarAlunosVIP();
    }
    
    /**
     * Atualiza dados de um aluno.
     * 
     * @param id ID do aluno
     * @param alunoAtualizado Aluno com dados atualizados
     * @return Aluno atualizado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     * @throws ValidacaoException Se dados inválidos
     */
    public Aluno atualizar(String id, Aluno alunoAtualizado) 
            throws EntidadeNaoEncontradaException, ValidacaoException {
        Aluno aluno = buscarPorId(id);
        
        validarAluno(alunoAtualizado);
        
        // Atualiza campos
        aluno.setNome(alunoAtualizado.getNome());
        aluno.setTelefone(alunoAtualizado.getTelefone());
        aluno.setEmail(alunoAtualizado.getEmail());
        aluno.setEndereco(alunoAtualizado.getEndereco());
        
        return alunoRepositorio.salvar(aluno);
    }
    
    /**
     * Remove um aluno.
     * 
     * @param id ID do aluno
     * @return true se removido com sucesso
     * @throws EntidadeNaoEncontradaException Se não encontrado
     * @throws NegocioException Se aluno possui matrículas ativas
     */
    public boolean remover(String id) 
            throws EntidadeNaoEncontradaException, NegocioException {
        Aluno aluno = buscarPorId(id);
        
        // Verifica se possui matrículas ativas
        if (!aluno.getMatriculas().isEmpty()) {
            throw new NegocioException("Não é possível remover aluno com matrículas ativas");
        }
        
        return alunoRepositorio.remover(id);
    }
    
    /**
     * Promove um aluno regular para VIP.
     * 
     * @param id ID do aluno
     * @return Aluno VIP criado
     * @throws EntidadeNaoEncontradaException Se não encontrado
     * @throws NegocioException Se já for VIP
     */
    public AlunoVIP promoverParaVIP(String id) 
            throws EntidadeNaoEncontradaException, NegocioException {
        Aluno aluno = buscarPorId(id);
        
        if (aluno.isVIP()) {
            throw new NegocioException("Aluno já é VIP");
        }
        
        // Cria novo aluno VIP com dados do aluno regular
        AlunoVIP alunoVIP = new AlunoVIP();
        alunoVIP.setId(aluno.getId());
        alunoVIP.setNome(aluno.getNome());
        alunoVIP.setCpf(aluno.getCpf());
        alunoVIP.setTelefone(aluno.getTelefone());
        alunoVIP.setEmail(aluno.getEmail());
        alunoVIP.setDataNascimento(aluno.getDataNascimento());
        alunoVIP.setEndereco(aluno.getEndereco());
        alunoVIP.setMatricula(aluno.getMatricula());
        alunoVIP.setAtivo(aluno.isAtivo());
        
        // Remove aluno regular e salva VIP
        alunoRepositorio.remover(id);
        return (AlunoVIP) alunoRepositorio.salvar(alunoVIP);
    }
    
    /**
     * Valida os dados de um aluno.
     * 
     * @param aluno Aluno a ser validado
     * @throws ValidacaoException Se dados inválidos
     */
    private void validarAluno(Aluno aluno) throws ValidacaoException {
        if (aluno == null) {
            throw new ValidacaoException("Aluno não pode ser nulo");
        }
        
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            throw new ValidacaoException("Nome do aluno é obrigatório");
        }
        
        if (aluno.getCpf() == null || aluno.getCpf().trim().isEmpty()) {
            throw new ValidacaoException("CPF do aluno é obrigatório");
        }
        
        if (aluno.getEmail() == null || aluno.getEmail().trim().isEmpty()) {
            throw new ValidacaoException("Email do aluno é obrigatório");
        }
        
        if (!aluno.getEmail().contains("@")) {
            throw new ValidacaoException("Email inválido");
        }
    }
    
    /**
     * Gera um número de matrícula único.
     * 
     * @return Número de matrícula
     */
    private String gerarMatricula() {
        String ano = String.valueOf(java.time.Year.now().getValue());
        int numero = alunoRepositorio.contar() + 1;
        return String.format("%s%04d", ano, numero);
    }
}
