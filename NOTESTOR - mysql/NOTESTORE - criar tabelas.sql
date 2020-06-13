-- CREATE DATABASE NOTESTORE;
CREATE TABLE filial (
    id_filial INT AUTO_INCREMENT PRIMARY KEY,
    nome_filial VARCHAR(20) NOT NULL,
    estado_filial VARCHAR(15) NOT NULL
);

CREATE TABLE produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    id_filial INT,
    nome_produto VARCHAR(25) NOT NULL,
    marca_produto VARCHAR(25),
    quantidade_produto INT NOT NULL,
    valor_produto DOUBLE NOT NULL,
    desc_produto VARCHAR(50),
    data_entrada DATE NOT NULL,
    FOREIGN KEY (id_filial)
        REFERENCES filial (id_filial)
);

CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    id_filial INT,
    nome_cliente VARCHAR(30) NOT NULL,
    cpf_cliente VARCHAR(15) UNIQUE NOT NULL,
    nasc_cliente DATE NOT NULL,
    sexo_cliente VARCHAR(10),
    telefone_cliente VARCHAR(16) NOT NULL,
    email_cliente VARCHAR(30) NOT NULL,
    uf_cliente VARCHAR(2) NOT NULL,
    cidade_cliente VARCHAR(20),
    cep_cliente VARCHAR(15) NOT NULL,
    bairro_cliente VARCHAR(20),
    numero_cliente VARCHAR(8),
    FOREIGN KEY (id_filial)
        REFERENCES filial (id_filial)
);

CREATE TABLE venda (
    id_venda INT AUTO_INCREMENT PRIMARY KEY,
    data_venda DATETIME,
    total_venda DOUBLE NOT NULL,
    id_cliente INT,
    id_filial INT,
    FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente),
    FOREIGN KEY (id_filial)
        REFERENCES filial (id_filial)
);

CREATE TABLE venda_produto (
    id_venda_produto INT AUTO_INCREMENT PRIMARY KEY,
    quantidade_produto INT NOT NULL,
    id_produto INT,
    id_venda INT,
    FOREIGN KEY (id_produto)
        REFERENCES produto (id_produto),
    FOREIGN KEY (id_venda)
        REFERENCES venda (id_venda)
);

CREATE TABLE usuariosistema (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(255),
    senha VARCHAR(255),
    perfil VARCHAR(255),
    id_filial INT,
     FOREIGN KEY (id_filial)
        REFERENCES filial (id_filial)
);

CREATE TABLE funcionario (
    id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    funcao VARCHAR(25),
    salario DOUBLE,
    id_filial INT,
    id_usuario INT,
      FOREIGN KEY (id_filial)
        REFERENCES filial (id_filial),
    FOREIGN KEY (id_usuario)
        REFERENCES usuariosistema (id_usuario) ON DELETE CASCADE
);

insert into filial values(default, 'SÃO PAULO', 'SP');
insert into filial values(default, 'RIO DE JANEIRO', 'RJ');
insert into filial values(default, 'MINAS GERAIS', 'MG');

SELECT * FROM filial;

SELECT * from usuariosistema;

SELECT * FROM funcionario f inner join usuariosistema us on us.id_usuario = f.id_usuario;

insert into usuariosistema (usuario, senha, perfil,id_filial) values  ('admin', '$2a$12$QOCya.5hab5l10NK7H27PufZwF5gnJ3DQjqB7qDFZntV08YG4FvXm', 'admin',1);
-- insert into usuariosistema (usuario, senha, perfil,id_filial) values  ('gerente', '$2a$12$QOCya.5hab5l10NK7H27PufZwF5gnJ3DQjqB7qDFZntV08YG4FvXm', 'gerente',2);
-- insert into usuariosistema (usuario, senha, perfil,id_filial) values  ('mike', '$2a$12$LRiaGx0f6hKD7ffdF0huquyeKchRVI0A10fWOx.uIN37bC/RSd9OS', 'vendedor',2);
-- insert into usuariosistema (usuario, senha, perfil,id_filial) values  ('wallan', '$2a$12$QOCya.5hab5l10NK7H27PufZwF5gnJ3DQjqB7qDFZntV08YG4FvXm', 'estoquista',2);
