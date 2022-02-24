/*Begining to try to draw*/
//1/30/2022

package com.mhauptart.drawstuff;

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.*; 
import javafx.stage.*; 
import javafx.scene.shape.LineTo; 
import javafx.scene.shape.MoveTo; 
import javafx.scene.shape.Path; 
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

import java.lang.Math;
         
public class DrawStuff extends Application 
{ 

   private double negX,posX,negY,posY,negZ,posZ;
   private Path path;
   private Path path2;
   private LineTo r;
   private int WIDTH = 600;
   private int HEIGHT = 600;
   private MoveTo moveTo;
   private MoveTo moveTo2;
   private LineTo lines[][];
   private LineTo lines2[][];
   private double pi = 3.141592;

   Button btnZoomIn;
   Button btnZoomOut;
   Button btnAngPlusX;
   Button btnAngMinusX;
   Button btnAngPlusY;
   Button btnAngMinusY;
   Button btnAngPlusZ;
   Button btnAngMinusZ;

   double angleX = pi/4.0;
   double angleY = pi/2.0;
   double angleZ = pi/2.0;
   double zoom = 1.0;
   int gridlines = 60;
   Color color;

   Group root;
   Scene scene;
   //Scene scene;
   //Scene scene2;
   

   @Override public void start(Stage primaryStage) 
   { 
      Stage stage = new Stage();
       
      //Creating a Path 
      path = new Path();
      path2 = new Path();
      r = new LineTo();
      root = new Group();
      color = Color.rgb(255,255,255);
      
      btnZoomIn = new Button();
      btnZoomOut = new Button();
      btnAngPlusX = new Button();
      btnAngMinusX = new Button();
      btnAngPlusY = new Button();
      btnAngMinusY = new Button();
      btnAngPlusZ = new Button();
      btnAngMinusZ = new Button();
      

      negX = -10.0;
      posX = 10.0;
      negY = -10.0;
      posY = 10.0;
      negZ = -10.0;
      posZ = 10.0;

      

      lines = new LineTo[gridlines][gridlines];

      this.GetVariety(angleX,angleY,angleZ,zoom,gridlines);

      btnZoomIn.setText("Zoom +");
      btnZoomIn.setOnAction(e -> { GetVariety(angleX,angleY,angleZ,zoom += 0.2,gridlines); });

      btnZoomOut.setText("Zoom -");
      btnZoomOut.setOnAction(e -> { GetVariety(angleX,angleY,angleZ,zoom -= 0.2,gridlines); });

      btnAngPlusX.setText("Angle X +");
      btnAngPlusX.setOnAction(e -> { GetVariety(angleX+=0.2,angleY,angleZ,zoom,gridlines); });

      btnAngMinusX.setText("Angle X -");
      btnAngMinusX.setOnAction(e -> { GetVariety(angleX-=0.2,angleY,angleZ,zoom,gridlines); });

      btnAngPlusY.setText("Angle Y +");
      btnAngPlusY.setOnAction(e -> { GetVariety(angleX,angleY+=0.2,angleZ,zoom,gridlines); });

      btnAngMinusY.setText("Angle Y -");
      btnAngMinusY.setOnAction(e -> { GetVariety(angleX,angleY-=0.2,angleZ,zoom,gridlines); });

      btnAngPlusZ.setText("Angle Z +");
      btnAngPlusZ.setOnAction(e -> { GetVariety(angleX,angleY,angleZ+=0.2,zoom,gridlines); });

      btnAngMinusZ.setText("Angle Z -");
      btnAngMinusZ.setOnAction(e -> { GetVariety(angleX,angleY,angleZ-=0.2,zoom,gridlines); });
  
       
      HBox pane = new HBox(10);
      pane.getChildren().addAll(btnZoomIn,
                                    btnZoomOut,
                                    btnAngPlusX,
                                    btnAngMinusX,
                                    btnAngPlusY,
                                    btnAngMinusY,
                                    btnAngPlusZ,
                                    btnAngMinusZ);

      //Creating a Group object  
      //root = new Group(r,path,path2); 
      root = new Group(path,path2);
      
         
      //Creating a scene object 
      Scene scene = new Scene(root, WIDTH, HEIGHT);  
      Scene scene2 = new Scene(pane, 600, 75);
      
      //Setting title to the Stage 
      stage.setTitle("Parametric Surface");
      
      //Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show();  

      primaryStage.setScene(scene2);
      primaryStage.setTitle("controls");
      primaryStage.show();       
   } 





//////////////////////////////////////////////////////////////////////////////
   public void GetVariety(double angleX,double angleY,double angleZ,double zoom,int gridlines) 
   {

    //double pi = 3.141592;
    int size = gridlines;

    double vecTorX[][] = new double[size][size];
    double vecTorY[][] = new double[size][size];
    double vecTorZ[][] = new double[size][size];

    double ParamT[][] = new double[size][size];
    double ParamU[][] = new double[size][size];
    

    double Tresolution = 9.0 / (double) size;
    double Uresolution = 9.0 / (double) size;

    for(int j = 0;j < size;j++)
        for(int i = 0;i < size;i++) ParamT[i][j] = (double) (i) * Tresolution;

    for(int j = 0;j < size;j++)
        for(int i = 0;i < size;i++) ParamU[i][j] = (double) (j) * Uresolution;
        

    for(int i = 0;i < size;i++)
        for(int j = 0;j < size;j++){
        double U = ParamT[i][j];
        double V = ParamU[i][j];
        double a = 4.0;
        vecTorX[i][j] = Math.cos(U)*(a+Math.cos(1/2 * U)*(Math.sin(V)) - Math.sin(1/2*U)*Math.sin(2*V));
        vecTorY[i][j] = Math.sin(U)*(a + Math.cos(1/2 * U)*(Math.sin(V)) - Math.sin(1/2*U)*Math.sin(2*V));
        vecTorZ[i][j] = Math.sin(1/2 * U)*(Math.sin(V)) + Math.cos(1/2*U)*Math.sin(2*V);
        }


         this.drawVec(vecTorX,vecTorY,vecTorZ,gridlines,angleX,angleY,angleZ,zoom); 
   }

