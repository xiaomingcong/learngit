package testpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Play2 {
	
	private final static int FLOOR = 100;
	private final static int[] floorMin = new int[FLOOR+1];
	private final static int[] bestPath = new int[FLOOR+1];
	
	public static void main(String[] args) {
		for(int i=0;i<floorMin.length;i++){
			floorMin[i] = 0;
		}
		floorMin[1] = 1;
		floorMin[2] = 1;
		floorMin[3] = 1;
		bestPath[1] = 1;
		bestPath[2] = 2;
		bestPath[3] = 2;
		long startTime = System.nanoTime();
		for(int i=4;i<=FLOOR;i++){
			start(i);
		}
		System.out.println(System.nanoTime() - startTime);
		for(int i=1;i<=FLOOR;i++){
			System.out.print("楼层为"+i+"时,最多测试次数为："+floorMin[i]+"次. 路径为：");
			int k = bestPath[i];
			while(k < i) {
				System.out.print(k+"-->");
				k = k + bestPath[i - k];
			}
			System.out.println();
		}
	}

	/**
	 * 获取floor层，完这个游戏最少需要的次数。
	 * @param floor
	 * @return
	 */
	private static int start(int floor) {
		if(floor<=3){
			return floorMin[floor];
		}else{
				int min = floor;//记录floor层,最少需要的次数。
				int temp;
				for(int i=3;i<floor;i++){//从哪一层开始丢
					int a1 = 1 + floorMin[floor - i];//没破,+1次，继续重复这个游戏，前面已经算出start(floor - i)放入floorMin[floor - i]中
					int a2 = 1+i-2;//破了,就从2开始
					if(a1 >= a2) {
						temp = a1;
					}else {
						temp = a2;
					}
					if(temp < min) {//本次计算出来的次数更小，更新最小次数
						min = temp;
						bestPath[floor] = i;
					}
					if(min == floorMin[floor -1]) {//最少次数已经跟floor - 1层的次数一样了，没必要计算了：分支限界
						floorMin[floor] = min;
						return min;
					}
				}
				floorMin[floor] = min;
				return min;
		}
		
	}

}
