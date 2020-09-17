package FinalProject_2020_ASubramanian.CowSheepGrassCellSpace;

import twoDCellSpace.*;
import simView.*;
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;
import genDevs.plots.*;
import java.util.*;
import java.awt.*;
import java.text.*;
import java.io.*;
import statistics.*;
import quantization.*;
public class CowSheepGrassCell extends TwoDimCell
{
	public static final double sheepMoveT=1.0;
	public static final double sheepLifeT=3.0;
    public static final double sheepReproduceT=3.5;
    public static final double cowMoveT=1.5; //more than sheep
	public static final double cowLifeT=3.0; //Same as sheep
    public static final double cowReproduceT=4.0; //more than sheep
    public static final double grassReproduceT=2.0;
    double Sheepmovetime=sheepMoveT;
    double Sheeplifetime=sheepLifeT;
    double Sheepreproducetime=sheepReproduceT;
    double Cowmovetime=cowMoveT;
    double Cowlifetime=cowLifeT;
    double Cowreproducetime=cowReproduceT;
    double Grassreproducetime=grassReproduceT;
    double time=0;
    String Status=null;
    int grasscount;
    int future;

    	
    public static final String[] inports = { "inN", "inNE", "inE", "inSE", "inS",
			"inSW", "inW", "inNW" };
    public CowSheepGrassCell() 
    {
	    this(0, 0);
	}
    public CowSheepGrassCell(int xcoord, int ycoord)
    {
    	super(new Pair(new Integer(xcoord), new Integer(ycoord)));
    }
    public void scenario1()
    {
    	if(x_pos==0 && y_pos==0)
	    {
	    	holdIn("Grass",0);
	    	CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Grass";	
	    }
   
		else
		{
			passivateIn("Empty");
		}
    }
    
