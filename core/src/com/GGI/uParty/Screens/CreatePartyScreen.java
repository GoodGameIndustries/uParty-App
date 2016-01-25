package com.GGI.uParty.Screens;

import java.time.LocalDate;
import java.util.Date;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Network.CreateParty;
import com.GGI.uParty.Network.Party;
import com.GGI.uParty.Objects.Keyboard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Align;

public class CreatePartyScreen implements Screen,InputProcessor{
	public uParty u;
	private SpriteBatch pic = new SpriteBatch();
	private float w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	private GlyphLayout layout = new GlyphLayout();
	private int select = 0;
	
	private Rectangle nameB = new Rectangle(w/4,13*h/16,w/2,h/16);
	private Rectangle hourB = new Rectangle(4*w/16,23*h/32,w/8,h/16);
	private Rectangle minB = new Rectangle(7*w/16,23*h/32,w/8,h/16);
	private Rectangle descriptionB = new Rectangle(w/8,18*h/32,3*w/4,h/8);
	private Rectangle whereB = new Rectangle(w/8,13*h/32,3*w/4,h/8);
	private Rectangle tSwitchB = new Rectangle(10*w/16,23*h/32,w/8,h/16);
	private Rectangle createPartyB = new Rectangle(w/8,10*h/32,3*w/4,h/16);
	private Rectangle backBounds = new Rectangle(h/64,61*h/64,h/32,h/32);
	
	private TextButtonStyle buttonStyle;
	private TextButtonStyle plainButtonStyle;
	private TextButton createParty;
	private TextButton tSwitch;
	private TextButton back;
	private boolean isPm=false;
	
	private TextFieldStyle style;
	private TextFieldStyle styleS;
	private TextField name;
	private TextField hour;
	private TextField min;
	private TextArea description;
	private TextArea where;
	
	private Keyboard keyBoard;
	
	
	public String n="",d="",hr="",m="",wr="";
	
