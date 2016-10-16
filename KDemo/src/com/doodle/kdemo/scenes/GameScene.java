package com.doodle.kdemo.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.doodle.kdemo.common.BaseLevelMap;
import com.doodle.kdemo.entities.Hero;
import com.doodle.kdemo.entities.LevelMap2;
import com.doodle.kdemo.physics.Xml2Body;

public class GameScene extends BaseScene {

	public final float MAP_MAX_WINDTH = 2213f;

	private Hero hero;
	
		
	private Xml2Body physicsBodys = new Xml2Body("configs/level1_box2d.xml");
	
	public BaseLevelMap map;
	
	private InputAdapter input = new InputAdapter(){

		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			switch(keycode){
			case Keys.A:
				hero.moveLeft();
				break;
			case Keys.S:
				hero.moveDown();
				break;
			case Keys.W:
				hero.moveUp();
				break;
			case Keys.D:
				hero.moveRight();
				break;
			case Keys.J:
				hero.jump();
				break;
			}
			return true;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			switch(keycode){
			case Keys.A:
				hero.stopLeft();
				break;
			case Keys.S:
				hero.stopDown();
				break;
			case Keys.W:
				hero.stopUp();
				break;
			case Keys.D:
				hero.stopRight();
				break;
			case Keys.J:
				break;
			}
			return true;
		}
		
	};
	
	public GameScene(){
		
		map = new LevelMap2();
		paintGroup.addActor(map);
		
		hero = new Hero(this);
		paintGroup.addActor(hero);		
		
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		Gdx.input.setInputProcessor(input);
	}

	
	
	
}
