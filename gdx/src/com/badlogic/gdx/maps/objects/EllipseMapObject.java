/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;

public class EllipseMapObject extends MapObject {

	private Ellipse ellipse;
	
	/**
	 * @return ellipse shape
	 */
	public Ellipse getEllipse() {
		return ellipse;
	}
	
	/**
	 * Creates an ellipse object which lower left corner is at (0, 0) with width=1 and height=1
	 */
	public EllipseMapObject() {
		this(0.0f, 0.0f, 1.0f, 1.0f);
	}
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public EllipseMapObject(float x, float y, float width, float height) {
		super();
		ellipse = new Ellipse(x, y, width, height);
	}
	
}