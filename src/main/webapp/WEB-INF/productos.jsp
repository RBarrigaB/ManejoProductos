<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página de inicio</title>
<meta name="Autor" content="Reinaldo Barriga B.">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/webapp/WEB-INF/css/estilos.css">
</head>
<body>
	<div class="container-fluid m-4">
		<div class="col-sm-12">
			<h1 class="text-center">Bienvenido a Empresa
				Logistical</h1>
			<br>
			<h2 class="pl-3 ">¿Qué desea hacer?</h2> <br>
			<div class="col-sm-4">
				<div class="dropdown-menu">
			
					<br>
				</div>
				<div>
					<h5>
						<a class="pl-4 dropdown-item" href="addProdBack">1) Agregar un
							producto</a>
					</h5>
					<br>
					<h5>
						<a class="pl-4 dropdown-item" href="paginaDelete">2) Eliminar
							un producto</a>
					</h5>
					<br>
					<h5>
						<a class="pl-4 dropdown-item" href="updateProductos">3)
							Actualizar un producto</a>
					</h5>
					<br>
					<h5>
						<a class="pl-4 dropdown-item" href="buscadorProductos">4) Ir al
							buscador de productos</a>
					</h5>
					<br>
				</div>
			</div>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>