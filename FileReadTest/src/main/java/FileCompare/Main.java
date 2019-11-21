package FileCompare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import main.IOUtils;

public class Main {
	private List<SheetContent> list = new ArrayList<>();
	public static void main(String[] args) {
		File f = new File("E:\\eclipseworkspace\\htgl\\htbase01_extendField\\resources");
		if(f.exists()) {
			System.out.println(f.length());
			if(f.isDirectory()) {
				for(String name:f.list()) {
					System.out.println(name);
				}
			}
		}
		new Main().
		compare("E:\\eclipseworkspace\\htgl\\htbase01_compare\\resources","E:\\eclipseworkspace\\htgl\\htbase01_compare\\src\\resources");
//		System.out.println("sd");
//		Scanner in = new Scanner(System.in);
//		String sourcePath;//比对的原文件路径
//		String comparePath;//和原文件比对的文件路径
//		String targetPath;//生成的目标文件路径
//		String excludeSuffix;//不参与比较的文件后缀
//		String includeSuffix;//参与比对的文件后缀，设置该值时excludeSuffix不生效
//		System.out.println("sourcePath:");
//		sourcePath = in.next();
//		System.out.println("comparePath:");
//		comparePath = in.next();
//		System.out.println("targetPath:");
//		targetPath = in.next();
//		System.out.println("excludeSuffix:");
//		excludeSuffix = in.next();
//		System.out.println("includeSuffix:");
//		includeSuffix = in.next();
//		System.out.println("sourcePath:"+sourcePath);
//		System.out.println("comparePath:"+comparePath);
//		System.out.println("targetPath:"+targetPath);
//		System.out.println("excludeSuffix:"+excludeSuffix);
//		System.out.println("includeSuffix:"+includeSuffix);
	}
	
	public void compare(String sourcePath,String comparePath,String targetPath,
			String excludeSuffix) {
		this.compare(sourcePath, comparePath, targetPath, excludeSuffix, "all");
	}
	public void compare(String sourcePath,String comparePath,String targetPath
			) {
		this.compare(sourcePath, comparePath, targetPath, "none", "all");
	}
	public void compare(String sourcePath,String comparePath) {
		this.compare(sourcePath, comparePath, sourcePath, "none", "all");
	}
	public void compare(String sourcePath,String comparePath,String targetPath,
			String excludeSuffix ,String includeSuffix)  {
		look(sourcePath,comparePath,"");
		
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
            font.setFontHeightInPoints((short) 15); // 字体高度
            font.setColor(Font.COLOR_NORMAL); // 字体颜色
            font.setFontName("黑体"); // 字体
            font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 宽度
            font.setItalic(false); // 是否使用斜体
            
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
                cell.setCellValue(createHelper.createRichTextString(list.get(rownum).getFileName()));// 设置单元格内容
                cell.setCellStyle(cellStyle);// 设置单元格样式
                cell.setCellType(Cell.CELL_TYPE_STRING);// 指定单元格格式：数值、公式或字符串
                
                Cell cell1 = row.createCell((short) 2);
                cell1.setCellValue(createHelper.createRichTextString(list.get(rownum).getRelativePath()));// 设置单元格内容
                cell1.setCellStyle(cellStyle);// 设置单元格样式
                cell1.setCellType(Cell.CELL_TYPE_STRING);// 指定单元格格式：数值、公式或字符串
                
                Cell cell2 = row.createCell((short) 3);
                cell2.setCellValue(createHelper.createRichTextString(list.get(rownum).getOperation()));// 设置单元格内容
                cell2.setCellStyle(cellStyle);// 设置单元格样式
                cell2.setCellType(Cell.CELL_TYPE_STRING);// 指定单元格格式：数值、公式或字符串
                
                Cell cell3 = row.createCell((short) 4);
                cell3.setCellValue(createHelper.createRichTextString(list.get(rownum).getAbsolutePath()));// 设置单元格内容
                cell3.setCellStyle(cellStyle);// 设置单元格样式
                cell3.setCellType(Cell.CELL_TYPE_STRING);// 指定单元格格式：数值、公式或字符串
                

                sheet.autoSizeColumn((short) 0); // 调整第一列宽度
                sheet.autoSizeColumn((short) 1); // 调整第二列宽度
                sheet.autoSizeColumn((short) 2); // 调整第3列宽度
                sheet.autoSizeColumn((short) 3); // 调整第4列宽度
                sheet.autoSizeColumn((short) 4); // 调整第5列宽度
                sheet.autoSizeColumn((short) 5); // 调整第6列宽度

            }

           
            

            // 保存
            String filename = System.getProperty("user.dir")+"/workbook.xls";
            if (workbook instanceof XSSFWorkbook) {
                filename = filename + "x";
            }

            FileOutputStream out;
			try {
				out = new FileOutputStream(filename);
				workbook.write(out);
	            out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println(list.size());
	}
	
	public void look(String sourcePath,String comparePath,String relativePath) {
		File sourceFile = new File(sourcePath+relativePath);
		if(!sourceFile.exists()) {
			return;
		}
		
		if(sourceFile.isDirectory()&&sourceFile.list().length>0) {
			String[] fileNames = sourceFile.list();
			sourceFile=null;
			for(String fileName:fileNames) {
				look(sourcePath,comparePath,relativePath+"\\"+fileName);
			}
			return;
			
		}
		File compareFile = new File(comparePath+relativePath);
		SheetContent sheetContent = new SheetContent();
		if(!compareFile.exists()) {
			sheetContent.setAbsolutePath(sourcePath+relativePath);
			sheetContent.setFileName(sourceFile.getName());
			sheetContent.setRelativePath(relativePath);
			sheetContent.setOperation("新建");
			list.add(sheetContent);
			return;
		}
		if(compareFile.length()!=sourceFile.length()) {
			sheetContent.setAbsolutePath(sourcePath+relativePath);
			sheetContent.setFileName(sourceFile.getName());
			sheetContent.setRelativePath(relativePath);
			sheetContent.setOperation("更新");
			list.add(sheetContent);
			return;
		}
	}
 
}
class SheetContent{
	private String absolutePath;
	private String relativePath;
	private String fileName; 
	private String operation;
	
	public SheetContent() {
		
	}
	
	public SheetContent(String absolutePath, String relativePath, String fileName, String operation) {
		super();
		this.absolutePath = absolutePath;
		this.relativePath = relativePath;
		this.fileName = fileName;
		this.operation = operation;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
}

