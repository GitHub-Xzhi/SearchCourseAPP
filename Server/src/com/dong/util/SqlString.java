package com.dong.util;

import com.dong.main.GetTime;

public class SqlString {
	private String sql;	
	public String getSql(String temp,String temp1){		
		if(temp1==null){
			char c=temp.charAt(0);
			if(c>='0'&&c<='9'){
				sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` like '%"+temp+"%'";
			}else if(c>='a'&&c<='z'||c>='A'&&c<='Z'){
				sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `�γ̺�` like '%"+temp+"%'";
			}else{
				sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where concat(`�γ�����`,`������ʦ����`,`����`) like  '%"+temp+"%'"; 
			}
		}else{
			if(temp1.length()<=3){
				char a=temp1.charAt(0);
				String b=temp1.subSequence(1,temp1.length()).toString();
				//��ȡʵʱʱ������ѯ�γ�
				if(a=='8'){
					System.out.println("dongbo");
					sql=GetTime.timer()+" and `����` like '"+temp+"%' and  `����`  not like '����%'";
					return sql;
				}else if(a=='9'){
					sql=GetTime.getWeek()+" and `����` like '"+temp+"%' and  `����`  not like '����%'";
					return sql;
				}
				
				
				if(b.equals("1")||b.equals("2")){
					sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+a+"' and (`�ڴ�` like '1-2%' or `�ڴ�` like '1-4%' )";
				}else if(b.equals("3")||b.equals("4")){
					sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+a+"' and (`�ڴ�` like '3-4%' or `�ڴ�` like '1-4%' )";
				}else if(b.equals("5")||b.equals("6")){
					sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+a+"' and (`�ڴ�` like '5-6%' or `�ڴ�` like '5-8%'or `�ڴ�` like '5-7%' )";
				}else if(b.equals("7")){
					sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+a+"' and (`�ڴ�` like '7-8%' or `�ڴ�` like '5-8%'or `�ڴ�` like '5-7%' )";
				}else if(b.equals("8")){
					sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+a+"' and (`�ڴ�` like '7-8%' or `�ڴ�` like '5-8%' )";
				}else if(b.equals("9")||b.equals("10")){
					sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+a+"' and (`�ڴ�` like '9-10%' or `�ڴ�` like '9-11%' )";
				}else if(b.equals("11")){
					sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+a+"' and `�ڴ�` like '9-11%' )";
				}else if(b.equals("12")){
					sql="select course_id,�γ�����,�γ̺�,������ʦ����,�ڴ�,����,�ܴηֲ�,���� from course_table where `����` ='"+a+"' and `�ڴ�` like '9-12%' )";
				}
			}
		}
		return sql;
	}
}
