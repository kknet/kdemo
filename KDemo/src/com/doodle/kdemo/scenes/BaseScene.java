package com.doodle.kdemo.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.doodle.kdemo.managers.TM;
import com.doodle.kdemo.utils.SV;

public abstract class BaseScene implements Screen {
	
	
	protected SpriteBatch batch;
	protected Stage stage;
	protected PerspectiveCamera cam;
	protected boolean isPause;
	
	protected boolean DEBUG = SV.DEBUG;
	protected int numDrawCalls = 0;
	protected BitmapFont font;
	

	public BaseScene(){
		
		batch = new SpriteBatch();
		stage = new Stage(SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT, SV.WINDOW_SCALE_MODE, batch);
		cam = new PerspectiveCamera(67, SV.WINDOW_WIDTH, SV.WINDOW_HEIGHT);
		stage.setCamera(cam);
		isPause = false;
		font = new BitmapFont();
	}

	
	//���Ǳ�Ҫ����������д�˷���
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		//��ʱ������������Ⱦ��ͣӰ�죬�����Ҫ���ж�ʱ����ͣ�������TM.instance().pause()��
		//���ж�ʱ����ͣ������������ˢ��ֱ��������TM.instance.resume()
		//��Ҫ��ͣĳ���ض���ʱ�������Ե���KTimerʵ���е�pause();
		
		TM.instance().step(delta);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		numDrawCalls = 0;
		if(this.isPause == false){
			stage.act(delta);
			stage.draw();
		}
		
		if(DEBUG){

			batch.begin();
			font.draw(batch, "DrawCalls : " + numDrawCalls, 20, 100);
			font.draw(batch, "NativeHeap: " + Gdx.app.getNativeHeap(), 20, 70);
			font.draw(batch, "JavaHeap  : " + Gdx.app.getJavaHeap(), 20, 40);
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
	
	
	//��Ӧ���ؼ���Ϣ
	public void onBack(){
		
	}

}
