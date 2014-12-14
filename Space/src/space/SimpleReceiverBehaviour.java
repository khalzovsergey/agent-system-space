/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Sergey
 */
public class SimpleReceiverBehaviour extends Behaviour
{
    private ACLMessageHandler msgHandler;

    public SimpleReceiverBehaviour(ACLMessageHandler msgHandler)
    {
        this.msgHandler = msgHandler;
    }

    public void action()
    {
        ACLMessage msg = myAgent.receive();
        if (msg != null)
        {
            msgHandler.Invoke(myAgent, msg);
        }
        block();
    }

    public boolean done()
    {
        return false;
    }
}
