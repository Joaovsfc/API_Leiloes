package br.com.api.leiloes.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.modelo.UsuarioModelo;
import br.com.api.leiloes.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServico {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RespostaModelo respostaModelo;

    public Iterable<UsuarioModelo> listar(){
        return usuarioRepositorio.findAll();
    }

    public boolean existe(Long idUsuario){
        return usuarioRepositorio.existsById(idUsuario);
    }

    public ResponseEntity<?> cadastrarAlterar(UsuarioModelo usuario, String acao){
        if(usuario.getNome().equals("")){
            respostaModelo.setMensagem("O nome do categoria é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
        }else if(usuario.getEmail().equals("")){
            respostaModelo.setMensagem("O email do usuario é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
        }else if(usuario.getSenha().equals("")){
            respostaModelo.setMensagem("Senha do usuario é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
        }else{
            if(acao.equals("cadastrar")){
                return new ResponseEntity<>(usuarioRepositorio.save(usuario),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(usuarioRepositorio.save(usuario),HttpStatus.OK);
            }
        }
    }

    public ResponseEntity<RespostaModelo> remover(Long codigo){
        boolean existe = existe(codigo);
        if(existe){
            usuarioRepositorio.deleteById(codigo);
            respostaModelo.setMensagem("O usuario foi excluida com sucesso!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.OK);
        }else{
            respostaModelo.setMensagem("O não existe!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        }
    }

    public List<UsuarioModelo> validarLoginTeste(String email){
        List <UsuarioModelo> usuarios =  usuarioRepositorio.findByEmail(email);
        return usuarios;
    }

    /*
     * public boolean validarLogin(UsuarioModelo usuario){
        boolean existe = true;
        List<UsuarioModelo> usu = usuarioRepositorio.findBySenhaAndEmail(usuario.getSenha(), usuario.getEmail());//usuarioRepositorio.findByEmailAndPassword(email, senha);
        if(usu.isEmpty()){
            existe = false;
        }
        return existe;
    }
     */
    public UsuarioModelo validarLogin(UsuarioModelo usuario){
        boolean existe = true;
        UsuarioModelo usu = usuarioRepositorio.findBySenhaAndEmail(usuario.getSenha(), usuario.getEmail());//usuarioRepositorio.findByEmailAndPassword(email, senha);
        if(usu.getEmail().equals("")){
            usu.setIdUsuario(null);
        }else{
            usu.setSenha("");
        }
        return usu;
    }
}
