package br.com.dynamicapp.softplayerapi.controller;

import br.com.dynamicapp.softplayerapi.respository.ProdutoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping({"/contacts"})
public class UsuarioController {

    private ProdutoRepository repository;

    UsuarioController(ProdutoRepository contactRepository) {
        this.repository = contactRepository;
    }
}