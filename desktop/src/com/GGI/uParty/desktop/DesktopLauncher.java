package com.GGI.uParty.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.GGI.uParty.Adapter;
import com.GGI.uParty.uParty;

public class DesktopLauncher implements Adapter{
	public static void main (String[] arg) {
		new DesktopLauncher();
	}
	
	public DesktopLauncher(){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=502;
		config.height=900;
		new LwjglApplication(new uParty(this), config);
	}

	@Override
	public void showOrLoadInterstital() {
		System.out.println("show ad");
		
	}

	
}
