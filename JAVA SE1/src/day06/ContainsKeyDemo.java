package day06;

import java.util.HashMap;
import java.util.Map;

public class ContainsKeyDemo {
	public static void main(String[] args) {
		String str="123.456.765.987.432.876.123.987.445.123";
		//统计当前字符串中，每组数字出现的次数
		//思路
//		1先将每组数字拆出
//		2顺序将每组数字放入map
//		3放入时要先看这个数字是否作为key在map中存在了
//		不存在：将数字作为key，value为1（第一次出现）
//		存在：将数字作为key取出，并累加一再存进去
		String[] nums=str.split("\\.");//正则表达式
		System.out.println(nums.length);
		Map<String,Integer>map=new HashMap<String,Integer>();
		for(String num:nums){
			if(map.containsKey(num)){
				map.put(num, map.get(num)+1);
			}else{
				map.put(num, 1);
			}
		}
		System.out.println(map);
	}
}