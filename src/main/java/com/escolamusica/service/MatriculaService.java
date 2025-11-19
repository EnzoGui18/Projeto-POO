package com.escolamusica.service;

import com.escolamusica.exception.EntidadeNaoEncontradaException;
import com.escolamusica.exception.NegocioException;
import com.escolamusica.exception.ValidacaoException;
import com.escolamusica.model.*;
import com.escolamusica.repository.MatriculaRepositorio;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Serviço de gerenciamento de matrículas.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class MatriculaService {
    
    private MatriculaRepositorio matriculaRepositorio;
    private AlunoService alunoService;
    private CursoService cursoService;
    
    public MatriculaService(MatriculaRepositorio matriculaRepositorio,
                           AlunoService alunoService,
                           CursoService cursoService) {
        this.matriculaRepositorio = matriculaRepositorio;
        this.alunoService = alunoService;
        this.cursoService = cursoService;
    }
    
    /**
     * Cria uma nova matrícula.
     * 
     * @param alunoId ID do aluno
     * @param cursoId ID do curso
     * @param tipoAula Tipo de aula
     * @return Matrícula criada
     * @throws EntidadeNaoEncontradaException Se aluno ou curso não encontrado
     * @throws ValidacaoException Se dados inválidos
     * @throws NegocioException Se aluno já matriculado no curso
     */
    public Matricula matricular(String alunoId, String cursoId, TipoAula tipoAula) 
            throws EntidadeNaoEncontradaException, ValidacaoException, NegocioException {
        
        Aluno aluno = alunoService.buscarPorId(alunoId);
        Curso curso = cursoService.buscarPorId(cursoId);
        
        // Verifica se já está matriculado neste curso
        List<Matricula> matriculasAluno = matriculaRepositorio.buscarPorAluno(alunoId);
        boolean jaMatriculado = matriculasAluno.stream()
                .anyMatch(m -> m.getCurso().getId().equals(cursoId) && 
                             m.getStatus() == StatusMatricula.ATIVA);
        
        if (jaMatriculado) {
            throw new NegocioException("Aluno já está matriculado neste curso");
        }
        
        // Cria matrícula
        Matricula matricula = new Matricula();
        matricula.setId(UUID.randomUUID().toString());
        matricula.setAluno(aluno);
        matricula.setCurso(curso);
        matricula.setTipoAula(tipoAula);
        matricula.setDataMatricula(LocalDate.now());
        matricula.setStatus(StatusMatricula.ATIVA);
        
        return matriculaRepositorio.salvar(matricula);
    }
    
    /**
     * Busca matrícula por ID.
     * 
     * @param id ID da matrícula
     * @return Matrícula encontrada
     * @throws EntidadeNaoEncontradaException Se não encontrada
     */
    public Matricula buscarPorId(String id) throws EntidadeNaoEncontradaException {
        return matriculaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Matrícula", id));
    }
    
    /**
     * Lista matrículas de um aluno.
     * 
     * @param alunoId ID do aluno
     * @return Lista de matrículas
     */
    public List<Matricula> listarPorAluno(String alunoId) {
        return matriculaRepositorio.buscarPorAluno(alunoId);
    }
    
    /**
     * Lista matrículas de um curso.
     * 
     * @param cursoId ID do curso
     * @return Lista de matrículas
     */
    public List<Matricula> listarPorCurso(String cursoId) {
        return matriculaRepositorio.buscarPorCurso(cursoId);
    }
    
    /**
     * Suspende uma matrícula.
     * 
     * @param id ID da matrícula
     * @return Matrícula suspensa
     * @throws EntidadeNaoEncontradaException Se não encontrada
     */
    public Matricula suspender(String id) throws EntidadeNaoEncontradaException {
        Matricula matricula = buscarPorId(id);
        matricula.suspender();
        return matriculaRepositorio.salvar(matricula);
    }
    
    /**
     * Reativa uma matrícula suspensa.
     * 
     * @param id ID da matrícula
     * @return Matrícula reativada
     * @throws EntidadeNaoEncontradaException Se não encontrada
     */
    public Matricula reativar(String id) throws EntidadeNaoEncontradaException {
        Matricula matricula = buscarPorId(id);
        matricula.reativar();
        return matriculaRepositorio.salvar(matricula);
    }
    
    /**
     * Cancela uma matrícula.
     * 
     * @param id ID da matrícula
     * @return Matrícula cancelada
     * @throws EntidadeNaoEncontradaException Se não encontrada
     */
    public Matricula cancelar(String id) throws EntidadeNaoEncontradaException {
        Matricula matricula = buscarPorId(id);
        matricula.cancelar();
        return matriculaRepositorio.salvar(matricula);
    }
    
    /**
     * Conclui uma matrícula.
     * 
     * @param id ID da matrícula
     * @return Matrícula concluída
     * @throws EntidadeNaoEncontradaException Se não encontrada
     */
    public Matricula concluir(String id) throws EntidadeNaoEncontradaException {
        Matricula matricula = buscarPorId(id);
        matricula.concluir();
        return matriculaRepositorio.salvar(matricula);
    }
    
    /**
     * Lista todas as matrículas ativas.
     * 
     * @return Lista de matrículas ativas
     */
    public List<Matricula> listarAtivas() {
        return matriculaRepositorio.listarAtivas();
    }
}
