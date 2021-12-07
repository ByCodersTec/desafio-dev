package br.com.desafiodev.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tipo_transacao")
public class TipoTransacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long id;
    private int tipo;
    private String descricao;
    private String natureza;
    private String sinal;
}
