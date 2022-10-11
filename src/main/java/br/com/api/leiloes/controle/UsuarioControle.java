package br.com.api.leiloes.controle;

import java.util.List;

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

import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.modelo.UsuarioModelo;
import br.com.api.leiloes.servico.UsuarioServico;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioControle {

    @Autowired
    private UsuarioServico usuarioServico;

    @PostMapping("/usuario/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioModelo usuarioModelo){
        return usuarioServico.cadastrarAlterar(usuarioModelo, "cadastrar");
    }

    @GetMapping("/usuario/listar")
    public Iterable<UsuarioModelo> listar(){
        return usuarioServico.listar();
    }

    @DeleteMapping("/usuario/remover/{codigo}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo){
        return usuarioServico.remover(codigo);
    }

    @PutMapping("/usuario/alterar")
    public ResponseEntity<?> alterar(@RequestBody UsuarioModelo usuarioModelo){
        return usuarioServico.cadastrarAlterar(usuarioModelo, "alterar");
    }

    @PostMapping("/usuario/login")
    public UsuarioModelo login(@RequestBody UsuarioModelo usuarioModelo){
        UsuarioModelo  usuario = usuarioServico.validarLogin(usuarioModelo);
        return usuario;
    }
}
