package com.doodle.kdemo.entities;

import org.omg.CORBA.portable.IDLEntity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.doodle.kdemo.common.BaseActor;
import com.doodle.kdemo.managers.PWM;
import com.doodle.kdemo.managers.RM;
import com.doodle.kdemo.scenes.GameScene;
import com.doodle.kdemo.utils.SV;


public class Hero2 extends BaseActor {
	
	
	private boolean DEBUG = false;
	
	private GameScene m_gameScene;
	private Camera mainCamera;
	
	private TextureRegion[] idleAnim;
	private TextureRegion[] runAnim;
	private TextureRegion[] atk1Anim;
	private TextureRegion[] atk2Anim;
	private TextureRegion[] atk3Anim;
	
	public Hero2(GameScene scene){
		this.setTag(SV.TAG_HERO);
		this.setPosition(50, 40);
		
		m_gameScene = scene;		
		mainCamera = scene.cam;
		
		this.speed_x = 300;
		this.speed_y = 200;	
		
		TextureAtlas atlas = RM.instance().getTexAtls("game/role/role.atlas");
		Array<Sprite> texRegions = atlas.createSprites();
		
		int idle_frames = 31;
		idleAnim = new TextureRegion[idle_frames];
		for(int i = 0; i < idle_frames; i++){
			idleAnim[i] = texRegions.get(i);
		}
		
		int run_frames = 24;
		runAnim = new TextureRegion[run_frames];
		for(int i = 0; i < run_frames; i++){
			runAnim[i] = texRegions.get(idle_frames + i);
		}
		
		int atk1_frames = 13;
		atk1Anim = new TextureRegion[atk1_frames];
		for(int i = 0; i < run_frames; i++){
			atk1Anim[i] = texRegions.get(idle_frames+ run_frames + i);
		}
	}


	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		
		if(PWM.instance().pointIsInObstacle(this.getX(), this.getY())){
			if(!PWM.instance().pointIsInObstacle(this.getX(), lastPos.y)){
				this.setPosition(this.getX(), lastPos.y);
			}else if(!PWM.instance().pointIsInObstacle(lastPos.x, this.getY())){
				this.setPosition(lastPos.x, this.getY());
			}else{
				this.setPosition(lastPos.x, lastPos.y);
			}
			
			this.lastPos.x = this.getX();
			this.lastPos.y = this.getY();
			
		}				
		
		mainCameraFollowHero();
		
		
	}

	private void mainCameraFollowHero(){
		if(this.getX() > SV.WINDOW_WIDTH/2 && this.getX() < m_gameScene.map.MAP_MAX_WINDTH - SV.WINDOW_WIDTH/2){
			mainCamera.position.x = this.getX();
			
		}
	}
	

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		
	}


	@Override
	public boolean moveLeft() {
		// TODO Auto-generated method stub
		boolean rs = super.moveLeft();
		if(rs == true){
			this.heroSkeleton.setFlipX(true);
			this.state.setAnimation(0, "walk", true);
		}
		return rs;
	}


	@Override
	public boolean moveRight() {
		// TODO Auto-generated method stub
		boolean rs = super.moveRight();
		if(rs == true){
			this.heroSkeleton.setFlipX(false);
			this.state.setAnimation(0, "walk", true);
		}
		return rs;
	}


	@Override
	public boolean moveUp() {
		// TODO Auto-generated method stub
		boolean rs = super.moveUp();
		if(rs == true){
			this.state.setAnimation(0, "walk", true);
		}
		return rs;
	}


	@Override
	public boolean moveDown() {
		// TODO Auto-generated method stub
		boolean rs = super.moveDown();
		if(rs == true){
			this.state.setAnimation(0, "walk", true);
		}
		return rs;
	}


	@Override
	public boolean stopLeft() {
		// TODO Auto-generated method stub
		boolean rs = super.stopLeft();
		if(rs == true && this.y_dir == STOP){
			this.state.setAnimation(0, "idle", true);
		}
		return rs;
	}


	@Override
	public boolean stopRight() {
		// TODO Auto-generated method stub
		boolean rs = super.stopRight();
		if(rs == true && this.y_dir == STOP){			
			this.state.setAnimation(0, "idle", true);
		}
		return rs;
	}


	@Override
	public boolean stopUp() {
		// TODO Auto-generated method stub
		boolean rs = super.stopUp();
		if(rs == true && this.x_dir == STOP){
			this.state.setAnimation(0, "idle", true);
		}
		return rs;
	}


	@Override
	public boolean stopDown() {
		// TODO Auto-generated method stub
		boolean rs = super.stopDown();
		if(rs == true && this.x_dir == STOP){
			this.state.setAnimation(0, "idle", true);
		}
		return rs;
	}


	@Override
	public boolean jump() {
		// TODO Auto-generated method stub
		boolean rs = super.jump();
		if(rs == true){
			this.state.setAnimation(1, "jump", false);
		}
		return rs;
		
	}
	
	
	
	
	

}
