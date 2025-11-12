-- Este script cria as tabelas necessárias para os testes de CRUD.

-- Tabela de Alunos (usada por AlunoDAO)
CREATE TABLE IF NOT EXISTS tb_alunos (
    id INT NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT,
    curso VARCHAR(100),
    fase INT
);

-- Tabela de Professores (usada por ProfessorDAO)
CREATE TABLE IF NOT EXISTS tb_professores (
    id INT NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT,
    campus VARCHAR(100),
    cpf VARCHAR(15),
    contato VARCHAR(20),
    titulo VARCHAR(50),
    salario DOUBLE
);