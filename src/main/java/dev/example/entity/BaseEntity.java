package dev.example.entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract interface BaseEntity <T extends Serializable> {

    T getId();
    void setId(T id);
}
