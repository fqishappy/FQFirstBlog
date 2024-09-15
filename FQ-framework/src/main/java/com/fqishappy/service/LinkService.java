package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.entity.Link;

/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-09-13 16:43:36
 */
public interface LinkService extends IService<Link> {
    /**
     * 查询所有审核通过的友链
     * @return
     */
    ResponseResult getAllLink();
}
