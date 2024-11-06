package com.tamagoseed.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamagoseed.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	    public Optional<Cliente> findByEmail(String email);
	    public Optional<Cliente> findByCpf(String cpf);

}