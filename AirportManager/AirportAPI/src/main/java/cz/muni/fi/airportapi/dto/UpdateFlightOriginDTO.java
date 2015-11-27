/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;

import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airport.entity.Flight;
import java.util.Objects;

/**
 *
 * @author Gabriela Podolnikova
 */
public class UpdateFlightOriginDTO {
    private Long id;
    private Destination origin;
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.getId());
        hash = 67 * hash + Objects.hashCode(this.getOrigin());
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Flight)) {
            return false;
        }
        final Flight other = (Flight) obj;
        if (!Objects.equals(this.getOrigin(), other.getOrigin())) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the destination origin
     */
    public Destination getOrigin() {
        return origin;
    }

    /**
     * @param origin the destination to set
     */
    public void setOrigin(Destination origin) {
        this.origin = origin;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
