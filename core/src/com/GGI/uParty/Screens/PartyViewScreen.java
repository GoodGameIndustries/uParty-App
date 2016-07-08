/**
 * 
 */
package com.GGI.uParty.Screens;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Network.Party;
import com.GGI.uParty.Objects.CommentList;
import com.GGI.uParty.Objects.Keyboard;
import com.GGI.uParty.Objects.PartyModule;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Align;

/**
 * @author Emmett
 *
 */
public class PartyViewScreen implements Screen,InputProcessor{

	private float w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	
	private uParty u;
	private Party p;
	
	private SpriteBatch pic;
	private ShapeRenderer shape;
	
	private String com = "";
	
	private Rectangle backBounds = new Rectangle(w/36,61*h/64,w/18,h/32);
	private Rectangle commentB = new Rectangle(0,.895f*h,.91f*w,.05f*h);
	private Rectangle sendB = new Rectangle(.9f*w,.895f*h,.1f*w,.05f*h);
	
	private TextButtonStyle plainButtonStyle;
	
	private TextButton back;
	private TextButton send;
	private PartyModule pModule;
	private TextField comment;
	private TextFieldStyle style;
	private LabelStyle grey;
	private Label noComments;
	
	private CommentList comm;

	private TextButtonStyle buttonStyle;
	
	private Keyboard keyBoard;
	
	private Stage stage;
	
	public PartyViewScreen(uParty u, Party p){
		this.u=u;
		this.p=p;
		p.comments.add("test comment 1");
		p.comments.add("test comment 2");
		
		keyBoard = new Keyboard(u,this);
		
		comm = new CommentList(p,u);
			comm.refresh(p.comments);
		this.pModule=new PartyModule(u,p);
		pModule.bounds=new Rectangle(0,h/2,w,h/2-.1f*h);
		pModule.locationEnabled=true;
		
		buttonStyle = new TextButtonStyle();
			buttonStyle.font=u.assets.medium;
			buttonStyle.fontColor=u.assets.dark;
			buttonStyle.up=u.assets.buttonStyleUp;
			buttonStyle.down=u.assets.buttonStyleDown;
			buttonStyle.checked=u.assets.buttonStyleDown;
		send = new TextButton(">",buttonStyle);
			send.setBounds(sendB.x, sendB.y, sendB.width, sendB.height);
		plainButtonStyle = new TextButtonStyle();
			plainButtonStyle.font=u.assets.large;
			plainButtonStyle.fontColor = u.assets.orange;
			plainButtonStyle.checkedFontColor = Color.GRAY;
		back = new TextButton("<",plainButtonStyle);
			back.setBounds(backBounds.x,backBounds.y,backBounds.width,backBounds.height);
			
		style = new TextFieldStyle();
			style.font=u.assets.small;
			style.fontColor=Color.WHITE;
			style.background=u.assets.textStyleBorder;
			style.focusedBackground=u.assets.focusTextStyleBorder;
		comment = new TextField("",style);
			comment.setAlignment(Align.center);
			comment.setMessageText("Write a comment...");
			comment.setBounds(commentB.x, commentB.y, commentB.width, commentB.height);
		
		grey = new LabelStyle();
			grey.font=u.assets.large;
			grey.fontColor=Color.DARK_GRAY;
		noComments = new Label("No Comments",grey);
			noComments.setAlignment(Align.center);
			noComments.setBounds(w/4, h/4, w/2, h/3);
		
		stage = new Stage();
		stage.addActor(comment);
			
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		pic = new SpriteBatch();
		shape = new ShapeRenderer();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.05f, .05f, .05f, 05);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		for(int i = 0; i < u.assets.parties.size();i++){
			if(p.name.equals(u.assets.parties.get(i).p.name)&&p.description.equals(u.assets.parties.get(i).p.description)&&p.where.equals(u.assets.parties.get(i).p.where)){
				this.p=u.assets.parties.get(i).p;}
		}
		
		this.pModule=new PartyModule(u,p);
		pModule.bounds=new Rectangle(0,3*h/4-.105f*h,w,h/4);
		pModule.locationEnabled=true;
		
		
	
		pic.begin();
		comment.draw(pic, 1);
		send.draw(pic, 1);
		
		if(p.comments.size()<=0){
			noComments.draw(pic, 1);
		}
		
		pic.end();
		comm.render(0);
		pModule.render();
		u.assets.toolBar.render();
		
		if(keyBoard.isVisible||keyBoard.theta!=0){keyBoard.render();}
	
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
		if(character == ''&&com.length()>0){
			com=com.substring(0, com.length()-1);
		}
		else if((character == '\r' || character == '\n')){}
		else if(com.length()<80){
			com+=character;
		}
		
		com=com.replaceAll("\\p{Cntrl}","");
		
		comment.setText(com);
	return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY=(int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		pModule.down(touch);
		if(Intersector.overlaps(touch, sendB)){send.toggle();}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY=(int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		u.assets.toolBar.touch(touch);
		pModule.up(touch);
		if(keyBoard.isVisible&&!Intersector.overlaps(touch, keyBoard.bounds)){
			keyBoard.isVisible=false;stage.setKeyboardFocus(null);
		}
		if(Intersector.overlaps(touch, commentB)){keyBoard.isVisible=true;stage.setKeyboardFocus(comment);}
		if(Intersector.overlaps(touch, sendB)){send.toggle();comment.setText("");}
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
