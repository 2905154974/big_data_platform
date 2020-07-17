package com.qcl.request;

import lombok.Data;

/**
 * niuyabo 20200319
 */
@Data
public class AdminForm {

    private String username;
    private String password;
    private String phone;
    private Integer adminId;
    private Integer adminType;
}
