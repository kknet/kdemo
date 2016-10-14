package com.doodle.kdemo;

import com.badlogic.gdx.Game;
import com.doodle.kdemo.common.SceneData;
import com.doodle.kdemo.managers.GM;
import com.doodle.kdemo.utils.SV;

public class KGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		GM.instance().registerGame(this);
		
		SceneData sceneData = new SceneData();
		sceneData.sceneId = SV.SCENE_LOADING;
		GM.instance().changeScene(sceneData);
	}

}
