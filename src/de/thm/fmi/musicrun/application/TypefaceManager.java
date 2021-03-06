package de.thm.fmi.musicrun.application;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;

public class TypefaceManager {

	// Activity
	Activity activity;
	
	// Constants
	private static final String DEFAULT_NORMAL_FONT_FILENAME = "fonts/Lato-Light.ttf";
	private static final String DEFAULT_BOLD_FONT_FILENAME = "fonts/Lato-Regular.ttf";
	private static final String DEFAULT_ITALIC_FONT_FILENAME = "fonts/Lato-Semibold.ttf";
	private static final String DEFAULT_BOLD_ITALIC_FONT_FILENAME = "fonts/Lato-Bold.ttf";

	// Typefaces
	private Typeface regular;
    private Typeface bold;
    private Typeface italic;
    private Typeface boldItalic;
	public enum FontStyle { REGULAR, BOLD, ITALIC, ITALIC_BOLD; }
	
	// DEBUG
	private static final String TAG = MainActivity.class.getName();
	private static final boolean D = false;
	
	// ------------------------------------------------------------------------
	
	public TypefaceManager(Activity activity){
		
		this.activity = activity;
	}
	
	// ------------------------------------------------------------------------
	
	// sets the custom typefaces global, except the navigation bar
	public void setDefaultFont() {

	    try {
	    	this.regular = Typeface.createFromAsset(this.activity.getAssets(),DEFAULT_NORMAL_FONT_FILENAME);
	        this.bold = Typeface.createFromAsset(this.activity.getAssets(), DEFAULT_BOLD_FONT_FILENAME);
	        this.italic = Typeface.createFromAsset(this.activity.getAssets(), DEFAULT_ITALIC_FONT_FILENAME);
	        this.boldItalic = Typeface.createFromAsset(this.activity.getAssets(), DEFAULT_BOLD_ITALIC_FONT_FILENAME);
	       
	        java.lang.reflect.Field DEFAULT = Typeface.class.getDeclaredField("DEFAULT");
	        DEFAULT.setAccessible(true);
	        DEFAULT.set(null, this.regular);

	        java.lang.reflect.Field DEFAULT_BOLD = Typeface.class.getDeclaredField("DEFAULT_BOLD");
	        DEFAULT_BOLD.setAccessible(true);
	        DEFAULT_BOLD.set(null, this.bold);

	        java.lang.reflect.Field sDefaults = Typeface.class.getDeclaredField("sDefaults");
	        sDefaults.setAccessible(true);
	        sDefaults.set(null, new Typeface[]{
	                this.regular, this.bold, this.italic, this.boldItalic
	        });

	    } catch (NoSuchFieldException e) {
	        Log.e(TAG, e.toString());
	    } catch (IllegalAccessException e) {
	    	Log.e(TAG, e.toString());
	    } catch (Throwable e) {
	        //cannot crash app if there is a failure with overriding the default font!
	    	Log.e(TAG, e.toString());
	    }
	}	
	
	// ------------------------------------------------------------------------
	
	public String getTypeface(FontStyle style){
	
		switch(style){
		case REGULAR: 		return this.DEFAULT_NORMAL_FONT_FILENAME;
		case BOLD:			return this.DEFAULT_BOLD_FONT_FILENAME;
		case ITALIC:		return this.DEFAULT_ITALIC_FONT_FILENAME;
		case ITALIC_BOLD:	return this.DEFAULT_BOLD_ITALIC_FONT_FILENAME;
		default:			return this.DEFAULT_NORMAL_FONT_FILENAME;
		}
	}
	
	
}
