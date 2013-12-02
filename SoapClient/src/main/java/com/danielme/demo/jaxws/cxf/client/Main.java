package com.danielme.demo.jaxws.cxf.client;
 
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.danielme.demo.jaxws.cxf.model.Player;
import com.danielme.demo.jaxws.cxf.ws.ITeamService;
 
/**
 * 
 * @author danielme.com
 *
 */
public class Main 
{
 
    public static final Logger logger = Logger.getLogger(Main.class);
 
    public static void main(String[] args) throws Exception
    {
//        Properties properties = new Properties();
//        properties.load(Main.class.getClassLoader().getResourceAsStream("config.properties"));
//         
//        //client
//        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//        jaxWsProxyFactoryBean.setServiceClass(ITeamService.class);
//        jaxWsProxyFactoryBean.setAddress(properties.getProperty("endpoint"));
//        ITeamService teamServiceClient = (ITeamService) jaxWsProxyFactoryBean.create();
         
    	 //Initializes Spring Container
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");      
        ITeamService teamServiceClient = (ITeamService) applicationContext.getBean("teamServiceClient");       
         
        //test
        logger.info("getTeam");     
        List<Player> team = teamServiceClient.getTeam();
        for (Player player : team)
        {
            logger.info("       " + player.getNumber() + " : " + player.getName() + " (" + player.getAge() +")");
        }       
         
        logger.info("\n updatePlayerByNumber");
        teamServiceClient.updatePlayerByNumber(1, new Player(1,"Anders Lindegaard", 28));
         
        logger.info("\n deletePlayer");
        teamServiceClient.deletePlayer(6); 
         
        logger.info("\n getPlayers");
        team = teamServiceClient.getPlayers(1,3,6);     
        for (Player player : team)
        {
            logger.info("       " + player.getNumber() + " : " + player.getName() + " (" + player.getAge() +")");
        }
    }
 
}