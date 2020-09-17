package Homework2020.BridgeSegment.Asubramanian;

import simView.*;

import java.lang.*;

import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;
import Homework2020.BridgeSegment.AbstractBridgeSystem.BridgeState;
import util.*;
import statistics.*;

public class bridgeSegment extends ViewableAtomic{

  protected double carTravelTime;
  protected double trafficlighttime;
  protected double SignalRemainingTime;
  protected entity car, currentcar = null;
  protected BridgeState stateofbridge;
  protected DEVSQueue westq,eastq;

  public bridgeSegment() 
  {
  	this("bridgeSegment");
  }

public bridgeSegment(String name)
      {
      super(name);
      addInport("EastcarIn");
      addInport("WestcarIn");
      addOutport("EastcarOut");
      addOutport("WestcarOut");   
      }
public bridgeSegment(String name, BridgeState bState, double TLTime)
{
	this(name);
	stateofbridge = bState;
	trafficlighttime = TLTime;
	funcinitialize();
}
public void funcinitialize()
  {
  	SignalRemainingTime = trafficlighttime;
  	sigma = trafficlighttime; 
       phase = stateofbridge.toString();
     if(phase == "WEST_TO_EAST")
    	   phase = "West_To_East";      
       else if(phase== "EAST_TO_WEST")
    	   phase = "East_To_West";
     holdIn(phase, sigma);
  }
public void initialize()
{
 	carTravelTime = 10;
  	currentcar = null;
  	westq = new DEVSQueue();
    eastq = new DEVSQueue();
    
}
 public void  deltext(double e,message x)
{

Continue(e);

for (int i=0; i< x.getLength();i++)
	   {
		   if (messageOnPort(x, "EastcarIn", i) )
          {
	   
			   if (phaseIs("East_To_West") || phaseIs("EtoW") && currentcar==null)
			   {
				   SignalRemainingTime = SignalRemainingTime - e;
				   if(eastq.isEmpty())
				   {
					   if(SignalRemainingTime >= carTravelTime)
					   {
						   SignalRemainingTime = SignalRemainingTime-carTravelTime ;
						   car = x.getValOnPort("EastcarIn", i);
						   currentcar = car;
						   holdIn("EtoW", carTravelTime);
						   System.out.println("East car come directly on bridge");
	   
					   }
					   else
					   {
						   car = x.getValOnPort("EastcarIn", i);
						   eastq.add(car);
						   System.out.println("Queing up east car");
              
					   }
	   
				   }
				else
				{
					car = x.getValOnPort("EastcarIn", i);
					eastq.add(car);
					System.out.println("East car Queing up");
					
				}
				   
				}
			   else if (!eastq.isEmpty() || currentcar != null )
			   {
				     car = x.getValOnPort("EastcarIn", i);
		             eastq.add(car);
		             System.out.println("Queing up east car");
		          
			   }
			 }
		   

		   else if (messageOnPort(x, "WestcarIn", i) )
          {
	   
			   if (phaseIs("West_To_East") || phaseIs("WtoE") && currentcar==null)
			   {
				   SignalRemainingTime = SignalRemainingTime - e;
				   if(westq.isEmpty())
				   {
					   if(SignalRemainingTime >= carTravelTime)
					   {
						   SignalRemainingTime = SignalRemainingTime-carTravelTime ;
						   car = x.getValOnPort("WestcarIn", i);
						   currentcar = car;
						   holdIn("WtoE", carTravelTime);
						   System.out.println("West car come directly on bridge");
	   
					   }
					   else
					   {
						   car = x.getValOnPort("WestcarIn", i);
						   westq.add(car);
						   System.out.println("Queing up west car");
              
					   }
	   
				   }
				else
				{
					car = x.getValOnPort("WestcarIn", i);
					westq.add(car);
					System.out.println("West car Queing up");
      
				}
				   
				}
			   else if (!westq.isEmpty() || currentcar != null )
			   {
				     car = x.getValOnPort("WestcarIn", i);
		             westq.add(car);
		             System.out.println("Queing up West car in stage 2");
		          
			   }
			 }
		   
		   
		   
			}
		   }
		   
 
 
 public void  deltint( )
{
	 if (phaseIs("EtoW"))
	 {

		  if ((SignalRemainingTime >= carTravelTime) && (!eastq.isEmpty()))
		 {
					
			SignalRemainingTime = SignalRemainingTime - carTravelTime;
			car = (entity)eastq.first();
			currentcar = car;
			holdIn("EtoW", carTravelTime);
			eastq.remove();
			
		 }
		 else if ((SignalRemainingTime >= carTravelTime) && (eastq.isEmpty()))
		 {
			holdIn("East_To_West", SignalRemainingTime);
			currentcar= null;
		 }
		 else if (SignalRemainingTime < carTravelTime)
			{
				System.out.println("Signal will be green only for few more secs");
				holdIn("East_To_West", SignalRemainingTime);
				currentcar=null;
				
			}
	
		  if(SignalRemainingTime <= 0)
			{
				
				holdIn("East_To_West", SignalRemainingTime);
			}

		
	 }

	 
	 else if (phaseIs("WtoE"))
	 {

		  if ((SignalRemainingTime >= carTravelTime) && (!westq.isEmpty()))
		 {
					
			SignalRemainingTime = SignalRemainingTime - carTravelTime;
			car = (entity)westq.first();
			currentcar = car;
			holdIn("WtoE", carTravelTime);
			westq.remove();
			
		 }
		 else if ((SignalRemainingTime >= carTravelTime) && (eastq.isEmpty()))
		 {
			holdIn("West_To_East", SignalRemainingTime);
			currentcar= null;
		 }
		 else if (SignalRemainingTime < carTravelTime)
			{
				System.out.println("Signal will be green only for few more secs");
				holdIn("West_To_East", SignalRemainingTime);
				currentcar=null;
				
			}
	
	        if(SignalRemainingTime <= 0)
				{
					holdIn("West_To_East", SignalRemainingTime);
				}
		
	 }

	 
	 else if(phaseIs("East_To_West") || phaseIs("West_To_East"))
   {
		SignalRemainingTime = trafficlighttime;
			 if (phaseIs("East_To_West")) 
			 {
                if (westq.isEmpty())
                {
                 holdIn("West_To_East", trafficlighttime);
                }
                else
                {
                car = (entity)westq.first();
        		currentcar = car;
        		holdIn("WtoE", carTravelTime);
        		westq.remove();
                }
		     }
			 else if (phaseIs("West_To_East")) 
			 {
                if (eastq.isEmpty())
                {
                 holdIn("East_To_West", trafficlighttime);
                }
                else
                {
                car = (entity)eastq.first();
        		currentcar = car;
        		holdIn("EtoW", carTravelTime);
        		eastq.remove();
                }
		     }
   }
			  
}


public message  out()
{
	
	 message  m = new message();
	
	 if (phaseIs("WtoE"))
	 {
		m.add(makeContent("WestcarOut", currentcar));
	 }
	
	 if (phaseIs("EtoW"))
	 {
		m.add(makeContent("EastcarOut", currentcar));
	 }
	 
	 
	 return m; 
}


}

