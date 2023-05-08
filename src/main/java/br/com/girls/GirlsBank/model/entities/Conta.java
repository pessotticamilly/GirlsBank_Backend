package br.com.girls.GirlsBank.model.entities;

import lombok.*;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "conta")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private Double saldo;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;
}