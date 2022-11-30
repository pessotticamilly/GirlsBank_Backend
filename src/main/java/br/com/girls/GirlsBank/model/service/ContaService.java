package br.com.girls.GirlsBank.model.service;

import br.com.girls.GirlsBank.model.entities.Conta;
import br.com.girls.GirlsBank.model.entities.Pessoa;
import br.com.girls.GirlsBank.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public boolean existById(Integer numero) {
        return contaRepository.existsById(numero);
    }

    public Optional<Conta> findById(Integer numero) {
        return contaRepository.findById(numero);
    }

    public Optional<Conta> findByPessoa(Pessoa pessoa) {
        return contaRepository.findByPessoa(pessoa);
    }

    public List<Conta> findAll(){
        return contaRepository.findAll();
    }

    public Conta save(Conta conta) {
        return contaRepository.save(conta);
    }

    public void deleteById(Integer numero) {
        contaRepository.deleteById(numero);
    }
}