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

    @Test
    public void test001() throws Exception {
        //5、使用代理对象执行方法，方法因地制宜，专注于这里
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存对象
     */
    @Test
    public void testSave001() throws Exception {
        User user = new User();
        user.setId(6);
        user.setName("吴歆明");
        user.setPwd("666");
        userDao.saveUser(user);
        sqlSession.commit();
    }
    /**
     * 测试修改对象
     */
    @Test
    public void testupdate001() throws Exception {
        User user = new User();
        user.setId(6);
        user.setName("明明");
        user.setPwd("999");
        userDao.updateUser(user);
        sqlSession.commit();
    }

    /**
     * 测试删除对象
     */
    @Test
    public void testDelete001() throws Exception {
        userDao.deleteUser(7);
        sqlSession.commit();

    }

    /**
     * 测试删除对象
     */
    @Test
    public void testSelect001() throws Exception {
        User user = userDao.selectUser(1);
        System.out.println(user);
        sqlSession.commit();

    }

    /**
     * 测试模糊查询对象
     */
    @Test
    public void testFindByName001() throws Exception {
        List<User> byName = userDao.findByName("%王%");
        for (User user : byName) {
            System.out.println(user);
        }
    }
    /**
     * 测试模糊查询对象第二种方法
     */
    @Test
    public void testFindByName002() throws Exception {
        List<User> byName = userDao.findByName002("王");
        for (User user : byName) {
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal001() throws Exception {
        int total = userDao.findTotal();
        System.out.println(total);
    }
}
