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
import space.MessageHandler;

/**
 *
 * @author Sergey
 */
public class SimpleShipFactoryMH implements MessageHandler
{
    public void Invoke(Agent agent, ACLMessage msg, Map<String, Object> content)
    {
        String name = msg.getSender().getLocalName() + "_Ship_" + content.get("time").toString();
        AgentContainer c = agent.getContainerController();
        try
        {
            AgentController a = c.createNewAgent(name, SimpleShipAgent.class.getName(), new Object[] { msg, content });
            a.start();
        }
        catch (Exception e)
        {
        }
    }
}
