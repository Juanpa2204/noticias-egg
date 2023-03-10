/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.controlador;

import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaservicio;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_JOURNALIST')")
    @GetMapping("/carga")
    public String carga() {
        return "noticia_form.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_JOURNALIST')")
    @PostMapping("/crear")
    public String crear(@RequestParam(required=false) Long id, @RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) throws MiException {

        try {
            noticiaservicio.crearNoticia(id, titulo, cuerpo);
            modelo.put("exito", "notica cargada corretamente");

        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "noticia_form.html";
        }
        return "redirect:/inicio";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_JOURNALIST')")
    @GetMapping("/mostrar/{id}")
    public String mostrar(@PathVariable Long id, String titulo, String cuerpo, ModelMap modelo) {

        Noticia noticia = noticiaservicio.listarNoticiaId(id);
        modelo.addAttribute("noticia", noticia);

        return "noticia.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_JOURNALIST')")
    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Noticia> noticias = noticiaservicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "noticia_list.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_JOURNALIST')")
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia", noticiaservicio.getOne(id));
        return "noticia_modificar.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_JOURNALIST')")
    @PostMapping("/modificar/{id}")
    public String actualizar(@PathVariable(required=false) Long id, String titulo, String cuerpo, ModelMap modelo) throws MiException {
     
           try {
           noticiaservicio.modificarNoticia(id, titulo, cuerpo);
            modelo.put("exito", "notica modificada corretamente");
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
        return "noticia_modificar.html";
        }
           return "redirect:../lista";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo) {
        noticiaservicio.darBaja(id);
        modelo.put("exito", "noticia eliminada"); 
        return "redirect:/inicio";
    }
}
 