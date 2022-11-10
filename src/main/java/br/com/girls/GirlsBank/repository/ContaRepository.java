package br.com.girls.GirlsBank.repository;

import br.com.girls.GirlsBank.model.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}