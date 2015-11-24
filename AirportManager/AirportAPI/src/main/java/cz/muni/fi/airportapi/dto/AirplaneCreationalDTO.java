/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;

import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Jakub Stromský
 */
public class AirplaneCreationalDTO {
    
    @NotNull
    @Size(min = 5, max = 50)
    private String name;
    
    @Min(0)
    private int capacity;
    
    @NotNull
    @Size(min = 3, max = 10)
    private String type;

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
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + this.capacity;
        hash = 41 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AirplaneCreationalDTO other = (AirplaneCreationalDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.capacity != other.capacity) {
            return false;
        }
        return Objects.equals(this.type, other.type);
    }

    @Override
    public String toString() {
        return "AirportCreateDTO{" + "name=" + name 
                + ", capacity=" + capacity
                + ", type=" + type + '}';
    }   
}
