# Agenda Telefonica em Java

Projeto Integrador II-A desenvolvido em Java com integracao ao banco de dados MySQL.

---

## Objetivo

Desenvolver uma aplicacao de Agenda Telefonica utilizando operacoes CRUD:

- Create
- Read
- Update
- Delete

O sistema permite cadastrar, listar, buscar, atualizar e remover contatos armazenados em banco de dados MySQL.

---

## Tecnologias Utilizadas

- Java
- JDBC
- MySQL
- VS Code
- GitHub

---

## Estrutura do Projeto

src/
- dao
- database
- main
- model

---

## Funcionalidades

- Cadastro de contatos
- Listagem de contatos
- Busca por nome
- Atualizacao de contatos
- Remocao de contatos
- Validacoes de dados
- Tratamento de excecoes
- Interface interativa no terminal

---

## Estrutura das Classes

### Contato.java
Responsavel pela entidade do sistema.

### Conexao.java
Responsavel pela conexao com o banco de dados MySQL utilizando JDBC.

### ContatoDAO.java
Responsavel pelas operacoes CRUD no banco de dados.

### AgendaTeste.java
Classe principal do sistema.

Responsavel pelo menu e interacao com o usuario via terminal.

---

## Banco de Dados

O banco exportado esta disponivel na pasta:

banco/

Arquivo:
agenda_telefonica.sql

---

## Documentacao

A documentacao do projeto esta disponivel na pasta:

documentacao/

Contendo:
- levantamento de requisitos
- diagrama de caso de uso
- materiais complementares

---

## Funcionalidades Implementadas

✔ Cadastro de contatos

✔ Listagem de contatos

✔ Busca por nome

✔ Atualizacao de contatos

✔ Remocao de contatos

✔ Validacao de telefone duplicado

✔ Validacao de email

✔ Tratamento de erros

✔ Confirmacao de remocao

✔ Interface amigavel no terminal

---

## Como Executar

1. Criar o banco de dados MySQL
2. Importar o arquivo:
agenda_telefonica.sql
3. Configurar usuario e senha do MySQL na classe:
Conexao.java
4. Executar:
AgendaTeste.java

---

## Autor

Rafael Moreira

Projeto Integrador II-A  
Pontificia Universidade Catolica de Goias  
Curso de Analise e Desenvolvimento de Sistemas
