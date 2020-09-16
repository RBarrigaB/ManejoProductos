package com.edutecno.logistical.desafio_logistical.VO;

import java.util.List;

import com.edutecno.logistical.desafio_logistical.model.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO {
	
	
	private List<Producto> listaPag;
	private Integer numberPage;
	private String mensaje;
	private String codigo;
}
