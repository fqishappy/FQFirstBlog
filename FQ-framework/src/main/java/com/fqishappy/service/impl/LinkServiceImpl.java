package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.constants.SystemConstants;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.dto.LinkDto;
import com.fqishappy.domain.vo.AdminLinkVO;
import com.fqishappy.domain.vo.LinkVO;
import com.fqishappy.domain.entity.Link;
import com.fqishappy.domain.vo.PageVO;
import com.fqishappy.domain.vo.UpdateLinkVO;
import com.fqishappy.mapper.LinkMapper;
import com.fqishappy.service.LinkService;
import com.fqishappy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2024-09-13 16:43:36
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    private final LinkMapper linkMapper;

    public LinkServiceImpl(LinkMapper linkMapper) {
        this.linkMapper = linkMapper;
    }

    /**
     * 查询所有审核通过的友链
     * @return
     */
    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.STATUS_NORMAL);
        List<Link> links = this.list(queryWrapper);
        //转换成vo
        List<LinkVO> linkVOS = BeanCopyUtils.copyBeanList(links, LinkVO.class);
        return ResponseResult.okResult(linkVOS);

    }

    /**
     * 后台分页模糊查询友链名
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    @Override
    public ResponseResult getLinkList(Integer pageNum, Integer pageSize, String name, String status) {
        Page<Link> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(name), Link::getName, name);
        queryWrapper.eq(StringUtils.hasText(status), Link::getStatus, status);
        page(page, queryWrapper);
        List<AdminLinkVO> adminLinkVOS = BeanCopyUtils.copyBeanList(page.getRecords(), AdminLinkVO.class);
        return ResponseResult.okResult(new PageVO(adminLinkVOS, page.getTotal()));
    }

    /**
     * 后台修改友链时回显友链信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult getLink(Long id) {
        Link byId = getById(id);
        LinkDto linkDto = BeanCopyUtils.copyBean(byId, LinkDto.class);
        return ResponseResult.okResult(linkDto);
    }

    /**
     * 更新友链信息
     * @param link
     * @return
     */
    @Override
    public ResponseResult updateLink(UpdateLinkVO link) {
        Link updateLink = BeanCopyUtils.copyBean(link, Link.class);
        linkMapper.updateById(updateLink);
        return ResponseResult.okResult();
    }

    /**
     * 逻辑删除友链
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteLink(Long id) {
        linkMapper.deleteLink(id);
        return ResponseResult.okResult();
    }

    /**
     * 新增友链
     * @param linkVO
     * @return
     */
    @PostMapping
    public ResponseResult addLink(AdminLinkVO linkVO) {
        linkMapper.insert(BeanCopyUtils.copyBean(linkVO, Link.class));
        return ResponseResult.okResult();
    }
}
