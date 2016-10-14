package com.doodle.kdemo.managers;

import java.util.HashMap;
import java.util.Map;

import com.doodle.kdemo.common.BaseActor;


//BaseActor管理类，BaseActorManager
public class BAM {
	
	private static BAM s_instance;
	
	private BAM(){
		
	}
	
	public static BAM instance(){
		if(s_instance == null){
			s_instance = new BAM();
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
		
	}
	
	public void destroy(){
		
	}
	
	//====================================游戏相关========================================
	private Map<Integer, BaseActor> baseActors = new HashMap<Integer, BaseActor>();
	
	public void addBaseActor(BaseActor actor){
		if(actor != null && baseActors != null){
			baseActors.put(actor.getTag(), actor);
		}		
	}

	public void delBaseActor(int tag){
		if(baseActors != null){
			baseActors.remove(tag);
		}
	}
	
	public BaseActor getBaseActor(int tag){
		BaseActor rs = null;
		if(baseActors != null){
			rs = baseActors.get(tag); 
		}
		return rs;
	}
	
}
