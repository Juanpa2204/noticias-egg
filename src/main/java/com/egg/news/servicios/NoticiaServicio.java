/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.servicios;

import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MiException;
import com.egg.news.repositorio.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio; 
    
    
    @Transactional
    public void crearNotica(String titulo, String cuerpo) throws MiException{
        
        Noticia noticia = new Noticia();
        
        //validar(id, titulo, cuerpo); 
        noticia.setId(Math.round(Math.random()*100));
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setAlta(new Date());
        
        noticiaRepositorio.save(noticia); 
    }
    
    public List<Noticia> listarNoticias(){
       List<Noticia> noticias = new ArrayList();
       noticias = noticiaRepositorio.findAll();
       return noticias;
    }
    
    
    @Transactional
    public void modificarNoticia(Long id, String titulo, String cuerpo) throws MiException{
        
        validar(id, titulo, cuerpo);
        
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticiaRepositorio.save(noticia);
        }
    }
    
    public void darBaja(Long id, String titulo, String cuerpo, Date alta){
         
    }
    
    public void validar(Long id, String titulo, String cuerpo) throws MiException{
        
    if (titulo.isEmpty() || titulo ==null) {
            throw new MiException("el titulo no puede ser nulo o esta vacio");
        }
    if (cuerpo.isEmpty() || cuerpo ==null) {
            throw new MiException("el cuerpo no puede ser nulo o esta vacio");
        }
}
    
    public Noticia getOne(Long id){
        return noticiaRepositorio.getOne(id);
    }

}
