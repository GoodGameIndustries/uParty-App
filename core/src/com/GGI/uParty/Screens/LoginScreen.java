package com.GGI.uParty.Screens;

import java.io.PrintWriter;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Network.Forgot;
import com.GGI.uParty.Network.Login;
import com.GGI.uParty.Objects.Keyboard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Align;

public class LoginScreen implements Screen,InputProcessor{

	public uParty u;
	private SpriteBatch pic = new SpriteBatch();
	private float w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	private String intro="\"Dude that party was LIT!\"";	
	private GlyphLayout layout = new GlyphLayout();
	private int s = 0;
	
	public String error="";
	public TextField err;
	private TextFieldStyle errorStyle;
	private Rectangle errB = new Rectangle(0,9*h/16,w,h/16);
	
	private Rectangle emailBounds = new Rectangle(w/8, h/2, 3*w/4, h/16);
	private Rectangle passBounds = new Rectangle(w/8,h/2-h/12,3*w/4,h/16);
	private Rectangle loginBounds = new Rectangle(w/8, h/3, 9*w/16, h/16);
	private Rectangle signUpBounds = new Rectangle(w/8, 4*h/16, 3*w/4, h/16);
	private Rectangle rememberBounds = new Rectangle(3*w/4,h/3,w/8,h/16);
	private Rectangle forgotBounds = new Rectangle(w/8,13*h/64,3*w/4,h/32);
	
	private Keyboard keyBoard;
	
	private TextFieldStyle style;
	private TextField email;
	private TextField pass;
	
	private String e="",p="";
	
	private int selected = 0;
	
	private TextButtonStyle buttonStyle;
	private TextButtonStyle plainButtonStyle;
	private TextButton login;
	private TextButton signUp;
	private TextButton forgot;
	
	private CheckBoxStyle checkStyle;
	private CheckBox remember;
	
	public LoginScreen(uParty u){
		this.u=u;
		keyBoard = new Keyboard(u,this);
		
		/** Text Field Setup */
		
		style = new TextFieldStyle();
			style.font=u.assets.medium;
			style.fontColor=u.assets.orange;
			style.background=u.assets.textStyleBorder;
		email = new TextField(e,style);
			email.setMessageText("Email (must be .edu)");
			email.setBounds(emailBounds.x,emailBounds.y,emailBounds.width,emailBounds.height);
			email.setAlignment(Align.center);
			
		pass = new TextField(p,style);
			pass.setMessageText("Password");
			pass.setBounds(passBounds.x,passBounds.y,passBounds.width,passBounds.height);
			pass.setPasswordMode(true);
			pass.setPasswordCharacter('*');
			pass.setAlignment(Align.center);
			
		/** Button setup */
		buttonStyle = new TextButtonStyle();
			buttonStyle.font=u.assets.medium;
			buttonStyle.fontColor=u.assets.dark;
			buttonStyle.up=u.assets.buttonStyleUp;
			buttonStyle.down=u.assets.buttonStyleDown;
			buttonStyle.checked=u.assets.buttonStyleDown;
		login = new TextButton("Login",buttonStyle);
			login.setBounds(loginBounds.x,loginBounds.y,loginBounds.width,loginBounds.height);
		signUp = new TextButton("Sign Up",buttonStyle);
			signUp.setBounds(signUpBounds.x,signUpBounds.y,signUpBounds.width,signUpBounds.height);
		
		plainButtonStyle = new TextButtonStyle();
			plainButtonStyle.font=u.assets.medium;
			plainButtonStyle.fontColor=Color.WHITE;
			plainButtonStyle.checkedFontColor=Color.GRAY;
		forgot = new TextButton("Forgot Password?",plainButtonStyle);
			forgot.setBounds(forgotBounds.x, forgotBounds.y, forgotBounds.width, forgotBounds.height);
			
		errorStyle = new TextFieldStyle();
			errorStyle.font = u.assets.medium;
			errorStyle.fontColor=Color.RED;
			err = new TextField(error,errorStyle);err.setBounds(errB.x,errB.y,errB.width,errB.height);err.setAlignment(Align.center);
			
		/** CheckBox setup */
		checkStyle = new CheckBoxStyle();
			checkStyle.up=u.assets.checkBoxStyleOff;
			checkStyle.down=u.assets.checkBoxStyleOn;
			checkStyle.checked=u.assets.checkBoxStyleOn;
			checkStyle.font=u.assets.small;
			checkStyle.fontColor=Color.WHITE;
		remember = new CheckBox("Remember", checkStyle);
			remember.align(Align.center);
			remember.setBounds(rememberBounds.x,rememberBounds.y,rememberBounds.width,rememberBounds.height);
			
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		//u.assets.myProfile=null;
		
	}

