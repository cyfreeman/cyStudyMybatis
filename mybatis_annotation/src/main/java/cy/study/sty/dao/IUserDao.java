package cy.study.sty.dao;

import cy.study.sty.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserDao {

    @Select("select * from user")
    List<User> findAll();

    /**
     * 增
     * @param user
     */
    @Insert("insert into user(id, name, pwd)values(#{id},#{name},#{pwd})")
    void saveUser(User user);

    /**
     * 改
     * @param user
     */
    @Update("update user set name=#{name},pwd=#{pwd} where id=#{id}")
    void updateUser(User user);

    /**
     * 删
     * @param id
     */
    @Delete("delete from user where id = #{uid}")
    void deleteUser(Integer id);

    /**
     * 查
     * @param id
     */
    @Select("select * from user where id = #{uid}")
    User selectUser(Integer id);

    /**
     * 根据用户名称模糊查询用户信息
     * @param name
     */
    @Select("select * from user where name like #{name}")
    List<User> findByName(String name);

    /**
     * 根据用户名称模糊查询用户信息
     * @param name
     */
    @Select("select * from user where name like '%${value}%'")
    List<User> findByName002(String name);

    /**
     * 查询总用户数
     * @return
     */
    @Select("select count(id) from user")
    int findTotal();

}
