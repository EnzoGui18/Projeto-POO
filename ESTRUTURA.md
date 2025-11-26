# 🗂️ Estrutura Completa do Projeto

```
POO_P2/
│
├── 📄 README.md                    # Documentação principal
├── 📄 COMPILAR.md                  # Guia de compilação
├── 📄 RESUMO_PROJETO.md            # Estatísticas e resumo
├── 📄 S3b - Escola de Música.pdf   # Documento de requisitos
│
├── 🔧 run.sh                       # Script para Linux/Mac
├── 🔧 run.bat                      # Script para Windows
│
└── 📁 src/
    └── 📁 main/
        └── 📁 java/
            └── 📁 com/
                └── 📁 escolamusica/
                    │
                    ├── 🚀 EscolaMusicaApp.java    # APLICAÇÃO PRINCIPAL
                    │   └── [Menu interativo com todas as funcionalidades]
                    │
                    ├── 📁 model/                   # CAMADA DE DOMÍNIO (18 arquivos)
                    │   ├── 👤 Pessoa.java          # Classe abstrata base
                    │   ├── 👨‍🎓 Aluno.java            # Aluno regular
                    │   ├── ⭐ AlunoVIP.java         # Aluno com benefícios (herança)
                    │   ├── 👨‍🏫 Professor.java       # Professor com especialidades
                    │   ├── 📚 Curso.java            # Curso de instrumento
                    │   ├── 📝 Aula.java             # Sessão de aula
                    │   ├── 🏠 Sala.java             # Sala/estúdio
                    │   ├── 🔑 AluguelSala.java      # Aluguel de espaço
                    │   ├── 📋 Matricula.java        # Matrícula em curso
                    │   ├── 📊 Desempenho.java       # Avaliação de aluno
                    │   ├── 🎭 Evento.java           # Evento/recital
                    │   ├── 💰 Pagamento.java        # Pagamento com multas
                    │   ├── 🎁 PlanoFidelidade.java  # Programa de pontos
                    │   ├── 🎸 TipoInstrumento.java  # Enum (10 instrumentos)
                    │   ├── 🎯 TipoAula.java         # Enum (Individual/Grupo/Turma)
                    │   └── ✅ StatusMatricula.java  # Enum (Ativa/Suspensa/...)
                    │
                    ├── 📁 service/                  # CAMADA DE NEGÓCIO (6 arquivos)
                    │   ├── 💼 AlunoService.java
                    │   │   ├── cadastrar()
                    │   │   ├── buscarPorId()
                    │   │   ├── buscarPorNome()
                    │   │   ├── promoverParaVIP()
                    │   │   └── validarAluno()
                    │   │
                    │   ├── 💼 ProfessorService.java
                    │   │   ├── cadastrar()
                    │   │   ├── buscarPorEspecialidade()
                    │   │   └── listarDisponiveis()
                    │   │
                    │   ├── 💼 CursoService.java
                    │   │   ├── cadastrar()
                    │   │   ├── buscarPorInstrumento()
                    │   │   └── buscarPorNivel()
                    │   │
                    │   ├── 💼 MatriculaService.java
                    │   │   ├── matricular()
                    │   │   ├── suspender()
                    │   │   ├── reativar()
                    │   │   └── concluir()
                    │   │
                    │   ├── 💼 EventoService.java
                    │   │   ├── criar()
                    │   │   ├── inscreverAluno()
                    │   │   └── listarFuturos()
                    │   │
                    │   └── 💼 PagamentoService.java
                    │       ├── gerarPagamento()
                    │       ├── registrarPagamento()
                    │       └── listarAtrasados()
                    │
                    ├── 📁 repository/               # CAMADA DE DADOS (9 arquivos)
                    │   ├── 🗄️ RepositorioGenerico.java  # Base abstrata
                    │   │   ├── salvar()
                    │   │   ├── buscarPorId()
                    │   │   ├── listarTodos()
                    │   │   ├── remover()
                    │   │   └── extrairId() [abstrato]
                    │   │
                    │   ├── 🗄️ AlunoRepositorio.java
                    │   │   ├── buscarPorCpf()
                    │   │   ├── buscarPorMatricula()
                    │   │   └── listarAlunosVIP()
                    │   │
                    │   ├── 🗄️ ProfessorRepositorio.java
                    │   │   ├── buscarPorEspecialidade()
                    │   │   └── buscarPorRegistro()
                    │   │
                    │   ├── 🗄️ CursoRepositorio.java
                    │   │   ├── buscarPorInstrumento()
                    │   │   └── buscarPorNivel()
                    │   │
                    │   ├── 🗄️ MatriculaRepositorio.java
                    │   │   ├── buscarPorAluno()
                    │   │   ├── buscarPorCurso()
                    │   │   └── buscarPorStatus()
                    │   │
                    │   ├── 🗄️ AulaRepositorio.java
                    │   │   ├── buscarPorProfessor()
                    │   │   ├── buscarPorSala()
                    │   │   └── buscarPorPeriodo()
                    │   │
                    │   ├── 🗄️ SalaRepositorio.java
                    │   │   ├── buscarPorNumero()
                    │   │   └── buscarPorCapacidade()
                    │   │
                    │   ├── 🗄️ EventoRepositorio.java
                    │   │   ├── buscarPorPeriodo()
                    │   │   ├── listarFuturos()
                    │   │   └── listarComVagas()
                    │   │
                    │   └── 🗄️ PagamentoRepositorio.java
                    │       ├── buscarPorAluno()
                    │       ├── listarPendentes()
                    │       └── listarAtrasados()
                    │
                    ├── 📁 util/                     # UTILITÁRIOS (4 arquivos)
                    │   ├── ✔️ ValidadorCPF.java
                    │   │   ├── validar()
                    │   │   └── formatar()
                    │   │
                    │   ├── ✔️ ValidadorEmail.java
                    │   │   ├── validar()
                    │   │   └── normalizar()
                    │   │
                    │   ├── 📅 FormatadorData.java
                    │   │   ├── formatarData()
                    │   │   ├── formatarDataHora()
                    │   │   └── parseData()
                    │   │
                    │   └── 💵 FormatadorMoeda.java
                    │       ├── formatar()
                    │       └── parse()
                    │
                    └── 📁 exception/                # EXCEÇÕES (4 arquivos)
                        ├── ⚠️ EscolaMusicaException.java       # Base
                        ├── ❌ EntidadeNaoEncontradaException.java
                        ├── ⛔ ValidacaoException.java
                        └── 🚫 NegocioException.java
```

