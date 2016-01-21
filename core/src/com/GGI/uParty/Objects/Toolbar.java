package com.GGI.uParty.Objects;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Screens.LoginScreen;
import com.GGI.uParty.Screens.MainScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Toolbar {

	private uParty u;
	private SpriteBatch pic;
	private ShapeRenderer shape;
	private float w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	private GlyphLayout layout = new GlyphLayout();
	public boolean loggingOut=false;
	public boolean backEnabled=false;
	public Toolbar(uParty u){
		this.u=u;
		pic = new SpriteBatch();
		shape = new ShapeRenderer();
	}
	public void render(){
		shape.begin(ShapeType.Filled);
		shape.setColor(u.assets.orange);
		shape.rect(0,.95f*h,w,.05f*h);
		float darken = .8f;
		shape.setColor(new Color(darken*(247f/255f),darken*(148f/255f),darken*(29f/255f),1f));
		shape.rect(0,.945f*h,w,.005f*h);
		shape.end();
		
		pic.begin();
		u.assets.medium.setColor(u.assets.dark);
		u.assets.medium.draw(pic, backEnabled?"< Back":"uParty", .05f*w, .98f*h);
		layout.setText(u.assets.medium, loggingOut?"Logout":u.assets.myProfile.name);
		u.assets.medium.draw(pic, loggingOut?"Logout":u.assets.myProfile.name, .95f*w-layout.width, .98f*h);
		pic.end();
	}
	
	public boolean touch(Rectangle touch){
		layout.setText(u.assets.medium, loggingOut?"Logout":u.assets.myProfile.name);
		if(Intersector.overlaps(touch, new Rectangle(.95f*w-layout.width, .98f*h-layout.height,layout.width,layout.height))){
			if(loggingOut){u.assets.myProfile=null;u.setScreen(new LoginScreen(u));}
			loggingOut=true;}
		else if(backEnabled){
			layout.setText(u.assets.medium, "< Back");
			if(Intersector.overlaps(touch, new Rectangle(.05f*w, .98f*h-layout.height,layout.width,layout.height))){
				backEnabled=false;
				u.setScreen(new MainScreen(u));
			}
		}
		else{loggingOut=false;}
		
		return true;
		
	}
	
}
