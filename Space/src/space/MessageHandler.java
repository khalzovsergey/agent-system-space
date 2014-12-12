/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import jade.lang.acl.ACLMessage;
import java.util.Map;

/**
 *
 * @author Sergey
 */
public interface MessageHandler
{
    public void Invoke(ACLMessage msg, Map<String, Object> content);
}
