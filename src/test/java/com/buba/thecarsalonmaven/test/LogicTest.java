/*
 * Copyright (C) 2016 University of Debrecen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.buba.thecarsalonmaven.test;

import com.buba.thecarsalonmaven.handlers.OrderHandler;
import com.buba.thecarsalonmaven.logic.LocalDateAdapter;
import com.buba.thecarsalonmaven.logic.Logic;
import com.buba.thecarsalonmaven.models.Color;
import com.buba.thecarsalonmaven.models.Colors;
import com.buba.thecarsalonmaven.models.ConfCar;
import com.buba.thecarsalonmaven.models.MotorSize;
import com.buba.thecarsalonmaven.models.MotorSizes;
import com.buba.thecarsalonmaven.models.MotorType;
import com.buba.thecarsalonmaven.models.MotorTypes;
import com.buba.thecarsalonmaven.models.Order;
import com.buba.thecarsalonmaven.models.Orders;
import com.buba.thecarsalonmaven.models.Part;
import com.buba.thecarsalonmaven.models.Parts;
import com.buba.thecarsalonmaven.models.User;
import com.buba.thecarsalonmaven.xml.FileCreator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Szanics Levente
 */
public class LogicTest {

    private static final Logic logic = new Logic();
    private Colors colors = new Colors();
    private MotorSizes motorSizes = new MotorSizes();
    private MotorTypes motorTypes = new MotorTypes();
    private ConfCar confCar;
    private Part partA, partB, partC;
    private List<Part> partList = new ArrayList<Part>();
    private Order orderA;
    private Order orderB;
    private Parts parts;
    private OrderHandler handler = new OrderHandler();

    public LogicTest() {
        colors.getColors().add(new Color("szín név", 500));
        motorSizes.getMotorSizes().add(new MotorSize("1.5", 20000));
        motorTypes.getMotorTypes().add(new MotorType("dizel", motorSizes));
        confCar = new ConfCar("teszt név", 400000, colors, motorTypes, 2);
        partA = new Part("A", "3", "500");
        partB = new Part("B", "3", "600");
        partC = new Part("C", "2", "300");
        orderA = new Order(confCar, parts, 30000, "user", LocalDate.now().minusDays(2));
        orderB = new Order(confCar, parts, 30000, "user", LocalDate.now().minusDays(3));
    }

    @Test
    public void testGetCost() {
        partList.add(partA);
        partList.add(partB);
        assertEquals(421600, logic.getCost(confCar, colors.getColors().get(0), motorSizes.getMotorSizes().get(0), partList));
        partList.add(partC);
        assertEquals(421900, logic.getCost(confCar, colors.getColors().get(0), motorSizes.getMotorSizes().get(0), partList));
    }

    @Test
    public void testUsable() {
        assertEquals(true, logic.usable(confCar, partC));
        assertEquals(false, logic.usable(confCar, partA));
    }

    @Test
    public void testRemovable() {
        assertEquals(true, logic.removable(orderA));
        assertEquals(false, logic.removable(orderB));
    }

    @Test
    public void testRemoveOrder() {
        FileCreator fCreator = new FileCreator();
        List<Order> orderListA = new ArrayList<Order>();
        List<Order> orderListB = new ArrayList<Order>();
        List<Order> savedList = new ArrayList<Order>();
        savedList.addAll(logic.getOrdersList());
        orderListA.add(orderA);
        orderListA.add(orderB);
        orderListB.add(orderB);
        assertEquals(orderListB, logic.removeOrder(orderListA, orderA));
        logic.actualizeOrders(savedList);
    }

    @Test
    public void testGetOrdersList() throws Exception {

        FileCreator fCreator = new FileCreator();
        partList.add(partA);
        partList.add(partB);
        Parts parts = new Parts();
        parts.getParts().addAll(partList);
        List<Order> savedList = new ArrayList<Order>();
        List<Order> orderList = new ArrayList<Order>();
        savedList.addAll(logic.getOrdersList());
        orderList.addAll(logic.makeOrder(confCar, motorSizes.getMotorSizes().get(0), motorTypes.getMotorTypes().get(0), colors.getColors().get(0), 20000, partList));
        logic.actualizeOrders(savedList);
        orderList.clear();
        orderList.addAll(logic.getOrdersList());
        assertEquals(savedList, orderList);
    }

    @Test
    public void testRegister(){
        List<User> savedList = new ArrayList<User>();
        List<User> userList = new ArrayList<User>();
        savedList.addAll(logic.getUsersList());
        userList.addAll(logic.register("teszt", "teszt"));
        logic.actualizeUsers(savedList);
        userList.clear();
        userList.addAll(logic.getUsersList());
        assertEquals(userList, savedList);
    }
    
    @Test
    public void testLogin(){
        FileCreator fCreator = new FileCreator();
        assertEquals(true,logic.login("default", "default"));
        assertEquals(false, logic.login("a", "a"));
    }
}
