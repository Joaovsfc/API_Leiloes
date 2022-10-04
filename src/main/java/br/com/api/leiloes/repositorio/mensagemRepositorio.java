package br.com.api.leiloes.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.api.leiloes.modelo.MensagemModelo;

public interface mensagemRepositorio extends CrudRepository<MensagemModelo, Long>{
    
}