# SisUni - Sistema de Gerenciamento Universitário

Este é um projeto de desktop desenvolvido em Java com interface gráfica Swing para um sistema de cadastro de alunos e professores. As informações são armazenadas em um banco de dados MySQL.

## 👥 Colaboradores

- Allana Thayná Santos Pimentel - 10724263997 - Github: [Allana-Pimentel](https://github.com/Allana-Pimentel)
- Davi Cardoso Rhee - 1072221147 - Github: [DaviRhee](https://github.com/DaviRhee)
- Douglas Rodrigues Toledo - 1072225657 - Github: [Toledodouglas](https://github.com/Toledodouglas)
- João Pedro de Novais Sombra - 1072221731 - Github: [jopnovais](https://github.com/jopnovais)
- Luiz Felipe Correa Soares - 1072223007 - Github: [LFSCorr](https://github.com/LFSCorr)

## 📋 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

1. **Java JDK 8 ou superior**
   - Verifique a instalação: `java -version`
   - Se não tiver, baixe em: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) ou [OpenJDK](https://openjdk.org/)

2. **Maven 3.6 ou superior**
   - Verifique a instalação: `mvn -version`
   - Se não tiver, baixe em: [Maven Download](https://maven.apache.org/download.cgi)

3. **MySQL Server 8.0 ou superior**
   - Verifique a instalação: `mysql --version`
   - Se não tiver, baixe em: [MySQL Download](https://dev.mysql.com/downloads/mysql/)

## 🚀 Passo a Passo para Executar o Projeto

### Passo 1: Clone o Repositório

```bash
git clone <url-do-repositorio>
cd a3_GQS_unisul
```

### Passo 2: Instale e Configure o MySQL

1. **Instale o MySQL Server** (se ainda não tiver):
   - Siga as instruções do instalador
   - **IMPORTANTE**: Anote o usuário e senha do MySQL que você configurou durante a instalação

2. **Inicie o serviço MySQL**:
   - **Windows**: Verifique se o serviço MySQL está rodando no Gerenciador de Serviços
   - **Linux/Mac**: Execute `sudo systemctl start mysql` ou `brew services start mysql`

3. **Crie o banco de dados**:
   - Abra o MySQL Workbench ou linha de comando do MySQL
   - Execute o script SQL fornecido no arquivo `db_escola.sql`:
   
   ```bash
   mysql -u root -p < db_escola.sql
   ```
   
   Ou manualmente no MySQL:
   ```sql
   CREATE SCHEMA IF NOT EXISTS `db_escola` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
   USE `db_escola`;
   -- Execute o restante do script db_escola.sql
   ```

### Passo 3: Configure as Credenciais do MySQL no Projeto

1. Abra o arquivo `src/main/java/principal/Principal.java`

2. Localize as linhas onde as credenciais são configuradas (aproximadamente linha 20-21):

   ```java
   TelaLogin.userDB = "root";
   TelaLogin.passwordDB = "password";
   ```

3. **Substitua pelos seus dados do MySQL**:
   ```java
   TelaLogin.userDB = "seu_usuario_mysql";        // Exemplo: "root"
   TelaLogin.passwordDB = "sua_senha_mysql";      // Exemplo: "MinhaSenha123"
   ```

### Passo 4: Baixe as Dependências do Maven

Execute o comando para baixar todas as dependências do projeto:

```bash
mvn clean install
```

Este comando irá:
- Baixar as dependências (MySQL Connector, FlatLaf, JCalendar)
- Compilar o projeto
- Executar os testes (se houver)

### Passo 5: Execute o Projeto

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
│   │       └── DAO/                       # Data Access Object (acesso ao banco)
│   └── test/
│       └── java/                          # Testes unitários
├── db_escola.sql                          # Script SQL para criar o banco
├── pom.xml                                # Configuração do Maven
└── README.md                              # Este arquivo
```

## 🗄️ Estrutura do Banco de Dados

O banco de dados `db_escola` possui duas tabelas principais:

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

## 🔧 Configurações Importantes

- **Host do Banco**: `localhost`
- **Porta do MySQL**: `3306`
- **Nome do Banco**: `db_escola`
- **Driver JDBC**: `com.mysql.cj.jdbc.Driver`

## ⚠️ Solução de Problemas

### Erro: "Erro ao conectar com o banco de dados MySQL"

**Possíveis causas e soluções:**

1. **MySQL não está rodando**:
   - Verifique se o serviço MySQL está ativo
   - Windows: Gerenciador de Serviços → MySQL
   - Linux: `sudo systemctl status mysql`

2. **Banco de dados não existe**:
   - Execute o script `db_escola.sql` novamente

3. **Credenciais incorretas**:
   - Verifique o usuário e senha no arquivo `Principal.java`
   - Teste a conexão manualmente no MySQL Workbench

4. **Porta 3306 bloqueada**:
   - Verifique se a porta 3306 está acessível
   - Windows: Firewall → Permitir aplicativo através do firewall

### Erro: "O driver não foi encontrado"

- Certifique-se de que o comando `mvn clean install` foi executado com sucesso
- Verifique se a dependência do MySQL Connector está no `pom.xml`

### Erro de Compilação Java

- Verifique se você está usando Java JDK 8 ou superior
- Execute `mvn clean` e depois `mvn install` novamente

## 🧪 Executando os Testes

Para executar os testes unitários:

```bash
mvn test
```

**Nota**: Os testes requerem que o MySQL esteja rodando e as credenciais estejam configuradas corretamente.

## 📚 Tecnologias Utilizadas

- **Java 8+**: Linguagem de programação
- **Maven**: Gerenciador de dependências
- **MySQL**: Banco de dados relacional
- **Swing**: Biblioteca para interface gráfica
- **FlatLaf**: Tema moderno para Swing
- **JCalendar**: Componente de calendário
- **JUnit**: Framework de testes

## 📝 Licença

Este é um projeto educacional desenvolvido para fins acadêmicos.

## 🤝 Contribuindo

Este é um projeto acadêmico. Para sugestões ou correções, abra uma issue ou entre em contato com os colaboradores.

---

**Desenvolvido com ❤️ para fins educacionais**

