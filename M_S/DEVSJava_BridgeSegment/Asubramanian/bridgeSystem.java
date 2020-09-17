package Homework2020.BridgeSegment.Asubramanian;


import simView.*;

import java.awt.*;

import Homework2020.BridgeSegment.*;
//import java.io.*;
//import genDevs.modeling.*;
//import genDevs.simulation.*;
//import GenCol.*;


public class bridgeSystem extends AbstractBridgeSystem{


public bridgeSystem(){
    this("bridgeSys");
}

public bridgeSystem(String nm){
    super(nm);
    bridgeSystemConstruct();
}

public void bridgeSystemConstruct(){

    this.addOutport("EastboundOut");
    this.addOutport("WestboundOut");

    ViewableAtomic east_car_genr = new eastcarGenr("eastcarGenr",10);
    ViewableAtomic west_car_genr = new westcarGenr("westcarGenr",20);
    ViewableAtomic bridgesegment1 = new bridgeSegment("bridgesegment1",AbstractBridgeSystem.BridgeSystemSetting.Bridge1InitialState, AbstractBridgeSystem.BridgeSystemSetting.Bridge1TrafficLightDurationTime);
    ViewableAtomic bridgesegment2 = new bridgeSegment("bridgesegment2",AbstractBridgeSystem.BridgeSystemSetting.Bridge2InitialState, AbstractBridgeSystem.BridgeSystemSetting.Bridge2TrafficLightDurationTime);
    ViewableAtomic bridgesegment3 = new bridgeSegment("bridgesegment3",AbstractBridgeSystem.BridgeSystemSetting.Bridge3InitialState, AbstractBridgeSystem.BridgeSystemSetting.Bridge3TrafficLightDurationTime);
    ViewableAtomic trand = new Transducer();

     add(east_car_genr);
     add(bridgesegment3);
     add(bridgesegment2);
     add(bridgesegment1);
     add(west_car_genr);
     add(trand);


     addCoupling(east_car_genr,"out",bridgesegment1,"EastcarIn");
     addCoupling(west_car_genr,"out",bridgesegment3,"WestcarIn");
     addCoupling(bridgesegment3,"EastcarOut",this,"EastboundOut");
     addCoupling(bridgesegment3,"WestcarOut", bridgesegment2,"WestcarIn");
     addCoupling(bridgesegment2,"EastcarOut", bridgesegment1,"EastcarIn");
     addCoupling(bridgesegment2,"WestcarOut", bridgesegment3,"WestcarIn");
     addCoupling(bridgesegment1,"EastcarOut", bridgesegment2,"EastcarIn");
     addCoupling(bridgesegment1,"WestcarOut",this,"WestboundOut");
     addCoupling(bridgesegment3,"EastcarOut",trand,"WestboundOut");
     addCoupling(bridgesegment1,"WestcarOut",trand,"EastboundOut");
     addCoupling(bridgesegment3,"WestcarOut",trand,"Bridge3_WestOut");
     addCoupling(bridgesegment2,"WestcarOut",trand,"Bridge2_WestOut");
     addCoupling(bridgesegment1,"EastcarOut",trand,"Bridge1_EastOut");
     addCoupling(bridgesegment2,"EastcarOut",trand,"Bridge1_EastOut");
}

    public void layoutForSimView()
    {
        preferredSize = new Dimension(1380, 600);
        if((ViewableComponent)withName("eastcarGenr")!=null)
             ((ViewableComponent)withName("eastcarGenr")).setPreferredLocation(new Point(605, 320));
        if((ViewableComponent)withName("bridgesegment1")!=null)
             ((ViewableComponent)withName("bridgesegment1")).setPreferredLocation(new Point(465, 181));
        if((ViewableComponent)withName("transducer")!=null)
             ((ViewableComponent)withName("transducer")).setPreferredLocation(new Point(320, 44));
        if((ViewableComponent)withName("bridgesegment2")!=null)
             ((ViewableComponent)withName("bridgesegment2")).setPreferredLocation(new Point(312, 257));
        if((ViewableComponent)withName("bridgesegment3")!=null)
             ((ViewableComponent)withName("bridgesegment3")).setPreferredLocation(new Point(149, 165));
        if((ViewableComponent)withName("westcarGenr")!=null)
             ((ViewableComponent)withName("westcarGenr")).setPreferredLocation(new Point(-12, 163));
    }
}
