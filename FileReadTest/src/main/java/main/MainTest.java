package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class MainTest {

    public static void main(String[] args) throws Exception {
    	List<String> list=IOUtils.get("add.txt");
        Workbook[] wbs = new Workbook[] { new HSSFWorkbook(), new XSSFWorkbook() };
        for (int i = 0; i < wbs.length; i++) {
            Workbook workbook = wbs[i];
            // 得到一个POI的工具类
            CreationHelper createHelper = workbook.getCreationHelper();

            // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
            Sheet sheet = workbook.createSheet();
            // Sheet sheet = workbook.createSheet("SheetName");

            // 用于格式化单元格的数据
            DataFormat format = workbook.createDataFormat();

            // 设置字体
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 20); // 字体高度
            font.setColor(Font.COLOR_RED); // 字体颜色
            font.setFontName("黑体"); // 字体
            font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 宽度
            font.setItalic(true); // 是否使用斜体
            // font.setStrikeout(true); //是否使用划线

            // 设置单元格类型
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平布局：居中
            cellStyle.setWrapText(true);

            
            

            // 定义几行
            for (int rownum = 0; rownum < list.size(); rownum++) {
                // 创建行
                Row row = sheet.createRow(rownum);
                // 创建单元格
                Cell cell = row.createCell((short) 1);
                cell.setCellValue(createHelper.createRichTextString(list.get(rownum)));// 设置单元格内容
                cell.setCellStyle(cellStyle);// 设置单元格样式
                cell.setCellType(Cell.CELL_TYPE_STRING);// 指定单元格格式：数值、公式或字符串
                

                sheet.autoSizeColumn((short) 0); // 调整第一列宽度
                sheet.autoSizeColumn((short) 1); // 调整第二列宽度

            }

           
            

            // 保存
            String filename = System.getProperty("user.dir")+"/workbook.xls";
            if (workbook instanceof XSSFWorkbook) {
                filename = filename + "x";
            }

            FileOutputStream out = new FileOutputStream(filename);
            workbook.write(out);
            out.close();
        }
    }

}