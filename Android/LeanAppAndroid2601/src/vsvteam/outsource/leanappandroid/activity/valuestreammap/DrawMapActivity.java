package vsvteam.outsource.leanappandroid.activity.valuestreammap;

import vsvteam.outsource.leanappandroid.R;
import vsvteam.outsource.leanappandroid.actionbar.ActionChangeActivity;
import vsvteam.outsource.leanappandroid.actionbar.ActionExportActivity;
import vsvteam.outsource.leanappandroid.actionbar.ActionSettingActivity;
import vsvteam.outsource.leanappandroid.actionbar.ActionVersionActivity;
import vsvteam.outsource.leanappandroid.activity.home.VSVTeamBaseActivity;
import vsvteam.outsource.leanappandroid.database.LeanAppAndroidSharePreference;
import vsvteam.outsource.leanappandroid.database.TProcessDataBase;
import vsvteam.outsource.leanappandroid.database.TProcessDataBaseHandler;
import vsvteam.outsource.leanappandroid.mapobjects.StreamMapSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DrawMapActivity extends VSVTeamBaseActivity implements
		OnClickListener {

	private ImageView btnSetting;
	private ImageView btnExport;
	private ImageView btnVersion;
	private ImageView btnChangedProject;
	private RelativeLayout frDrawStreamMap;
	private int width;
	private int height;
	public TProcessDataBase mTProcessDataBase=null;
	public TProcessDataBaseHandler mProcessDataBaseHandler;

	public static final int NUM_OBJECTS = 6;
	private StreamMapSurfaceView mSurfaceView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_draw_map);
		Display d = getWindowManager().getDefaultDisplay();
		getDataValueStream();
		width = d.getWidth();
		height = d.getHeight();
		intitalize();
		initDrawFrame();
	}

	public void getDataValueStream() {
		mProcessDataBaseHandler = new TProcessDataBaseHandler(this);
		LeanAppAndroidSharePreference mPreference = LeanAppAndroidSharePreference
				.getInstance(this);
		if (mPreference.getProcessIdActive() >= 0) {
			mTProcessDataBase = mProcessDataBaseHandler.getProcess(mPreference
					.getProcessIdActive());
		}
	}

	private void intitalize() {
		frDrawStreamMap = (RelativeLayout) findViewById(R.id.id_fr_draw_map);

		btnExport = (ImageView) findViewById(R.id.img_project_export);
		btnExport.setOnClickListener(this);
		//
		btnSetting = (ImageView) findViewById(R.id.img_project_setting);
		btnSetting.setOnClickListener(this);
		//
		btnVersion = (ImageView) findViewById(R.id.img_project_version);
		btnVersion.setOnClickListener(this);
		//
		btnChangedProject = (ImageView) findViewById(R.id.img_project_change_project);
		btnChangedProject.setOnClickListener(this);

	}

	public void initDrawFrame() {
		mSurfaceView = new StreamMapSurfaceView(this, frDrawStreamMap);
		frDrawStreamMap.addView(mSurfaceView);
		mSurfaceView.initMap();

	}

	@Override
	public void onClick(View view) {
		if (view == btnExport) {
			gotoActivityInGroup(DrawMapActivity.this,
					ActionExportActivity.class);
		} else if (view == btnSetting) {
			gotoActivityInGroup(DrawMapActivity.this,
					ActionSettingActivity.class);
		} else if (view == btnVersion) {
			gotoActivityInGroup(DrawMapActivity.this,
					ActionVersionActivity.class);
		} else if (view == btnChangedProject) {
			gotoActivityInGroup(DrawMapActivity.this,
					ActionChangeActivity.class);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.e("action down", "ok");
			mSurfaceView.onTouchDown(event);
			break;
		case MotionEvent.ACTION_UP:
			mSurfaceView.onTouchUp(event);
			Log.e("action up", "ok");
			break;
		case MotionEvent.ACTION_MOVE:
			mSurfaceView.onTouchMove(event);
			Log.e("action move", "ok");
			break;
		default:
			break;
		}
		return true;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}
}
