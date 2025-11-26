package com.escolamusica;

import com.escolamusica.exception.*;
import com.escolamusica.model.*;
import com.escolamusica.repository.*;
import com.escolamusica.service.*;
import com.escolamusica.util.FormatadorData;
import com.escolamusica.util.FormatadorMoeda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal da aplicação Escola de Música Nota Máxima.
 * Sistema de gerenciamento completo para escola de música.
 * 
 * @author Escola de Música Nota Máxima
 * @version 1.0
 */
public class EscolaMusicaApp {
    
    // Repositórios
    private static AlunoRepositorio alunoRepositorio;
    private static ProfessorRepositorio professorRepositorio;
    private static CursoRepositorio cursoRepositorio;
    private static MatriculaRepositorio matriculaRepositorio;
    private static EventoRepositorio eventoRepositorio;
    private static PagamentoRepositorio pagamentoRepositorio;
    private static AulaRepositorio aulaRepositorio;
    private static SalaRepositorio salaRepositorio;
    private static AluguelSalaRepositorio aluguelSalaRepositorio;
    
    // Serviços
    private static AlunoService alunoService;
    private static ProfessorService professorService;
    private static CursoService cursoService;
    private static MatriculaService matriculaService;
    private static EventoService eventoService;
    private static PagamentoService pagamentoService;
    private static AulaService aulaService;
    private static SalaService salaService;
    private static AluguelSalaService aluguelSalaService;
    private static DesempenhoService desempenhoService;
    
    private static Scanner scanner;
    
