package com.doodle.kdemo.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.doodle.kdemo.managers.TM;
import com.doodle.kdemo.utils.SV;

public abstract class BaseScene implements Screen {
	
	
	private SpriteBatch batch;
	private Stage stage;
	public  PerspectiveCamera cam;
	protected boolean isPause;
	
	protected boolean DEBUG = SV.DEBUG;
	protected int numDrawCalls = 0;
	protected BitmapFont font;
	protected Group paintGroup;
	protected Group debugGroup;

	public BaseScene(){
		
		batch = new SpriteBatch();
		stage = new Stage(SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT, SV.WINDOW_SCALE_MODE, batch);
		
		//设置透视相机
		cam = new PerspectiveCamera(60.0f, SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT);		
		cam.far = 1000;
		cam.position.x = 400;
		cam.position.y = 240;
		cam.position.z = (float) (240 / Math.tan(30.0f / 180.0f * Math.PI ));
		stage.setCamera(cam);
		
		
		isPause = false;
		
		
		
		paintGroup = new Group();
		stage.addActor(paintGroup);
		
		//调试信息
		if(DEBUG){
			font = new BitmapFont();			
			
			Group debugGroup = new Group(){

				@Override
				public void draw(SpriteBatch batch, float parentAlpha) {
					// TODO Auto-generated method stub
					super.draw(batch, parentAlpha);
					numDrawCalls = batch.renderCalls;
				}
				
			};
			stage.addActor(debugGroup);
		}
		
	}

	
	private float delayTime = 0;
	
	//除非必要，否则不用重写此方法
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		if(isPause)
			return;		
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		delayTime += delta;
		while(delayTime >= SV.PHYSICS_TIME_SPAN){
			delayTime -= SV.PHYSICS_TIME_SPAN;
			TM.instance().step(delta);	
			stage.act(delta);
		}
		
		stage.draw();
		
		//调试信息
		if(DEBUG){
			batch.begin();
			font.draw(batch, "DrawCalls : " + numDrawCalls, 20, 100);
			font.draw(batch, "NativeHeap: " + Gdx.app.getNativeHeap() / 1024f / 1024f + " Mb", 20, 70);
			font.draw(batch, "JavaHeap  : " + Gdx.app.getJavaHeap()/1024f/1024f + " Mb", 20, 40);
			batch.end();
			
			numDrawCalls = 0;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		if(stage != null){
			stage.dispose();
			stage = null;
		}
		
		if(batch != null){
			batch.dispose();
			batch = null;
		}

		if(font != null){
			font.dispose();
			font = null;
		}
	}
	
	
	//响应返回键消息
	public void onBack(){
		
	}

}
