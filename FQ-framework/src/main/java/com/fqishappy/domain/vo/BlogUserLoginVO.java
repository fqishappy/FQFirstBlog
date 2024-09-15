package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqishappy
 * @date 2024/9/13 19:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserLoginVO {
    private String token;
    private UserInfoVO userInfoVO;
}
