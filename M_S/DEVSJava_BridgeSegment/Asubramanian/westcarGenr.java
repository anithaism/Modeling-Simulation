package Homework2020.BridgeSegment.Asubramanian;

import simView.*;

import genDevs.modeling.*;
import GenCol.*;
import statistics.*;

public class westcarGenr extends ViewableAtomic
{
    protected double int_gen_time;
    protected int count;
    protected rand r;

    public String getSettingString()
    {
    	return "MeanInterval=" + int_gen_time;
    }
    
    public westcarGenr() 
    {
        this("carGenr", 7);
    }

    public westcarGenr(String name, double period)
    {
        super(name);
        addInport("in");
        addOutport("out");

        int_gen_time = period ;
    }

    public void initialize()
    {
        r = new rand(88888);
        holdIn("active", r.uniform(int_gen_time));
        count = 0;
    }

    public void deltext(double e, message x) 
    {
        Continue(e);
        for (int i=0; i< x.getLength();i++)
        {
            if (messageOnPort(x, "in", i)) 
            { 
                passivate();
            }
        }
    }

    public void deltint( ) 
    {
        if(phaseIs("active"))
        {
            count = count +1;
            holdIn("active",r.uniform(int_gen_time));
        } 
        else 
            passivate();
    }

    public message out( ) 
    {
        message m = new message();
        content con = makeContent("out", new entity(name + "_Car" + count));
        m.add(con);
        return m;
    }
   
}

