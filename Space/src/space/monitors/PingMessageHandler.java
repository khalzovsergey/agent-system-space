/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.monitors;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import space.MessageHandler;

/**
 *
 * @author Sergey
 */
public class PingMessageHandler implements MessageHandler
{
    public void Invoke(Agent agent, ACLMessage msg, Map<String, Object> content)
    {
        System.out.println("Receive ping from " + msg.getSender().getLocalName());
        ACLMessage reply = msg.createReply();
        content.put("type", "pingReply");
        try
        {
            reply.setContentObject((Serializable)content);
        }
        catch (IOException e)
        {
        }
        agent.send(reply);
    }
}
