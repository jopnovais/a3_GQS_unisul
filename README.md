# SisUni - Sistema de Gerenciamento Universitário

Este é um projeto de desktop desenvolvido em Java com interface gráfica Swing para um sistema de cadastro de alunos e professores. As informações são armazenadas em um banco de dados SQLite.

##  Colaboradores

- Allana Thayná Santos Pimentel - 10724263997 - Github: [Allana-Pimentel](https://github.com/Allana-Pimentel)
- Davi Cardoso Rhee - 1072221147 - Github: [DaviRhee](https://github.com/DaviRhee)
- Douglas Rodrigues Toledo - 1072225657 - Github: [Toledodouglas](https://github.com/Toledodouglas)
- João Pedro de Novais Sombra - 1072221731 - Github: [jopnovais](https://github.com/jopnovais)
- Luiz Felipe Correa Soares - 1072223007 - Github: [LFSCorr](https://github.com/LFSCorr)

##  Pré-requisitos

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

##  Passo a Passo para Executar o Projeto

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
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── principal/
│   │       │   └── Principal.java        # Classe principal da aplicação
│   │       ├── view/                      # Interfaces gráficas (Swing)
│   │       ├── model/                     # Modelos de dados (Aluno, Professor)
│   │       ├── db/                        # ConnectionFactory (SQLite)
│   │       ├── repository/                # Camada de repositório (Repository Pattern)
│   │       ├── service/                   # Camada de serviço (lógica de negócio)
│   │       └── DAO/                       # Data Access Object (legado - não utilizado)
│   └── test/
│       └── java/                          # Testes unitários
├── pom.xml                                # Configuração do Maven
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

3Driver SQLite não encontradO:
   - Execute `mvn clean install` para baixar as dependências
   - Verifique se a dependência `sqlite-jdbc` está no `pom.xml`

## Executando os Testes

O projeto possui testes de integração para validar o funcionamento da camada de repositório (Repository) com o banco de dados SQLite.

### Executar Todos os Testes

Para executar todos os testes do projeto:

```bash
mvn test
```

### Executar Testes Específicos

Para executar apenas os testes de `AlunoRepositoryImpl`:

```bash
mvn test -Dtest=AlunoRepositoryImplTest
```

Para executar apenas os testes de `ProfessorRepositoryImpl`:

```bash
mvn test -Dtest=ProfessorRepositoryImplTest
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

### Casos de Teste Implementados

#### Testes de AlunoRepositoryImpl (9 casos)

| # | Método | Descrição | Status |
|---|--------|-----------|--------|
| 1 | `save()` | Salvar aluno válido - deve gerar ID | OK |
| 2 | `save()` | Salvar aluno com nome nulo - deve salvar com null | OK |
| 3 | `save()` | Salvar dois alunos diferentes - deve salvar ambos | OK |
| 4 | `findById()` | Buscar por ID existente - deve retornar aluno | OK |
| 5 | `findById()` | Buscar por ID inexistente - deve retornar null | OK |
| 6 | `findAll()` | Listar quando banco vazio - deve retornar lista vazia | OK |
| 7 | `findAll()` | Listar após salvar 3 alunos - deve retornar 3 alunos | OK |
| 8 | `update()` | Atualizar nome do aluno - deve atualizar corretamente | OK |
| 9 | `delete()` | Excluir aluno - deve remover do banco | OK |

#### Testes de ProfessorRepositoryImpl (8 casos)

| # | Método | Descrição | Status |
|---|--------|-----------|--------|
| 1 | `save()` | Salvar professor válido - deve gerar ID | OK |
| 2 | `save()` | Salvar professor com campo nulo - deve salvar com null | OK |
| 3 | `findById()` | Buscar por ID existente - deve retornar professor | OK |
| 4 | `findById()` | Buscar por ID inexistente - deve retornar null | OK |
| 5 | `findAll()` | Listar quando banco vazio - deve retornar lista vazia | OK |
| 6 | `findAll()` | Listar após salvar 2 professores - deve retornar 2 professores | OK |
| 7 | `update()` | Atualizar contato do professor - deve atualizar corretamente | OK |
| 8 | `delete()` | Excluir professor - deve remover do banco | OK |

Total: 17 testes de integração

### Estrutura dos Testes

Os testes estão localizados em:
```
src/test/java/repository/
├── AlunoRepositoryImplTest.java
└── ProfessorRepositoryImplTest.java
```

### Tecnologias de Teste

- JUnit 5: (Jupiter): Framework de testes
- JaCoCo: Análise de cobertura de código
- SQLite: Banco de dados em memória para testes de integração

# Tecnologias Utilizadas

- Java 8+: Linguagem de programação
- Maven: Gerenciador de dependências
- SQLite: Banco de dados relacional embutido
- Swing: Biblioteca para interface gráfica
- FlatLaf: Tema moderno para Swing
- JCalendar: Componente de calendário
- JUnit 5: Framework de testes
- JaCoCo: Análise de cobertura de código
- SonarCloud: Análise de qualidade de código (CI/CD)




