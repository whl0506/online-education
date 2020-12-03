package com.guli.edu.service.impl;

import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.utils.ExcelImportXSSFUtil;
import com.guli.edu.entity.EduSubject;
import com.guli.edu.entity.EduSubjectExample;
import com.guli.edu.mapper.EduSubjectMapper;
import com.guli.edu.service.EduSubjectService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class EduSubjectServiceImpl implements EduSubjectService {

    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    @Override
    public List<String> batchImport(MultipartFile file) {
        // 返回错误信息
        List<String> msg = new ArrayList<>();

        try {
            // 获取Excel文件输入流
            ExcelImportXSSFUtil excelImportXSSFUtil = new ExcelImportXSSFUtil(file.getInputStream());
            XSSFSheet sheet = excelImportXSSFUtil.getSheet();
            int rowNum = sheet.getLastRowNum();
            if (rowNum <= 1) {
                msg.add("请填写数据");
                return msg;
            }
            // 循环读取Excel中每row
            for (int i = 1; i <= rowNum; i++) {
                // 获取每列中数据
                Row row = sheet.getRow(i);
                String levelOneValue = "";
                Long parentId = 0L;
                if (row != null) { //行不为空
                    // 获取1列数据
                    Cell levelOneCell = row.getCell(0);
                    if (levelOneCell != null) {
                        // 1级分类名称
                        int cellType = levelOneCell.getCellType();
                        levelOneValue = excelImportXSSFUtil.getCellValue(levelOneCell,cellType);
                        if (StringUtils.isEmpty(levelOneValue)) {
                            msg.add("第" + i + "行一级分类为空");
                        } else {
                            // 判断数据库中是否有相同一级分类
                            List<EduSubject> eduSubjects = this.getSubjectByTitle(levelOneValue, 0L);
                            if (eduSubjects.size() == 0){
                                EduSubject eduSubject = new EduSubject();
                                eduSubject.setTitle(levelOneValue);
                                eduSubject.setParentId(0L);
                                eduSubject.setSort(0);
                                eduSubjectMapper.insert(eduSubject);
                                parentId = eduSubject.getId();
                            } else {
                                parentId = eduSubjects.get(0).getId();
                            }
                        }
                    }
                    // 二级分类名称
                    String levelTwoValue = "";
                    Cell levelTwoCell = row.getCell(1);
                    if (levelTwoCell != null) {
                        // 1级分类名称
                        int cellType = levelTwoCell.getCellType();
                        levelTwoValue = excelImportXSSFUtil.getCellValue(levelTwoCell,cellType);
                        if (StringUtils.isEmpty(levelTwoValue)) {
                            msg.add("第" + i + "行二级分类为空");
                        } else {
                            // 判断数据库中是否有相同二级分类
                            List<EduSubject> subjects = this.getSubjectByTitle(levelTwoValue, parentId);
                            if (subjects.size() == 0){
                                EduSubject eduSubject = new EduSubject();
                                eduSubject.setTitle(levelTwoValue);
                                eduSubject.setParentId(parentId);
                                eduSubject.setSort(0);
                                eduSubjectMapper.insert(eduSubject);
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            //EXCEL_DATA_ERROR(false, 21005, "Excel数据导入错误");
            e.printStackTrace();
            throw new GuliException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }

        return msg;
    }

    private List<EduSubject> getSubjectByTitle(String levelOneValue,Long parentId) {
        EduSubjectExample example = new EduSubjectExample();
        EduSubjectExample.Criteria criteria = example.createCriteria();
        criteria.andTitleEqualTo(levelOneValue);
        criteria.andParentIdEqualTo(parentId);
        List<EduSubject> eduSubjects = eduSubjectMapper.selectByExample(example);
        return eduSubjects;
    }
}
