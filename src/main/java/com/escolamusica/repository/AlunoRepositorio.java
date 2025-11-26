package com.escolamusica.repository;

import com.escolamusica.model.Aluno;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciar alunos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class AlunoRepositorio extends RepositorioGenerico<Aluno, String> {
    
    @Override
    protected String extrairId(Aluno entidade) {
        return entidade.getId();
    }
    
    /**
     * Busca alunos por nome (busca parcial, case-insensitive).
     * 
     * @param nome Nome ou parte do nome
     * @return Lista de alunos encontrados
     */
    public List<Aluno> buscarPorNome(String nome) {
        return dados.values().stream()
                .filter(aluno -> aluno.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca aluno por CPF.
     * 
     * @param cpf CPF do aluno
     * @return Aluno encontrado ou null
     */
    public Aluno buscarPorCpf(String cpf) {
        return dados.values().stream()
                .filter(aluno -> aluno.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Busca aluno por matrícula.
     * 
     * @param matricula Número de matrícula
     * @return Aluno encontrado ou null
     */
    public Aluno buscarPorMatricula(String matricula) {
        return dados.values().stream()
                .filter(aluno -> aluno.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Lista apenas alunos VIP.
     * 
     * @return Lista de alunos VIP
     */
    public List<Aluno> listarAlunosVIP() {
        return dados.values().stream()
                .filter(Aluno::isVIP)
                .collect(Collectors.toList());
    }
    
    /**
     * Lista apenas alunos regulares (não VIP).
     * 
     * @return Lista de alunos regulares
     */
    public List<Aluno> listarAlunosRegulares() {
        return dados.values().stream()
                .filter(aluno -> !aluno.isVIP())
                .collect(Collectors.toList());
    }
    
    /**
     * Lista alunos ativos.
     * 
     * @return Lista de alunos ativos
     */
    public List<Aluno> listarAtivos() {
        return dados.values().stream()
                .filter(Aluno::isAtivo)
                .collect(Collectors.toList());
    }
}
