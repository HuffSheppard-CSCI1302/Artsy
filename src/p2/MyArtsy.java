package src.p2                  ;
import  javafx.scene.image.Image;
import  src.effects.Artsy       ;

/**
 * @author Preston Sheppard (coding)
 * @author Miles B Huff (bugfixing and formatting)
**/
public class MyArtsy implements Artsy
{ //+class
	////////////////////////////////////////////////////////////////////////
	@Override
	public Image doCopy(Image oSrc)
	{ //+method
		int           iWidth  =        oSrc.getWidth(               );
		int           iHeight =       oSrc.getHeight(               );
		WritableImage oCopy   =    new WritableImage(iWidth, iHeight);
		PixelReader   oPR     =  oSrc.getPixelReader(               );
		PixelWriter   oPW     = oCopy.getPixelWriter(               );
		for (int iX = 0     ;
			 iX < iWidth;
			 iX++       )
		{ //+loop
			for(int iY = 0      ;
				iY < iHeight;
				iY++        )
			{ oPW.setArgb(iX, iY, oPR.getArgb(iX, iY)); }
		} //-loop
		return oCopy;
	} //-method

	////////////////////////////////////////////////////////////////////////
	@Override
	public Image doRotate(Image  oSrc,
		              double dDeg)
	{ //+method
		Image       oNew = new           Image(copyImage(oSrc));
		PixelReader oPR  = oSrc.getPixelReader(               );
		PixelWriter oPW  = oNew.getPixelWriter(               );
		for(int iX = 0              ,
		     iNewX = 0              ;
		        iX < oNew.getWidth();
		        iX++                )
		{ //+loop
			for(int iY = 0               ,
			     iNewY = 0               ;
			        iY < oNew.getHeight();
			        iY++                 )
			{ //+loop
				iNewX =  iX * (int)Math.cos(dDeg) + iY * (int)Math.sin(dDeg);
				iNewY = -iX * (int)Math.sin(dDeg) + iY * (int)Math.cos(dDeg);
				if((    0<= iNewX           )
				&& (    0<= iNewY           )
				&& (iNewX<= oNew.getWidth() )
				&& (iNewY<= oNew.getHeight()))
				{ oPW.setArgb(iNewX, iNewY, oPR.getArgb(iX, iY)); }
			} //-loop
		} //-loop
		return oNew;
	} //-method

	////////////////////////////////////////////////////////////////////////
	@Override
	public Image doCheckers(Image oSrc1,
	                        Image oSrc2,
	                        int   iSize)
	{ //+method
	        Image       oNew = new            Image(doCopy(oSrc1));
	        PixelReader oPR  = oSrc2.getPixelReader(             );
	        PixelWriter oPW  =  oNew.getPixelWriter(             );
	        for(int iOffsetY = 0              ,
	                      iX = 0              ;
	                      iX < oNew.getWidth();
	                      iX+= iSize          )
	        { //+loop
	                for(int iY = 0               ;
	                        iY < oNew.getHeight();
	                        iY+= iSize           )
	                { //+loop
	                        iY+= iOffsetY;
	                        for(int iNewX = iX   ,
	                              iCountX = 0    ;
	                              iCountX < iSize;
	                              iCountX++      ,
	                                iNewX++      )
	                        { //+loop
	                                for(int iNewY = iY   ,
	                                      iCountY = 0    ;
	                                      iCountY < iSize;
	                                      iCountY++      ,
	                                        iNewY++      )
	                                { oPW.setArgb(iNewX, iNewY, oPR.getArgb(iNewX, iNewY)); }
	                        } //-loop
	                } //-loop
	                if(iOffsetY == 0) iOffsetY = iSize;
	                else              iOffsetY = 0    ;
	        } //-loop
	        return oNew;
	} //-method

	////////////////////////////////////////////////////////////////////////
	@Override
	public Image doStripesHorizontal(Image oSrc1  ,
		                         Image oSrc2  ,
		                         int   iHeight)
	{ //+method
		Image       oNew = new            Image(doCopy(oSrc1));
		PixelReader oPR  = oSrc2.getPixelReader(             );
		PixelWriter oPW  =  oNew.getPixelWriter(             );
		for(int iY = 0              ,
			iX = 0              ;
			iX < oNew.getWidth();
			iX++                )
		{ //+loop
			for(int iCount = 0      ;
				iCount < iHeight;
				iCount++        ,
				    iY++        )
			{ //+loop
				oPW.setArgb(iX, iY, oPR.getArgb(iX, iY));
			} //-loop
			iY+= iHeight;
		} //-loop
		return oNew;
	} //-method

	////////////////////////////////////////////////////////////////////////
	@Override
	public Image doStripesVertical(Image oSrc1 ,
		                       Image oSrc2 ,
		                       int   iWidth)
	{ //method
		Image       oNew = new            Image(doCopy(oSrc1));
		PixelReader oPR  = oSrc2.getPixelReader(             );
		PixelWriter oPW  =  oNew.getPixelWriter(             );
		for(int iX = 0               ,
			iY = 0               ;
			iY < oNew.getHeight();
			iY++                 )
		{ //+loop
			for(int count = 0;
				count < iWidth;
				count++       ,
				   iX+= iWidth)
			{ //+loop
				iX++;
				oPW.setArgb(iX, iY, oPR.getArgb(iX, iY));
			} //-loop
		} //-loop
		return oNew;
	} //-method
} //-class