    public void scenario2()
    {

		if(x_pos==0 && y_pos==0 || x_pos==-20 && y_pos==-30 || x_pos==40 && y_pos==60 || x_pos==-80 && y_pos==60 || x_pos==120 && y_pos==-180)
		{
			holdIn("Grass",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Grass";
		}
		
		else 
			passivateIn("Empty");
    }
    
    public void scenario3()
    {
    	if(x_pos==0 && y_pos==0)
		{
			holdIn("Sheep",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Sheep";
		}
		else
			passivateIn("Empty");
    }
    
    public void scenario4()
    {
    	if(x_pos==0 && y_pos==0 || x_pos==40 && y_pos==40 || x_pos==40 && y_pos==50 || x_pos==-40 && y_pos==-50)

		{
			holdIn("Sheep",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Sheep";
		}
		else
			passivateIn("Empty");
    }
    
    public void scenario5()
    {

		if(x_pos==0 && y_pos==0 || x_pos==10 && y_pos==0 || x_pos==-100 && y_pos==0 || x_pos==200 && y_pos==-120 )

		{
			holdIn("Grass",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Grass";
		}
		else if(x_pos==20 && y_pos==0)
		{
			holdIn("Sheep",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Sheep";
		}
		
		else
			passivateIn("Empty");
    	
    }
    public void scenario6()
    {

    	if(x_pos>=-10 && x_pos<10 && y_pos>=-20 && y_pos<=20 || x_pos>=40 && x_pos<=50 && y_pos>=30 && y_pos<=50 || x_pos>=-40 && x_pos<=-30 && y_pos>=-30 && y_pos<=-10 || x_pos>=-40 && x_pos<=-30 && y_pos>=30 && y_pos<=40)
		{
			holdIn("Grass",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Grass";
		}
    	else if(x_pos>=20 && x_pos<=40 && y_pos<=40 && y_pos >=20 || x_pos==-40 && y_pos ==-10 || x_pos==10 && y_pos==10)
		{
			holdIn("Sheep",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Sheep";
		}
		else 
			passivateIn("Empty");
		//	holdIn("Empty",0);
	
    }
    
    public void scenario7() //No grass only Cow
    {
    	if(x_pos==0 && y_pos==0)
		{
			holdIn("Cow",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Cow";
		}
		else
			passivateIn("Empty");
    }
    
    public void scenario8() //No grass only Cow at multiple locations
    {
    	if(x_pos==10 && y_pos==10 || x_pos==50 && y_pos==50 || x_pos==60 && y_pos==70 || x_pos==-80 && y_pos==-100)

		{
			holdIn("Cow",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Cow";
		}
		else
			passivateIn("Empty");
    }
    
    public void scenario9() //Grass with Cow
    {

    	if(x_pos==0 && y_pos==0 || x_pos==10 && y_pos==0 || x_pos==-100 && y_pos==0 || x_pos==200 && y_pos==-120 )

		{
			holdIn("Grass",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Grass";
		}
		else if(x_pos==20 && y_pos==0)
		{
			holdIn("Cow",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Cow";
		}
		
		else
			passivateIn("Empty");
    	
    }
 
    
    public void scenario10() //CellSpace with both Sheep and Cow
    {

    	if(x_pos>=-10 && x_pos<10 && y_pos>=-20 && y_pos<=20 || x_pos>=40 && x_pos<=50 && y_pos>=30 && y_pos<=50 || x_pos>=-40 && x_pos<=-30 && y_pos>=-30 && y_pos<=-10 || x_pos>=-40 && x_pos<=-30 && y_pos>=30 && y_pos<=40)
		{
			holdIn("Grass",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Grass";
		}
    	
    	else if(x_pos==-20 && y_pos==-10 || x_pos==-20 && y_pos==10)
    	{
    		holdIn("Cow",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Cow";
        }
    	
    	else if(x_pos>=20 && x_pos<=40 && y_pos<=40 && y_pos >=20 || x_pos==-40 && y_pos ==-10 || x_pos==10 && y_pos==10)
		{
			holdIn("Sheep",0);
			CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Sheep";
		}
    	
    	else
			passivateIn("Empty");
    }
    
    

    public void initialize()
    {
    	switch(CowSheepGrassCellSpace.testcase)
    	{
    	case 1: scenario1();
    	        break;
    	       
    	case 2: scenario2();
                break;
                
    	case 3: scenario3();
                break;
                
    	case 4: scenario4();
                break;
                
    	case 5: scenario5();
                break;
                
    	case 6: scenario6();
                break;
                
    	case 7: scenario7();
    	        break;
    	        
    	case 8: scenario8();
                break;
                
    	case 9: scenario9();
                break;
                
    	case 10: scenario10();
                break;
    	        
    	}
    }
    
    public void deltint() 
    {
   
    	if(phaseIs("Grass"))
    		holdIn("Grassreproduce",Grassreproducetime);
    	else if(phaseIs("Grassreproduce"))
    	{
    		//System.out.println("grass is producing at" +" "+x_pos+" "+y_pos);
    		grasscount=1;
    		grasscount=keepgrasscount(grasscount);
    		//System.out.println("Grass count : "+grasscount);
    		
    		holdIn("Grassreproduce",Grassreproducetime);
    	}
    	else if(phaseIs("Sheep") || phaseIs("Sheepreproduce"))
    	{
    		if(Sheepmovetime<Sheeplifetime && Sheepmovetime<Sheepreproducetime)
			 {
    			 //System.out.println("It is coming to sheep state or sheep reproduce state");
    			 //System.out.println("Sigma now is"+sigma);
				 holdIn("Sheepmove",Sheepmovetime);
				 Sheepreproducetime=Sheepreproducetime-sigma;
				 Sheeplifetime=Sheeplifetime-sigma;
			 }
			 else if(Sheeplifetime<=Sheepmovetime && Sheeplifetime<=Sheepreproducetime)
				 holdIn("Sheepdie",Sheeplifetime);
			 else if(Sheepreproducetime<Sheepmovetime && Sheepreproducetime<Sheeplifetime)
			 {
				 holdIn("Sheepreproduce",Sheepreproducetime);
			     Sheepmovetime=Sheepmovetime-sigma;
			     Sheeplifetime=Sheeplifetime-sigma;
		 
			 }
    	}
    	
    	else if(phaseIs("Cow") || phaseIs("Cowreproduce"))
    	{
    		if(Cowmovetime<Cowlifetime && Cowmovetime<Cowreproducetime)
			 {
    			 //System.out.println("It is coming to cow state or cow reproduce state");
    			 //System.out.println("Sigma now is"+sigma);
				 holdIn("Cowmove",Cowmovetime);
				 Cowreproducetime=Cowreproducetime-sigma;
				 Cowlifetime=Cowlifetime-sigma;
			 }
			 else if(Cowlifetime<=Cowmovetime && Cowlifetime<=Cowreproducetime)
				 holdIn("Cowdie",Cowlifetime);
			 else if(Cowreproducetime<Cowmovetime && Cowreproducetime<Cowlifetime)
			 {
				 holdIn("Cowreproduce",Cowreproducetime);
			     Cowmovetime=Cowmovetime-sigma;
			     Cowlifetime=Cowlifetime-sigma;
		 
			 }
    	}
    	else if(phaseIs("Sheepmove"))
    	{
    		System.out.println("Sheep is moving");
    		CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Empty";
    			passivateIn("Empty");
    		
    	}
    	else if(phaseIs("Cowmove"))
    	{
    		System.out.println("Cow is moving");
    		CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Empty";
    			passivateIn("Empty");
    		
    	}	 
	    else if(phaseIs("Sheepdie"))
			 passivateIn("Empty");
	    else if(phaseIs("Cowdie"))
			 passivateIn("Empty");
    	else if(phaseIs("Empty"))
    		holdIn("Empty",3.0);
    }


    public void deltext(double e, message x)
    {
    	Continue(e);
    	
    	Status=null;
    	 Pair a=null;
		 Pair b=null;
		 String phaserec=null;
		
		 
		 for (int i = 0; i < x.getLength(); i++) 
		 {
			 for(int j=0;j<8;j++)
			 {
				 if(messageOnPort(x,inports[j],i))
				 {
					 a=(Pair) x.getValOnPort(inports[j],i );
					 phaserec=a.getKey().toString();
				 }
			 }
			 
			 //System.out.println("phase is " + phaserec);
			 
			 if(phaserec=="Grassreproduce")
			 {
				 holdIn("Grass",0);
				 CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Grass";		
				 Sheepreproducetime=sheepReproduceT;
				 Cowreproducetime=cowReproduceT;
				 Sheepmovetime=sheepMoveT;
				 Cowmovetime=cowMoveT;
				 Sheeplifetime=sheepLifeT;
				 Cowlifetime=cowLifeT;
				 System.out.println("The time variable is : "+e);
				
			 }
			 else if(phaserec=="Sheepmove")
			 {
				 if(phaseIs("Grassreproduce") || phaseIs("Grass"))
				 {
					 CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Sheep";
					 System.out.println("Sheep is eating Grass");
					 b=(Pair) a.getValue();
					 Sheepreproducetime=(Double) b.getValue();
					 Sheepmovetime=this.sheepMoveT;
					 Sheeplifetime=this.sheepLifeT;
					 holdIn("Sheep",0);
				 }
				 else if(phaseIs("Empty"))
				 {
					 CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Sheep";
					 b=(Pair) a.getValue();
					 this.Sheeplifetime=(Double) b.getKey();
					 this.Sheepreproducetime=(Double) b.getValue();
					 this.Sheepmovetime=this.sheepMoveT;
					 holdIn("Sheep",0);
				 }
			 }
			 else if(phaserec=="Cowmove")
			 {
				 if(phaseIs("Grassreproduce") || phaseIs("Grass"))
				 {
					 CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Cow";
					 System.out.println("Cow is eating Grass");
					 b=(Pair) a.getValue();
					 Cowreproducetime=(Double) b.getValue();
					 Cowmovetime=this.cowMoveT;
					 Cowlifetime=this.cowLifeT;
					 holdIn("Cow",0);
				 }
				 else if(phaseIs("Empty"))
				 {
					 CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Cow";
					 b=(Pair) a.getValue();
					 this.Cowlifetime=(Double) b.getKey();
					 this.Cowreproducetime=(Double) b.getValue();
					 this.Cowmovetime=this.cowMoveT;
					 holdIn("Cow",0);
				 }
			 }
			 else if(phaserec=="Sheepreproduce")
			 {
				 Sheepreproducetime=sheepReproduceT;
				 Sheepmovetime=sheepMoveT;
				 Sheeplifetime=sheepLifeT;
				 CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Sheep";
				 holdIn("Sheep",0);
			 }
			 else if(phaserec=="Cowreproduce")
			 {
				 Cowreproducetime=cowReproduceT;
				 Cowmovetime=cowMoveT;
				 Cowlifetime=cowLifeT;
				 CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Cow";
				 holdIn("Cow",0);
			 }
			 
    }
    }
    
    public message out()
    {
    	message m = super.out();
		String dir;
		
			if(phaseIs("Grass"))
			{
				m.add(makeContent("outDraw", new DrawCellEntity("drawCellToScale",
				          x_pos, y_pos, Color.green, Color.green)));
				grasscount=grasscount+1;
				System.out.println("Grass count "+grasscount);
				System.out.println("Drawing Green at"+x_pos+" "+y_pos);
			}
			else if(phaseIs("Grassreproduce"))
			{
				m.add(makeContent("outDraw", new DrawCellEntity("drawCellToScale",
				          x_pos, y_pos, Color.green, Color.green)));
				grasscount=grasscount+1;
				System.out.println("Grass count "+grasscount);
				System.out.println("Drawing Green at"+x_pos+" "+y_pos);
				
			    dir=getdirectiongrass(xcoord,ycoord);
				if(dir!="busy")
				{
				content con = makeContent(dir,new Pair("Grassreproduce",null));
			    m.add(con);
			    //System.out.println(" Grass reproduce"+grassReproduceT+" "+sheepMoveT+" "+sheepReproduceT+" "+xcoord+" "+ycoord);
				}
			}
			
			else if(phaseIs("Sheep"))
			{
				m.add(makeContent("outDraw", new DrawCellEntity("drawCellToScale",
				          x_pos, y_pos, Color.red, Color.red)));
				System.out.println("Drawing Red at"+x_pos+" "+y_pos);
			}
			
			else if(phaseIs("Cow"))
			{
				m.add(makeContent("outDraw", new DrawCellEntity("drawCellToScale",
				          x_pos, y_pos, Color.blue, Color.blue)));
				System.out.println("Drawing Blue at"+x_pos+" "+y_pos);
			}
			
			else if(phaseIs("Sheepmove"))
			{
				dir=getdirectionsheep(xcoord,ycoord);
				if(dir!="busy")
				{
					Status="moved";
					CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Empty";
					m.add(makeContent("outDraw", new DrawCellEntity("drawCellToScale",
					          x_pos, y_pos, Color.white, Color.white)));
					System.out.println("Drawing White at"+x_pos+" "+y_pos);
					content con = makeContent(dir,new Pair("Sheepmove",new Pair(this.Sheeplifetime,this.Sheepreproducetime)));
					m.add(con);	
				
				this.Sheepreproducetime=this.sheepReproduceT;
				 this.Sheepmovetime=this.sheepMoveT;
				 this.Sheeplifetime=this.sheepLifeT; 
				}
				else
					Status="notmoved";
				
			}
			
			else if(phaseIs("Cowmove"))
			{
				dir=getdirectioncow(xcoord,ycoord);
				if(dir!="busy")
				{
					Status="moved";
					CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Empty";
					m.add(makeContent("outDraw", new DrawCellEntity("drawCellToScale",
					          x_pos, y_pos, Color.white, Color.white)));
					System.out.println("Drawing White at"+x_pos+" "+y_pos);
					content con = makeContent(dir,new Pair("Cowmove",new Pair(this.Cowlifetime,this.Cowreproducetime)));
					m.add(con);	
				
				this.Cowreproducetime=this.cowReproduceT;
				 this.Cowmovetime=this.cowMoveT;
				 this.Cowlifetime=this.cowLifeT; 
				}
				else
					Status="notmoved";
				
			}
			
			else if(phaseIs("Sheepdie"))
			{
				m.add(makeContent("outDraw", new DrawCellEntity("drawCellToScale",
				          x_pos, y_pos, Color.white, Color.white)));
				System.out.println("Drawing White at"+x_pos+" "+y_pos);
				CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Empty";
				Sheepreproducetime=sheepReproduceT;
				 Sheepmovetime=sheepMoveT;
				 Sheeplifetime=sheepLifeT; 
			}
			
			else if(phaseIs("Cowdie"))
			{
				m.add(makeContent("outDraw", new DrawCellEntity("drawCellToScale",
				          x_pos, y_pos, Color.white, Color.white)));
				System.out.println("Drawing White at"+x_pos+" "+y_pos);
				CowSheepGrassCellSpace.cellphase[xcoord][ycoord]="Empty";
				Cowreproducetime=cowReproduceT;
				Cowmovetime=cowMoveT;
				Cowlifetime=cowLifeT; 
			}
			
			else if(phaseIs("Sheepreproduce"))
			{
				dir=getdirectionsheepreproduce(xcoord,ycoord);
				if(dir!="busy")
				{
					
					content con = makeContent(dir,new Pair("Sheepreproduce",null));
					m.add(con);
					//System.out.println(" Sheep reproduce"+sheepLifeT+" "+sheepMoveT+" "+sheepReproduceT+" "+xcoord+" "+ycoord);
				}
			}
			else if(phaseIs("Cowreproduce"))
			{
				dir=getdirectioncowreproduce(xcoord,ycoord);
				if(dir!="busy")
				{
					
					content con = makeContent(dir,new Pair("Cowreproduce",null));
					m.add(con);
					//System.out.println(" Cow reproduce"+cowLifeT+" "+cowMoveT+" "+cowReproduceT+" "+xcoord+" "+ycoord);
				}
			}
			else if(phaseIs("Empty"))
			{
				//passivateIn("Empty");
				holdIn("Empty",3.0);
				m.add(makeContent("outDraw", new DrawCellEntity("drawCellToScale",
				          x_pos, y_pos, Color.white, Color.white)));
				System.out.println("Drawing White at"+x_pos+" "+y_pos);
			}
		return m;
    }
    
    public int keepgrasscount(int grasscount)
    {
    	future=future+grasscount;
    	//System.out.println("Future count : "+future);
      	grasscount=future;
    	return grasscount;
    }
 
    public String getdirectiongrass(int xco,int yco)
    {
    	int count=0,i=0,max=7,min=0,count1=0,randomNum;
    	int[] pav=new int[8];
    	int[] neigh=new int[2];
    	Random rand = new Random();
    	long seed = 95; 
       // rand.setSeed(seed); 
    	for(i=0;;i++)
    	{
    		if(count1<9)
    		{
    			randomNum =rand.nextInt((max - min)+1) + min;
    			if(pav[randomNum]==0)
    			{
    				pav[randomNum]=1;
    				neigh=getNeighborXYCoord(randomNum);
    				if(CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]=="Empty") 
      	  			{
      	  				CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]="Grass";
      	  				count=1;
      	  				break;
      	  			}
    				count1++;
    			}
    			if(count1==8)
    				break;
    		}
    	}
    	if(count==0)
    		return "busy";
    	else
    	{
    		String a=null;
    		if(randomNum==0)
  				a="outN";
  			if(randomNum==1)
  				a="outNE";
  			if(randomNum==2)
  				a="outE";
  			if(randomNum==3)
  				a="outSE";
  			if(randomNum==4)
  				a="outS";
  			if(randomNum==5)
  				a="outSW";
  			if(randomNum==6)
  				a="outW";
  			if(randomNum==7)
  				a="outNW";
  			return a;
    	}
    }
    public String getdirectionsheepreproduce(int xco,int yco)
    {
    	int count=0,i=0,max=7,min=0,count1=0,randomNum;
    	int[] pav=new int[8];
    	int[] neigh=new int[2];
    	Random rand = new Random();
    	for(i=0;;i++)
    	{
    		if(count1<9)
    		{
    			randomNum =rand.nextInt((max - min)+1) + min;
    			if(pav[randomNum]==0)
    			{
    				pav[randomNum]=1;
    				neigh=getNeighborXYCoord(randomNum);
    				if(CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]=="Empty") 
      	  			{
      	  				CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]="Sheep";
      	  				count=1;
      	  				break;
      	  			}
    				count1++;
    			}
    			if(count1==8)
    				break;
    		}
    	}
    	if(count==0)
    		return "busy";
    	else
    	{
    		String a=null;
    		if(randomNum==0)
  				a="outN";
  			if(randomNum==1)
  				a="outNE";
  			if(randomNum==2)
  				a="outE";
  			if(randomNum==3)
  				a="outSE";
  			if(randomNum==4)
  				a="outS";
  			if(randomNum==5)
  				a="outSW";
  			if(randomNum==6)
  				a="outW";
  			if(randomNum==7)
  				a="outNW";
  			return a;
    	}
    }
    
    public String getdirectioncowreproduce(int xco,int yco)
    {
    	int count=0,i=0,max=7,min=0,count1=0,randomNum;
    	int[] pav=new int[8];
    	int[] neigh=new int[2];
    	Random rand = new Random();
    	for(i=0;;i++)
    	{
    		if(count1<9)
    		{
    			randomNum =rand.nextInt((max - min)+1) + min;
    			if(pav[randomNum]==0)
    			{
    				pav[randomNum]=1;
    				neigh=getNeighborXYCoord(randomNum);
    				if(CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]=="Empty") 
      	  			{
      	  				CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]="Cow";
      	  				count=1;
      	  				break;
      	  			}
    				count1++;
    			}
    			if(count1==8)
    				break;
    		}
    	}
    	if(count==0)
    		return "busy";
    	else
    	{
    		String a=null;
    		if(randomNum==0)
  				a="outN";
  			if(randomNum==1)
  				a="outNE";
  			if(randomNum==2)
  				a="outE";
  			if(randomNum==3)
  				a="outSE";
  			if(randomNum==4)
  				a="outS";
  			if(randomNum==5)
  				a="outSW";
  			if(randomNum==6)
  				a="outW";
  			if(randomNum==7)
  				a="outNW";
  			return a;
    	}
    }
    public String getdirectionsheep(int xco,int yco)
    {
    	int count=0,i=0,max=7,min=0,count1=0,randomNum;
    	int[] pav=new int[8];
    	int[] neigh=new int[2];
    	Random rand = new Random();
    	for(i=0;;i++)
    	{
    		if(count1<9)
    		{
    			randomNum =rand.nextInt((max - min)+1) + min;
    			if(pav[randomNum]==0)
    			{
    				pav[randomNum]=1;
    				neigh=getNeighborXYCoord(randomNum);
    				if(CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]=="Grass") 
      	  			{
      	  				CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]="Sheep";
      	  				count=1;
      	  				break;
      	  			}
    				count1++;
    			}
    			if(count1==8)
    				break;
    		}
    	}
    	count1=0;
    	
    	if(count==0)
    	{
    		for(int j=0;j<8;j++)
        		pav[j]=0;
    		for(i=0;;i++)
        	{
        		if(count1<9)
        		{
        			randomNum =rand.nextInt((max - min)+1) + min;
        			if(pav[randomNum]==0)
        			{
        				pav[randomNum]=1;
        				neigh=getNeighborXYCoord(randomNum);
        				if(CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]=="Empty") 
          	  			{
          	  				CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]="Sheep";
          	  				count=1;
          	  				break;
          	  			}
        				count1++;
        			}
        			if(count1==8)
        				break;
        		}
        	}
    	}
    	if(count==0)
    		return "busy";
    	else
    	{
    		String a=null;
    		if(randomNum==0)
  				a="outN";
  			if(randomNum==1)
  				a="outNE";
  			if(randomNum==2)
  				a="outE";
  			if(randomNum==3)
  				a="outSE";
  			if(randomNum==4)
  				a="outS";
  			if(randomNum==5)
  				a="outSW";
  			if(randomNum==6)
  				a="outW";
  			if(randomNum==7)
  				a="outNW";
  			return a;
    	}
    }
    
    public String getdirectioncow(int xco,int yco)
    {
    	int count=0,i=0,max=7,min=0,count1=0,randomNum;
    	int[] pav=new int[8];
    	int[] neigh=new int[2];
    	Random rand = new Random();
    	for(i=0;;i++)
    	{
    		if(count1<9)
    		{
    			randomNum =rand.nextInt((max - min)+1) + min;
    			if(pav[randomNum]==0)
    			{
    				pav[randomNum]=1;
    				neigh=getNeighborXYCoord(randomNum);
    				if(CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]=="Grass") 
      	  			{
      	  				CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]="Cow";
      	  				count=1;
      	  				break;
      	  			}
    				count1++;
    			}
    			if(count1==8)
    				break;
    		}
    	}
    	count1=0;
    	
    	if(count==0)
    	{
    		for(int j=0;j<8;j++)
        		pav[j]=0;
    		for(i=0;;i++)
        	{
        		if(count1<9)
        		{
        			randomNum =rand.nextInt((max - min)+1) + min;
        			if(pav[randomNum]==0)
        			{
        				pav[randomNum]=1;
        				neigh=getNeighborXYCoord(randomNum);
        				if(CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]=="Empty") 
          	  			{
          	  				CowSheepGrassCellSpace.cellphase[neigh[0]][neigh[1]]="Cow";
          	  				count=1;
          	  				break;
          	  			}
        				count1++;
        			}
        			if(count1==8)
        				break;
        		}
        	}
    	}
    	if(count==0)
    		return "busy";
    	else
    	{
    		String a=null;
    		if(randomNum==0)
  				a="outN";
  			if(randomNum==1)
  				a="outNE";
  			if(randomNum==2)
  				a="outE";
  			if(randomNum==3)
  				a="outSE";
  			if(randomNum==4)
  				a="outS";
  			if(randomNum==5)
  				a="outSW";
  			if(randomNum==6)
  				a="outW";
  			if(randomNum==7)
  				a="outNW";
  			return a;
    	}
    }
    
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


