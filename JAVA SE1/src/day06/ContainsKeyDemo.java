package day06;

import java.util.HashMap;
import java.util.Map;

public class ContainsKeyDemo {
	public static void main(String[] args) {
		String str="123.456.765.987.432.876.123.987.445.123";
		//ͳ�Ƶ�ǰ�ַ����У�ÿ�����ֳ��ֵĴ���
		//˼·
//		1�Ƚ�ÿ�����ֲ��
//		2˳��ÿ�����ַ���map
//		3����ʱҪ�ȿ���������Ƿ���Ϊkey��map�д�����
//		�����ڣ���������Ϊkey��valueΪ1����һ�γ��֣�
//		���ڣ���������Ϊkeyȡ�������ۼ�һ�ٴ��ȥ
		String[] nums=str.split("\\.");//������ʽ
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