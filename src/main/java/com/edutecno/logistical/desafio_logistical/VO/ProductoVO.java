package com.edutecno.logistical.desafio_logistical.VO;

import java.util.List;

import com.edutecno.logistical.desafio_logistical.model.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoVO {

	private List<Producto> lista_productosVO;
	private String mensaje;
	private String codigo;
	
}
