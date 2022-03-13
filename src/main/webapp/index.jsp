<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>Hello!!!</title>

				<link rel="stylesheet" type="text/css" href="./css/style.css" media="screen" />
			</head>

			<body>

				<section>
					<c:if test="${not empty erro }">
						<h1>
							<c:out value="${erro}" />
						</h1>
					</c:if>

					<div class="center">
						<h2>CADASTRO DE CLIENTE</h2>
						<form method="POST" action="cliente">


							<input type="text" placeholder="cpf" name="cpf">
							<br>
							<input type="text" placeholder="nome" name="nome">
							<br>
							<input type="text" placeholder="email" name="email">
							<br>
							<input type="text" placeholder="limite de credito" name="limite">
							<br>
							<input type="text" placeholder="data de nascimento" name="nascimento">
							<br>
							<input type="submit" value="Enviar">


						</form>
					</div>
				</section>

				<section class="center sec-listar">
					<h2 class="center-text">LISTA DOS CLIENTES</h2>
					<br>
					<h3 class="center-text">CAMPO DE PESQUISA</h3>

					<div>
						<input type="text" id="pesquisa">
						<a href="#" id="btnPesquisar" class="btn">Pesquisar</a>
					</div>

					<br>
					<a class="btn" href="cliente?action=listartodos">Listar Todos</a>

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
									<td>
										<c:out value="${cliente.cpf}" />
									</td>
									<td>
										<c:out value="${cliente.nome}" />
									</td>
									<td>
										<c:out value="${cliente.email}" />
									</td>
									<td>
										<c:out value="${cliente.limiteCredito}" />
									</td>
									<td>
										<c:out value="${cliente.dataNascimento}" />
									</td>
									<td><a class="btn" href="cliente?action=edit&cpf=<c:out value="
											${cliente.cpf}" />">Update</a>
									</td>
									<td><a class="btn" href="cliente?action=delete&cpf=<c:out value="
											${cliente.cpf}" />">Delete</a>
									</td>

								</tr>
							</c:if>

							<c:if test="${not empty clientes }">

								<c:forEach items="${clientes }" var="c">
									<tr>
										<td>
											<c:out value="${c.cpf}" />
										</td>
										<td>
											<c:out value="${c.nome}" />
										</td>
										<td>
											<c:out value="${c.email}" />
										</td>
										<td>
											<c:out value="${c.limiteCredito}" />
										</td>
										<td>
											<c:out value="${c.dataNascimento}" />
										</td>
										<td><a href="cliente?action=edit&cpf=<c:out value=" ${c.cpf}" />">Update</a>
										</td>
										<td><a href="cliente?action=delete&cpf=<c:out value=" ${c.cpf}" />">Delete</a>
										</td>

									</tr>
								</c:forEach>

							</c:if>
						</tbody>

					</table>

				</section>

				<script type="text/javascript">
					let btnPesquisar = document.getElementById("btnPesquisar");

					btnPesquisar.addEventListener("click", event => {
						event.preventDefault();

						console.log("SOU RONALDO")
						let barraPesquisa = document.getElementById("pesquisa");
						let cpf = barraPesquisa.value;

						window.location.href = "cliente?action=pesquisa&cpf=" + cpf;

						/*fetch("cliente?action=pesquisa?cpf" + cpf, {
							method: "get"
						});*/

					});

				</script>

			</body>

			</html>