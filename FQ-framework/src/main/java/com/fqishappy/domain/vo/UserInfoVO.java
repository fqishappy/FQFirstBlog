package com.fqishappy.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fqishappy
 * @date 2024/9/13 19:50
 */
@Data
@Accessors(chain = true)
public class UserInfoVO {
    /**
     * 主键 */
    private Long id;
    /**
     * 昵称 */
    private String nickName;
    /**
     * 头像 */
    private String avatar;
    private String sex;
    private String email;
}
