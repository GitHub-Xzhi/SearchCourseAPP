package com.dong.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
//��ȡ����ʱ��
public class GetTime {
	
	
	public static String timer(){
		String str="";
		int d,c,i;
		Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat df = new SimpleDateFormat("HH");
	    i=calendar.get(Calendar.DAY_OF_WEEK)-1;
	    if(calendar.get(Calendar.DAY_OF_WEEK)==1){
	    	i=7;
	    }
	    c =Integer.parseInt( df.format(calendar.getTime()));	    
	    df = new SimpleDateFormat("m");
	    d = Integer.parseInt(df.format(calendar.getTime()));
	    if(c>0&&c<12){
	    	System.out.println("����");
	    	str="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+i+"' and (`�ڴ�` like '1-2%' or `�ڴ�` like '1-4%' or `�ڴ�` like '3-4%')";
	    }else if(c>=12&&c<18||c==18&&d<30){
	    	System.out.println("����");
	    	str="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+i+"' and (`�ڴ�` like '5-6%' or `�ڴ�` like '5-7%' or `�ڴ�` like '5-8%' or `�ڴ�` like '7-8%')";
	    }else {
	    	System.out.println("����");
	    	str="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+i+"' and (`�ڴ�` like '9-10%' or `�ڴ�` like '9-11%' or `�ڴ�` like '9-12%')";
	    }
	    System.out.println(str);
	    return str;
	}
	public static String getWeek(){
		String str="";
		Calendar calendar = Calendar.getInstance();
		int i=calendar.get(Calendar.DAY_OF_WEEK)-1;
	    if(calendar.get(Calendar.DAY_OF_WEEK)==1){
	    	i=7;
	    }
	    str="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+i+"'";
		return str;
	}
}
