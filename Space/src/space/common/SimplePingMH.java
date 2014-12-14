/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.common;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Sergey
 */
public class SimplePingMH implements MessageHandler
{
    public void Invoke(Agent agent, ACLMessage msg, Map<String, Object> msgContent)
    {
        Object message_type = msgContent.get("message_type");
        if (message_type == null)
        {
            return;
        }
        if (message_type.equals("request"))
        {
            System.out.println("ping >> request from " + msg.getSender().getLocalName());
            ACLMessage reply = msg.createReply();
            msgContent.put("message_type", "reply");
            try
            {
                reply.setContentObject((Serializable)msgContent);
            }
            catch (IOException e)
            {
            }
            agent.send(reply);
        }
        else if (message_type.equals("reply"))
        {
            long time = (Long)msgContent.get("time");
            System.out.println("ping >> reply from " + msg.getSender().getLocalName() + ": time=" + (System.currentTimeMillis() - time) + "ms");
        }
    }
}
