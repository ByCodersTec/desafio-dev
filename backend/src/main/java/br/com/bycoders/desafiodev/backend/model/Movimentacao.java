package br.com.bycoders.desafiodev.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoTransacao tipo;

    @Column
    private LocalDate data;

    @Column
    private BigDecimal valor;

    @Column
    private String cpf;

    @Column
    private String cartao;

    @Column
    private LocalTime hora;

    @Column
    private String donoLoja;

    @Column
    private String nomeLoja;
}
