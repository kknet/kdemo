package com.doodle.kdemo.imagepacker;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class ImagePacker {

	public static void main(String[] argv) {
		String[] inputs = {
			"menu",
			"loading",
			"game",
			//"result",
		};

		String input = "E:/git¿â/kdemo/KDemo_TexPacker/assets/images/", output = "E:/git¿â/kdemo/KDemo/assets/images/", packFileName = "";
		Settings settings = new Settings();
		settings.filterMin = TextureFilter.Linear;
		settings.filterMag = TextureFilter.Linear;
		settings.duplicatePadding = true;
		settings.jpegQuality = 1f;

		for (int i = 0; i < inputs.length; i++) {
			String tmp = inputs[i];
			TexturePacker2.process(settings, input + tmp, output + tmp, tmp);
		}

	}
}
