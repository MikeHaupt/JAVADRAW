#include"image.h"
#include<iostream>

#include"graph3D.h"

#include"../recipes/code/nr3.h"

using namespace std;

Graph3D::Graph3D(Doub xnegX,Doub xposX,Doub ynegY,Doub yposY,Doub znegZ,Doub zposZ)
{
negX = xnegX;
posX = xposX;
negY = ynegY;
posY = yposY;
negZ = znegZ;
posZ = zposZ;
}

void Graph3D::genLinSpace(MatDoub &vecDatX,MatDoub &vecDatY,const int size)
{
Doub Xresolution = (posX - negX)/size;
Doub Yresolution = (posY - negY)/size;
for(int j = 0;j < size;j++)
for(int i = 0;i < size;i++) vecDatX[i][j] = static_cast<Doub>(i)*Xresolution + negX;
for(int j = 0;j < size;j++)
for(int i = 0;i < size;i++) vecDatY[j][i] = static_cast<Doub>(i)*Yresolution + negY;
}


/*void Graph2D::drawGrid(double scale)
{



}*/

/*void Graph2D::drawAxis()
{
int length = 2000;

//point2D *Axis = new point2D[length];
VecDoub AxisX(length);
VecDoub AxisY(length);


genLinSpace(AxisX,length);
for(int i = 0;i < length;i++) AxisY[i] = 0; 

char3 color;
color.red = 0;
color.blue = 0;
color.green = 0;

drawVec(AxisX,AxisY,length,6,color);

for(int i = 0;i < length;i++) AxisY[i] = AxisX[i];
for(int i = 0;i < length;i++) AxisX[i] = 0;

drawVec(AxisX,AxisY,length,6,color);


//delete [] Axis;
}*/


////////////////
void Graph3D::drawVec(const MatDoub &vecDatX,const MatDoub &vecDatY,const MatDoub &vecDatZ,
               const Int m,const Doub angleX,const Doub angleY,const Doub angleZ,
               const Doub zoom,const Int brushsize,const char3 color,const bool points)
{
//const Doub pi = 3.141592;

VecInt convertX(m);
VecInt convertZ(m);

Doub Xdist = posX - negX;
Doub Zdist = posZ - negZ;


Doub offsetX = fabs(negX);
Doub offsetZ = fabs(negZ);

Doub Xscale = static_cast<Doub>(WIDTH)/Xdist;
Doub Zscale = static_cast<Doub>(HEIGHT)/Zdist;

Doub x,y,z;

for(int k = 0;k < 2;k++)
for(int j = 0;j < m;j++)
{
  for(int i = 0;i < m;i++)
   {
   Doub xx,yy,zz;
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
   transform(x,y,z,xx,yy,zz,angleX,angleY,angleZ);

   //if(xx > posX || xx < negX || yy > posY || yy < negY || zz > posZ || zz < negZ) continue; 
   xx*=zoom;
   zz*=zoom;

   convertX[i] = static_cast<int>( xx * Xscale + offsetX * Xscale);
   convertZ[i] = static_cast<int>( zz * Zscale + offsetZ * Zscale);
   }
  if(points)
  for(int i = 0;i < m;i++)
   {
   DrawLine(convertX[i],convertZ[i],convertX[i],convertZ[i],brushsize,color);
   }
  else
  for(int i = 0;i < m-1;i++)
   {
   DrawLine(convertX[i],convertZ[i],convertX[i+1],convertZ[i+1],brushsize,color);
   }

}
}
///////////////

void Graph3D::saveGraph(char *fileName)
{
write(fileName);
}

void Graph3D::transform(const Doub x,const Doub y,const Doub z,Doub &xx,Doub &yy,Doub &zz,
                 const Doub angleX,const Doub angleY,const Doub angleZ)
{
///Rx
xx = x;
yy = cos(angleX)*y - sin(angleX)*z;
zz = sin(angleX)*y + cos(angleX)*z;

Doub tempx = xx;
Doub tempy = yy;
Doub tempz = zz;


//Ry
xx = cos(angleY)*tempx + sin(angleY)*tempz;
yy = tempy;
zz = -sin(angleY)*tempx + cos(angleY)*tempz;

tempx = xx;
tempy = yy;
tempz = zz;

//Rz
xx = cos(angleZ)*tempx - sin(angleZ)*tempy;
yy = sin(angleZ)*tempx + cos(angleZ)*tempy;
zz = tempz;
}



