package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * 账户服务实现类
 * Xml配置方式：
 *
 * <bean id="accountService"
 *           class="com.itheima.service.impl.AccountServiceImpl"
 *           scope="prototype"
 *           init-method="init"
 *           destroy-method="destroy">
 *    <property name="" value="" | ref=""/>
 * </>
 *
 * 注解方式
 * 1.用于创建对象的
 *      和<bean>标签作用一样
 *      注解：@Component，value属性用于指定id属性，如果不指定，则默认是类名首字母改小写
 *      1.@Controller
 *      2.@Service
 *      3.@Repository
 *      以上3个注解，作用和@Component是一样的，这3个注解，是Spring为我们提供明确的三层架构注解
 * 2.用于注入数据的
 *      和<property>标签作用一样
 *      注解：Autowired
 *          作用：自动按类型注入，如果容器中有一个bean对象的类型，和要注入的变量的类型匹配，就可以注入成功
 *               1.如果ioc容器中，没有任何一个bean的类型匹配，则报错
 *               2.如果ioc容器中，有多个匹配时，会用变量名作为id去查找，如果也找不到，就会报错
 *          出现位置：可以是变量上，也可以是方法上
 *      细节：
 *          使用注解注入，它的set方法就不是必须要提供的了
 *
 *       注解：Qualifier
 *          作用：在按类型注入的基础上，再按名称注入。他在类变量上不能单独使用（必须配合@Autowired），但是在方法注入上可以
 *          属性：
 *              value:用于指定注入bean的id
 *
 *       注解：Resource
 *          作用：不需要配合@Autowired来使用，可以独立使用，相当于@Autowired加上@Qualifier
 *          属性：
 *              name：用于指定bean的id（不是value了）
 *
 *       上面3个注解，都只能注入其他bean类型的对象，而无法注入基本数据类型实例
 *       另外，集合属性的注入，只能通过Xml的方式来实现。
 *
 *       注解：@Value
 *          作用：用于注入基本数据类型和String类型的数据
 *          属性：
 *              value：用于指定属性的值，它可以使用spring中的SpEL（也就是Spring的EL表达式）
 *              SpEL的写法：${表达式}
 *
 * 3.用于改变作用范围的
 *      和<bean>中使用scope属性一样
 *      Scope属性
 *          作用：指定bean的作用范围
 *          属性：
 *              value：指定范围，常用取值：singleton、prototype，不写默认为singleton
 * 4.和生命周期相关的
 *      和<bean>中使用init-method、destroy-method是一样的
 *      注解：
 *          PreDestroy
 *              作用：指定销毁方法
 *          PostConstruct
 *              作用：指定初始化方法
 */
//@Component("accountService")
@Service("accountService")
//@Scope("prototype")
public class AccountServiceImpl implements IAccountService {
    /**
     * ---------------- 注解配置时使用 ----------------
     */

//    @Autowired
//    @Qualifier("accountDao1")
//    private IAccountDao accountDao;

//    @Resource(name = "accountDao1")
//    private IAccountDao accountDao;
    /**
     * ---------------- 注解配置时使用 ----------------
     */

    /**
     * ---------------- Xml配置时使用 ----------------
     */
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
    /**
     * ---------------- Xml配置时使用 ----------------
     */

    @PostConstruct
    public void init() {
        System.out.println("创建...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁...");
    }

    public void saveAccount(Account account) {
        accountDao.save(account);
    }

    public void updateAccount(Account account) {
        accountDao.update(account);
    }

    public void deleteAccount(Integer accountId) {
        accountDao.delete(accountId);
    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findById(accountId);
    }

    public List<Account> findAllAccount() {
        return accountDao.findAll();
    }
}