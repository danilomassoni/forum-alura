package br.com.alura.forum.controller;


import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        if(nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.convert(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.convert(topicos);
        }
    }

    @PostMapping
    public void cadastrar(@RequestBody TopicoForm form) {
            Topico topico = form.converter(cursoRepository);
            topicoRepository.save(topico);
        }


    }


