package per.study.mapper;

import org.apache.ibatis.annotations.Param;
import per.study.bean.TUser;

public interface UserMapper {
    /**
     * 每个方法都在Mapper文件中有一个sql标签对应
     * 所有参数都应该用@Param进行签名，以后使用指定的名字在SQL中取值
     *
     * @param id
     * @return
     */
    TUser getUserById(@Param("id") Long id);
}
