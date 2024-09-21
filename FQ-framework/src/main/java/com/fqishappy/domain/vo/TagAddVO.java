package com.fqishappy.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author fqishappy
 * @date 2024/9/21 14:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagAddVO {
    private String name;
    //备注
    private String remark;
}