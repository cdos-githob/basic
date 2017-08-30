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
		
		//获取id属性    
        Field idF = c.getDeclaredField("configResources");    
        //实例化这个类赋给o    
        Object o = c.newInstance();    
        //打破封装    
        idF.setAccessible(true); //使用反射机制可以打破封装性，导致了java对象的属性不安全。    
        System.out.println(idF.get(o));
        //给o对象的id属性赋值"110"    
        idF.set(o, new Resource[100]); //set  
        //get    
        System.out.println(((Resource[])idF.get(o)).length);
		
		//获取所有的属性  
        Field[] fs = c.getDeclaredFields();    
     
        //定义可变长的字符串，用来存储属性    
        StringBuffer sb = new StringBuffer();    
        //通过追加的方法，将每个属性拼接到此字符串中    
        //最外边的public定义    
        sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() +"{\n");    
        //里边的每一个属性    
        for(Field field:fs){    
            sb.append("\t");//空格    
            sb.append(Modifier.toString(field.getModifiers())+" ");//获得属性的修饰符，例如public，static等等    
            sb.append(field.getType().getSimpleName() + " ");//属性的类型的名字    
            sb.append(field.getName()+";\n");//属性的名字+回车    
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
