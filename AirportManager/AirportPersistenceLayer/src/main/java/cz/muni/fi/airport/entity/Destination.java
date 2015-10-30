/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airport.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michal Zbranek
 */
@Entity
public class Destination {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable=false,unique=true)
    private String location;
    
    public Destination(){}
    
    public Destination(Long id){
        this.id = id;
    }
    
    public Long getId(){
        return id;
    }
    
    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Destination)) {
            return false;
        }
        final Destination other = (Destination) obj;
        if (!Objects.equals(this.location, other.getLocation())) {
            return false;
        }
        return true;
    }
}
