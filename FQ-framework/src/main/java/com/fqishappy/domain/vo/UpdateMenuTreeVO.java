package com.fqishappy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/23 03:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMenuTreeVO {
    List<MenuTreeVO> menus;
    List<Long> checkedKeys;
}
