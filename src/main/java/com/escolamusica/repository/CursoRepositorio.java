package com.escolamusica.repository;

import com.escolamusica.model.Curso;
import com.escolamusica.model.TipoInstrumento;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciar cursos.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class CursoRepositorio extends RepositorioGenerico<Curso, String> {
    
    @Override
    protected String extrairId(Curso entidade) {
        return entidade.getId();
    }
    
    /**
     * Busca cursos por instrumento.
     * 
     * @param instrumento Tipo de instrumento
     * @return Lista de cursos encontrados
     */
    public List<Curso> buscarPorInstrumento(TipoInstrumento instrumento) {
        return dados.values().stream()
                .filter(curso -> curso.getInstrumento() == instrumento)
                .collect(Collectors.toList());
    }
    
    /**
     * Busca cursos por nível.
     * 
     * @param nivel Nível do curso
     * @return Lista de cursos encontrados
     */
    public List<Curso> buscarPorNivel(String nivel) {
        return dados.values().stream()
                .filter(curso -> curso.getNivel().equalsIgnoreCase(nivel))
                .collect(Collectors.toList());
    }
    
    /**
     * Lista cursos ativos.
     * 
     * @return Lista de cursos ativos
     */
    public List<Curso> listarAtivos() {
        return dados.values().stream()
                .filter(Curso::isAtivo)
                .collect(Collectors.toList());
    }
}
