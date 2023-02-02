
package com.egg.news.servicios;

import com.egg.news.entidades.Usuario;
import com.egg.news.enumeraciones.Rol;
import com.egg.news.excepciones.MiException;
import com.egg.news.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    private void registar(String email, String password, String password2) throws MiException{
       
        validar(email, password, password2); 
        
        Usuario usuario = new Usuario();
        
        usuario.setNombreUsuario(email);
        usuario.setPassword(password);
        usuario.setRol(Rol.User);
        usuario.setAlta(new Date());
        usuario.setActivo(Boolean.TRUE);
        
        usuarioRepositorio.save(usuario);
    }
    
    private void validar(String email, String password, String password2) throws MiException{
        
        if (email.isEmpty() || email == null) {
            throw new MiException("el email no puede ser nulo o estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("el password no puede ser nulo o estar vacio y debe tener mas de 5 caracteres");
        }
        if (!password.equals(password2)) {
            throw new MiException("las contraseñas deben ser iguales");
        }
        
        
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        
        if (usuario !=null) {
            
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE"+ usuario.getRol().toString());
            
            permisos.add(p);
            
            User user = new User(usuario.getNombreUsuario(), usuario.getPassword(), permisos);
        
        }else{
            return null;
        }
    }
}
