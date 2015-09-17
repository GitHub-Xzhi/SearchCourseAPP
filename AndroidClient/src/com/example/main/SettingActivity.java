package com.example.main;

import java.util.Calendar;


import com.example.dialog.OneWheel;
import com.example.schedule.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingActivity extends Activity {

	ImageView imageView;
	TextView title;
	RelativeLayout settingWeek;
	RelativeLayout settingmaxcount;
	RelativeLayout currentTerm;
	TextView curTerm;
	TextView maxNum;
	TextView curWeek;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_settings);
		title = (TextView) findViewById(R.id.title_txv_title);
		title.setText("�γ�����");
		imageView = (ImageView) findViewById(R.id.title_imgv_left_icon);
		currentTerm = (RelativeLayout) findViewById(R.id.setting_rlyt_switch_semester);
		settingWeek = (RelativeLayout) findViewById(R.id.setting_rlyt_cur_week);
		settingmaxcount = (RelativeLayout) findViewById(R.id.setting_rlyt_maxcount);
		curWeek=(TextView)findViewById(R.id.setting_txv_cur_week);
		maxNum=(TextView)findViewById(R.id.setting_txv_maxcount);
		curTerm=(TextView)findViewById(R.id.setting_txv_switch_semester);
		
		
		final SharedPreferences courseSettings = getSharedPreferences(
				"course_setting", Activity.MODE_PRIVATE);
		
		
		// ��ȡ��ǰѧ��
		String currentTerms = courseSettings.getString("currentTerm",null);
		// ��ȡ����
		// String currentWeek =
		// CourseSettingUtil.figureCurrentWeek(courseSettings);
		String currentWeek=courseSettings.getString("currentWeek", null);

		// ��ȡ������
		String maxCourseNum = courseSettings.getString("maxCourseNum",null);
		
		if(currentTerms!=null){
			curTerm.setText(currentTerms);
		}
		
		if(currentWeek!=null){
			curWeek.setText(currentWeek);
		}
		if(maxCourseNum!=null){
			maxNum.setText(maxCourseNum);
		}

		
		
		// ���õ�ǰѧ��
		currentTerm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String terms[] = null;
				int year = Calendar.getInstance().get(Calendar.YEAR);
				// ��ǰѧ�ڵ�����
				int currentIndex = 0;
				if (terms == null) {
					terms = new String[16];
					for (int i = 0; i < 8; i++) {
						terms[i * 2] = String.valueOf(year + i - 4) + "-"
								+ String.valueOf(year + i - 3) + "-1";
						terms[i * 2 + 1] = String.valueOf(year + i - 4) + "-"
								+ String.valueOf(year + i - 3) + "-2";
					}
				}
				final OneWheel wheel = new OneWheel(SettingActivity.this,
						R.style.MyDialog, terms, currentIndex);

				wheel.show();
				wheel.setTitle("��ѡ��ǰѧ��");
				final String array[]=terms;
				// ��Ƽ�����
				wheel.setPositiveButton("ȷ��", new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Editor editor=courseSettings.edit();
						editor.putString("currentTerm",array[wheel.getItemID(0)]);
						editor.commit();
						curTerm.setText(array[wheel.getItemID(0)]);
						wheel.dismiss();
					}
				});
			}
		});

		// ���õ�ǰ����

		settingWeek.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int currentIndex = 0;
				String[] terms = new String[25];
				for (int i = 0; i < 25; i++) {
					terms[i] = i + 1 + "";
				}
				final OneWheel wheel = new OneWheel(SettingActivity.this,
						R.style.MyDialog, terms, currentIndex);
				wheel.show();
				wheel.setTitle("��ѡ��ǰ����");
				final String array[]=terms;
				wheel.setPositiveButton("ȷ��", new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Editor editor=courseSettings.edit();
						editor.putString("currentWeek",array[wheel.getItemID(0)]);
						editor.commit();
						curWeek.setText(array[wheel.getItemID(0)]);
						wheel.dismiss();
					}
				});

			}
		});

		// ����������
		settingmaxcount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int currentIndex = 0;
				String[] terms = new String[10];
				for (int i = 0; i < 10; i++) {
					terms[i] = i + 5 + "";
				}
				final OneWheel wheel = new OneWheel(SettingActivity.this,
						R.style.MyDialog, terms, currentIndex);
				wheel.show();
				wheel.setTitle("������������");
				final String array[]=terms;
				wheel.setPositiveButton("ȷ��", new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Editor editor=courseSettings.edit();
						editor.putString("maxCourseNum",array[wheel.getItemID(0)]);
						editor.commit();
						maxNum.setText(array[wheel.getItemID(0)]);
						wheel.dismiss();
					}
				});
			}
		});

		// �������ذ�ť
		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent();
				// intent.setClass(SearchCourse.this, MainActivity.class);
				// startActivity(intent);
				SettingActivity.this.finish();

			}
		});
	}

}
