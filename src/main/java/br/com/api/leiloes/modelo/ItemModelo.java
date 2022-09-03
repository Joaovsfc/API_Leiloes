package br.com.api.leiloes.modelo;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter
@Setter
public class ItemModelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;
    private String nome;
    private String descricao;
    
    @OneToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "idCategoria")
    private CategoriaModelo categoria;

    
    
}
