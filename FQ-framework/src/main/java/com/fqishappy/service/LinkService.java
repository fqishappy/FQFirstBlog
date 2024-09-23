package com.fqishappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.AddLinkDto;
import com.fqishappy.domain.entity.Link;
import com.fqishappy.domain.vo.UpdateLinkVO;

import java.util.List;

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

    /**
     * 后台分页模糊查询友链名
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    ResponseResult getLinkList(Integer pageNum, Integer pageSize, String name, String status);

    /**
     * 后台修改友链时回显友链信息
     * @param id
     * @return
     */
    ResponseResult getLink(Long id);

    /**
     * 更新友链信息
     * @param link
     * @return
     */
    ResponseResult updateLink(UpdateLinkVO link);

    /**
     * 逻辑删除友链
     * @param ids
     * @return
     */
    ResponseResult deleteLink(List<Long> ids);

    /**
     * 添加友链
     * @param link
     * @return
     */
    ResponseResult addLink(AddLinkDto link);
}
