package com.doodle.kdemo.physics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape.Type;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

class PolygonInfo{
	
	public PolygonInfo(){
		
	}
	
	public PolygonInfo(PolygonInfo info){
		this.numVertexes = info.numVertexes;
		this.vets = info.vets.clone();
	}
	
	public int numVertexes;
	public float[] vets;
}

class FixtureInfo{
	public float density;
	public float friction;
	public float restitution;
	public short filter_categoryBits;
	public short filter_groupIndex;
	public short filter_maskBits;
	public boolean isSensor;
	public Type type;
	public int numPolygons;
	
	public List<PolygonInfo> polygons = new ArrayList<PolygonInfo>(); 
}

class BodyInfo{
	public String name;
	public float anchorpoint_x;
	public float anchorpoint_y;
	
	public List<FixtureInfo> fixtures = new ArrayList<FixtureInfo>();
    
}


public class Xml2Body {
	
	public Map<String, BodyInfo> bodys = new HashMap<String, BodyInfo>();	
	
	public Xml2Body(String filename){
		parseXml(Gdx.files.internal(filename));
	}

	
	public Body createBody(String name, World world, BodyDef bodyDef, float origin_x, float origin_y, float scale_x, float scale_y, boolean flip_x, boolean flip_y){

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++=");
		BodyInfo bodyInfo = bodys.get(name);
		if( bodyInfo == null){			
			return null;	
		}

		Body body = world.createBody(bodyDef);			
		for(FixtureInfo fixtureInfo :bodyInfo.fixtures){
			
			for(int j = 0; j < fixtureInfo.numPolygons; j++){
				PolygonInfo polygonInfo = new PolygonInfo(fixtureInfo.polygons.get(j));					
				FixtureDef fixtureDef = new FixtureDef();
				PolygonShape polygon = new PolygonShape();
				
			for(int i=0; i < polygonInfo.vets.length; i++){
					if(i%2 == 0){		
						if(flip_x){
							polygonInfo.vets[i] *= -1;
						}
						polygonInfo.vets[i] = (polygonInfo.vets[i] - origin_x) * scale_x;
						
						
					}else{	
						if(flip_y){
							polygonInfo.vets[i] *= -1;
						}
						polygonInfo.vets[i] = (polygonInfo.vets[i] - origin_y) * scale_y;
						
					}			 
				}
				
				polygon.set(polygonInfo.vets);
				fixtureDef.shape = polygon;
				fixtureDef.density = fixtureInfo.density;
				fixtureDef.friction = fixtureInfo.friction;
				fixtureDef.restitution = fixtureInfo.restitution;
				fixtureDef.isSensor = fixtureInfo.isSensor;
				fixtureDef.filter.categoryBits = fixtureInfo.filter_categoryBits;
				fixtureDef.filter.groupIndex = fixtureInfo.filter_groupIndex;
				fixtureDef.filter.maskBits = fixtureInfo.filter_maskBits;
			
				System.out.println("filter_categoryBits£º" + fixtureInfo.filter_categoryBits);
				System.out.println("filter_groupIndex£º" + fixtureInfo.filter_groupIndex);
				System.out.println("filter_maskBits£º" + fixtureInfo.filter_maskBits);
				body.createFixture(fixtureDef);
			}
		}		
		return body;
	}		
	
	private void parseXml(FileHandle fh){
		XmlReader reader = new XmlReader();
		Element e_bodydef;
		try {
			e_bodydef = reader.parse(fh);
			
			Element e_bodies = e_bodydef.getChildByName("bodies");

			Array<Element> e_body_s = e_bodies.getChildrenByName("body");
			
			for(Element e_body: e_body_s){
				
				BodyInfo bodyInfo = new BodyInfo();
				bodyInfo.name = e_body.getAttribute("name");
				String[] anchorStr = e_body.get("anchorpoint").split(","); 
				bodyInfo.anchorpoint_x = Float.parseFloat(anchorStr[0]);
				bodyInfo.anchorpoint_y = Float.parseFloat(anchorStr[1]);
				 																		
				Array<Element> e_fixture_s = e_body.getChildByName("fixtures").getChildrenByName("fixture");	
				
				System.out.println("e_fixture_s.size: "+e_fixture_s.size);
				
				for(Element e_fixture: e_fixture_s){
					FixtureInfo fixtureInfo = new FixtureInfo();
										
					fixtureInfo.density =e_fixture.getFloat("density");						
					fixtureInfo.friction = e_fixture.getFloat("friction");
					fixtureInfo.restitution = e_fixture.getFloat("restitution");
					fixtureInfo.filter_categoryBits = (short)e_fixture.getInt("filter_categoryBits");
					fixtureInfo.filter_groupIndex =  (short)e_fixture.getInt("filter_groupIndex");
					fixtureInfo.filter_maskBits =  (short)e_fixture.getInt("filter_maskBits");
					if(e_fixture.getChildByName("isSensor") != null){
						fixtureInfo.isSensor = true;
					}else{
						fixtureInfo.isSensor = false;
					}
					
					fixtureInfo.type = Type.Polygon;
					fixtureInfo.numPolygons = e_fixture.getChildByName("polygons").getChildCount();						
					Array<Element> e_polygon_s = e_fixture.getChildByName("polygons").getChildrenByName("polygon");
			
					for(Element e_polygon: e_polygon_s){
						PolygonInfo polygonInfo = new PolygonInfo();
						String str = e_polygon.getText();
						String[] str1 = str.split(",");							
						polygonInfo.vets = new float[str1.length];
						
						for(int i=0; i<str1.length; i++){
							float x = Float.parseFloat(str1[i]);																
							polygonInfo.vets[i] = x;
						}
						fixtureInfo.polygons.add(polygonInfo);						
					}
					bodyInfo.fixtures.add(fixtureInfo);
				}
													
				bodys.put(bodyInfo.name, bodyInfo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}