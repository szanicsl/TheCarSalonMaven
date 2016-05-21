package com.buba.thecarsalonmaven.logic;

import com.buba.thecarsalonmaven.MainApp;
import com.buba.thecarsalonmaven.handlers.OrderHandler;
import com.buba.thecarsalonmaven.handlers.UserHandler;
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
import com.buba.thecarsalonmaven.models.Users;
import com.buba.thecarsalonmaven.xml.WriteXMLFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * A {@link Logic} osztály az üzleti logika egy részét tartalmazó osztály.
 *
 * @author Szanics Levente
 */
public class Logic {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logic.class); 
    
    private SAXParserFactory sPFactory = SAXParserFactory.newInstance();
    private SAXParser parser;
    
    private String path = System.getProperty("user.home") + "/.projectdatabase/";

    private Orders orders = new Orders();
    private Users users = new Users();
    private OrderHandler orderHandler = new OrderHandler();
    private UserHandler userHandler = new UserHandler();
    
    private boolean contains = false;

    /**
     *
     * @return A {@link Logic} osztály metódusai által előállított rendelések listája.
     */
    public Orders getOrders() {
        return orders;
    }

    /**
     * A rendelésbe foglalt termék/termékek összértékét határozza meg.
     *
     * @param confCar A rendelt autó.
     * @param color A rendelt autó színe.
     * @param motorSize A rendelt autó motorjának mérete.
     * @param partList A rendelt autóhoz tartozó extrák.
     * @return Visszaadja a rendelt autó (<code>confCar</code>), a kiválasztott
     * szín (<code>color</code>), a kiválasztott méretű motor típus
     * (<code>motorSize</code>) és az extrák (<code>partList</code>) árának
     * összegét.
     */
    public int getCost(ConfCar confCar, Color color, MotorSize motorSize, List<Part> partList) {
        logger.info("Ár lekérve.");
        return confCar.getCost() + color.getCost() + motorSize.getCost() + partList.stream().mapToInt(p -> Integer.parseInt(p.getPcost())).sum();
    }

    /**
     * Megnézi, hogy a felhasználónév minimum 3 karakter hosszú-e.
     * @param userName A felhasználónév.
     * @return Visszatérési érték {@code true}, ha legalább 3 karakter hosszú a {@code userName}, egyébként {@code false}.
     */
    public boolean userNameValidate(String userName) {
        return userName.length() >= 3;
    }

    /**
     * Megnézi, hogy a jelszó minimum 4 karakter hosszú-e.
     * @param password A jelszó.
     * @return Visszatérési érték {@code true}, ha legalább 4 karakter hosszú a {@code password}, egyébként {@code false}.
     */
    public boolean passwordValidate(String password) {
        return password.length() >= 4;
    }

    /**
     * Megmondja, hogy az adott {@code confCar} autóba beszerelhető-e a
     * {@code part} extra.
     *
     * @param confCar A kiválasztott autó.
     * @param part Beszerelendő extra.
     * @return Visszatérési érték {@code true}, ha beszerelhető, {@code false}
     * egyébként.
     */
    public boolean usable(ConfCar confCar, Part part) {
        logger.info("Használhatóság lekérve.");
        return confCar.getLevel() >= Integer.parseInt(part.getPlevel());
    }

    /**
     * Eldönti, hogy kitörölhető-e a paraméterként kapott {@code order}
     * rendelés.
     *
     * @param order A kitörölendő rendelés.
     * @return Visszatérési érték {@code true}, ha a rendelés kevesebb mint 3
     * napja történt.
     */
    public boolean removable(Order order) {
        logger.info("Törölhetőség lekérve");
        return order.getOrderDate().plusDays(3).isAfter(LocalDate.now());
    }

    /**
     * Kitörli a paraméterként kapott {@code orderList} listából az
     * {@code order} rendelést, majd módosítja a rendeléseket tároló xml fájlt.
     *
     * @param orderList A módosítandó rendelések listája.
     * @param order A kitörlendő rendelés.
     * @return Visszatér azzal listával, amelyből már töröltük a törlendő
     * {@code order} rendelést.
     */
    public List<Order> removeOrder(List<Order> orderList, Order order) {
        try {
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).equals(order)) {
                    orderList.remove(i);
                    break;
                }
            }
            WriteXMLFile w = new WriteXMLFile();
            w.init(1);
            orders.setOrderList(orderList);
            w.getJaxbMarshaller().marshal(orders, new File(path + "orders.xml"));
            logger.info("orders.xml sikeresen módosítva. Törlés történt.");
        } catch (JAXBException ex) {
            logger.error(ex.getClass().getName() + ": orders.xml létrehozási/módosítási hiba.");
        }
        return orderList;
    }

    /**
     * Létrehoz egy új rendelést a kapott paraméterek alapján, majd azt
     * hozzáadja az orders.xml-ből beolvasott rendelések listájához, és ezt a
     * listát
     *
     * @param confCar A rendelt autó.
     * @param motorSize A rendelt autó motor mérete.
     * @param motorType A rendelt autó motor típusa.
     * @param color A rendelt autó színe.
     * @param cost A rendelt autó ára.
     * @param partList A rendelt autóba rakott extrák listája.
     * @return Visszatér azzal listával, amelyhez már hozzáadtuk a rendelést.
     */
    public List<Order> makeOrder(ConfCar confCar, MotorSize motorSize, MotorType motorType, Color color, int cost, List<Part> partList) {
        MotorSizes motorSizes = new MotorSizes();
        MotorTypes motorTypes = new MotorTypes();
        Colors colors = new Colors();
        Parts parts = new Parts();

        motorSizes.getMotorSizes().add(motorSize);
        motorType.setMotorSizes(motorSizes);
        motorTypes.getMotorTypes().add(motorType);
        colors.getColors().add(color);
        parts.getParts().addAll(partList);

        confCar.setColors(colors);
        confCar.setMotorTypes(motorTypes);
        getOrdersList();

        Order order = new Order(confCar, parts, cost, MainApp.onlineUser.get(), LocalDate.now());
        try {
            WriteXMLFile w = new WriteXMLFile();
            w.init(1);
            orders.getOrderList().add(order);
            w.getJaxbMarshaller().marshal(orders, new File(path + "orders.xml"));
            logger.info("orders.xml sikeresen módosítva. Rendelés történt.");
            return orders.getOrderList();
        } catch (JAXBException ex) {
            logger.error(ex.getClass().getName() + ": orders.xml létrehozási/módosítási hiba.");
            return null;
        }
    }

    /**
     * A orders.xml fájlban tárolt {@link Order} objektumok listáját felülírja a paraméterül kapott listával.
     * @param ordersList A rendelések listája.
     * @return Aktualizált {@link Order} lista.
     */
    public List<Order> actualizeOrders(List<Order> ordersList) {
        try {
            WriteXMLFile w = new WriteXMLFile();
            w.init(1);
            orders.getOrderList().clear();
            orders.getOrderList().addAll(ordersList);
            w.getJaxbMarshaller().marshal(orders, new File(path + "orders.xml"));
            logger.info("orders.xml sikeresen módosítva. Rendelés történt.");
            return orders.getOrderList();
        } catch (JAXBException ex) {
            logger.error(ex.getClass().getName() + ": orders.xml létrehozási/módosítási hiba.");
            return null;
        }
    }

    /**
     * Lekéri az orders.xml-ből a rendeléseket, feltölti a
     * {@code orderHandler}-ben található listát velük, majd átadja azt az
     * {@link Orders} típusú statikus {@link orders} tagnak.
     *
     * @return Az orders.xml-ben található rendelések listája.
     */
    public List<Order> getOrdersList() {
        try {
            parser = sPFactory.newSAXParser();
            if (!orderHandler.getOrdersList().isEmpty()) {
                orderHandler.getOrdersList().clear();
            }
            parser.parse(new File(path + "orders.xml"), orderHandler);

            orders.setOrderList(orderHandler.getOrdersList());

            logger.info("Rendelések sikeresen lekérdezve.");

            return orderHandler.getOrdersList();
        } catch (ParserConfigurationException ex) {
            logger.error(ex.getClass().getName() + ": Sikertelen dátum átalakítás.");
            return null;
        } catch (SAXException ex) {
            logger.error(ex.getClass().getName() + ": Sikertelen XML beolvasás.");
            return null;
        } catch (IOException ex) {
            logger.error(ex.getClass().getName() + ": Sikertelen XML megnyitás.");
            return null;
        }
    }

    /**
     *
     * @return A users.xml-ben található felhasználók listája.
     */
    public List<User> getUsersList() {
        try {
            parser = sPFactory.newSAXParser();
            if (!userHandler.getUserList().isEmpty()) {
                userHandler.getUserList().clear();
            }
            parser.parse(new File(path + "users.xml"), userHandler);

            users.setUsers(userHandler.getUserList());

            logger.info("Felhasználók sikeresen lekérdezve.");

            return userHandler.getUserList();
        } catch (ParserConfigurationException ex) {
            logger.error(ex.getClass().getName() + ": Sikertelen SAX parser létrehozás.");
            return null;
        } catch (SAXException ex) {
            logger.error(ex.getClass().getName() + ": Sikertelen XML beolvasás.");
            return null;
        } catch (IOException ex) {
            logger.error(ex.getClass().getName() + ": Sikertelen XML megnyitás.");
            return null;
        }
    }

    /**
     * Regisztrálja a felhasználót a users.xml fájlban.
     * @param userName A felhasználónév.
     * @param password A jelszó.
     * @return Visszaadja a regisztrált felhasználót tartalmazó {@link User} listát.
     */
    public List<User> register(String userName, String password) {

        getUsersList();

        User user = new User(userName, password);

        try {
            WriteXMLFile w = new WriteXMLFile();
            w.init(0);
            users.getUsers().add(user);
            w.getJaxbMarshaller().marshal(users, new File(path + "users.xml"));
            logger.info("users.xml sikeresen módosítva. Regisztáció történt.");
        } catch (JAXBException ex) {
            logger.error(ex.getClass().getName() + ": users.xml létrehozási/módosítási hiba.");
            return null;
        }

        return users.getUsers();
    }

    /**
     * A users.xml fájlban tárolt {@link User} objektumok listáját felülírja a paraméterül kapott listával.
     * @param userList A felhasználó lista.
     * @return Aktualizált {@link User} lista.
     */
    public List<User> actualizeUsers(List<User> userList) {
        try {
            WriteXMLFile w = new WriteXMLFile();
            w.init(0);
            users.getUsers().clear();
            users.getUsers().addAll(userList);
            w.getJaxbMarshaller().marshal(users, new File(path + "users.xml"));
            logger.info("users.xml sikeresen módosítva. Regisztáció történt.");
        } catch (JAXBException ex) {
            logger.error(ex.getClass().getName() + ": users.xml létrehozási/módosítási hiba.");
            return null;
        }
        return users.getUsers();
    }

    /**
     * Amennyiben a felhasználónév és a jelszó egyezik egy, a felhasználók listájában szereplő {@link User} objektuméval, akkor igazzal tér vissza, tehát a belépő felhasználó a {@code userName} nevű lesz.
     * @param userName A felhasználónév.
     * @param password A jelszó.
     * @return Ha a felhasználónév és a jelszó egyezik egy, a felhasználók listájában szereplő {@link User} objektuméval, akkor {@code true}, egyébként {@code false}.
     */
    public boolean login(String userName, String password) {
        getUsersList();
        users.getUsers().stream().forEach((u) -> {
            if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                contains = true;
            }
        });
        if (contains) {
            contains = false;
            return !contains; //true
        } else {
            return contains; //false
        }
    }
}
