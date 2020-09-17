package FinalProject_2020_ASubramanian.CowSheepGrassCellSpace;

import simView.*;
import twoDCellSpace.TwoDimCellSpace;
import twoDCellSpace.lightSpreadCell;
import genDevs.modeling.*;
import genDevs.simulation.*;
import genDevs.simulation.heapSim.HeapCoord;
import genDevs.simulation.realTime.*;
import GenCol.*;
import genDevs.plots.*;
import java.util.*;
import java.awt.*;

import java.text.*;
import java.io.*;

public class CowSheepGrassCellSpace extends TwoDimCellSpace {


	static String[][] cellphase=new String[30][30]; //two dim array to find neighbors
	static int testcase=10;//change the test cases here (6 for SheepGrass & 10 for CowSheepGrass)
  	public CowSheepGrassCellSpace() 
  	{	
	     this(30,30);
	     
	  }
  	public void Setphase(int xc,int yc,String p)
  	{
  		cellphase[xc][yc]= p;
  	}
  	

	public CowSheepGrassCellSpace(int xDim, int yDim) 
	{
		super("CowSheepGrassCellSpace", xDim, yDim);
		this.numCells = xDim * yDim;
		for (int i = 0; i < xDimCellspace; i++) 
		{
		      for (int j = 0; j < yDimCellspace; j++) 
		      {
		    	  
		    	  CowSheepGrassCell fc = new CowSheepGrassCell(i, j);
		    	  
		    	  addCell(fc, xDimCellspace, yDimCellspace);
		    	 
		      }
		}
		doNeighborToNeighborCoupling();
		coupleOneToAll(this, "stop", "stop");
	    coupleOneToAll(this, "start", "start");
	    CellGridPlot t = new CellGridPlot("CowSheepGrassCellSpace", 0.1,
                "", 400, "", 400);

		t.setCellGridViewLocation(600, 300);
		t.setCellSize(10);
		t.setCellGridViewLocation(570, 100);
		add(t);
		// t.setHidden(false);
		coupleAllTo("outDraw", t, "drawCellToScale");
		DoBoundaryToBoundaryCoupling();
		for(int i=0;i<30;i++)
		{
			for(int j=0;j<30;j++)
			{
				cellphase[i][j]="Empty";
			}
		}
	}
	  public static void main(String args[]){
		//  coordinator r = new coordinator(new SheepGrassCellSpace());
		  //HeapCoord r = new HeapCoord(new SheepGrassCellSpace());
	    TunableCoordinator r = new TunableCoordinator(new CowSheepGrassCellSpace());
	    r.setTimeScale(0.5);
		  
		  
	  r.initialize();

	  r.simulate(10000);
	 // System.exit(0);
	  }

///////////////////////////////////////////////////////////////////////////////////////
// The following are two utility functions that can be useful for you to finish the homework

/**
 * Add couplings among boundary cells to make the cell space wrapped
 */
	private void DoBoundaryToBoundaryCoupling()
    {
        //top and bottom rows
        for( int x = 1; x < xDimCellspace-1; x++ )
        {
            // (x,0) -- bottom to top
            addCoupling(withId(x, 0), "outS", withId(x, yDimCellspace-1), "inN");
            addCoupling(withId(x, 0), "outSW", withId(x-1, yDimCellspace-1), "inNE");
            addCoupling(withId(x, 0), "outSE", withId(x+1, yDimCellspace-1), "inNW");

            // (x,29) -- top to bottom
            addCoupling(withId(x, yDimCellspace-1), "outN", withId(x, 0), "inS");
            addCoupling(withId(x, yDimCellspace-1), "outNE", withId(x+1, 0), "inSW");
            addCoupling(withId(x, yDimCellspace-1), "outNW", withId(x-1, 0), "inSE");
        }

        //west and east columns
        for( int y = 1; y < yDimCellspace-1; y++ )
        {
            // (0,y) -- West - east
            addCoupling(withId(0, y), "outW", withId(xDimCellspace-1, y), "inE");
            addCoupling(withId(0, y), "outSW", withId(xDimCellspace-1, y-1), "inNE");
            addCoupling(withId(0, y), "outNW", withId(xDimCellspace-1, y+1), "inSE");

            // (29,y) -- West - east
            addCoupling(withId(xDimCellspace-1, y), "outE", withId(0, y), "inW");
            addCoupling(withId(xDimCellspace-1, y), "outNE", withId(0, y+1), "inSW");
            addCoupling(withId(xDimCellspace-1, y), "outSE", withId(0, y-1), "inNW");
        }
        //corners
        //(0, 0)
        addCoupling(withId(0, 0), "outNW", withId(xDimCellspace-1, 1), "inSE");
        addCoupling(withId(0, 0), "outW", withId(xDimCellspace-1, 0), "inE");
        addCoupling(withId(0, 0), "outSW", withId(xDimCellspace-1, yDimCellspace-1), "inNE");
        addCoupling(withId(0, 0), "outS", withId(0, yDimCellspace-1), "inN");
        addCoupling(withId(0, 0), "outSE", withId(1, yDimCellspace-1), "inNW");
        //(29, 0)
        addCoupling(withId(xDimCellspace-1, 0), "outSW", withId(xDimCellspace-2, yDimCellspace-1), "inNE");
        addCoupling(withId(xDimCellspace-1, 0), "outE", withId(0, 0), "inW");
        addCoupling(withId(xDimCellspace-1, 0), "outSE", withId(0, yDimCellspace-1), "inNW");
        addCoupling(withId(xDimCellspace-1, 0), "outS", withId(xDimCellspace-1, yDimCellspace-1), "inN");
        addCoupling(withId(xDimCellspace-1, 0), "outNE", withId(0, 1), "inSW");
        //(0, 29)
        addCoupling(withId(0, yDimCellspace-1), "outSW", withId(xDimCellspace-1, yDimCellspace-2), "inNE");
        addCoupling(withId(0, yDimCellspace-1), "outW", withId(xDimCellspace-1, yDimCellspace-1), "inE");
        addCoupling(withId(0, yDimCellspace-1), "outNE", withId(1, 0), "inSW");
        addCoupling(withId(0, yDimCellspace-1), "outN", withId(0, 0), "inS");
        addCoupling(withId(0, yDimCellspace-1), "outNW", withId(xDimCellspace-1, 0), "inSE");
        //(29, 29)
        addCoupling(withId(xDimCellspace-1, yDimCellspace-1), "outNW", withId(xDimCellspace-2, 0), "inSE");
        addCoupling(withId(xDimCellspace-1, yDimCellspace-1), "outE", withId(0, yDimCellspace-1), "inW");
        addCoupling(withId(xDimCellspace-1, yDimCellspace-1), "outSE", withId(0, yDimCellspace-2), "inNW"); // Xiaolin Hu, 10/16/2016
        addCoupling(withId(xDimCellspace-1, yDimCellspace-1), "outN", withId(xDimCellspace-1, 0), "inS");
        addCoupling(withId(xDimCellspace-1, yDimCellspace-1), "outNE", withId(0, 0), "inSW");
    }

