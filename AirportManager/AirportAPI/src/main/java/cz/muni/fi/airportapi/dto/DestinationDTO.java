/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;

import java.util.Objects;

/**
 *
 * @author Michal Zbranek
 */
public class DestinationDTO {
    
    private Long id;
    private String location;
    
    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param name
     */
    public void setLocation(String name) {
        this.location = location;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DestinationDTO)) {
            return false;
        }
        final DestinationDTO other = (DestinationDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.location, other.location);
    }

    @Override
    public String toString() {
        return "DestinationDTO{" + "id=" + id + ", location=" + location + '}';
    }
}
