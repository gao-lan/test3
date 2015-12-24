package com.example.test3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private EditText et_name;
	private EditText et_age;
	private EditText et_tel;
	private EditText et_work;
	
	private TextView tv;
	
	public static final String TECH = "teacher";
	public static final String STUD = "student";
	private static final String TAG = "MainActivity";
	
	List<Student> student ;
	List<Teacher> teacher ;
	
	TreeSet<BusinessCard> tecSet ;
	
	TreeSet<BusinessCard> stuSet ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        et_name = (EditText) findViewById(R.id.input_name);
        et_age = (EditText) findViewById(R.id.input_age);
        et_tel = (EditText) findViewById(R.id.input_tel);
        et_work = (EditText) findViewById(R.id.input_work);
        
        tv = (TextView)findViewById(R.id.show);
        
        student = new ArrayList<Student>();
        teacher = new ArrayList<Teacher>();
        
        tecSet = new TreeSet<BusinessCard>(new AgeCompare());
        
        stuSet = new TreeSet<BusinessCard>(new FirstAlphaCompare());
        
        Teacher tch1 = new Teacher("张三", "13047911234", 26, TECH);
        Teacher tch2 = new Teacher("赵四", "18747911234", 27, TECH);
        Teacher tch3 = new Teacher("王五", "15647911234", 29, TECH);
        Teacher tch4 = new Teacher("马六", "14947911234", 33, TECH);
        Teacher tch5 = new Teacher("魏七", "17047911234", 43, TECH);
        
        Student stu1 = new Student("田三三", "18770811523", 20, STUD);
        Student stu2 = new Student("熊二", "18670811523", 20, STUD);
        Student stu3 = new Student("马云", "13770811523", 20, STUD);
        Student stu4 = new Student("马化腾", "18970811523", 28, STUD);
        Student stu5 = new Student("李彦宏", "15670811523", 27, STUD);
        
        student.add(stu1);
        student.add(stu2);
        student.add(stu3);
        student.add(stu4);
        student.add(stu5);
        
        teacher.add(tch1);
        teacher.add(tch2);
        teacher.add(tch3);
        teacher.add(tch4);
        teacher.add(tch5);
        
        tecSet.addAll(teacher);
        stuSet.addAll(student);
        StringBuffer buf = new StringBuffer();
        buf.append("老师按照年龄排名\r\n");
        Log.i(TAG, "老师按照年龄排名");
        for(BusinessCard s1: tecSet){
        	 Log.i(TAG, s1.getName() + "   " + s1.age + "");
        	 buf.append(s1.getName() + "   " + s1.age + "\r\n");
        }
        buf.append("学生按照首字母排名\r\n");
        Log.i(TAG, "学生按照首字母排名");
        for(BusinessCard s1: stuSet){
        	buf.append(s1.getName() + "\r\n");
       }
        
        tv.setText(buf.toString());
        
    }
    
    public void add(View view){
    	String name = et_name.getText().toString();
    	String age = et_age.getText().toString();
    	String tel = et_tel.getText().toString();
    	String work = et_work.getText().toString();
    	
    }
    
    class AgeCompare implements Comparator<BusinessCard>{

		@Override
		public int compare(BusinessCard lhs, BusinessCard rhs) {
			int age1 = lhs.age;
			int age2 = rhs.age;
			if(age1>age2)
				return 1;
			else 
				return -1;
		}
    }
    
    class FirstAlphaCompare implements Comparator<BusinessCard>{

		@Override
		public int compare(BusinessCard lhs, BusinessCard rhs) {
			String char1 = PinyinUtil.getPinYinHeadChar(String.valueOf(lhs.name.charAt(0)));
			String char2 = PinyinUtil.getPinYinHeadChar(String.valueOf(rhs.name.charAt(0)));
			char[] char3 = char1.toCharArray();
			char[] char4 = char2.toCharArray();
			if(char3[0]>char4[0])
				return 1;
			else
				return -1;
		}
    }
    
    
}
