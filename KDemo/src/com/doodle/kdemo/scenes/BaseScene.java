package com.doodle.kdemo.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.doodle.kdemo.managers.PWM;
import com.doodle.kdemo.managers.TM;
import com.doodle.kdemo.utils.SV;

public abstract class BaseScene implements Screen {
	
	private SpriteBatch uiBatch;
	private SpriteBatch gameBatch;
	protected Stage gameStage;
	protected Stage uiStage;
	public  PerspectiveCamera cam;
	protected boolean isPause;
	
	protected boolean DEBUG = SV.DEBUG;
	protected int numDrawCalls = 0;
	protected BitmapFont font;
	protected Group paintGroup;
	protected Group debugGroup;
	
	
	public BaseScene(){
		
		gameBatch = new SpriteBatch();
		gameStage = new Stage(SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT, SV.WINDOW_SCALE_MODE, gameBatch);
		
		//设置透视相机
		cam = new PerspectiveCamera(60.0f, SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT);		
		cam.far = 1000;
		cam.position.x = 400;
		cam.position.y = 240;
		cam.position.z = (float) (240 / Math.tan(30.0f / 180.0f * Math.PI ));
		gameStage.setCamera(cam);
		
		
		isPause = false;
		
		uiBatch = new SpriteBatch();
		uiStage = new Stage(SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT, SV.WINDOW_SCALE_MODE, uiBatch);
		
		
		paintGroup = new Group();
		paintGroup.setSize(SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT);
		gameStage.addActor(paintGroup);
		
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
			uiStage.addActor(debugGroup);
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
			PWM.instance().step(delta);
			TM.instance().step(delta);	
			gameStage.act(delta);
			uiStage.act(delta);
		}
		
		gameStage.draw();
		uiStage.draw();
		//调试信息
		if(DEBUG){
			uiBatch.begin();
			font.draw(uiBatch, "DrawCalls : " + numDrawCalls, 20, 100);
			font.draw(uiBatch, "NativeHeap: " + Gdx.app.getNativeHeap() / 1024f / 1024f + " Mb", 20, 70);
			font.draw(uiBatch, "JavaHeap  : " + Gdx.app.getJavaHeap()/1024f/1024f + " Mb", 20, 40);
			uiBatch.end();
			
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
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(uiStage);
		multiplexer.addProcessor(gameStage);
		Gdx.input.setInputProcessor(multiplexer);
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
		if(gameStage != null){
			gameStage.dispose();
			gameStage = null;
		}
		
		if(gameBatch != null){
			gameBatch.dispose();
			gameBatch = null;
		}

		if(uiStage != null){
			uiStage.dispose();
			uiStage = null;
		}
		
		if(uiBatch != null){
			uiBatch.dispose();
			uiBatch = null;
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
