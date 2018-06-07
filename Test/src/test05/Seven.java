package test05;

public class Seven {
	public static void main(String[] args) {
		Seven seven=new Seven();
		System.out.println((int)'0');
		System.out.println(seven.fun("10a"));
	}
	int fun(String s){
		System.out.println(Integer.parseInt(s, 16));
		int length=s.length();
		int sum=0;
		for(int i=0;i<length;i++){
			if(48<=(s.charAt(i))&&(s.charAt(i))<=57){
				sum+=(s.charAt(i)-48)*Math.pow(16,length-i-1);
			}
			else{
				sum+=(s.charAt(i)-87)*Math.pow(16,length-i-1);
			}
		}
		return sum;
	}
}
