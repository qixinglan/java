package day06;
/**
 * ģ��ģʽ
 * @author asus
 *
 */
public abstract class  Person {
	//�����Լ������к�
	public void sayHello(){
		//���к�
		System.out.println("��Һ�");
		//�����Լ�����
		System.out.println("�ҽ�"+getName());
		//�����Լ����
		System.out.println(getInfo());
		//˵�ݰ�
		System.out.println();
	}
	/**
	 * ��ȡ�Լ�����
	 */
	public abstract String getName();
		
	
	/**
	 * ��ȡ�Լ����
	 */
	public abstract String getInfo();
		
	
	/**
	 * ˵�ټ�
	 */
	public abstract String sayGoodBye();
		
	
}
