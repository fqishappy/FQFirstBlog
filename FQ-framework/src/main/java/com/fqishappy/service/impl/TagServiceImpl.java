package com.fqishappy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqishappy.domain.entity.Tag;
import com.fqishappy.mapper.TagMapper;
import com.fqishappy.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2024-09-19 01:10:21
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}