
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

</head>

<body>

	<div id="NavBar">
		<jsp:include page='navBar.jsp' />
	</div>

	<div id="dados">


		<table>
			<thead>
				<tr>
					<th>idItem</th>
					<th>Nome</th>
					<th>Valor Item</th>
					<th>Quant. Est.</th>
					<th>Valor liq. Uni.</th>
					<th>Descont. Uni.</th>
					<th colspan=2>Ações</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="produto" items="${produtos}">
					<tr>
						<td><c:out value="${produto.id}" /> </td>
						<td><c:out value="${produto.nome}" /> </td>
						<td><c:out value="${produto.valor}" /> </td>
						<td><c:out value="${produto.qtd}" /> </td>
						<td><c:out value="${produto.desconto}" /> </td>
						<td><c:out value="${produto.valor}" /> </td>
						<td><c:out value="${produto.valor}" /> </td>
						
						<td><a href="Page?action=edit&id=<c:out value="${produto.id}"/>">Update</a></td>
						<td><a href="Page?action=delete&id=<c:out value="${produto.id}"/>">Delete</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		
	</div>

	<jsp:include page='footer.jsp' />

	<!--JavaScript at end of body for optimized loading-->
	<script type="text/javascript" src="./js/materialize.min.js"></script>
</body>
</body>
</html>

<div class="row">
	
	<form class="col s12" action="cadastro" method="post">
		<div class="row">
		
			<div class="input-field col s6">
				<input id="idProd" type="number" class="validate" name="idProd"> <label
					for="idProd">idProd</label>
			</div>
			
			<div class="input-field col s6">
				<input id="nome_produto" type="text" class="validate" name="nome"> <label
					for="nome_produto">Nome do Produto</label>
			</div>
			
			<div class="input-field col s6">
				<input id="valor" type="number" class="validate" name="valor"> <label
					for="valor">Valor do Produto</label>
			</div>
			
			<div class="input-field col s6">
				<input id="desconto" type="number" class="validate" name="desconto"> <label
					for="desconto">Desconto porcentagem não ultiliza o %</label>
			</div>
			
		</div>
			<input id="botao" value="Salvar" type="Submit" name="param">
	</form>
	
</div>


