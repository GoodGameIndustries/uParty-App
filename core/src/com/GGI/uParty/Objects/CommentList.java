package com.GGI.uParty.Objects;

import java.util.ArrayList;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Network.Party;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class CommentList extends ArrayList<CommentModule>{

	private uParty u;
	public float height;
	
	private float w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	
	public CommentList(Party p, uParty u){
		this.u=u;
	}
	
	public void render(int scrolled){
		height = 0;
		for(int i = 0;i<size();i++){
			get(i).bounds=new Rectangle(0,.58f*h - (i*.075f*h) + scrolled,w,.075f*h);
			height+=.075f*h;
			get(i).render();
		}
	}
	
	public void refresh(ArrayList<String> str){
		ArrayList<CommentModule> temp = new ArrayList<CommentModule>();
			for(int i = 0; i < str.size(); i++){
				temp.add(new CommentModule(str.get(i),u));
			}
		this.clear();
		this.addAll(temp);
	}
}
