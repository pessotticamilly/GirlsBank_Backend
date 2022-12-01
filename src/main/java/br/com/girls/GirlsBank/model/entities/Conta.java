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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(nullable = false)
    private Double saldo;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;
}