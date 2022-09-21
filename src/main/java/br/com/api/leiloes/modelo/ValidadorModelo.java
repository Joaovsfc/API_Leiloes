package br.com.api.leiloes.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidadorModelo {
    
    private String campo;
    private String mensagem;
    private boolean isValido;
}
