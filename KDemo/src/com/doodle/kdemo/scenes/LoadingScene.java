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
import com.doodle.kdemo.entities.Hero;
import com.doodle.kdemo.managers.RM;

public class LoadingScene extends BaseScene {
	
	private Image loadingBg;
	private Hero hero;
	
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
	
	public LoadingScene(){
		TextureAtlas atlas =  RM.instance().getTexAtls("loading/loading.atlas");
		Sprite tmp1 = atlas.createSprite("loading_bg");
		loadingBg = new Image(tmp1);
		
		paintGroup.addActor(loadingBg);
		hero = new Hero();
		paintGroup.addActor(hero);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		Gdx.input.setInputProcessor(input);
	}

	
	
	
}
