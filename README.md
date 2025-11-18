# SisUni - Sistema de Gerenciamento Universitário

Este é um projeto de desktop desenvolvido em Java com interface gráfica Swing para um sistema de cadastro de alunos e professores. As informações são armazenadas em um banco de dados SQLite.

## Colaboradores

- Allana Thayná Santos Pimentel - 10724263997 - Github: [Allana-Pimentel](https://github.com/Allana-Pimentel)
- Davi Cardoso Rhee - 1072221147 - Github: [DaviRhee](https://github.com/DaviRhee)
- Douglas Rodrigues Toledo - 1072225657 - Github: [Toledodouglas](https://github.com/Toledodouglas)
- João Pedro de Novais Sombra - 1072221731 - Github: [jopnovais](https://github.com/jopnovais)
- Luiz Felipe Correa Soares - 1072223007 - Github: [LFSCorr](https://github.com/LFSCorr)

## Badges

<p align="center">
  <!-- GitHub Actions -->
  <a href="https://github.com/jopnovais/a3_GQS_unisul/actions">
    <img src="https://github.com/jopnovais/a3_GQS_unisul/actions/workflows/maven.yml/badge.svg" alt="Build Status">
  </a>

  <!-- SonarCloud: Quality Gate -->
  <a href="https://sonarcloud.io/summary/overall?id=jopnovais_a3_GQS_unisul">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=jopnovais_a3_GQS_unisul&metric=alert_status" alt="Quality Gate">
  </a>

  <!-- SonarCloud: Coverage -->
  <a href="https://sonarcloud.io/summary/overall?id=jopnovais_a3_GQS_unisul">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=jopnovais_a3_GQS_unisul&metric=coverage" alt="Coverage">
  </a>
</p>

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

1. Java JDK 8 ou superior

   - Verifique a instalação: `java -version`
   - Se não tiver, baixe em: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) ou [OpenJDK](https://openjdk.org/)

2. Maven 3.6 ou superior

   - Verifique a instalação: `mvn -version`
   - Se não tiver, baixe em: [Maven Download](https://maven.apache.org/download.cgi)

3. SQLite (opcional - já incluído como dependência)
   - O banco de dados SQLite é criado automaticamente na primeira execução
   - Não requer instalação ou configuração adicional

## Passo a Passo para Executar o Projeto

### Passo 1: Clone o Repositório

```bash
git clone <url-do-repositorio>
cd a3_GQS_unisul
```

### Passo 2: Baixe as Dependências do Maven

Execute o comando para baixar todas as dependências do projeto:

```bash
mvn clean install
```

Este comando irá:

- Baixar as dependências (SQLite JDBC, FlatLaf, JCalendar, JUnit, JaCoCo)
- Compilar o projeto
- Executar os testes de integração
- Gerar o relatório de cobertura de código

Nota: O banco de dados SQLite (`db_escola.db`) será criado automaticamente na raiz do projeto na primeira execução.

### Passo 3: Execute o Projeto

Você tem três opções para executar o projeto:

#### Opção 1: Via Maven (Recomendado)

```bash
mvn exec:java -Dexec.mainClass="principal.Principal"
```

#### Opção 2: Via JAR Executável

1. Gere o JAR com todas as dependências:

   ```bash
   mvn package
   ```

2. Execute o JAR gerado:
   ```bash
   java -jar target/A3_gqs_unisul-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

#### Opção 3: Via IDE (IntelliJ IDEA, Eclipse, NetBeans)

1. Abra o projeto na sua IDE
2. Configure o JDK 8 ou superior no projeto
3. Execute a classe `principal.Principal` como aplicação Java

## 📁 Estrutura do Projeto

```
a3_GQS_unisul/
├── .github/
│   └── workflows/
│       └── maven.yml                      # Pipeline CI/CD (DEV, HMG, PRD)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── principal/
│   │   │   │   └── Principal.java         # Classe principal da aplicação
│   │   │   ├── view/                      # Interfaces gráficas (Swing)
│   │   │   │   ├── CadastroAluno.java    # Tela de cadastro de alunos
│   │   │   │   ├── CadastroProfessor.java # Tela de cadastro de professores
│   │   │   │   ├── EditarAluno.java      # Tela de edição de alunos
│   │   │   │   ├── EditarProfessor.java  # Tela de edição de professores
│   │   │   │   ├── GerenciaAlunos.java   # Tela de gerenciamento de alunos
│   │   │   │   ├── GerenciaProfessores.java # Tela de gerenciamento de professores
│   │   │   │   ├── TelaLogin.java        # Tela de login
│   │   │   │   ├── TelaPrincipal.java   # Tela principal do sistema
│   │   │   │   ├── Sobre.java            # Tela sobre o sistema
│   │   │   │   └── Mensagens.java        # Utilitário de mensagens
│   │   │   ├── model/                     # Modelos de dados
│   │   │   │   ├── Pessoa.java           # Classe abstrata base
│   │   │   │   ├── Aluno.java            # Modelo de aluno
│   │   │   │   └── Professor.java         # Modelo de professor
│   │   │   ├── db/
│   │   │   │   └── ConnectionFactory.java # Factory de conexão SQLite
│   │   │   ├── repository/                # Camada de repositório (Repository Pattern)
│   │   │   │   ├── AbstractRepository.java # Classe abstrata base
│   │   │   │   ├── AlunoRepository.java   # Interface do repositório de alunos
│   │   │   │   ├── AlunoRepositoryImpl.java # Implementação do repositório de alunos
│   │   │   │   ├── ProfessorRepository.java # Interface do repositório de professores
│   │   │   │   ├── ProfessorRepositoryImpl.java # Implementação do repositório de professores
│   │   │   │   └── exception/
│   │   │   │       └── DataAccessException.java # Exceção de acesso a dados
│   │   │   ├── service/                   # Camada de serviço (lógica de negócio)
│   │   │   │   ├── AlunoService.java      # Interface do serviço de alunos
│   │   │   │   ├── AlunoServiceImpl.java  # Implementação do serviço de alunos
│   │   │   │   ├── ProfessorService.java   # Interface do serviço de professores
│   │   │   │   ├── ProfessorServiceImpl.java # Implementação do serviço de professores
│   │   │   │   └── exception/
│   │   │   │       └── ValidacaoException.java # Exceção de validação
│   │   │   └── dao/                        # Data Access Object (legado - mantido para testes)
│   │   │       ├── AlunoDAO.java          # DAO de alunos (legado)
│   │   │       └── ProfessorDAO.java      # DAO de professores (legado)
│   │   └── resources/
│   │       └── view/
│   │           └── refresh.png            # Ícone de atualização
│   └── test/
│       └── java/                          # Testes automatizados
│           ├── model/                     # Testes dos modelos
│           │   ├── PessoaTest.java        # 20 casos de teste
│           │   ├── AlunoTest.java         # 29 casos de teste
│           │   └── ProfessorTest.java     # 24 casos de teste
│           ├── service/                   # Testes dos serviços
│           │   ├── AlunoServiceTest.java  # 35 casos de teste
│           │   ├── AlunoServiceImplTest.java # 35 casos de teste
│           │   ├── ProfessorServiceTest.java # 37 casos de teste
│           │   ├── ProfessorServiceImplTest.java # 36 casos de teste
│           │   └── exception/
│           │       └── ValidacaoExceptionTest.java # 6 casos de teste
│           ├── repository/                # Testes dos repositórios
│           │   ├── AbstractRepositoryTest.java # 6 casos de teste
│           │   ├── AlunoRepositoryImplTest.java # 12 casos de teste
│           │   ├── ProfessorRepositoryImplTest.java # 15 casos de teste
│           │   └── exception/
│           │       └── DataAccessExceptionTest.java # 6 casos de teste
│           └── dao/                        # Testes dos DAOs (legado)
│               ├── AlunoDAOTest.java      # 19 casos de teste
│               └── ProfessorDAOTest.java  # 21 casos de teste
├── pom.xml                                # Configuração do Maven
├── db_escola.db                           # Banco de dados SQLite (gerado automaticamente)
└── README.md                              # Este arquivo
```

## 🗄️ Estrutura do Banco de Dados

O banco de dados SQLite (`db_escola.db`) possui duas tabelas principais:

### Tabela: `tb_alunos`

- `id` (INT, PRIMARY KEY)
- `nome` (VARCHAR(250))
- `idade` (INT)
- `curso` (VARCHAR(45))
- `fase` (INT)

### Tabela: `tb_professores`

- `id` (INT, PRIMARY KEY)
- `nome` (VARCHAR(250))
- `idade` (INT)
- `campus` (VARCHAR(45))
- `cpf` (VARCHAR(14))
- `contato` (VARCHAR(16))
- `titulo` (VARCHAR(45))
- `salario` (DOUBLE)

## Configurações Importantes

- Tipo de Banco: SQLite
- Arquivo do Banco: `db_escola.db` (criado automaticamente na raiz do projeto)
- Driver JDBC: `org.sqlite.JDBC`
- String de Conexão: `jdbc:sqlite:db_escola.db`

## Solução de Problemas

### Erro: "Erro ao conectar ao banco de dados SQLite"

Possíveis causas e soluções:

1. Permissões de escrita:

   - Verifique se o diretório do projeto tem permissão de escrita
   - O arquivo `db_escola.db` será criado automaticamente

2. Arquivo de banco corrompido:
   - Delete o arquivo `db_escola.db` e execute novamente
   - O banco será recriado automaticamente

3. Driver SQLite não encontrado:

   - Execute `mvn clean install` para baixar as dependências
   - Verifique se a dependência `sqlite-jdbc` está no `pom.xml`

## 🧪 Executando os Testes

O projeto possui uma suíte completa de testes cobrindo todas as camadas:

1. **Testes Unitários de Modelo**: Testam os modelos de dados (Pessoa, Aluno, Professor)
2. **Testes Unitários de Serviço** (Mockito): Testam a camada de serviço isoladamente, usando mocks dos repositórios
3. **Testes de Integração de Repositório**: Testam a camada de repositório com o banco de dados SQLite
4. **Testes de Integração de DAO**: Testam os DAOs legados (mantidos para compatibilidade)
5. **Testes de Exceções**: Testam as classes de exceção customizadas

### Executar Todos os Testes

Para executar todos os testes do projeto (unitários + integração):

```bash
mvn test
```

### Executar Testes Específicos

#### Testes Unitários

Para executar apenas os testes unitários de `AlunoServiceImpl`:

```bash
mvn test -Dtest=AlunoServiceImplTest
```

Para executar apenas os testes unitários de `ProfessorServiceImpl`:

```bash
mvn test -Dtest=ProfessorServiceImplTest
```

#### Testes de Integração

Para executar apenas os testes de integração de `AlunoRepositoryImpl`:

```bash
mvn test -Dtest=AlunoRepositoryImplTest
```

Para executar apenas os testes de integração de `ProfessorRepositoryImpl`:

```bash
mvn test -Dtest=ProfessorRepositoryImplTest
```

#### Testes de Modelo

Para executar apenas os testes de modelo:

```bash
mvn test -Dtest=AlunoTest
mvn test -Dtest=ProfessorTest
mvn test -Dtest=PessoaTest
```

#### Testes de DAO (Legado)

Para executar apenas os testes de DAO legado:

```bash
mvn test -Dtest=AlunoDAOTest
mvn test -Dtest=ProfessorDAOTest
```

#### Testes de Exceções

Para executar apenas os testes de exceções:

```bash
mvn test -Dtest=ValidacaoExceptionTest
mvn test -Dtest=DataAccessExceptionTest
```

### Gerar Relatório de Cobertura (JaCoCo)

Para gerar o relatório de cobertura de código:

```bash
mvn clean verify
```

Após executar, o relatório HTML será gerado em:

```
target/site/jacoco/index.html
```

Para abrir o relatório no navegador:

Windows:

```bash
start target\site\jacoco\index.html
```

Linux/Mac:

```bash
xdg-open target/site/jacoco/index.html
# ou
open target/site/jacoco/index.html
```

### 📊 Casos de Teste Implementados

O projeto possui uma suíte completa de testes cobrindo todas as camadas da aplicação:

#### 📋 Resumo Geral de Testes

| Categoria | Classe de Teste | Casos | Tipo |
|-----------|----------------|-------|------|
| **Model** | PessoaTest | 20 | Unitário |
| **Model** | AlunoTest | 29 | Unitário |
| **Model** | ProfessorTest | 24 | Unitário |
| **Service** | AlunoServiceTest | 35 | Unitário (Mockito) |
| **Service** | AlunoServiceImplTest | 35 | Unitário (Mockito) |
| **Service** | ProfessorServiceTest | 37 | Unitário (Mockito) |
| **Service** | ProfessorServiceImplTest | 36 | Unitário (Mockito) |
| **Service** | ValidacaoExceptionTest | 6 | Unitário |
| **Repository** | AbstractRepositoryTest | 6 | Integração |
| **Repository** | AlunoRepositoryImplTest | 12 | Integração |
| **Repository** | ProfessorRepositoryImplTest | 15 | Integração |
| **Repository** | DataAccessExceptionTest | 6 | Unitário |
| **DAO (Legado)** | AlunoDAOTest | 19 | Integração |
| **DAO (Legado)** | ProfessorDAOTest | 21 | Integração |
| **TOTAL** | **14 classes** | **301 casos** | - |

#### 🧪 Testes de Modelo (73 casos)

Testam os modelos de dados (Pessoa, Aluno, Professor):

- **PessoaTest (20 casos)**: Testa a classe abstrata base
  - Construtores (padrão e parametrizado)
  - Getters e Setters (id, nome, idade)
  - Validações de campos

- **AlunoTest (29 casos)**: Testa o modelo de aluno
  - Construtores (padrão, curso/fase, completo)
  - Getters e Setters (curso, fase)
  - Validações de campos e regras de negócio

- **ProfessorTest (24 casos)**: Testa o modelo de professor
  - Construtores (padrão e parametrizado)
  - Getters e Setters (campus, CPF, contato, título, salário)
  - Validações de campos e formatações

#### 🔧 Testes de Serviço (143 casos)

Testam a lógica de negócio usando Mockito para isolar dependências:

**AlunoServiceTest & AlunoServiceImplTest (70 casos total)**

| Categoria   | Método            | Quantidade | Descrição                                        |
| ----------- | ----------------- | ---------- | ------------------------------------------------ |
| Validações  | `salvar()`        | 10         | Validações de nome, idade, curso, fase, etc.     |
| Validações  | `atualizar()`     | 11         | Validações de ID, nome, idade, curso, fase, etc. |
| Operações   | `excluir()`       | 1          | Exclusão de aluno                                |
| Operações   | `buscarPorId()`   | 1          | Busca por ID                                     |
| Operações   | `listarTodos()`   | 1          | Listagem de todos os alunos                      |
| Utilitários | `calcularIdade()` | 3          | Cálculo de idade com diferentes cenários         |

**ProfessorServiceTest & ProfessorServiceImplTest (73 casos total)**

| Categoria   | Método               | Quantidade | Descrição                                                            |
| ----------- | -------------------- | ---------- | -------------------------------------------------------------------- |
| Validações  | `salvar()`           | 21         | Validações de nome, campus, CPF, contato, idade, salário, título     |
| Validações  | `atualizar()`        | 4          | Validações de ID, CPF duplicado, etc.                                |
| Operações   | `excluir()`          | 1          | Exclusão de professor                                                |
| Operações   | `buscarPorId()`      | 1          | Busca por ID                                                         |
| Operações   | `buscarPorCpf()`     | 1          | Busca por CPF                                                        |
| Operações   | `listarTodos()`      | 1          | Listagem de todos os professores                                     |
| Utilitários | `calcularIdade()`    | 3          | Cálculo de idade com diferentes cenários                             |
| Utilitários | `validarFormatado()` | 4          | Validação e formatação de strings (remover caracteres não numéricos) |

**ValidacaoExceptionTest (6 casos)**: Testa a exceção de validação
- Criação com mensagem
- Criação com causa
- Criação com mensagem e causa
- Métodos de acesso

**Principais cenários testados nos serviços:**

- ✅ Validação de campos obrigatórios (nome, curso, fase, campus, CPF, contato, título)
- ✅ Validação de regras de negócio (idade mínima, fase entre 1-10)
- ✅ Validação de formato de nome (deve conter letras)
- ✅ Validação de formato de CPF e contato (11 dígitos numéricos)
- ✅ Validação de salário (mínimo 4 dígitos, maior que zero)
- ✅ Validação de CPF duplicado no cadastro e atualização
- ✅ Cálculo correto de idade considerando aniversário
- ✅ Formatação de strings (remover caracteres não numéricos)
- ✅ Verificação de chamadas ao repositório

#### 🗄️ Testes de Repositório (33 casos)

Testam a camada de acesso a dados com banco de dados SQLite:

**AlunoRepositoryImplTest (12 casos)**

| #   | Método       | Descrição                                             | Status |
| --- | ------------ | ----------------------------------------------------- | ------ |
| 1   | `save()`     | Salvar aluno válido - deve gerar ID                   | ✅     |
| 2   | `save()`     | Salvar aluno com nome nulo - deve salvar com null     | ✅     |
| 3   | `save()`     | Salvar dois alunos diferentes - deve salvar ambos     | ✅     |
| 4   | `findById()` | Buscar por ID existente - deve retornar aluno         | ✅     |
| 5   | `findById()` | Buscar por ID inexistente - deve retornar null        | ✅     |
| 5.5 | `update()`   | Atualizar com ID inexistente - deve retornar false    | ✅     |
| 5.6 | `delete()`   | Excluir com ID inexistente - deve retornar false      | ✅     |
| 6   | `findAll()`  | Listar quando banco vazio - deve retornar lista vazia | ✅     |
| 7   | `findAll()`  | Listar após salvar 3 alunos - deve retornar 3 alunos  | ✅     |
| 8   | `update()`   | Atualizar nome do aluno - deve atualizar corretamente | ✅     |
| 9   | `delete()`   | Excluir aluno - deve remover do banco                 | ✅     |
| 10  | `getMaxId()` | getMaxId quando tabela vazia - deve retornar 0        | ✅     |
| 11  | `getMaxId()` | getMaxId após salvar múltiplos - deve retornar maior ID | ✅     |
| 12  | `findAll()`  | findAll com múltiplos alunos - deve retornar todos    | ✅     |

**ProfessorRepositoryImplTest (15 casos)**

| #   | Método        | Descrição                                                      | Status |
| --- | ------------- | -------------------------------------------------------------- | ------ |
| 1   | `save()`      | Salvar professor válido - deve gerar ID                        | ✅     |
| 2   | `save()`      | Salvar professor com campo nulo - deve salvar com null         | ✅     |
| 3   | `findById()`  | Buscar por ID existente - deve retornar professor              | ✅     |
| 4   | `findById()`  | Buscar por ID inexistente - deve retornar null                 | ✅     |
| 4.5 | `findByCpf()` | Buscar por CPF existente - deve retornar professor             | ✅     |
| 4.6 | `findByCpf()` | Buscar por CPF inexistente - deve retornar null                | ✅     |
| 5   | `findAll()`   | Listar quando banco vazio - deve retornar lista vazia          | ✅     |
| 6   | `findAll()`   | Listar após salvar 2 professores - deve retornar 2 professores | ✅     |
| 6.5 | `update()`    | Atualizar com ID inexistente - deve retornar false             | ✅     |
| 6.6 | `delete()`    | Excluir com ID inexistente - deve retornar false               | ✅     |
| 7   | `update()`    | Atualizar contato do professor - deve atualizar corretamente   | ✅     |
| 8   | `delete()`    | Excluir professor - deve remover do banco                      | ✅     |
| 9   | `getMaxId()`  | getMaxId quando tabela vazia - deve retornar 0                 | ✅     |
| 10  | `getMaxId()`  | getMaxId após salvar múltiplos - deve retornar maior ID        | ✅     |
| 11  | `findAll()`   | findAll com múltiplos professores - deve retornar todos        | ✅     |

**AbstractRepositoryTest (6 casos)**: Testa métodos protegidos da classe abstrata base
- `executeMaxIdQuery()`: Busca do maior ID
- `executeDelete()`: Exclusão de registros

**DataAccessExceptionTest (6 casos)**: Testa a exceção de acesso a dados
- Criação com mensagem
- Criação com causa
- Criação com mensagem e causa
- Métodos de acesso

#### 📦 Testes de DAO - Legado (40 casos)

Testes mantidos para compatibilidade com código legado:

- **AlunoDAOTest (19 casos)**: Testa o DAO legado de alunos
- **ProfessorDAOTest (21 casos)**: Testa o DAO legado de professores

### 📈 Métricas de Testes

| Métrica | Valor |
|---------|-------|
| **Total de Classes de Teste** | 14 |
| **Total de Casos de Teste** | **301** |
| **Testes Unitários** | 216 casos |
| **Testes de Integração** | 85 casos |
| **Cobertura de Código** | Verificar no SonarCloud |
| **Taxa de Sucesso** | 100% (todos os testes passando) |

### 📁 Estrutura dos Testes

```
src/test/java/
├── model/                          # Testes dos Modelos (73 casos)
│   ├── PessoaTest.java            # 20 casos
│   ├── AlunoTest.java             # 29 casos
│   └── ProfessorTest.java         # 24 casos
├── service/                        # Testes dos Serviços (143 casos)
│   ├── AlunoServiceTest.java      # 35 casos
│   ├── AlunoServiceImplTest.java  # 35 casos
│   ├── ProfessorServiceTest.java # 37 casos
│   ├── ProfessorServiceImplTest.java # 36 casos
│   └── exception/
│       └── ValidacaoExceptionTest.java # 6 casos
├── repository/                     # Testes dos Repositórios (33 casos)
│   ├── AbstractRepositoryTest.java # 6 casos
│   ├── AlunoRepositoryImplTest.java # 12 casos
│   ├── ProfessorRepositoryImplTest.java # 15 casos
│   └── exception/
│       └── DataAccessExceptionTest.java # 6 casos
└── dao/                           # Testes dos DAOs Legado (40 casos)
    ├── AlunoDAOTest.java          # 19 casos
    └── ProfessorDAOTest.java     # 21 casos
```

### 🛠️ Tecnologias de Teste

- **JUnit 5 (Jupiter)**: Framework de testes para Java
  - Versão: 5.10.0
  - Suporta testes parametrizados, repetidos e dinâmicos
- **Mockito**: Framework de mocking para testes unitários
  - Versão: 4.11.0
  - Usado para isolar dependências na camada de serviço
- **JaCoCo**: Análise de cobertura de código
  - Versão: 0.8.10
  - Gera relatórios HTML e XML de cobertura
- **SQLite**: Banco de dados para testes de integração
  - Versão JDBC: 3.44.1.0
  - Banco criado em memória para testes isolados
- **Maven Surefire Plugin**: Execução de testes durante o build
  - Versão: 3.2.5
  - Integrado com JaCoCo para cobertura

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 8+**: Linguagem de programação
- **Maven 3.6+**: Gerenciador de dependências e build
- **SQLite 3.44.1.0**: Banco de dados relacional embutido
- **JDBC**: API para acesso a dados

### Frontend
- **Java Swing**: Biblioteca para interface gráfica desktop
- **FlatLaf 3.2.5**: Tema moderno e flat para Swing
- **JCalendar 1.4**: Componente de calendário para seleção de datas

### Testes e Qualidade
- **JUnit 5.10.0**: Framework de testes para Java
- **Mockito 4.11.0**: Framework de mocking para testes unitários
- **JaCoCo 0.8.10**: Análise de cobertura de código
- **Maven Surefire Plugin 3.2.5**: Execução de testes durante o build

### CI/CD e Qualidade de Código
- **GitHub Actions**: Pipeline de CI/CD automatizado
  - **DEV**: Compilação do projeto
  - **HMG**: Execução de testes e análise SonarCloud
  - **PRD**: Empacotamento e release
- **SonarCloud**: Análise estática de qualidade de código
  - Code smells
  - Bugs potenciais
  - Vulnerabilidades de segurança
  - Cobertura de código

### Logging
- **SLF4J 2.0.13**: API de logging
- **SLF4J Simple**: Implementação simples para logging

## 📊 Arquitetura do Projeto

O projeto segue uma arquitetura em camadas (Layered Architecture):

```
┌─────────────────────────────────────┐
│         View (Swing GUI)             │  ← Interface do Usuário
├─────────────────────────────────────┤
│         Service (Lógica)            │  ← Regras de Negócio
├─────────────────────────────────────┤
│      Repository (Acesso Dados)      │  ← Abstração de Dados
├─────────────────────────────────────┤
│    ConnectionFactory (SQLite)       │  ← Conexão com BD
└─────────────────────────────────────┘
```

### Padrões de Projeto Utilizados

- **Repository Pattern**: Abstração da camada de acesso a dados
- **Service Layer**: Separação da lógica de negócio
- **Factory Pattern**: Criação de conexões com banco de dados
- **DAO Pattern**: Camada legada mantida para compatibilidade
- **Exception Handling**: Tratamento centralizado de exceções

## 🔄 Pipeline CI/CD

O projeto possui um pipeline automatizado com três ambientes:

1. **DEV**: Compilação e validação básica
2. **HMG**: Execução de testes, geração de cobertura e análise SonarCloud
3. **PRD**: Empacotamento e criação de release (apenas na branch main)

O pipeline é executado automaticamente em:
- Push para a branch `main`
- Pull Requests para a branch `main`
- Execução manual via `workflow_dispatch`
