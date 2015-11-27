/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.exceptions;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author Sebastian Kupka
 */
public class ValidationDataException extends DataAccessException {

    public ValidationDataException(Throwable cause) {
        super("Validation failed on Persistant Layer", cause);
    }  
}
