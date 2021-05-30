package ar.edu.unju.fi.tp8.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Cliente;
import ar.edu.unju.fi.tp8.repository.IClienteDAO;
import ar.edu.unju.fi.tp8.service.IClienteService;

@Service("clienteServiceMysql")
public class ClienteServiceImpMysql implements IClienteService{
	
	@Autowired
	IClienteDAO clienteDAO;

	@Override
	public void guardarCliente(Cliente cliente) {
		clienteDAO.save(cliente);
	}

	@Override
	public void addCliente(Cliente cliente) {
		clienteDAO.save(cliente);
	}

	@Override
	public List<Cliente> getClientes() {
		List<Cliente> clientes = (List<Cliente>) clienteDAO.findAll();
		return clientes;
	}

	@Override
	public void generarListaClientes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Cliente> getClientePorId(Long id) {
		Optional<Cliente>cliente = clienteDAO.findById(id);
		return cliente;
	}

	public void borrarCliente(Long id) {
		clienteDAO.deleteById(id);
		
	}

}
