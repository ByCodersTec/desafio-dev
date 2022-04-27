package br.com.bycoders.desafiodev.backend.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "tipo_transacao")
public class TipoTransacao {
    
    @Id
    private int id;

    @Column
    private String descricao;

    @Column
    private String natureza;

    @Column
    private String sinal;

    
}
