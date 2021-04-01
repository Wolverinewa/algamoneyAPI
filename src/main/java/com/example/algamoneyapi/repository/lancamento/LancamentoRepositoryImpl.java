package com.example.algamoneyapi.repository.lancamento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
		// TODO Auto-generated method stub
		return null;
	}

}
