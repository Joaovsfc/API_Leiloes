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

import br.com.api.leiloes.modelo.MensagemModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.servico.mensagemServico;

@RestController
@CrossOrigin(origins = "*")
public class MensagemControle {

    @Autowired
    private mensagemServico mensagemServico;


    @GetMapping("/mensagem/listar")
    public Iterable<MensagemModelo> listar(){
        return mensagemServico.listar();
    }

    @PostMapping("/mensagem/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody MensagemModelo mensagemModelo){
        return mensagemServico.cadastrarAlterar(mensagemModelo, "cadastrar");
    } 

    @PutMapping("/mensagem/alterar")
    public ResponseEntity<?> alterar(@RequestBody MensagemModelo mensagemModelo){
        return mensagemServico.cadastrarAlterar(mensagemModelo, "alterar");
    }

    @DeleteMapping("/mensagem/remover/{codigo}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo){
        return mensagemServico.remover(codigo);
    }

}
