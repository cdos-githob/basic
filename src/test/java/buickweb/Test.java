package buickweb;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.springframework.core.io.Resource;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
		Class c = Class.forName("org.springframework.context.support.ClassPathXmlApplicationContext");
		System.out.println(c);
		
//		Object o = c.newInstance();
		
		//��ȡid����    
        Field idF = c.getDeclaredField("configResources");    
        //ʵ��������ำ��o    
        Object o = c.newInstance();    
        //���Ʒ�װ    
        idF.setAccessible(true); //ʹ�÷�����ƿ��Դ��Ʒ�װ�ԣ�������java��������Բ���ȫ��    
        System.out.println(idF.get(o));
        //��o�����id���Ը�ֵ"110"    
        idF.set(o, new Resource[100]); //set  
        //get    
        System.out.println(((Resource[])idF.get(o)).length);
		
		//��ȡ���е�����  
        Field[] fs = c.getDeclaredFields();    
     
        //����ɱ䳤���ַ����������洢����    
        StringBuffer sb = new StringBuffer();    
        //ͨ��׷�ӵķ�������ÿ������ƴ�ӵ����ַ�����    
        //����ߵ�public����    
        sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() +"{\n");    
        //��ߵ�ÿһ������    
        for(Field field:fs){    
            sb.append("\t");//�ո�    
            sb.append(Modifier.toString(field.getModifiers())+" ");//������Ե����η�������public��static�ȵ�    
            sb.append(field.getType().getSimpleName() + " ");//���Ե����͵�����    
            sb.append(field.getName()+";\n");//���Ե�����+�س�    
        }    
        sb.append("}");    
        System.out.println(sb);  
	}
	
	public static void stackqueue(){
		Stack a;
		Queue q;
		LinkedList ll;
	}
}
