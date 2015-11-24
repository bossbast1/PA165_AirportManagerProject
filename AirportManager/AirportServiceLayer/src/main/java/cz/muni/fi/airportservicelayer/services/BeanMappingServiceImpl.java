/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jakub Stromsky
 */
public class BeanMappingServiceImpl implements BeanMappingService {
    
    @Autowired
    Mapper mapper;

    @Override
    public <T> List<T> mapTo(Collection<?> objectCollection, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for(Object o : objectCollection) {
            mappedCollection.add(mapper.map(o, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public <T> T mapTo(Object object, Class<T> mapToClass) {
        return mapper.map(object, mapToClass);
    }
    
}
