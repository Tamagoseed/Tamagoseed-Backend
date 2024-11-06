package com.tamagoseed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import com.tamagoseed.model.Cliente;
import com.tamagoseed.model.ClienteLogin;
import com.tamagoseed.repository.ClienteRepository;
//import com.tamagoseed.security.JwtService;
import com.tamagoseed.security.JwtService;


@Service
public class ClienteService {
	

	    @Autowired
	    private ClienteRepository clienteRepository;

	    @Autowired
	    private JwtService jwtService;
	    
	    @Autowired
	    private AuthenticationManager authenticationManager;

	    public Optional<Cliente> cadastrarCliente(Cliente cliente) {

	        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent() || clienteRepository.findByCpf(cliente.getCpf()).isPresent())
	            return Optional.empty();

	        cliente.setSenha(criptografarSenha(cliente.getSenha())); 

	        return Optional.of(clienteRepository.save(cliente));
	        
	        }
	    
	    private String criptografarSenha(String senha) {

	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        return encoder.encode(senha);
	    }
	        
	        public Optional<Cliente> atualizarCliente(Cliente cliente) {

	            if(clienteRepository.findById(cliente.getId()).isPresent()) {

	                Optional<Cliente> buscaUsuario = clienteRepository.findByEmail(cliente.getEmail());

	                if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != cliente.getId()))
	                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

	                cliente.setSenha(criptografarSenha(cliente.getSenha()));

	                return Optional.ofNullable(clienteRepository.save(cliente));

	            }

	            return Optional.empty();

	        }
	        
	        public Optional<ClienteLogin> autenticarCliente(Optional<ClienteLogin> clienteLogin) {

	            var credenciais = new UsernamePasswordAuthenticationToken(clienteLogin.get().getEmail(),
	                   clienteLogin.get().getSenha());

	            Authentication authentication = authenticationManager.authenticate(credenciais);

	            if (authentication.isAuthenticated()) {

	                Optional<Cliente> cliente = clienteRepository.findByEmail(clienteLogin.get().getEmail());

	                if (cliente.isPresent()) {
	                	
	                	clienteLogin.get().setId(cliente.get().getId());
	                    clienteLogin.get().setRazaoSocial(cliente.get().getRazaoSocial());
	                    clienteLogin.get().setCnpj(cliente.get().getCpf());
	                    clienteLogin.get().setFoto(cliente.get().getFoto());
	                    clienteLogin.get().setToken(gerarToken(clienteLogin.get().getEmail()));
	                    clienteLogin.get().setSenha("");

	                   return clienteLogin;

	                }

	            }
	            return Optional.empty();
	        }

	        private String gerarToken(String usuario) {
	            return "Bearer " + jwtService.generateToken(usuario);
	        }
	                }