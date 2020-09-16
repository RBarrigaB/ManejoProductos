package com.edutecno.logistical.desafio_logistical.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edutecno.logistical.desafio_logistical.model.Producto;

@Repository
public interface ProductoDAO extends JpaRepository<Producto, Integer>{

	@Query(value="SELECT * FROM productos WHERE nombre like %?1%",
			countQuery = "SELECT count(*) FROM productos WHERE nombre like %?1%",
			nativeQuery = true)
	Page<Producto> findByProductName(String nombreProducto, Pageable pageable);
}
