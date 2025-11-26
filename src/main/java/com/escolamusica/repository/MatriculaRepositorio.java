package com.escolamusica.repository;

import com.escolamusica.model.Matricula;
import com.escolamusica.model.StatusMatricula;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciar matrículas.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class MatriculaRepositorio extends RepositorioGenerico<Matricula, String> {
    
    @Override
    protected String extrairId(Matricula entidade) {
        return entidade.getId();
    }
    
    /**
     * Busca matrículas de um aluno específico.
     * 
     * @param alunoId ID do aluno
     * @return Lista de matrículas do aluno
     */
    public List<Matricula> buscarPorAluno(String alunoId) {
        return dados.values().stream()
                .filter(mat -> mat.getAluno().getId().equals(alunoId))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca matrículas de um curso específico.
     * 
     * @param cursoId ID do curso
     * @return Lista de matrículas do curso
     */
    public List<Matricula> buscarPorCurso(String cursoId) {
        return dados.values().stream()
                .filter(mat -> mat.getCurso().getId().equals(cursoId))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca matrículas por status.
     * 
     * @param status Status da matrícula
     * @return Lista de matrículas com o status
     */
    public List<Matricula> buscarPorStatus(StatusMatricula status) {
        return dados.values().stream()
                .filter(mat -> mat.getStatus() == status)
                .collect(Collectors.toList());
    }
    
    /**
     * Lista matrículas ativas.
     * 
     * @return Lista de matrículas ativas
     */
    public List<Matricula> listarAtivas() {
        return buscarPorStatus(StatusMatricula.ATIVA);
    }
}
