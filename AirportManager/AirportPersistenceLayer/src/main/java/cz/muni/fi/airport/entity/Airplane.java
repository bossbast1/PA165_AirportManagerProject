/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airport.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Jakub Stromský
 */

@Entity
public class Airplane {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private int capacity;
    private String type;

    public Airplane(Long id) {
        this.id = id;
    }

    public Airplane() {
    }

    public String getName() {
        return name;
    }   
    
    public Long getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.getName());
        hash = 67 * hash + this.getCapacity();
        hash = 67 * hash + Objects.hashCode(this.getType());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Airplane)) {
            return false;
        }
        final Airplane other = (Airplane) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (this.getCapacity() != other.getCapacity()) {
            return false;
        }
        return Objects.equals(this.getType(), other.getType());
    }
    
    
}
