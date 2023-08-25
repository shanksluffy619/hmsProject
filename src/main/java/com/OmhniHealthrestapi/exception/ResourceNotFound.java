package com.OmhniHealthrestapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    private String resourcename;
    private String fieldname;
    private long fieldvalue;

    public ResourceNotFound(String resourcename, String fieldname, long fieldvalue) {
        super(String.format("%s not found with %s:%d",resourcename,fieldname,fieldvalue));
        this.resourcename = resourcename;
        this.fieldname = fieldname;
        this.fieldvalue = fieldvalue;
    }



}
