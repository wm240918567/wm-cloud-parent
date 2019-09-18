package com.wm.quickstarter.quickstarter.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class User {

    private int id;

    @NotBlank(message = "name不能为空")
    private String name;

}
