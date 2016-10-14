package com.doodle.kdemo.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

//StaticValue�࣬����һЩ��ֵ
public class SV {
	
	//�ֱ�����Ϣ
	public static final float WINDOW_WIDTH = 800;			//���ڿ���
	public static final float WINDOW_HEIGHT = 480;			//���ڸ߶�
	public static final boolean WINDOW_SCALE_MODE = false;	//����ģʽ
	
	
	//����ID
	public static final int SCENE_LOADING = 0;				//��Ϸ���س���
	public static final int SCENE_MENU = 1;					//��Ϸ���˵�
	public static final int SCENE_GAME = 2;					//��Ϸ����
	public static final int SCENE_SELECT_1 = 3;				//��Ϸѡ�ؽ���
	public static final int SCENE_RESULT = 4;				//��Ϸ�������
	
	
	//��Ϸ��Ϣ
	public static final String GameName = "KDemo";			//��Ϸ����
	
	
	//DEBUG��Ϣ
	public static boolean DEBUG = true;						//�Ƿ���DEBUGģʽ
	
	//��־����ȼ�
	public static final int LOG_NONE = Application.LOG_NONE;
	public static final int LOG_DEBUG = Application.LOG_DEBUG;
	public static final int LOG_INFO = Application.LOG_INFO;
	public static final int LOG_ERROR = Application.LOG_ERROR;

}