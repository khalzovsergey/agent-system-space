/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import jade.lang.acl.ACLMessage;

/**
 *
 * @author Sergey
 */
public interface ACLMessageHandler
{
    public void Invoke(ACLMessage msg);
}
