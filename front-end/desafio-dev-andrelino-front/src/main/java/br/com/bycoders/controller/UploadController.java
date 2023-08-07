package br.com.bycoders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.bycoders.service.UploadService;

@Controller
public class UploadController {
	@Autowired
	private UploadService uploadService;

	@GetMapping("/")
    public ModelAndView home1() {
		return new ModelAndView("cnab");
    }
    
    @GetMapping("/cnab")
    public ModelAndView home() {    	
		return new ModelAndView("cnab");
    }
    
    
    @PostMapping("/upload")
    public ModelAndView handleFileUpload(@RequestParam("cnab") MultipartFile cnab)  {
        // Verifique se o arquivo não está vazio
        if (cnab.isEmpty()) {
        	ModelAndView mv = new ModelAndView("cnab");
    	    mv.addObject("globalMessage","Arquivo Inexistente! Por favor, anexa um arquivo do modelo CNAB.");
    	    return mv;
        }
        
        return uploadService.upload(cnab);       
      
    }
	
}
