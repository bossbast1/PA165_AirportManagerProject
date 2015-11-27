/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportapi.facade;

import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airportapi.dto.FlightCreationalDTO;
import cz.muni.fi.airportapi.dto.FlightDTO;
import cz.muni.fi.airportapi.dto.UpdateFlightsAirplaneDTO;
import cz.muni.fi.airportapi.dto.UpdateFlightArrivalDTO;
import cz.muni.fi.airportapi.dto.UpdateFlightDepartureDTO;
import cz.muni.fi.airportapi.dto.UpdateFlightDestinationDTO;
import cz.muni.fi.airportapi.dto.UpdateFlightOriginDTO;
import java.util.List;

/**
 *
 * @author Gabriela Podolnikova
 */
public interface FlightFacade {
    public FlightDTO getFlightWithId(Long id);
    public List<FlightDTO> getAllFlights();
    public List<FlightDTO> getFlightsByDate(boolean arrival);
    public Long createFlight(FlightCreationalDTO a);
    public void removeFlight(Long id);
    public void updateFlightArrival(UpdateFlightArrivalDTO update);
    public void updateFlightDeparture(UpdateFlightDepartureDTO update);
    public void updateFlightDestination(UpdateFlightDestinationDTO update);
    public void updateFlightOrigin(UpdateFlightOriginDTO update);
    public void updateFlightAirplane(UpdateFlightsAirplaneDTO update);
    public List<FlightDTO> getFlightsByOrigin(Destination origin);
    public List<FlightDTO> getFlightsByDestination(Destination destination);
    //public AirplaneDTO getFlightAirplane(Flight f);
    //public List<StewardDTO> getFlightStewards(Flight f);
    
}
