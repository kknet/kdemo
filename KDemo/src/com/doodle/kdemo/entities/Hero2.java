package com.doodle.kdemo.entities;

import org.omg.CORBA.portable.IDLEntity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
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
	
	public static final int ANIM_IDLE_R = 1;
	public static final int ANIM_IDLE_L = 2;
	
	
	private Animation idleAnim_r;
	private Animation runAnim_r;
	private Animation atk1Anim_r;
	private Animation atk2Anim_r;
	private Animation atk3Anim_r;
	private Animation atk4Anim_r;
	private Animation atk5Anim_r;
	private Animation atk6Anim_r;
	private Animation skill_1_Anim_r;
	private Animation skill_2_Anim_r;
	private Animation skill_3_Anim_r;
	private Animation skill_4_Anim_r;
	private Animation hitAnim_r;
	private Animation standUpAnim_r;
	
	private Animation idleAnim_l;
	private Animation runAnim_l;
	private Animation atk1Anim_l;
	private Animation atk2Anim_l;
	private Animation atk3Anim_l;
	private Animation atk4Anim_l;
	private Animation atk5Anim_l;
	private Animation atk6Anim_l;
	private Animation skill_1_Anim_l;
	private Animation skill_2_Anim_l;
	private Animation skill_3_Anim_l;
	private Animation skill_4_Anim_l;
	private Animation hitAnim_l;
	private Animation standUpAnim_l;
	
	
	private TextureRegion[] idleAnimTex_r;
	private TextureRegion[] runAnimTex_r;
	private TextureRegion[] atk1AnimTex_r;
	private TextureRegion[] atk2AnimTex_r;
	private TextureRegion[] atk3AnimTex_r;
	private TextureRegion[] atk4AnimTex_r;
	private TextureRegion[] atk5AnimTex_r;
	private TextureRegion[] atk6AnimTex_r;
	private TextureRegion[] skill_1_AnimTex_r;
	private TextureRegion[] skill_2_AnimTex_r;
	private TextureRegion[] skill_3_AnimTex_r;
	private TextureRegion[] skill_4_AnimTex_r;
	private TextureRegion[] hitAnimTex_r;
	private TextureRegion[] standUpAnimTex_r;
	
	private TextureRegion[] idleAnimTex_l;
	private TextureRegion[] runAnimTex_l;
	private TextureRegion[] atk1AnimTex_l;
	private TextureRegion[] atk2AnimTex_l;
	private TextureRegion[] atk3AnimTex_l;
	private TextureRegion[] atk4AnimTex_l;
	private TextureRegion[] atk5AnimTex_l;
	private TextureRegion[] atk6AnimTex_l;
	private TextureRegion[] skill_1_AnimTex_l;
	private TextureRegion[] skill_2_AnimTex_l;
	private TextureRegion[] skill_3_AnimTex_l;
	private TextureRegion[] skill_4_AnimTex_l;
	private TextureRegion[] hitAnimTex_l;
	private TextureRegion[] standUpAnimTex_l;
	
	private Animation currentAnimation = null;
	private float runTime = 0;
	private boolean isLooping = false;
	
	public Hero2(GameScene scene){
		this.setTag(SV.TAG_HERO);
		this.setPosition(50, 40);
		
		m_gameScene = scene;		
		mainCamera = scene.cam;
		
		this.speed_x = 300;
		this.speed_y = 200;	
		
		TextureAtlas atlas = RM.instance().getTexAtls("game/role/role.atlas");
		Array<Sprite> texRegions = atlas.createSprites();
		Array<Sprite> texRegionsMirror = atlas.createSprites();
		
		float frame_time = 0.25f;
		//IDLE¶¯»­
		int idle_frames = 31;
		idleAnimTex_r = new TextureRegion[idle_frames];
		idleAnimTex_l = new TextureRegion[idle_frames];
		for(int i = 0; i < idle_frames; i++){
			idleAnimTex_r[i] = texRegions.get(i);
			idleAnimTex_l[i] = texRegionsMirror.get(i);
			idleAnimTex_l[i].flip(true, false);
		}
		idleAnim_r = new Animation(frame_time, idleAnimTex_r);
		idleAnim_l = new Animation(frame_time, idleAnimTex_l);
		
		
		//±¼ÅÜ¶¯»­
		int run_frames = 24;
		runAnimTex_r = new TextureRegion[run_frames];
		runAnimTex_l = new TextureRegion[run_frames];
		for(int i = 0; i < run_frames; i++){
			runAnimTex_r[i] = texRegions.get(idle_frames + i);
			runAnimTex_l[i] = texRegions.get(idle_frames + i);
			runAnimTex_l[i].flip(true, false);
		}
		runAnim_r = new Animation(frame_time, runAnimTex_r);
		runAnim_l = new Animation(frame_time, runAnimTex_l);
		
		//¹¥»÷1¶¯»­
		int atk1_frames = 8;
		atk1AnimTex_r = new TextureRegion[atk1_frames];
		atk1AnimTex_l = new TextureRegion[atk1_frames];
		for(int i = 0; i < run_frames; i++){
			atk1AnimTex_r[i] = texRegions.get(idle_frames+ run_frames + i);
			atk1AnimTex_l[i] = texRegions.get(idle_frames+ run_frames + i);
			atk1AnimTex_l[i].flip(true, false);
		}
		atk1Anim_r = new Animation(frame_time, atk1AnimTex_r);
		atk1Anim_l = new Animation(frame_time, atk1AnimTex_l);
		
		
		int atk2_frames = 14;
		atk2AnimTex_r = new TextureRegion[atk2_frames];
		atk2AnimTex_l = new TextureRegion[atk2_frames];
		for(int i = 0; i < atk2_frames; i++){
			atk2AnimTex_r[i] = texRegions.get(idle_frames + run_frames + atk1_frames + i);
			atk2AnimTex_l[i] = texRegions.get(idle_frames + run_frames + atk1_frames + i);
			atk2AnimTex_l[i].flip(true, false);
		}
		atk2Anim_r = new Animation(frame_time, atk2AnimTex_r);
		atk2Anim_l = new Animation(frame_time, atk2AnimTex_l);
		
		
		int atk3_frames = 21;
		atk3AnimTex_r = new TextureRegion[atk3_frames];
		atk3AnimTex_l = new TextureRegion[atk3_frames];
		for(int i = 0; i < atk3_frames; i++){
			atk3AnimTex_r[i] = texRegions.get(idle_frames + run_frames + atk1_frames  + atk2_frames+ i);
			atk3AnimTex_l[i] = texRegions.get(idle_frames + run_frames + atk1_frames  + atk2_frames+ i);
			atk3AnimTex_l[i].flip(true, false);
		}
		atk3Anim_r = new Animation(frame_time, atk3AnimTex_r);
		atk3Anim_l = new Animation(frame_time, atk3AnimTex_l);
		
		int atk4_frames = 15;
		atk4AnimTex_r = new TextureRegion[atk4_frames];
		atk4AnimTex_l = new TextureRegion[atk4_frames];
		for(int i = 0; i < atk4_frames; i++){
			atk4AnimTex_r[i] = texRegions.get(idle_frames + run_frames + atk1_frames  + atk2_frames + atk3_frames+ i);
			atk4AnimTex_l[i] = texRegions.get(idle_frames + run_frames + atk1_frames  + atk2_frames + atk3_frames+ i);
			atk4AnimTex_l[i].flip(true, false);
		}
		atk4Anim_r = new Animation(frame_time, atk4AnimTex_r);
		atk4Anim_l = new Animation(frame_time, atk4AnimTex_l);
		
		
		int atk5_frames = 29;
		atk5AnimTex_r = new TextureRegion[atk5_frames];
		atk5AnimTex_l = new TextureRegion[atk5_frames];
		for(int i = 0; i < atk5_frames; i++){
			atk5AnimTex_r[i] = texRegions.get(idle_frames + run_frames + atk1_frames  + atk2_frames + atk3_frames+ atk4_frames+ i);
			atk5AnimTex_l[i] = texRegions.get(idle_frames + run_frames + atk1_frames  + atk2_frames + atk3_frames+ atk4_frames+ i);
			atk5AnimTex_l[i].flip(true, false);
		}
		atk5Anim_r = new Animation(frame_time, atk5AnimTex_r);
		atk5Anim_l = new Animation(frame_time, atk5AnimTex_l);
		
		int atk6_frames = 25;
		atk6AnimTex_r = new TextureRegion[atk6_frames];
		atk6AnimTex_l = new TextureRegion[atk6_frames];
		for(int i = 0; i < atk6_frames; i++){
			atk6AnimTex_r[i] = texRegions.get(idle_frames + run_frames + atk1_frames  + atk2_frames + atk3_frames+ atk4_frames + atk5_frames+ i);
			atk6AnimTex_l[i] = texRegions.get(idle_frames + run_frames + atk1_frames  + atk2_frames + atk3_frames+ atk4_frames + atk5_frames+ i);
			atk6AnimTex_l[i].flip(true, false);
		}
		atk6Anim_r = new Animation(frame_time, atk6AnimTex_r);
		atk6Anim_l = new Animation(frame_time, atk6AnimTex_l);
		
		int normal_atk = 165;
		int skill_1_frames = 41;
		skill_1_AnimTex_r = new TextureRegion[skill_1_frames];
		skill_1_AnimTex_l = new TextureRegion[skill_1_frames];
		for(int i = 0; i < skill_1_frames; i++){
			skill_1_AnimTex_r[i] = texRegions.get(normal_atk+ i);
			skill_1_AnimTex_l[i] = texRegions.get(normal_atk+ i);
			skill_1_AnimTex_l[i].flip(true, false);
		}
		skill_1_Anim_r = new Animation(frame_time, skill_1_AnimTex_r);
		skill_1_Anim_l = new Animation(frame_time, skill_1_AnimTex_l);
		
		int skill_2_frames = 147;
		skill_2_AnimTex_r = new TextureRegion[skill_2_frames];
		skill_2_AnimTex_l = new TextureRegion[skill_2_frames];
		for(int i = 0; i < skill_2_frames; i++){
			skill_2_AnimTex_r[i] = texRegions.get(normal_atk + skill_1_frames + i);
			skill_2_AnimTex_l[i] = texRegions.get(normal_atk + skill_1_frames + i);
			skill_2_AnimTex_l[i].flip(true, false);
		}
		skill_2_Anim_r = new Animation(frame_time, skill_2_AnimTex_r);
		skill_2_Anim_l = new Animation(frame_time, skill_2_AnimTex_l);
		
		int skill_3_frames = 66;
		skill_3_AnimTex_r = new TextureRegion[skill_3_frames];
		skill_3_AnimTex_l = new TextureRegion[skill_3_frames];
		for(int i = 0; i < skill_3_frames; i++){
			skill_3_AnimTex_r[i] = texRegions.get(normal_atk+ skill_1_frames + skill_2_frames + i);
			skill_3_AnimTex_l[i] = texRegions.get(normal_atk+ skill_1_frames + skill_2_frames + i);
			skill_3_AnimTex_l[i].flip(true, false);
		}
		skill_3_Anim_r = new Animation(frame_time, skill_3_AnimTex_r);
		skill_3_Anim_l = new Animation(frame_time, skill_3_AnimTex_l);
		
		
		int skill_4_frames = 54;
		skill_4_AnimTex_r = new TextureRegion[skill_4_frames];
		skill_4_AnimTex_l = new TextureRegion[skill_4_frames];
		for(int i = 0; i < skill_4_frames; i++){
			skill_4_AnimTex_r[i] = texRegions.get(normal_atk+ skill_1_frames + skill_2_frames  + skill_3_frames+ i);
			skill_4_AnimTex_l[i] = texRegions.get(normal_atk+ skill_1_frames + skill_2_frames  + skill_3_frames+ i);
			skill_4_AnimTex_l[i].flip(true, false);
		}
		skill_4_Anim_r = new Animation(frame_time, skill_4_AnimTex_r);
		skill_4_Anim_l = new Animation(frame_time, skill_4_AnimTex_l);
		
		int front_frames = 473;
		int hit_frames = 16;
		hitAnimTex_r = new TextureRegion[hit_frames];
		hitAnimTex_l = new TextureRegion[hit_frames];
		for(int i = 0; i < hit_frames; i++){
			hitAnimTex_r[i] = texRegions.get(front_frames + i);
			hitAnimTex_l[i] = texRegions.get(front_frames + i);
			hitAnimTex_l[i].flip(true, false);
		}
		hitAnim_r = new Animation(frame_time, hitAnimTex_r);
		hitAnim_l = new Animation(frame_time, hitAnimTex_l);
		
		
		int stand_up_frames = 36;
		standUpAnimTex_r = new TextureRegion[stand_up_frames];
		standUpAnimTex_l = new TextureRegion[stand_up_frames];
		for(int i = 0; i < stand_up_frames; i++){
			standUpAnimTex_r[i] = texRegions.get(front_frames + hit_frames + i);
			standUpAnimTex_l[i] = texRegions.get(front_frames + hit_frames + i);
			standUpAnimTex_l[i].flip(true, false);
		}
		standUpAnim_r = new Animation(frame_time, standUpAnimTex_r);
		standUpAnim_l = new Animation(frame_time, standUpAnimTex_l);
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
		
		if(currentAnimation != null){
			TextureRegion tex = currentAnimation.getKeyFrame(runTime, isLooping);
			batch.draw(tex, this.getX() - mainCamera.position.x	, this.getY() - mainCamera.position.y);
		}
		
	}

	
	public void playAnimation(String name){
		this.runTime = 0;
 //============================================================
	}
	

	@Override
	public boolean moveLeft() {
		// TODO Auto-generated method stub
		boolean rs = super.moveLeft();
		if(rs == true){
		}
		return rs;
	}


	@Override
	public boolean moveRight() {
		// TODO Auto-generated method stub
		boolean rs = super.moveRight();
		if(rs == true){
		}
		return rs;
	}


	@Override
	public boolean moveUp() {
		// TODO Auto-generated method stub
		boolean rs = super.moveUp();
		if(rs == true){

		}
		return rs;
	}


	@Override
	public boolean moveDown() {
		// TODO Auto-generated method stub
		boolean rs = super.moveDown();
		if(rs == true){
		}
		return rs;
	}


	@Override
	public boolean stopLeft() {
		// TODO Auto-generated method stub
		boolean rs = super.stopLeft();
		if(rs == true && this.y_dir == STOP){
		}
		return rs;
	}


	@Override
	public boolean stopRight() {
		// TODO Auto-generated method stub
		boolean rs = super.stopRight();
		if(rs == true && this.y_dir == STOP){			
		}
		return rs;
	}


	@Override
	public boolean stopUp() {
		// TODO Auto-generated method stub
		boolean rs = super.stopUp();
		if(rs == true && this.x_dir == STOP){
		}
		return rs;
	}


	@Override
	public boolean stopDown() {
		// TODO Auto-generated method stub
		boolean rs = super.stopDown();
		if(rs == true && this.x_dir == STOP){

		}
		return rs;
	}


	@Override
	public boolean jump() {
		// TODO Auto-generated method stub
		boolean rs = super.jump();
		if(rs == true){
		}
		return rs;
		
	}
	
	
	
	
	

}
