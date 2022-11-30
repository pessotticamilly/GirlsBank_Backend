package br.com.girls.GirlsBank.controller;

import br.com.girls.GirlsBank.dto.PessoaDto;
import br.com.girls.GirlsBank.dto.PessoaDtoLogin;
import br.com.girls.GirlsBank.model.entities.Conta;
import br.com.girls.GirlsBank.model.entities.Pessoa;
import br.com.girls.GirlsBank.model.service.ContaService;
import br.com.girls.GirlsBank.model.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@AllArgsConstructor
@Controller
@RequestMapping("/girlsbank/pessoa")
public class PessoaController {
    private final PessoaService pessoaService;

    private final ContaService contaService;

    @GetMapping("/listar/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Integer id) {
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);

        if (pessoaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa com o ID informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaOptional);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> save(@RequestBody @Valid PessoaDto pessoaDto) {
        if (pessoaService.existByCpf(pessoaDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado");
        }

        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);

        pessoaService.save(pessoa);

        Conta conta = new Conta();
        conta.setSaldo(0.0);
        conta.setPessoa(pessoa);

        contaService.save(conta);

        return ResponseEntity.status(HttpStatus.OK).body("Pessoa cadastrada com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid PessoaDtoLogin pessoaDtoLogin) {
        if (pessoaService.existByCpf(pessoaDtoLogin.getCpf()) && pessoaService.existsBySenha(pessoaDtoLogin.getSenha())) {
            return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findByCpf(pessoaDtoLogin.getCpf()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe nenhuma pessoa com este CPF ou senha!");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Integer id, @Valid @RequestBody PessoaDto pessoaDto) {
        if (!pessoaService.existById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa com o ID informado");
        }

        Pessoa pessoa = pessoaService.findById(pessoaDto.getId()).get();
        BeanUtils.copyProperties(pessoaDto, pessoa);

        pessoaService.save(pessoa);
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa editada com sucesso");
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Integer id) {
        if (!pessoaService.existById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa com o ID informado");
        }

        pessoaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso");
    }
}