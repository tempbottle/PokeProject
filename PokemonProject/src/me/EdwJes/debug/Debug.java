package me.EdwJes.debug;

import java.io.PrintStream;

public class Debug{
	public static PrintStream console;
	public static DebugRenderer render;
	
	public Debug(){
		console = System.out;
		console.printf("Testing...");
		render=new DebugRenderer();
	}
}
