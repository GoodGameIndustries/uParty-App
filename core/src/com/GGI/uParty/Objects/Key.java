package com.GGI.uParty.Objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Key extends TextButton{

	public String lowerCase,upperCase;
	public Keyboard keyBoard;
	public Rectangle bounds;
	
	public Key(String text,String shiftText, Keyboard k) {
		super(text, k.buttonStyle);
		lowerCase = text;
		upperCase = shiftText;
		keyBoard = k;
		
	}
	
	public void shift(boolean b){
		setText(b?upperCase:lowerCase);
	}
	
	public void setBounds(float x, float y, float w, float h){
		super.setBounds(x, y, w, h);
		bounds = new Rectangle(x,y,w,h);
	}

}
