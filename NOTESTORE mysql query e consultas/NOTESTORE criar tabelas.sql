create database testes;
use testes;

CREATE TABLE filial (
    ID_filial INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    estado VARCHAR(2) NOT NULL
);

CREATE TABLE produto (
    ID_produto INT AUTO_INCREMENT PRIMARY KEY,
    ID_filial INT,
    nome_produto VARCHAR(25) NOT NULL,
    marca_produto VARCHAR(25),
    quantidade_produto INT NOT NULL,
    valor_produto DOUBLE NOT NULL,
    desc_produto VARCHAR(50),
    data_entrada DATETIME NOT NULL,
    FOREIGN KEY (id_filial)
        REFERENCES filial (id_filial)
);

CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    id_filial INT,
    nome_cliente VARCHAR(30) NOT NULL,
    nasc_cliente DATE,
    sexo_cliente VARCHAR(10),
    cpf_cliente VARCHAR(15),
    telefone_cliente VARCHAR(15),
    email_cliente VARCHAR(30),
    uf_cliente VARCHAR(2),
    cidade_cliente VARCHAR(20),
    cep_cliente VARCHAR(15),
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
    quantidade_produto INT,
    id_produto INT,
    id_venda INT,
    FOREIGN KEY (id_produto)
        REFERENCES produto (id_produto),
    FOREIGN KEY (id_venda)
        REFERENCES venda (id_venda)
);
