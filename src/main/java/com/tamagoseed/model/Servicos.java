package com.tamagoseed.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_servicos")
public class Servicos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

  	@Size(max =  5000) 
  	private String foto;
	
	@NotBlank(message = "O atributo contato é Obrigatório!") 
    @Size(min = 5, max = 20, message = "O atributo contato deve conter no mínimo 05 e no máximo 20 caracteres")
	private String contato;
	
	@NotBlank(message="O atributo descrição é obrigatório!")
    @Size(min = 5, max = 500,  message = "O atributo descrição deve ter no mínimo 150 caracteres e no máximo 500 caracteres")
	private String descricao;
	
	@ManyToOne
	@JsonIgnoreProperties("servicos")
	private SetorAtuacao setorAtuacao;
	
	@ManyToOne
    @JsonIgnoreProperties("servicos")
    private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SetorAtuacao getSetorAtuacao() {
		return setorAtuacao;
	}

	public void setSetorAtuacao(SetorAtuacao setorAtuacao) {
		this.setorAtuacao = setorAtuacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
}
