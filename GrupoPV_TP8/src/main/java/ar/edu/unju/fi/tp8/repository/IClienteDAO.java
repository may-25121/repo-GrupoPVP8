package ar.edu.unju.fi.tp8.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tp8.model.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Long> {
	
	

}
