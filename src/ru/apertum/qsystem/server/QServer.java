/*
 *  Copyright (C) 2010 {Apertum}Projects. web: www.apertum.ru email: info@apertum.ru
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.apertum.qsystem.server;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import java.util.ServiceLoader;
import ru.apertum.qsystem.About;
import ru.apertum.qsystem.client.Locales;
import ru.apertum.qsystem.client.forms.FAbout;
import ru.apertum.qsystem.common.CodepagePrintStream;
import ru.apertum.qsystem.common.GsonPool;
import ru.apertum.qsystem.common.Mailer;
import ru.apertum.qsystem.common.QConfig;
import ru.apertum.qsystem.common.Uses;
import ru.apertum.qsystem.common.QLog;
import ru.apertum.qsystem.common.cmd.JsonRPC20;
import ru.apertum.qsystem.common.cmd.RpcGetAdvanceCustomer;
import ru.apertum.qsystem.common.exceptions.ServerException;
import ru.apertum.qsystem.common.model.ATalkingClock;
import ru.apertum.qsystem.common.model.QCustomer;
import ru.apertum.qsystem.extra.IStartServer;
import ru.apertum.qsystem.hibernate.AnnotationSessionFactoryBean;
import ru.apertum.qsystem.reports.model.QReportsList;
import ru.apertum.qsystem.reports.model.WebServer;
import ru.apertum.qsystem.server.controller.Executer;
import ru.apertum.qsystem.server.http.JettyRunner;
import ru.apertum.qsystem.server.model.QService;
import ru.apertum.qsystem.server.model.QServiceTree;
import ru.apertum.qsystem.server.model.QUser;
import ru.apertum.qsystem.server.model.QUserList;
import ru.apertum.qsystem.server.model.postponed.QPostponedList;

/**
 * Starts and exits the server initialization. Flow management assignments.
 *
 * @author Evgeniy Egorov
 * @author Alfonso Tienda
 */
public class QServer extends Thread {

    private final Socket socket;

