package com.epic.widgets;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.epic.main.EpicMain;

@SuppressWarnings("serial")
public class DrawGraphNext extends JPanel implements MouseMotionListener, MouseListener{
   private static final int MAX_SCORE = 20;
   private static final int PREF_W = 250;
   private static final int PREF_H = 300;
   private static final int BORDER_GAP = 30;
   private static final Color GRAPH_COLOR = Color.green;
   private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int GRAPH_POINT_WIDTH = 12;
   private static final int Y_HATCH_CNT = 10;
   private List<Integer> scores;
   List<Point> graphPoints = new ArrayList<Point>();
   int toothWidth_ = 0;
   Vector v = new Vector();
   Graphics2D g2 = null;
   double depth = 35;
   Point cpoint = new Point(198,2);
   Vector vecTeethL = new Vector();
   Vector vecTeethR = new Vector();
   Vector vecLeftTurningPts = new Vector();
   Vector vecRightTurningPts = new Vector();
   boolean traversed_ = false;
   int option_ = 0;
   
   public DrawGraphNext(List<Integer> scores) {
      this.scores = scores;
      v.clear();
//      v.add(cpoint);
      
      addMouseMotionListener(this);
      addMouseListener(this);
     // drawTriangle();
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
//System.out.println("Graphics is :: "+g.hashCode());      
      g2 = (Graphics2D)g;
//      System.out.println("GraphicsG2 is 111:: "+g2.hashCode());
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

      /*List<Point> graphPoints = new ArrayList<Point>();
      for (int i = 0; i < scores.size(); i++) {
         int x1 = (int) (i * xScale + BORDER_GAP);
         int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
         graphPoints.add(new Point(x1, y1));
      }
      */
      File file = null;
      try {         
//    	  final String imageFile = "./standard.jpg";
    	  
    	  //System.out.println("File is:: "+file.getPath()+"   <<"+file.getAbsolutePath()+">>"+file.hashCode());
    	  file = new File("standard.jpeg");
//    	  Image img = Toolkit.getDefaultToolkit().getImage(EpicMain.class.getResource("res/standard.jpeg"));
      BufferedImage image = ImageIO.read(file);
      g2.drawImage(image,0,0,null);
//      System.out.println("Graphics is 222:: "+g2.hashCode());
      } catch (Exception ex) {
    	  System.out.println(getClass().getResource("res/standard.jpeg").getPath());
    	  ex.printStackTrace();
    	  System.out.println(ex.getMessage()+"   <<<<<");
     }
      // create x and y axes 
    //  g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
    //  g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

      // create hatch marks for y axis. 
   /*   for (int i = 0; i < Y_HATCH_CNT; i++) {
         int x0 = BORDER_GAP;
         int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
         int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
         int y1 = y0;
         g2.drawLine(x0, y0, x1, y1);
      }

      // and for x axis
      for (int i = 0; i < scores.size() - 1; i++) {
         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
         int x1 = x0;
         int y0 = getHeight() - BORDER_GAP;
         int y1 = y0 - GRAPH_POINT_WIDTH;
         g2.drawLine(x0, y0, x1, y1);
      }*/

      Stroke oldStroke = g2.getStroke();
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);         
      }
      g2.setStroke(oldStroke);      
      g2.setColor(GRAPH_POINT_COLOR);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g2.fillOval(x, y, ovalW, ovalH);
      }
      g2.drawOval(cpoint.x, cpoint.y, 2, 2);
      if (option_ == 0)
      {
    	  clear();
    	  return;
      }
      populateStatically(option_);
      drawOvals();
      findTurnsForLeft(2);
      findTurnsForLeft(4);
      findTurnsForRight(2);
      findTurnsForRight(4);
      drawInnerLeftLine();
      drawInnerRightLine();
      drawInnerBridge();
      
      
      
      
      
      
