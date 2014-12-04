/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.monitors;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import space.Scheduler;

/**
 *
 * @author Sergey
 */
public class MonitorAgent extends Agent
{
    protected void setup()
    {
        addBehaviour(new PassiveBehaviour());
        addBehaviour(new ActiveBehaviour());
    }
    
    protected void takeDown()
    {
        System.exit(0);
    }
    
    private class PassiveBehaviour extends Behaviour
    {
        //private boolean finished = false;
        
        //private int n = 0;

        public void action()
        {
            //System.out.println("iteration number = " + n++);
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
            ACLMessage msg = myAgent.receive(mt);
            if (msg != null)
            {
                System.out.println(msg.getContent());
            }
            block();
        }
        
        public boolean done()
        {
            //myAgent.doDelete();
            return false;
            //return finished;
        }
    }
    
    private class ActiveBehaviour extends Behaviour
    {
        //private boolean finished = false;
        
        //private int n = 0;
        
        private int delay = 3000;
        private Scheduler scheduler = new Scheduler();

        public void action()
        {
            //System.out.println("iteration number = " + n++);
            if (scheduler.execute())
            {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.setContent("civilization number");
                msg.addReceiver(new AID("Mars", AID.ISLOCALNAME));
                myAgent.send(msg);
                scheduler.next(delay);
            }
            long dt = scheduler.getDelay();
            if (dt > 0)
            {
                block(dt);
            }
        }
        
        public boolean done()
        {
            //myAgent.doDelete();
            return false;
            //return finished;
        }
    }
}
