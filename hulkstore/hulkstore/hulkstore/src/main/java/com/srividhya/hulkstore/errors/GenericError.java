package com.srividhya.hulkstore.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenericError {
    String errorCode ;
    String errorMessage;

}
