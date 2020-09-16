package com.edutecno.logistical.desafio_logistical.service;

import org.springframework.data.domain.Pageable;

import com.edutecno.logistical.desafio_logistical.VO.PageVO;
import com.edutecno.logistical.desafio_logistical.VO.ProductoVO;
import com.edutecno.logistical.desafio_logistical.model.Producto;

public interface ProductoService {

	// Método para agregar un Producto
	public ProductoVO addProducto(Producto producto);
	
	// Método para eliminar un Producto
	public ProductoVO deleteProducto(Producto producto);
	
	// Método para actualizar la información de un Producto
	public ProductoVO updateProducto(Producto producto);
	
	// Método para buscar un Producto por nombre del producto
	public PageVO findByNombreProducto(String busqueda, Pageable pageable);
	
	// Método para mostrar la lista completa
	public ProductoVO findAll();
	
	// Método para seleccionar la información de un producto mediante su ID
	public ProductoVO findAllById(int idP);
	
	// Método que devuelve los registros CRUD con su paginación correspondiente
	public PageVO getPage(Integer page, Integer number);
}
