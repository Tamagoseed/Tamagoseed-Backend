package com.tamagoseed.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "setorAtuacao", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("setorAtuacao")
    private List<Servicos> servicos;

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

	public List<Servicos> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servicos> servicos) {
		this.servicos = servicos;
	}


}