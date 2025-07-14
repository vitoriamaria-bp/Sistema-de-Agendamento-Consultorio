-- Cria o Banco de Dados.
CREATE DATABASE db_consultorio;
GO

-- Começa a utilizar o banco criado.
USE db_consultorio;
GO

-- Armazena os dados de login dos usuários do sistema.
CREATE TABLE tbl_usuarios (
    usuarioid INT PRIMARY KEY IDENTITY(1,1),
    nomeusuario VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(50) NOT NULL
);
GO

-- Usuário para testes.
INSERT INTO tbl_usuarios (nomeusuario, senha) VALUES ('admin', 'admin123');
GO

-- Cria tabela de pacientes.
CREATE TABLE tbl_pacientes (
    pacienteid INT PRIMARY KEY IDENTITY(1,1),
    nomecompleto VARCHAR(255) NOT NULL,
    datanascimento DATE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    enderecoid INT,
    CONSTRAINT FK_Paciente_Endereco FOREIGN KEY (enderecoid) REFERENCES tbl_enderecos(enderecoid)
);
GO

--Cria tabela de endereços.
CREATE TABLE tbl_enderecos (
    enderecoid INT PRIMARY KEY IDENTITY(1,1),
    cep VARCHAR(9),
    logradouro VARCHAR(255),
    numero VARCHAR(10),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado CHAR(2)
);
GO

SELECT * FROM tbl_usuarios
SELECT * FROM tbl_pacientes
SELECT * FROM tbl_enderecos