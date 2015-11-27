/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;

import cz.muni.fi.airport.entity.Airplane;
import cz.muni.fi.airport.entity.Flight;
import java.util.Objects;

/**
 *
 * @author Gabriela Podolnikova
 */
public class UpdateFlightsAirplaneDTO {
    
    private Long id;
    private Airplane airplane;
    
     @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.getId());
        hash = 67 * hash + Objects.hashCode(this.getAirplane());
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
        if (!Objects.equals(this.getAirplane(), other.getAirplane())) {
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
     * @return the airplane
     */
    public Airplane getAirplane() {
        return airplane;
    }

    /**
     * @param airplane the airplane to set
     */
    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
}
