package com.clouddo.system.mapper;

import com.cloudd.commons.auth.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户mapper层
 * @author zhongming
 * @since 3.0
 * 2018/5/4下午5:00
 */
@Mapper
public interface UserMapper {

    User get(Long userId);

    List<User> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(User user);

    int update(User user);

    int remove(Long userId);

    int batchRemove(Long[] userIds);

    Long[] listAllDept();
}