---

## 📊 Hierarquia de Classes (Herança)

```
         ┌─────────────┐
         │   Pessoa    │ (abstrata)
         │  (abstract) │
         └──────┬──────┘
                │
        ┌───────┴───────┐
        │               │
   ┌────▼────┐    ┌────▼────┐
   │  Aluno  │    │Professor│
   └────┬────┘    └─────────┘
        │
   ┌────▼────┐
   │AlunoVIP │
   └─────────┘
```

## 🔄 Fluxo de Dados (Camadas)

```
┌─────────────────────────────────────────────────┐
│         EscolaMusicaApp (Main)                  │
│         [Menu Interativo]                       │
└────────────────┬────────────────────────────────┘
                 │ usa
                 ▼
┌─────────────────────────────────────────────────┐
│         SERVICE LAYER                           │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐     │
│  │  Aluno   │  │Professor │  │  Curso   │     │
│  │ Service  │  │ Service  │  │ Service  │ ... │
│  └────┬─────┘  └────┬─────┘  └────┬─────┘     │
└───────┼─────────────┼─────────────┼────────────┘
        │ usa         │ usa         │ usa
        ▼             ▼             ▼
┌─────────────────────────────────────────────────┐
│         REPOSITORY LAYER                        │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐     │
│  │  Aluno   │  │Professor │  │  Curso   │     │
│  │Repository│  │Repository│  │Repository│ ... │
│  └────┬─────┘  └────┬─────┘  └────┬─────┘     │
└───────┼─────────────┼─────────────┼────────────┘
        │ armazena    │ armazena    │ armazena
        ▼             ▼             ▼
┌─────────────────────────────────────────────────┐
│         MODEL LAYER (In-Memory)                 │
│  ┌──────┐  ┌─────────┐  ┌──────┐               │
│  │Aluno │  │Professor│  │Curso │  ...          │
│  └──────┘  └─────────┘  └──────┘               │
└─────────────────────────────────────────────────┘
```

## 🎯 Relacionamentos Entre Entidades

```
     Aluno ───────────► Matricula ◄─────────── Curso
       │                    │
       │                    │
       ▼                    ▼
  Desempenho           TipoAula (enum)
       │
       │
       ▼
   Pagamento
       │
       ▼
 PlanoFidelidade


   Professor ──────► Aula ◄────── Sala
                      │
                      ▼
                   Alunos[]


     Evento ◄────── Aluno
       │
       │
       ▼
  Participantes[]
  ListaEspera[]
```

## 💾 Armazenamento (In-Memory)

```
RepositorioGenerico<T, ID>
         │
         ├── HashMap<ID, T> dados
         │
         ├── salvar(T entidade)
         ├── buscarPorId(ID id)
         ├── listarTodos()
         ├── remover(ID id)
         └── extrairId(T entidade) [abstrato]
                    │
                    │ implementado por
                    ▼
    ┌──────────────────────────────┐
    │  AlunoRepositorio            │
    │  ProfessorRepositorio        │
    │  CursoRepositorio            │
    │  MatriculaRepositorio        │
    │  EventoRepositorio           │
    │  PagamentoRepositorio        │
    │  AulaRepositorio             │
    │  SalaRepositorio             │
    └──────────────────────────────┘
```

## 🎮 Menu Principal

```
═══════════════════════════════════════════════
        ESCOLA DE MÚSICA NOTA MÁXIMA
═══════════════════════════════════════════════
           MENU PRINCIPAL
═══════════════════════════════════════════════
1. Gerenciar Alunos
   ├── Cadastrar Aluno
   ├── Listar Alunos
   ├── Buscar Aluno
   └── Promover para VIP

2. Gerenciar Professores
   ├── Cadastrar Professor
   └── Listar Professores

3. Gerenciar Cursos
   ├── Cadastrar Curso
   └── Listar Cursos

4. Gerenciar Matrículas
   ├── Nova Matrícula
   └── Listar Matrículas

5. Gerenciar Eventos
   ├── Criar Evento
   ├── Listar Eventos Futuros
   └── Inscrever Aluno

6. Gerenciar Pagamentos
   ├── Gerar Pagamento
   ├── Registrar Pagamento
   ├── Listar Pendentes
   └── Listar Atrasados

7. Relatórios
   ├── Estatísticas Gerais
   └── Cursos Mais Populares

0. Sair
═══════════════════════════════════════════════
```

---

**Total: 40 arquivos Java organizados em 6 pacotes + 1 aplicação principal**

🎵 **Projeto completo e totalmente funcional!** 🎵
