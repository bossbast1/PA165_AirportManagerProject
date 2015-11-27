/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;

import cz.muni.fi.airport.entity.Flight;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Gabrila Podolnikova
 */
public class UpdateFlightArrivalDTO {
    private Long id;
    private Date arrival;
    
    /**
     * @return the arrival
     */
    public Date getArrival() {
        return arrival;
    }

    /**
     * @param arrival the arrival to set
     */
    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
        
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.getArrival());
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
        if (!Objects.equals(this.getArrival(), other.getArrival())) {
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

    public void setId(Long id) {
        this.id = id;
    }
}
