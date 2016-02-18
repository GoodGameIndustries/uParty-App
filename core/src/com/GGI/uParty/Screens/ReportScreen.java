package com.GGI.uParty.Screens;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Network.Report;
import com.GGI.uParty.Objects.PartyModule;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class ReportScreen implements Screen, InputProcessor{

	public uParty u;
	public PartyModule p;
	private TextButtonStyle buttonStyle;
	
	private float w = Gdx.graphics.getWidth(),h=Gdx.graphics.getHeight();
	
	private Rectangle yesB=new Rectangle(6*w/8,h/3,w/8,h/32);
	private Rectangle noB=new Rectangle(w/8,h/3,w/8,h/32);
	
	private TextButton yes;
	private TextButton no;
	
	private SpriteBatch pic = new SpriteBatch();
	private GlyphLayout layout=new GlyphLayout();
	
	public ReportScreen(uParty uParty, PartyModule p) {
		this.u=uParty;
		this.p=p;
		
		buttonStyle = new TextButtonStyle();
		buttonStyle.font=u.assets.medium;
		buttonStyle.fontColor=u.assets.dark;
		buttonStyle.up=u.assets.buttonStyleUp;
		buttonStyle.down=u.assets.buttonStyleDown;
		buttonStyle.checked=u.assets.buttonStyleDown;
		
		yes = new TextButton("Yes",buttonStyle);
			yes.setBounds(yesB.x, yesB.y, yesB.width, yesB.height);
		
		no = new TextButton("No",buttonStyle);
			no.setBounds(noB.x, noB.y, noB.width, noB.height);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		p.bounds=new Rectangle(0,h-((1)*.2f*h),w,.2f*h);
		
		pic.begin();
		yes.draw(pic, 1);
		no.draw(pic, 1);
		u.assets.medium.setColor(u.assets.orange);
		layout.setText(u.assets.medium, "Are you sure you want to report this party?");
		u.assets.medium.draw(pic, "Are you sure you want to report this party?", w/2-layout.width/2, h/2);
		pic.end();
		p.render();
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY = (int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		if(Intersector.overlaps(touch,noB)){no.toggle();}
		else if(Intersector.overlaps(touch,yesB)){yes.toggle();}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY = (int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		
		if(Intersector.overlaps(touch,noB)){no.toggle();u.setScreen(new MainScreen(u));}
		else if(Intersector.overlaps(touch,yesB)){yes.toggle();u.send(new Report(p.p,u.assets.myProfile));u.setScreen(new MainScreen(u));}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
