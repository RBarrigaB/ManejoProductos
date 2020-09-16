package com.edutecno.logistical.desafio_logistical.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutecno.logistical.desafio_logistical.DAO.ProductoDAO;
import com.edutecno.logistical.desafio_logistical.VO.PageVO;
import com.edutecno.logistical.desafio_logistical.VO.ProductoVO;
import com.edutecno.logistical.desafio_logistical.model.Producto;

@Service
public class ProductoServiceImp implements ProductoService {

	@Autowired
	ProductoDAO productoDao;
	ProductoVO productoVo;
	PageVO pageVo;

	private static Logger logger = LoggerFactory.getLogger(ProductoDAO.class);

	@Override
	@Transactional
	public ProductoVO addProducto(Producto producto) {

		productoVo = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "101");
		try {
			int registroInsertado = productoDao.save(producto).getId_producto();
			if (registroInsertado > 0) {
				productoVo.setMensaje(String.format("Operación de inserción de producto, correcta"));
				productoVo.setCodigo("1");
			} else {
				productoVo.setMensaje("Error al agregar el producto");
				productoVo.setCodigo("0");
			}
		} catch (Exception ex) {
			logger.error("ProductoService : addProducto : " + ex);
		}
		return productoVo;
	}

	@Override
	@Transactional
	public ProductoVO deleteProducto(Producto producto) {

		productoVo = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "102");

		try {
			productoDao.delete(producto);
			productoVo.setMensaje(String.format("Producto eliminado correctamente"));
			productoVo.setCodigo("1");

		} catch (Exception ex) {
			logger.error("ProductoService : deleteProducto :" + ex);
		}
		return productoVo;
	}

	@Override
	@Transactional
	public ProductoVO updateProducto(Producto Producto) {

		productoVo = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error al insertar la actualización",
				"103");

		try {
			int idNewProd = productoDao.save(Producto).getId_producto();
			if (idNewProd > 0) {
				productoVo.setMensaje(String.format("Producto Id: %d, actualizado correctamente", idNewProd));
				productoVo.setCodigo("1");
			} else {
				productoVo.setMensaje("Error al actualizar la información del Producto");
				productoVo.setCodigo("0");
			}
		} catch (Exception ex) {
			logger.error("ProductoService : updateProducto :" + ex);
		}
		return productoVo;
	}

	@Override
	@Transactional(readOnly = true)
	public PageVO findByNombreProducto(String busqueda, Pageable pageable) {

		pageVo = new PageVO(new ArrayList<Producto>(),0, "Error buscando el Producto", "104");
		
		Page<Producto> ProductosBusqueda = productoDao.findByProductName(busqueda, pageable);
		try {
		
			if (!(ProductosBusqueda.isEmpty())) {
				pageVo.setListaPag(ProductosBusqueda.getContent());
				pageVo.setNumberPage(ProductosBusqueda.getTotalPages());
				pageVo.setMensaje(String.format("Resultados de su búsqueda por '%s'", busqueda));
				pageVo.setCodigo("1");
			} else {
				pageVo.setMensaje("Error en la búsqueda del Producto");
				pageVo.setCodigo("0");
			}
		} catch (Exception ex) {
			logger.error("ProductoService : findByNombreProducto :" + ex);
		}
		return pageVo;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoVO findAll() {

		productoVo = new ProductoVO(new ArrayList<Producto>(), "Error, no se encuentra la lista", "105");

		try {
			List<Producto> listaTodosLosProductos = (List<Producto>) productoDao.findAll();
			if (!(listaTodosLosProductos.isEmpty())) {
				productoVo.setLista_productosVO(listaTodosLosProductos);
				productoVo.setMensaje(String.format("Lista de Productos extraída correctamente"));
				productoVo.setCodigo("1");
			} else {
				productoVo.setMensaje("Error en la extracción del Producto");
				productoVo.setCodigo("0");
			}
		} catch (Exception ex) {
			logger.error("ProductoService : findAll :" + ex);
		}
		return productoVo;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoVO findAllById(int idProducto) {

		productoVo = new ProductoVO(new ArrayList<Producto>(), "Error, producto no encontrado", "106");

		try {

			productoVo.getLista_productosVO().add(productoDao.findById(idProducto).get());
			productoVo.setMensaje(String.format("Lista de libros extraída correctamente"));
			productoVo.setCodigo("1");

		} catch (Exception ex) {
			logger.error("ProductoService : findAllById :" + ex);
		}
		return productoVo;
	}

	@Override
	@Transactional(readOnly = true)
	public PageVO getPage(Integer paginaSize, Integer paginaNumber) {

		pageVo=new PageVO(new ArrayList<Producto>(),0,"Ha ocurrido un error","107");

		try {
			Pageable pageable = PageRequest.of(paginaSize, paginaNumber);
			Page<Producto> responsePage = productoDao.findAll(pageable);
			
			pageVo.setListaPag(responsePage.getContent());
			pageVo.setNumberPage(responsePage.getTotalPages());
			pageVo.setMensaje(String.format("Se han encontrado %d registros", pageVo.getListaPag().size()));
			pageVo.setCodigo("1");
		
		} catch (Exception ex) {
			logger.error("ProductoService: getPage:"+ ex);
		}
		return pageVo;
	}
}
