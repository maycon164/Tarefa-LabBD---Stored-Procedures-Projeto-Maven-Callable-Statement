<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello!!!</title>
</head>

<body>

	<section>
		<div>
			<h2>CADASTRO DE CLIENTE</h2>
			<form method="POST" action="clienteInsert">
				<input type="text" placeholder="cpf" name="cpf"> <br> <input
					type="text" placeholder="nome" name="nome"> <br> <input
					type="text" placeholder="email" name="email"> <br> <input
					type="text" placeholder="limite de credito" name="limite">
				<br> 
				<input type="text" placeholder="data de nascimento" name="nascimento"> 
				<br> 
				<input type="submit" value="Enviar">
			</form>
		</div>
	</section>

	<section>
		<h2>LISTA DOS CLIENTES</h2>
		<br>
		<h3>CAMPO DE PESQUISA</h3>

		<form action="clientePesquisa" method="POST">
			<input type="text" name="pesquisa"> 
			<input type="submit" value="Pesquisar" />
		</form>

		<form action="clientes" method="POST">
			<input type="submit" value="Buscar Todos" />
		</form>

		<table border="1" class="center espacamento-top">
			<thead>
				<tr>
					<th>cpf</th>
					<th>nome</th>
					<th>email</th>
					<th>limite credito</th>
					<th>data nascimento</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty cliente }">
					<tr>
						<td><c:out value="${cliente.cpf}" /></td>
						<td><c:out value="${cliente.nome}" /></td>
						<td><c:out value="${cliente.email}" /></td>
						<td><c:out value="${cliente.limiteCredito}" /></td>
						<td><c:out value="${cliente.dataNascimento}" /></td>
					</tr>
				</c:if>

				<c:if test="${not empty clientes }">

					<c:forEach items="${clientes }" var="c">
						<tr>
							<td><c:out value="${c.cpf}" /></td>
							<td><c:out value="${c.nome}" /></td>
							<td><c:out value="${c.email}" /></td>
							<td><c:out value="${c.limiteCredito}" /></td>
							<td><c:out value="${c.dataNascimento}" /></td>
						</tr>
					</c:forEach>

				</c:if>
			</tbody>

		</table>

	</section>
</body>
</html>