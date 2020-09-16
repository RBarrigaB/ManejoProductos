<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscador de productos</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<script type="text/javascript">
	function ejecFirst() {
		document.forms["busquedaA"].submit();
	}
</script>
<body>
	<div class="container-fluid m-4">
		<h1>Buscador de productos</h1>
		<h3>Ingrese el nombre del producto que desea buscar</h3>
		<br>
		<div class="row">
			<div class="col-sm-5-1">
				<form action="/listaProductos" method="GET" name="busquedaA">
					Buscador de productos: <input type="text" name="criterio"
						placeholder="Ingresa criterio"> <input type="submit"
						value="Buscar">
				</form>
			</div>
			<div class="col-md-2-1 ml-2">
				<form action="/listaProductos" method="GET">
					<input type="submit" value="Limpiar" name="clean">
				</form>
			</div>
			<div class="col mt-1">
				<strong><a class="text-dark" href="/home">Volver</a></strong>
			</div>
		</div>

		<c:if test="${not empty listaProductosFilter}">
			<br>

			<div class="row">
				<div class="col text-left mt-2">
					<h5>
						<strong><c:out value="${mensaje}"></c:out></strong>
					</h5>
				</div>
				<div class="col text-right mr-5">

					<c:url value="/listaProductos" var="urln">
						<c:param name="page" value="${currentPage}" />
						<c:param name="criterio" value="${criterioB}"></c:param>
					</c:url>
					<form action="${urln}" method="get" name="pageNum">
						<input type="number" name="numPage"
							placeholder="Ingrese N° de páginas" onclick="submit"
							class="border border-success rounded mt-1">
					</form>
				</div>
			</div>
			<table class="table table-striped mt-3">
				<thead>


					<tr>
						<th scope="col">Código</th>
						<th scope="col">Nombre</th>
						<th scope="col">Precio</th>
						<th scope="col">Stock</th>
					</tr>

				</thead>
				<tbody>
					<c:forEach items="${listaProductosFilter}" var="libroT">
						<tr>

							<td><c:out value="${libroT.getCodigo()}"></c:out></td>
							<td><c:out value="${libroT.getNombre()}"></c:out></td>
							<td><c:out value="${libroT.getPrecio()}"></c:out></td>
							<td><c:out value="${libroT.getStock()}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<nav aria-label="Pagination">
				<ul class="pagination justify-content-center fixed-bottom">
					<c:url value="/listaProductos" var="urlPrev">
						<c:param name="page" value="${prev}" />
						<c:param name="criterio" value="${criterioB}"></c:param>
						<c:param name="numPage" value="${size}"></c:param>
					</c:url>

					<c:choose>
						<c:when test="${currentPage eq 1}">
							<li class="page-item disabled"><a class="page-link"
								href="${urlPrev}">&laquo;</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="${urlPrev}">&laquo;</a></li>
						</c:otherwise>
					</c:choose>

					<c:forEach items="${paginas}" var="p">
						<c:url value="/listaProductos" var="url">
							<c:param name="page" value="${p}" />
							<c:param name="criterio" value="${criterioB}"></c:param>
							<c:param name="numPage" value="${size}">></c:param>
						</c:url>
						<c:choose>
							<c:when test="${currentPage == p}">

								<li class="page-item active"><a class="page-link"
									href="${url}"><c:out value="${p}"></c:out></a></li>
							</c:when>

							<c:otherwise>
								<li class="page-item"><a class="page-link" href="${url}"><c:out
											value="${p}"></c:out></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:url value="/listaProductos" var="urlPost">
						<c:param name="page" value="${next}" />
						<c:param name="criterio" value="${criterioB}"></c:param>
						<c:param name="numPage" value="${size}">></c:param>
					</c:url>

					<c:choose>
						<c:when test="${currentPage < pageNumber}">
							<li class="page-item"><a class="page-link" href="${urlPost}">&raquo;</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled"><a class="page-link"
								href="${urlPost}">&raquo;</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</c:if>
	</div>
</body>
</html>