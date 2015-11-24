/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Stromský
 */
@Service
public interface BeanMappingService {
    
    /**
     * Mapping collection of source objects to list of destination objects (DTO)
     * 
     * @param <T> class of source objects
     * @param objectCollection collection of source objects 
     * @param mapToClass destination class 
     * @return list of destination objects (mapped source objects) - DTOs
     */
    public  <T> List<T> mapTo(Collection<?> objectCollection, Class<T> mapToClass);
    
    /**
     * 
     * @param <T> class of source objects
     * @param object souce object
     * @param mapToClass destination class
     * @return destination object (mapped source object) - DTO
     */
    public  <T> T mapTo(Object object, Class<T> mapToClass);
    
}
