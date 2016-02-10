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
	
	/**The party list holds all of the parties that have been generated
	 * in their party modules
	 * @param u
	 */
	public PartyList(uParty u){
		this.u=u;
	}
	
	
	/**This method takes care of rendering of the party modules and also
	 * simulates the scrolling
	 * @param scrolled
	 */
	public void render(int scrolled){
		height = 0;
		for(int i = 0;i<size();i++){
			get(i).bounds=new Rectangle(0,.945f*h-((i+1)*.2f*h)+scrolled,w,.2f*h);
			height+=.2f*h;
			get(i).render();
		}
		
	}
	
	/**If touched this method is triggered and decides the proper action
	 * based on the touch
	 * @param touch
	 * @return ifTouched
	 */
	public boolean down(Rectangle touch){
		if(!Intersector.overlaps(touch, new Rectangle(0,.945f*h-height,w,height))){return false;}
		else{for(int i = 0; i < size();i++){
			if(get(i).down(touch)){break;}
		}}
		return true;
		
	}
	
	/**If released this method is triggered and decides the proper action
	 * based on the touch
	 * @param touch
	 * @return ifReleased
	 */
	public boolean up(Rectangle touch){
		if(!Intersector.overlaps(touch, new Rectangle(0,.945f*h-height,w,height))){return false;}
		else{for(int i = 0; i < size();i++){
			if(get(i).up(touch)){break;}
		}}
		return true;
		
	}

	/**This method updates all the parties in the list while also
	 * sorting them and removing any parties that are too old
	 * @param parties
	 */
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


	public void refresh(Party o) {
		boolean update = false;
		
		Date d = new Date();
		for(int i = 0; i < this.size();i++){
			if(d.getDate()!=get(i).p.d.getDate()){
				remove(i);
			}
			else{
			if(get(i).p.id.equals(o.id)){
				remove(i);
				add(new PartyModule(u,o));
				update=true;
				break;
			}
			}
		}
		if(!update){
			add(new PartyModule(u,o));
		}
		
		Collections.sort(this);
		
	}
}
