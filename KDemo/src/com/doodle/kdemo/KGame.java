package com.doodle.kdemo;

import com.badlogic.gdx.Game;
import com.doodle.kdemo.common.SceneData;
import com.doodle.kdemo.managers.GM;
import com.doodle.kdemo.managers.LM;
import com.doodle.kdemo.utils.SV;

public class KGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		GM.instance().registerGame(this);
		LM.instance().setLogLevel(SV.LOG_DEBUG);
		
		SceneData sceneData = new SceneData();
		sceneData.sceneId = SV.SCENE_GAME;
		GM.instance().changeScene(sceneData);
	}

}
