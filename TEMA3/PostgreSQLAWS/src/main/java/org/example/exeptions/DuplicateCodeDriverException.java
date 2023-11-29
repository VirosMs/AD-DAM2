package org.example.exeptions;

import java.sql.SQLException;


public class DuplicateCodeDriverException extends SQLException {

    public DuplicateCodeDriverException(String message) {
        super(message);
    }


}
