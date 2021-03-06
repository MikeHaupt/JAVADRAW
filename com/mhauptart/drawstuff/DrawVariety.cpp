//
// DrawVariety.java
//
package com.mhauptart.drawvariety;

import java.lang.Math;
import Graph3D.java;

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.shape.LineTo; 
import javafx.scene.shape.MoveTo; 
import javafx.scene.shape.Path; 


public class DrawVariety extends Application {

    double pi = 3.141592;
    int size = 70;

    double vecTorX[][] = new double[size][size];
    double vecTorY[][] = new double[size][size];
    double vecTorZ[][] = new double[size][size];

    double paramT[][] = new double[size][size];
    double paramU[][] = new double[size][size];
    
    Graph3D somePic = new Graph3D(-10,10,-10,10,-10,10);

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
        vecTorX[i][j] = cos(U)*(a+cos(1/2 * U)*(sin(V)) - sin(1/2*U)*sin(2*V));
        vecTorY[i][j] = sin(U)*(a + cos(1/2 * U)*(sin(V)) - sin(1/2*U)*sin(2*V));
        vecTorZ[i][j] = sin(1/2 * U)*(sin(V)) + cos(1/2*U)*sin(2*V);
        }

    char3 color;
    color.red = 0;
    color.green = 0;
    color.blue = 0;

    somePic.drawVec(vecTorX,vecTorY,vecTorZ,size,pi,pi/3.0,pi/4.0,1,3,color,0);
}

/*/*
int main() {
    char outputName[] = "output.bmp";
    Graph3D somePic = new Graph3D(-10,10,-10,10,-10,10);

    int size =70;

    MatDoub vecTorX(size,size);
    MatDoub vecTorY(size,size);
    MatDoub vecTorZ(size,size);

    MatDoub ParamT(size,size);
    MatDoub ParamU(size,size);

    //for(double i = -1;i < 1;i+= 2/static_cast<double>(size)){
    //for(int i = 0;i < size;i++){
    //   ParamT[i] = static_cast<Doub>(i) * 2 / static_cast<Doub>(size) - 1;
    //   ParamU[i] = static_cast<Doub>(i) * 2 / static_cast<Doub>(size) - 1;
    //}

    Doub Tresolution = 9.0/size;
    Doub Uresolution = 9.0/size;
    for(int j = 0;j < size;j++)
    for(int i = 0;i < size;i++) ParamT[i][j] = static_cast<Doub>(i)*Tresolution;
    for(int j = 0;j < size;j++)
    for(int i = 0;i < size;i++) ParamU[i][j] = static_cast<Doub>(j) * Uresolution;

    for(int i = 0;i < size;i++)
    for(int j = 0;j < size;j++){
    Doub U = ParamT[i][j];
    Doub V = ParamU[i][j];
    Doub a = 4;
    vecTorX[i][j] = cos(U)*(a+cos(1/2 * U)*(sin(V)) - sin(1/2*U)*sin(2*V));
    vecTorY[i][j] = sin(U)*(a + cos(1/2 * U)*(sin(V)) - sin(1/2*U)*sin(2*V));
    vecTorZ[i][j] = sin(1/2 * U)*(sin(V)) + cos(1/2*U)*sin(2*V);
    }


    char3 color;
    color.red = 0;
    color.green = 0;
    color.blue = 0;
/*
    srand(time(NULL));

    somePic.genLinSpace(vecTorX,vecTorY,size);
    for(int i = 0;i < size;i++)
       for(int j = 0;j < size;j++){
        vecTorZ[i][j] = -3 
	+ static_cast <float> (rand()) /( static_cast <float> (RAND_MAX/(6)));
        }

                    				//sqrt(25-pow(vecTorX[i][j],2) 
                                                //          -pow(vecTorY[i][j],2)); 
    char3 color;
    color.red = 0;
    color.green = 0;
    color.blue = 0;

    VecDoub vecX(size);
    VecDoub vecY(size);
   
    for(int i = 0;i < size;i++){
    vecX[i] = vecTorX[i][0];
    vecY[i] = vecTorY[0][i];
    }

    Spline2D_interp myfunc(vecX,vecY,vecTorZ);

    size = 70;   
    
    MatDoub vecTorXX(size,size);
    MatDoub vecTorYY(size,size);
    MatDoub vecTorZZ(size,size);    

    somePic.genLinSpace(vecTorXX,vecTorYY,size);
    
    for(int i = 0;i < size;i++)
       for(int j = 0;j < size;j++) vecTorZZ[i][j] = myfunc.interp(vecTorXX[i][j],
								  vecTorYY[i][j]); 
  */

    
    somePic.drawVec(vecTorX,vecTorY,vecTorZ,size,pi,pi/3,pi/4,1,3,color,0);
/*
    color.red = 255;

    somePic.genLinSpace(vecTorX,vecTorY,size);
    for(int i = 0;i < size;i++)
       for(int j = 0;j < size;j++) vecTorZ[i][j] = -sqrt(25-pow(vecTorX[i][j],2) 
                                                          -pow(vecTorY[i][j],2)); 

    somePic.drawVec(vecTorX,vecTorY,vecTorZ,size,pi/5,0,pi/8,0.7,3,color,0);
   */ 
    somePic.saveGraph(outputName);
  return 0;

}
