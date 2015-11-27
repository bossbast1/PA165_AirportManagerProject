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
 * @author Gabriela Podolnikova
 */
public class UpdateFlightDepartureDTO {
    
    private Long id;
    private Date departure;

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.getId());
        hash = 67 * hash + Objects.hashCode(this.getDeparture());
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
        if (!Objects.equals(this.getDeparture(), other.getDeparture())) {
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
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the departure
     */
    public Date getDeparture() {
        return departure;
    }

    /**
     * @param departure the departure to set
     */
    public void setDeparture(Date departure) {
        this.departure = departure;
    }
}
