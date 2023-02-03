/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.controlador;

import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.repositorio.NoticiaRepositorio;
import com.egg.news.servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/")
    public String index(Model model) {
        List<Noticia> listaNoticias = new ArrayList();
        listaNoticias = noticiaRepositorio.buscarNoticiasDisponible();

        model.addAttribute("noticias", listaNoticias);
        return "index.html";
    }

    @GetMapping("/admin/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/admin/registro")
    public String registro(@RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {

        try {
            usuarioServicio.registrar(email, password, password2);
            modelo.put("exito", "usuario registrado correctamente");
            return "redirect:/";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("email", email); 
            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required=false) String error, ModelMap modelo){
        
        if (error !=null) {
            modelo.put("error", "usuario o contrasenha invalida!");
        }
        return "login.html";
    }
    
    @GetMapping("/inicio")
    public String inicio(){
         return "inicio.html";
}
}
