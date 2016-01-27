package com.GGI.uParty.Screens;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Network.Login;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen implements Screen{

	public uParty u;
	private SpriteBatch pic;
	private float w=Gdx.graphics.getWidth(),h=Gdx.graphics.getHeight();
	private double theta,scl=5;
	
	public LoadingScreen(uParty u){
		this.u=u;
		pic = new SpriteBatch();
	}
	
	@Override
	public void show() {
		Thread t = new Thread(new ParaLoad());
		t.start();
		
		u.adapter.showOrLoadInterstital();
		
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		theta+=Math.PI/24;
		pic.begin();
		pic.draw(u.assets.logo1024,(float)(w/3-scl*Math.sin(theta)/2f),(float)((h/2)-(w/6)-scl*Math.sin(theta)/2f),(float)(w/3+scl*Math.sin(theta)),(float)(w/3+scl*Math.sin(theta)));
		pic.end();
		
		if(theta>6*Math.PI){
			u.setScreen(u.assets.myProfile==null||!u.assets.myProfile.verr?new LoginScreen(u):new MainScreen(u));
		}
		
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
	
	public class ParaLoad implements Runnable{

		@Override
		public void run() {
			if(Gdx.files.local("uPartyRemember.txt").exists()){
				String[] split = Gdx.files.local("uPartyRemember.txt").readString().split(":");
			Login l = new Login();
			l.email=split[0];
			l.pass=split[1];
			u.send(l);
			}
			
		}
		
	}

}
