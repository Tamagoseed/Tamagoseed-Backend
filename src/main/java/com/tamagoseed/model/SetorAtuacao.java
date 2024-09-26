package com.tamagoseed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_setor_atuacao")
public class SetorAtuacao {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é Obrigatório!") 
    @Size(min = 5, max = 255, message = "O atributo nome deve conter no mínimo 05 e no máximo 255 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo descrição é Obrigatório!") 
	@Size(min = 5, max = 255, message = "O atributo descrição deve conter no mínimo 05 e no máximo 255 caracteres")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
