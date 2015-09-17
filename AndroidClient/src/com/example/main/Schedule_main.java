package com.example.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.example.adatper.DayNowAdapter;
import com.example.coursedetail.CourseDetail;
import com.example.coursedetail.EditCourse;
import com.example.data.CourseInfo;
import com.example.db.CourseInfoDB;
import com.example.schedule.R;
import com.example.util.CheckedWifi;
import com.example.util.GetShape;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Schedule_main extends Activity {
	private int mMonth = 0;
	private TextView textMonth;
	GridView gv;
	GridView gv1;
	ImageView imageView;
	
	
	//���γ���
	int maxNum=0;
	
	Calendar c = Calendar.getInstance();
	RelativeLayout course_table_layout;
	RelativeLayout course_top;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_table);
		String[] mWeek = { "��һ", "�ܶ�", "����", "����", "����", "����", "����" };
		mMonth = c.get(Calendar.MONTH);// ��ȡ��ǰ�·�
		textMonth = (TextView) this.findViewById(R.id.week_course_txv_month);
		textMonth.setText(mMonth + 1 + "��");
		imageView = (ImageView) findViewById(R.id.title_imgv_left_icon);
		// ����Ļ�Ŀ��

		// ������õ���Ϣ
		final SharedPreferences courseSettings = getSharedPreferences(
				"course_setting", Activity.MODE_PRIVATE);
		// ��ȡ������
		String maxCourseNum = courseSettings.getString("maxCourseNum", "12");

		maxNum=Integer.parseInt(maxCourseNum);

		// �õ���ǰ�ܼ�
		int id = c.get(Calendar.DAY_OF_WEEK) - 2;
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			id = 6;
		}

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float width = dm.widthPixels;
		int height = dm.heightPixels;
		// ƽ�����
		float aveWidth = (width - 30) / 7;
		int aveHeight = (height - 30) / 10;

		// ������ʾ������
		gv = (GridView) findViewById(R.id.week_course_gv_dayOfMonth);
		gv1 = (GridView) findViewById(R.id.week_course_gv_week);
		DayNowAdapter adapter = new DayNowAdapter(Schedule_main.this,
				getDate(), R.layout.item_gridview, R.id.week_day, id,
				(int) aveWidth);
		gv.setAdapter(adapter);
		DayNowAdapter adapter1 = new DayNowAdapter(Schedule_main.this, mWeek,
				R.layout.item_gridview, R.id.week_day, id, (int) aveWidth);
		gv1.setAdapter(adapter1);

		course_table_layout = (RelativeLayout) this
				.findViewById(R.id.week_course_llyt_course);
		course_top = (RelativeLayout) this.findViewById(R.id.course_top);

		initBody(width, aveWidth, aveHeight,maxNum);

		// �������ذ�ť
		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Schedule_main.this.finish();

			}
		});

		List<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
		// �����ݿ���ȡ�����ݣ���ʾ���γ̱��������
		CourseInfoDB courseDB = new CourseInfoDB(this);
		courseInfos = courseDB.queryAll();
		courseDB.close();
		loadCourse(courseInfos, aveWidth, aveHeight);

	}

	// ���ɿγ̱������
	@SuppressWarnings("deprecation")
	protected void initBody(final float width, float aveWidth, int aveHeight,int maxCourseNum) {
		for (int i = 1; i <= maxCourseNum; i++) {
			for (int j = 1; j <= 8; j++) {
				final String a = j - 1 + "";
				final String b = i + "";
				TextView tx = new TextView(Schedule_main.this);
				if (j > 1) {
					tx.setClickable(true);
					tx.setFocusable(false);
					tx.setFocusableInTouchMode(true);
					tx.setSelected(false);
					tx.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							if (!CheckedWifi
									.CheckNetworkState(Schedule_main.this)) {
								Intent intent = new Intent();
								intent.setClass(Schedule_main.this,
										EditCourse.class);
								startActivity(intent);
							} else {
								Intent intent = new Intent();
								intent.setClass(Schedule_main.this,
										SearchCourse.class);
								Bundle bundle = new Bundle();
								bundle.putString("key", a + b);
								intent.putExtras(bundle);
								startActivity(intent);
							}
						}
					});
				} else {
					tx.setClickable(false);
					tx.setFocusable(false);
					tx.setFocusableInTouchMode(true);
					tx.setSelected(false);
				}
				tx.setId((i - 1) * 8 + j);
				// �������һ�У���ʹ��course_text_view_bg���������һ��û���ұ߿�
				if (j < 2)
					tx.setBackgroundDrawable(Schedule_main.this.getResources()
							.getDrawable(R.drawable.course_table_last_colum));
				else
					tx.setBackgroundDrawable(Schedule_main.this.getResources()
							.getDrawable(R.drawable.course_text_view_bg));
				// ��Բ��ֲ���
				RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
						(int) aveWidth - 1, (int) aveHeight);
				// ���ֶ��뷽ʽ
				tx.setGravity(Gravity.CENTER);
				// ������ʽ
				tx.setTextColor(Color.parseColor("#ff2e94da"));
				tx.setTextAppearance(this, R.style.courseTableText);
				// (R.drawable.add_course_select);

				// ����ǵ�һ�У���Ҫ���ÿε���ţ�1 �� 12��
				if (j == 1) {
					tx.setText(String.valueOf(i));
					rp.width = 44;
					// �������ǵ����λ��
					if (i == 1)
						rp.addRule(RelativeLayout.BELOW, course_top.getId());
					else
						rp.addRule(RelativeLayout.BELOW, (i - 1) * 8);
				} else {
					rp.addRule(RelativeLayout.RIGHT_OF, (i - 1) * 8 + j - 1);
					rp.addRule(RelativeLayout.ALIGN_TOP, (i - 1) * 8 + j - 1);
					tx.setText("");
				}
				tx.setLayoutParams(rp);
				course_table_layout.addView(tx);
			}
		}
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		this.finish();
		Intent intent = new Intent();
		intent.setClass(this, Schedule_main.class);
		startActivity(intent);

	}

	// ���ؿγ�
	protected void loadCourse(List<CourseInfo> courseInfos, float aveWidth,
			int aveHeight) {
		// ����γ̴洢Ϊ�գ��Ͳ���ʾ�γ�
		if (courseInfos == null) {
			return;
		}
		// �½�һ����ֵ�ԣ������ܺ�������ֵ
		HashMap<String, Object> item = new HashMap<String, Object>();
		for (CourseInfo courseInfo : courseInfos) {

			final CourseInfo courseinfo = courseInfo;

			// ��ȡ��ֵ����Ϣ��Textview�����γߴ�
			item = GetShape.ItemShape(courseInfo, aveWidth, aveHeight);
			TextView courseItem = new TextView(this);
			// ���ı�������ʾ�γ����ƺ��Ͽεص�
			courseItem.setText(courseInfo.getCourseName() + "@"
					+ courseInfo.getCoursePlace());
			// ����Textview�����
			RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
					(int) aveWidth - 2, (Integer) item.get("length") - 1);

			rlp.setMargins(44 + (Integer) item.get("column"),
					(Integer) item.get("row"), 0, 0);
			courseItem.setGravity(Gravity.CENTER);
			courseItem.setTextSize(12);
			courseItem.setLayoutParams(rlp);
			courseItem.setTextColor(Color.WHITE);
			courseItem.setBackgroundResource(R.drawable.course_info_green);

			// ΪTextview����һ��������

			courseItem.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					Intent intent1 = new Intent();
					intent1.setClass(Schedule_main.this, CourseDetail.class);
					intent1.putExtra("courseInfo", courseinfo);
					startActivity(intent1);

					// schedule_main.this.finish();
				}
			});

			course_table_layout.addView(courseItem);
		}
	}

	// ���ñ�ǩ��ʾ������ ����ǰ���������ʱ����ʾ��������
	@SuppressLint("SimpleDateFormat")
	public String[] getDate() {
		String[] today = new String[7];
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			c.add(Calendar.DAY_OF_MONTH, -7);
		}
		if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);

		for (int i = 1; i <= 7; i++) {
			c.add(Calendar.DAY_OF_MONTH, 1);
			// System.out.println(sdf.format(c.getTime()));
			today[i - 1] = sdf.format(c.getTime()).toString();
		}
		return today;
	}
}
