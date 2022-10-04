package br.com.api.leiloes.modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="mensagem")
@Getter
@Setter
public class MensagemModelo{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensagem;

    @OneToOne
    @JoinColumn(name = "id_remetente", referencedColumnName = "idUsuario")
    private UsuarioModelo remetente;

    @OneToOne
    @JoinColumn(name = "id_destinatario", referencedColumnName = "idUsuario")
    private UsuarioModelo destinatario;

    @OneToOne
    @JoinColumn(name = "id_leilao", referencedColumnName = "idLeilao")
    private LeilaoModelo leilao;

    private Long nrSequencia;
    private String mensagem;
    
    @JsonFormat(pattern="dd/MM/yyyy hh:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date dtMensagem;

    public String dateFormater(Date dt){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dateFormat.format(dt);
        return dataFormatada;
    }

    public String getDtMensagem(){
        return dateFormater(this.dtMensagem);
    }

}
