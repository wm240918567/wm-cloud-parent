package com.wm.quickstarter.quickstarter.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class User {

    private int id;

    private String name;

}
