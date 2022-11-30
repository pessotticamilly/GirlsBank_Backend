package br.com.girls.GirlsBank.repository;

import br.com.girls.GirlsBank.model.entities.Conta;
import br.com.girls.GirlsBank.model.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    Optional<Conta> findByPessoa(Pessoa pessoa);

}