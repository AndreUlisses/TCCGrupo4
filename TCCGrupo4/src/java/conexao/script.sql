DROP DATABASE IF EXISTS DB_TCCGrupo4;

CREATE DATABASE DB_TCCGrupo4;
USE DB_TCCGrupo4;

CREATE TABLE USUARIO (
    IDUSUARIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT
    , EMAIL VARCHAR(100)
    , SENHA VARCHAR(45)
);

CREATE TABLE PESSOA (
	IDPESSOA INT NOT NULL PRIMARY KEY AUTO_INCREMENT
	, IDUSUARIO INT 
	, NOME VARCHAR(100)
	, TELRESIDENCIAL VARCHAR(15)
	, TELCELULAR VARCHAR(15)
	, RUA VARCHAR(100)
	, NUMERO INT
	, COMPLEMENTO VARCHAR(100)
	, BAIRRO VARCHAR(100)
	, CEP CHAR(8)
	, CIDADE VARCHAR(100)
	, ESTADO CHAR(2)
	, CPF CHAR(11)
	, CNPJ CHAR(14)
	, EMAIL VARCHAR(110) NOT NULL
	, CONSTRAINT FK_PESSOA_USUARIO FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO(IDUSUARIO) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE INNODB;

CREATE TABLE FUNCIONARIO (
	IDFUNCIONARIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT
	, IDUSUARIO INT 
	, NOME VARCHAR(100)
	, TELCELULAR VARCHAR(15)
	, ADM ENUM ('SIM', 'NAO')
	, EMAIL VARCHAR(100)
	, CONSTRAINT FK_FUNCIONARIO_USUARIO  FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO(IDUSUARIO) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE INNODB;

CREATE TABLE PREVENTIVA (
	IDPREVENTIVA INT NOT NULL PRIMARY KEY AUTO_INCREMENT
	, IDPESSOA INT NOT NULL
	, DESCRICAO TEXT
	, INTERVALO INT
	, PERIODO ENUM('DIA', 'MÊS', 'ANO')
	, CONSTRAINT FK_PRECENTIVA_PESSOA FOREIGN KEY (IDPESSOA) REFERENCES PESSOA(IDPESSOA) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE INNODB;

CREATE TABLE CHAMADO (
	IDCHAMADO INT NOT NULL PRIMARY KEY AUTO_INCREMENT
	, IDPESSOA INT 
	, IDPREVENTIVA INT
	, TELEFONE_CONTATO VARCHAR(15)
	, TELEFONE_CELULAR VARCHAR(15)
	, DT_CHAMADO DATE
	, NOME VARCHAR(100)
	, DESCRICAO TEXT
	, RUA VARCHAR(100)
	, NUMERO INT
	, COMPLEMENTO VARCHAR(100)
	, BAIRRO VARCHAR(100)
	, CEP CHAR(8)
	, CIDADE VARCHAR(100)
	, ESTADO CHAR(8)
	, SITUACAO ENUM('PENDENTE', 'EM ANDAMENTO', 'CONCLUIDO', 'CANCELADO', 'EM ESPERA')
	, CONSTRAINT FK_CHAMADO_PESSOA FOREIGN KEY(IDPESSOA) REFERENCES PESSOA (IDPESSOA) ON UPDATE CASCADE ON DELETE CASCADE
	, CONSTRAINT FK_CHAMADO_PREVENTIVA FOREIGN KEY(IDPREVENTIVA) REFERENCES PREVENTIVA (IDPREVENTIVA) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE INNODB;

CREATE TABLE OCORRENCIA (
	IDOCORRENCIA INT NOT NULL PRIMARY KEY AUTO_INCREMENT
	, IDCHAMADO INT NOT NULL
	, IDUSUARIO INT NOT NULL
	, DESCRICAO TEXT
	, DT_OCORRENCIA DATETIME 
	, CONSTRAINT FK_OCORRENCIA_CHAMADO FOREIGN KEY(IDCHAMADO) REFERENCES CHAMADO (IDCHAMADO) ON UPDATE CASCADE ON DELETE CASCADE
	, CONSTRAINT FK_OCORRENCIA_USUARIO FOREIGN KEY(IDUSUARIO) REFERENCES USUARIO (IDUSUARIO) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE INNODB;

CREATE TABLE EXECUTA (
	IDFUNCIONARIO INT NOT NULL
	, IDCHAMADO INT NOT NULL
	, CONSTRAINT PK_EXECUTA PRIMARY KEY (IDFUNCIONARIO, IDCHAMADO)
	, CONSTRAINT FK_EXECUTA_FUNCIONARIO FOREIGN KEY(IDFUNCIONARIO) REFERENCES FUNCIONARIO (IDFUNCIONARIO) ON UPDATE CASCADE ON DELETE CASCADE
	, CONSTRAINT FK_EXECUTA_CHAMADO FOREIGN KEY(IDCHAMADO) REFERENCES CHAMADO (IDCHAMADO) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE INNODB;

DELIMITER $$
CREATE TRIGGER TR_PESSOA_BEFORE_INSERT BEFORE INSERT ON PESSOA FOR EACH ROW
BEGIN

	IF EXISTS (SELECT 0 FROM USUARIO WHERE EMAIL = NEW.EMAIL) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Não é possivel incluir a pessoa, ja existe um usuário com esse email';
	ELSE
		
		INSERT INTO USUARIO(EMAIL, SENHA)VALUES(NEW.EMAIL, '123456');
		SET NEW.IDUSUARIO = LAST_INSERT_ID();

	END IF;

END $$

CREATE TRIGGER TR_PESSOA_BEFORE_UPDATE BEFORE UPDATE ON PESSOA FOR EACH ROW
BEGIN
	IF (OLD.IDUSUARIO <> NEW.IDUSUARIO)THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Não é possivel alterar o usuário associado em uma pessoa';
	END IF;

	IF (OLD.EMAIL <> NEW.EMAIL) THEN
	
		IF EXISTS (SELECT 0 FROM USUARIO WHERE EMAIL = NEW.EMAIL) THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Não é possivel incluir a pessoa, ja existe um usuário com esse email';
		ELSE
			UPDATE USUARIO SET EMAIL = NEW.EMAIL WHERE IDUSUARIO = NEW.IDUSUARIO;
		END IF;

	END IF;

END $$

CREATE TRIGGER TR_PESSOA_AFTER_DELETE AFTER DELETE ON PESSOA FOR EACH ROW
BEGIN
	DELETE FROM USUARIO WHERE IDUSUARIO = OLD.IDUSUARIO;
END $$

CREATE TRIGGER TR_FUNCIONARIO_BEFORE_INSERT BEFORE INSERT ON FUNCIONARIO FOR EACH ROW
BEGIN

	IF EXISTS (SELECT 0 FROM USUARIO WHERE EMAIL = NEW.EMAIL) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Não é possivel incluir o funcionário, ja existe um usuário com esse email';
	ELSE
		
		INSERT INTO USUARIO(EMAIL, SENHA)VALUES(NEW.EMAIL, '123456');
		SET NEW.IDUSUARIO = LAST_INSERT_ID();

	END IF;

END $$

CREATE TRIGGER TR_FUNCIONARIO_BEFORE_UPDATE BEFORE UPDATE ON FUNCIONARIO FOR EACH ROW
BEGIN
	IF (OLD.IDUSUARIO <> NEW.IDUSUARIO)THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Não é possivel alterar o usuário associado em um funcionário';
	END IF;

	IF (OLD.EMAIL <> NEW.EMAIL) THEN
	
		IF EXISTS (SELECT 0 FROM USUARIO WHERE EMAIL = NEW.EMAIL) THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Não é possivel incluir o funcionário, ja existe um usuário com esse email';
		ELSE
			UPDATE USUARIO SET EMAIL = NEW.EMAIL WHERE IDUSUARIO = NEW.IDUSUARIO;
		END IF;

	END IF;

END $$


CREATE TRIGGER TR_FUNCIONARIO_AFTER_DELETE AFTER DELETE ON FUNCIONARIO FOR EACH ROW
BEGIN
	DELETE FROM USUARIO WHERE IDUSUARIO = OLD.IDUSUARIO;
END $$

DELIMITER ;
