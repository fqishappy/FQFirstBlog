package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.constants.SystemConstants;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.domain.vo.LinkVO;
import com.fqishappy.domain.entity.Link;
import com.fqishappy.mapper.LinkMapper;
import com.fqishappy.service.LinkService;
import com.fqishappy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2024-09-13 16:43:36
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

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
}
