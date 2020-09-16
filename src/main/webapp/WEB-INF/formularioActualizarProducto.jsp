<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Actualizar productos</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function inicio() {

		window.location.href = "/home";
	}
</script>
</head>

<body>

	<div class="container-fluid">
		<div class="col-sm-12">
			<h1 class="mt-3 text-center">Producto a modificar</h1><br>
			<div class="row">
				<div class="col">
					<h4 class="text-left">Por favor, rellene los campos con la
						nueva información del producto</h4>
				</div>
			</div>
			<br> <br>

			<div class="col-sm-4">
				<form action="/formUpdateProducto" method="POST">
					<div class="form-group row">
						<label for="inputId" class="col-sm-2 col-form-label">ID</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="inputId"
								name="infoModUser"
								value="${InfoPlaceHolder.getId_producto()}" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputTitulo" class="col-sm-2 col-form-label">Código</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="inputTitle"
								name="infoModUser"
								placeholder="${InfoPlaceHolder.getCodigo()}" required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputAutor" class="col-sm-2 col-form-label">Nombre</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="inputAutor"
								name="infoModUser"
								placeholder="${InfoPlaceHolder.getNombre()}" required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputImprenta" class="col-sm-2 col-form-label">Precio</label>
						<div class="col-sm-6">
							<input type="number" class="form-control" id="inputImprenta"
								name="infoModUser"
								placeholder="${InfoPlaceHolder.getPrecio()}" required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputAnio" class="col-sm-2 col-form-label">Stock</label>
						<div class="col-sm-6">
							<input type="number" class="form-control" id="inputAnio"
								name="infoModUser"
								placeholder="${InfoPlaceHolder.getStock()}" required>
						</div>
					</div>
									
				<div class="text-left mr-3">
				<button class="btn btn-success" type="submit">Modificar</button>
					<button class="btn btn-success " onclick="inicio()">Volver</button>
				</div>
				</form>

			</div>

		</div>
	</div>
</body>
</html>