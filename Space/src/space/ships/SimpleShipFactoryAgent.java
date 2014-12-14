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
import space.SimpleReceiverBehaviour;

/**
 *
 * @author Sergey
 */
public class SimpleShipFactoryAgent extends Agent
{
    private ACLMessageHandler msgHandler;

    private void messageHandlersInitialization()
    {
        KeyValueList list = new KeyValueList("type");
        KeyBuilder keyBuilder = new KeyBuilder(list);
        Map<String, MessageHandler> messageHandlers = new HashMap<>();
        list.setValue("type", "createShip");
        messageHandlers.put(KeyBuilder.build(list), new SimpleShipFactoryMH());
        msgHandler = new SimpleACLMessageHandler(keyBuilder, messageHandlers);
    }

    private void servicesRegistration()
    {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        ServiceDescription sd = new ServiceDescription();
        sd.setType("createShip");
        sd.setName(KeyBuilder.build(getLocalName(), "createShip"));
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
    }
    
    protected void setup()
    {
        initialization();
        messageHandlersInitialization();
        addBehaviour(new SimpleReceiverBehaviour(msgHandler));
        servicesRegistration();
    }

    protected void takeDown()
    {
        servicesDeregistration();
        System.exit(0);
    }
}
