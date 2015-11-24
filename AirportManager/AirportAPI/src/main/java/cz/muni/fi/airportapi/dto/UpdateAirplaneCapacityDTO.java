/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;

import java.util.Objects;
import javax.validation.constraints.Min;

/**
 *
 * @author Jakub Stromský
 */
public class UpdateAirplaneCapacityDTO {
    
    private Long airplaneId;
    
    @Min(0)
    private int capacity;

    public Long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.airplaneId);
        hash = 29 * hash + this.capacity;
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
        final UpdateAirplaneCapacityDTO other = (UpdateAirplaneCapacityDTO) obj;
        if (!Objects.equals(this.airplaneId, other.airplaneId)) {
            return false;
        }
        return this.capacity == other.capacity;
    }

    @Override
    public String toString() {
        return "UpdateAirplaneCapacityDTO{" + "airplaneId=" + airplaneId 
                + ", capacity=" + capacity + '}';
    }  
}
