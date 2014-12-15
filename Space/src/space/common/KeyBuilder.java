/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.common;

import java.util.Map;

/**
 *
 * @author Sergey
 */
public interface KeyBuilder
{
    public String getKey(Map<String, Object> msgContent);
}
