package com.fqishappy.domain.vo;

import com.fqishappy.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/23 00:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MenuTreeVO {
    private List<MenuTreeVO> children;
    private Long id;
    private String menuName;
    private String label;
    private Long parentId;
}
