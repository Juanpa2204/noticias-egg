/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.repositorio;

import com.egg.news.entidades.Noticia;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long>{
    
    @Query("select n from Noticia n where n.titulo= :titulo")
    public Noticia buscarPorTitulo(@Param("titulo") String titulo);
     
    @Query("SELECT n.baja FROM Noticia n WHERE n.id= :id")
    public Date buscarBaja(@Param("id") Long id);
    
    @Query("SELECT n FROM Noticia n WHERE n.baja IS NULL")
    public List<Noticia> buscarNoticiasDisponible();
    
}