	public CreatePartyScreen(uParty u) {
		this.u=u;
		
		keyBoard = new Keyboard(u,this);
		
		/** Text Field Setup */
		style = new TextFieldStyle();
			style.font=u.assets.medium;
			style.fontColor=u.assets.orange;
			style.background=u.assets.textStyleBorder;
		styleS = new TextFieldStyle();
			styleS.font=u.assets.small;
			styleS.fontColor=u.assets.orange;
			styleS.background=u.assets.textStyleBorder;
		name = new TextField(n,style);
			name.setBounds(nameB.x, nameB.y, nameB.width, nameB.height);
			name.setMessageText("Event Name");
			name.setAlignment(Align.center);
		hour = new TextField(hr,style);
			hour.setBounds(hourB.x, hourB.y, hourB.width, hourB.height);
			hour.setMessageText("HH");
			hour.setAlignment(Align.center);
		min = new TextField(m,style);
			min.setBounds(minB.x, minB.y, minB.width, minB.height);
			min.setMessageText("MM");
			min.setAlignment(Align.center);
		description = new TextArea(d,style);
			description.setBounds(descriptionB.x, descriptionB.y, descriptionB.width, descriptionB.height);
			description.setMessageText("\nEvent Description");
			description.setAlignment(Align.center);
		where = new TextArea(wr,style);
			where.setBounds(whereB.x, whereB.y, whereB.width, whereB.height);
			where.setMessageText("\nEvent Location");
			where.setAlignment(Align.center);
		
		
			
		/** Button setup */
		buttonStyle = new TextButtonStyle();
			buttonStyle.font=u.assets.medium;
			buttonStyle.fontColor=u.assets.dark;
			buttonStyle.up=u.assets.buttonStyleUp;
			buttonStyle.down=u.assets.buttonStyleDown;
			buttonStyle.checked=u.assets.buttonStyleDown;
		tSwitch = new TextButton("AM",buttonStyle);
			tSwitch.setBounds(tSwitchB.x, tSwitchB.y, tSwitchB.width, tSwitchB.height);
		createParty = new TextButton("Create Event",buttonStyle);
			createParty.setBounds(createPartyB.x, createPartyB.y, createPartyB.width, createPartyB.height);
	
		plainButtonStyle = new TextButtonStyle();
			plainButtonStyle.font=u.assets.large;
			plainButtonStyle.fontColor = u.assets.orange;
			plainButtonStyle.checkedFontColor = Color.GRAY;
		back = new TextButton("<",plainButtonStyle);
			back.setBounds(backBounds.x,backBounds.y,backBounds.width,backBounds.height);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(isPm){tSwitch.setText("PM");}else{tSwitch.setText("AM");}
		
		pic.begin();
		name.draw(pic, 1);
		hour.draw(pic, 1);
		min.draw(pic, 1);
		description.draw(pic, 1);
		where.draw(pic, 1);
		tSwitch.draw(pic, 1);
		createParty.draw(pic,1);
		back.draw(pic, 1);
		pic.end();
		
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
		System.out.println("Char: "+character+":"+select);
		/**Name*/
		if(select==1){if(character == ''&&n.length()>0){
			n=n.substring(0, n.length()-1);
		}
		else if((character == '\r' || character == '\n')){}
		else{
			n+=character;
		}}
		
		/**Hour*/
		if(select==2){if(character == ''&&hr.length()>0){
			hr=hr.substring(0, hr.length()-1);
		}
		else if((character == '\r' || character == '\n' || Character.isLetter(character) || hr.length()>=2 )){}
		else{
			hr+=character;
		}}
		
		/**Min*/
		if(select==3){if(character == ''&&m.length()>0){
			m=m.substring(0, m.length()-1);
		}
		else if((character == '\r' || character == '\n'|| Character.isLetter(character) || m.length()>=2 )){}
		else{
			m+=character;
		}}
		
		/**description*/
		if(select==4){if(character == ''&&d.length()>0){
			d=d.substring(0, d.length()-1);
		}
		else if((character == '\r' || character == '\n')||d.length()>105){}
		else{
			d+=character;
		}}
		
		/**where*/
		if(select==5){if(character == ''&&wr.length()>0){
			wr=wr.substring(0, wr.length()-1);
		}
		else if((character == '\r' || character == '\n')||wr.length()>105){}
		else{
			wr+=character;
		}}
		
		n=n.replaceAll("\\p{Cntrl}","");
		m=m.replaceAll("\\p{Cntrl}","");
		hr=hr.replaceAll("\\p{Cntrl}","");
		
		d=d.replace(" ", "$space$");
		d=d.replaceAll("\\p{Cntrl}","");
		d=d.replace("$space$", " ");
		String dT = "\n   ";
		String[] dBreak = d.split(" ");
		String d1="",d2="",d3="";
		for(int i = 0; i < dBreak.length;i++){
			if(d1.length()<35){d1+=dBreak[i]+" ";}
			else if(d2.length()<35){d2+=dBreak[i]+" ";}
			else{d3+=dBreak[i]+" ";}
		}
		dT+=d1+"\n   "+d2+"\n   "+d3;
		
		
		wr=wr.replace(" ", "$space$");
		wr=wr.replaceAll("\\p{Cntrl}","");
		wr=wr.replace("$space$", " ");
		
		String wrT = "\n   ";
		String[] wrBreak = wr.split(" ");
		String wr1="",wr2="",wr3="";
		for(int i = 0; i < wrBreak.length;i++){
			if(wr1.length()<35){wr1+=wrBreak[i]+" ";}
			else if(wr2.length()<35){wr2+=wrBreak[i]+" ";}
			else{wr3+=wrBreak[i]+" ";}
		}
		wrT+=wr1+"\n   "+wr2+"\n   "+wr3;
		
		name.setText(n);
		hour.setText(hr);
		min.setText(m);
		if(d.length()>0){description.setText(dT);}else{description.setText("");}
		if(wr.length()>0){where.setText(wrT);}else{where.setText("");}
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		
		
		screenY = (int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		keyBoard.touchDown(touch);
		if(Intersector.overlaps(touch, tSwitchB)){tSwitch.toggle();}
		else if(Intersector.overlaps(touch,createPartyB)){createParty.toggle();}
		else if (Intersector.overlaps(touch, backBounds)){back.toggle();}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY = (int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		
		if(!Intersector.overlaps(touch, keyBoard.bounds)){select = 0;keyBoard.isVisible = false;}
		else{
			keyBoard.touchUp(touch);
		}
		
	
		if(Intersector.overlaps(touch, tSwitchB)){tSwitch.toggle();isPm=!isPm;}
		else if (Intersector.overlaps(touch, backBounds)){back.toggle();u.setScreen(new MainScreen(u));}
		else if(Intersector.overlaps(touch,createPartyB)){createParty.toggle();
			if(n.length()>0&&hr.length()>0&&m.length()>0&&d.length()>0&&wr.length()>0){
				Party p = new Party();
				p.name=n;
				p.where=wr;
				p.description=d;
				p.d=new Date();
				p.d.setHours(!isPm?Integer.parseInt(hr):Integer.parseInt(hr)+12);
				p.d.setMinutes(Integer.parseInt(m));
				p.d.setSeconds(0);
				p.owner=u.assets.myProfile;
				
				CreateParty cp = new CreateParty();
				cp.p=p;
				
				u.send(cp);
				u.setScreen(new MainScreen(u));
			}
		}
		
		if(Intersector.overlaps(touch,nameB)){select = 1;keyBoard.isVisible=(true);}
		if(Intersector.overlaps(touch,hourB)){select = 2;keyBoard.isVisible=(true);}
		if(Intersector.overlaps(touch,minB)){select = 3;keyBoard.isVisible=(true);}
		if(Intersector.overlaps(touch,descriptionB)){select = 4;keyBoard.isVisible=(true);}
		if(Intersector.overlaps(touch,whereB)){select = 5;keyBoard.isVisible=(true);}
		
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
