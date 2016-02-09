package com.GGI.uParty.Screens;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Network.ResendConfirmation;
import com.GGI.uParty.Network.Verify;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Align;

public class ConfirmationScreen implements Screen, InputProcessor{

	public uParty u;
	private SpriteBatch pic = new SpriteBatch();
	private GlyphLayout layout = new GlyphLayout();
	private float w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	private int selected = 0;
	
	private TextFieldStyle style;
	private TextField confirmCode;
	
	private Rectangle codeBounds = new Rectangle(w/8, h/2, 3*w/4, h/16);
	private Rectangle resendBounds = new Rectangle(w/8, h/2-h/12, 3*w/4, h/16);
	private Rectangle contBounds = new Rectangle(w/8, h/3, 3*w/4, h/16);
	
	private String code = "";
	private String message = "";
	
	private TextButtonStyle buttonStyle;
	private TextButton resend;
	private TextButton cont;
	
	private Keyboard keyBoard;
	
	/**This screen allows you to type in your confirmation code
	 * that allows us to make sure it is a real email
	 * @param u
	 */
	public ConfirmationScreen(uParty u){
		this.u=u;
		
		keyBoard = new Keyboard(u,this);
		
		/** Text Field Setup */
		style = new TextFieldStyle();
			style.font=u.assets.medium;
			style.fontColor=u.assets.orange;
			style.background=u.assets.textStyleBorder;
		confirmCode = new TextField(code,style);
			confirmCode.setMessageText("Confirmation Code");
			confirmCode.setBounds(codeBounds.x, codeBounds.y, codeBounds.width, codeBounds.height);
			confirmCode.setAlignment(Align.center);
			
		/** Button setup */
		buttonStyle = new TextButtonStyle();
			buttonStyle.font=u.assets.medium;
			buttonStyle.fontColor=u.assets.dark;
			buttonStyle.up=u.assets.buttonStyleUp;
			buttonStyle.down=u.assets.buttonStyleDown;
			buttonStyle.checked=u.assets.buttonStyleDown;
		resend = new TextButton("Resend Code",buttonStyle);
			resend.setBounds(resendBounds.x, resendBounds.y, resendBounds.width, resendBounds.height);
		cont = new TextButton("Continue",buttonStyle);
			cont.setBounds(contBounds.x, contBounds.y, contBounds.width, contBounds.height);
	}
	
	/**when the screen is first shown this method is called
	 * and does what is necessary for the screen
	 */
	@Override
	public void show() {
		Gdx.input.setOnscreenKeyboardVisible(false);
		Gdx.input.setInputProcessor(this);
		
	}

	/**this method is responsible for rendering the entire screen
	 *with its buttons
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		pic.begin();
		
		u.assets.large.setColor(u.assets.orange);
		layout.setText(u.assets.large, "We sent you an email!");
		u.assets.large.draw(pic, "We sent you an email!", w/2-layout.width/2, 2*h/3);
		
		u.assets.medium.setColor(Color.RED);
		layout.setText(u.assets.medium, message);
		u.assets.medium.draw(pic, message, w/2-layout.width/2, h/4);
		
		confirmCode.draw(pic, 1);
		resend.draw(pic, 1);
		cont.draw(pic, 1);
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
		System.out.println(character);
		System.out.println(selected);
		if(selected==1){if(character == ''&&code.length()>0){
			code=code.substring(0, code.length()-1);
		}
		else if((character == '\r' || character == '\n')){}
		else if(Character.isDigit(character)){
			code+=character;
		}}
		code=code.replaceAll("\\p{Cntrl}","");
		confirmCode.setText(code);
		
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY=(int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		
		if(Intersector.overlaps(touch, contBounds)){cont.toggle();}
		else if(Intersector.overlaps(touch, resendBounds)){resend.toggle();}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenY=(int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		
		//selected = 0;
		
		if(!Intersector.overlaps(touch, keyBoard.bounds)){selected = 0;keyBoard.isVisible = false;}
		else{
			keyBoard.touchUp(touch);
		}
		
		if(Intersector.overlaps(touch, contBounds)){cont.toggle();
			if(code!=null&&code.length()>0&&Integer.parseInt(code)==u.assets.myProfile.verrCode){u.assets.myProfile.verr=true;u.send(new Verify(u.assets.myProfile.email));u.setScreen(new MainScreen(u));}
			else{message="Wrong Code";}
			}
			
		else if(Intersector.overlaps(touch, resendBounds)){resend.toggle();message = "New code sent!";u.send(new ResendConfirmation(u.assets.myProfile.email));}
		else if(Intersector.overlaps(touch, codeBounds)){selected = 1;keyBoard.isVisible=true;}
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
