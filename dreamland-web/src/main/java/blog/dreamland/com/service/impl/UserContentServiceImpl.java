package blog.dreamland.com.service.impl;

import blog.dreamland.com.common.PageHelper;
import blog.dreamland.com.dao.UserContentMapper;
import blog.dreamland.com.entity.Comment;
import blog.dreamland.com.entity.UserContent;
import blog.dreamland.com.service.UserContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther SyntacticSugar
 * @data 2019/3/1 0001下午 3:56
 */
@Service
public class UserContentServiceImpl implements UserContentService {


    @Autowired
    private UserContentMapper userContentMapper;

    // todo
    @Override
    public PageHelper.Page<UserContent> findAll(UserContent content, Integer pageNum, Integer pageSize) {

        return null;
    }

    @Override
    public PageHelper.Page<UserContent> findAll(UserContent content, Comment comment, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public PageHelper.Page<UserContent> findAllByUpvote(UserContent content, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<UserContent> findByUserId(Long uid) {
        return null;
    }


    @Override
    public List<UserContent> findAll() {
        return null;
    }

    @Override
    public List<UserContent> findCategoryByUserId(Long uid) {
        List<UserContent> categoryByUserId = userContentMapper.findCategoryByUserId(uid);
        return categoryByUserId;
    }

    @Override
    public PageHelper.Page<UserContent> findByCategory(String category, Long uid, Integer pageSize, Integer pageNum) {
        UserContent userContent = new UserContent();
        //判断category非空
        if (StringUtils.isNotBlank(category) &&!category.equals("")) {
            userContent.setCategory(category);
        }
        userContent.setuId(uid);
        userContent.setPersonal("0");//
        PageHelper.startPage(pageNum, pageSize);
        userContentMapper.select(userContent);
        PageHelper.Page endPage = PageHelper.endPage();
        return endPage;
    }
}
