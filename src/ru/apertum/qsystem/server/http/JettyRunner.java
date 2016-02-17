/*
 * Copyright (C) 2010 {Apertum}Projects. web: www.apertum.ru email: info@apertum.ru
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
package ru.apertum.qsystem.server.http;

import java.io.File;
/*
 import java.io.FilenameFilter;
 import java.io.IOException;
 import java.lang.reflect.InvocationTargetException;
 import java.lang.reflect.Method;
 import java.net.MalformedURLException;
 import java.net.URL;
 import java.net.URLClassLoader;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.lang.ArrayUtils;
 import org.eclipse.jetty.servlet.ServletHolder;
 */
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import ru.apertum.qsystem.common.QLog;
import ru.apertum.qsystem.common.exceptions.ServerException;

/**
 * Class start and stop the Jetty server. When starting a new thread is created, and it will start Jetty
 *
 * @author Evgeniy Egorov
 * @author Alfonso Tienda <atienda@iprocuratio.com>
 */
public class JettyRunner implements Runnable {

    /**
     * Start Jetty
     *
     * @param port
     *            the port on which the server starts
     */
    public static void start(int port) {
        servetPort = port;
        if (jetthread != null && jetthread.isInterrupted() == false) {
            try {
                if (jetty.isRunning()) {
                    jetty.stop();
                }
            } catch (Exception ex) {
                QLog.l().logger().error("Error stop Jetty server.", ex);
            }
            jetthread.interrupt();
        }
        jetthread = new Thread(new JettyRunner());
        jetthread.setDaemon(true);
        jetthread.start();
    }

    /**
     * Stops Jetty server
     */
    public static void stop() {
        if (jetthread != null && jetthread.isInterrupted() == false) {
            try {
                if (jetty.isRunning()) {
                    jetty.stop();
                }
            } catch (Exception ex) {
                throw new ServerException("Error stopping Jetty Server.", ex);
            }
            jetthread.interrupt();
        }
        QLog.l().logger().info("Jetty server stopped successfully.");
    }

    private static volatile Server jetty = null;
    private static int servetPort = 8081;
    private static Thread jetthread = null;

    @Override
    public void run() {
        QLog.l().logger().info("Start Jetty server on port " + servetPort);
        jetty = new Server();

        // org.eclipse.jetty.io.nio.AsyncConnection d;
        HttpConfiguration http_config = new HttpConfiguration();
        http_config.setSecureScheme("https");
        http_config.setSecurePort(8443);
        http_config.setOutputBufferSize(32768);
        http_config.setRequestHeaderSize(8192);
        http_config.setResponseHeaderSize(8192);
        http_config.setSendServerVersion(true);
        http_config.setSendDateHeader(false);
        ServerConnector http_connector = new ServerConnector(jetty, new HttpConnectionFactory(http_config));
        http_connector.setIdleTimeout(30000);
        http_connector.setPort(servetPort);
        jetty.addConnector(http_connector);

        final ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[] { "index.html" });
        resource_handler.setResourceBase("www");

        /*
         * // WebSocket: Регистрируем ChatWebSocketHandler в сервере Jetty.
         * такой метож сдох в jttty9 QWebSocketHandler qWebSocketHandler = new
         * QWebSocketHandler(); // Это вариант хэндлера для
         * WebSocketHandlerContainer qWebSocketHandler.setHandler(new
         * DefaultHandler());
         * 
         */
        final ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContext.setContextPath("/");
        // If necessary, have the servlet, adding them to the handlers like this
        // servletContext.addServlet(new ServletHolder(new HelloServlet()),
        // "/hell");

        /*
         * // поддержка расширяемости плагинами. На будующее, пото если
         * понадобится приделаю сервлеты как плагины for (final
         * IChangeCustomerStateEvent event :
         * ServiceLoader.load(IChangeCustomerStateEvent.class)) {
         * QLog.l().logger().info("Вызов SPI расширения. Описание: " +
         * event.getDescription()); try { event.change(this, state,
         * newServiceId); } catch (Throwable tr) { QLog.l().logger().error(
         * "Вызов SPI расширения завершился ошибкой. Описание: " + tr); } }
         */
        final HandlerList handlers = new HandlerList();

        // An important point - the order of the Handler so the order will be
        // sent a request, if it is not processed ie since the beginning of the
        // file is searched for, if not found, then the URL is transmitted to
        // the execution team, komaedah taken into account that the URL of
        // vebsoketa need to traverse further, it will catch Handler vebsoketov
        
        // handlers.setHandlers(new Handler[]{resource_handler, new
        // CommandHandler(), qWebSocketHandler});
        handlers.setHandlers(new Handler[] { resource_handler, new CommandHandler(), servletContext });

        // Download war folder
        String folder = "./www/war/";
        QLog.l().logger().info("Downloading war folder " + folder);
        final File[] list = new File(folder).listFiles((File dir, String name) -> name.toLowerCase().endsWith(".war"));
        if (list != null && list.length != 0) {
            for (File file : list) {
                final String name = file.getName().substring(0, file.getName().lastIndexOf(".")).toLowerCase();
                QLog.l().logger().debug("WAR " + name + ": " + file.getAbsolutePath());
                final WebAppContext webapp = new WebAppContext();
                webapp.setContextPath("/" + name);
                webapp.setWar(file.getAbsolutePath());
                handlers.addHandler(webapp);
            }
        }

        jetty.setHandler(handlers);

        try {
            jetty.start();
        } catch (Exception ex) {
            throw new ServerException("Jetty server failed to start. ", ex);
        }
        QLog.l().logger().info("Join Jetty server on port" + servetPort);
        try {
            jetty.join();
        } catch (InterruptedException ex) {
            QLog.l().logger().warn("Jetty has stopped working");
        }
        QLog.l().logger().info("Jetty server is stopped.");
    }

}
