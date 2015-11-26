/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.facade;

import cz.muni.fi.airportapi.facade.FlightFacade;
import cz.muni.fi.airportservicelayer.services.BeanMappingService;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kuba
 */
@Service
@Transactional
public class FlightFacadeImpl implements FlightFacade {
}
