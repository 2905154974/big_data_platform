package com.qcl.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * niuyabo 20200319
 */
@Data
public class big_data_phone_Req {
    private Integer Id;
    private String Sex;
    @NotEmpty(message = "名字必填")
    private String Name;
    @NotNull(message = "手机号必填")
    private String Phone;
//    private Integer foodId;
//    @NotEmpty(message = "菜品名必填")
//    private String foodName;
//    @NotNull(message = "菜品价格必填")
//    private BigDecimal foodPrice;
//    private Integer foodStock;
//    private String foodDesc;
//    @NotEmpty(message = "菜品图必填")
//    private String foodIcon;
//
//    private Integer leimuType;
}
