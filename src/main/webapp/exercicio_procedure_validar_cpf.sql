CREATE DATABASE EX_CLIENTE;
USE EX_CLIENTE;

CREATE TABLE cliente(
	cpf varchar(11) primary key,
	nome varchar(100),
	email varchar(100),
	limite_credito  decimal(7,2),
	dt_nascimento date
)

---------- PROCEDERE PARA UM REGISTRO
CREATE PROCEDURE sp_select_one(
	@cpf VARCHAR(11)
)
AS
BEGIN 
	SELECT * FROM Cliente WHERE cpf = @cpf;
	RETURN;
END

---------PROCEDURE PARA PEGAR TODOS OS REGISTROS
CREATE PROCEDURE sp_select_all
AS
BEGIN 
	SELECT * FROM Cliente;
	RETURN;
END

--------PROCEDURE PARA DELETAR REGISTRO
DROP PROCEDURE sp_delete_cliente

CREATE PROCEDURE sp_delete_cliente(
	@cpf VARCHAR(11)
)
AS
BEGIN 
	DELETE FROM cliente WHERE cpf = @cpf
END

------ PROCEDURE PARA ATUALIZAR REGISTRO
CREATE PROCEDURE sp_update_cliente(
	@cpf VARCHAR(11),
	@nome varchar(100),
	@email varchar(100),
	@limite_credito  decimal(7,2),
	@dt_nascimento date
)
AS
	DECLARE @aux INT;
BEGIN 
	UPDATE Cliente
	SET nome = @nome, email = @email, limite_credito = @limite_credito, dt_nascimento = @dt_nascimento 
	WHERE cpf = @cpf
END


-------PROCEDURE DE INSERT COM VALIDAÇÃO
CREATE PROCEDURE sp_insert_cliente
	@cpf VARCHAR(11),
	@nome varchar(100),
	@email varchar(100),
	@limite_credito  decimal(7,2),
	@dt_nascimento date
AS
	DECLARE @numerosIguais INT
	DECLARE @cpfValido INT
	BEGIN
		EXEC @numerosIguais = numerosIguais @numero = @cpf
		EXEC @cpfValido = cpfValido @numero = @cpf

		IF @numerosIguais = 0 AND @cpfValido = 1 
			BEGIN 
				INSERT INTO cliente (cpf, nome, email, limite_credito, dt_nascimento)
				VALUES (@cpf, @nome, @email, @limite_credito, @dt_nascimento);
			END
		 ELSE
		 	RAISERROR('NÃO FOI POSSIVEL INSERIR', 16, 1)
	END

DROP PROCEDURE  sp_insert_cliente
	

----- PROCEDURE QUE VERIFICA SE OS DIGITOS SÃO IGUAIS
CREATE PROCEDURE numerosIguais
	@numero AS varchar(11)
AS		
declare 
		@posicao AS int,
		@aux AS VARCHAR(1),
		@value AS INT
set @posicao = 1
set @value = 1 
BEGIN
SET @aux = SUBSTRING(@numero, @posicao, 1)
WHILE(@posicao < LEN(@numero))
	BEGIN		
		IF @aux != SUBSTRING(@numero, (@posicao + 1), 1)
			BEGIN
				SET @value = 0
				RETURN @value
				BREAK
			END
			
		SET @posicao = @posicao + 1;
		SET @aux = SUBSTRING(@numero, @posicao, 1);
	END
	RETURN @value	
END	
	
------PROCEDURE QUE VALIDA DIGITOS DO CPF
CREATE PROCEDURE cpfValido
	@numero AS VARCHAR(11)
AS
BEGIN 
	DECLARE @value INT
	SET @value = 1;
	
	IF LEN(@numero) < 11
		BEGIN 
			SET @value = 0
			RETURN @value
		END
	
	DECLARE @primeiroDigito INT,
			@segundoDigito INT
		
	EXEC @primeiroDigito = doMagic  @numero= @numero, @multiplicadorDesc = 10, @quantidadeVezes=9
	EXEC @segundoDigito = doMagic  @numero= @numero, @multiplicadorDesc = 11, @quantidadeVezes=10
	
	IF SUBSTRING(@numero, 10, 1) = CAST(@primeiroDigito AS varchar(1)) AND  SUBSTRING(@numero, 11, 1) = CAST(@segundoDigito AS varchar(1))
		BEGIN
				RETURN @value
		END
	ELSE
		SET @value = 0
		RETURN @value
END

------- magica para validar os digitos
CREATE PROCEDURE doMagic(
 @numero AS varchar(11),
 @multiplicadorDesc AS int,
 @quantidadeVezes AS int 
)
AS
BEGIN 
	DECLARE @soma int
	SET @soma = 0
	
	DECLARE @iterador int
	SET @iterador = 1
	
	WHILE(@iterador <= @quantidadeVezes)
		BEGIN
			SET @soma = @soma + (CAST(SUBSTRING(@numero, @iterador, 1) AS INT) * @multiplicadorDesc)
			SET @iterador = @iterador + 1
			SET @multiplicadorDesc  = @multiplicadorDesc  - 1
		END
	RETURN ((@soma * 10) % 11) 
END