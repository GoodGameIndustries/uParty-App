/**
 * 
 */
package com.GGI.uParty.Objects;

import java.util.ArrayList;

import com.GGI.uParty.uParty;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import sun.security.validator.KeyStores;

/**
 * @author Emmett
 *
 */
public class Keyboard {

	public SpriteBatch pic;
	public ShapeRenderer shape;
	public float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
	public boolean isVisible = false;
	public Rectangle bounds = new Rectangle(0,0,w,h/3);
	public TextButtonStyle buttonStyle;
	private uParty u;
	private InputProcessor inp;
	private ArrayList<Key> keys = new ArrayList<Key>();
	private boolean isShifted = false;
	public double theta = 0;
	
	/**This class represents the uParty keyboard*/
	public Keyboard(uParty u, InputProcessor inp){
		this.u=u;
		this.inp=inp;
		
		pic = new SpriteBatch();
		shape = new ShapeRenderer();
		
		buttonStyle = new TextButtonStyle();
			buttonStyle.font=u.assets.medium;
			buttonStyle.fontColor=u.assets.dark;
			buttonStyle.up=u.assets.keyStyleUp;
			buttonStyle.down=u.assets.keyStyleDown;
			buttonStyle.checked=u.assets.keyStyleDown;
		
		initKeys();
	}
	
	/**creates all the needed keys and sets their bounds*/
	public void initKeys(){
			keys.add(new Key("1","1",this));
			keys.get(keys.size()-1).setBounds(0, .825f*bounds.height, w/10, .15f*bounds.height);
		
			keys.add(new Key("2","2",this));
			keys.get(keys.size()-1).setBounds(.1f*w, .825f*bounds.height, w/10, .15f*bounds.height);
			
			keys.add(new Key("3","3",this));
			keys.get(keys.size()-1).setBounds(.2f*w, .825f*bounds.height, w/10, .15f*bounds.height);
			
			keys.add(new Key("4","4",this));
			keys.get(keys.size()-1).setBounds(.3f*w, .825f*bounds.height, w/10, .15f*bounds.height);
			
			keys.add(new Key("5","5",this));
			keys.get(keys.size()-1).setBounds(.4f*w, .825f*bounds.height, w/10, .15f*bounds.height);
			
			keys.add(new Key("6","6",this));
			keys.get(keys.size()-1).setBounds(.5f*w, .825f*bounds.height, w/10, .15f*bounds.height);
			
			keys.add(new Key("7","7",this));
			keys.get(keys.size()-1).setBounds(.6f*w, .825f*bounds.height, w/10, .15f*bounds.height);
			
			keys.add(new Key("8","8",this));
			keys.get(keys.size()-1).setBounds(.7f*w, .825f*bounds.height, w/10, .15f*bounds.height);
			
			keys.add(new Key("9","9",this));
			keys.get(keys.size()-1).setBounds(.8f*w, .825f*bounds.height, w/10, .15f*bounds.height);
			
			keys.add(new Key("0","0",this));
			keys.get(keys.size()-1).setBounds(.9f*w, .825f*bounds.height, w/10, .15f*bounds.height);
			
			keys.add(new Key("q","Q",this));
			keys.get(keys.size()-1).setBounds(0, .6f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("w","W",this));
			keys.get(keys.size()-1).setBounds(.1f*w, .6f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("e","E",this));
			keys.get(keys.size()-1).setBounds(.2f*w, .6f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("r","R",this));
			keys.get(keys.size()-1).setBounds(.3f*w, .6f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("t","T",this));
			keys.get(keys.size()-1).setBounds(.4f*w, .6f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("y","Y",this));
			keys.get(keys.size()-1).setBounds(.5f*w, .6f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("u","U",this));
			keys.get(keys.size()-1).setBounds(.6f*w, .6f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("i","I",this));
			keys.get(keys.size()-1).setBounds(.7f*w, .6f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("o","O",this));
			keys.get(keys.size()-1).setBounds(.8f*w, .6f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("p","P",this));
			keys.get(keys.size()-1).setBounds(.9f*w, .6f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("a","A",this));
			keys.get(keys.size()-1).setBounds(.05f*w, .4f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("s","S",this));
			keys.get(keys.size()-1).setBounds(.15f*w, .4f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("d","D",this));
			keys.get(keys.size()-1).setBounds(.25f*w, .4f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("f","F",this));
			keys.get(keys.size()-1).setBounds(.35f*w, .4f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("g","G",this));
			keys.get(keys.size()-1).setBounds(.45f*w, .4f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("h","H",this));
			keys.get(keys.size()-1).setBounds(.55f*w, .4f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("j","J",this));
			keys.get(keys.size()-1).setBounds(.65f*w, .4f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("k","K",this));
			keys.get(keys.size()-1).setBounds(.75f*w, .4f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("l","L",this));
			keys.get(keys.size()-1).setBounds(.85f*w, .4f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("Shift","Shift",this));
			keys.get(keys.size()-1).setBounds(0, .2f*bounds.height, .15f*w, .2f*bounds.height);
			
			keys.add(new Key("z","Z",this));
			keys.get(keys.size()-1).setBounds(.15f*w, .2f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("x","X",this));
			keys.get(keys.size()-1).setBounds(.25f*w, .2f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("c","C",this));
			keys.get(keys.size()-1).setBounds(.35f*w, .2f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("v","V",this));
			keys.get(keys.size()-1).setBounds(.45f*w, .2f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("b","B",this));
			keys.get(keys.size()-1).setBounds(.55f*w, .2f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("n","N",this));
			keys.get(keys.size()-1).setBounds(.65f*w, .2f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("m","M",this));
			keys.get(keys.size()-1).setBounds(.75f*w, .2f*bounds.height, w/10, .2f*bounds.height);
			
			keys.add(new Key("Back","Back",this));
			keys.get(keys.size()-1).setBounds(.85f*w, .2f*bounds.height, .15f*w, .2f*bounds.height);
			
			keys.add(new Key("@","@",this));
			keys.get(keys.size()-1).setBounds(0, 0, w/10, .2f*bounds.height);
			
			keys.add(new Key("-","-",this));
			keys.get(keys.size()-1).setBounds(.1f*w, 0, w/10, .2f*bounds.height);
			
			keys.add(new Key("_","_",this));
			keys.get(keys.size()-1).setBounds(.2f*w, 0, w/10, .2f*bounds.height);
			
			keys.add(new Key(".",".",this));
			keys.get(keys.size()-1).setBounds(.7f*w, 0, w/10, .2f*bounds.height);
			
			keys.add(new Key("Enter","Enter",this));
			keys.get(keys.size()-1).setBounds(.8f*w, 0, .2f*w, .2f*bounds.height);
			
			keys.add(new Key("Space","Space",this));
			keys.get(keys.size()-1).setBounds(.3f*w, 0, .4f*w, .2f*bounds.height);
			
	}
	
