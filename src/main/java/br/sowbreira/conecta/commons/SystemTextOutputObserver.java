package br.sowbreira.conecta.commons;

import java.awt.Component;

/**
 * @author sobreira
 * Created on 18/06/2004
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class SystemTextOutputObserver {
	public SystemTextOutputObserver(){
		
	}
	
	public SystemTextOutputObserver(Component component){
	}
	
	public abstract void print(String text);
	
}