   ///////////////////////////////////////////////////
   public void drawVec(double vecDatX[][],double vecDatY[][],double vecDatZ[][],int gridlines,double angleX,
                        double angleY,double angleZ,double zoom)
   {
      moveTo = new MoveTo();
      moveTo2 = new MoveTo();
      lines = new LineTo[gridlines][gridlines];
      lines2 = new LineTo[gridlines][gridlines];

      for(int i = 0; i < gridlines;i++)
      for(int j = 0;j<gridlines;j++)
      {
         lines[i][j] = new LineTo();
         //lines2[i][j] = new LineTo();
      }

       for(int i = 0; i < gridlines;i++)
      for(int j = 0;j<gridlines;j++)
      {
         //lines[i][j] = new LineTo();
         lines2[i][j] = new LineTo();
      }


      int convertX[][] = new int[gridlines][gridlines];
      int convertZ[][] = new int[gridlines][gridlines];

      double Xdist = posX - negX;
      double Zdist = posZ - negZ;

      double offsetX = Math.abs(negX);
      double offsetZ = Math.abs(negZ);

      double Xscale = (double)(WIDTH)/Xdist;
      double Zscale = (double)(HEIGHT)/Zdist;

      double x,y,z;
      int startX = 100;
      int startZ = 100;

      for(int k = 0;k < 2;k++)
      for(int j = 0;j < gridlines;j++)
      {
      for(int i = 0;i < gridlines;i++)
      {
         double xx,yy,zz;
         if(k == 0){
            x = vecDatX[i][j];
            y = vecDatY[i][j];
            z = vecDatZ[i][j];   
         }
         else{
            x = vecDatX[j][i];
            y = vecDatY[j][i];
            z = vecDatZ[j][i];   
         }
   //iif(x > posX || x < negX || y > posY || y < negY || z > posZ || z < negZ) continue; 
      double point[] = new double[3]; 
      point[0] = x;
      point[1] = y;
      point[2] = z;
      this.transform(point,angleX,angleY,angleZ);
      x = point[0];
      y = point[1];
      z = point[2];

   //if(xx > posX || xx < negX || yy > posY || yy < negY || zz > posZ || zz < negZ) continue; 
      x*=zoom;
      z*=zoom;

      convertX[j][i] = (int)( x * Xscale + offsetX * Xscale);
      convertZ[j][i] = (int)( z * Zscale + offsetZ * Zscale);
      }
      }
     
      //r = new Rectangle();
      


      //color = Color.rgb(255,255,255);
      //scene.BackgroundFill(color);
      
      
      path.getElements().clear();

      moveTo.setX(convertX[0][0]);
      moveTo.setY(convertZ[0][0]);
      path.getElements().add(moveTo);

      for(int i = 0;i < gridlines;i++)
      for(int j = 0;j < gridlines;j++){
         lines[i][j].setX(convertX[i][j]);
         lines[i][j].setY(convertZ[i][j]);
         path.getElements().add(lines[i][j]);
         //path.getElements().add(lines2[j][i]);
      }

      //path.getElements().add(moveTo);

      for(int i = 0;i < gridlines;i++)
      for(int j = 0;j < gridlines;j++){
            int temp = convertX[i][j];
            int temp2 = convertZ[i][j];
            convertX[i][j] = convertX[j][i];
            convertZ[i][j] = convertZ[j][i];
            convertX[j][i] = temp;
            convertZ[j][i] = temp2;

      }
       
      path2.getElements().clear();

      moveTo2.setX(convertX[0][0]);
      moveTo2.setY(convertZ[0][0]);
      path2.getElements().add(moveTo2);

      for(int i = 0;i < gridlines;i++){
         moveTo2.setX(convertX[0][0]);
         moveTo2.setY(convertZ[0][0]);
         path2.getElements().add(moveTo2);
      
      for(int j = 0;j < gridlines;j++){         
         lines2[i][j].setX(convertX[i][j]);
         lines2[i][j].setY(convertZ[i][j]);
         
         
         path2.getElements().add(lines2[j][i]);
         //path.getElements().add(lines2[j][i]);
      }
      }
      //root.getChildren().addAll(r,path,path2);
      
      
   }

   ////////////
   public void transform(double point[],double angleX,double angleY,double angleZ)
   {
      double xx,yy,zz;
      double x,y,z;
      x = point[0];
      y = point[1];
      z = point[2];

      xx = x;
      yy = Math.cos(angleX)*y - Math.sin(angleX)*z;
      zz = Math.sin(angleX)*y + Math.cos(angleX)*z;

      double tempx = xx;
      double tempy = yy;
      double tempz = zz;


//Ry
      xx = Math.cos(angleY)*tempx + Math.sin(angleY)*tempz;
      yy = tempy;
      zz = -Math.sin(angleY)*tempx + Math.cos(angleY)*tempz;

      tempx = xx;
      tempy = yy;
      tempz = zz;

//Rz
      xx = Math.cos(angleZ)*tempx - Math.sin(angleZ)*tempy;
      yy = Math.sin(angleZ)*tempx + Math.cos(angleZ)*tempy;
      zz = tempz;

      point[0] = xx;
      point[1] = yy;
      point[2] = zz;
   }





   public static void main(String args[]){ 
      launch(args); 
   } 
}       