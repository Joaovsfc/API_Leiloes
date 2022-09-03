package br.com.api.leiloes.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.leiloes.modelo.ItemModelo;

@Repository
public interface ItemRepositorio extends CrudRepository<ItemModelo, Long>{
    
}
