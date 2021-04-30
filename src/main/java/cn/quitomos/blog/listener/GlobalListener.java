package cn.quitomos.blog.listener;

import cn.quitomos.blog.service.OptionService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.lang.ref.Reference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

@WebListener
public class GlobalListener implements HttpSessionListener, ServletContextListener, ServletRequestListener {

    private OptionService optionService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 注入optionService
        if (optionService == null) {
            optionService = WebApplicationContextUtils.findWebApplicationContext(sce.getServletContext()).getBean(OptionService.class);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        optionService.addViewsByOne();
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        try {
            cleanUpThreadLocals();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            // 注销数据库驱动
            ServletContext context = sce.getServletContext();
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                DriverManager.deregisterDriver(drivers.nextElement());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清理ThreadLocal
     */
    private void cleanUpThreadLocals() throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Thread thread = Thread.currentThread();
        Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");
        threadLocalsField.setAccessible(true);
        Object threadLocalsInThread = threadLocalsField.get(thread);
        Class threadLocalMapClass = Class.forName("java.lang.ThreadLocal$ThreadLocalMap");
        Method removeInThreadLocalMap = threadLocalMapClass.getDeclaredMethod("remove", ThreadLocal.class);
        removeInThreadLocalMap.setAccessible(true);

        Field tableField = threadLocalMapClass.getDeclaredField("table");
        tableField.setAccessible(true);
        Object table = tableField.get(threadLocalsInThread);
        int length = Array.getLength(table);
        for (int i = 0; i < length; ++i) {
            Object entry = Array.get(table, i);
            Method getMethod = Reference.class.getDeclaredMethod("get");
            if (entry != null) {
                ThreadLocal threadLocal = (ThreadLocal) getMethod.invoke(entry);
                removeInThreadLocalMap.invoke(threadLocalsInThread, threadLocal);
            }
        }
    }
}
