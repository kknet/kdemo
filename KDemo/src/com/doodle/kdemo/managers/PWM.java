package com.doodle.kdemo.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.doodle.kdemo.utils.SV;

//物理世界管理类 PhysicsWorldManager
public class PWM {	
	private static PWM s_instance;
	
	private PWM(){
		
	}
	
	public static PWM instance(){
		if(s_instance == null){
			s_instance = new PWM();
			s_instance.init();
		}
		return s_instance;
	}
	
	public static void relInstance(){
		if(s_instance != null){
			s_instance.destroy();
			s_instance = null;
		}
	}

	public void init(){
		this.world = new World(new Vector2(0,0), false);
	}
	
	public void destroy(){
		
	}
	
	
	//==========================================游戏相关==============================================
	private World world;
	
	public World getWorld(){
		return this.world;
	}
	
	public void step(float delta){
		this.world.step(delta, SV.VEL_ITRT, SV.POS_ITRT);
	}
	
	//----------------------------------------------------------
	private List<Body> mapBlockBodys = new ArrayList<Body>();
	
	private List<Fixture> mapBlockFixtures = new ArrayList<Fixture>();
	
	private void getMapBlockFixtures(){
		mapBlockFixtures.clear();
		for(Body body: mapBlockBodys){
			for(Fixture fixture: body.getFixtureList()){
				mapBlockFixtures.add(fixture);
			}
		}
	}
	
	public boolean pointIsInObstacle(float x, float y){
		boolean rs = false;
		for(int i = 0; i < mapBlockFixtures.size(); i ++){
			if(mapBlockFixtures.get(i).testPoint(x, y)){
				rs = true;
				break;
			}
		}
		return rs;
	}
	
	
	public void addMapBlockBodys(Body mapBody){
		this.mapBlockBodys.add(mapBody);
		getMapBlockFixtures();
	}
	
	public void clearMapBlockBodys(){
		mapBlockBodys.clear();
		getMapBlockFixtures();
	}
	
	public List<Body> getMapBlockBodys(){
		return mapBlockBodys;
	}
	
	public void setContactListener(ContactListener listener){
		this.world.setContactListener(listener);
	}
	
	//---------------------------------------------------------
	private Body heroBody;
	public void registerHeroBody(Body heroBody){
		this.heroBody = heroBody;
	} 
	
}
