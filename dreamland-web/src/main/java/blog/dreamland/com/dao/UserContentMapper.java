package blog.dreamland.com.dao;

import blog.dreamland.com.entity.UserContent;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */
public interface UserContentMapper extends Mapper<UserContent> {

    List<UserContent> findCategoryByUserId(@Param("uid") Long uid);

}
