/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.ships;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import java.util.Map;
import space.common.MessageHandler;

/**
 *
 * @author Sergey
 */
public class SimpleShipFactoryMH implements MessageHandler
{
    public void Invoke(Agent agent, ACLMessage msg, Map<String, Object> msgContent)
    {
        String name = msg.getSender().getLocalName() + "_Ship_" + msgContent.get("time").toString();
        AgentContainer c = agent.getContainerController();
        try
        {
            AgentController a = c.createNewAgent(name, SimpleShipAgent.class.getName(), new Object[] { msg, msgContent });
            a.start();
        }
        catch (Exception e)
        {
            System.err.println(agent.getLocalName() + ": ship not created.");
        }
    }
}
