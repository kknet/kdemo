package com.doodle.kdemo.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.doodle.kdemo.common.BaseMap;
import com.doodle.kdemo.managers.PWM;
import com.doodle.kdemo.managers.RM;
import com.doodle.kdemo.physics.Xml2Body;

public class Map02 extends BaseMap {
	
	private MapBlock bg1;
	private MapBlock bg2;
	private MapBlock bg3;
	private Xml2Body physicsBodys;
	
	public Map02(){
		World world = PWM.instance().getWorld();
		
		physicsBodys = new Xml2Body("configs/map02_box2d.xml");
		
		TextureAtlas atlas =  RM.instance().getTexAtls("game/map02/map02.atlas");
		Sprite tmp1 = atlas.createSprite("bg1");
		BodyDef bodyDef1 = new BodyDef();
		bodyDef1.type = BodyType.StaticBody;
		bg1 = new MapBlock(tmp1, physicsBodys.createBody("bg1", world, bodyDef1, 0, 0, 1, 1, false, false));
		bg1.setPosition(0, 0);
		this.addActor(bg1);
		
		tmp1 = atlas.createSprite("bg2");
		BodyDef bodyDef2 = new BodyDef();
		bodyDef2.type = BodyType.StaticBody;
		bg2 = new MapBlock(tmp1, physicsBodys.createBody("bg2", world, bodyDef2, 0, 0, 1, 1, false, false));
		bg2.setPosition(800, 0);
		this.addActor(bg2);
		
		tmp1 = atlas.createSprite("bg3");
		BodyDef bodyDef3 = new BodyDef();
		bodyDef3.type = BodyType.StaticBody;
		bg3 = new MapBlock(tmp1, physicsBodys.createBody("bg3", world, bodyDef3, 0, 0, 1, 1, false, false));
		bg3.setPosition(1600, 0);
		this.addActor(bg3);
		
		this.MAP_MAX_WINDTH = bg1.getWidth() + bg2.getWidth() + bg3.getWidth();
	}
}
