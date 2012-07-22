/**

 */
package me.EdwJes;

/**
 * General methods that could be useful for every class. All the methods should be static. It should not be allowed to create objects out of this class.   
 *  
 * @author Edwin
 *
 */
public class Misc{
	private Misc(){}
	
	/**
	 * Modulo, will work with negative numbers and big numbers comparing to the built-in % operation.<br/>
	 * Like (a mod n) or (a % n)
	 * 
	 * @param a
	 * @param n
	 * @return The remainder after division
	 */
	public static int mod(int a,int n){
		return (a % n + n) % n;
	}
	
	/**
	 * Performs word wrapping.  Returns the input string with long lines of
	 * text cut (between words) for readability.
	 * 
	 * @param in  text to be word-wrapped
	 * @param len number of characters in a line
	 */
	public static String strWrap(String in,int len) {
		String newline = System.getProperty("line.separator");
	    //Trim
	    while(in.length() > 0 && (in.charAt(0) == '\t' || in.charAt(0) == ' '))
	        in = in.substring(1);
	    //If Small Enough Already, Return Original
	    if(in.length() < len)
	        return in;
	    //If Next length Contains Newline, Split There
	    if(in.substring(0, len).contains(newline))
	        return in.substring(0, in.indexOf(newline)).trim() + newline +
	               strWrap(in.substring(in.indexOf("\n") + 1), len);
	    //Otherwise, Split Along Nearest Previous Space/Tab/Dash
	    int spaceIndex = Math.max(Math.max(in.lastIndexOf(" ",  len),
	                                       in.lastIndexOf("\t", len)),
	                                       in.lastIndexOf("-",  len));
	    //If No Nearest Space, Split At length
	    if(spaceIndex == -1)
	        spaceIndex = len;
	    //Split
	    return in.substring(0, spaceIndex).trim() + newline + strWrap(in.substring(spaceIndex), len);
	}
}
