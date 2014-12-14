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
import jade.lang.acl.ACLMessage;
import java.util.HashMap;
import java.util.Map;
import space.common.ACLMessageHandler;
import space.common.KeyBuilder;
import space.common.KeyValueList;
import space.common.MessageHandler;
import space.common.SimpleACLMessageHandler;
import space.common.SimpleDeleteBehaviour;
import space.common.SimpleReceiverBehaviour;

/**
 *
 * @author Sergey
 */
public class SimpleShipAgent extends Agent
{
    private ACLMessageHandler msgHandler;
    private Map<String, Object> ship;
    
    private boolean shipInitialization(ACLMessage msg, Map<String, Object> msgContent)
    {
        boolean result = true;
        ship = new HashMap<>();
        ship.put("name", getLocalName());
        ship.put("parent", msg.getSender());
        Map<String, Object> content = (Map<String, Object>)msgContent.get("content");
        Map<String, Object> planet = (Map<String, Object>)content.get("planet");
        //Map<String, Object> civilization = (Map<String, Object>)content.get("civilization");
        ship.put("coordinates", planet.get("coordinates"));
        return result;
    }

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

    private boolean initialization()
    {
        boolean result = false;
        Object[] args = this.getArguments();
        if (args != null && args.length == 2 && args[0] instanceof ACLMessage && args[1] instanceof Map)
        {
            result = shipInitialization((ACLMessage)args[0], (Map<String, Object>)args[1]);
        }
        return result;
    }
    
    protected void setup()
    {
        if (initialization())
        {
            System.out.println(getLocalName() + " was created.");
            messageHandlersInitialization();
            addBehaviour(new SimpleReceiverBehaviour(msgHandler));
            servicesRegistration();
        }
        else
        {
            String msg = getLocalName() + ": Incorrect creation.";
            addBehaviour(new SimpleDeleteBehaviour(msg));
        }
    }

    protected void takeDown()
    {
        servicesDeregistration();
        System.exit(0);
    }
}
