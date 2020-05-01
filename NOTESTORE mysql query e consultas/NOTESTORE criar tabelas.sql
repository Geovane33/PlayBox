-- create database notestore;
-- use notestore;

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
    cpf_cliente VARCHAR(15)  NOT NULL,
    nasc_cliente DATE NOT NULL,
    sexo_cliente VARCHAR(10),
    telefone_cliente VARCHAR(15)  NOT NULL,
    email_cliente VARCHAR(30)  NOT NULL,
    uf_cliente VARCHAR(2)  NOT NULL,
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

insert into filial values(default, 'SÃO PAULO', 'SP');
insert into filial values(default, 'RIO DE JANEIRO', 'RJ');
insert into filial values(default, 'MINAS GERAIS', 'MG');