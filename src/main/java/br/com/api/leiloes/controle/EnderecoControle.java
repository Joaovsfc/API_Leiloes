package br.com.api.leiloes.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.leiloes.modelo.EnderecoModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.servico.EnderecoServico;

@RestController
@CrossOrigin(origins = "*")
public class EnderecoControle {
    
    @Autowired
    private EnderecoServico usuarioServico;

    @PostMapping("/endereco/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody EnderecoModelo enderecoModelo){
        return usuarioServico.cadastrarAlterar(enderecoModelo, "cadastrar");
    }

    @GetMapping("/endereco/listar")
    public Iterable<EnderecoModelo> listar(){
        return usuarioServico.listar();
    }

    @DeleteMapping("/endereco/remover/{codigo}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo){
        return usuarioServico.remover(codigo);
    }

    @PutMapping("/endereco/alterar")
    public ResponseEntity<?> alterar(@RequestBody EnderecoModelo enderecoModelo){
        return usuarioServico.cadastrarAlterar(enderecoModelo, "alterar");
    }
}
