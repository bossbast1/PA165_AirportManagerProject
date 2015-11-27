/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;

import java.util.Objects;

/**
 *
 * @author Jakub Stromský
 */
public class AirplaneDTO {
    
    private Long id;
    private String name;
    private int capacity;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.getName());
        hash = 89 * hash + this.getCapacity();
        hash = 89 * hash + Objects.hashCode(this.getType());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(!(obj instanceof AirplaneDTO)) {
            return false;
        }
        final AirplaneDTO other = (AirplaneDTO) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (this.getCapacity() != other.getCapacity()) {
            return false;
        }
        return Objects.equals(this.getType(), other.getType());
    }

    
       
}
