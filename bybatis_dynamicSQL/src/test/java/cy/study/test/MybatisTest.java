package cy.study.test;

import cy.study.sty.dao.IUserDao;
import cy.study.sty.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {


    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        //1、读取配置文件,T
        in = Resources.getResourceAsStream("SqlMapperConfig.xml");

        //2、创建SqlSessionFactory工厂,T
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3、使用工厂生产SqlSession,T
        sqlSession = factory.openSession();
        //4、使用SqlSession创建Dao接口的代理对象,T
        userDao =  sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void destroy() throws Exception {
        //6、释放资源,T
        sqlSession.close();
        in.close();
    }
    /**
     * 测试动态查询
     */
    @Test
    public void testFindByConditon001() throws Exception {
        User user = new User();
        user.setName("laowang");
        List<User> userByCondition = userDao.findUserByCondition(user);
        for (User user1 : userByCondition) {
            System.out.println(user1);
        }
    }


}
