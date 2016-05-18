package com.buba.thecarsalonmaven;

import com.buba.thecarsalonmaven.handlers.OrderHandler;
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
import com.buba.thecarsalonmaven.xml.WriteXMLFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * A {@link Logic} osztály az üzleti logika egy részét tartalmazó osztály.
 * @author Szanics Levente
 */
public class Logic {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logic.class);
    private String path = System.getProperty("user.home")+"/.projectdatabase/";
    /**
     * Egy {@link Orders} típusú objektum, amelyben letároljuk a {@link Logic} osztály metódusai által előállított rendelések listáját.
     */
    public static Orders orders = new Orders();
    private OrderHandler handler = new OrderHandler();

    private SAXParserFactory sPFactory = SAXParserFactory.newInstance();
    private SAXParser parser;

    /**
     * A rendelésbe foglalt termék/termékek összértékét határozza meg.
     * @param confCar A rendelt autó.
     * @param color A rendelt autó színe.
     * @param motorSize A rendelt autó motorjának mérete.
     * @param partList A rendelt autóhoz tartozó extrák.
     * @return Visszaadja a rendelt autó (<code>confCar</code>), a kiválasztott szín (<code>color</code>), a kiválasztott méretű motor típus (<code>motorSize</code>) és az extrák (<code>partList</code>) árának összegét.
     */
    public int getCost(ConfCar confCar, Color color, MotorSize motorSize, List<Part> partList) {
        logger.info("Ár lekérve.");
        return confCar.getCost() + color.getCost() + motorSize.getCost() + partList.stream().mapToInt(p -> Integer.parseInt(p.getPcost())).sum();
    }

    /**
     * Megmondja, hogy az adott {@code confCar} autóba beszerelhető-e a {@code part} extra.
     * @param confCar A kiválasztott autó.
     * @param part Beszerelendő extra.
     * @return Visszatérési érték {@code true}, ha beszerelhető, {@code false} egyébként.
     */
    public boolean usable(ConfCar confCar, Part part) {
        logger.info("Használhatóság lekérve.");
        return confCar.getLevel() >= Integer.parseInt(part.getPlevel());
    }

    /**
     * Eldönti, hogy kitörölhető-e a paraméterként kapott {@code order} rendelés.
     * @param order A kitörölendő rendelés.
     * @return Visszatérési érték {@code true}, ha a rendelés kevesebb mint 3 napja történt.
     */
    public boolean removable(Order order) {
        logger.info("Törölhetőség lekérve");
        return order.getOrderDate().plusDays(3).isAfter(LocalDate.now());
    }
    
    /**
     * Kitörli a paraméterként kapott {@code orderList} listából az {@code order} rendelést, majd módosítja a rendeléseket tároló xml fájlt.
     * @param orderList A módosítandó rendelések listája.
     * @param order A kitörlendő rendelés.
     * @return Visszatér azzal listával, amelyből már töröltük a törlendő {@code order} rendelést.
     */
    public List<Order> removeOrder(List<Order> orderList, Order order){
        try {
            for(int i=0; i< orderList.size();i++)
            {
                if(orderList.get(i).equals(order)){
                    orderList.remove(i);
                    break;
                }
            }
            WriteXMLFile w = new WriteXMLFile();
            w.init(1);
            orders.setOrderList(orderList);
            w.getJaxbMarshaller().marshal(orders, new File(path+"orders.xml"));
            logger.info("orders.xml sikeresen módosítva. Törlés történt.");
        } catch (JAXBException ex) {
            ex.printStackTrace();
            logger.error(ex.getClass().getName() + ": orders.xml létrehozási/módosítási hiba.");
        }
        return orderList;
    }

    /**
     * Létrehoz egy új rendelést a kapott paraméterek alapján, majd azt hozzáadja az orders.xml-ből beolvasott rendelések listájához, és ezt a listát 
     * @param confCar A rendelt autó.
     * @param motorSize A rendelt autó motor mérete.
     * @param motorType A rendelt autó motor típusa.
     * @param color A rendelt autó színe.
     * @param cost A rendelt autó ára.
     * @param partList A rendelt autóba rakott extrák listája.
     */
    public void makeOrder(ConfCar confCar, MotorSize motorSize, MotorType motorType, Color color, int cost, ObservableList<Part> partList) {
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

        try {
            WriteXMLFile w = new WriteXMLFile();
            w.init(1);
            orders.getOrderList().add(new Order(confCar, parts, cost, MainApp.onlineUser.get(), LocalDate.now()));
            w.getJaxbMarshaller().marshal(orders, new File(path+"orders.xml"));
            logger.info("orders.xml sikeresen módosítva. Rendelés történt.");
        } catch (JAXBException ex) {
            ex.printStackTrace();
            logger.error(ex.getClass().getName() + ": orders.xml létrehozási/módosítási hiba.");
        }
    }
    
    /**
     * Lekéri az orders.xml-ből a rendeléseket, feltölti a {@code handler}-ben található listát velük, majd átadja azt az {@link Orders} típusú statikus {@link orders} tagnak.
     */
    public void getOrdersList() {
        try {
            parser = sPFactory.newSAXParser();
            if (!handler.getOrdersList().isEmpty()) {
                handler.getOrdersList().clear();
            }
            parser.parse(new File(path + "orders.xml"), handler);

            orders.setOrderList(handler.getOrdersList());
            logger.info("Rendelések sikeresen lekérdezve.");
        } catch (ParserConfigurationException ex) {
            logger.error(ex.getClass().getName() + ": Sikertelen dátum átalakítás.");
        } catch (SAXException ex) {
            logger.error(ex.getClass().getName() + ": Sikertelen XML beolvasás.");
        } catch (IOException ex) {
            logger.error(ex.getClass().getName() + ": Sikertelen XML megnyitás.");
        }
    }
}
