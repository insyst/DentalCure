package com.epic.widgets;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel implements MouseMotionListener, MouseListener{
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
   Vector v = new Vector();
   Graphics2D g2 = null;
   double depth = 65;
   
   public DrawGraph(List<Integer> scores) {
      this.scores = scores;
      v.clear();
      
      addMouseMotionListener(this);
      addMouseListener(this);
     // drawTriangle();
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
System.out.println("Graphics is :: "+g.hashCode());      
      g2 = (Graphics2D)g;
      System.out.println("GraphicsG2 is 111:: "+g2.hashCode());
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
      
      try {         
    	  final String imageFile = "/standard.jpg";
    	  File file = new File(getClass().getResource("/standard.jpeg").toURI());
    	  //System.out.println("File is:: "+file.getPath()+"   <<"+file.getAbsolutePath()+">>"+file.hashCode());
      BufferedImage image = ImageIO.read(file);
      g2.drawImage(image,0,0,null);
      System.out.println("Graphics is 222:: "+g2.hashCode());
      } catch (Exception ex) {
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
      System.out.println("Graphics is 333:: "+g.hashCode());      
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);         
      }
      System.out.println("Graphics is ::4444 "+g.hashCode());
      g2.setStroke(oldStroke);      
      g2.setColor(GRAPH_POINT_COLOR);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g2.fillOval(x, y, ovalW, ovalH);
      }
      System.out.println("Graphics is 5555:: "+g.hashCode());
      drawTriangle();
//      calculateDepth();;
//      calculateStep();
//      
//      doSecondTurn();
//      doFourthTurn();
//      drawFinalCurve();
   }
   
   public void mouseMoved(MouseEvent ee) {
       double xxpt = ee.getPoint().getX();
       double yypt = ee.getPoint().getY();
	   double wid = Math.sqrt((yypt-1)*(yypt-1) + (xxpt-120)*(xxpt-120)); 
	   setToolTipText("MD: "+ Math.round(wid));
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
     Point pnt = me.getPoint();
     int xpos = me.getX();
     int ypos = me.getY();
     //graphPoints.add(pnt);
     System.out.println("Cliked Point is :"+xpos+" "+ypos);
     
     this.getGraphics().setColor(Color.RED);
     this.getGraphics().drawOval(xpos,ypos,3 ,3);
     v.add(pnt);
     if (v.size() >=2)
     {
    	 calculateDistance();
     }
//     calculateInnerPoint(xpos,ypos);
    }
    
   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
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
	   double newx = midx+Math.sqrt(depth*depth/(1+perpendiM*perpendiM));
	   
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
	   double newdepth = 17.5;
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
	   double newdepth = 15;
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
      
      DrawGraph mainPanel = new DrawGraph(scores);
      

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


	   Point srcpnt = new Point(30, 133);
	   Point dstpnt = new Point(20, 177);

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
	   g2.drawLine((int)111.62, (int)66.52, (int)124.52, (int)73.83);
	   g2.drawOval((int)111.62, (int)66.52, 1, 1);
	   ////
	   g2.drawLine((int)124.52, (int)73.83, 113, 87);
	   g2.drawOval((int)124.52, (int)73.83, 1, 1);
	   g2.drawLine(113, 87, 99,104);
	   g2.drawOval(113, 87, 1, 1);
	   //third and fourth turns
	   
	   g2.drawLine(99,104, 94, 114);
	   g2.drawOval(99,104, 1, 1);
	   g2.drawLine(94, 114, 106, 108);
	   g2.drawOval(94, 114, 1, 1);
	   
	   g2.drawLine(106, 108, 100, 132);
	   g2.drawOval(106, 108, 1, 1);
	   g2.drawLine(100, 132, 88,169);
	   g2.drawOval(100, 132, 1, 1);
	   
   }
   public void calculateStep(){
//	   Math.hypot(arg0, arg1)
   }
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
        	 createAndShowGui();
         }
      });
   }
}