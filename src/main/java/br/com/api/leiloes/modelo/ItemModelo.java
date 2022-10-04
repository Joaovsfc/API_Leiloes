package br.com.api.leiloes.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 
    @ManyToMany
    @JoinTable(
        name ="leilao_itens",
        joinColumns = {@JoinColumn(name = "id_item", referencedColumnName = "idItem")},
        inverseJoinColumns = {@JoinColumn(name = "id_leilao", referencedColumnName = "idLeilao")}
    )
    private List<LeilaoModelo> leiloes = new ArrayList<>();
    
    
}
