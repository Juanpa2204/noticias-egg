/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.news.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Noticia {
    
    @Id
    private Long id; 
    private String titulo;
    private String cuerpo;
    @Temporal(TemporalType.DATE)
    private Date alta;
     @Temporal(TemporalType.DATE)
    private Date baja; 

}