	/**
	 * Get the x and y coordinate (int[2]) of a neighbor cell based on the direction in a wrapped cell space
	 * @param direction: the direction defines which neighbor cell to get. 0 - N; 1 - NE; 2 - E; ... (clokewise)
	 * @return the x and y coordinate
	 */
    public int[] getNeighborXYCoord( int direction )
    {
        int[] myneighbor = new int[2];
        int freeNeighbors = 0;
        int tempXplus1 = xcoord+1;
        int tempXminus1 = xcoord-1;
        int tempYplus1 = ycoord+1;
        int tempYminus1 = ycoord-1;

        if( tempXplus1 >= xDimCellspace)
            tempXplus1 = 0;

        if( tempXminus1 < 0 )
            tempXminus1 = xDimCellspace-1;

        if( tempYplus1 >= yDimCellspace)
            tempYplus1 = 0;

        if( tempYminus1 < 0 )
            tempYminus1 = yDimCellspace-1;

        // N
        if( (direction == 0) )
        {
            myneighbor[0] = xcoord;
            myneighbor[1] = tempYplus1;
        }
        // NE
        else if( (direction == 1) )
        {
            myneighbor[0] = tempXplus1;
            myneighbor[1] = tempYplus1;
        }
        // E
        else if( (direction == 2) )
        {
            myneighbor[0] = tempXplus1;
            myneighbor[1] = ycoord;
        }
        // SE
        else if( (direction == 3) )
        {
            myneighbor[0] = tempXplus1;
            myneighbor[1] = tempYminus1;
        }
        // S
        else if( (direction == 4) )
        {
            myneighbor[0] = xcoord;
            myneighbor[1] = tempYminus1;
        }
        // SW
        else if( (direction == 5) )
        {
            myneighbor[0] = tempXminus1;
            myneighbor[1] = tempYminus1;
        }
        // W
        else if( (direction == 6) )
        {
            myneighbor[0] = tempXminus1;
            myneighbor[1] = ycoord;
        }
        // NW
        //( (direction == 7) )
        else if((direction == 7))
        {
            myneighbor[0] = tempXminus1;
            myneighbor[1] = tempYplus1;
        }
        return myneighbor;
    }




}
// End CowSheepGrassCellSpace
