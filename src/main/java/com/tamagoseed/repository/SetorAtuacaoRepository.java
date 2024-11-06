package com.tamagoseed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tamagoseed.model.SetorAtuacao;

public interface SetorAtuacaoRepository extends JpaRepository<SetorAtuacao,Long>{
	
	public List<SetorAtuacao> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}

