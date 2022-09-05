package br.com.api.leiloes.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.leiloes.modelo.ItemModelo;
import br.com.api.leiloes.servico.ItemServico;

@RestController
@CrossOrigin(origins = "*")
public class ItemControle {
    
    @Autowired
    private ItemServico itemServico;

    @PostMapping("/item/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ItemModelo itemModelo){
        return itemServico.cadastrarAlterar(itemModelo, "cadastrar");
    }

    @GetMapping("/item/listar")
    public Iterable<ItemModelo> listar(){
        return itemServico.listar();
    }
}
