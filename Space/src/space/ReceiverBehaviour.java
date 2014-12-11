/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import java.util.Map;

/**
 *
 * @author Sergey
 */
public class ReceiverBehaviour extends Behaviour
{
    private Map<String, MessageHandler> messageHandlers;

    public ReceiverBehaviour(Map<String, MessageHandler> messageHandlers)
    {
        this.messageHandlers = messageHandlers;
    }

    public void action()
    {
        ACLMessage msg = myAgent.receive();
        if (msg != null)
        {
            Map<String, Object> message = null;
            try
            {
                message = (Map<String, Object>)msg.getContentObject();
            }
            catch (Exception e)
            {
            }
            if (message != null)
            {
                MessageHandler handler = messageHandlers.get(KeyBuilder.build(message.get("type")));
                if (handler != null)
                {
                    handler.Invoke(msg);
                }
            }
        }
        block();
    }

    public boolean done()
    {
        return false;
    }
}
