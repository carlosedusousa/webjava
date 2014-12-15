<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../css/bootstrap-theme.min.css" />
	<link rel="stylesheet" type="text/css" href="../css/default.css" />
	<script src="../js/jquery-2.1.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>

	<title>Categorias</title>
</head>
<body>
    <div class="container">
        <h1>Adicionando uma Categoria</h1>
        <h3>Preencha o formulário abaixo e clique em enviar</h3>
        <form method="POST" action="/trabalho/categorias" class="form-horizontal">
            <div class="form-group">
                <label for="nome" class="col-sm-1 control-label">Nome</label>
                <div class="col-sm-10">
                    <input type="text" id="categoria" name="categoria" placeholder="Nome da Categoria" class="form-control" />
                </div>
            </div>

            <div class="form-group">
                <label for="palavra" class="col-sm-1 control-label">Palavras</label>
                <div class="col-sm-10">
                    <input type="text" id="palavras" name="texto" placeholder="Palavras Similares" class="form-control" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-10">
                    <input type="submit" value="Enviar">
                </div>
            </div>
        </form>
    </div>
</body>
</html>