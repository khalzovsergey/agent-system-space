/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.planets;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import space.common.Scheduler;
import space.common.Variable;

/**
 *
 * @author Sergey
 */
public class CivilizationBehaviour extends Behaviour
{
    private Map<String, Object> planet;
    private Map<String, Object> civilization;
    private Variable<Double> number;
    private int delay = 1000;
    private Scheduler scheduler;
    private double coeff_2 = 0.001;
    private double coeff_3 = 1.001;
    private double coeff_4 = 1.005;
    private Variable<Double> resources_value;
    private Variable<Double> resources_max;
    private int timer = 0;
    private int period = 10;
    private double coeff_5 = 50.0;
    private double coeff_6 = 10.0;
    private double coeff_7 = 20.0;
    private Map<String, Object> createShipMsg;
//    private Variable<Long> time;
    
    public CivilizationBehaviour(Map<String, Object> planet, Map<String, Object> civilization, long initialDelay)
    {
        this.planet = planet;
        this.civilization = civilization;
        number = (Variable<Double>)civilization.get("number");
        Map<String, Object> resources = (Map<String, Object>)planet.get("resources");
        resources_value = (Variable<Double>)resources.get("value");
        resources_max = (Variable<Double>)resources.get("max");
        scheduler = new Scheduler();
        scheduler.addDelay(initialDelay);
        createShipMsg = new HashMap<>();
        createShipMsg.put("type", "createShip");
        Map<String, Object> content = new HashMap<>();
        content.put("planet", planet);
        content.put("civilization", civilization);
        content.put("manpower", new Variable<>(coeff_6));
        //content.put("shipType", "passenger_transport");
        createShipMsg.put("content", content);
//        time = new Variable<>();
//        createShipMsg.put("time", time);
    }
    
    private DFAgentDescription[] serviceSearch(String type)
    {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd  = new ServiceDescription();
        sd.setType(type);
        dfd.addServices(sd);

        DFAgentDescription[] result = null;
        try
        {
            result = DFService.search(myAgent, dfd);
        }
        catch (FIPAException e)
        {
        }
        return result;
    }
    
    private AID getShipFactory()
    {
        AID result = null;
        DFAgentDescription[] shipFactories = serviceSearch("createShip");
        if (shipFactories != null && shipFactories.length > 0)
        {
            result = shipFactories[(int)System.currentTimeMillis() % shipFactories.length].getName();
        }
        return result;
    }
    
    private void createShip(AID shipFactory)
    {
        ACLMessage msg = new ACLMessage(ACLMessage.UNKNOWN);
        createShipMsg.put("time", System.currentTimeMillis());
        try
        {
            msg.setContentObject((Serializable)createShipMsg);
        }
        catch (Exception e)
        {
            System.err.println("Not serialized.");
        }
        msg.addReceiver(shipFactory);
        myAgent.send(msg);
    }
    
    public void action()
    {
        if (scheduler.mayExecute())
        {
            if (number.value > coeff_7)
            {
                timer = (timer + 1) % period;
                if (timer == 0 && resources_value.value > coeff_5 && number.value > coeff_6)
                {
                    AID shipFactory = getShipFactory();
                    if (shipFactory != null)
                    {
                        resources_value.value -= coeff_5;
                        number.value -= coeff_6;
                        createShip(shipFactory);
                        System.out.println(civilization.get("name").toString()+ ": This civilization has created a ship.");
                    }
                }
                if (resources_value.value > 0)
                {
                    resources_value.value -= number.value * coeff_2;
                    number.value *= coeff_3;
                }
                else
                {
                    number.value /= coeff_4;
                }
            }
            else
            {
                //delete this civilization
            }
            System.out.println(civilization.get("name").toString() + ": number = " + number.value);
            scheduler.addDelay(delay);
        }
        scheduler.block(this);
    }

    public boolean done()
    {
        return false;
    }
}
