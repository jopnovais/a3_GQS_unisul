# 📊 Relatórios de Testes

Este documento descreve os relatórios de testes gerados para o projeto SisUni.

## 📁 Localização dos Relatórios

Os relatórios HTML foram gerados na pasta `target/site/`:

### 1. Relatório de Testes (Surefire Report)
**Localização:** `target/site/surefire-report.html`

Este relatório contém:
- ✅ Resumo geral dos testes executados
- ✅ Lista de todas as classes de teste
- ✅ Detalhes de cada teste (sucesso, falha, erro)
- ✅ Tempo de execução de cada teste
- ✅ Estatísticas completas

**Como abrir:**
- Navegue até a pasta `target/site/`
- Abra o arquivo `surefire-report.html` no seu navegador

### 2. Relatório de Cobertura de Código (JaCoCo)
**Localização:** `target/site/jacoco/index.html`

Este relatório contém:
- 📈 Percentual de cobertura de código por pacote
- 📈 Cobertura de linhas, branches e métodos
- 📈 Detalhes de cobertura por classe
- 📈 Código fonte destacado mostrando linhas cobertas/não cobertas

**Como abrir:**
- Navegue até a pasta `target/site/jacoco/`
- Abra o arquivo `index.html` no seu navegador

## 🚀 Como Gerar os Relatórios

Para gerar os relatórios novamente, execute:

```bash
mvn clean test surefire-report:report
```

Ou simplesmente:

```bash
mvn test
```

Os relatórios são gerados automaticamente após a execução dos testes.

## 📊 Resumo dos Testes

### Testes de Validação (ValidacaoTest)
- **29 testes** - Validações de CPF, idade, nome, contato, salário, curso, fase, campus, título

### Testes de Sistema Completo (SistemaCompletoTest)
- **6 testes** - Fluxos end-to-end de alunos e professores

### Testes de Integração (AlunoDAOTest)
- **6 testes** - Operações CRUD de alunos

### Testes de Integração (ProfessorDAOTest)
- **5 testes** - Operações CRUD de professores

### Testes Unitários (PessoaTest)
- **2 testes** - Validação de modelo

### Testes de View
- **8 testes** - Validação de interface e cálculos

**Total: 56 testes executados com sucesso! ✅**

## 📈 Cobertura de Código

O relatório JaCoCo mostra:
- Cobertura por pacote (DAO, model, view, util, principal)
- Linhas de código cobertas vs não cobertas
- Métodos testados vs não testados
- Branches (condicionais) cobertas

## 🔍 Visualizando os Relatórios

### No Windows:
1. Abra o Explorador de Arquivos
2. Navegue até: `C:\Users\LFSCorr\Documents\a3_GQS_unisul\target\site\`
3. Clique duas vezes em `surefire-report.html` ou `jacoco/index.html`

### Via Linha de Comando:
```bash
# Windows
start target\site\surefire-report.html
start target\site\jacoco\index.html

# Linux/Mac
xdg-open target/site/surefire-report.html
open target/site/jacoco/index.html
```

## 📝 Notas Importantes

- Os relatórios são gerados automaticamente após cada execução de testes
- Os relatórios HTML são estáticos e podem ser compartilhados
- O relatório JaCoCo mostra o código fonte com destaque para linhas cobertas (verde) e não cobertas (vermelho)
- Os relatórios são atualizados a cada execução de `mvn test`

---

**Última atualização:** Relatórios gerados com sucesso após execução dos testes.

