package br.com.girls.GirlsBank.model.service;

import br.com.girls.GirlsBank.model.entities.Pessoa;
import br.com.girls.GirlsBank.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public boolean existById(Integer id) {
        return pessoaRepository.existsById(id);
    }

    public Optional<Pessoa> findById(Integer id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public Pessoa save(Pessoa livro) {
        return pessoaRepository.save(livro);
    }

    public void deleteById(Integer id) {
        pessoaRepository.deleteById(id);
    }
}