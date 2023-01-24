/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.controlador;

import com.egg.news.excepciones.MiException;
import com.egg.news.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

@Controller
@RequestMapping("/admin")
public class NoticiaControlador {
    
    @Autowired
    private NoticiaServicio noticiaservicio;
    
    @GetMapping("/carga")
    public String carga(){
        return "noticia_form.html";
    }
    
    @PostMapping("/crear")
    public String crear(@RequestParam String titulo,@RequestParam String Cuerpo, ModelMap modelo) throws MiException{
        
        //try{
           noticiaservicio.crearNotica(titulo, Cuerpo);
           modelo.put("exito", "notica cargada"); 
       //} catch (MiException e) {
         //   modelo.put("error", e.getMessage());
       //     return "noticia_form.html";
        //}
         return "redirect:/";
    }
}


