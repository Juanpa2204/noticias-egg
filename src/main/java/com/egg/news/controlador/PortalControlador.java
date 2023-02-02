/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.controlador;

import com.egg.news.entidades.Noticia;
import com.egg.news.repositorio.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/")
    public String index(Model model) {
        List<Noticia> listaNoticias = new ArrayList();
        listaNoticias = noticiaRepositorio.buscarNoticiasDisponible();

        model.addAttribute("noticias", listaNoticias);
        return "index.html";
    }
    
    @GetMapping("/registar")
    public String registrar(){
        return "registro.html";
    }
    
     @GetMapping("/login")
    public String login(){
        return "login.html";
    }
}
