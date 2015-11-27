/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;

import java.util.Objects;

/**
 *
 * @author Gabriela Podolnikova
 */
public class UpdateFlightsAirplaneDTO {
    
    private Long id;
    private AirplaneDTO airplane;
    
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
        if (!(obj instanceof UpdateFlightsAirplaneDTO)) {
            return false;
        }
        final UpdateFlightsAirplaneDTO other = (UpdateFlightsAirplaneDTO) obj;
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
    public AirplaneDTO getAirplane() {
        return airplane;
    }

    /**
     * @param airplane the airplane to set
     */
    public void setAirplane(AirplaneDTO airplane) {
        this.airplane = airplane;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