    public static void main(String[] args) {
        inicializarSistema();
        carregarDadosExemplo();
        
        scanner = new Scanner(System.in);
        
        System.out.println("=".repeat(60));
        System.out.println("   BEM-VINDO À ESCOLA DE MÚSICA NOTA MÁXIMA");
        System.out.println("=".repeat(60));
        
        boolean continuar = true;
        while (continuar) {
            try {
                exibirMenuPrincipal();
                int opcao = lerOpcao();
                
                switch (opcao) {
                    case 1 -> menuAlunos();
                    case 2 -> menuProfessores();
                    case 3 -> menuCursos();
                    case 4 -> menuMatriculas();
                    case 5 -> menuEventos();
                    case 6 -> menuPagamentos();
                    case 7 -> menuAulas();
                    case 8 -> menuSalas();
                    case 9 -> menuDesempenho();
                    case 10 -> exibirRelatorios();
                    case 0 -> {
                        System.out.println("\nObrigado por utilizar o sistema!");
                        continuar = false;
                    }
                    default -> System.out.println("\nOpção inválida!");
                }
            } catch (Exception e) {
                System.err.println("\nErro: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    private static void inicializarSistema() {
        // Inicializa repositórios
        alunoRepositorio = new AlunoRepositorio();
        professorRepositorio = new ProfessorRepositorio();
        cursoRepositorio = new CursoRepositorio();
        matriculaRepositorio = new MatriculaRepositorio();
        eventoRepositorio = new EventoRepositorio();
        pagamentoRepositorio = new PagamentoRepositorio();
        aulaRepositorio = new AulaRepositorio();
        salaRepositorio = new SalaRepositorio();
        aluguelSalaRepositorio = new AluguelSalaRepositorio();
        
        // Inicializa serviços
        alunoService = new AlunoService(alunoRepositorio);
        professorService = new ProfessorService(professorRepositorio);
        cursoService = new CursoService(cursoRepositorio);
        salaService = new SalaService(salaRepositorio);
        matriculaService = new MatriculaService(matriculaRepositorio, alunoService, cursoService);
        eventoService = new EventoService(eventoRepositorio, alunoService);
        pagamentoService = new PagamentoService(pagamentoRepositorio, alunoService);
        aulaService = new AulaService(aulaRepositorio, professorService, salaService, cursoService);
        aluguelSalaService = new AluguelSalaService(aluguelSalaRepositorio, aulaRepositorio, salaService, alunoService);
        desempenhoService = new DesempenhoService(alunoService, cursoService);
    }
    
    private static void carregarDadosExemplo() {
        try {
            // Cadastra professores
            Professor prof1 = new Professor();
            prof1.setId("1");
            prof1.setNome("Carlos Silva");
            prof1.setCpf("111.111.111-11");
            prof1.setTelefone("(11) 91111-1111");
            prof1.setEmail("carlos@escolamusica.com");
            prof1.setDataNascimento(LocalDate.of(1980, 5, 15));
            prof1.setEndereco("Rua A, 100");
            prof1.setRegistro("PROF0001");
            prof1.adicionarEspecialidade(TipoInstrumento.PIANO);
            prof1.adicionarEspecialidade(TipoInstrumento.TECLADO);
            professorService.cadastrar(prof1);
            
            Professor prof2 = new Professor();
            prof2.setId("2");
            prof2.setNome("Maria Santos");
            prof2.setCpf("222.222.222-22");
            prof2.setTelefone("(11) 92222-2222");
            prof2.setEmail("maria@escolamusica.com");
            prof2.setDataNascimento(LocalDate.of(1985, 8, 20));
            prof2.setEndereco("Rua B, 200");
            prof2.setRegistro("PROF0002");
            prof2.adicionarEspecialidade(TipoInstrumento.VIOLAO);
            prof2.adicionarEspecialidade(TipoInstrumento.GUITARRA);
            professorService.cadastrar(prof2);
            
            // Cadastra cursos
            Curso curso1 = new Curso();
            curso1.setId("1");
            curso1.setNome("Piano Iniciante");
            curso1.setDescricao("Curso básico de piano");
            curso1.setInstrumento(TipoInstrumento.PIANO);
            curso1.setValorMensal(350.00);
            curso1.setDuracaoMeses(12);
            curso1.setNivel("Iniciante");
            cursoService.cadastrar(curso1);
            
            Curso curso2 = new Curso();
            curso2.setId("2");
            curso2.setNome("Violão Popular");
            curso2.setDescricao("Aprenda violão popular");
            curso2.setInstrumento(TipoInstrumento.VIOLAO);
            curso2.setValorMensal(300.00);
            curso2.setDuracaoMeses(10);
            curso2.setNivel("Iniciante");
            cursoService.cadastrar(curso2);
            
            Curso curso3 = new Curso();
            curso3.setId("3");
            curso3.setNome("Canto Lírico");
            curso3.setDescricao("Técnica vocal clássica");
            curso3.setInstrumento(TipoInstrumento.CANTO);
            curso3.setValorMensal(400.00);
            curso3.setDuracaoMeses(12);
            curso3.setNivel("Intermediário");
            cursoService.cadastrar(curso3);
            
            // Cadastra alunos regulares
            Aluno aluno1 = new Aluno();
            aluno1.setId("1");
            aluno1.setNome("João Pedro");
            aluno1.setCpf("333.333.333-33");
            aluno1.setTelefone("(11) 93333-3333");
            aluno1.setEmail("joao@email.com");
            aluno1.setDataNascimento(LocalDate.of(2005, 3, 10));
            aluno1.setEndereco("Rua C, 300");
            aluno1.setMatricula("20240001");
            alunoService.cadastrar(aluno1);
            
            Aluno aluno2 = new Aluno();
            aluno2.setId("2");
            aluno2.setNome("Ana Carolina");
            aluno2.setCpf("444.444.444-44");
            aluno2.setTelefone("(11) 94444-4444");
            aluno2.setEmail("ana@email.com");
            aluno2.setDataNascimento(LocalDate.of(2008, 7, 22));
            aluno2.setEndereco("Rua D, 400");
            aluno2.setMatricula("20240002");
            alunoService.cadastrar(aluno2);
            
            // Cadastra aluno VIP
            AlunoVIP alunoVIP = new AlunoVIP();
            alunoVIP.setId("3");
            alunoVIP.setNome("Roberto Almeida");
            alunoVIP.setCpf("555.555.555-55");
            alunoVIP.setTelefone("(11) 95555-5555");
            alunoVIP.setEmail("roberto@email.com");
            alunoVIP.setDataNascimento(LocalDate.of(2002, 11, 5));
            alunoVIP.setEndereco("Rua E, 500");
            alunoVIP.setMatricula("20240003");
            alunoVIP.setDataInicioVIP(LocalDate.now().minusMonths(6));
            alunoService.cadastrar(alunoVIP);
            
            // Cria matrículas
            matriculaService.matricular("1", "1", TipoAula.INDIVIDUAL);
            matriculaService.matricular("2", "2", TipoAula.GRUPO);
            matriculaService.matricular("3", "3", TipoAula.INDIVIDUAL);
            
            // Cria evento
            Evento evento1 = new Evento();
            evento1.setId("1");
            evento1.setNome("Recital de Fim de Ano");
            evento1.setDescricao("Apresentação dos alunos");
            evento1.setDataHora(LocalDateTime.now().plusMonths(2));
            evento1.setLocal("Auditório Principal");
            evento1.setCapacidadeTotal(100);
            evento1.setVagasReservadasVIP(20);
            evento1.setValorIngresso(0.0);
            evento1.setTipo("Recital");
            eventoService.criar(evento1);
            
            // Cadastra salas
            Sala sala1 = new Sala();
            sala1.setId("1");
            sala1.setNumero("101");
            sala1.setCapacidade(1);
            sala1.setTipo("Sala Individual");
            sala1.setValorHoraAluguel(50.0);
            sala1.setDisponivel(true);
            salaService.cadastrar(sala1);
            
            Sala sala2 = new Sala();
            sala2.setId("2");
            sala2.setNumero("102");
            sala2.setCapacidade(5);
            sala2.setTipo("Sala Grupo");
            sala2.setValorHoraAluguel(80.0);
            sala2.setDisponivel(true);
            salaService.cadastrar(sala2);
            
            Sala sala3 = new Sala();
            sala3.setId("3");
            sala3.setNumero("201");
            sala3.setCapacidade(1);
            sala3.setTipo("Estúdio");
            sala3.setValorHoraAluguel(120.0);
            sala3.setDisponivel(true);
            salaService.cadastrar(sala3);
            
            // Agenda aulas
            LocalDateTime dataAula1 = LocalDateTime.now().plusDays(1).withHour(14).withMinute(0);
            aulaService.agendarAula("1", "1", "1", dataAula1, TipoAula.INDIVIDUAL, 60);
            
            LocalDateTime dataAula2 = LocalDateTime.now().plusDays(2).withHour(15).withMinute(0);
            aulaService.agendarAula("2", "2", "2", dataAula2, TipoAula.GRUPO, 90);
            
            // Registra desempenho
            desempenhoService.registrarDesempenho(
                    "1", "1", 8.5, "Intermediário", 
                    "Ótimo progresso técnico",
                    "Postura, técnica de dedilhado",
                    "Praticar mais escalas");
            
            desempenhoService.registrarDesempenho(
                    "2", "2", 7.0, "Iniciante",
                    "Boa evolução no ritmo",
                    "Coordenação motora",
                    "Leitura de partitura");
            
            System.out.println("\n✓ Dados de exemplo carregados com sucesso!");
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados de exemplo: " + e.getMessage());
        }
    }
    
    private static void exibirMenuPrincipal() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    MENU PRINCIPAL");
        System.out.println("=".repeat(60));
        System.out.println("1. Gerenciar Alunos");
        System.out.println("2. Gerenciar Professores");
        System.out.println("3. Gerenciar Cursos");
        System.out.println("4. Gerenciar Matrículas");
        System.out.println("5. Gerenciar Eventos");
        System.out.println("6. Gerenciar Pagamentos");
        System.out.println("7. Gerenciar Aulas");
        System.out.println("8. Gerenciar Salas e Aluguel");
        System.out.println("9. Registrar Desempenho");
        System.out.println("10. Relatórios");
        System.out.println("0. Sair");
        System.out.println("=".repeat(60));
        System.out.print("Escolha uma opção: ");
    }
    
    // ========== MENU ALUNOS ==========
    
    private static void menuAlunos() {
        System.out.println("\n--- GERENCIAR ALUNOS ---");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Listar Alunos");
        System.out.println("3. Buscar Aluno");
        System.out.println("4. Promover para VIP");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1 -> cadastrarAluno();
            case 2 -> listarAlunos();
            case 3 -> buscarAluno();
            case 4 -> promoverParaVIP();
        }
    }
    
    private static void cadastrarAluno() {
        try {
            System.out.println("\n--- CADASTRAR ALUNO ---");
            scanner.nextLine(); // Limpa buffer
            
            String nome = lerTextoObrigatorio("Nome: ");
            String cpf = lerCPF();
            String telefone = lerTextoObrigatorio("Telefone: ");
            String email = lerEmail();
            LocalDate dataNascimento = lerData("Data de Nascimento (dd/MM/yyyy): ");
            String endereco = lerTextoObrigatorio("Endereço: ");
            boolean isVIP = lerSimNao("É aluno VIP? (S/N): ");
            
            Aluno aluno;
            if (isVIP) {
                aluno = new AlunoVIP();
                ((AlunoVIP) aluno).setDataInicioVIP(LocalDate.now());
            } else {
                aluno = new Aluno();
            }
            
            aluno.setNome(nome);
            aluno.setCpf(cpf);
            aluno.setTelefone(telefone);
            aluno.setEmail(email);
            aluno.setDataNascimento(dataNascimento);
            aluno.setEndereco(endereco);
            
            aluno = alunoService.cadastrar(aluno);
            
            System.out.println("\n✓ Aluno cadastrado com sucesso!");
            System.out.println("Matrícula: " + aluno.getMatricula());
            
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }
    
    private static void listarAlunos() {
        List<Aluno> alunos = alunoService.listarTodos();
        
        System.out.println("\n--- LISTA DE ALUNOS ---");
        System.out.println("Total: " + alunos.size() + " alunos");
        System.out.println("-".repeat(110));
        System.out.printf("%-10s %-15s %-25s %-15s %-30s %-10s%n", 
                "ID", "MATRÍCULA", "NOME", "CPF", "EMAIL", "TIPO");
        System.out.println("-".repeat(110));
        
        for (Aluno aluno : alunos) {
            System.out.printf("%-10s %-15s %-25s %-15s %-30s %-10s%n",
                    aluno.getId(),
                    aluno.getMatricula(),
                    aluno.getNome(),
                    aluno.getCpf(),
                    aluno.getEmail(),
                    aluno.isVIP() ? "VIP" : "Regular");
        }
    }
    
    private static void buscarAluno() {
        try {
            scanner.nextLine(); // Limpa buffer
            System.out.print("\nDigite o nome do aluno: ");
            String nome = scanner.nextLine();
            
            List<Aluno> alunos = alunoService.buscarPorNome(nome);
            
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno encontrado.");
            } else {
                System.out.println("\nAlunos encontrados: " + alunos.size());
                System.out.println("-".repeat(110));
                System.out.printf("%-10s %-15s %-25s %-15s %-30s %-10s%n", 
                        "ID", "MATRÍCULA", "NOME", "CPF", "EMAIL", "TIPO");
                System.out.println("-".repeat(110));
                
                for (Aluno aluno : alunos) {
                    System.out.printf("%-10s %-15s %-25s %-15s %-30s %-10s%n",
                            aluno.getId(),
                            aluno.getMatricula(),
                            aluno.getNome(),
                            aluno.getCpf(),
                            aluno.getEmail(),
                            aluno.isVIP() ? "VIP" : "Regular");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void promoverParaVIP() {
        try {
            scanner.nextLine();
            System.out.print("\nDigite o ID do aluno: ");
            String id = scanner.nextLine();
            
            AlunoVIP alunoVIP = alunoService.promoverParaVIP(id);
            System.out.println("\n✓ Aluno promovido para VIP com sucesso!");
            System.out.println("Desconto VIP: " + alunoVIP.calcularDesconto() + "%");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    // ========== MENU PROFESSORES ==========
    
    private static void menuProfessores() {
        System.out.println("\n--- GERENCIAR PROFESSORES ---");
        System.out.println("1. Cadastrar Professor");
        System.out.println("2. Listar Professores");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1 -> cadastrarProfessor();
            case 2 -> listarProfessores();
        }
    }
    
    private static void cadastrarProfessor() {
        try {
            System.out.println("\n--- CADASTRAR PROFESSOR ---");
            scanner.nextLine();
            
            String nome = lerTextoObrigatorio("Nome: ");
            String cpf = lerCPF();
            String telefone = lerTextoObrigatorio("Telefone: ");
            String email = lerEmail();
            LocalDate dataNascimento = lerData("Data de Nascimento (dd/MM/yyyy): ");
            String endereco = lerTextoObrigatorio("Endereço: ");
            
            Professor professor = new Professor();
            professor.setNome(nome);
            professor.setCpf(cpf);
            professor.setTelefone(telefone);
            professor.setEmail(email);
            professor.setDataNascimento(dataNascimento);
            professor.setEndereco(endereco);
            
            System.out.println("\nEspecialidades disponíveis:");
            TipoInstrumento[] instrumentos = TipoInstrumento.values();
            for (int i = 0; i < instrumentos.length; i++) {
                System.out.println((i + 1) + ". " + instrumentos[i]);
            }
            
            boolean especialidadesValidas = false;
            while (!especialidadesValidas) {
                try {
                    System.out.print("Escolha especialidades (separadas por vírgula): ");
                    String[] escolhas = scanner.nextLine().split(",");
                    
                    for (String escolha : escolhas) {
                        int index = Integer.parseInt(escolha.trim()) - 1;
                        if (index >= 0 && index < instrumentos.length) {
                            professor.adicionarEspecialidade(instrumentos[index]);
                        } else {
                            throw new IllegalArgumentException("Opção inválida: " + (index + 1));
                        }
                    }
                    
                    if (professor.getEspecialidades().isEmpty()) {
                        System.out.println("É necessário escolher pelo menos uma especialidade. Tente novamente.");
                    } else {
                        especialidadesValidas = true;
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida: " + e.getMessage() + " Tente novamente.");
                    professor.getEspecialidades().clear(); // Limpa especialidades inválidas
                }
            }
            
            professor = professorService.cadastrar(professor);
            System.out.println("\n✓ Professor cadastrado com sucesso!");
            System.out.println("Registro: " + professor.getRegistro());
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void listarProfessores() {
        List<Professor> professores = professorService.listarTodos();
        
        System.out.println("\n--- LISTA DE PROFESSORES ---");
        System.out.println("Total: " + professores.size());
        System.out.println("-".repeat(120));
        System.out.printf("%-15s %-10s %-25s %-15s %-35s %-20s%n", 
                "REGISTRO", "ID", "NOME", "CPF", "EMAIL", "ESPECIALIDADES");
        System.out.println("-".repeat(120));
        
        for (Professor prof : professores) {
            String especialidades = prof.getEspecialidades().stream()
                    .map(TipoInstrumento::toString)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Nenhuma");
                    
            System.out.printf("%-15s %-10s %-25s %-15s %-35s %-20s%n",
                    prof.getRegistro(),
                    prof.getId(),
                    prof.getNome(),
                    prof.getCpf(),
                    prof.getEmail(),
                    especialidades);
        }
        System.out.println("-".repeat(120));
    }
    
    // ========== MENU CURSOS ==========
    
    private static void menuCursos() {
        System.out.println("\n--- GERENCIAR CURSOS ---");
        System.out.println("1. Cadastrar Curso");
        System.out.println("2. Listar Cursos");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1 -> cadastrarCurso();
            case 2 -> listarCursos();
        }
    }
    
    private static void cadastrarCurso() {
        try {
            System.out.println("\n--- CADASTRAR CURSO ---");
            scanner.nextLine();
            
            String nome = lerTextoObrigatorio("Nome: ");
            String descricao = lerTextoObrigatorio("Descrição: ");
            
            System.out.println("\nInstrumentos:");
            TipoInstrumento[] instrumentos = TipoInstrumento.values();
            for (int i = 0; i < instrumentos.length; i++) {
                System.out.println((i + 1) + ". " + instrumentos[i]);
            }
            
            TipoInstrumento instrumento = null;
            while (instrumento == null) {
                try {
                    int instrIndex = lerInteiroPositivo("Escolha: ") - 1;
                    if (instrIndex >= 0 && instrIndex < instrumentos.length) {
                        instrumento = instrumentos[instrIndex];
                    } else {
                        System.out.println("Opção inválida. Escolha entre 1 e " + instrumentos.length + ". Tente novamente.");
                    }
                } catch (Exception e) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
            
            double valorMensal = lerDecimalPositivo("Valor Mensal (R$): ");
            int duracaoMeses = lerInteiroPositivo("Duração (meses): ");
            String nivel = lerTextoObrigatorio("Nível (Iniciante/Intermediário/Avançado): ");
            
            Curso curso = new Curso();
            curso.setNome(nome);
            curso.setDescricao(descricao);
            curso.setInstrumento(instrumento);
            curso.setValorMensal(valorMensal);
            curso.setDuracaoMeses(duracaoMeses);
            curso.setNivel(nivel);
            
            curso = cursoService.cadastrar(curso);
            System.out.println("\n✓ Curso cadastrado com sucesso!");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void listarCursos() {
        List<Curso> cursos = cursoService.listarTodos();
        
        System.out.println("\n--- LISTA DE CURSOS ---");
        System.out.println("Total: " + cursos.size());
        System.out.println("-".repeat(100));
        
        for (Curso curso : cursos) {
            System.out.println(curso);
            System.out.println("-".repeat(100));
        }
    }
    
    // ========== MENU MATRÍCULAS ==========
    
    private static void menuMatriculas() {
        System.out.println("\n--- GERENCIAR MATRÍCULAS ---");
        System.out.println("1. Nova Matrícula");
        System.out.println("2. Listar Matrículas");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1 -> novaMatricula();
            case 2 -> listarMatriculas();
        }
    }
    
    private static void novaMatricula() {
        try {
            scanner.nextLine();
            System.out.println("\n--- NOVA MATRÍCULA ---");
            
            System.out.print("ID do Aluno: ");
            String alunoId = scanner.nextLine();
            
            System.out.print("ID do Curso: ");
            String cursoId = scanner.nextLine();
            
            System.out.println("\nTipo de Aula:");
            System.out.println("1. INDIVIDUAL");
            System.out.println("2. GRUPO");
            System.out.println("3. TURMA");
            System.out.print("Escolha: ");
            int tipoOpcao = lerOpcao();
            
            TipoAula tipoAula = switch (tipoOpcao) {
                case 1 -> TipoAula.INDIVIDUAL;
                case 2 -> TipoAula.GRUPO;
                case 3 -> TipoAula.TURMA;
                default -> TipoAula.INDIVIDUAL;
            };
            
            Matricula matricula = matriculaService.matricular(alunoId, cursoId, tipoAula);
            System.out.println("\n✓ Matrícula realizada com sucesso!");
            System.out.println("Valor mensal: " + FormatadorMoeda.formatar(matricula.calcularValorMensal()));
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void listarMatriculas() {
        List<Matricula> matriculas = matriculaService.listarAtivas();
        
        System.out.println("\n--- MATRÍCULAS ATIVAS ---");
        System.out.println("Total: " + matriculas.size());
        System.out.println("-".repeat(100));
        
        for (Matricula mat : matriculas) {
            System.out.println(mat);
            System.out.println("-".repeat(100));
        }
    }
    
    // ========== MENU EVENTOS ==========
    
    private static void menuEventos() {
        System.out.println("\n--- GERENCIAR EVENTOS ---");
        System.out.println("1. Criar Evento");
        System.out.println("2. Listar Eventos Futuros");
        System.out.println("3. Inscrever Aluno");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1 -> criarEvento();
            case 2 -> listarEventosFuturos();
            case 3 -> inscreverAlunoEvento();
        }
    }
    
    private static void criarEvento() {
        try {
            System.out.println("\n--- CRIAR EVENTO ---");
            scanner.nextLine();
            
            String nome = lerTextoObrigatorio("Nome: ");
            String descricao = lerTextoObrigatorio("Descrição: ");
            LocalDateTime dataHora = lerDataHora("Data/Hora (dd/MM/yyyy HH:mm): ");
            String local = lerTextoObrigatorio("Local: ");
            int capacidade = lerInteiroPositivo("Capacidade Total: ");
            int vagasVIP = lerInteiroPositivo("Vagas VIP: ");
            double valor = lerDecimalPositivo("Valor do Ingresso (R$): ");
            String tipo = lerTextoObrigatorio("Tipo: ");
            
            Evento evento = new Evento();
            evento.setNome(nome);
            evento.setDescricao(descricao);
            evento.setDataHora(dataHora);
            evento.setLocal(local);
            evento.setCapacidadeTotal(capacidade);
            evento.setVagasReservadasVIP(vagasVIP);
            evento.setValorIngresso(valor);
            evento.setTipo(tipo);
            
            evento = eventoService.criar(evento);
            System.out.println("\n✓ Evento criado com sucesso!");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void listarEventosFuturos() {
        List<Evento> eventos = eventoService.listarFuturos();
        
        System.out.println("\n--- EVENTOS FUTUROS ---");
        System.out.println("Total: " + eventos.size());
        System.out.println("-".repeat(100));
        
        for (Evento evento : eventos) {
            System.out.println(evento);
            System.out.println("-".repeat(100));
        }
    }
    
    private static void inscreverAlunoEvento() {
        try {
            scanner.nextLine();
            System.out.print("\nID do Evento: ");
            String eventoId = scanner.nextLine();
            
            System.out.print("ID do Aluno: ");
            String alunoId = scanner.nextLine();
            
            eventoService.inscreverAluno(eventoId, alunoId);
            System.out.println("\n✓ Aluno inscrito com sucesso!");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    // ========== MENU PAGAMENTOS ==========
    
    private static void menuPagamentos() {
        System.out.println("\n--- GERENCIAR PAGAMENTOS ---");
        System.out.println("1. Gerar Pagamento");
        System.out.println("2. Registrar Pagamento");
        System.out.println("3. Listar Pendentes");
        System.out.println("4. Listar Atrasados");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1 -> gerarPagamento();
            case 2 -> registrarPagamento();
            case 3 -> listarPagamentosPendentes();
            case 4 -> listarPagamentosAtrasados();
        }
    }
    
    private static void gerarPagamento() {
        try {
            scanner.nextLine();
            System.out.println("\n--- GERAR PAGAMENTO ---");
            
            System.out.print("ID do Aluno: ");
            String alunoId = scanner.nextLine();
            
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            
            System.out.print("Valor (R$): ");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print("Data Vencimento (dd/MM/yyyy): ");
            LocalDate vencimento = FormatadorData.parseData(scanner.nextLine());
            
            Pagamento pag = pagamentoService.gerarPagamento(alunoId, descricao, valor, vencimento);
            System.out.println("\n✓ Pagamento gerado com sucesso!");
            System.out.println("ID: " + pag.getId());
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void registrarPagamento() {
        try {
            scanner.nextLine();
            System.out.print("\nID do Pagamento: ");
            String id = scanner.nextLine();
            
            System.out.print("Forma de Pagamento: ");
            String forma = scanner.nextLine();
            
            pagamentoService.registrarPagamento(id, forma);
            System.out.println("\n✓ Pagamento registrado com sucesso!");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void listarPagamentosPendentes() {
        List<Pagamento> pagamentos = pagamentoService.listarPendentes();
        
        System.out.println("\n--- PAGAMENTOS PENDENTES ---");
        System.out.println("Total: " + pagamentos.size());
        System.out.println("-".repeat(100));
        
        for (Pagamento pag : pagamentos) {
            System.out.println(pag);
            System.out.println("-".repeat(100));
        }
    }
    
    private static void listarPagamentosAtrasados() {
        List<Pagamento> pagamentos = pagamentoService.listarAtrasados();
        
        System.out.println("\n--- PAGAMENTOS ATRASADOS ---");
        System.out.println("Total: " + pagamentos.size());
        System.out.println("-".repeat(100));
        
        for (Pagamento pag : pagamentos) {
            System.out.println(pag);
            System.out.println("Multa: " + FormatadorMoeda.formatar(pag.calcularMulta()));
            System.out.println("Total com Multa: " + FormatadorMoeda.formatar(pag.calcularValorTotal()));
            System.out.println("-".repeat(100));
        }
    }
    
    // ========== MENU AULAS ==========
    
    private static void menuAulas() {
        System.out.println("\n--- GERENCIAR AULAS ---");
        System.out.println("1. Agendar Aula");
        System.out.println("2. Listar Aulas");
        System.out.println("3. Marcar Aula como Realizada");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1 -> agendarAula();
            case 2 -> listarAulas();
            case 3 -> marcarAulaRealizada();
        }
    }
    
    private static void agendarAula() {
        try {
            scanner.nextLine();
            System.out.println("\n--- AGENDAR AULA ---");
            
            String cursoId = lerTextoObrigatorio("ID do Curso: ");
            String professorId = lerTextoObrigatorio("ID ou Registro do Professor (ex: PROF0001): ");
            String salaId = lerTextoObrigatorio("ID da Sala: ");
            LocalDateTime dataHora = lerDataHora("Data/Hora (dd/MM/yyyy HH:mm): ");
            
            System.out.println("\nTipo de Aula:");
            System.out.println("1. INDIVIDUAL");
            System.out.println("2. GRUPO");
            System.out.println("3. TURMA");
            
            TipoAula tipoAula = null;
            while (tipoAula == null) {
                int tipoOpcao = lerInteiroPositivo("Escolha: ");
                tipoAula = switch (tipoOpcao) {
                    case 1 -> TipoAula.INDIVIDUAL;
                    case 2 -> TipoAula.GRUPO;
                    case 3 -> TipoAula.TURMA;
                    default -> {
                        System.out.println("Opção inválida. Escolha entre 1 e 3. Tente novamente.");
                        yield null;
                    }
                };
            }
            
            int duracao = lerInteiroPositivo("Duração (minutos): ");
            
            com.escolamusica.model.Aula aula = aulaService.agendarAula(
                    cursoId, professorId, salaId, dataHora, tipoAula, duracao);
            
            System.out.println("\n✓ Aula agendada com sucesso!");
            System.out.println("ID: " + aula.getId());
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void listarAulas() {
        List<com.escolamusica.model.Aula> aulas = aulaService.listarTodas();
        
        System.out.println("\n--- LISTA DE AULAS ---");
        System.out.println("Total: " + aulas.size());
        System.out.println("-".repeat(100));
        
        for (com.escolamusica.model.Aula aula : aulas) {
            System.out.println(aula);
            System.out.println("-".repeat(100));
        }
    }
    
    private static void marcarAulaRealizada() {
        try {
            scanner.nextLine();
            System.out.print("\nID da Aula: ");
            String id = scanner.nextLine();
            
            aulaService.marcarComoRealizada(id);
            System.out.println("\n✓ Aula marcada como realizada!");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    // ========== MENU SALAS ==========
    
    private static void menuSalas() {
        System.out.println("\n--- GERENCIAR SALAS E ALUGUEL ---");
        System.out.println("1. Cadastrar Sala");
        System.out.println("2. Listar Salas");
        System.out.println("3. Verificar Disponibilidade");
        System.out.println("4. Alugar Sala");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1 -> cadastrarSala();
            case 2 -> listarSalas();
            case 3 -> verificarDisponibilidadeSala();
            case 4 -> alugarSala();
        }
    }
    
    private static void cadastrarSala() {
        try {
            scanner.nextLine();
            System.out.println("\n--- CADASTRAR SALA ---");
            
            String numero = lerTextoObrigatorio("Número: ");
            int capacidade = lerInteiroPositivo("Capacidade: ");
            String tipo = lerTextoObrigatorio("Tipo (Sala/Estúdio): ");
            double valor = lerDecimalPositivo("Valor Hora Aluguel (R$): ");
            
            Sala sala = new Sala();
            sala.setNumero(numero);
            sala.setCapacidade(capacidade);
            sala.setTipo(tipo);
            sala.setValorHoraAluguel(valor);
            sala.setDisponivel(true);
            
            sala = salaService.cadastrar(sala);
            System.out.println("\n✓ Sala cadastrada com sucesso!");
            System.out.println("ID: " + sala.getId());
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void listarSalas() {
        List<Sala> salas = salaService.listarTodas();
        
        System.out.println("\n--- LISTA DE SALAS ---");
        System.out.println("Total: " + salas.size());
        System.out.println("-".repeat(100));
        
        for (Sala sala : salas) {
            System.out.println(sala);
            System.out.println("-".repeat(100));
        }
    }
    
    private static void verificarDisponibilidadeSala() {
        try {
            scanner.nextLine();
            System.out.print("\nID da Sala: ");
            String salaId = scanner.nextLine();
            
            System.out.print("Data (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            LocalDateTime data = FormatadorData.parseData(dataStr).atStartOfDay();
            
            String disponibilidade = aluguelSalaService.listarHorariosDisponiveis(salaId, data);
            System.out.println("\n" + disponibilidade);
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void alugarSala() {
        try {
            scanner.nextLine();
            System.out.println("\n--- ALUGAR SALA ---");
            
            String alunoId = lerTextoObrigatorio("ID do Aluno: ");
            String salaId = lerTextoObrigatorio("ID da Sala: ");
            LocalDateTime inicio = lerDataHora("Data/Hora Início (dd/MM/yyyy HH:mm): ");
            LocalDateTime fim = lerDataHora("Data/Hora Fim (dd/MM/yyyy HH:mm): ");
            String finalidade = lerTextoObrigatorio("Finalidade: ");
            
            AluguelSala aluguel = aluguelSalaService.alugarSala(
                    alunoId, salaId, inicio, fim, finalidade);
            
            System.out.println("\n✓ Sala alugada com sucesso!");
            System.out.println("Valor: " + FormatadorMoeda.formatar(aluguel.calcularValorTotal()));
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    // ========== MENU DESEMPENHO ==========
    
    private static void menuDesempenho() {
        System.out.println("\n--- REGISTRAR DESEMPENHO ---");
        System.out.println("1. Registrar Avaliação");
        System.out.println("2. Relatório de Progresso");
        System.out.println("3. Listar Avaliações de Aluno");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1 -> registrarAvaliacao();
            case 2 -> relatorioProgresso();
            case 3 -> listarAvaliacoesAluno();
        }
    }
    
    private static void registrarAvaliacao() {
        try {
            scanner.nextLine();
            System.out.println("\n--- REGISTRAR AVALIAÇÃO ---");
            
            String alunoId = lerTextoObrigatorio("ID do Aluno: ");
            String cursoId = lerTextoObrigatorio("ID do Curso: ");
            double nota = lerNota("Nota (0-10): ");
            String nivel = lerTextoObrigatorio("Nível (Iniciante/Intermediário/Avançado): ");
            String observacoes = lerTextoObrigatorio("Observações: ");
            String pontosFortes = lerTextoObrigatorio("Pontos Fortes: ");
            String pontosAMelhorar = lerTextoObrigatorio("Pontos a Melhorar: ");
            
            Desempenho desempenho = desempenhoService.registrarDesempenho(
                    alunoId, cursoId, nota, nivel, observacoes, 
                    pontosFortes, pontosAMelhorar);
            
            System.out.println("\n✓ Avaliação registrada com sucesso!");
            System.out.println("Conceito: " + desempenho.getConceito());
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void relatorioProgresso() {
        try {
            scanner.nextLine();
            System.out.print("\nID do Aluno: ");
            String alunoId = scanner.nextLine();
            
            String relatorio = desempenhoService.gerarRelatorioProgresso(alunoId);
            System.out.println("\n" + relatorio);
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void listarAvaliacoesAluno() {
        try {
            scanner.nextLine();
            System.out.print("\nID do Aluno: ");
            String alunoId = scanner.nextLine();
            
            List<Desempenho> avaliacoes = desempenhoService.listarPorAluno(alunoId);
            
            System.out.println("\n--- AVALIAÇÕES DO ALUNO ---");
            System.out.println("Total: " + avaliacoes.size());
            System.out.println("-".repeat(100));
            
            for (Desempenho desemp : avaliacoes) {
                System.out.println(desemp);
                System.out.println("-".repeat(100));
            }
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    // ========== RELATÓRIOS ==========
    
    private static void exibirRelatorios() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    RELATÓRIOS");
        System.out.println("=".repeat(60));
        
        // Estatísticas gerais
        System.out.println("\n📊 ESTATÍSTICAS GERAIS");
        System.out.println("-".repeat(60));
        System.out.println("Total de Alunos: " + alunoService.listarTodos().size());
        System.out.println("Alunos VIP: " + alunoService.listarVIPs().size());
        System.out.println("Total de Professores: " + professorService.listarTodos().size());
        System.out.println("Total de Cursos: " + cursoService.listarTodos().size());
        System.out.println("Matrículas Ativas: " + matriculaService.listarAtivas().size());
        System.out.println("Eventos Futuros: " + eventoService.listarFuturos().size());
        System.out.println("Pagamentos Pendentes: " + pagamentoService.listarPendentes().size());
        System.out.println("Pagamentos Atrasados: " + pagamentoService.listarAtrasados().size());
        
        // Cursos mais populares
        System.out.println("\n📈 CURSOS MAIS POPULARES");
        System.out.println("-".repeat(60));
        List<Curso> cursos = cursoService.listarAtivos();
        for (Curso curso : cursos) {
            long qtdMatriculas = matriculaService.listarPorCurso(curso.getId()).size();
            System.out.printf("%-30s: %d matrículas%n", curso.getNome(), qtdMatriculas);
        }
    }
    
    // ========== UTILIDADES ==========
    
    private static int lerOpcao() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Limpa buffer
            return -1;
        }
    }
    
    /**
     * Lê um texto obrigatório, repetindo até o usuário fornecer um valor válido
     */
    private static String lerTextoObrigatorio(String mensagem) {
        String valor;
        while (true) {
            System.out.print(mensagem);
            valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("Este campo é obrigatório. Tente novamente.");
        }
    }
    
    /**
     * Lê um CPF válido, repetindo até o usuário fornecer um CPF correto
     */
    private static String lerCPF() {
        while (true) {
            System.out.print("CPF: ");
            String cpf = scanner.nextLine().trim();
            try {
                if (com.escolamusica.util.ValidadorCPF.validar(cpf)) {
                    return cpf;
                }
            } catch (Exception e) {
                System.out.println("CPF inválido: " + e.getMessage() + " Tente novamente.");
            }
        }
    }
    
    /**
     * Lê um email válido, repetindo até o usuário fornecer um email correto
     */
    private static String lerEmail() {
        while (true) {
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            try {
                if (com.escolamusica.util.ValidadorEmail.validar(email)) {
                    return email;
                }
            } catch (Exception e) {
                System.out.println("Email inválido: " + e.getMessage() + " Tente novamente.");
            }
        }
    }
    
    /**
     * Lê uma data válida, repetindo até o usuário fornecer uma data correta
     */
    private static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String dataStr = scanner.nextLine().trim();
            try {
                return FormatadorData.parseData(dataStr);
            } catch (Exception e) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy. Tente novamente.");
            }
        }
    }
    
    /**
     * Lê uma data e hora válida, repetindo até o usuário fornecer um valor correto
     */
    private static LocalDateTime lerDataHora(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String dataHoraStr = scanner.nextLine().trim();
            try {
                return FormatadorData.parseDataHora(dataHoraStr);
            } catch (Exception e) {
                System.out.println("Data/hora inválida. Use o formato dd/MM/yyyy HH:mm. Tente novamente.");
            }
        }
    }
    
    /**
     * Lê um número inteiro válido, repetindo até o usuário fornecer um número correto
     */
    private static int lerInteiroPositivo(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valorStr = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(valorStr);
                if (valor > 0) {
                    return valor;
                }
                System.out.println("O valor deve ser maior que zero. Tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro. Tente novamente.");
            }
        }
    }
    
    /**
     * Lê um número decimal válido, repetindo até o usuário fornecer um número correto
     */
    private static double lerDecimalPositivo(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valorStr = scanner.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble(valorStr);
                if (valor > 0) {
                    return valor;
                }
                System.out.println("O valor deve ser maior que zero. Tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número decimal. Tente novamente.");
            }
        }
    }
    
    /**
     * Lê uma nota (0-10), repetindo até o usuário fornecer um valor válido
     */
    private static double lerNota(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valorStr = scanner.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble(valorStr);
                if (valor >= 0 && valor <= 10) {
                    return valor;
                }
                System.out.println("A nota deve estar entre 0 e 10. Tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número decimal entre 0 e 10. Tente novamente.");
            }
        }
    }
    
    /**
     * Lê uma confirmação S/N, repetindo até o usuário fornecer uma resposta válida
     */
    private static boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String resposta = scanner.nextLine().trim().toUpperCase();
            if (resposta.equals("S") || resposta.equals("SIM")) {
                return true;
            } else if (resposta.equals("N") || resposta.equals("NAO") || resposta.equals("NÃO")) {
                return false;
            }
            System.out.println("Resposta inválida. Digite S para Sim ou N para Não. Tente novamente.");
        }
    }
}