	@Override
	public void render(float delta) {
		
		
		
		if(u.assets.myProfile!=null){
			if(u.assets.myProfile.verr){u.setScreen(new MainScreen(u));}
			else{u.setScreen(new ConfirmationScreen(u));}
		}
		
		
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		pic.begin();
		pic.draw(u.assets.logo1024,w/3,h-(w/2),w/3,w/3);
		
		u.assets.large.setColor(u.assets.orange);
		layout.setText(u.assets.large, intro.substring(0, s));
		u.assets.large.draw(pic, intro.substring(0, s), w/2-layout.width/2, 2*h/3);
		
		if(s<intro.length()){s++;}
		
		try {
			Thread.sleep(75);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		email.draw(pic, 1);
		pass.draw(pic, 1);
		login.draw(pic, 1);
		signUp.draw(pic, 1);
		remember.draw(pic, 1);
		forgot.draw(pic, 1);
		err.draw(pic, 1);
		pic.end();
		
		if(keyBoard.isVisible||keyBoard.theta!=0){keyBoard.render();}
		
		if(u.updateReq){
			u.setScreen(new VersionUpdateScreen(u));
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		//keyTyped('\n');
		System.out.println("key up");
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		System.out.println("Char: " + character);
		/**email*/
		if(selected==1){if(character == ''&&e.length()>0){
			e=e.substring(0, e.length()-1);
		}
		else if((character == '\r' || character == '\n')){}
		else{
			e+=character;
		}}
		/**password*/
		if(selected==2){if(character == ''&&p.length()>0){
			p=p.substring(0, p.length()-1);
		}
		else if((character == '\r' || character == '\n')){}
		else{
			p+=character;
		}}
		
		
		e=e.replaceAll("\\p{Cntrl}","");
		p=p.replaceAll("\\p{Cntrl}","");
		email.setText(e);
		pass.setText(p);
		
		//Gdx.input.setOnscreenKeyboardVisible(false);
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY=(int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		
		keyBoard.touchDown(touch);
		
		if(Intersector.overlaps(touch, loginBounds)){login.toggle();}
		else if(Intersector.overlaps(touch, signUpBounds)){signUp.toggle();}
		else if(Intersector.overlaps(touch, forgotBounds)){forgot.toggle();}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		//Gdx.input.setOnscreenKeyboardVisible(false);
		
		
		screenY=(int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		if(!Intersector.overlaps(touch, keyBoard.bounds)){selected = 0;keyBoard.isVisible = false;}
		else{
			keyBoard.touchUp(touch);
		}
		if(!keyBoard.isVisible){
		if(Intersector.overlaps(touch, emailBounds)){selected = 1;keyBoard.isVisible = true;}
		else if(Intersector.overlaps(touch, passBounds)){selected = 2;keyBoard.isVisible = true;}
		else if(Intersector.overlaps(touch, loginBounds)){login.toggle();Login l = new Login();l.email=e;l.pass=p;l.version=u.version;u.send(l);
			if(remember.isChecked()){
				System.out.println("Save attempt");
				FileHandle file = Gdx.files.local("uPartyRemember.txt");
				file.writeString(e+":"+p, false);
			}
		}
		else if(Intersector.overlaps(touch, signUpBounds)){signUp.toggle();u.setScreen(new SignUpScreen(u));}
		else if(Intersector.overlaps(touch, rememberBounds)){remember.toggle();}
		else if(Intersector.overlaps(touch, forgotBounds)){forgot.toggle();u.send(new Forgot(e));}
		}
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
