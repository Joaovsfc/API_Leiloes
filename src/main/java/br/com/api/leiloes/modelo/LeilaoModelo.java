package br.com.api.leiloes.modelo;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private Date dtInicio;

    private Date dtFim;

    private Boolean isArremate;
    
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
