package br.com.api.leiloes.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.leiloes.modelo.LeilaoModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.modelo.ValidadorModelo;
import br.com.api.leiloes.repositorio.LeilaoRepositorio;

@Service
public class LeilaoServico {

    @Autowired
    private LeilaoRepositorio leilaoRepositorio;

    @Autowired
    private RespostaModelo respostaModelo;

    public Iterable<LeilaoModelo> listar(){
        return leilaoRepositorio.findAll();
    }

    public boolean existe(Long idLeilao){
        return leilaoRepositorio.existsById(idLeilao);
    }

    public ValidadorModelo validarCadastro(LeilaoModelo leilao){
        ValidadorModelo validador = new ValidadorModelo();
        if(leilao.getTitulo().equals("")){
            validador.setCampo("Titulo");
            validador.setMensagem("O tirulo é obrigatorio");
            validador.setValido(false);
        }else if(leilao.getDescricao().equals("")){
            validador.setCampo("descricao");
            validador.setMensagem("O descrição é obrigatorio");
            validador.setValido(false);
        }else if(leilao.getDtInicio().equals("")){
            validador.setCampo("Data de inicio");
            validador.setMensagem("A data de inicio é obrigatorio");
            validador.setValido(false);
        }else if(leilao.getDtFim().equals("")){
            validador.setCampo("Data fim");
            validador.setMensagem("A tata fim é obrigatorio");
            validador.setValido(false);
        }else if(leilao.getValorInicial() == null){
            validador.setCampo("Valor inicial é obrigatorio");
            validador.setMensagem("O valor inicial é obrigatorio");
            validador.setValido(false);
        }else if(leilao.getStep() == null){
            validador.setCampo("Valor de intervalo");
            validador.setMensagem("O valor de intervalo é obrigatorio");
            validador.setValido(false);
        }else{
            validador.setValido(true);
        }
        return validador;
    }

    public ResponseEntity<?> cadastrarAlterar(LeilaoModelo leilao, String acao){
        if(acao.equals("cadastrar")){
            ValidadorModelo enderecoValido = validarCadastro(leilao);
            if(enderecoValido.isValido()){
                return new ResponseEntity<>(leilaoRepositorio.save(leilao),HttpStatus.CREATED);
            }else{
                respostaModelo.setMensagem(enderecoValido.getMensagem());
                return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
            }
        }else{
            if(existe(leilao.getIdLeilao())){
                return new ResponseEntity<>(leilaoRepositorio.save(leilao),HttpStatus.OK); 
            }else{
                respostaModelo.setMensagem("Falha ao editar o leilão. Leilão não existe.");
                return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
            }
        }         
    }

    public ResponseEntity<RespostaModelo> remover(Long codigo){
        boolean existe = existe(codigo);
        if(existe){
            leilaoRepositorio.deleteById(codigo);
            respostaModelo.setMensagem("O Leilão foi excluida com sucesso!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.OK);
        }else{
            respostaModelo.setMensagem("O Leilão não existe!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        }
    } 


}
