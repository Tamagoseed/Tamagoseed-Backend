package com.tamagoseed.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tamagoseed.model.Servicos;
import com.tamagoseed.repository.ServicosRepository;
import com.tamagoseed.repository.SetorAtuacaoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServicosController {
	
	@Autowired
    private ServicosRepository servicosRepository;
	
	@Autowired
    private SetorAtuacaoRepository setorAtuacaoRepository;
	
	@GetMapping
    public ResponseEntity<List<Servicos>>getAll(){
        return ResponseEntity.ok(servicosRepository.findAll());
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Servicos> getById(@PathVariable Long id) {
  
        return servicosRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
	

	@PostMapping
    public ResponseEntity<Servicos> post(@Valid @RequestBody Servicos servicos){
        if(setorAtuacaoRepository.existsById(servicos.getSetorAtuacao().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(servicosRepository.save(servicos));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Setor de Atuação não existe|", null);
    }
	
	@PutMapping
    public ResponseEntity<Servicos> put(@Valid @RequestBody Servicos servicos){
        if(servicosRepository.existsById(servicos.getId())) {
            if(setorAtuacaoRepository.existsById(servicos.getSetorAtuacao().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(servicosRepository.save(servicos));
            
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Setor de atuação não existe!", null);
        }
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Servicos> servicos = servicosRepository.findById(id);
		
		if(servicos.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		servicosRepository.deleteById(id);
	}

}
