package com.fqishappy.domain.vo;

import com.fqishappy.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fqishappy
 * @date 2024/9/20 12:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVO {
    private List<Menu> menus;
}