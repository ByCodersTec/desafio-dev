package br.com.desafiodev.model;

import br.com.desafiodev.dto.TipoTransacaoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(schema = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo", nullable = false)
    @JsonIgnore
    private TipoTransacao tipo;

    @Column(name = "data_transacao")
    private LocalDate dataTransacao;

    @Column(name = "valor_transacao")
    private BigDecimal valor;

    @Column(name = "cpf_beneficiario")
    private String cpf;

    @Column(name = "cartao")
    private String cartao;

    @Column(name = "hora_transacao")
    private LocalTime hora;

    @Column(name = "nome_dono")
    private String nomeDono;

    @Column(name = "nome_loja")
    private String nomeLoja;
}
