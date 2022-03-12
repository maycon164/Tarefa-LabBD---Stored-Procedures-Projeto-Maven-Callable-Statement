<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Cliente</title>
</head>
<body>
	<section>
		<div>
			<h2>EDITAR CLIENTE</h2>
			<form method="POST" action="cliente">
				<c:if test="${not empty clienteUpdate }">

					<input type="text" placeholder="cpf" name="cpf"
						value="<c:out value="${clienteUpdate.cpf}"/>" readonly>
					<br>
					<input type="text" placeholder="nome" name="nome"
						value="<c:out value="${clienteUpdate.nome}"/>">
					<br>
					<input type="text" placeholder="email" name="email"
						value="<c:out value="${clienteUpdate.email}"/>">
					<br>
					<input type="text" placeholder="limite de credito" name="limite" value="<c:out value="${clienteUpdate.limiteCredito}"/>" >
					<br>
					<input type="text" placeholder="data de nascimento"
					<fmt:formatDate value="${clienteUpdate.dataNascimento}" pattern="yyyy-MM-dd" var="parsedDate" />
						name="nascimento" value="<c:out value="${parsedDate}"/>" >
					<br>
					
					<input type="submit" value="Salvar">

				</c:if>
			</form>
		</div>
	</section>
</body>
</html>