
<%@ page import="br.wagner.classe.*"%>
<%@ page import="java.util.*"%>
<%@ page import="br.wagner.model.Produto"%>

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

<link rel="shortcut icon" href="./icon/edita.ico">

<style>
#espaco {
	height: 15px;
}

#nome {
	text-transform: uppercase;
}

::-webkit-input-placeholder {
	text-transform: lowercase;
}

:-moz-placeholder {
	text-transform: lowercase;
}

::-moz-placeholder {
	text-transform: lowercase;
}

:-ms-input-placeholder {
	text-transform: lowercase;
}
</style>

</head>

<body>

	<%
	String action = request.getParameter("action");
	%>

	<div id="NavBar">

		<nav class="nav-extended">
			<div class="nav-wrapper grey">

				<%
				if (action.equalsIgnoreCase("edit")) {
				%>

				<a href="#" class="brand-logo">Editar de Produto</a>

				<%
				} else {
				%>

				<a href="#" class="brand-logo">Cadastrar Novo Produto</a>

				<%
				}
				%>
			</div>
		</nav>

	</div>

	<div id="espaco"></div>

	<div id="dados">

		<div class="row">
			<form class="col s12" method="post" action="Page?action=insert"
				name="insert">

				<div class="row">


					<div class="input-field col s6 hide">
						<i class="material-icons prefix">chevron_right</i> <input
							id="idProduto" type="number" name="idProduto"
							value="<c:out value="${produto.id}" />" readonly="readonly">
						<label for="chevron_right">Id Produto</label>
					</div>


					<div class="input-field col s6">
						<i class="material-icons prefix">weekend</i>

						<%
						if (action.equalsIgnoreCase("edit")) {
						%>
						<input id="nome" type="text" class="validate" name="nome" max="250"
							value="<c:out value="${produto.nome}" />" readonly="readonly">

						<%
						} else {
						%>
						<input autofocus required id="nome" type="text" class="validate" max="250"
							name="nome" value="<c:out value="${produto.nome}" />">
						<%
						}
						%>

						<label for="weekend">Nome Produto</label>
					</div>


					<div class="input-field col s6">
						<i class="material-icons prefix">attach_money</i>

						<%
						if (action.equalsIgnoreCase("edit")) {
						%>

						<input required autofocus id="valor" type="number"
							class="validate" step="0.010" name="valor"
							value="<c:out value="${produto.valor}" />">
						<%
						} else {
						%>
						<input required id="valor" type="number" class="validate"
							step="0.010" name="valor"
							value="<c:out value="${produto.valor}" />">
						<%
						}
						%>

						<label for="attach_money">Valor Produto</label>
					</div>

					<div class="input-field col s6">
						<i class="material-icons prefix">money_off</i> <input required
							id="desconto" type="number" class="validate" name="desconto"
							value="<c:out value="${produto.desconto}" />"> <label
							for="money_off">Desconto %</label>
					</div>

					<div class="input-field col s6">
						<i class="material-icons prefix">add</i> <input required id="qtd"
							type="number" class="validate" name="qtd"
							value="<c:out value="${produto.qtd}" />"> <label
							for="add">Quantidade</label>
					</div>


				</div>

				<button class="btn waves-effect waves-light right" type="submit"
					name="action">
					Submit <i class="material-icons right">send</i>
				</button>

				<a class="waves-effect waves-light btn" href="Page?action=home"><i
					class="material-icons left">arrow_back</i>Voltar</a>

			</form>
		</div>

	</div>


	<%
	try {

		String check = (String) request.getAttribute("check");

		if ((check.equalsIgnoreCase("error")) && (check != null)) {
	%>

	<div>
		<h6 class="center-align red">Erro ao tentar Salvar Produto. Tente
			novamente mais tarde.</h6>
	</div>

	<%
	} else if ((check.equalsIgnoreCase("repetido")) && (check != null)) {
	%>

	<div>
		<h6 class="center-align blue">Nome do Produto Já foi Ultilizado.</h6>
	</div>

	<%
	}
	} catch (Exception e) {

	}
	%>


	<jsp:include page='footer.jsp' />

	<script>
		document.getElementById("valor").addEventListener("change", function() {
			this.value = parseFloat(this.value).toFixed(2);
		});
	</script>

	<!--JavaScript at end of body for optimized loading-->
	<script type="text/javascript" src="./js/materialize.min.js"></script>
</body>
</body>
</html>


