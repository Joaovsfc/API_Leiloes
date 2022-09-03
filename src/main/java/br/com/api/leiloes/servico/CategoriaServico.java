package br.com.api.leiloes.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.leiloes.modelo.CategoriaModelo;
import br.com.api.leiloes.modelo.RespostaModelo;
import br.com.api.leiloes.repositorio.CategoriaRepositorio;

@Service
public class CategoriaServico {
    
    @Autowired
    private CategoriaRepositorio categoriaR;

    @Autowired
    private RespostaModelo respostaM;

    public Iterable<CategoriaModelo> listar(){
        return categoriaR.findAll();
    }

    public Optional<CategoriaModelo> buscarId(Long id){
        return categoriaR.findById(id);
    }

    public ResponseEntity<?> cadastrarAlterar(CategoriaModelo categoria, String acao){
        if(categoria.getNome().equals("")){
            respostaM.setMensagem("O nome da categoria é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(respostaM,HttpStatus.BAD_REQUEST);
        }else if(categoria.getDescricao().equals("")){
            respostaM.setMensagem("A descrição da categoria é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(respostaM,HttpStatus.BAD_REQUEST);
        }else{
            if(acao.equals("cadastrar")){
                return new ResponseEntity<>(categoriaR.save(categoria),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(categoriaR.save(categoria),HttpStatus.OK);
            }
        }
    }
}
