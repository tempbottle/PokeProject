package me.EdwJes.debug;

import java.io.PrintStream;

public class Debug{
	public static PrintStream console;
	public static DebugRenderer render;
	public static boolean renderDebug=true;
	
	public Debug(){
		render=new DebugRenderer();
	}
}
