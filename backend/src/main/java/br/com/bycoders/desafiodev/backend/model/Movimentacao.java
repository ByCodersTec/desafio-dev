package br.com.bycoders.desafiodev.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "movimentacao")
public class Movimentacao {
    
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoTransacao tipo;

    @Column
    private Date data;

    @Column
    private double valor;

    @Column
    private String cpf;

    @Column
    private String cartao;

    @Column
    private String hora;

    @Column
    private String donoLoja;

    @Column
    private String nomeLoja;
}
