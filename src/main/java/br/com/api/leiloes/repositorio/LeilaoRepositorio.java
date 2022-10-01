package br.com.api.leiloes.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.leiloes.modelo.LeilaoModelo;

@Repository
public interface LeilaoRepositorio extends CrudRepository<LeilaoModelo, Long>{
    
}

