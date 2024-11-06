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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tamagoseed.model.SetorAtuacao;
import com.tamagoseed.repository.SetorAtuacaoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/setoratuacao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SetorAtuacaoController {
	
	@Autowired
	private SetorAtuacaoRepository setorAtuacaoRepository;
	
	@GetMapping
    public ResponseEntity<List<SetorAtuacao>> getAll(){
        return ResponseEntity.ok(setorAtuacaoRepository.findAll());
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<SetorAtuacao> getById(@PathVariable Long id){
        return setorAtuacaoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
	
	@GetMapping("/nome/{nome}")
    public ResponseEntity<List<SetorAtuacao>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(setorAtuacaoRepository.findAllByNomeContainingIgnoreCase(nome));
    }
	
	@PostMapping
    public ResponseEntity<SetorAtuacao> post(@Valid @RequestBody SetorAtuacao setorAtuacao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(setorAtuacaoRepository.save(setorAtuacao));
        }
	
	@PutMapping
	public ResponseEntity<SetorAtuacao> put(@Valid @RequestBody SetorAtuacao setorAtuacao) {
	    return setorAtuacaoRepository.findById(setorAtuacao.getId())
	        .map(existingSetor -> {
	            existingSetor.setNome(setorAtuacao.getNome());
	            existingSetor.setDescricao(setorAtuacao.getDescricao());
	            SetorAtuacao updatedSetor = setorAtuacaoRepository.save(existingSetor);
	            return ResponseEntity.ok(updatedSetor);
	        })
	        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	
	@DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<SetorAtuacao> setorAtuacao= setorAtuacaoRepository.findById(id);
        if(setorAtuacao.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        setorAtuacaoRepository.deleteById(id);
    }
	
}