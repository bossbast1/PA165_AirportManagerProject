/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Michal Zbranek
 */
public class DestinationCreationalDTO {
    
    @NotNull
    @Size(min = 5, max = 50)
    private String location;

    /**
     * Gets the location of destination
     * 
     * @return the location of destination
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of destination
     * 
     * @param location location to be set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DestinationCreationalDTO)) {
            return false;
        }
        final DestinationCreationalDTO other = (DestinationCreationalDTO) obj;
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DestinationCreationalDTO{" + "location=" + location + '}';
    }
  
}
