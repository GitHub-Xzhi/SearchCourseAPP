package com.example.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CourseInfo implements Serializable{
	private String courseName;  //�γ�����
	private int courseId;   	//�γ�ID 
	private String courseNum;		//�γ����
	private String teacherName;	//��ʦ����
	private String courseTime; 		//�ļ����Ͽ�
	private int week;		//�ܼ��Ͽ�
	private String  weekNum;  	//�ļ����пΣ��ϵ�������
	private String  coursePlace;	//�Ͽεص�
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public int getWeek() {	
		return week;
	}
	public String getWeekString(){
		//String temp=null;
		switch(week){
		case 1:
			return "��һ";
		case 2:
			return "�ܶ�";
		case 3:
			return "����";
		case 4:
			return "����";
		case 5:
			return "����";
		case 6:
			return "����";
		case 7:
			return "����";
			default:
				return null;
		}
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public String getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(String weekNum) {
		this.weekNum = weekNum;
	}
	public String getCoursePlace() {
		return coursePlace;
	}
	public void setCoursePlace(String coursePlace) {
		this.coursePlace = coursePlace;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		super.toString();
		return "�γ�����:"+courseName+",��ʦ����"+teacherName+",�Ͽεص�"+coursePlace;
	}
	
}
