package me.EdwJes.main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BorderImage{
	/*     BorderImage Variables Layout
	 * ╔═══════════╤═══════════╤═══════════╗ ←╤
	 * ║0,0        |1,0        |2,0        ║  |
	 * ║topLeft    |topSide    |topRight   ║ ◄| 0: topH
	 * ║           |           |           ║  |
	 * ╟-----------┼-----------┼-----------╢ ←╪
	 * ║0,1        |1,1        |2,1        ║  |
	 * ║sideLeft   |inner      |sideRight  ║ ◄| 1: innerH
	 * ║           |           |           ║  |
	 * ╟-----------┼-----------┼-----------╢ ←╪
	 * ║0,2        |1,2        |2,2        ║  |
	 * ║bottomLeft |bottomSide |bottomRight║ ◄| 2: bottomH
	 * ║           |           |           ║  |
	 * ╚═══════════╧═══════════╧═══════════╝ ←╧
	 * ↑_____▲_____↑_____▲_____↑_____▲_____↑  Y-axis
	 * ║0: leftW   ║1: innerW  ║2: rightW  ║ X-axis
	 * */
	Image image,topLeft,topRight,bottomLeft,bottomRight,sideLeft,sideRight,topSide,bottomSide,inner;
	int leftW,topH,rightW,bottomH,innerW,innerH,borderH,borderW,imgW,imgH;
	public BorderImage(Image img,int leftW,int topH,int rightW,int bottomH){
		image=img;
		imgW=img.getWidth();
		imgH=img.getHeight();
		
		this.leftW=leftW;
		this.rightW=rightW;
		this.topH=topH;
		this.bottomH=bottomH;
		
		this.borderW=leftW+rightW;
		this.borderH=topH+bottomH;
		
		this.innerW=imgW-borderW;
		this.innerH=imgH-borderH;
		
		topLeft=img.getSubImage(0,0,leftW,topH);
		sideLeft=img.getSubImage(0,topH,leftW,imgH-topH-bottomH);
		bottomLeft=img.getSubImage(0,imgH-bottomH,leftW,bottomH);

		topRight=img.getSubImage(imgW-rightW,0,rightW,topH);
		sideRight=img.getSubImage(imgW-rightW,topH,rightW,imgH-topH-bottomH);
		bottomRight=img.getSubImage(imgW-rightW,imgH-bottomH,rightW,bottomH);
		
		topSide=img.getSubImage(leftW,0,imgW-leftW-rightW,topH);
		inner=img.getSubImage(leftW,topH, imgW-leftW-rightW, imgH-topH-bottomH);
		bottomSide=img.getSubImage(leftW,imgH-bottomH,imgW-leftW-rightW,bottomH);
	}
	
	public void destroy(){
		try{
			image.destroy();
			image=null;
			topLeft.destroy();
			topLeft=null;
			topRight.destroy();
			topRight=null;
			bottomLeft.destroy();
			bottomLeft=null;
			bottomRight.destroy();
			bottomRight=null;
			sideLeft.destroy();
			sideLeft=null;
			sideRight.destroy();
			sideRight=null;
			topSide.destroy();
			topSide=null;
			bottomSide.destroy();
			bottomSide=null;
			inner.destroy();
			inner=null;}
		catch(SlickException e){
			e.printStackTrace();
		}
	}
}
