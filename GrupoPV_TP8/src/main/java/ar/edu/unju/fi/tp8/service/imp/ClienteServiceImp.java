package ar.edu.unju.fi.tp8.service.imp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Cliente;
import ar.edu.unju.fi.tp8.service.IClienteService;
import ar.edu.unju.fi.tp8.util.TablaCliente;

@Service("clienteImp")
public class ClienteServiceImp implements IClienteService{
	
	private static final Log LOGGER = LogFactory.getLog(ClienteServiceImp.class);
	
	private List<Cliente> clientes;

	@Override
	public void addCliente(Cliente cliente) {
		LOGGER.info("SERVICE: IClienteService -> ClienteServiceImp");
		LOGGER.info("METHOD: addCliente()");
		LOGGER.info("RESULT: agrega un objeto cliente a la lista");
		this.clientes.add(cliente);	
	}

	@Override
	public List<Cliente> getClientes() {
		LOGGER.info("SERVICE: IClienteService -> ClienteServiceImp");
		LOGGER.info("METHOD: getClientes()");
		LOGGER.info("RESULT: devuelve una lista de los clientes agregados");
		return clientes;
	}

	@Override
	public void generarListaClientes() {
		LOGGER.info("SERVICE: IClienteService -> ClienteServiceImp");
		LOGGER.info("METHOD: generarListaClientes()");
		LOGGER.info("RESULT: Se crea un cliente cargado por defecto y se genera una tabla agregando al cliente creado");
		clientes = TablaCliente.clientes;
		clientes.add(new Cliente("DNI", 12345678, "Cosme Fulanito", "cosmeFulanito@gmail.com", "contraseña", LocalDate.of(1990,01,19), 31, 220, 5308624, LocalDate.of(2020, 05, 20)));
	}

	@Override
	public void guardarCliente(Cliente cliente) {
		LOGGER.info("SERVICE: IClienteService -> ClienteServiceImp");
		LOGGER.info("METHOD: guardarCliente() -- PARAMS: cliente'"+cliente+"'");
		LOGGER.info("RESULT: En el caso de que la lista de clientes sea nula se realiza un llamado al metodo generarListaClientes(), en el caso de que no lo sea se agrega el cliente ala lista");
		if(clientes == null) {
			generarListaClientes();
		}
		clientes.add(cliente);
	}

	@Override
	public Optional<Cliente> getClientePorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public void borrarCliente(Long id) {

		// TODO Auto-generated method stub
		
	}

}
