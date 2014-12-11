/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.monitors;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import java.util.HashMap;
import java.util.Map;
import space.KeyBuilder;
import space.MessageHandler;
import space.ReceiverBehaviour;

/**
 *
 * @author Sergey
 */
public class MonitorAgent extends Agent
{
    private Map<String, MessageHandler> messageHandlers;

    private void messageHandlersInitialization()
    {
        messageHandlers = new HashMap<>();
        messageHandlers.put(KeyBuilder.build("text"), new TextMassageHandler());
    }

    private void servicesRegistration()
    {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        ServiceDescription sd = new ServiceDescription();
        sd.setType("monitor");
        sd.setName(KeyBuilder.build(getLocalName(), "monitor"));
        dfd.addServices(sd);

        try
        {
            DFService.register(this, dfd);
        }
        catch (FIPAException e)
        {
        }
    }

    private void servicesDeregistration()
    {
        try
        {
            DFService.deregister(this);
        }
        catch (Exception e)
        {
        }
    }

    private void initialization()
    {
        //Object[] args = this.getArguments();
    }
    
    protected void setup()
    {
        initialization();
        messageHandlersInitialization();
        addBehaviour(new ReceiverBehaviour(messageHandlers));
        addBehaviour(new RequestBehaviour());
        servicesRegistration();
    }

    protected void takeDown()
    {
        servicesDeregistration();
        System.exit(0);
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
    
//    class AppRunner
//    {
//        public void main1(String args[])
//        {
//            Runtime r = Runtime.getRuntime();
//            Process p = null;
//            String cmd = "notepad";
//            try
//            {
//                p = r.exec(cmd);
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
}
