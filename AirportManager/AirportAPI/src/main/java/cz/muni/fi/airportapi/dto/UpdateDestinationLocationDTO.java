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
public class UpdateDestinationLocationDTO {
    
    private Long id;
    
    @NotNull
    @Size(min = 5, max = 50)
    private String location;

    /**
     * Gets identificator of destination
     * 
     * @return id of destination
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets identificator of destination
     * 
     * @param id identificator of destination
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets location of destination
     * 
     * @param location location of destination
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets location of destination
     * 
     * @return location of destination
     */
    public String getLocation() {
        return location;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UpdateDestinationLocationDTO)) {
            return false;
        }
        final UpdateDestinationLocationDTO other = (UpdateDestinationLocationDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "UpdateDestinationLocationDTO{" + "id=" + id + ", location=" + location + '}';
    }
    
}
