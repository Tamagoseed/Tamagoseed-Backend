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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@SuppressWarnings("unused")
@Entity
@Table(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo CPF é Obrigatório")
    @Size(min = 11, max = 11, message = "O atributo CPF deve conter no mínimo 11 e no máximo 11 caracteres")
    private String cpf;

    @NotBlank(message = "O atributo razão social é Obrigatório!!") 
    @Size(min = 4, max = 300, message = "O atributo titulo deve conter no mínimo 05 e no máximo 300 caracteres") 
    private String razaoSocial;

    @NotBlank(message = "O atributo email é Obrigatório!") 
    @Size(min = 5, max = 50, message = "O atributo email deve conter no mínimo 05 e no máximo 50 caracteres")
    private String email;

    @NotBlank(message = "O atributo senha  é Obrigatório!") 
    @Size(min = 8, max = 500, message = "O atributo senha deve conter no mínimo 08 e no máximo 30 caracteres")
    private String senha;

    @Size(max = 5000)
    private String foto;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("cliente")
    private List<Servicos> servicos;

    public Cliente(Long id, String cpf, String razaoSocial, String email, String senha, String foto) {
        this.id = id;
        this.cpf = cpf;
        this.razaoSocial = razaoSocial;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Servicos> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servicos> servicos) {
        this.servicos = servicos;
    }

   
    public String getNome() {
        return razaoSocial; 
    }
}
