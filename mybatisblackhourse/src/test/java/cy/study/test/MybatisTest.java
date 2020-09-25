package cy.study.test;

import cy.study.sty.dao.IUserDao;
import cy.study.sty.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void test001() throws Exception {

        //1、读取配置文件,T
        InputStream in = Resources.getResourceAsStream("SqlMapperConfig.xml");

        //2、创建SqlSessionFactory工厂,T
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3、使用工厂生产SqlSession,T
        SqlSession session = factory.openSession();
        //4、使用SqlSession创建Dao接口的代理对象,T
        IUserDao userDao = session.getMapper(IUserDao.class);

        //5、使用代理对象执行方法，方法因地制宜，专注于这里
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6、释放资源,T
        session.close();
        in.close();
    }
}
