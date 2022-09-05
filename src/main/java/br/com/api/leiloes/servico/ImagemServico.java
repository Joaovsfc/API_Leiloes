package br.com.api.leiloes.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.leiloes.modelo.ImagemModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.repositorio.ImagemRepositorio;

@Service
public class ImagemServico {
    
    @Autowired
    private ImagemRepositorio imagemRepositorio;

    @Autowired
    private RespostaModelo respostaModelo;

    @Autowired
    private ItemServico itemServico;

    public Iterable<ImagemModelo> listar(){
        return imagemRepositorio.findAll();
    }

    public ResponseEntity<?> cadastrarAlterar(ImagemModelo imagem, String acao){
        if(imagem.getTitulo().equals("")){
            respostaModelo.setMensagem("O titulo da Imagem é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        }else if(imagem.getCaminho().equals("")){
            respostaModelo.setMensagem("O caminho da Imagem é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        }else if(imagem.getItem() == null){
            respostaModelo.setMensagem("Nenhum item especificado para a imagem.");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        }else if(!itemServico.existeItem(imagem.getItem().getIdItem())){
            respostaModelo.setMensagem("O item especificado para a imagem não existe.");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        }else{
            if(acao.equals("cadastrar")){
                return new ResponseEntity<>(imagemRepositorio.save(imagem),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(imagemRepositorio.save(imagem),HttpStatus.OK);

            }
        }
    }
}
