package com.itheima.factory;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import com.itheima.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {
    private IAccountService accountService;
    public final void setAccountService(IAccountService accountService) { this.accountService = accountService;}
    private TransactionManager txManager;
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }
    public IAccountService getIAccountServiceProxy(){
        return  (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object returnValues = null;
                try {
                    //1.开启事务
                    txManager.beginTransaction();
                    //2.执行操作
                    returnValues = method.invoke(accountService,args);
                    //3.提交事务
                    txManager.commit();
                } catch (Exception e) {
                    //4.回滚操作
                    txManager.rollback();
                } finally {
                    //5.释放连接
                    txManager.release();
                }
                return returnValues;
            }
        });
    }

}