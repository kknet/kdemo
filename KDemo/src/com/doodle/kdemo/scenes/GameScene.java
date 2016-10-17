package com.doodle.kdemo.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.InputAdapter;
import com.doodle.kdemo.common.BaseMap;
import com.doodle.kdemo.common.GameSceneData;
import com.doodle.kdemo.entities.Hero;
import com.doodle.kdemo.entities.Map01;
import com.doodle.kdemo.entities.Map02;

public class GameScene extends BaseScene {

	private Hero hero;
	
	public BaseMap map;
	
	private GameSceneData gameSceneData;
	
	public GameScene(GameSceneData sceneData){
		
		switch(sceneData.levelId){
		case 1:
			map = new Map01();
			paintGroup.addActor(map);
			break;
		case 2:
			map = new Map02();
			paintGroup.addActor(map);
			break;
		}
		
		hero = new Hero(this);
		paintGroup.addActor(hero);		
		
	
		uiStage.addListener(new InputListener(){

			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				// TODO Auto-generated method stub
				switch(keycode){
				case Keys.W:
					hero.moveUp();
					break;
				case Keys.A:
					hero.moveLeft();
					break;
				case Keys.S:
					hero.moveDown();
					break;
				case Keys.D:
					hero.moveRight();
					break;
				case Keys.J:
					hero.jump();
					break;
				}
				return super.keyDown(event, keycode);
			}

			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				// TODO Auto-generated method stub
				switch(keycode){
				case Keys.W:
					hero.stopUp();
					break;
				case Keys.A:
					hero.stopLeft();
					break;
				case Keys.S:
					hero.stopDown();
					break;
				case Keys.D:
					hero.stopRight();
					break;
				}
				return super.keyUp(event, keycode);
			}
			
		});
		
	}	
	
}
