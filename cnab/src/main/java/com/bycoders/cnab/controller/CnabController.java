package com.bycoders.cnab.controller;

import com.bycoders.cnab.entity.Cnab;
import com.bycoders.cnab.enums.TipoTransacao;
import com.bycoders.cnab.repository.CnabRepository;
import com.bycoders.cnab.service.CnabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class CnabController {

    @Autowired
    private CnabService cnabService;

    @Autowired
    private CnabRepository cnabRepository;

    @GetMapping("/")
    public String show() {
        return "index.html";
    }

    @PostMapping("/enviar")
    public @ResponseBody ResponseEntity<Object> enviar(@RequestParam("file") MultipartFile file) {

        Cnab cnab = new Cnab();

        cnab.setTipo(TipoTransacao.ALUGUEL);
        cnab.setData(LocalDate.now());
        cnab.setValor(new BigDecimal(100));
        cnab.setCpf("cpf");
        cnab.setCartao("cartao");
        cnab.setHora(LocalTime.now());
        cnab.setDonoLoja("dono");
        cnab.setNomeLoja("nome)");

        System.out.println(cnab.getTipo());
        System.out.println(cnab.getData());
        System.out.println(cnab.getCartao());
        System.out.println(cnab.getCpf());
        System.out.println(cnab.getDonoLoja());
        System.out.println(cnab.getNomeLoja());
        System.out.println(cnab.getValor());
        System.out.println(cnab.getHora());

        Cnab c = new Cnab();
        c = cnabRepository.save(cnab);


        System.out.println(c.getTipo());
        System.out.println(c.getData());
        System.out.println(c.getCartao());
        System.out.println(c.getCpf());
        System.out.println(c.getDonoLoja());
        System.out.println(c.getNomeLoja());
        System.out.println(c.getValor());
        System.out.println(c.getHora());
        return null;
        // try {
        //     return cnabService.salvar(file);
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }
    }
}