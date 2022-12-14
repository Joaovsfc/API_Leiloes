package br.com.api.leiloes.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.leiloes.modelo.UsuarioModelo;

@Repository
public interface UsuarioRepositorio extends CrudRepository<UsuarioModelo, Long> {
    List<UsuarioModelo> findByEmail(String email);

    UsuarioModelo findBySenhaAndEmail(String senha, String email);
    //List<UsuarioModelo> findBySenhaAndEmail(String senha, String email);

}
