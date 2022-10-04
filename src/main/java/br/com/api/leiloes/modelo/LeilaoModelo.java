package br.com.api.leiloes.modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern="dd/MM/yyyy hh:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date dtInicio;
    @JsonFormat(pattern="dd/MM/yyyy hh:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date dtFim;
    private Boolean isArremate;

    @ManyToMany(mappedBy = "leiloes")
    private List<ItemModelo> itens = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "id_ganhador", referencedColumnName = "idUsuario")
    private UsuarioModelo ganhador; 
    private Double valorInicial;
    private Double valorAtual;
    private Double valorArremate;
    private Double step;
     
    
    public String dateFormater(Date dt){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dateFormat.format(dt);
        return dataFormatada;
    }

    public String getDtInicio(){
        return dateFormater(this.dtInicio);
    }

    public String getDtFim(){
        return dateFormater(this.dtFim);
    }    

}
