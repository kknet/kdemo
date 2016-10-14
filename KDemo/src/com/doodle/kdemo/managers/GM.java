package com.doodle.kdemo.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.doodle.kdemo.common.SceneData;
import com.doodle.kdemo.scenes.BaseScene;
import com.doodle.kdemo.scenes.GameScene;
import com.doodle.kdemo.scenes.LoadingScene;
import com.doodle.kdemo.scenes.MenuScene;
import com.doodle.kdemo.scenes.ResultScene;
import com.doodle.kdemo.scenes.SelectLevelScene;
import com.doodle.kdemo.utils.SV;


//��Ϸ������GameManager
public class GM {

	private static GM s_instance;
	
	private GM(){
		
	}
	
	public static GM instance(){
		if( s_instance == null){
			s_instance = new GM();
			s_instance.init();
		}
		return s_instance;
	}
	
	
	//�ͷŵ�������
	public static void relInstance(){
		if( s_instance != null){
			s_instance.destroy();
			s_instance = null;
		}
	}
	
	//��ʼ������
	private void init(){
		
	}
	
	//���ٺ���
	private void destroy(){
		if(g_game != null){
			g_game = null;
		}
	}
	
	//====================================��Ϸ���===========================================
	
	//Game����
	private Game g_game;
	
	//ע��Game����
	public void registerGame(Game game){
		this.g_game = game;
	}	
	
	
	private SceneData lastSceneData;		//�����ϴεĳ�����������
	private SceneData currentSceneData;		//���汾�εĳ�����������
	
	//���ݳ���id�л�����
	public void changeScene(SceneData sceneData){
		if( sceneData == null)
			return;
		
		if( this.g_game == null)
			return;
		
		//��������
		BaseScene nextScene = null;
		switch(sceneData.sceneId){
		case SV.SCENE_LOADING:
			nextScene = new LoadingScene();
			break;
		case SV.SCENE_MENU:
			nextScene = new MenuScene();
			break;
		case SV.SCENE_SELECT_1:
			nextScene = new SelectLevelScene();
			break;
		case SV.SCENE_GAME:
			nextScene = new GameScene();
			break;
		case SV.SCENE_RESULT:
			nextScene = new ResultScene();
			break;
		}
		
		//�л�����
		if(nextScene != null){
			Screen curScreen = g_game.getScreen();
			if(curScreen != null){
				curScreen.dispose();
			}
			g_game.setScreen(nextScene);
			lastSceneData = currentSceneData;
			currentSceneData = sceneData;
		}
		
	}
	
	//���ص���һ������
	public void backToLastScene(){
		changeScene(lastSceneData);
	}
	
	
	//���ؼ�����,·�ɵ���ǰ������
	public void backKeyDown(){
		if(g_game != null){
			BaseScene baseScene = (BaseScene)g_game.getScreen();
			baseScene.onBack();
		}
		
	}
	
	//====================================================================================
}
