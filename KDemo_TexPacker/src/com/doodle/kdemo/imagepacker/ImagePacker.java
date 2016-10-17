package com.doodle.kdemo.imagepacker;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class ImagePacker {

	public static void main(String[] argv) {
		String[] inputs = {
			"loading",
			"menu",
		};
		
		String[] inputs_map = {
			"map01",
			"map02",
			"role",
		};

		String input = "E:/android工程/kdemo/KDemo_TexPacker/assets/images/", output = "E:/android工程/kdemo/KDemo/assets/images/", packFileName = "";
		String input2 = "E:/android工程/kdemo/KDemo_TexPacker/assets/images/game/", output2 = "E:/android工程/kdemo/KDemo/assets/images/game/", packFileName2 = "";
		Settings settings = new Settings();
		settings.filterMin = TextureFilter.Linear;
		settings.filterMag = TextureFilter.Linear;
		settings.stripWhitespaceX = true;
		settings.stripWhitespaceY = true;
		settings.jpegQuality = 1f;

		for (int i = 0; i < inputs.length; i++) {
			String tmp = inputs[i];
			TexturePacker2.process(settings, input + tmp, output + tmp, tmp);
		}
		
		for (int i = 0; i < inputs_map.length; i++) {
			String tmp = inputs_map[i];
			TexturePacker2.process(settings, input2 + tmp, output2 + tmp, tmp);
		}

	}
}
