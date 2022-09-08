package br.com.api.leiloes.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.leiloes.modelo.CategoriaModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.servico.CategoriaServico;

@RestController
@CrossOrigin(origins = "*")
public class CategoriaControle {
    
    @Autowired
    private CategoriaServico categoriaServico;
    
    @PostMapping("/categoria/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody CategoriaModelo categoriaModelo){
        return categoriaServico.cadastrarAlterar(categoriaModelo, "cadastrar");
    }

    @GetMapping("/categoria/listar")
    public Iterable<CategoriaModelo> listar(){
        return categoriaServico.listar();
    }

    @DeleteMapping("/categoria/remover/{codigo}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo){
        return categoriaServico.remover(codigo);
    }

}
