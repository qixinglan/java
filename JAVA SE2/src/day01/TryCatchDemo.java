package day01;
/**
 * try catch
 * @author asus
 *
 */
public class TryCatchDemo {
	public static void main(String[] args){
		try{
			//String str=null;
			String str="abc";
			//当jvm执行到str。length（）时发现str是null，会创建一个Nullpointexception实例，并将错误信息设置到这个实例中，并抛出
			System.out.println(str.length());//异常后面的语句不执行，直接去找catch语句，从catch语句开始执行
			System.out.println(str.charAt(0));
			int num=Integer.parseInt(str);//NumFormatException
			System.out.println("未执行");
			
		}catch(NullPointerException e){
			System.out.println("这里有个空指针");
		}catch(StringIndexOutOfBoundsException e){
			System.out.println("字符串下标越界异常");
		}catch(Exception e){
			System.out.println("出现未知异常");
		}
		/**
		 * 捕获异常应按照从具体到宽泛的原则，因为一旦捕获后面的catch语句将不执行
		 */
		
		System.out.println("程序结束了");
	}
}
