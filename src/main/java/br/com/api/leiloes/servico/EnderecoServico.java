package br.com.api.leiloes.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.leiloes.modelo.EnderecoModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.modelo.ValidadorModelo;
import br.com.api.leiloes.repositorio.EnderecoRepositorio;

@Service
public class EnderecoServico {
    
    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private RespostaModelo respostaModelo;

    public Iterable<EnderecoModelo> listar(){
        return enderecoRepositorio.findAll();
    }

    public boolean existe(Long idEndereco){
        return enderecoRepositorio.existsById(idEndereco);
    }

    public ValidadorModelo validarCadastro(EnderecoModelo endereco){
        ValidadorModelo validador = new ValidadorModelo();
        if(endereco.getBairro().equals("")){
            validador.setCampo("bairro");
            validador.setMensagem("O bairro é obrigatorio");
            validador.setValido(false);
        }else if(endereco.getCidade().equals("")){
            validador.setCampo("cidade");
            validador.setMensagem("O cidade é obrigatorio");
            validador.setValido(false);
        }else if(endereco.getEstado().equals("")){
            validador.setCampo("estado");
            validador.setMensagem("O estado é obrigatorio");
            validador.setValido(false);
        }else if(endereco.getNumero().equals("")){
            validador.setCampo("numero");
            validador.setMensagem("O numero é obrigatorio");
            validador.setValido(false);
        }else if(endereco.getUf().equals("")){
            validador.setCampo("UF");
            validador.setMensagem("A UF é obrigatorio");
            validador.setValido(false);
        }else{
            validador.setValido(true);
        }
        return validador;
    }

    /*
     public EnderecoModelo enderecoAlterado(EnderecoModelo enderecoBody){
        Optional<EnderecoModelo> endereco = enderecoRepositorio.findById(enderecoBody.getIdEndereco());
        if(enderecoBody.getBairro().equals("")){
            enderecoBody.setBairro(endereco.get().getBairro());
        }
        


    } 
    */
    
    public ResponseEntity<?> cadastrarAlterar(EnderecoModelo endereco, String acao){
        if(acao.equals("cadastrar")){
            ValidadorModelo enderecoValido = validarCadastro(endereco);
            if(enderecoValido.isValido()){
                return new ResponseEntity<>(enderecoRepositorio.save(endereco),HttpStatus.CREATED);
            }else{
                respostaModelo.setMensagem(enderecoValido.getMensagem());
                return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
            }
        }else{
            if(existe(endereco.getIdEndereco())){
                return new ResponseEntity<>(enderecoRepositorio.save(endereco),HttpStatus.OK); 
            }else{
                respostaModelo.setMensagem("Falha ao editar o endereço. Endereço não existe.");
                return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
            }
        }         
    }

    public ResponseEntity<RespostaModelo> remover(Long codigo){
        boolean existe = existe(codigo);
        if(existe){
            enderecoRepositorio.deleteById(codigo);
            respostaModelo.setMensagem("O endereco foi excluida com sucesso!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.OK);
        }else{
            respostaModelo.setMensagem("O endereço não existe!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        }
    } 
}
