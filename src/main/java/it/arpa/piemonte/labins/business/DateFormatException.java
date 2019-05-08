/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.arpa.piemonte.labins.business;

import java.time.DateTimeException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author utente
 */
@Provider
public class DateFormatException implements ExceptionMapper<DateTimeException> {

    @Override
    public Response toResponse(DateTimeException exception) {
        return Response.status(Response.Status.BAD_REQUEST).header("caused-by", "errore nel formato della data").build();
    }
    
}
