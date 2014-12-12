/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.ships;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import java.util.Map;
import space.MessageHandler;

/**
 *
 * @author Sergey
 */
public class SimpleShipFactoryMH implements MessageHandler
{
    private Agent myAgent;
    
    public SimpleShipFactoryMH(Agent agent)
    {
        myAgent = agent;
    }
    
    public void Invoke(ACLMessage msg, Map<String, Object> content)
    {
        
    }
    
//    public void createNewAgent()
//    {
//        String name = "Alice";
//        AgentContainer c = getContainerController();
//        try
//        {
//            AgentController a = c.createNewAgent(name, "Pong", null);
//            a.start();
//        }
//        catch (Exception e)
//        {
//        }
//    }
}
