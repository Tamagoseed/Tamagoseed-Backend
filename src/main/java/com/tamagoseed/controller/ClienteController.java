package com.tamagoseed.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tamagoseed.model.ClienteLogin;
import com.tamagoseed.model.Cliente;
import com.tamagoseed.repository.ClienteRepository;
import com.tamagoseed.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
	
	@Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> postCliente(@RequestBody @Valid Cliente cliente) {

        return clienteService.cadastrarCliente(cliente)
                .map(resposta-> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/logar")
    public ResponseEntity<ClienteLogin> autenticarCliente(@RequestBody Optional<ClienteLogin> clienteLogin){

        return clienteService.autenticarCliente(clienteLogin)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    
    @PutMapping("/atualizar")
    public ResponseEntity<Cliente> putUsuario(@Valid @RequestBody Cliente cliente) {

        return clienteService.atualizarCliente(cliente)
            .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
    
    @GetMapping("/all")
    public ResponseEntity <List<Cliente>> getAll(){

        return ResponseEntity.ok(clienteRepository.findAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        return clienteRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.notFound().build());
    }
	
	

}