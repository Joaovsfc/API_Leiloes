package br.com.api.leiloes.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.leiloes.modelo.ItemModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.repositorio.ItemRepositorio;

@Service
public class ItemServico {
    
    @Autowired
    private ItemRepositorio itemRepositorio;

    @Autowired 
    private RespostaModelo respostaModelo;

    public Iterable<ItemModelo> listar(){
        return itemRepositorio.findAll();
    }

    public ResponseEntity<?> cadastrarAlterar(ItemModelo item, String acao){
        if(item.getNome().equals("")){
            respostaModelo.setMensagem("O nome do item é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
        }else if(item.getDescricao().equals("")){
            respostaModelo.setMensagem("A descrição do item é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
        }else{
            if(acao.equals("cadastrar")){
                return new ResponseEntity<>(itemRepositorio.save(item),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(itemRepositorio.save(item),HttpStatus.OK);
            }
        }
    }

}
