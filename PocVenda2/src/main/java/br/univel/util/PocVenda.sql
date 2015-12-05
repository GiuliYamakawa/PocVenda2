CREATE TABLE Produto (
  idProduto SERIAL  NOT NULL ,
  codigoBarras VARCHAR    ,
  descricao VARCHAR    ,
  categoria VARCHAR    ,
  unidade VARCHAR    ,
  custo NUMERIC    ,
  margemLucro NUMERIC    ,
  ativo CHAR(1)  DEFAULT 1    ,
PRIMARY KEY(idProduto));




CREATE TABLE Cliente (
  idCliente SERIAL  NOT NULL ,
  nome VARCHAR    ,
  telefone VARCHAR    ,
  endereco VARCHAR    ,
  cidade VARCHAR    ,
  estado VARCHAR    ,
  email VARCHAR    ,
  genero VARCHAR    ,
  ativo CHAR(1)  DEFAULT 1    ,
PRIMARY KEY(idCliente));




CREATE TABLE Usuario (
  idUsuario SERIAL  NOT NULL ,
  idCliente INTEGER   NOT NULL ,
  senha VARCHAR      ,
PRIMARY KEY(idUsuario)  ,
  FOREIGN KEY(idCliente)
    REFERENCES Cliente(idCliente));


CREATE INDEX Usuario_FKIndex1 ON Usuario (idCliente);


CREATE INDEX IFK_Rel_01 ON Usuario (idCliente);


CREATE TABLE Venda (
  idVenda SERIAL  NOT NULL ,
  idCliente INTEGER   NOT NULL ,
  total NUMERIC    ,
  ativo CHAR(1)  DEFAULT 1    ,
PRIMARY KEY(idVenda)  ,
  FOREIGN KEY(idCliente)
    REFERENCES Cliente(idCliente));


CREATE INDEX Venda_FKIndex1 ON Venda (idCliente);


CREATE INDEX IFK_Rel_02 ON Venda (idCliente);


CREATE TABLE Produto_Venda (
  idVenda INTEGER   NOT NULL ,
  idProduto INTEGER   NOT NULL ,
  valor NUMERIC    ,
  quantidade INTEGER      ,
PRIMARY KEY(idVenda, idProduto)    ,
  FOREIGN KEY(idProduto)
    REFERENCES Produto(idProduto),
  FOREIGN KEY(idVenda)
    REFERENCES Venda(idVenda));


CREATE INDEX Produto_has_Venda_FKIndex1 ON Produto_Venda (idProduto);
CREATE INDEX Produto_has_Venda_FKIndex2 ON Produto_Venda (idVenda);


CREATE INDEX IFK_Rel_03 ON Produto_Venda (idProduto);
CREATE INDEX IFK_Rel_04 ON Produto_Venda (idVenda);