	/**If the keyboard is touched it resolves what to do based on where
	 * it is pressed
	 * @param touch
	 */
	public void touchDown(Rectangle touch){
		for(int i = 0; i<keys.size();i++){
			if(Intersector.overlaps(touch, keys.get(i).bounds)){
				keys.get(i).setChecked(true);
				break;
			}
		}
	}
	
	/**If the keyboard is released what to do based on where 
	 * it was touched
	 * @param touch
	 */
	public void touchUp(Rectangle touch){
		for(int i = 0; i<keys.size();i++){
				keys.get(i).setChecked(false);
		}
		for(int i = 0; i<keys.size();i++){
			if(Intersector.overlaps(touch, keys.get(i).bounds)){
				if(keys.get(i).getText().length()>1){
					String s = keys.get(i).getText().toString();
					if(s.equals("Shift")){shift();}
					else if(s.equals("Back")){inp.keyTyped('');}
					else if(s.equals("Enter")){isVisible=false;}
					else if(s.equals("Space")){inp.keyTyped(' ');}
					
				}
				else{
					System.out.println("A key was typed");
					inp.keyTyped(keys.get(i).getText().charAt(0));
					if(!keys.get(i).getText().equals("Shift")&&isShifted){
						shift();
					}
				}
				break;
			}
		}
	}
	
	/**If the shift key is pressed this toggles the shift boolean
	 * and makes sure all the individual keys know that the shift 
	 * key is pressed
	 */
	private void shift() {
		isShifted = !isShifted;
		for(int i = 0; i < keys.size(); i++){
			keys.get(i).shift(isShifted);
		}
		
	}

	/**This method takes care of rendering the keyboard onto the screen.
	 * It Renders the background then each key on top.
	 */
	public void render(){
		if(isVisible&&theta<Math.PI/2){theta+=Math.PI/4;}
		if(!isVisible&&theta>0){theta-=Math.PI/4;}
		
		shape.begin(ShapeType.Filled);
		shape.setColor(new Color(.7f,.7f,.7f,1));
			shape.rect(bounds.x,bounds.y,bounds.width,(float) (bounds.height*Math.sin(theta)));
		shape.end();
		
		
		pic.begin();
		
		
		if(theta==Math.PI/2){
			//pic.draw(u.assets.logo1024, 9*w/20, (float) (0), w/10,w/10);
			for(int i = 0; i < keys.size(); i++){
				keys.get(i).draw(pic, 1);
			}
		}
		pic.end();
	}
	
}
