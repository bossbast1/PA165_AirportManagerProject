/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;

import cz.muni.fi.airport.entity.Airplane;
import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airport.entity.Flight;
import cz.muni.fi.airport.entity.Steward;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Gabriela Podolnikova
 */
public class FlightCreationalDTO {
   
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date arrival;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date departure;
    
    @NotNull
    @ManyToOne
    private DestinationCreationalDTO origin;
    
    @NotNull
    @ManyToOne
    private DestinationCreationalDTO destination;
    
    @NotNull
    @ManyToOne
    private AirplaneCreationalDTO airplane;
    
    @ManyToMany
    private List<StewardCreationalDTO> stewards = new ArrayList<>(); 
    
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

    /**
     * @return the stewards
     */
    public List<StewardCreationalDTO> getStewards() {
        return Collections.unmodifiableList(stewards);
    }
    
    public void addSteward(StewardCreationalDTO s) {
        stewards.add(s);
    }
        
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.getArrival());
        hash = 67 * hash + Objects.hashCode(this.getDeparture());
        hash = 67 * hash + Objects.hashCode(this.getOrigin());
        hash = 67 * hash + Objects.hashCode(this.getDestination());
        hash = 67 * hash + Objects.hashCode(this.getAirplane());
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FlightCreationalDTO)) {
            return false;
        }
        final FlightCreationalDTO other = (FlightCreationalDTO) obj;
        if (!Objects.equals(this.getArrival(), other.getArrival())) {
            return false;
        }
        if (!Objects.equals(this.getDeparture(), other.getDeparture())) {
            return false;
        }
        if (!Objects.equals(this.getDestination(), other.getDestination())) {
            return false;
        }
        if (!Objects.equals(this.getOrigin(), other.getOrigin())) {
            return false;
        }
        if (!Objects.equals(this.getAirplane(), other.getAirplane())) {
            return false;
        }
        return true;
    }

    /**
     * @return the origin
     */
    public DestinationCreationalDTO getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(DestinationCreationalDTO origin) {
        this.origin = origin;
    }

    /**
     * @return the destination
     */
    public DestinationCreationalDTO getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(DestinationCreationalDTO destination) {
        this.destination = destination;
    }

    /**
     * @return the airplane
     */
    public AirplaneCreationalDTO getAirplane() {
        return airplane;
    }

    /**
     * @param airplane the airplane to set
     */
    public void setAirplane(AirplaneCreationalDTO airplane) {
        this.airplane = airplane;
    }

}
