package com.wm.quickstarter.quickstarter.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.wmframework.dto.PageBaseDto;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "UserDto对象")
public class UserDto extends PageBaseDto {

    @ApiModelProperty(value = "姓名", name = "name", example = "wm")
    @Length(max = 3, message = "最大3位")
    @NotBlank(message = "name{notnull}")
    private String name;

}
