/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.monitors;

import jade.core.Agent;

/**
 *
 * @author Sergey
 */
public class MonitorAgent extends Agent
{
    protected void setup()
    {
    }
    
    protected void takeDown()
    {
        System.exit(0);
    }
}
