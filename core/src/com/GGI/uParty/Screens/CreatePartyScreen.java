package com.GGI.uParty.Screens;

import java.util.ArrayList;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	private Rectangle startDB = new Rectangle(w/4,23*h/32,5*w/16,h/16);
	private Rectangle endDB = new Rectangle(w/4,20*h/32,5*w/16,h/16);
	
	private Rectangle descriptionB = new Rectangle(w/8,15*h/32,3*w/4,h/8);
	private Rectangle whereB = new Rectangle(w/8,10*h/32,3*w/4,h/8);
	private Rectangle tSwitch1B = new Rectangle(10*w/16,23*h/32,w/8,h/16);
	private Rectangle tSwitch2B = new Rectangle(10*w/16,20*h/32,w/8,h/16);
	private Rectangle createPartyB = new Rectangle(w/8,7*h/32,3*w/4,h/16);
	private Rectangle backBounds = new Rectangle(h/64,61*h/64,h/32,h/32);
	
	private TextButtonStyle buttonStyle;
	private TextButtonStyle plainButtonStyle;
	private TextButton createParty;
	private TextButton tSwitch1;
	private TextButton tSwitch2;
	private TextButton back;
	private boolean isPm1=false;
	private boolean isPm2=false;
	
	private TextFieldStyle style;
	private TextFieldStyle styleS;
	private TextField name;
	//private TextField hour;
	//private TextField min;
	
	private TextField startDate;
	private TextField endDate;
	
	private TextArea description;
	private TextArea where;
	
	private String startD="";
	private String endD="";
	
	private Keyboard keyBoard;
	
	
	public String n="",d="",hr="",m="",wr="";
	
	public Stage stage;
	
	public CreatePartyScreen(uParty u) {
		this.u=u;
		
		keyBoard = new Keyboard(u,this);
		
		/** Text Field Setup */
		style = new TextFieldStyle();
			style.font=u.assets.medium;
			style.fontColor=u.assets.orange;
			style.background=u.assets.textStyleBorder;
			style.focusedBackground=u.assets.focusTextStyleBorder;
		styleS = new TextFieldStyle();
			styleS.font=u.assets.small;
			styleS.fontColor=u.assets.orange;
			styleS.background=u.assets.textStyleBorder;
			styleS.focusedBackground=u.assets.focusTextStyleBorder;
		name = new TextField(n,style);
			name.setBounds(nameB.x, nameB.y, nameB.width, nameB.height);
			name.setMessageText("Event Name");
			name.setAlignment(Align.center);
		startDate = new TextField("",style);
			startDate.setBounds(startDB.x, startDB.y, startDB.width, startDB.height);
			startDate.setMessageText("Start Date");
			startDate.setAlignment(Align.center);
		endDate = new TextField("",style);
			endDate.setBounds(endDB.x, endDB.y, endDB.width, endDB.height);
			endDate.setMessageText("End Date");
			endDate.setAlignment(Align.center);
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
		tSwitch1 = new TextButton("AM",buttonStyle);
			tSwitch1.setBounds(tSwitch1B.x, tSwitch1B.y, tSwitch1B.width, tSwitch1B.height);
		tSwitch2 = new TextButton("AM",buttonStyle);
			tSwitch2.setBounds(tSwitch2B.x, tSwitch2B.y, tSwitch2B.width, tSwitch2B.height);
		createParty = new TextButton("Create Event",buttonStyle);
			createParty.setBounds(createPartyB.x, createPartyB.y, createPartyB.width, createPartyB.height);
	
		plainButtonStyle = new TextButtonStyle();
			plainButtonStyle.font=u.assets.large;
			plainButtonStyle.fontColor = u.assets.orange;
			plainButtonStyle.checkedFontColor = Color.GRAY;
		back = new TextButton("<",plainButtonStyle);
			back.setBounds(backBounds.x,backBounds.y,backBounds.width,backBounds.height);
			
		stage = new Stage();
		stage.addActor(name);
		stage.addActor(startDate);
		stage.addActor(endDate);
		stage.addActor(description);
		stage.addActor(where);
		stage.addActor(tSwitch1);
		stage.addActor(tSwitch2);
		stage.addActor(createParty);
		stage.addActor(back);
	}

	private String formatDate(String str) {
		String result = "MM/DD/YY hh:mm";
		ArrayList<String> segment = new ArrayList<String>();
		while(str.length()>0){
			if(str.length()>2){
				segment.add(str.substring(0,2));
				str=str.substring(2);
			}
			else{
				segment.add(str.substring(0,str.length()));
				str="";
			}
		}
		switch(segment.size()){
		case 0: result=result;break;
		case 1: result=segment.get(0)+"/dd/yy hh:mm";break;
		case 2: result=segment.get(0)+"/"+segment.get(1)+"/yy hh:mm";break;
		case 3: result=segment.get(0)+"/"+segment.get(1)+"/"+segment.get(2)+" hh:mm";break;
		case 4: result=segment.get(0)+"/"+segment.get(1)+"/"+segment.get(2)+" "+segment.get(3)+":mm";break;
		case 5: result=segment.get(0)+"/"+segment.get(1)+"/"+segment.get(2)+" "+segment.get(3)+":"+segment.get(4);break;
		}
		
		return result;
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(isPm1){tSwitch1.setText("PM");}else{tSwitch1.setText("AM");}
		if(isPm2){tSwitch2.setText("PM");}else{tSwitch2.setText("AM");}
	
		pic.begin();
		//stage.draw();
		name.draw(pic, 1);
		description.draw(pic, 1);
		where.draw(pic, 1);
		tSwitch1.draw(pic, 1);
		tSwitch2.draw(pic, 1);
		createParty.draw(pic, 1);
		back.draw(pic,1);
		startDate.draw(pic, 1);
		endDate.draw(pic, 1);
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
		
		/**startDate*/
		if(select==2){if(character == ''&&startD.length()>0){
			startD=startD.substring(0, startD.length()-1);
		}
		else if((character == '\r' || character == '\n' || startD.length()>=10 )){}
		else{
			startD+=character;
		}}
		
		/**endDate*/
		if(select==3){if(character == ''&&endD.length()>0){
			endD=endD.substring(0, endD.length()-1);
		}
		else if((character == '\r' || character == '\n'|| endD.length()>=10 )){}
		else{
			endD+=character;
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
		startD=startD.replaceAll("\\p{Cntrl}","");
		endD=endD.replaceAll("\\p{Cntrl}","");
		
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
		startDate.setText(startD.length()>0?formatDate(startD):"");
		endDate.setText(endD.length()>0?formatDate(endD):"");
		if(d.length()>0){description.setText(dT);}else{description.setText("");}
		if(wr.length()>0){where.setText(wrT);}else{where.setText("");}
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		
		
		screenY = (int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		keyBoard.touchDown(touch);
		if(Intersector.overlaps(touch, tSwitch1B)){tSwitch1.toggle();}
		else if(Intersector.overlaps(touch, tSwitch2B)){tSwitch2.toggle();}
		else if(Intersector.overlaps(touch,createPartyB)){createParty.toggle();}
		else if (Intersector.overlaps(touch, backBounds)){back.toggle();}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY = (int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		
		if(!Intersector.overlaps(touch, keyBoard.bounds)){select = 0;keyBoard.isVisible = false;stage.setKeyboardFocus(null);}
		else{
			keyBoard.touchUp(touch);
		}
		
	
		if(Intersector.overlaps(touch, tSwitch1B)){tSwitch1.toggle();isPm1=!isPm1;}
		else if(Intersector.overlaps(touch, tSwitch2B)){tSwitch2.toggle();isPm2=!isPm2;}
		else if (Intersector.overlaps(touch, backBounds)){back.toggle();u.setScreen(new MainScreen(u));}
		else if(Intersector.overlaps(touch,createPartyB)){createParty.toggle();
			if(n.length()>0&&startD.length()==10&&endD.length()==10&&d.length()>0&&wr.length()>0){
				Party p = new Party();
				p.name=n;
				p.where=wr;
				p.description=d;
				p.startD=new Date();
				p.startD.setMonth(Integer.parseInt(startD.substring(0,2))-1);
				p.startD.setDate(Integer.parseInt(startD.substring(2,4)));
				p.startD.setYear(100+Integer.parseInt(startD.substring(4,6)));
				p.startD.setHours(!isPm1||Integer.parseInt(startD.substring(6,8))==12?Integer.parseInt(startD.substring(6,8)):Integer.parseInt(startD.substring(6,8))+12);
				p.startD.setMinutes(Integer.parseInt(startD.substring(8)));
				p.startD.setSeconds(0);
				
				p.endD=new Date();
				p.endD.setMonth(Integer.parseInt(endD.substring(0,2))-1);
				p.endD.setDate(Integer.parseInt(endD.substring(2,4)));
				p.endD.setYear(100+Integer.parseInt(endD.substring(4,6)));
				p.endD.setHours(!isPm2||Integer.parseInt(endD.substring(6,8))==12?Integer.parseInt(endD.substring(6,8)):Integer.parseInt(endD.substring(6,8))+12);
				p.endD.setMinutes(Integer.parseInt(endD.substring(8)));
				p.endD.setSeconds(0);
				p.owner=u.assets.myProfile;
				
				CreateParty cp = new CreateParty();
				cp.p=p;
				
				if(p.endD.getTime()>=p.startD.getTime()){
				u.send(cp);
				u.setScreen(new MainScreen(u));
				}
			}
		}
		
		if(Intersector.overlaps(touch,nameB)){select = 1;keyBoard.isVisible=(true);stage.setKeyboardFocus(name);}
		if(Intersector.overlaps(touch,startDB)){select = 2;keyBoard.isVisible=(true);stage.setKeyboardFocus(startDate);}
		if(Intersector.overlaps(touch,endDB)){select = 3;keyBoard.isVisible=(true);stage.setKeyboardFocus(endDate);}
		if(Intersector.overlaps(touch,descriptionB)){select = 4;keyBoard.isVisible=(true);stage.setKeyboardFocus(description);}
		if(Intersector.overlaps(touch,whereB)){select = 5;keyBoard.isVisible=(true);stage.setKeyboardFocus(where);}
		
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