    /**
     * @param args - primer parametro pasado a nombre completo ajuste-XML
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        About.printdef();
        QLog.initial(args, 0);
        Locale.setDefault(Locales.getInstance().getLangCurrent());

        //Configuración de los mensajes de la consola de salida a la codificación correcta
        if ("\\".equals(File.separator)) {
            try {
                String consoleEnc = System.getProperty("console.encoding", "Cp866");
                System.setOut(new CodepagePrintStream(System.out, consoleEnc));
                System.setErr(new CodepagePrintStream(System.err, consoleEnc));
            } catch (UnsupportedEncodingException e) {
                System.out.println("Unable to setup console codepage: " + e);
            }
        }

        System.out.println("Bienvenidos al servidor iProcuratio QSystem. El MYSQL debe estar activo.");

        FAbout.loadVersionSt();

        if (Locales.getInstance().isRuss) {

            if ("0".equals(FAbout.CMRC_)) {
                System.out.println("Bienvenido al QSystem servidor. Para el trabajo requiere MySQL 5.5 o superior.");
                System.out.println("Versión del servidor: " + FAbout.VERSION_ + "-community QSystem Server (GPL) + iProcuratio QSystem");
                System.out.println("Versión de la base de datos: " + FAbout.VERSION_DB_ + " para MySQL 5.5-community Server (GPL)");
                System.out.println("Fecha de publicación : " + FAbout.DATE_);
                System.out.println("Copyright (c) 2016, Apertum Projects. iProcuratio Consultores.");
                System.out.println("QSystem es un software libre, puede ");
                System.out.println("distribuir y / o modificarlo bajo los términos de la General Public");
                System.out.println("License GNU (GNU GPL), publicada por la Free Softwafre Foundation (FSF)");
                System.out.println(" ya sea la versión 3 de la Licencia, o cualquier versión posterior.");

                System.out.println("Debería haber recibido una copia de la Licencia Pública General de GNU junto");
                System.out.println("con este programa. Si no es así, escriba a la Free Software Foundation");
                System.out.println("(Free Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA)");
            }

            System.out.println("Escriba \"exit\" para detener limpiamente el servidor.");
            System.out.println();
        } else {
            if ("0".equals(FAbout.CMRC_)) {
                System.out.println("Server version: " + FAbout.VERSION_ + "-community QSystem Server (GPL)");
                System.out.println("Database version: " + FAbout.VERSION_DB_ + " for MySQL 5.5-community Server (GPL)");
                System.out.println("Released : " + FAbout.DATE_);

                System.out.println("Copyright (c) 2010-2016, Apertum Projects - iProcuratio Consultores ");
                System.out.println("Este software viene con ABSOLUTAMENTE NINGUNA GARANTÍA. Esto es software libre,");
                System.out.println("y le invitamos a modificar y redistribuirlo bajo licencia GPL v3");
                System.out.println("El texto de la licencia (en inglés) está incluido en una carpeta del programa");
            }

            System.out.println("Escriba 'exit' para detener los trabajos y detener el servidor.");
            System.out.println();
        }

        final long start = System.currentTimeMillis();

        // Загрузка плагинов из папки plugins
        if (!QConfig.cfg().isNoPlugins()) {
            Uses.loadPlugins("./plugins/");
        }

        // is not necessary to look at whether to start jetty
        // it needs to start with the key http
        // if you can fill key http, then run the server and accept it commands to the server MSA
        if (QConfig.cfg().getHttp() > 0) {
            QLog.l().logger().info("Run Jetty.");
            try {
                JettyRunner.start(QConfig.cfg().getHttp());
            } catch (NumberFormatException ex) {
                QLog.l().logger().error("El número del parámetro para el inicio del Jetty no es un número válido. El formato del parámetro es 8081 '-http 8081'.", ex);
            }
        }

        // Reporting Server, servidor web que se encarga de las solicitudes para la emisión de informes
        WebServer.getInstance().startWebServer(ServerProps.getInstance().getProps().getWebServerPort());
        loadPool();
        // run the engine for display customer messages 
        MainBoard.getInstance().showBoard();
        // test ServerProps.getInstance().getProps().getZoneBoardServAddrList();
        if (!(Uses.format_HH_mm.format(ServerProps.getInstance().getProps().getStartTime()).equals(Uses.format_HH_mm.format(ServerProps.getInstance().getProps().getFinishTime())))) {
            /**
             * Timer, by which we will clear all services and sending spam to the daily report.
             */
            ATalkingClock clearServices = new ATalkingClock(Uses.DELAY_CHECK_TO_LOCK, 0) {

                @Override
                public void run() {
                    // reset
                    if (!QConfig.cfg().isRetain() && Uses.format_HH_mm.format(new Date(new Date().getTime() + 10 * 60 * 1000)).equals(Uses.format_HH_mm.format(ServerProps.getInstance().getProps().getStartTime()))) {
                        QLog.l().logger().info("Desactivando todos los servicios.");
                        // clean all the services of the customers on previous day
                        QServer.clearAllQueue();
                    }

                    // is sending daily report
                    if (("true".equalsIgnoreCase(Mailer.fetchConfig().getProperty("mailing")) || "1".equals(Mailer.fetchConfig().getProperty("mailing")))
                            && Uses.format_HH_mm.format(new Date(new Date().getTime() - 30 * 60 * 1000)).equals(Uses.format_HH_mm.format(ServerProps.getInstance().getProps().getFinishTime()))) {
                        QLog.l().logger().info("Boletín de noticias Informe diario.");
                        // Limpiar los servicios para los clientes del día anterior
                        for (QUser user : QUserList.getInstance().getItems()) {
                            if (user.getReportAccess()) {
                                final HashMap<String, String> p = new HashMap<>();
                                p.put("date", Uses.format_dd_MM_yyyy.format(new Date()));
                                final byte[] result = QReportsList.getInstance().generate(user, "/distribution_job_day.pdf", p);
                                try {
                                    try (FileOutputStream fos = new FileOutputStream("temp/distribution_job_day.pdf")) {
                                        fos.write(result);
                                        fos.flush();
                                    }
                                    Mailer.sendReporterMailAtFon(null, null, null, "temp/distribution_job_day.pdf");
                                } catch (Exception ex) {
                                    QLog.l().logger().error("Errores con informes diarios", ex);
                                }
                                break;
                            }
                        }
                    }
                }
            };
            clearServices.start();
        }

     // Connect plug-ins, which will start at the beginning.
        // Support extensibility plug-ins
        for (final IStartServer event : ServiceLoader.load(IStartServer.class)) {
            QLog.l().logger().info("Llamada al SPI: " + event.getDescription());
            try {
                new Thread(() -> {
                    event.start();
                }).start();
            } catch (Throwable tr) {
                QLog.l().logger().error("Fallo en la llamada al SPI: " + tr);
            }
        }

        // Screw the socket on localhost port 3128
        final ServerSocket server;
        try {
            QLog.l().logger().info("The server system captures the port \"" + ServerProps.getInstance().getProps().getServerPort() + "\".");
            server = new ServerSocket(ServerProps.getInstance().getProps().getServerPort());
        } catch (IOException e) {
            throw new ServerException("Network error. Creating net socket is not possible: " + e);
        } catch (Exception e) {
            throw new ServerException("Network error: " + e);
        }
        server.setSoTimeout(500);
        final AnnotationSessionFactoryBean as = (AnnotationSessionFactoryBean) Spring.getInstance().getFactory().getBean("conf");
        System.out.println("Server QSystem started.\n");
        QLog.l().logger().info("Server System 'qSystem' lanzado. DB name='" + as.getName() + "' url=" + as.getUrl());
        int pos = 0;
        boolean exit = false;
        // слушаем порт
        while (!globalExit && !exit) {
            // a la espera de una nueva conexión, y luego empieza a procesar el cliente

            try {
                final QServer qServer = new QServer(server.accept());
                qServer.start();
                if (QConfig.cfg().isDebug()) {
                    System.out.println();
                }
            } catch (SocketTimeoutException e) {
                // ничего страшного, гасим исключение стобы дать возможность отработать входному/выходному потоку
            } catch (Exception e) {
                throw new ServerException("Network error: " + e);
            }

            if (!QConfig.cfg().isDebug()) {
                final char ch = '*';
                String progres = "Process: " + ch;
                final int len = 5;
                for (int i = 0; i < pos; i++) {
                    progres = progres + ch;
                }
                for (int i = 0; i < len; i++) {
                    progres = progres + ' ';
                }
                if (++pos == len) {
                    pos = 0;
                }
                System.out.print(progres);
                System.out.write(13);// '\b' - возвращает корретку на одну позицию назад

            }

            // Try to assume key pressed 
            // If you press ENTER, it will cause the server 
            // and the file will be overwritten by the temporary state Uses.TEMP_STATE_FILE
            //BufferedReader r = new BufferedReader(new StreamReader(System.in));
            int bytesAvailable = System.in.available();
            if (bytesAvailable > 0) {
                byte[] data = new byte[bytesAvailable];
                System.in.read(data);
                if (bytesAvailable == 5
                        && data[0] == 101
                        && data[1] == 120
                        && data[2] == 105
                        && data[3] == 116
                        && ((data[4] == 10) || (data[4] == 13))) {
                    // typed "exit" command and press ENTER
                    QLog.l().logger().info("Apadado del servidor.");
                    exit = true;
                }
            }
        }// while

        QLog.l().logger().debug("Cerrando el socket del servidor.");
        server.close();
        QLog.l().logger().debug("Deteniendo Jetty.");
        JettyRunner.stop();
        QLog.l().logger().debug("Deteniendo el servidor de informes");
        WebServer.getInstance().stopWebServer();
        QLog.l().logger().debug("Apagando la instancia principal");
        MainBoard.getInstance().close();

        deleteTempFile();
        Thread.sleep(1500);
        QLog.l().logger().info("Servidor apagado limpiamente. Hora: " + Uses.roundAs(((double) (System.currentTimeMillis() - start)) / 1000 / 60, 2) + " мин.");
        System.exit(0);
    }

    private static volatile boolean globalExit = false;

    /**
     * @param socket
     */
    public QServer(Socket socket) {
        this.socket = socket;
        // and launching a new computational flow (see. p-th run ())
        setDaemon(true);
        setPriority(NORM_PRIORITY);
    }

    @Override
    public void run() {
        try {
            QLog.l().logger().debug(" Start thread for receiving task. host=" + socket.getInetAddress().getHostAddress() + " ip=" + Arrays.toString(socket.getInetAddress().getAddress()));

            // from a socket client take the flow of incoming data
            InputStream is;
            try {
                is = socket.getInputStream();
            } catch (IOException e) {
                throw new ServerException("Input Stream broken: " + Arrays.toString(e.getStackTrace()));
            }

            final String data;
            try {
                // wait for anything to crawl from the network, but not more than 10 seconds.
                int i = 0;
                while (is.available() == 0 && i < 100) {
                    Thread.sleep(100);//бля
                    i++;
                }

                StringBuilder sb = new StringBuilder(new String(Uses.readInputStream(is)));
                while (is.available() != 0) {
                    sb = sb.append(new String(Uses.readInputStream(is)));
                    Thread.sleep(150);//бля
                }
                data = URLDecoder.decode(sb.toString(), "utf-8");
            } catch (IOException ex) {
                throw new ServerException("Error al leer el flujo de entrada: " + ex);
            } catch (InterruptedException ex) {
                throw new ServerException("Problem with sleep: " + ex);
            } catch (IllegalArgumentException ex) {
                throw new ServerException("Mensaje de error de red de decodificación: " + ex);
            }
            QLog.l().logger().trace("Task:\n" + (data.length() > 200 ? (data.substring(0, 200) + "...") : data));

            /*
             If caught on the grid exit, it means that stops the batch file launched.
             */
            if ("exit".equalsIgnoreCase(data)) {
                globalExit = true;
                return;
            }

            final String answer;
            final JsonRPC20 rpc;
            final Gson gson = GsonPool.getInstance().borrowGson();
            try {
                rpc = gson.fromJson(data, JsonRPC20.class);
                // received the task of Pool
                final Object result = Executer.getInstance().doTask(rpc, socket.getInetAddress().getHostAddress(), socket.getInetAddress().getAddress());
                answer = gson.toJson(result);
            } catch (JsonSyntaxException ex) {
                QLog.l().logger().error("Received data \"" + data + "\" has not correct JSOM format. ", ex);
                throw new ServerException("Received data \"" + data + "\" has not correct JSOM format. " + Arrays.toString(ex.getStackTrace()));
            } catch (Exception ex) {
                QLog.l().logger().error("Late caught the error when running the command. ", ex);
                throw new ServerException("Late caught the error when running the command: " + Arrays.toString(ex.getStackTrace()));
            } finally {
                GsonPool.getInstance().returnGson(gson);
            }

            // выводим данные:
            QLog.l().logger().trace("Response:\n" + (answer.length() > 200 ? (answer.substring(0, 200) + "...") : answer));
            try {
                // Respuesta de datos
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                writer.print(URLEncoder.encode(answer, "utf-8"));
                writer.flush();
            } catch (IOException e) {
                throw new ServerException("Error writing to stream: " + Arrays.toString(e.getStackTrace()));
            }
        } catch (ServerException | JsonParseException ex) {
            final StringBuilder sb = new StringBuilder("\nStackTrace:\n");
            for (StackTraceElement bag : ex.getStackTrace()) {
                sb.append("    at ").append(bag.getClassName()).append(".").append(bag.getMethodName()).append("(").append(bag.getFileName()).append(":").append(bag.getLineNumber()).append(")\n");
            }
            final String err = sb.toString() + "\n";
            sb.setLength(0);
            throw new ServerException("An error occurred while performing the task.\n" + ex + err);
        } finally {
            // terminate the connection
            try {
                //wrap close, because he can generate an error Exception. Just discard stack trace
                socket.close();
            } catch (IOException e) {
                QLog.l().logger().trace(e);
            }
            QLog.l().logger().trace("Response was finished");
        }
    }

    /**
     * Persistence pool services in a xml-file to disk
     */
    public synchronized static void savePool() {
        final long start = System.currentTimeMillis();
        QLog.l().logger().info("Save the state.");
        final LinkedList<QCustomer> backup = new LinkedList<>();// Create a list of saved kastomer
        final LinkedList<Long> pauses = new LinkedList<>();// Create a list of users who have pause
        QServiceTree.getInstance().getNodes().stream().forEach((service) -> {
            backup.addAll(service.getClients());
        });

        QUserList.getInstance().getItems().forEach((user) -> {
            if (user.getCustomer() != null) {
                backup.add(user.getCustomer());
            }
            if (user.isPause()) {
                pauses.add(user.getId());
            }
        });
        // in tempo file
        final FileOutputStream fos;
        try {
            (new File(Uses.TEMP_FOLDER)).mkdir();
            fos = new FileOutputStream(new File(Uses.TEMP_FOLDER + File.separator + Uses.TEMP_STATE_FILE));
        } catch (FileNotFoundException ex) {
            throw new ServerException("Unable to create a temporary file status. " + ex.getMessage());
        }
        Gson gson = null;
        try {
            gson = GsonPool.getInstance().borrowGson();
            fos.write(gson.toJson(new TempList(backup, QPostponedList.getInstance().getPostponedCustomers(), pauses)).getBytes("UTF-8"));
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            throw new ServerException("Can not save the changes to the stream." + ex.getMessage());
        } finally {
            GsonPool.getInstance().returnGson(gson);
        }
        QLog.l().logger().info("State of preservation. Elapsed time: " + ((double) (System.currentTimeMillis() - start)) / 1000 + " сек.");
    }

    static public class TempList {

        public TempList() {
        }

        public TempList(LinkedList<QCustomer> backup, LinkedList<QCustomer> postponed) {
            this.backup = backup;
            this.postponed = postponed;
        }

        public TempList(LinkedList<QCustomer> backup, LinkedList<QCustomer> postponed, LinkedList<Long> pauses) {
            this.backup = backup;
            this.postponed = postponed;
            this.pauses = pauses;
        }
        @Expose
        @SerializedName("backup")
        public LinkedList<QCustomer> backup;
        @Expose
        @SerializedName("postponed")
        public LinkedList<QCustomer> postponed;
        @Expose
        @SerializedName("method")
        public String method = null;
        @Expose
        @SerializedName("pauses")
        public LinkedList<Long> pauses = null;
        @Expose
        @SerializedName("date")
        public Long date = new Date().getTime();
    }

    /**
     * Loading pool status of temporary services json-file
     */
    static public void loadPool() {
        final long start = System.currentTimeMillis();
        // if there is a temporary file persistence, you have to download it.
        // ignore all errors of reading and parsing .
        QLog.l().logger().info("Trying to restore the system state.");
        File recovFile = new File(Uses.TEMP_FOLDER + File.separator + Uses.TEMP_STATE_FILE);
        if (recovFile.exists()) {
            QLog.l().logger().warn(Locales.locMes("came_back"));
            //Restores the state

            final FileInputStream fis;
            try {
                fis = new FileInputStream(recovFile);
            } catch (FileNotFoundException ex) {
                throw new ServerException(ex);
            }
            final Scanner scan = new Scanner(fis, "utf8");
            String rec_data = "";
            while (scan.hasNextLine()) {
                rec_data += scan.nextLine();
            }
            try {
                fis.close();
            } catch (IOException ex) {
                throw new ServerException(ex);
            }

            final TempList recList;
            final Gson gson = GsonPool.getInstance().borrowGson();
            final RpcGetAdvanceCustomer rpc;
            try {
                recList = gson.fromJson(rec_data, TempList.class);
            } catch (JsonSyntaxException ex) {
                throw new ServerException("Es imposible interpretar los datos almacenados.\n" + ex.toString());
            } finally {
                GsonPool.getInstance().returnGson(gson);
            }

            // Verify whether the cache is expired. Default time 3 hours.
            if (!QConfig.cfg().isRetain() && (recList.date == null || new Date().getTime() - recList.date > 3 * 60 * 60 * 1000)) {
                // Просрочился кеш, не грузим
                QLog.l().logger().warn("The limit period for stored state has expired. If the system does nothing for 3 hours it is considered that the stored data is irrevocably obsolete.");
            } else {
                // Свежий, загружаем в сервер данные кеша

                try {
                    QPostponedList.getInstance().loadPostponedList(recList.postponed);
                    for (QCustomer recCustomer : recList.backup) {
                        // в эту очередь он был
                        final QService service = QServiceTree.getInstance().getById(recCustomer.getService().getId());
                        if (service == null) {
                            QLog.l().logger().warn("An attempt to add the client \"" + recCustomer.getPrefix() + recCustomer.getNumber() + "\" к услуге \"" + recCustomer.getService().getName() + "\" не успешна. Услуга не обнаружена!");
                            continue;
                        }
                        service.setCountPerDay(recCustomer.getService().getCountPerDay());
                        service.setDay(recCustomer.getService().getDay());
                        // так зовут юзера его обрабатываюшего
                        final QUser user = recCustomer.getUser();
                        // кастомер ща стоит к этой услуге к какой стоит
                        recCustomer.setService(service);
                        // смотрим к чему привязан кастомер. либо в очереди стоит, либо у юзера обрабатыватся
                        if (user == null) {
                            // сохраненный кастомер стоял в очереди и ждал, но его еще никто не звал
                            QServiceTree.getInstance().getById(recCustomer.getService().getId()).addCustomerForRecoveryOnly(recCustomer);
                            QLog.l().logger().debug("add Customers \"" + recCustomer.getPrefix() + recCustomer.getNumber() + "\" к услуге \"" + recCustomer.getService().getName() + "\"");
                        } else {
                            // сохраненный кастомер обрабатывался юзером с именем userId
                            if (QUserList.getInstance().getById(user.getId()) == null) {
                                QLog.l().logger().warn("An attempt to add the client \"" + recCustomer.getPrefix() + recCustomer.getNumber() + "\" к юзеру \"" + user.getName() + "\" не успешна. Юзер не обнаружен!");
                                continue;
                            }
                            QUserList.getInstance().getById(user.getId()).setCustomer(recCustomer);
                            recCustomer.setUser(QUserList.getInstance().getById(user.getId()));
                            QLog.l().logger().debug("add Customers \"" + recCustomer.getPrefix() + recCustomer.getNumber() + "\" к юзеру \"" + user.getName() + "\"");
                        }
                    }
                    for (long idUser : recList.pauses) {
                        final QUser user = QUserList.getInstance().getById(idUser);
                        if (user != null) {
                            user.setPause(Boolean.TRUE);
                        }
                    }
                } catch (ServerException ex) {
                    System.err.println("Restoring Server after configuration change. " + ex);
                    clearAllQueue();
                    QLog.l().logger().error("Restoring Server after the configuration change. To stop the server, use the exit command. ", ex);
                }
            }
        }
        QLog.l().logger().info("System state recovery is completed. Elapsed time: " + ((double) (System.currentTimeMillis() - start)) / 1000 + " сек.");
    }

    static public void clearAllQueue() {
        // clean all the services of the dead customer
        QServiceTree.getInstance().getNodes().forEach((service) -> {
            service.clearNextNumber();
            service.freeCustomers();
        });
        QService.clearNextStNumber();

        QPostponedList.getInstance().clear();
        MainBoard.getInstance().clear();

        // Сотрем временные файлы
        deleteTempFile();
        QLog.l().logger().info("Cleaning all users linked by customer.");
        QUserList.getInstance().getItems().forEach((user) -> {
            user.setCustomer(null);
            user.setShadow(null);
            user.getPlanServices().forEach((plan) -> {
                plan.setAvg_wait(0);
                plan.setAvg_work(0);
                plan.setKilled(0);
                plan.setWorked(0);
            });
        });
    }

    public static void deleteTempFile() {
        QLog.l().logger().debug("Remove " + Uses.TEMP_FOLDER + File.separator + Uses.TEMP_STATE_FILE);
        File file = new File(Uses.TEMP_FOLDER + File.separator + Uses.TEMP_STATE_FILE);
        if (file.exists()) {
            file.delete();
        }
        QLog.l().logger().debug("Remove " + Uses.TEMP_FOLDER + File.separator + Uses.TEMP_STATATISTIC_FILE);
        file = new File(Uses.TEMP_FOLDER + File.separator + Uses.TEMP_STATATISTIC_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}