//      drawTriangle();
//      calculateDepth();;
//      calculateStep();
//      
//      doSecondTurn();
//      doFourthTurn();
//      drawFinalCurve();
   }
   
   public void mouseMoved(MouseEvent ee) {
       Point curpnt = ee.getPoint();
       if (cpoint.getX()>curpnt.getX())
       {
    	   Point prevpnt = null;
    	   if (vecTeethL.size() == 0)
    		   prevpnt = cpoint;
    	   else prevpnt = (Point)(((Tooth)vecTeethL.lastElement()).getEndpoint());
		   double MD = Math.sqrt(  ((curpnt.x-prevpnt.x)*(curpnt.x-prevpnt.x))+ ((curpnt.y-prevpnt.y)*(curpnt.y-prevpnt.y))); 
		   setToolTipText("MD: "+ Math.round(MD/6));
		   toothWidth_ = (int)MD;
       }
       else
       {
    	   Point prevpnt = null;
    	   if (vecTeethR.size() == 0)
    		   prevpnt = cpoint;
    	   else prevpnt = (Point)(((Tooth)vecTeethR.lastElement()).getEndpoint());
		   double MD = Math.sqrt(  ((curpnt.x-prevpnt.x)*(curpnt.x-prevpnt.x))+ ((curpnt.y-prevpnt.y)*(curpnt.y-prevpnt.y))); 
		   setToolTipText("MD: "+ Math.round(MD/6));
		   toothWidth_ = (int)MD;
       }
    	   
	   //System.out.println(ee.getPoint().getX()+","+ee.getPoint().getY());
    }

    public void mouseDragged(MouseEvent ee) {
       //saySomething("Mouse dragged", e);
    	 //////////setToolTipText(ee.getPoint().getX()+","+ee.getPoint().getY());
    	   //System.out.println(ee.getPoint().getX()+","+ee.getPoint().getY());
    }
   
    
    
    
    public void mouseEntered (MouseEvent me) {}
    public void mousePressed (MouseEvent me) {}
    public void mouseReleased (MouseEvent me) {} 
    public void mouseExited (MouseEvent me) {} 
   

    // This method will be called when the mouse has been clicked.
    public void mouseClicked (MouseEvent me) {
     // Save the coordinates of the click lke this.
    	if (me.getButton() == MouseEvent.BUTTON3)
    	{
    		int selectedOption = JOptionPane.showConfirmDialog(null, 
                    "Delete last point?", 
                    "Confirm...", 
                    JOptionPane.YES_NO_OPTION); 
    		if (selectedOption == JOptionPane.YES_OPTION) {
    			if (v.size() >= 2)
    			v.remove(v.size()-1);
    		}
    		else if (selectedOption == JOptionPane.NO_OPTION) {
//    			drawInnerLine();
    		}
    		}
    else{
    	int selectedOption = JOptionPane.showConfirmDialog(null, 
                "Keep this point?", 
                "Choose", 
                JOptionPane.YES_NO_OPTION); 
		if (selectedOption == JOptionPane.YES_OPTION) {
			System.out.println("Clicked point is: "+me.getPoint());
			/*
			toothWidth_ = 7;
//			window.dispose();
			Point pnt = me.getPoint();
			Tooth tooth = new Tooth();
			if (cpoint.getX() > pnt.getX())
			{
		    if (vecTeethL.size() == 0)
		     tooth.setToothNo("11");
		    else if (vecTeethL.size() == 1)
		     tooth.setToothNo("12");
		    else if (vecTeethL.size() == 2)
			 tooth.setToothNo("13");
		    else if (vecTeethL.size() == 3)
		     tooth.setToothNo("14");
		    else if (vecTeethL.size() == 4)
		     tooth.setToothNo("15");
		    else if (vecTeethL.size() == 5)
		     tooth.setToothNo("16");
		    else if (vecTeethL.size() == 6)
		     tooth.setToothNo("17");
		    
		    if (vecTeethL.size() == 0)
		    	tooth.setStpnt(cpoint);
		    else
		    	tooth.setStpnt((Point)(((Tooth)vecTeethL.lastElement()).endpoint));
			}
			else  if (cpoint.getX() < pnt.getX())//Calculation For Right
			{
			    if (vecTeethR.size() == 0)
			     tooth.setToothNo("21");
			    else if (vecTeethR.size() == 1)
			     tooth.setToothNo("22");
			    else if (vecTeethR.size() == 2)
				 tooth.setToothNo("13");
			    else if (vecTeethR.size() == 3)
			     tooth.setToothNo("14");
			    else if (vecTeethR.size() == 4)
			     tooth.setToothNo("15");
			    else if (vecTeethR.size() == 5)
			     tooth.setToothNo("16");
			    else if (vecTeethR.size() == 6)
			     tooth.setToothNo("17");
			    
			    if (vecTeethR.size() == 0)
			    	tooth.setStpnt(cpoint);
			    else
			    	tooth.setStpnt((Point)(((Tooth)vecTeethR.lastElement()).endpoint));
				}
		    v.add(pnt);
		     tooth.setEndpoint(pnt);
		     calculateDepth(tooth);
	    	 tooth.setWidth(toothWidth_);
		     
		     
		     
		     int xpos = me.getX();
		     int ypos = me.getY();
		     //graphPoints.add(pnt);
		     System.out.println("Cliked Point is :"+xpos+" "+ypos);
		     
		     this.getGraphics().setColor(Color.CYAN);
		     this.getGraphics().drawOval(xpos,ypos,3 ,3);
		     v.add(pnt);
		     if (cpoint.getX() > pnt.getX())
		    	 vecTeethL.add(tooth);
		     else 
		    	 vecTeethR.add(tooth);
		      */
		}
    }//end of else
    }
    public void drawInnerLeftLine(){
//    	g2.drawLine(175,47,134,61);
//    	g2.drawLine(134,61,117,102);
//    	g2.drawLine(117,102,111,142);
//    	cpoint.getX() > pnt.getX()
    	if (vecTeethL != null && vecTeethL.size()< 7)
    		return;
    	for (int i = 0;i<vecTeethL.size()-1;i++)
    	{
    		Tooth tooth = (Tooth)vecTeethL.get(i);
    		Tooth tooth1 = (Tooth)vecTeethL.get(i+1);
    		if (i == 2 )
//    		if (tooth.getToothNo().equals("12") || tooth.getToothNo().equals("14"))
    		{
    			g2.drawLine((int)tooth.getDepthPt().getX(), (int)tooth.getDepthPt().getY(),(int)((Point)vecLeftTurningPts.get(0)).getX(), (int)((Point)vecLeftTurningPts.get(0)).getY());
    			g2.drawLine((int)((Point)vecLeftTurningPts.get(0)).getX(), (int)((Point)vecLeftTurningPts.get(0)).getY()
    					,(int)((Point)vecLeftTurningPts.get(1)).getX(), (int)((Point)vecLeftTurningPts.get(1)).getY());
    			g2.drawLine((int)((Point)vecLeftTurningPts.get(1)).getX(), (int)((Point)vecLeftTurningPts.get(1)).getY()
    					,(int)tooth1.getDepthPt().getX(), (int)tooth1.getDepthPt().getY());
    			continue;
    		}
    		else if (i == 4)
//        		if (tooth.getToothNo().equals("12") || tooth.getToothNo().equals("14"))
        		{
        			g2.drawLine((int)tooth.getDepthPt().getX(), (int)tooth.getDepthPt().getY(),(int)((Point)vecLeftTurningPts.get(2)).getX(), (int)((Point)vecLeftTurningPts.get(2)).getY());
        			g2.drawLine((int)((Point)vecLeftTurningPts.get(2)).getX(), (int)((Point)vecLeftTurningPts.get(2)).getY()
        					,(int)((Point)vecLeftTurningPts.get(3)).getX(), (int)((Point)vecLeftTurningPts.get(3)).getY());
        			g2.drawLine((int)((Point)vecLeftTurningPts.get(3)).getX(), (int)((Point)vecLeftTurningPts.get(3)).getY()
        					,(int)tooth1.getDepthPt().getX(), (int)tooth1.getDepthPt().getY());
        			continue;
        		}
    		g2.drawLine((int)tooth.getDepthPt().getX(), (int)tooth.getDepthPt().getY(),(int)tooth1.getDepthPt().getX(), (int)tooth1.getDepthPt().getY());
    	}
    	validate();
    	repaint();
    }
    public void drawInnerRightLine(){
//    	g2.drawLine(175,47,134,61);
//    	g2.drawLine(134,61,117,102);
//    	g2.drawLine(117,102,111,142);
//    	cpoint.getX() > pnt.getX()
    	for (int i = 0;i<vecTeethR.size()-1;i++)
    	{
    		Tooth tooth = (Tooth)vecTeethR.get(i);
    		Tooth tooth1 = (Tooth)vecTeethR.get(i+1);
    		if (i == 2 )
//        		if (tooth.getToothNo().equals("12") || tooth.getToothNo().equals("14"))
        		{
        			g2.drawLine((int)tooth.getDepthPt().getX(), (int)tooth.getDepthPt().getY(),(int)((Point)vecRightTurningPts.get(0)).getX(), (int)((Point)vecRightTurningPts.get(0)).getY());
        			g2.drawLine((int)((Point)vecRightTurningPts.get(0)).getX(), (int)((Point)vecRightTurningPts.get(0)).getY()
        					,(int)((Point)vecRightTurningPts.get(1)).getX(), (int)((Point)vecRightTurningPts.get(1)).getY());
        			g2.drawLine((int)((Point)vecRightTurningPts.get(1)).getX(), (int)((Point)vecRightTurningPts.get(1)).getY()
        					,(int)tooth1.getDepthPt().getX(), (int)tooth1.getDepthPt().getY());
        			continue;
        		}
        		else if (i == 4)
//            		if (tooth.getToothNo().equals("12") || tooth.getToothNo().equals("14"))
            		{
            			g2.drawLine((int)tooth.getDepthPt().getX(), (int)tooth.getDepthPt().getY(),(int)((Point)vecRightTurningPts.get(2)).getX(), (int)((Point)vecRightTurningPts.get(2)).getY());
            			g2.drawLine((int)((Point)vecRightTurningPts.get(2)).getX(), (int)((Point)vecRightTurningPts.get(2)).getY()
            					,(int)((Point)vecRightTurningPts.get(3)).getX(), (int)((Point)vecRightTurningPts.get(3)).getY());
            			g2.drawLine((int)((Point)vecRightTurningPts.get(3)).getX(), (int)((Point)vecRightTurningPts.get(3)).getY()
            					,(int)tooth1.getDepthPt().getX(), (int)tooth1.getDepthPt().getY());
            			continue;
            		}
    		g2.drawLine((int)tooth.getDepthPt().getX(), (int)tooth.getDepthPt().getY(),(int)tooth1.getDepthPt().getX(), (int)tooth1.getDepthPt().getY());
    	}
    	validate();
    	repaint();
    }
   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }
   public void findTurnsForLeft(int toothNo){
	   if (vecTeethL != null && vecTeethL.size()==7){
	   int depth = 0;
	   if (toothNo == 2)
		   depth = 7*6;
	   else depth = 63;//10.5*6;
	   Point thirdMidPt = ((Tooth)vecTeethL.get(toothNo)).getMidpnt();
	   Point fourthMidPt = ((Tooth)vecTeethL.get(toothNo+1)).getMidpnt();
g2.drawLine(thirdMidPt.x, thirdMidPt.y, fourthMidPt.x, fourthMidPt.y);
	   Point midPtOfThirdFourthMid = new Point((int)(thirdMidPt.x+fourthMidPt.x)/2 ,(int)(thirdMidPt.y+fourthMidPt.y)/2);
	   //Slope(m) = (Y2-Y1)/(X2-X1)
	   double xgap = (thirdMidPt.x-fourthMidPt.x);
	   double ygap = (thirdMidPt.y-fourthMidPt.y);
//	   System.out.println("xgap :"+xgap+"  , ygap : "+ygap );
	   double m = ygap/xgap;
	   
	   double perpendiM = -1/m;
	   double firstTurnPtX = 0.0;
	   System.out.println("     the xgap is :: "+xgap);
	   if (xgap > 0)//left side
		   firstTurnPtX = midPtOfThirdFourthMid.x+Math.sqrt(depth*depth/(1+perpendiM*perpendiM));
	   else //Right side
		   firstTurnPtX = midPtOfThirdFourthMid.x-Math.sqrt(depth*depth/(1+perpendiM*perpendiM));
	   double firstTurnPtY = perpendiM*(firstTurnPtX-midPtOfThirdFourthMid.x)+midPtOfThirdFourthMid.y;
	   
	   Point firstTurnPt = new Point((int)firstTurnPtX,(int)firstTurnPtY);
	   //Now push this point to a vector
	   vecLeftTurningPts.add(firstTurnPt);
	   
	   
	   g2.drawOval((int)firstTurnPtX, (int)firstTurnPtY, 4, 4);
	   Point fourthDepthPt = ((Tooth)vecTeethL.get(toothNo+1)).getDepthPt();
	   Point fifthDepthPt = ((Tooth)vecTeethL.get(toothNo+2)).getDepthPt();
	   //slope(m) = (Y2-Y1)/(X2-X1)
	   double mOf45depthLine = (fifthDepthPt.y-fourthDepthPt.y) / (fifthDepthPt.x-fourthDepthPt.x);
	   
	   double c1 = midPtOfThirdFourthMid.y - perpendiM*midPtOfThirdFourthMid.x;
	   double c2 = fourthDepthPt.y - mOf45depthLine*fourthDepthPt.x;
//       Two lines y = m1x+c1 and y = m2x+c2 
//       x = (c2-c1)/(m1-m2)
//       y = m1x + c1 = m1(c2-c1)/(m1-m2) + c1
	   double slopediff = (perpendiM-mOf45depthLine);
	   double ydiff = (c2 -c1);
	   double doublex = ydiff/slopediff;
	   int interx = (int)doublex;
	   int intery = (int)(mOf45depthLine*interx)+(int)c2;
	   
	   Point secondTurnPt = new Point(interx,intery);
	   vecLeftTurningPts.add(secondTurnPt);
	   System.out.println("The second turn is at :: "+interx+","+ intery);
	   g2.drawOval(interx, intery, 4, 4);
	   }
	   
   }
   public void findTurnsForRight(int toothNo){
	   if (vecTeethR != null && vecTeethR.size()==7){
	   int depth = 0;
	   if (toothNo == 2)
		   depth = 7*6;
	   else depth = 63;//10.5*6;
	   Point thirdMidPt = ((Tooth)vecTeethR.get(toothNo)).getMidpnt();
	   Point fourthMidPt = ((Tooth)vecTeethR.get(toothNo+1)).getMidpnt();
g2.drawLine(thirdMidPt.x, thirdMidPt.y, fourthMidPt.x, fourthMidPt.y);
	   Point midPtOfThirdFourthMid = new Point((int)(thirdMidPt.x+fourthMidPt.x)/2 ,(int)(thirdMidPt.y+fourthMidPt.y)/2);
	   //Slope(m) = (Y2-Y1)/(X2-X1)
	   double xgap = (thirdMidPt.x-fourthMidPt.x);
	   double ygap = (thirdMidPt.y-fourthMidPt.y);
//	   System.out.println("xgap :"+xgap+"  , ygap : "+ygap );
	   double m = ygap/xgap;
	   
	   double perpendiM = -1/m;
	   double firstTurnPtX = 0.0;
	   System.out.println("     the xgap is :: "+xgap);
	   if (xgap > 0)//left side
		   firstTurnPtX = midPtOfThirdFourthMid.x+Math.sqrt(depth*depth/(1+perpendiM*perpendiM));
	   else //Right side
		   firstTurnPtX = midPtOfThirdFourthMid.x-Math.sqrt(depth*depth/(1+perpendiM*perpendiM));
	   double firstTurnPtY = perpendiM*(firstTurnPtX-midPtOfThirdFourthMid.x)+midPtOfThirdFourthMid.y;
	   
	   Point firstTurnPt = new Point((int)firstTurnPtX,(int)firstTurnPtY);
	   //Now push this point to a vector
	   vecRightTurningPts.add(firstTurnPt);
	   
	   g2.drawOval((int)firstTurnPtX, (int)firstTurnPtY, 4, 4);
	   Point fourthDepthPt = ((Tooth)vecTeethR.get(toothNo+1)).getDepthPt();
	   Point fifthDepthPt = ((Tooth)vecTeethR.get(toothNo+2)).getDepthPt();
	   //slope(m) = (Y2-Y1)/(X2-X1)
	   double mOf45depthLine = (fifthDepthPt.y-fourthDepthPt.y) / (fifthDepthPt.x-fourthDepthPt.x);
	   
	   double c1 = midPtOfThirdFourthMid.y - perpendiM*midPtOfThirdFourthMid.x;
	   double c2 = fourthDepthPt.y - mOf45depthLine*fourthDepthPt.x;
//       Two lines y = m1x+c1 and y = m2x+c2 
//       x = (c2-c1)/(m1-m2)
//       y = m1x + c1 = m1(c2-c1)/(m1-m2) + c1
	   double slopediff = (perpendiM-mOf45depthLine);
	   double ydiff = (c2 -c1);
	   double doublex = ydiff/slopediff;
	   int interx = (int)doublex;
	   int intery = (int)(mOf45depthLine*interx)+(int)c2;
	   
	   Point secondTurnPt = new Point(interx,intery);
	   vecRightTurningPts.add(secondTurnPt);
	   
	   System.out.println("The second turn is at :: "+interx+","+ intery);
	   g2.drawOval(interx, intery, 4, 4);
	   }
	   
   }
   public void calculateDepth(Tooth tooth) {
	   Point srcpnt = tooth.getStpnt();
	   Point dstpnt = tooth.getEndpoint(); 
	   //double len = Math.sqrt((yy-1)*(yy-1) + (xx-120)*(xx-120));
	   int midx =(srcpnt.x+dstpnt.x)/2;
	   int midy = (srcpnt.y+dstpnt.y)/2;
	   g2.drawOval(midx,midy, 2,2);
	   
	   //Slope(m) = (Y2-Y1)/(X2-X1)
	   double xgap = (dstpnt.x-srcpnt.x);
	   double ygap = (dstpnt.y-srcpnt.y);
	   System.out.println("xgap :"+xgap+"  , ygap : "+ygap );
	   double m = ygap/xgap;
	   System.out.println("Mid Point is:: ("+midx+","+midy+") and slope is : "+m);
	   //Slope of the perpendicular line = -1/m
	   double perpendiM = -1/m;
	   
	   //The point newx on the line is:- 
//	   newx = midx+sqrt(d^2/(1+m^2));
//	   Here d = the given depth = 9mm(say);
	   double newx = 0.0;
	   if (tooth.getToothNo().equals("11") || tooth.getToothNo().equals("12") || tooth.getToothNo().equals("13")
			   || tooth.getToothNo().equals("21") || tooth.getToothNo().equals("22") || tooth.getToothNo().equals("23"))
	     {
	    	 tooth.setDepth(42);
	     }
	   else if (tooth.getToothNo().equals("14") || tooth.getToothNo().equals("15")
			   || tooth.getToothNo().equals("24") || tooth.getToothNo().equals("25"))
	     {
	    	 tooth.setDepth(63);
	     }
	   else if (tooth.getToothNo().equals("16") || tooth.getToothNo().equals("17")
			   || tooth.getToothNo().equals("26") || tooth.getToothNo().equals("27"))
	     {
	    	 tooth.setDepth(78);
	     }
	   if (dstpnt.x > srcpnt.x)
	   {
		   newx = midx-Math.sqrt(tooth.getDepth()*tooth.getDepth()/(1+perpendiM*perpendiM));
	   }else
		   newx = midx+Math.sqrt(tooth.getDepth()*tooth.getDepth()/(1+perpendiM*perpendiM));   
	   
//	   g2.drawOval(newx,newy, 3,1);
	   //So new y would be :- 
	   //newy = perpendiM(newx-midx)+midy;
	   double newy = perpendiM*(newx-midx)+midy;
	   System.out.println("new X at "+depth+" pixes is : "+newx+" newy:: "+newy);
	   Point p = new Point((int)newx,(int)newy);
	   tooth.setMidpnt(new Point(midx,midy));
	   tooth.setDepthPt(p);
	   g2.drawOval(midx,midy, 3,3);
	   g2.drawLine((int)midx, (int)midy, (int)newx, (int)newy);
	   System.out.println("Radius : " + (int)Math.sqrt(((newx - midx)*(newx - midx))+((newy - midy)*(newy - midy))));
   }
   public void calculateDepth() {
	   Point srcpnt = (Point)v.get(v.size()-2);
	   Point dstpnt = (Point)v.get(v.size()-1); 
	   //double len = Math.sqrt((yy-1)*(yy-1) + (xx-120)*(xx-120));
	   int midx =(srcpnt.x+dstpnt.x)/2;
	   int midy = (srcpnt.y+dstpnt.y)/2;
	   g2.drawOval(midx,midy, 2,2);
	   
	   //Slope(m) = (Y2-Y1)/(X2-X1)
	   double xgap = (dstpnt.x-srcpnt.x);
	   double ygap = (dstpnt.y-srcpnt.y);
	   System.out.println("xgap :"+xgap+"  , ygap : "+ygap );
	   double m = ygap/xgap;
	   System.out.println("Mid Point is:: ("+midx+","+midy+") and slope is : "+m);
	   if (m ==0)
		   m= 0.15;
	   //Slope of the perpendicular line = -1/m
	   double perpendiM = -1/m;
	   
	   //The point newx on the line is:- 
//	   newx = midx+sqrt(d^2/(1+m^2));
//	   Here d = the given depth = 9mm(say);
	   double newx = 0.0;
	   if (dstpnt.x > srcpnt.x)
	   {
		   newx = midx-Math.sqrt(depth*depth/(1+perpendiM*perpendiM));
	   }else
		   newx = midx+Math.sqrt(depth*depth/(1+perpendiM*perpendiM));   
	   
//	   g2.drawOval(newx,newy, 3,1);
	   //So new y would be :- 
	   //newy = perpendiM(newx-midx)+midy;
	   double newy = perpendiM*(newx-midx)+midy;
	   System.out.println("new X at "+depth+"pixes is : "+newx+" newy:: "+newy);
	   Point p = new Point((int)newx,(int)newy);
	   g2.drawOval(midx,midy, 1,1);
	   g2.drawLine((int)midx, (int)midy, (int)newx, (int)newy);
	   System.out.println("Radius : " + (int)Math.sqrt(((newx - midx)*(newx - midx))+((newy - midy)*(newy - midy))));
   }
   public void doSecondTurn() {
	   Vector v = new Vector();
	   Point pnt1 = new Point((int)113.12,(int)87.12);
	   Point pnt2 = new Point((int)99.10,(int)103.98);
	   v.add(pnt1);
	   v.add(pnt2);
	   Point srcpnt = (Point)v.get(v.size()-2);
	   Point dstpnt = (Point)v.get(v.size()-1); 
	   //double len = Math.sqrt((yy-1)*(yy-1) + (xx-120)*(xx-120));
//	   int midx =(srcpnt.x+dstpnt.x)/2;
//	   int midy = (srcpnt.y+dstpnt.y)/2;
	   g2.drawLine(srcpnt.x,srcpnt.y, dstpnt.x,dstpnt.y);
//	   g2.drawOval(srcpnt.x,srcpnt.y, 3,3);

	   //Slope(m) = (Y2-Y1)/(X2-X1)
	   double xgap = (dstpnt.x-srcpnt.x);
	   double ygap = (dstpnt.y-srcpnt.y);
	   System.out.println("xgap :"+xgap+"  , ygap : "+ygap );
	   double m = ygap/xgap;
	   System.out.println("Slope is : "+m);
	   
	   if (m ==0)
		   m= 0.15;
	   //Slope of the perpendicular line = -1/m
	   double perpendiM = -1/m;
	   
	   //The point newx on the line is:- 
//	   newx = midx+sqrt(d^2/(1+m^2));
//	   Here d = the given depth = 9mm(say);
	   double newdepth = (7/3)*5;
	   double newx = srcpnt.x+Math.sqrt(newdepth*newdepth/(1+m*m));
	   
//	   g2.drawOval(newx,newy, 3,1);
	   //So new y would be :- 
	   //newy = perpendiM(newx-midx)+midy;
	   double newy = m*(newx-srcpnt.x)+srcpnt.y;
	   System.out.println("new X at "+newdepth +"mm is : "+newx+" newy:: "+newy);
	   Point p = new Point((int)newx,(int)newy);
	   g2.drawOval((int)newx,(int)newy, 1,1);
	   g2.drawLine((int)srcpnt.x, (int)srcpnt.y, (int)newx, (int)newy);
//	   System.out.println("Radius : " + (int)Math.sqrt(((newx - midx)*(newx - midx))+((newy - midy)*(newy - midy))));
	   
   }
   public void doFourthTurn() {
	   Vector v = new Vector();
	   Point pnt1 = new Point((int)99.70,(int)132.25);
	   Point pnt2 = new Point((int)88.38,(int)169.41);
	   v.add(pnt1);
	   v.add(pnt2);
	   Point srcpnt = (Point)v.get(v.size()-2);
	   Point dstpnt = (Point)v.get(v.size()-1); 
	   //double len = Math.sqrt((yy-1)*(yy-1) + (xx-120)*(xx-120));
//	   int midx =(srcpnt.x+dstpnt.x)/2;
//	   int midy = (srcpnt.y+dstpnt.y)/2;
	   g2.drawLine(srcpnt.x,srcpnt.y, dstpnt.x,dstpnt.y);
	   g2.drawOval(srcpnt.x,srcpnt.y, 3,3);
	   g2.drawOval(dstpnt.x,dstpnt.y, 3,3);

	   //Slope(m) = (Y2-Y1)/(X2-X1)
	   double xgap = (dstpnt.x-srcpnt.x);
	   double ygap = (dstpnt.y-srcpnt.y);
	   System.out.println("xgap :"+xgap+"  , ygap : "+ygap );
	   double m = ygap/xgap;
	   System.out.println("Slope is : "+m);
	   
	   if (m ==0)
		   m= 0.15;
	   //Slope of the perpendicular line = -1/m
	   double perpendiM = -1/m;
	   
	   //The point newx on the line is:- 
//	   newx = midx+sqrt(d^2/(1+m^2));
//	   Here d = the given depth = 9mm(say);
	   double newdepth = (10/3)*5;
	   double newx = srcpnt.x+Math.sqrt(newdepth*newdepth/(1+m*m));
	   
//	   g2.drawOval(newx,newy, 3,1);
	   //So new y would be :- 
	   //newy = perpendiM(newx-midx)+midy;
	   double newy = m*(newx-srcpnt.x)+srcpnt.y;
	   System.out.println("new X at "+newdepth +"mm is : "+newx+" newy:: "+newy);
	   Point p = new Point((int)newx,(int)newy);
	   g2.drawOval((int)newx,(int)newy, 1,1);
	   g2.drawLine((int)srcpnt.x, (int)srcpnt.y, (int)newx, (int)newy);
//	   System.out.println("Radius : " + (int)Math.sqrt(((newx - midx)*(newx - midx))+((newy - midy)*(newy - midy))));
	   
   }
   /*
   public void calculateInnerPoint(int xx, int yy) {
   
	   //double len = Math.sqrt((yy-1)*(yy-1) + (xx-120)*(xx-120));
	   int midx =(xx+120)/2;
	   int midy = (yy+0)/2;
	   //this.getGraphics().drawOval(midx,midy,4 ,4);
	   //calculate the existing line slope and b and equation 
	   double slope= (yy-1)/(xx - 120);
	   double b= yy-slope*xx;
	   
	   // Calculate perpendicular line 
	   // The perpendicular slope will be slope =-1/slope
	   // equation is  midy = -1/slope * midx + perpend_b 
	   
	   double perpend_slope= -1/slope;
	   double md = 7.0;
	   double perpend_b = midy + (midx/slope);   
	   
	   //perpendicular also passes through newx and newy points
	   // so equation can be written as newy= perpend_slope*newx + perpend_b
	   
	   
	   //calculate parallel line , slope is same as the calculated mid point line
	   // hence the slope is same as the mid point slope
	   //newy= slope*newx + b +md
	   //hence perpend_slope*newx + perpend_b = slope * newx + b +md 
	   // So newx = (b +md - perpend_b)/(perpend_slope-slope)
	   // newy = slope * newx+ b +md
	   
	   int newx = (int) ((b +md - perpend_b)/(perpend_slope-slope)+0.5);
	   int newy = (int) (slope * newx + b +md +0.5);
	   this.getGraphics().drawOval(newx,newy,2 ,2);
	   System.out.println("Inner point is : "+newx+","+newy);
	   
   }*/
   
   private static void createAndShowGui() {
      List<Integer> scores = new ArrayList<Integer>();
      /*Random random = new Random();
      int maxDataPoints = 16;
      int maxScore = 20;
     for (int i = 0; i < maxDataPoints ; i++) {
         scores.add(2*i*i);
      }*/
      
      DrawGraphNext mainPanel = new DrawGraphNext(scores);
      

      JFrame frame = new JFrame("DrawGraph");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        JPanel mypanel = new JPanel();
		mypanel.setLayout(new FlowLayout());
		mypanel.setPreferredSize(new Dimension(150, 200));
		
		JButton btnone = new JButton();
		btnone.setText("ArchForm1");
		JButton btntwo = new JButton();
		btntwo.setText("ArchForm2");
		JButton btnthree = new JButton();
		btnthree.setText("ArchForm3");
		mypanel.add(btnone);
		mypanel.add(btntwo);
		mypanel.add(btnthree);
		
		JButton btn11 = new JButton();
		btn11.setText("11");
		JTextField tf11_L = new JTextField(2);
		JTextField tf11_W = new JTextField(2);
		mypanel.add(btn11);
		mypanel.add(tf11_L);
		mypanel.add(tf11_W);
		
	  frame.getContentPane().setLayout(new FlowLayout());    
	  frame.getContentPane().add(mypanel);
      frame.getContentPane().add(mainPanel);
      frame.getContentPane().addMouseMotionListener(mainPanel);
      frame.getContentPane().addMouseListener(mainPanel);
      frame.setLocation(400, 200);
      frame.pack();
      
      //frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }
   public void calculateDistance(){
	   Point srcpnt = (Point)v.get(v.size()-1);
	   Point dstpnt = (Point)v.get(v.size()-2);
	   System.out.println("The src and dst points are :: "+srcpnt+"  "+dstpnt);
	   this.getGraphics().drawLine(187, 2, 124, 19);
	   this.getGraphics().drawLine(187, 2, 187, 19);
	   this.getGraphics().drawLine(187, 19, 124, 19);
	   
   }
   /*//Drawing inverted triangle
   public void drawTriangle(){
	   Point srcpnt = new Point(187, 2);
	   Point dstpnt = new Point(122, 20);
	   v.add(srcpnt);
	   v.add(dstpnt);
	   srcpnt = (Point)v.get(v.size()-1);
	   dstpnt = (Point)v.get(v.size()-2);
//	   System.out.println("The src and dst points are :: "+srcpnt+"  "+dstpnt);
//	   g2.drawLine(187, 2, 124, 19);//h
//	   g2.drawLine(187, 2, 187, 19);//p
//	   g2.drawLine(187, 19, 124, 19);//b
	   g2.drawLine(srcpnt.x,srcpnt.y, dstpnt.x,dstpnt.y);//h
	   g2.drawLine(srcpnt.x,srcpnt.y, srcpnt.x,dstpnt.y);//p
	   g2.drawLine(srcpnt.x,dstpnt.y, dstpnt.x, dstpnt.y);//b
	   double hypo = 0;
	   double base = srcpnt.x - dstpnt.x;
	   double normal = dstpnt.y - srcpnt.y;
	   double height = Math.sqrt(base*base + normal*normal);
	   System.out.println("The tiangle is drawn with hypotaneous :: "+height);
   }
   */
   public void drawTriangle(){
	   Point srcpnt = new Point(192, 2);
	   Point dstpnt = new Point(148, 9);

	   v.add(srcpnt);
	   v.add(dstpnt);
	   srcpnt = (Point)v.get(v.size()-2);
	   dstpnt = (Point)v.get(v.size()-1);
//	   System.out.println("The src and dst points are :: "+srcpnt+"  "+dstpnt);
//	   g2.drawLine(187, 2, 124, 19);//h
//	   g2.drawLine(187, 2, 187, 19);//p
//	   g2.drawLine(187, 19, 124, 19);//b
	   g2.drawLine(srcpnt.x,srcpnt.y, dstpnt.x,dstpnt.y);//h
	   g2.drawLine(srcpnt.x,srcpnt.y, srcpnt.x,dstpnt.y);//p
	   g2.drawLine(srcpnt.x,dstpnt.y, dstpnt.x, dstpnt.y);//b
	   double hypo = 0;
	   double base = srcpnt.x - dstpnt.x;
	   double normal = dstpnt.y - srcpnt.y;
	   double height = Math.sqrt(base*base + normal*normal);
	   System.out.println("The tiangle is drawn with hypotaneous :: "+height);
   }
   public void drawFinalCurve(){
	   
	   g2.drawLine(175, 40, 144, 47);
	   g2.drawOval(175, 40, 1, 1);
	   g2.drawLine(144, 47, 123, 58);
	   g2.drawOval(144, 47, 1, 1);
//	   first and second turn
	   g2.drawLine(123, 58, (int)111.62, (int)66.52);
	   g2.drawOval(123, 58, 1, 1);
	   g2.drawLine((int)111.62, (int)66.52, (int)119.58, (int)79.47);
	   g2.drawOval((int)111.62, (int)66.52, 1, 1);
	   ////
//	   g2.drawLine((int)124.52, (int)73.83, 113, 87);
//	   g2.drawOval((int)124.52, (int)73.83, 1, 1);
	   g2.drawLine((int)119.58, (int)79.47, 113, 87);
	   g2.drawOval((int)119.58, (int)79.47, 1, 1);
	   g2.drawLine(113, 87, 99,104);
	   g2.drawOval(113, 87, 1, 1);
	   //third and fourth turns
	   
	   g2.drawLine(99,104, 94, 114);
	   g2.drawOval(99,104, 1, 1);
	   g2.drawLine(94, 114, 103, 118);
	   g2.drawOval(94, 114, 1, 1);
	   
	   g2.drawLine(103, 118, 100, 132);
	   g2.drawOval(103, 118, 1, 1);
	   g2.drawLine(100, 132, 88,169);
	   g2.drawOval(100, 132, 1, 1);
	   
   }
   public void drawOvals(){
	   for (int i = 0;i<v.size();i++)
	   {
		   Point p = (Point)v.get(i);
		   g2.drawOval((int)p.getX(), (int)p.getY(), 2, 2);
	   }
   }
   public void drawInnerBridge(){
	   Point centerDown = new Point(cpoint.x,cpoint.y+42);//TODO
	   if (vecTeethL.size() > 0)
	   {
		   Point left = ((Tooth)vecTeethL.elementAt(0)).getDepthPt();
		   if (left != null)
		   g2.drawLine(left.x,left.y,centerDown.x,centerDown.y);
	   }
	   if (vecTeethR.size() > 0)
	   {
		   Point right = ((Tooth)vecTeethR.elementAt(0)).getDepthPt();
		   if (right != null)
		   g2.drawLine(centerDown.x,centerDown.y,right.x,right.y);
	   }
	   validate();
	   repaint();
   }
   public void calculateStep(){
//	   Math.hypot(arg0, arg1)
   }
   public void populateStatically(int opt){
	   int option = opt;
	   if (traversed_ == false)
	   {
		   Point p = null;
		   //default case
		   int px[] = {148,108,74,48,34,20,12};//9,6,7,7
			int py[] = {9,27,53,89,125,183,238};
		
			//option 1
		int px1[] = {143,108,74,48,34,20,12};//8,7,7,7
		int py1[] = {11,27,53,89,125,183,238};
			
			//option2
		int px2[] = {141,108,74,48,34,20,12};//10,6,7,7
		int py2[] = {12,27,53,89,125,183,238};
		if (opt == 1)
		{
			for (int i=0,j=0;i<px1.length && j<py1.length;i++,j++)
			{
		    
				p = new Point(px1[i],py1[i]);
				v.add(p);
			}
		}
		else if (opt == 2)
		{
			for (int i=0,j=0;i<px2.length && j<py2.length;i++,j++)
			{
		    
				p = new Point(px2[i],py2[i]);
				v.add(p);
			}
		}
		else
		{
			for (int i=0,j=0;i<px.length && j<py.length;i++,j++)
			{
		    
				p = new Point(px[i],py[i]);
				v.add(p);
	   /*
	   p = new Point(108, 27);
	   v.add(p);
	   p = new Point(74, 53);
	   v.add(p);
	   p = new Point(48, 89);
	   v.add(p);
	   p = new Point(34, 125);
	   v.add(p);
	   p = new Point(20, 183);
	   v.add(p);
	   p = new Point(12, 238);
	   v.add(p);
	   */
			}
		}
	   populateTeethObjects();
	   
	   v.clear();
	   //Right
	   
	   p = new Point(253, 14);
	   v.add(p);
	   p = new Point(288, 31);
	   v.add(p);
	   p = new Point(319, 57);
	   v.add(p);
	   p = new Point(345, 92);
	   v.add(p);
	   p = new Point(358, 121);
	   v.add(p);
	   p = new Point(373, 181);
	   v.add(p);
	   p = new Point(382, 235);
	   v.add(p);
	   populateTeethObjects();
	   traversed_ = true;
	   }
   }
   public void populateTeethObjects(){
	   for (int i =0;i<v.size();i++)
	   {
		   
	   toothWidth_ = 7;
//		window.dispose();
//		Point pnt = me.getPoint();
	   Point pnt = (Point)v.get(i);
		Tooth tooth = new Tooth();
		if (cpoint.getX() > pnt.getX())
		{
	    if (vecTeethL.size() == 0)
	     tooth.setToothNo("11");
	    else if (vecTeethL.size() == 1)
	     tooth.setToothNo("12");
	    else if (vecTeethL.size() == 2)
		 tooth.setToothNo("13");
	    else if (vecTeethL.size() == 3)
	     tooth.setToothNo("14");
	    else if (vecTeethL.size() == 4)
	     tooth.setToothNo("15");
	    else if (vecTeethL.size() == 5)
	     tooth.setToothNo("16");
	    else if (vecTeethL.size() == 6)
	     tooth.setToothNo("17");
	    
	    if (vecTeethL.size() == 0)
	    	tooth.setStpnt(cpoint);
	    else
	    	tooth.setStpnt((Point)(((Tooth)vecTeethL.lastElement()).endpoint));
		}
		else  if (cpoint.getX() < pnt.getX())//Calculation For Right
		{
		    if (vecTeethR.size() == 0)
		     tooth.setToothNo("21");
		    else if (vecTeethR.size() == 1)
		     tooth.setToothNo("22");
		    else if (vecTeethR.size() == 2)
			 tooth.setToothNo("13");
		    else if (vecTeethR.size() == 3)
		     tooth.setToothNo("14");
		    else if (vecTeethR.size() == 4)
		     tooth.setToothNo("15");
		    else if (vecTeethR.size() == 5)
		     tooth.setToothNo("16");
		    else if (vecTeethR.size() == 6)
		     tooth.setToothNo("17");
		    
		    if (vecTeethR.size() == 0)
		    	tooth.setStpnt(cpoint);
		    else
		    	tooth.setStpnt((Point)(((Tooth)vecTeethR.lastElement()).endpoint));
			}
//	    v.add(pnt);
	     tooth.setEndpoint(pnt);
	     calculateDepth(tooth);
   	 tooth.setWidth(toothWidth_);
	     
	     
	     
//	     int xpos = pnt.getX();
//	     int ypos = me.getY();
//	     //graphPoints.add(pnt);
//	     System.out.println("Cliked Point is :"+xpos+" "+ypos);
	     
	     this.getGraphics().setColor(Color.CYAN);
	     this.getGraphics().drawOval((int)pnt.getX(),(int)pnt.getY(),3 ,3);
//	     v.add(pnt);
	     if (cpoint.getX() > pnt.getX())
	    	 vecTeethL.add(tooth);
	     else 
	    	 vecTeethR.add(tooth);
	   }
   }
   public void plotGraph(int option)
   {
System.out.println("DrawGraph::option :: "+option);
	   option_ = option;
	   v.clear();
	   traversed_ = false;
	   populateStatically(option);
   }
   public void clear(){
	   vecTeethL.clear();
	   vecTeethR.clear();
//	   validate();
//	   repaint();
   }
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
        	 createAndShowGui();
         }
      });
   }
}