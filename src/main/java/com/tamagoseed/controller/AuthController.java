package com.tamagoseed.controller;

import com.tamagoseed.model.Cliente;
import com.tamagoseed.security.JwtTokenProvider;
import com.tamagoseed.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente cliente) {
        // Procurar o cliente no banco de dados usando o email
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(cliente.getEmail());

        if (clienteOptional.isPresent()) {
            Cliente clienteBanco = clienteOptional.get();

            // Verificar se a senha corresponde
            if (new BCryptPasswordEncoder().matches(cliente.getSenha(), clienteBanco.getSenha())) {
                // Gerar o token JWT
                String token = jwtTokenProvider.generateToken(clienteBanco);
                return ResponseEntity.ok("Bearer " + token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cliente não encontrado");
        }
    }
}
