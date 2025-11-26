package com.escolamusica.repository;

import com.escolamusica.model.Professor;
import com.escolamusica.model.TipoInstrumento;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para gerenciar professores.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class ProfessorRepositorio extends RepositorioGenerico<Professor, String> {
    
    @Override
    protected String extrairId(Professor entidade) {
        return entidade.getId();
    }
    
    /**
     * Busca professores por nome.
     * 
     * @param nome Nome ou parte do nome
     * @return Lista de professores encontrados
     */
    public List<Professor> buscarPorNome(String nome) {
        return dados.values().stream()
                .filter(prof -> prof.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca professores por especialidade (instrumento).
     * 
     * @param instrumento Tipo de instrumento
     * @return Lista de professores especializados
     */
    public List<Professor> buscarPorEspecialidade(TipoInstrumento instrumento) {
        return dados.values().stream()
                .filter(prof -> prof.isEspecialistaEm(instrumento))
                .collect(Collectors.toList());
    }
    
    /**
     * Lista professores disponíveis.
     * 
     * @return Lista de professores disponíveis
     */
    public List<Professor> listarDisponiveis() {
        return dados.values().stream()
                .filter(Professor::isDisponivel)
                .collect(Collectors.toList());
    }
    
    /**
     * Busca professor por registro.
     * 
     * @param registro Registro do professor
     * @return Professor encontrado ou null
     */
    public Professor buscarPorRegistro(String registro) {
        return dados.values().stream()
                .filter(prof -> prof.getRegistro().equals(registro))
                .findFirst()
                .orElse(null);
    }
}
