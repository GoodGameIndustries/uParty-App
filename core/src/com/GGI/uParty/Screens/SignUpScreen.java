package com.GGI.uParty.Screens;

import java.util.Date;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Network.Sendable;
import com.GGI.uParty.Network.SignUp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Align;

public class SignUpScreen implements Screen, InputProcessor{
	private uParty u;
	
	private GlyphLayout layout = new GlyphLayout();
	private SpriteBatch pic = new SpriteBatch();
	private float w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	
	private Rectangle nameB=new Rectangle(w/4,13*h/16,w/2,h/16);
	private Rectangle monthB=new Rectangle(w/4,23*h/32,w/8,h/16);
	private Rectangle dayB=new Rectangle(13*w/32,23*h/32,w/8,h/16);
	private Rectangle yearB=new Rectangle(18*w/32,23*h/32,3*w/16,h/16);
	private Rectangle emailB=new Rectangle(w/8,20*h/32,3*w/4,h/16);
	private Rectangle passB=new Rectangle(w/8,17*h/32,3*w/4,h/16);
	private Rectangle cpassB=new Rectangle(w/8,14*h/32,3*w/4,h/16);
	private Rectangle signUpBounds = new Rectangle(w/8, 5*h/16, 3*w/4, h/16);
	private Rectangle errB = new Rectangle(0,4*h/16,w,h/16);
	private Rectangle backBounds = new Rectangle(h/64,61*h/64,h/32,h/32);
	
	private TextFieldStyle style;
	private TextFieldStyle errorStyle;
	private TextField name;
	private TextField month;
	private TextField day;
	private TextField year;
	private TextField email;
	private TextField pass;
	private TextField cpass;
	public TextField err;
	
	private TextButtonStyle buttonStyle;
	private TextButtonStyle plainButtonStyle;
	private TextButton signUp;
	private TextButton back;
	
	
	private String n="",m="",d="",y="",e="",p="",cp="";
	public String error = "";
	
	private int selector = 0;
	
