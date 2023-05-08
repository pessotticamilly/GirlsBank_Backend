package br.com.girls.GirlsBank.controller;

import br.com.girls.GirlsBank.dto.ContaDto;
import br.com.girls.GirlsBank.model.entities.Conta;
import br.com.girls.GirlsBank.model.entities.Pessoa;
import br.com.girls.GirlsBank.model.service.PessoaService;
import br.com.girls.GirlsBank.model.service.ContaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/girlsbank/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @Autowired
    PessoaService pessoaService;


    @GetMapping("/listar/{numero}")
    public ResponseEntity<Object> findById(@PathVariable(value = "numero") Integer numero) {
        Optional<Conta> pessoaOptional = contaService.findById(numero);

        if (pessoaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma conta com o número informado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaOptional);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Conta>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.findAll());
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<Object> findByPessoa(@PathVariable(value = "pessoaId") Integer pessoaId) {
        Optional<Pessoa> optionalPessoa = pessoaService.findById(pessoaId);

        Pessoa pessoa = new Pessoa();

        BeanUtils.copyProperties(optionalPessoa.get(), pessoa);

        if (contaService.findByPessoa(pessoa) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma pessoa");
        }

        return ResponseEntity.status(HttpStatus.OK).body(contaService.findByPessoa(pessoa));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> save(@RequestBody @Valid ContaDto contaDto) {
        Conta conta = new Conta();
        BeanUtils.copyProperties(contaDto, conta);

        contaService.save(conta);
        return ResponseEntity.status(HttpStatus.OK).body("Conta cadastrada com sucesso");
    }

    @PutMapping("/editar/{numero}")
    public ResponseEntity<Object> update(@PathVariable(value = "numero") Integer numero, @Valid @RequestBody ContaDto contaDto) {
        if (!contaService.existById(numero)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma conta com o número informado");
        }

        Conta conta = new Conta();
        BeanUtils.copyProperties(contaDto, conta);

        contaService.save(conta);
        return ResponseEntity.status(HttpStatus.OK).body("Conta editada com sucesso");
    }

    @DeleteMapping("/excluir/{numero}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "numero") Integer numero) {
        if (!contaService.existById(numero)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhuma conta com o número informado");
        }

        contaService.deleteById(numero);
        return ResponseEntity.status(HttpStatus.OK).body("Conta deletada com sucesso");
    }
}