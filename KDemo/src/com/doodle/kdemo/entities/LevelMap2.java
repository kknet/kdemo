package com.doodle.kdemo.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.doodle.kdemo.common.BaseLevelMap;
import com.doodle.kdemo.managers.PWM;
import com.doodle.kdemo.managers.RM;
import com.doodle.kdemo.physics.Xml2Body;

public class LevelMap2 extends BaseLevelMap {
	
	private MapBlock bg1;
	private MapBlock bg2;
	private MapBlock bg3;
	private Xml2Body physicsBodys = new Xml2Body("configs/level2_box2d.xml");
	
	public LevelMap2(){
		this.MAP_MAX_WINDTH = 2400;
		World world = PWM.instance().getWorld();
		
		TextureAtlas atlas =  RM.instance().getTexAtls("game/game.atlas");
		Sprite tmp1 = atlas.createSprite("level2_bg1");
		BodyDef bodyDef1 = new BodyDef();
		bodyDef1.type = BodyType.StaticBody;
		bg1 = new MapBlock(tmp1, physicsBodys.createBody("level2_bg1", world, bodyDef1, 0, 0, 1, 1, false, false));
		bg1.setPosition(0, 0);
		this.addActor(bg1);
		
		tmp1 = atlas.createSprite("level2_bg2");
		BodyDef bodyDef2 = new BodyDef();
		bodyDef2.type = BodyType.StaticBody;
		bg2 = new MapBlock(tmp1, physicsBodys.createBody("level2_bg2", world, bodyDef2, 0, 0, 1, 1, false, false));
		bg2.setPosition(800, 0);
		this.addActor(bg2);
		
		tmp1 = atlas.createSprite("level2_bg3");
		BodyDef bodyDef3 = new BodyDef();
		bodyDef3.type = BodyType.StaticBody;
		bg3 = new MapBlock(tmp1, physicsBodys.createBody("level2_bg3", world, bodyDef3, 0, 0, 1, 1, false, false));
		bg3.setPosition(1600, 0);
		this.addActor(bg3);
	}
}
