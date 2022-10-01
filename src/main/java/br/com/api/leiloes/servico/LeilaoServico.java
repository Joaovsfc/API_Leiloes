package br.com.api.leiloes.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.leiloes.modelo.LeilaoModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.repositorio.LeilaoRepositorio;

@Service
public class LeilaoServico {

    @Autowired
    private LeilaoRepositorio leilaoRepositorio;

    @Autowired
    private RespostaModelo respostaModelo;

    public Iterable<LeilaoModelo> listar(){
        return leilaoRepositorio.findAll();
    }

}
