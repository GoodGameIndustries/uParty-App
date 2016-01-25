package com.GGI.uParty;

import com.GGI.uParty.Network.Profile;
import com.GGI.uParty.Objects.PartyList;
import com.GGI.uParty.Objects.Toolbar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Assets {
	public uParty u;
	public float w = Gdx.graphics.getWidth(),h=Gdx.graphics.getHeight();
	public PartyList parties;
	public Toolbar toolBar;
	
	/**My Profile*/
	public Profile myProfile;
	
	/**Colors*/
	public Color orange = new Color(247f/255f,148f/255f,29f/255f,1f);
	public Color dark = new Color(.1f,.1f,.1f,1);
	
	/**Images*/
	public Texture logo1024;
	public TextureRegionDrawable textStyleBorder;
	public TextureRegionDrawable buttonStyleUp;
	public TextureRegionDrawable buttonStyleDown;
	public TextureRegionDrawable keyStyleUp;
	public TextureRegionDrawable keyStyleDown;
	public TextureRegionDrawable checkBoxStyleOn;
	public TextureRegionDrawable checkBoxStyleOff;
	public TextureRegion voteButtonUp;
	public TextureRegion voteButtonDown;
	
	
	/**Fonts*/
	public BitmapFont small;
	public BitmapFont medium;
	public BitmapFont large;
	
	public Assets(uParty u){
		this.u=u;
		loadImages();
		loadFonts();
		toolBar = new Toolbar(u);
		parties = new PartyList(u);
	}


	private void loadFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		parameter.size = (int) (h/75);
		small = generator.generateFont(parameter); 
		
		parameter.size = (int) (h/40);
		medium = generator.generateFont(parameter); 
		
		parameter.size = (int) (h/25);
		large = generator.generateFont(parameter); 
		generator.dispose();
		
	}


	private void loadImages() {
		logo1024 = new Texture(Gdx.files.internal("1024.png"));
		textStyleBorder = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Images/TextStyleBorder.png"))));
		buttonStyleUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Images/ButtonStyleUp.png"))));
		buttonStyleDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Images/ButtonStyleDown.png"))));
		keyStyleUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Images/KeyStyleUp.png"))));
		keyStyleDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Images/KeyStyleDown.png"))));
		checkBoxStyleOn = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Images/CheckBoxStyleOn.png"))));
		checkBoxStyleOff = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Images/CheckBoxStyleOff.png"))));
		voteButtonUp = new TextureRegion(new Texture(Gdx.files.internal("Images/voteButtonUp.png")));
		voteButtonDown = new TextureRegion(new Texture(Gdx.files.internal("Images/voteButtonDown.png")));
	
	}
}
