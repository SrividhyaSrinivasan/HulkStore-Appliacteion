package com.srividhya.hulkstore.utils;

import com.srividhya.hulkstore.errors.GenericError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static ResponseEntity getItemNotExistResponse(Exception e){
        GenericError error = new GenericError("404", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    };
    public static ResponseEntity getItemExistResponse(Exception e){
        GenericError error = new GenericError("409", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    };
    // Inventory Out of Stock Exception response
    public static ResponseEntity getProductOutOfStockResponse(Exception e){
        GenericError error = new GenericError("404", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    };


}
