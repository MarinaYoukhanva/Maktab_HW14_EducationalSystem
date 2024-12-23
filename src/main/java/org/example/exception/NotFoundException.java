package org.example.exception;

import org.example.base.model.BaseEntity;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<? extends BaseEntity<Long>> theClass) {
        super(theClass.getName() + " does not exist");
    }
}
