package per.study.mapper;

import org.apache.ibatis.annotations.Mapper;
import per.study.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> queryList();
}
