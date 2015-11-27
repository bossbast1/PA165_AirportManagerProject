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
 * @author Gabrila Podolnikova
 */
public class UpdateFlightDestinationDTO {
    
    private Long id;
    private Destination destination;
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.getId());
        hash = 67 * hash + Objects.hashCode(this.getDestination());
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UpdateFlightDestinationDTO)) {
            return false;
        }
        final UpdateFlightDestinationDTO other = (UpdateFlightDestinationDTO) obj;
        if (!Objects.equals(this.getDestination(), other.getDestination())) {
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
     * @return the destination
     */
    public Destination getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
