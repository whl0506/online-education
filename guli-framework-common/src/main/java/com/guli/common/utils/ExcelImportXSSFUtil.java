package com.guli.common.utils;

import lombok.Data;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ExcelImportXSSFUtil {

    private XSSFFormulaEvaluator formulaEvaluator;
    private XSSFSheet sheet;
    private String pattern;// 日期格式

    public ExcelImportXSSFUtil() {
        super();
    }

    public ExcelImportXSSFUtil(InputStream is) throws IOException {
        this(is, 0, true);
    }

    public ExcelImportXSSFUtil(InputStream is, int sheetIndex) throws IOException {
        this(is, sheetIndex, true);
    }

    public ExcelImportXSSFUtil(InputStream is, int sheetIndex, boolean evaluateFormular) throws IOException {
        super();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        this.sheet = workbook.getSheetAt(sheetIndex);
        if (evaluateFormular) {
            this.formulaEvaluator = new XSSFFormulaEvaluator(workbook);
        }
    }

    public String getCellValue(Cell cell, int cellType) throws Exception {

        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC://0

                if (HSSFDateUtil.isCellDateFormatted(cell)) {//日期
                    Date date = cell.getDateCellValue();
                    if (pattern != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        return sdf.format(date);
                    } else {
                        return date.toString();
                    }
                } else {
                    // 不是日期格式，则防止当数字过长时以科学计数法显示
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    return cell.toString();
                }

            case Cell.CELL_TYPE_STRING://1
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_FORMULA://2

                if (this.formulaEvaluator == null) {//得到公式
                    return cell.getCellFormula();
                } else {//计算公式
                    CellValue evaluate = this.formulaEvaluator.evaluate(cell);
                    cellType = evaluate.getCellType();
                    return String.valueOf(this.getCellValue(cell, cellType));
                }
            case Cell.CELL_TYPE_BLANK://3
                //注意空和没有值不一样，从来没有录入过内容的单元格不属于任何数据类型，不会走这个case
                return "";
            case Cell.CELL_TYPE_BOOLEAN://4
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_ERROR:
                throw new Exception("数据类型错误");
            default:
                throw new Exception("未知数据类型:" + cellType);
        }
    }
}
