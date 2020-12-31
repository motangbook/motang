package com.motang.portal.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.motang.common.core.commonenum.WrapperEnum;
import com.motang.admin.entity.Author;
import com.motang.portal.exception.PortalException;
import com.motang.portal.mapper.AuthorMapper;
import com.motang.portal.service.IAuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description  作者表 服务实现类
 * @author liuhu
 * @Date 2020/12/15 21:55
 */
@Service
@Slf4j
public class AuthorServiceImpl  implements IAuthorService {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public void saveAuthor(Author authorO) {
        try {
            authorMapper.insert(authorO);
        }catch (Exception e){
            log.error("保存书籍作者失败！");
            throw new PortalException("保存书籍作者失败！");
        }
    }

    @Override
    public Author selectByName(String authorName) {
        return authorMapper.selectOne(new QueryWrapper<Author>().eq(WrapperEnum.PEN_NAME.getColumn(),authorName));
    }
}
