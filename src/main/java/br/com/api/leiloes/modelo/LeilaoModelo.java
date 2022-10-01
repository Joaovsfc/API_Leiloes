package br.com.api.leiloes.modelo;

import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="leilao")
@Getter
@Setter
public class LeilaoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLeilao;
     
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    private UsuarioModelo leiloeiro;
    private String titulo;
    private String descricao;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dtInicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dtFim;

    private Boolean isArramete;
    
    @OneToOne
    @JoinColumn(name = "id_ganhador", referencedColumnName = "idUsuario")
    private UsuarioModelo ganhador; 

    private Double valorInicial;
    private Double valorAtual;
    private Double valorArremate;
    private Double step;
    
    




    
    
    


    
}
