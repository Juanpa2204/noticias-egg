/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.repositorio;

import com.egg.news.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
    @Query("SELECT u FROM Usuario u WHERE u.email= :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    
    @Query("SELECT n FROM Noticia n WHERE n.baja IS NULL")
    public List<Usuario> listaUsuarios();
}
