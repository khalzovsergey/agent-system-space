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
//import jade.wrapper.AgentContainer;
//import jade.wrapper.AgentController;
import java.util.HashMap;
import java.util.Map;
import space.ACLMessageHandler;
import space.KeyBuilder;
import space.KeyValueList;
import space.MessageHandler;
import space.SimpleACLMessageHandler;

/**
 *
 * @author Sergey
 */
public class SimpleMonitorAgent extends Agent
{
    private ACLMessageHandler msgHandler;

    private void messageHandlersInitialization()
    {
        KeyValueList list = new KeyValueList("type");
        KeyBuilder keyBuilder = new KeyBuilder(list);
        Map<String, MessageHandler> messageHandlers = new HashMap<>();
        list.setValue("type", "text");
        messageHandlers.put(KeyBuilder.build(list), new TextMassageHandler());
        msgHandler = new SimpleACLMessageHandler(keyBuilder, messageHandlers);
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
//        Object[] args = this.getArguments();
//        if (args != null)
//        {
//            //jade.Boot
//            System.out.println("\n\n\n");
//            System.out.println(args.length);
//            System.out.println("\n\n\n");
//            if (args.length > 0)
//            {
//                System.out.println(args[0].toString());
//            }
//            System.out.println("\n\n\n");
//        }
    }
    
    protected void setup()
    {
        initialization();
        messageHandlersInitialization();
        addBehaviour(new ReceiverBehaviour(msgHandler));
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
