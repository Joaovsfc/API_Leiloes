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

import br.com.api.leiloes.modelo.ImagemModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.servico.ImagemServico;

@RestController
@CrossOrigin(originPatterns = "*")
public class ImagemControle {
    
    @Autowired
    private ImagemServico imagemServico;

    @GetMapping("/imagem/listar")
    public Iterable<ImagemModelo> listar(){
        return imagemServico.listar();
    }
    @PostMapping("/imagem/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ImagemModelo imagemModelo){
        return imagemServico.cadastrarAlterar(imagemModelo, "cadastrar");
    }

    @DeleteMapping("/imagem/remover/{codigo}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo){
        return imagemServico.remover(codigo);
    }
}
