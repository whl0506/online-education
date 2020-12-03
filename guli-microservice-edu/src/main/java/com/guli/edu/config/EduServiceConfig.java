package com.guli.edu.config;

import com.guli.edu.entity.BaseProperties;
import com.guli.edu.entity.EduTeacher;
import com.guli.edu.entity.EduTeacherExample;
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
public class EduServiceConfig implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object param = invocation.getArgs()[1];
        if (param == null ) {
            return invocation.proceed();
        }
//        EduTeacher eduTeacher = (EduTeacher) param;
        // 获取Sql执行类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        switch (sqlCommandType) {
            case INSERT:
                // 设置自动填充时间
                this.setTime(param);
                // 针对讲师实现逻辑删除
                if (param instanceof EduTeacher) {
                    EduTeacher eduTeacher = (EduTeacher) param;
                    if (ObjectUtils.isEmpty(eduTeacher.getIsDeleted())) {
                        eduTeacher.setIsDeleted(0);
                    }
                }
//                if (StringUtils.isEmpty(eduTeacher.getId())) {
//                    // 这里小demo用uuid就行了，具体根据业务来，
//                    // 我们生产是用的redis生成的全局唯一键
//                    eduTeacher.setId(Long.getLong(UUID.randomUUID().toString()));
//                }
                break;
            case UPDATE:
                this.setModifiedTime(param);
                break;
        }
        return invocation.proceed();
    }

    private void setModifiedTime(Object object) {
        if (object instanceof BaseProperties) {
            BaseProperties baseProperties = (BaseProperties) object;
            baseProperties.setGmtModified(new Date());
        }

    }

    private void setTime(Object object) {
        if (object instanceof BaseProperties) {
            BaseProperties baseProperties = (BaseProperties) object;
            if (ObjectUtils.isEmpty(baseProperties.getGmtCreate())) {
                baseProperties.setGmtCreate(new Date());
            }
            if (ObjectUtils.isEmpty(baseProperties.getGmtModified())) {
                baseProperties.setGmtModified(new Date());
            }
        }
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
