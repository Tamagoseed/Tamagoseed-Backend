package com.tamagoseed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tamagoseed.model.Cliente;
import com.tamagoseed.repository.ClienteRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tamagoseed.repository")
public class TamagoseedApplication {
	

    @Autowired
    private ClienteRepository clienteRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(TamagoseedApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            // Crie um novo cliente com senha criptografada
            Cliente cliente = new Cliente(null, null, null, null, null, null);
            cliente.setEmail("seed01ti@gmail.com");
            cliente.setSenha(new BCryptPasswordEncoder().encode("Tamago@123"));
            clienteRepository.save(cliente); // Salva o cliente no banco
        };
    }
}