package com.GGI.uParty.Objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Network.Party;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class PartyList extends ArrayList<PartyModule>{

	public float w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	public float height = 0;
	
	public uParty u;
	
	public PartyList(uParty u){
		this.u=u;
	}
	
	

	public void render(int scrolled){
		height = 0;
		for(int i = 0;i<size();i++){
			get(i).bounds=new Rectangle(0,.945f*h-((i+1)*.2f*h)+scrolled,w,.2f*h);
			height+=.2f*h;
			get(i).render();
		}
		
	}
	
	public boolean down(Rectangle touch){
		if(!Intersector.overlaps(touch, new Rectangle(0,.945f*h-height,w,height))){return false;}
		else{for(int i = 0; i < size();i++){
			if(get(i).down(touch)){break;}
		}}
		return true;
		
	}
	
	public boolean up(Rectangle touch){
		if(!Intersector.overlaps(touch, new Rectangle(0,.945f*h-height,w,height))){return false;}
		else{for(int i = 0; i < size();i++){
			if(get(i).up(touch)){break;}
		}}
		return true;
		
	}

	public void refresh(ArrayList<Party> parties) {
		//this.clear();
		ArrayList<PartyModule> p = new ArrayList<PartyModule>();
		Date d = new Date();
		for(int i = 0; i < parties.size();i++){
			if(d.getDate()==parties.get(i).d.getDate()){
			p.add(new PartyModule(u,parties.get(i)));}
		}
		Collections.sort(p);
		this.clear();
		this.addAll(p);
	}
}
