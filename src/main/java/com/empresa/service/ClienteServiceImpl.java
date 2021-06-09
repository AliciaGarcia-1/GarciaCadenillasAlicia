package com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Cliente;
import com.empresa.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public List<Cliente> listaCliente() {
		return repository.findAll();
	}

	@Override
	public Cliente insertaActualizaCliente(Cliente obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminaCliente(int id) {
		repository.deleteById(id);
	}

	@Override
	public List<Cliente> listaClientePorNombresLike(String filtro) {
		return repository.listaClientePorNombresLike(filtro);
	}

	@Override
	public Optional<Cliente> obtienePorId(int idCliente) {
		return repository.findById(idCliente);
	}

	@Override
	public List<Cliente> listaPorDni(String dni) {
		return repository.findByDni(dni);
	}

	@Override
	public List<Cliente> listaPorDni(String dni, int idCliente) {
		return repository.findByDniAndIdClienteNot(dni, idCliente);
	}

	

}
