package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/23 14:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserVO {
    private String email;
    private Long id;
    private String nickName;
    private String sex;
    private String status;
    private List<Long> roleIds;
}