	public SignUpScreen(uParty u) {
		this.u=u;
		
		/** Text Field Setup */
		style = new TextFieldStyle();
		style.font=u.assets.medium;
		style.fontColor=u.assets.orange;
		style.background=u.assets.textStyleBorder;
		
		errorStyle = new TextFieldStyle();
		errorStyle.font = u.assets.medium;
		errorStyle.fontColor=Color.RED;
		
		name=new TextField(n,style);name.setMessageText("First Name");name.setBounds(nameB.x,nameB.y,nameB.width,nameB.height);name.setAlignment(Align.center);
		month=new TextField(m,style);month.setMessageText("MM");month.setBounds(monthB.x,monthB.y,monthB.width,monthB.height);month.setAlignment(Align.center);
		day=new TextField(d,style);day.setMessageText("DD");day.setBounds(dayB.x,dayB.y,dayB.width,dayB.height);day.setAlignment(Align.center);
		year=new TextField(y,style);year.setMessageText("YYYY");year.setBounds(yearB.x,yearB.y,yearB.width,yearB.height);year.setAlignment(Align.center);
		email=new TextField(e,style);email.setMessageText("Email (must be .edu)");email.setBounds(emailB.x,emailB.y,emailB.width,emailB.height);email.setAlignment(Align.center);
		pass=new TextField(p,style);pass.setMessageText("Password");pass.setBounds(passB.x,passB.y,passB.width,passB.height);pass.setAlignment(Align.center);pass.setPasswordMode(true);pass.setPasswordCharacter('*');
		cpass=new TextField(cp,style);cpass.setMessageText("Confirm Password");cpass.setBounds(cpassB.x,cpassB.y,cpassB.width,cpassB.height);cpass.setAlignment(Align.center);cpass.setPasswordMode(true);cpass.setPasswordCharacter('*');
		err = new TextField(error,errorStyle);err.setBounds(errB.x,errB.y,errB.width,errB.height);err.setAlignment(Align.center);
		
		/** Button setup */
		buttonStyle = new TextButtonStyle();
			buttonStyle.font=u.assets.medium;
			buttonStyle.fontColor=u.assets.dark;
			buttonStyle.up=u.assets.buttonStyleUp;
			buttonStyle.down=u.assets.buttonStyleDown;
			buttonStyle.checked=u.assets.buttonStyleDown;
		plainButtonStyle = new TextButtonStyle();
			plainButtonStyle.font=u.assets.large;
			plainButtonStyle.fontColor = u.assets.orange;
			plainButtonStyle.checkedFontColor = Color.GRAY;
		signUp = new TextButton("Sign Up",buttonStyle);
			signUp.setBounds(signUpBounds.x,signUpBounds.y,signUpBounds.width,signUpBounds.height);
		back = new TextButton("<",plainButtonStyle);
			back.setBounds(backBounds.x,backBounds.y,backBounds.width,backBounds.height);
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void render(float delta) {
		if(u.assets.myProfile!=null&&!u.assets.myProfile.verr){u.setScreen(new ConfirmationScreen(u));}
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		pic.begin();
		u.assets.large.setColor(u.assets.orange);
		layout.setText(u.assets.large, "Sign Up");
		u.assets.large.draw(pic, "Sign Up", w/2-layout.width/2, h-1.1f*layout.height);
		
		name.draw(pic, 1);
		month.draw(pic, 1);
		day.draw(pic, 1);
		year.draw(pic, 1);
		email.draw(pic, 1);
		pass.draw(pic, 1);
		cpass.draw(pic, 1);
		signUp.draw(pic, 1);
		err.draw(pic,1);
		back.draw(pic, 1);
		pic.end();
		
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
		/**Name*/
		if(selector==1){if(character == ''&&n.length()>0){
			n=n.substring(0, n.length()-1);
		}
		else if((character == '\r' || character == '\n')){}
		else{
			n+=character;
		}}
		/**Month*/
		else if(selector==2){if(character == ''&&m.length()>0){
			m=m.substring(0, m.length()-1);
		}
		else if((character == '\r' || character == '\n'|| Character.isLetter(character) || m.length()>=2)){}
		else{
			m+=character;
		}}
		/**Day*/
		else if(selector==3){if(character == ''&&d.length()>0){
			d=d.substring(0, d.length()-1);
		}
		else if((character == '\r' || character == '\n'|| Character.isLetter(character) || d.length()>=2)){}
		else{
			d+=character;
		}}
		/**Year*/
		else if(selector==4){if(character == ''&&y.length()>0){
			y=y.substring(0, y.length()-1);
		}
		else if((character == '\r' || character == '\n'|| Character.isLetter(character) || y.length()>=4)){}
		else{
			y+=character;
		}}
		/**Email*/
		if(selector==5){if(character == ''&&e.length()>0){
			e=e.substring(0, e.length()-1);
		}
		else if((character == '\r' || character == '\n')){}
		else{
			e+=character;
		}}
		/**Pass*/
		if(selector==6){if(character == ''&&p.length()>0){
			p=p.substring(0, p.length()-1);
		}
		else if((character == '\r' || character == '\n')){}
		else{
			p+=character;
		}}
		/**cPass*/
		if(selector==7){if(character == ''&&cp.length()>0){
			cp=cp.substring(0, cp.length()-1);
		}
		else if((character == '\r' || character == '\n')){}
		else{
			cp+=character;
		}}
		
		n=n.replaceAll("\\p{Cntrl}","");
		m=m.replaceAll("\\p{Cntrl}","");
		d=d.replaceAll("\\p{Cntrl}","");
		y=y.replaceAll("\\p{Cntrl}","");
		e=e.replaceAll("\\p{Cntrl}","");
		p=p.replaceAll("\\p{Cntrl}","");
		cp=cp.replaceAll("\\p{Cntrl}","");
		
		name.setText(n);
		month.setText(m);
		day.setText(d);
		year.setText(y);
		email.setText(e);
		pass.setText(p);
		cpass.setText(cp);
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY=(int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		if(Intersector.overlaps(touch, signUpBounds)){signUp.toggle();}
		else if (Intersector.overlaps(touch, backBounds)){back.toggle();}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Gdx.input.setOnscreenKeyboardVisible(false);
		screenY=(int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		
		if(Intersector.overlaps(touch, nameB)){selector = 1;Gdx.input.setOnscreenKeyboardVisible(true);}
		else if(Intersector.overlaps(touch, monthB)){selector = 2;Gdx.input.setOnscreenKeyboardVisible(true);}
		else if(Intersector.overlaps(touch, dayB)){selector = 3;Gdx.input.setOnscreenKeyboardVisible(true);}
		else if(Intersector.overlaps(touch, yearB)){selector = 4;Gdx.input.setOnscreenKeyboardVisible(true);}
		else if(Intersector.overlaps(touch, emailB)){selector = 5;Gdx.input.setOnscreenKeyboardVisible(true);}
		else if(Intersector.overlaps(touch, passB)){selector = 6;Gdx.input.setOnscreenKeyboardVisible(true);}
		else if(Intersector.overlaps(touch, cpassB)){selector = 7;Gdx.input.setOnscreenKeyboardVisible(true);}
		else if (Intersector.overlaps(touch, backBounds)){back.toggle();u.setScreen(new LoginScreen(u));}
		else if(Intersector.overlaps(touch, signUpBounds)){signUp.toggle();
			//u.send(new Sendable());//for testing only	
			if(n.length()>0&&m.length()>0&&d.length()>0&&y.length()>0&&e.length()>0&&p.length()>0&&cp.length()>0){
				
				Date bday = null;
				try{
					bday = new Date();
					bday.setMonth(Integer.parseInt(m));
					bday.setDate(Integer.parseInt(d));
					bday.setYear(Integer.parseInt(y));
					
				}catch(Exception e){
					error = "Invalid Date";
				}
				
				if(e.endsWith(".edu")&&p.equals(cp)){
					SignUp s = new SignUp();
					s.name=n;
					s.date = bday;
					s.email=e;
					s.pass=p;
					u.send(s);
				}
				else{
					error = "Invalid email or password";
				}
				
			}
			else{error = "Please fill all the fields";}
			err.setText(error);
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
