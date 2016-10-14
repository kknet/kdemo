package com.doodle.kdemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.doodle.kdemo.utils.SV;

public class KDemo{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) SV.WINDOW_WIDTH;
		config.height = (int) SV.WINDOW_HEIGHT;
		config.useGL20 = true;
		new LwjglApplication(new KGame(), config);
	}

}
