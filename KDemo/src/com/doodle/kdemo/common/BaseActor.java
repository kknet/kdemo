package com.doodle.kdemo.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.doodle.kdemo.managers.BAM;
import com.doodle.kdemo.managers.LM;
import com.doodle.kdemo.utils.SV;

public class BaseActor extends Actor {
	public static final int STOP = 0;
	public static final int RIGHT = 1;
	public static final int LEFT = -1;
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int JUMP_UP = 1;
	public static final int JUMP_DOWN = -1;
	
	
	protected int tag = 0;
	
	public float jump_vel = 100;
	public boolean onGround = true;
	
	public float speed_x = 0;
	public float speed_y = 0;
	public float speed_z = 0;
	
	public int x_dir = STOP;
	public int y_dir = STOP;
	public int z_dir = STOP;  //跳跃方向为z轴
	
	protected Vector2 lastPos = new Vector2();
	
	public void setTag(int tag){
		if(this.tag != tag ){
			BAM.instance().delBaseActor(this.tag);
			this.tag = tag;
			BAM.instance().addBaseActor(this);
		}
	}
	
	public int getTag(){
		return tag;
	}
	
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		lastPos.x = this.getX();
		lastPos.y = this.getY();
		
		if(onGround){
			this.translate(speed_x * x_dir * delta, speed_y * y_dir * delta );
		}else{
			this.translate(speed_x * x_dir * delta, speed_y * y_dir * delta );//+ speed_z * z_dir * delta);	
			speed_z -= SV.SPEED_OF_DECAY * delta;
			
			jumpCalc();
		}		
	}
	
	//跳跃计算
	private void jumpCalc(){
		if(this.speed_z <= -jump_vel){
			onGround = true;
		}
	}
	
	

	public boolean moveLeft(){
		if(this.onGround){
			this.x_dir = LEFT;
			LM.instance().logD("Move Left");
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean moveRight(){
		if(this.onGround){
			this.x_dir = RIGHT;
			LM.instance().logD("Move Right");
			return true;
		}else{
			return false;
		}
	}
	
	public boolean moveUp(){
		if(this.onGround){
			this.y_dir = UP;
			LM.instance().logD("Move Up");
			return true;
		}else{
			return false;
		}

	}
	
	public boolean moveDown(){
		if(this.onGround){
			this.y_dir = DOWN;
			LM.instance().logD("Move Down");
			return true;
		}else{
			return false;
		}
	
	}
	
	public boolean stopLeft(){
		if(this.x_dir == LEFT){
			this.x_dir = STOP;
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean stopRight(){
		if(this.x_dir == RIGHT){
			this.x_dir = STOP;
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean stopUp(){
		if(this.y_dir == UP){
			this.y_dir = STOP;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean stopDown(){
		if(this.y_dir == DOWN){
			this.y_dir = STOP;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean jump(){
		if(this.onGround){
			this.speed_z = jump_vel;
			LM.instance().logD("Jump");
			this.onGround = false;
			return true;
		}else{
			return false;
		}
	}
}
