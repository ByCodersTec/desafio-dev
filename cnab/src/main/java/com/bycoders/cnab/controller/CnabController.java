package com.bycoders.cnab.controller;

import com.bycoders.cnab.dto.CnabDTO;
import com.bycoders.cnab.dto.FaturamentoDTO;
import com.bycoders.cnab.service.CnabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CnabController {
    private List<CnabDTO> lista = new ArrayList<>();
    private List<FaturamentoDTO> listaFinal = new ArrayList<>();

    @Autowired
    private CnabService cnabService;

    @GetMapping("/")
    public String show() {
        return "index";
    }

    @PostMapping("/upload") public String uploadImage(@RequestParam("cnab") MultipartFile file) throws IOException {

        try {
            cnabService.salvar(file);
            lista.addAll(cnabService.listar());
            listaFinal.addAll(cnabService.listarAbatimento());
            return "redirect:/listar";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }

    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("listar");
        modelAndView.addObject("lista", lista);
        modelAndView.addObject("listaFinal", listaFinal);
        return modelAndView;
    }
}