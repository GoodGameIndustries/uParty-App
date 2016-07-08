package com.GGI.uParty.Objects;



import com.GGI.uParty.uParty;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

public class CommentModule {
	
	private String s;
	private uParty u;

	private SpriteBatch pic;
	private ShapeRenderer shape;
	
	public Rectangle bounds = new Rectangle();
	
	private LabelStyle labelS;
	private Label comm;
	
	private float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
	public CommentModule(String s, uParty u){
		this.s=s;
		this.u=u;
		
		pic = new SpriteBatch();
		shape = new ShapeRenderer();
		
		labelS = new LabelStyle();
			labelS.font=u.assets.small;
			labelS.fontColor = Color.WHITE;
		comm = new Label(s,labelS);
			comm.setAlignment(Align.center);
	}
	
	public void render(){
		//bounds = new Rectangle(0,.58f*h,w,.075f*h);
		comm.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
		
		shape.begin(ShapeType.Filled);
		shape.setColor(u.assets.darkL);
		shape.rect(bounds.x, bounds.y, bounds.width, .9f*bounds.height);
		shape.setColor(Color.BLACK);
		shape.rect(bounds.x, bounds.y, bounds.width, .05f*bounds.height);
		shape.end();
		
		pic.begin();
		comm.draw(pic, 1);
		pic.end();
	}
	
}
