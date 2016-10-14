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


//游戏管理类GameManager
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
	
	
	//释放单例对象
	public static void relInstance(){
		if( s_instance != null){
			s_instance.destroy();
			s_instance = null;
		}
	}
	
	//初始化函数
	private void init(){
		
	}
	
	//销毁函数
	private void destroy(){
		if(g_game != null){
			g_game = null;
		}
	}
	
	//====================================游戏相关===========================================
	
	//Game引用
	private Game g_game;
	
	//注册Game引用
	public void registerGame(Game game){
		this.g_game = game;
	}	
	
	
	private SceneData lastSceneData;		//保存上次的场景构建参数
	private SceneData currentSceneData;		//保存本次的场景构建参数
	
	//根据场景id切换场景
	public void changeScene(SceneData sceneData){
		if( sceneData == null)
			return;
		
		if( this.g_game == null)
			return;
		
		//构建场景
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
		
		//切换场景
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
	
	//返回到上一个场景
	public void backToLastScene(){
		changeScene(lastSceneData);
	}
	
	
	//返回键按下,路由到当前场景中
	public void backKeyDown(){
		if(g_game != null){
			BaseScene baseScene = (BaseScene)g_game.getScreen();
			baseScene.onBack();
		}
		
	}
	
	//====================================================================================
}
