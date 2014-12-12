/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.ships;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
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
public class SimpleShipAgent extends Agent
{
    private ACLMessageHandler msgHandler;

    private void messageHandlersInitialization()
    {
        KeyValueList list = new KeyValueList("type");
        KeyBuilder keyBuilder = new KeyBuilder(list);
        Map<String, MessageHandler> messageHandlers = new HashMap<>();
//        list.setValue("type", "text");
//        messageHandlers.put(KeyBuilder.build(list), new TextMassageHandler());
        msgHandler = new SimpleACLMessageHandler(keyBuilder, messageHandlers);
    }

    private void servicesRegistration()
    {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        ServiceDescription sd = new ServiceDescription();
        sd.setType("getCoordinates");
        sd.setName(KeyBuilder.build(getLocalName(), "getCoordinates"));
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
        servicesRegistration();
    }

    protected void takeDown()
    {
        servicesDeregistration();
        System.exit(0);
    }
}
