package br.com.girls.GirlsBank.repository;

import br.com.girls.GirlsBank.model.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    boolean existsByCpf(Long cpf);

}