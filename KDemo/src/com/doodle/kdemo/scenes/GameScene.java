package com.doodle.kdemo.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.doodle.kdemo.entities.Hero;
import com.doodle.kdemo.managers.RM;

public class GameScene extends BaseScene {

	public final float MAP_MAX_WINDTH = 2213f;
	private Image bg1;
	private Image bg2;
	private Image bg3;
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
	
	public GameScene(){
		TextureAtlas atlas =  RM.instance().getTexAtls("game/game.atlas");
		Sprite tmp1 = atlas.createSprite("bg1");
		bg1 = new Image(tmp1);
		bg1.setPosition(0, 0);
		paintGroup.addActor(bg1);
		
		tmp1 = atlas.createSprite("bg2");
		bg2 = new Image(tmp1);
		bg2.setPosition(800, 0);
		paintGroup.addActor(bg2);
		
		tmp1 = atlas.createSprite("bg3");
		bg3 = new Image(tmp1);
		bg3.setPosition(1600, 0);
		paintGroup.addActor(bg3);
		
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
