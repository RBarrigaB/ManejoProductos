package com.edutecno.logistical.desafio_logistical.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.edutecno.logistical.desafio_logistical.VO.PageVO;
import com.edutecno.logistical.desafio_logistical.VO.ProductoVO;
import com.edutecno.logistical.desafio_logistical.model.Producto;
import com.edutecno.logistical.desafio_logistical.service.ProductoService;

@Controller
public class ProductoController {

	static String cri = "";
	@Autowired
	ProductoService producto;
	PageRequest pageRequest;

	@RequestMapping(value = { "home", "/" }, method = RequestMethod.GET)
	public String listarProductos() {
		return "productos";
	}

	@RequestMapping(value = { "addProdBack" }, method = RequestMethod.GET)
	public String addProductosBack() {
		return "agregarProducto";
	}

	@RequestMapping(value = "buscadorProductos", method = RequestMethod.GET)
	public String buscadorProductos() {
		return "listarProductos";
	}

	@RequestMapping(value = "/listaProductos", method = RequestMethod.GET)
	public String filtrarProductos(ModelMap modelo,
			@RequestParam(value = "criterio", defaultValue = "") String criterio,
			@RequestParam Map<String, Object> param) {

		int page = param.get("page") != null ? (Integer.valueOf(param.get("page").toString()) - 1) : 0;
		int size = param.get("numPage") != null ? (Integer.valueOf((String) param.get("numPage"))) : 5;

		String iclean = param.get("clean") != null ? ((String) param.get("clean")) : "";

		if (!(iclean.isEmpty())) {
			System.out.println("entro al if de clean");
			modelo.addAttribute("listaProductosFilter", new ArrayList<Producto>());
			cri = "";
		} else {
			if (!(criterio.isEmpty())) {

				PageVO PageVo = producto.findByNombreProducto(criterio, PageRequest.of(page, size));

				int totalPages = PageVo.getNumberPage();

				if (totalPages > 0) {

					List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());

					modelo.addAttribute("paginas", pages);
				}

				modelo.addAttribute("listaProductosFilter", PageVo.getListaPag());
				modelo.addAttribute("mensaje", PageVo.getMensaje());
				modelo.addAttribute("codigo", PageVo.getCodigo());
				modelo.addAttribute("pageNumber", totalPages);
				modelo.addAttribute("currentPage", page + 1);
				modelo.addAttribute("next", page + 2);
				modelo.addAttribute("prev", page);
				modelo.addAttribute("criterioB", criterio);
				modelo.addAttribute("size", size);
				cri = criterio;
			} else {
				PageVO PageVo = producto.findByNombreProducto(cri, PageRequest.of(page, size));

				int totalPages = PageVo.getNumberPage();

				if (totalPages > 0) {

					List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());

					modelo.addAttribute("paginas", pages);
				}

				modelo.addAttribute("listaProductosFilter", PageVo.getListaPag());
				modelo.addAttribute("mensaje", PageVo.getMensaje());
				modelo.addAttribute("codigo", PageVo.getCodigo());
				modelo.addAttribute("pageNumber", totalPages);
				modelo.addAttribute("currentPage", page + 1);
				modelo.addAttribute("next", page + 2);
				modelo.addAttribute("prev", page);
				modelo.addAttribute("criterioB", cri);
				modelo.addAttribute("size", size);

			}
		}
		return "listarProductos";
	}

	@RequestMapping(value = "borrarProductos", method = RequestMethod.GET)
	public RedirectView eliminarProductos(@RequestParam("id") List<Integer> id, ModelMap modelo) {

		List<Integer> listaId = new ArrayList<Integer>();
		listaId = id;
		ProductoVO productos;
		List<ProductoVO> productosDelete = new ArrayList<ProductoVO>();

		for (int i = 0; i < listaId.size(); i++) {

			productosDelete.add(producto.findAllById(listaId.get(i)));
			productos = producto.deleteProducto(productosDelete.get(i).getLista_productosVO().get(0));
		}
		return new RedirectView("/paginaDelete");
	}

	@RequestMapping(value = "paginaDelete", method = RequestMethod.GET)
	public String listarProductosDelete(@RequestParam Map<String, Object> param, ModelMap modelo) {

		int page = param.get("page") != null ? (Integer.valueOf(param.get("page").toString()) - 1) : 0;

		int size = param.get("numPage") != null ? (Integer.valueOf((String) param.get("numPage"))) : 5;

		PageVO pageVo = producto.getPage(page, size);

		int totalPages = pageVo.getNumberPage();

		if (totalPages > 0) {

			List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());

			modelo.addAttribute("paginas", pages);

		}
		modelo.addAttribute("listadoProductosDelete", pageVo.getListaPag());
		modelo.addAttribute("mensaje", pageVo.getMensaje());
		modelo.addAttribute("codigo", pageVo.getCodigo());
		modelo.addAttribute("pageNumber", totalPages);
		modelo.addAttribute("currentPage", page + 1);
		modelo.addAttribute("next", page + 2);
		modelo.addAttribute("prev", page);
		modelo.addAttribute("size", size);

		return "eliminarProductos";
	}

	@RequestMapping(value = "formPlaceHolder", method = RequestMethod.GET)
	public String formInfoHolder(@RequestParam("idUp") int idProd, ModelMap modelo) {

		ProductoVO ProductointernoVO = producto.findAllById(idProd);
		Producto ProductoInternoObj = ProductointernoVO.getLista_productosVO().get(0);
		modelo.addAttribute("InfoPlaceHolder", ProductoInternoObj);
		return "formularioActualizarProducto";
	}

	@RequestMapping(value = "formUpdateProducto", method = RequestMethod.POST)
	public RedirectView formActualizarProducto(@RequestParam("infoModUser") List<String> idMod, ModelMap modelo) {

		List<String> ProductosUserMod = new ArrayList<String>();
		for (String Producto : idMod) {
			ProductosUserMod.add(Producto);
		}

		Producto Productointerno = new Producto();

		Productointerno.setId_producto(Integer.parseInt(ProductosUserMod.get(0)));
		Productointerno.setCodigo(ProductosUserMod.get(1));
		Productointerno.setNombre(ProductosUserMod.get(2));
		Productointerno.setPrecio(Integer.parseInt(ProductosUserMod.get(3)));
		Productointerno.setStock((Integer.parseInt(ProductosUserMod.get(4))));

		ProductoVO ProductoVo = producto.updateProducto(Productointerno);
		modelo.addAttribute("listadoProductosUpdate", ProductoVo.getLista_productosVO());
		modelo.addAttribute("mensaje", ProductoVo.getMensaje());
		modelo.addAttribute("codigo", ProductoVo.getCodigo());

		return new RedirectView("/updateProductos");
	}

	@RequestMapping(value = "updateProductos", method = RequestMethod.GET)
	public String actualizarProducto(@RequestParam Map<String, Object> param, ModelMap modelo) {

		int page = param.get("page") != null ? (Integer.valueOf(param.get("page").toString()) - 1) : 0;

		int size = param.get("numPage") != null ? (Integer.valueOf((String) param.get("numPage"))) : 5;

		PageVO pageVo = producto.getPage(page, size);

		int totalPages = pageVo.getNumberPage();

		if (totalPages > 0) {

			List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());

			modelo.addAttribute("paginas", pages);

		}
		modelo.addAttribute("listadoProductosUpdate", pageVo.getListaPag());
		modelo.addAttribute("mensaje", pageVo.getMensaje());
		modelo.addAttribute("codigo", pageVo.getCodigo());
		modelo.addAttribute("pageNumber", totalPages);
		modelo.addAttribute("currentPage", page + 1);
		modelo.addAttribute("next", page + 2);
		modelo.addAttribute("prev", page);
		modelo.addAttribute("size", size);

		return "actualizarProducto";
	}

	@RequestMapping(value = "addProductos", method = RequestMethod.POST)
	public String agregarProducto(@RequestParam("addProduct") List<String> ProductoAdd, ModelMap modelo) {

		String retorno = null;

		List<String> ProductosUserAdd = new ArrayList<String>();
		for (String Producto : ProductoAdd) {
			ProductosUserAdd.add(Producto);
		}

		Producto Productointerno = new Producto();
		Productointerno.setCodigo(ProductosUserAdd.get(0));
		Productointerno.setNombre((String) ProductosUserAdd.get(1));
		Productointerno.setPrecio(Integer.parseInt(ProductosUserAdd.get(2)));
		Productointerno.setStock(Integer.parseInt(ProductosUserAdd.get(3)));

		ProductoVO ProductoVo = producto.addProducto(Productointerno);

		modelo.addAttribute("listadoProductosAdd", ProductoVo.getLista_productosVO());
		modelo.addAttribute("mensaje", ProductoVo.getMensaje());
		modelo.addAttribute("codigo", ProductoVo.getCodigo());

		if (ProductoVo.getCodigo() == "1") {
			retorno = "agregarOk";
		} else if (ProductoVo.getCodigo() == "0") {
			retorno = "agregarFail";
		}
		return retorno;
	}
}
