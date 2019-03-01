package blog.dreamland.com.dao;

import blog.dreamland.com.entity.RoleResource;
import tk.mybatis.mapper.common.Mapper;

public interface RoleResourceMapper extends Mapper<RoleResource> {
    int deleteByPrimaryKey(Long id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
}