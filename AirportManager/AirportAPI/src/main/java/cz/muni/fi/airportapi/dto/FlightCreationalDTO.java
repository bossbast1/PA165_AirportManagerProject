/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.dto;
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
    private DestinationDTO origin;
    
    @NotNull
    @ManyToOne
    private DestinationDTO destination;
    
    @NotNull
    @ManyToOne
    private AirplaneDTO airplane;
    
    @ManyToMany
    private List<StewardDTO> stewards = new ArrayList<StewardDTO>(); 
    
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
     * @return the origin
     */
    public DestinationDTO getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(DestinationDTO origin) {
        this.origin = origin;
    }

    /**
     * @return the destination
     */
    public DestinationDTO getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(DestinationDTO destination) {
        this.destination = destination;
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
     * @return the stewards
     */
    public List<StewardDTO> getStewards() {
        return Collections.unmodifiableList(stewards);
    }
    
    public void addSteward(StewardDTO s) {
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

}
