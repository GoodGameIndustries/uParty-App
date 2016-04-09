package com.GGI.uParty.Objects;

import com.GGI.uParty.uParty;
import com.GGI.uParty.Network.Party;
import com.GGI.uParty.Network.VoteDown;
import com.GGI.uParty.Network.VoteUp;
import com.GGI.uParty.Screens.PartyViewScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PartyModule implements Comparable{
	public uParty u;
	public SpriteBatch pic;
	public ShapeRenderer shape;
	
	private GlyphLayout layout = new GlyphLayout();
	
	private float w = Gdx.graphics.getWidth(),h = Gdx.graphics.getHeight();
	
	public Rectangle bounds;
	
	public Party p;
	
	public ImageButton voteUp;
	public ImageButton voteDown;
	
	public Rectangle voteUpB = new Rectangle();
	public Rectangle voteDownB = new Rectangle();
	public Rectangle titleB = new Rectangle();
	public Rectangle descriptionB = new Rectangle();
	public Rectangle whereB = new Rectangle();
	public Rectangle startB = new Rectangle();
	public Rectangle endB = new Rectangle();
	public Rectangle reportB = new Rectangle();
	
	public TextureRegion flippedUp,flippedDown;
	public boolean locationEnabled = true;
	private TextButtonStyle buttonStyle;
	private TextButton report;
	
	private LabelStyle styleLS;
	private LabelStyle styleLM;
	private Label title;
	private Label description;
	private Label where;
	private Label start;
	private Label end;
	

	/**This class is a visual representation of a given party
	 * It allows us to render the party and display its information
	 * @param u
	 * @param p
	 */
	public PartyModule(uParty u,Party p){
		this.u=u;
		this.p=p;
		
		/** Label setup*/
		styleLS = new LabelStyle();
			styleLS.font=u.assets.small;
			styleLS.fontColor=Color.WHITE;
		
		styleLM = new LabelStyle();
			styleLM.font=u.assets.medium;
			styleLM.fontColor=u.assets.orange;
		
		title = new Label(p.name,styleLM);
		start = new Label("Event Start: "+p.startD.toString(),styleLS);
		end = new Label("Event End: "+p.endD.toString(),styleLS);
		where = new Label("Location: "+p.where,styleLS);
			where.setWrap(p.where.length()>40?true:false);
		description = new Label("Description: "+p.description,styleLS);
			description.setWrap(p.description.length()>40?true:false);
			
			
		
		/** Button setup */
		buttonStyle = new TextButtonStyle();
			buttonStyle.font=u.assets.small;
			buttonStyle.fontColor=u.assets.dark;
			buttonStyle.up=u.assets.buttonStyleUp;
			buttonStyle.down=u.assets.buttonStyleDown;
			buttonStyle.checked=u.assets.buttonStyleDown;
		report = new TextButton("Report",buttonStyle);
			report.setBounds(reportB.x, reportB.y, reportB.width, reportB.height);
		
		bounds = new Rectangle();
		
		voteUp = new ImageButton(new TextureRegionDrawable(u.assets.voteButtonUp),new TextureRegionDrawable(u.assets.voteButtonDown),new TextureRegionDrawable(u.assets.voteButtonDown));
		
		flippedUp = new TextureRegion(u.assets.voteButtonUp);
		flippedUp.flip(false, true);
		flippedDown = new TextureRegion(u.assets.voteButtonDown);
		flippedDown.flip(false, true);
		
		voteDown = new ImageButton(new TextureRegionDrawable(flippedUp),new TextureRegionDrawable(flippedDown),new TextureRegionDrawable(flippedDown));
		
		voteUp.setBounds(voteUpB.x, voteUpB.y, voteUpB.width, voteUpB.height);
		voteDown.setBounds(voteDownB.x, voteDownB.y, voteDownB.width, voteDownB.height);
	}
	
	/**this method is responsible for the rendering of the module
	 * it displays all of the parties information in a relative manner
	 * so it will work on any screensize
	 */
	public void render(){
		if(pic==null){pic = new SpriteBatch();}
		if(shape==null){shape = new ShapeRenderer();}
		
		titleB = new Rectangle(bounds.x+.03f*bounds.width,bounds.y+.85f*bounds.height,.5f*bounds.width,.1f*bounds.height);
		startB = new Rectangle(bounds.x+.03f*bounds.width,bounds.y+.78f*bounds.height,.5f*bounds.width,.05f*bounds.height);
		endB = new Rectangle(bounds.x+.03f*bounds.width,bounds.y+.71f*bounds.height,.5f*bounds.width,.05f*bounds.height);
		descriptionB = new Rectangle(bounds.x+.03f*bounds.width,bounds.y+.42f*bounds.height,.5f*bounds.width,.2f*bounds.height);
		whereB = new Rectangle(bounds.x+.03f*bounds.width,bounds.y+.13f*bounds.height,.5f*bounds.width,.2f*bounds.height);
		voteUpB = new Rectangle(bounds.x+.85f*bounds.width,bounds.y+.675f*bounds.height,.1f*bounds.width,.35f*bounds.height);
		voteDownB = new Rectangle(bounds.x+.85f*bounds.width,bounds.y+.225f*bounds.height,.1f*bounds.width,.35f*bounds.height);
		reportB = new Rectangle(bounds.x+.85f*bounds.width,bounds.y+.075f*bounds.height,.1f*bounds.width,.1f*bounds.height);
		voteUp.setBounds(voteUpB.x, voteUpB.y, voteUpB.width, voteUpB.height);
		voteDown.setBounds(voteDownB.x, voteDownB.y, voteDownB.width, voteDownB.height);
		report.setBounds(reportB.x, reportB.y, reportB.width, reportB.height);
		title.setBounds(titleB.x, titleB.y, titleB.width, titleB.height);
		start.setBounds(startB.x, startB.y, startB.width, startB.height);
		end.setBounds(endB.x, endB.y, endB.width, endB.height);
		where.setBounds(whereB.x, whereB.y, whereB.width, whereB.height);
		description.setBounds(descriptionB.x, descriptionB.y, descriptionB.width, descriptionB.height);
		
		if(p.upVote.contains(u.assets.myProfile)){
			voteUp.setChecked(true);
		}
		if(p.downVote.contains(u.assets.myProfile)){
			voteDown.setChecked(true);
		}
		
		shape.begin(ShapeType.Filled);
		shape.setColor(u.assets.darkL);
		shape.rect(bounds.x,bounds.y+.05f*bounds.height,bounds.width,.95f*bounds.height);
		shape.setColor(Color.BLACK);
		shape.rect(bounds.x,bounds.y+.03f*bounds.height,bounds.width,.02f*bounds.height);
		//shape.rect(bounds.x,bounds.y+.95f*bounds.height,bounds.width,.05f*bounds.height);
		shape.end();
		
		pic.begin();
		/*
		u.assets.medium.setColor(u.assets.orange);
		u.assets.medium.draw(pic, p.name, bounds.x+.05f*bounds.width, bounds.y+.95f*bounds.height);
		u.assets.small.setColor(Color.WHITE);
		u.assets.small.draw(pic, "Time: "+p.startD.toString(), bounds.x+.05f*bounds.width, bounds.y+.8f*bounds.height);
		if(p.description.length()>35){
		u.assets.small.draw(pic, "Description: "+p.description.substring(0,35), bounds.x+.05f*bounds.width, bounds.y+.6f*bounds.height);
		u.assets.small.draw(pic, p.description.substring(35,p.description.length()>70?70:p.description.length()), bounds.x+.055f*bounds.width, locationEnabled?bounds.y+.54f*bounds.height:bounds.y+.39f*bounds.height);
		u.assets.small.draw(pic, p.description.substring(70,p.description.length()), bounds.x+.055f*bounds.width, locationEnabled?bounds.y+.48f*bounds.height:bounds.y+.33f*bounds.height);
		}else{u.assets.small.draw(pic, "Description: "+p.description, bounds.x+.05f*bounds.width, bounds.y+.5f*bounds.height);}
		if(locationEnabled ){
			u.assets.small.draw(pic, "Location: "+p.where.substring(0, p.where.length()>35?35:p.where.length()), bounds.x+.05f*bounds.width, bounds.y+.32f*bounds.height);
			if(p.where.length()>35){
			u.assets.small.draw(pic, p.where.substring(35,p.where.length()>70?70:p.where.length()), bounds.x+.055f*bounds.width, bounds.y+.26f*bounds.height);
			if(p.where.length()>70){
			u.assets.small.draw(pic, p.where.substring(70,p.where.length()), bounds.x+.055f*bounds.width, bounds.y+.2f*bounds.height);
			}
			}
			}
		
		*/
		try{
		voteUp.draw(pic, 1);
		voteDown.draw(pic, 1);
		report.draw(pic, 1);
		title.draw(pic, 1);
		start.draw(pic, 1);
		end.draw(pic, 1);
		where.draw(pic, 1);
		description.draw(pic, 1);
		layout.setText(u.assets.medium, ""+p.vote);
		u.assets.medium.setColor(u.assets.orange);
		u.assets.medium.draw(pic, ""+p.vote, .9f*w-layout.width/2, bounds.y+19*bounds.height/32+layout.height/2);
		}catch(Exception e){
			
		}
		pic.end();
	}
	
	/**If the module is touched this method determines what to do
	 * with the touch
	 * @param touch
	 * @return ifTouched
	 */
	public boolean down(Rectangle touch){
		if(!Intersector.overlaps(touch, bounds)){return false;}
		else if(Intersector.overlaps(touch, reportB)){report.toggle();}
		return true;
		
	}
	
	/**If the module is released this method determines what to do
	 * with the touch
	 * @param touch
	 * @return ifReleased
	 */
	public boolean up(Rectangle touch){
		if(!Intersector.overlaps(touch, bounds)){return false;}
		else if(Intersector.overlaps(touch, voteUpB)){u.send(new VoteUp(p,u.assets.myProfile));}
		else if(Intersector.overlaps(touch, voteDownB)){u.send(new VoteDown(p,u.assets.myProfile));}
		else if(Intersector.overlaps(touch, reportB)){report.toggle();u.reportConfirm(this);}
		else{
			u.assets.toolBar.backEnabled=true;
			u.setScreen(new PartyViewScreen(u,p));
			
		}
		return true;
		
	}

	
	/**This method allows for us to compare party modules to each other
	 * based on date, this allows for an easy way for us to sort them
	 */
	@Override
	public int compareTo(Object o) {
		PartyModule comp = (PartyModule) o;
		if(comp.p.startD.getTime()>p.startD.getTime()){return -1;}
		else if(comp.p.startD.getTime()>p.startD.getTime()){return 1;}
		else{
			return 0;
		}
	}
	
	
}
