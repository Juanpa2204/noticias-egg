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
import org.springframework.web.bind.annotation.PathVariable;
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
    public String crear(@RequestParam String titulo,@RequestParam String cuerpo, ModelMap modelo) throws MiException{
        
        
        //try{
        System.out.println("cuerpo"+cuerpo);
           noticiaservicio.crearNotica(titulo, cuerpo);
           modelo.put("exito", "notica cargada"); 
       //} catch (MiException e) {
         //   modelo.put("error", e.getMessage());
       //     return "noticia_form.html";
        //}
         return "redirect:/";
    }
    
    @GetMapping("/mostrar")
    public String mostrar(@PathVariable Long id, ModelMap modelo){
        modelo.put("noticia", noticiaservicio.getOne(id));
        
        return "noticia.html"; 
    }
    
}


