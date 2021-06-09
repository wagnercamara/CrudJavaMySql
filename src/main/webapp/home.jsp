<%@ page import="classe.*"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Produto"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE>

<html>
<head>

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="./css/materialize.min.css"
	media="screen,projection" />

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<meta charset=UTF-8>

<title>Estoque</title>

<style>
#espaco{
height: 15px;
}

</style>

</head>

<body>

	<div id="NavBar">
		<jsp:include page='navBar.jsp' />
	</div>

	<div id="dados">


		<table class="highlight centered">
			<thead>
				<tr>
					<th>idItem</th>
					<th>Nome</th>
					<th>Valor Item</th>
					<th>Quant. Est.</th>
					<th>%</th>					
					<th>Descont. Uni.</th>
					<th>Valor liq. Uni.</th>
					<th colspan=2>Ações</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="produto" items="${produtos}">
					<tr>
						<td><c:out value="${produto.id}" /></td>
						<td><c:out value="${produto.nome}" /></td>
						<td><fmt:formatNumber value="${produto.valor}" pattern="#,#00.00#"/></td>
						<td><c:out value="${produto.qtd}" /></td>
						<td><c:out value="${produto.desconto}" /></td>
						<td><fmt:formatNumber value="${(produto.valor * (produto.desconto/100))}" pattern="#,#00.00#"/></td>
						<td><fmt:formatNumber value="${produto.valor-(produto.valor * (produto.desconto/100))}" pattern="#,#00.00#"/></td>

						<td><a
							class="btn-floating btn-large waves-effect waves-light blue"
							href="Page?action=edit&id=<c:out value="${produto.id}"/>"><i
								class="material-icons">mode_edit</i></a></td>
						<td><a
							class="btn-floating btn-large waves-effect waves-light red"
							href="Page?action=delete&id=<c:out value="${produto.id}"/>"><i
								class="material-icons">delete</i></a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		
		<div id="espaco"></div>
		
		<a class="waves-effect waves-light btn" href="Page?acation=cadastro"><i
			class="material-icons left">save</i>Cadastrar Novo Produto</a> </a>

	</div>
	<div id="espaco"></div>
	<jsp:include page='footer.jsp' />

	<!--JavaScript at end of body for optimized loading-->
	<script type="text/javascript" src="./js/materialize.min.js"></script>
</body>
</body>
</html>