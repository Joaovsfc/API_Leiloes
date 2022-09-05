package br.com.api.leiloes.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "imagem")
@Getter
@Setter
public class ImagemModelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagem;
    @NonNull
    private String titulo;
    private String descricao;
    @NonNull
    private String caminho;

    @OneToOne
    @JoinColumn(name = "id_item", referencedColumnName = "idItem")
    private ItemModelo item;
}
