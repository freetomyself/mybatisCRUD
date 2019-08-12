package com.ticast.test;


/**
 * @program: day02_eesy_01mybatisCRUD--com.ticast.dao
 * @author: WaHotDog 2019-08-08 16:24
 **/


import com.itcast.dao.IUserDao;
import com.itcast.domain.QueryVo;
import com.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Mybatis 的CRUD操作
 */
public class MybatisTest {
    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        //1添加配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2创建SqlSessionFactoryBuilder工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3创建sqlSession
        session = factory.openSession();
        //4获取dao代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws Exception {
        //提交数据
        session.commit();
        //资源关闭
        session.close();
        in.close();
    }

    @Test
    public void testFindAll() throws Exception {
        //5执行查询所有
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testInserUser() throws Exception {
        User user = new User();
        user.setUsername("张三");
        user.setSex("男");
        user.setAddress("浙江绍兴");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setBirthday(format.parse("2018-02-01 10:10:10"));
        System.out.println("保存之前的user："+user);
        userDao.inserUser(user);
        System.out.println("保存之后的user："+user);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateUserById() throws Exception {
        User user = new User();
        user.setUsername("张三三");
        user.setSex("女");
        user.setAddress("浙江绍兴越城区");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setBirthday(format.parse("2019-07-08 15:30:10"));
        user.setId(52);
        userDao.updateUserById(user);
    }

    @Test
    public void testDeleteUser()throws Exception{
        User user = new User();
        user.setId(54);
        user.setUsername("张三");
        userDao.deleteUser(user);
    }

    @Test
    public void testFindById() throws  Exception{
        User user = userDao.findById(56);
        System.out.println(user);
    }

    @Test
    public void testFindByName() throws  Exception{
        List<User> users = userDao.findByName("%三%");
       /* List<User> users = userDao.findByName("三");*/
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testfindTotal() throws  Exception{
        int total = userDao.findTotal();
        System.out.println(total);
    }
    @Test
    public void testFindByQueryVo() throws  Exception{
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%三%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        /* List<User> users = userDao.findByName("三");*/
        for (User u : users) {
            System.out.println(u);
        }
    }
}
