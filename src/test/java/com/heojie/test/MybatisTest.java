package com.heojie.test;


import com.haojie.dao.IUserDao;
import com.haojie.domain.QueryVo;
import com.haojie.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * resultMap 结果类型 作为返回值类型
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private IUserDao iUserDao;

    @Before
    public void init() throws Exception {
        //读取配置文件
        in = Resources.getResourceAsStream("sqlmapconfig.xml");
        //创建sqlsessionfactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //使用工厂生成sqlsession对象
        session = factory.openSession(true);
        //使用session创建到接口对象
        iUserDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws Exception {
        //提交事务
       // session.commit();自动提交
        //释放资源
        session.close();
        in.close();
    }

    /**
     * 查询全部
     */
    @Test
    public void testfindAll(){
        List<User> users = iUserDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 查询一个
     */
    @Test
    public void findById(){
        User user = iUserDao.findById(53);
        System.out.println(user);
    }
    /**
     * 模糊查询
     */
    @Test
    public void findByUsername(){
        /*List<User> users = iUserDao.findByUsername("%岛%");*/
        List<User> users = iUserDao.findByUsername("岛");
        System.out.println(users);
    }
    /**
     * 测试使用queryvo查询条件
     */
    @Test
    public void testFindUserByVo(){
        QueryVo queryVo=new QueryVo();
        User user = new User();
        user.setUserName("%岛%");
        queryVo.setUser(user);
        List<User> list = iUserDao.findUserByVo(queryVo);
        for (User one : list) {
            System.out.println(one);
        }
    }
    @Test
    public void testFndByCondition(){
        User user = new User();
        user.setUserName("周杰伦");
        user.setUserSex("女");
        List<User> list = iUserDao.findUserByCondition(user);
        for (User one : list) {
            System.out.println(one);
        }
    }
    /**
     * 测试forreach 查询 id in ()
     */
    @Test
    public void testfindInIds(){
        QueryVo queryVo = new QueryVo();
        List<Integer> list=new ArrayList<Integer>();
        list.add(41);
        list.add(50);
        list.add(55);
        queryVo.setIds(list);
        List<User> ids = iUserDao.findUserInIds(queryVo);
        for (User id : ids) {
            System.out.println(id);
        }
    }
}
