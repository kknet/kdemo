package com.doodle.kdemo.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.doodle.kdemo.common.BaseActor;
import com.doodle.kdemo.utils.SV;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;

public class Hero extends BaseActor {
	
	private Skeleton heroSkeleton;
	private AnimationState state;
	private Camera camera;
	
	private PolygonSpriteBatch polygonBatch = new PolygonSpriteBatch();
	private SkeletonRenderer renderer;
	
	
	public Hero(){
		this.setTag(SV.TAG_HERO);
		
		this.camera = new OrthographicCamera(SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT);
		this.renderer = new SkeletonRenderer();
		
		TextureAtlas texAtls = new TextureAtlas(Gdx.files.internal("animations/spineboy.atlas"));
		SkeletonJson sklJson = new SkeletonJson(texAtls);
		sklJson.setScale(0.6f);
		SkeletonData sklData = sklJson.readSkeletonData(Gdx.files.internal("animations/spineboy.json"));
		heroSkeleton = new Skeleton(sklData);
		heroSkeleton.setPosition(this.getX(), this.getY());
		
		AnimationStateData stateData = new AnimationStateData(sklData);
		stateData.setDefaultMix(0.2f);
		state = new AnimationState(stateData);
		
		this.speed_x = 200;
		this.speed_y = 50;
	}


	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	
		heroSkeleton.setPosition(this.getX(), this.getY());
		state.update(delta);	
		state.apply(heroSkeleton);
		heroSkeleton.updateWorldTransform();
	}


	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		polygonBatch.begin();
		polygonBatch.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.draw(polygonBatch, heroSkeleton);
		polygonBatch.end();
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
