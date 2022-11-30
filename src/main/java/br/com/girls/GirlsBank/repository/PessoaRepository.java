package br.com.girls.GirlsBank.repository;

import br.com.girls.GirlsBank.model.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByCpf(Long cpf);

    boolean existsBySenha(String senha);

    boolean existsByCpf(Long cpf);

}