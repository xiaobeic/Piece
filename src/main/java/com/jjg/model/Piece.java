package com.jjg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Piece {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;


}
