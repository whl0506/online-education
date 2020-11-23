package com.gui.edu;

import com.guli.edu.GuliEduApplication;
import com.guli.edu.entity.EduTeacher;
import com.guli.edu.mapper.EduTeacherMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GuliEduApplication.class)
public class GuliEduTest {

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Test
    public void insertTest(){
        EduTeacher eduTeacher = new EduTeacher();
        eduTeacher.setName("Bob");
        eduTeacher.setLevel(2);
        int result = eduTeacherMapper.insertSelective(eduTeacher);
        System.out.println("result = " + result);

    }

}
