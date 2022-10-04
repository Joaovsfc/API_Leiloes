package br.com.api.leiloes.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.leiloes.modelo.MensagemModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.modelo.ValidadorModelo;
import br.com.api.leiloes.repositorio.mensagemRepositorio;

@Service
public class mensagemServico {
    
    @Autowired
    private mensagemRepositorio mensagemRepositorio;

    @Autowired
    private RespostaModelo respostaModelo;

    public Iterable<MensagemModelo> listar(){
        return mensagemRepositorio.findAll();
    }

    public boolean existe(Long idMensagem){
        return mensagemRepositorio.existsById(idMensagem);
    }

    public ValidadorModelo validarCadastro(MensagemModelo mensagem){
        ValidadorModelo validador = new ValidadorModelo();
        if(mensagem.getDestinatario() == null){
            validador.setCampo("Destinatario");
            validador.setMensagem("O Destinatario é obrigatorio");
            validador.setValido(false);
        }else if(mensagem.getRemetente() == null){
            validador.setCampo("Remetente");
            validador.setMensagem("O Remetente é obrigatorio");
            validador.setValido(false);
        }else if(mensagem.getMensagem().equals("")){
            validador.setCampo("Mensagem");
            validador.setMensagem("A mensagem é obrigatorio");
            validador.setValido(false);
        }else if(mensagem.getDtMensagem().equals("")){
            validador.setCampo("Data");
            validador.setMensagem("A data é obrigatorio");
            validador.setValido(false);
        }else{
            validador.setValido(true);
        }
        return validador;
    }

    public ResponseEntity<?> cadastrarAlterar(MensagemModelo mensagem, String acao){
        if(acao.equals("cadastrar")){
            ValidadorModelo enderecoValido = validarCadastro(mensagem);
            if(enderecoValido.isValido()){
                return new ResponseEntity<>(mensagemRepositorio.save(mensagem),HttpStatus.CREATED);
            }else{
                respostaModelo.setMensagem(enderecoValido.getMensagem());
                return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
            }
        }else{
            if(existe(mensagem.getIdMensagem())){
                return new ResponseEntity<>(mensagemRepositorio.save(mensagem),HttpStatus.OK); 
            }else{
                respostaModelo.setMensagem("Falha ao editar a mensagem. Mensagem não existe.");
                return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
            }
        }         
    }


    public ResponseEntity<RespostaModelo> remover(Long codigo){
        boolean existe = existe(codigo);
        if(existe){
            mensagemRepositorio.deleteById(codigo);
            respostaModelo.setMensagem("A mensagem foi excluida com sucesso!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.OK);
        }else{
            respostaModelo.setMensagem("A mensagem não existe!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        }
    } 


}
