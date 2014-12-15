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
import java.util.HashMap;
import java.util.Map;
import space.common.ACLMessageHandler;
import space.common.KeyBuilder;
import space.common.KeyValueList;
import space.common.MessageHandler;
import space.common.SimpleACLMessageHandler;
import space.common.SimpleKeyBuilder;
import space.common.SimpleReceiverBehaviour;

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
        KeyBuilder keyBuilder = new SimpleKeyBuilder(list);
        Map<String, MessageHandler> messageHandlers = new HashMap<>();
        list.setValue("type", "createShip");
        messageHandlers.put(SimpleKeyBuilder.getKey(list), new SimpleShipFactoryMH());
        msgHandler = new SimpleACLMessageHandler(keyBuilder, messageHandlers);
    }

    private void servicesRegistration()
    {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        ServiceDescription sd = new ServiceDescription();
        sd.setType("createShip");
        sd.setName(SimpleKeyBuilder.join(getLocalName(), "createShip"));
        dfd.addServices(sd);

        try
        {
            DFService.register(this, dfd);
        }
        catch (Exception e)
        {
            System.err.println(getLocalName() + ": services not registered.");
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
