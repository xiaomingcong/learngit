package FileCompare;

import java.io.File;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		File f = new File("/Users/xiaomingcong/Downloads/htbase01_extendField");
		if(f.exists()) {
			System.out.println(f.length());
			if(f.isDirectory()) {
				for(String name:f.list()) {
					System.out.println(name);
				}
			}
		}
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
			String excludeSuffix ,String includeSuffix) {
		File sourceDir = new File(sourcePath);
		File targetDir = new File(targetPath);
	}
	
	public void look(String sourcePath,String comparePath,String relativePath) {
		File sourceFile = new File(sourcePath+relativePath);
		if(!sourceFile.exists()) {
			return;
		}
		if(sourceFile.isDirectory()&&sourceFile.list().length>0) {
			for(String fileName:sourceFile.list()) {
				look(sourcePath,comparePath,relativePath+"/"+fileName);
			}
			return;
			
		}
		
	}

}
