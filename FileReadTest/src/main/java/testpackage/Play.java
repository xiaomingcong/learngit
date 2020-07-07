package testpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Play {
	
	private final static int FLOOR = 100;
	private final static int[] floorMin = new int[FLOOR+1];
	private final static int[] bestPath = new int[FLOOR+1];
	
	public static void main(String[] args) {
		for(int i=0;i<floorMin.length;i++){
			floorMin[i] = 0;
		}
		floorMin[1] = 0;
		floorMin[2] = 1;
		int index = 100;
		long startTime = System.nanoTime();
		start(FLOOR);
		System.out.println(System.nanoTime() - startTime);
		for(int i=1;i<=FLOOR;i++){
			System.out.println("楼层为"+i+"时,最佳测试楼层为"+bestPath[i]+"共需要测试"+floorMin[i]+"次.");
		}
		int start = 0;
		while(bestPath[index] > 0){
			int floor = bestPath[index];
			index -= floor;
			start += floor;
			System.out.print(start+"->");
		}

	}

	/**
	 * 获取floor层，完这个游戏最少需要的次数。
	 * @param floor
	 * @return
	 */
	private static int start(int floor) {
		if(floor<=2){
			return floorMin[floor];
		}else{
			if(floorMin[floor] == 0){
				int min = FLOOR;//记录floor层,最少需要的次数。
				int bestFloor = 0;
				for(int i=1;i<=floor;i++){//从哪一层开始丢
					int max = 0;//统计从该层丢，各个临界点需要测试多少次。
					int flag = 0;
					for(int j=1;j<=floor;j++){//临界点在哪一层 临界点表示这一层刚开始会摔破，i-1层不会摔破.
						int time = 0;//该临界点的情况下，需要测试多少次。
						if(i>=j){//摔破了
							if(i<=2){
								time = 1;//1 2层丢就破了，肯定1次就测出来了
							}else{
								time = j-1;//n>2时，比如4层，那么肯定需要3次了，即4层一次(破了),1层，2层共3次。
							}
						}else{//未摔破
							time = 1+start(floor-i);//相当于玩同样的游戏，只是楼层变为100-j层。
						}
						if(time > max){//记录各个临界点花费最多的次数
							max = time;
							flag = j;
						}
					}
					if(min > max){
						min = max;
						bestFloor = i;
					}
				}
//				if(floor == FLOOR){
					bestPath[floor] = bestFloor;
//				}
				floorMin[floor] = min;
				return min;
			}else{
				return floorMin[floor];
			}
			
		}
		
	}

}
