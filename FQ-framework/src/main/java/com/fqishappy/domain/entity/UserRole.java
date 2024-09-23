package com.fqishappy.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId; 
import com.baomidou.mybatisplus.annotation.TableName; 

/**
 * 用户和角色关联表(UserRole)表实体类
 *
 * @author makejava
 * @since 2024-09-23 15:24:13
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor 
@TableName("sys_user_role")
public class UserRole {
    // 用户ID
    private Long userId;
    // 角色ID
    private Long roleId;
}
