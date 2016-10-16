package com.doodle.kdemo.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

//StaticValue类，保存一些定值
public class SV {
	
	//分辨率信息
	public static final float WINDOW_WIDTH = 800;			//窗口宽度
	public static final float WINDOW_HEIGHT = 480;			//窗口高度
	public static final boolean WINDOW_SCALE_MODE = false;	//缩放模式
	
	
	//场景ID
	public static final int SCENE_LOADING = 0;				//游戏加载场景
	public static final int SCENE_MENU = 1;					//游戏主菜单
	public static final int SCENE_GAME = 2;					//游戏场景
	public static final int SCENE_SELECT_1 = 3;				//游戏选关界面
	public static final int SCENE_RESULT = 4;				//游戏结算界面
	
	
	//游戏信息
	public static final String GameName = "KDemo";			//游戏名字
	
	
	//DEBUG信息
	public static boolean DEBUG = true;						//是否是DEBUG模式
	
	//日志输出等级
	public static final int LOG_NONE = Application.LOG_NONE;
	public static final int LOG_DEBUG = Application.LOG_DEBUG;
	public static final int LOG_INFO = Application.LOG_INFO;
	public static final int LOG_ERROR = Application.LOG_ERROR;
	
	
	//常用的BaseActor的TAG，int类型
	public static final int TAG_NONE = 0;
	public static final int TAG_HERO = 1;
	
	
	//物理相关
	public static final float PHYSICS_TIME_SPAN = 1/50f;	//物理帧时间
	public static final int VEL_ITRT = 6;					//速度计算迭代次数
	public static final int POS_ITRT = 4;					//位置计算迭代次数
	public static final float SPEED_OF_DECAY = 200; 		// 单位：像素/每平方秒

}
