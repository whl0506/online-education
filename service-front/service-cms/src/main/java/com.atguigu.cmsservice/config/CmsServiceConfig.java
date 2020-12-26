package com.atguigu.cmsservice.config;

import com.atguigu.cmsservice.entity.CrmBanner;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Properties;


@Component
@Log4j2
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class})
})
public class CmsServiceConfig implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object param = invocation.getArgs()[1];
        if (param == null ) {
            return invocation.proceed();
        }
        CrmBanner banner = (CrmBanner) param;
        // 获取Sql执行类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        switch (sqlCommandType) {
            case INSERT:
                // 设置时间
                if (ObjectUtils.isEmpty(banner.getGmtCreate())) {
                    banner.setGmtCreate(new Date());
                }
                if (ObjectUtils.isEmpty(banner.getGmtModified())) {
                    banner.setGmtModified(new Date());
                }
                // 实现逻辑删除
                if (ObjectUtils.isEmpty(banner.getIsDeleted())) {
                    banner.setIsDeleted(false);
                }
                break;
            case UPDATE:
                if (ObjectUtils.isEmpty(banner.getGmtModified())) {
                    banner.setGmtModified(new Date());
                }
                break;
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 这里就直接用MyBatis为我们提供的Plugin.wrap返回代理类
        return Plugin.wrap(target, this);
    }

    /**
     * 这个方法用于在Mybatis配置文件中指定一些属性的。
     * 具体也不是太懂，这里就算了不深究了
     */
    @Override
    public void setProperties(Properties properties) {}
}
