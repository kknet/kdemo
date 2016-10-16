package com.doodle.kdemo.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.doodle.kdemo.managers.PWM;

public class MapBlock extends Image {

	private Body m_body;
	private static Box2DDebugRenderer renderer = new Box2DDebugRenderer();
	
	public MapBlock(Sprite sprite, Body body){
		super(sprite);
		m_body = body;
		PWM.instance().addMapBlockBodys(body);
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		if(m_body != null){
			m_body.setTransform(this.getX(), this.getY(), 0);
		}
		super.act(delta);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		
		batch.end();
		renderer.render(m_body.getWorld(), batch.getProjectionMatrix());
		batch.begin();
		
	}
	
	
	
	
}
