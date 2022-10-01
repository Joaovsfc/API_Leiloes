package br.com.api.leiloes.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.leiloes.modelo.LeilaoModelo;
import br.com.api.leiloes.servico.LeilaoServico;

@RestController
@CrossOrigin(origins = "*")
public class LeilaoControle {
    
    @Autowired
    private LeilaoServico leilaoServico;
    
    @GetMapping("/leilao/listar")
    public Iterable<LeilaoModelo> listar(){
        return leilaoServico.listar();
    }
}
