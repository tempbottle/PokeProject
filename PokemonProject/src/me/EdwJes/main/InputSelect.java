package me.EdwJes.main;

import me.EdwJes.Misc;

/*
 * Design: e.g. columns=3, length=7
 *               /\
 *   00 03 06     |
 *   01 04      Rows
 *   02 05        |
 *               \/
 * <- Columns ->
 * 
 * To make a list that goes in one direction e.g. down, set columns=1
 * */
public class InputSelect{
	private int index=0,columns=3,length=10;
	
	public InputSelect(){
		for(int i=0;i<length;i++)
			System.out.println(i+": "+getColumn(i)+","+getRow(i));
	}
	
	public int getColumns(){
		return columns;
	}
	
	public int getRows(){
		return (int)Math.ceil((float)length/(float)columns);
	}
	
	public int getColumn(int index){
		return (int)Math.floor((float)index/(float)getRows());
	}
	
	public int getRow(int index){
		return Misc.mod(index,getRows());
	}
	
	public void selectSet(int index){
		this.index=Math.max(0,Math.min(index,length-1));
	}
	
	public int selectMove(int relativeIndex){
		selectSet(index+relativeIndex);
		return index;
	}
	
	public int selectMoveX(int horizontalIndex){
		selectSet(index+horizontalIndex*getRows());
		return index;
	}
	
	public int selectMoveY(int verticalIndex){
		selectSet(Misc.mod((index+verticalIndex-getColumn(index)*getRows()),getRows())+getColumn(index)*getRows());
		return index;
	}
/* FOR TESTING PURPOSES
	@Override
	public void onKeyPressed(int key,char chr,int playerId,Config config){
		if(key==Input.KEY_X)
			selectMove(1);
		else if(key==Input.KEY_Z)
			selectMove(-1);
		else if(key==Input.KEY_LEFT)
			selectMoveX(-1);
		else if(key==Input.KEY_RIGHT)
			selectMoveX(1);
		else if(key==Input.KEY_UP)
			selectMoveY(-1);
		else if(key==Input.KEY_DOWN)
			selectMoveY(1);
	}

	@Override
	public void render(Graphics g,View view){
		for(int i=0;i<length;i++){
			if(index==i)
				g.setColor(Color.black);
			g.fillRect(getColumn(i)*32,getRow(i)*32,30,30);
			g.setColor(Color.red);
			g.drawString(getColumn(i)+","+getRow(i)+"\n"+i,getColumn(i)*32,getRow(i)*32);
			g.setColor(Color.white);
		}
		g.drawString(getColumns()+","+getRows(),128,0);
	}*/
}

class SelectOutItem{
	public void select(){
		
	}
}
