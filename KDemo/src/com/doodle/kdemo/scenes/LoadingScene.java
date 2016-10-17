package com.doodle.kdemo.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.doodle.kdemo.common.SceneData;
import com.doodle.kdemo.entities.Hero;
import com.doodle.kdemo.managers.GM;
import com.doodle.kdemo.managers.RM;
import com.doodle.kdemo.utils.SV;

public class LoadingScene extends BaseScene {
	
	private Image loading_bg;
	public LoadingScene(){
		TextureAtlas atlas = RM.instance().getTexAtls("loading/loading.atlas");
		Sprite tmp1 = atlas.createSprite("loading_bg");
		loading_bg = new Image(tmp1);
		uiStage.addActor(loading_bg);
		loading_bg.addListener(new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				SceneData sceneData = new SceneData();
				sceneData.sceneId = SV.SCENE_MENU;
				GM.instance().changeScene(sceneData);
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
	}
	
	
	
}
