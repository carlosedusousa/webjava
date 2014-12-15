<%@page import="java.util.List"%>
<%@page import="modelos.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
	<link rel="stylesheet" type="text/css" href="css/default.css" />
	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<title>Categorias</title>
</head>
<body>

	<%List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");%>

	<div class="container">
		<h4>Adicione uma nova Categoria</h4>
		<a href="jsp/adicionarCategoria.jsp" class="btn btn-primary">Adicionar Categorias</a> <br /><br />
	</div>

	<div class="container">

		<h4>Selecione a Categoria para remover</h4>
		<form method="POST" action="/trabalho/remover">
			<select size="1" name="categoria">
				<option selected value=todos>Todos</option>

				<%for (Categoria c : categorias) {%>

					<option selected value=<%=c.getId()%>><%=c.getCategoria()%></option>
				
				<%}%>
				
			</select> <br /> <input type="submit" value="Remover">
		</form>
		<br />

	</div>

	<div class="container">

		<h1>Listando Categorias</h1>

		<%for (Categoria c : categorias) {%>
		
			<div class="categoria">
				<h3 class="categoria nome"><%=c.getCategoria()%></h3>
	
				<%for (Palavra p : c.getPalavras()) {%>
	
				<h4><%=p.getPalavra()%></h4>
	
				<%}%>
			</div>
			
		<%}%>

	</div>

</body>
</html>