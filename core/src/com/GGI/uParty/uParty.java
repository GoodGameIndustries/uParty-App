package com.GGI.uParty;

import java.io.IOException;

import com.GGI.uParty.Network.Err;
import com.GGI.uParty.Network.Network;
import com.GGI.uParty.Network.PList;
import com.GGI.uParty.Network.Profile;
import com.GGI.uParty.Network.Sendable;
import com.GGI.uParty.Screens.LoadingScreen;
import com.GGI.uParty.Screens.LoginScreen;
import com.GGI.uParty.Screens.SignUpScreen;
import com.GGI.uParty.Screens.VersionUpdateScreen;
import com.badlogic.gdx.Game;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;

public class uParty extends Game {
	public Assets assets;
	private Client client;
	private boolean debug = false;
	public Adapter adapter;
	public String version = "1.0";
	public uParty(Adapter adapter){
		this.adapter=adapter;
	}
	
	@Override
	public void create () {
		client= new Client();
		
		assets = new Assets(this);
		
		client.addListener(new ThreadedListener(new Listener(){
			 public void received (Connection connection, Object object) {
		         //System.out.println("I recieved something");
				 
		         if (object instanceof Err) {
					 Err e = (Err)object;
					 if(e.message.equals("Version")){
						 setToUpdate();
					 }
					 
		             if(getScreen() instanceof SignUpScreen){
		            	 SignUpScreen s = (SignUpScreen)getScreen();
		            	 s.error=e.message;
		            	 s.err.setText(s.error);
		             }
		             else if(getScreen() instanceof LoginScreen){
		            	 LoginScreen s = (LoginScreen)getScreen();
		            	 s.error=e.message;
		            	 s.err.setText(s.error);
		             }
		             else if(getScreen() instanceof LoadingScreen){
		            	 LoadingScreen s = (LoadingScreen)getScreen();
		            	try{
		            	 setScreen(new LoginScreen(s.u));
		            	}catch(Exception r){}
		             }
		         }
		         
		         else if(object instanceof Profile){
		        	 assets.myProfile=(Profile)object;
		        	 System.out.println("Got profile: " + assets.myProfile.name);
		         }
		         else if(object instanceof PList){
		        	 PList o = (PList)object;
		        	 assets.parties.refresh(o.parties);
		        	 System.out.println("Refreshing parties");
		        	 System.out.println("Parties: "+o.parties.size());
		         }
		       }
			
		}));
		
		setScreen(new LoadingScreen(this));
	}
	
	protected void setToUpdate() {
		setScreen(new VersionUpdateScreen(this));
		
	}

	public void connect(){
		if(!client.isConnected()){
			try {
				
				client.start();
				client.connect(5000, debug ?"localhost":"52.89.96.208", 36693);
				Network.register(client);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void send(Sendable s){
		System.out.println("Sent something");
		connect();
		int t=0;
		boolean noSend=true;
		while(noSend&&t<=3)
		try{
		t++;
		client.sendTCP(s);
		noSend=false;
		}catch(Exception e){
			System.out.println("Send error");
			connect();
		}
	}
	
	

	
	
}
