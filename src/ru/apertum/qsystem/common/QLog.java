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
package ru.apertum.qsystem.common;

import java.util.Enumeration;
import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import ru.apertum.qsystem.About;
import ru.apertum.qsystem.server.ServerProps;

/**
 * Actually, logger log4j uses this singleton. Here, instead of getInstance , l() is used for shorter writing
 *
 * @author Evgeniy Egorov
 */
public class QLog {

    private Logger logger = Logger.getLogger("server.file");//**.file.info.trace

    public Logger logger() {
        return logger;
    }
    /**
     * We use this constant to work with the log for records
     */
    private Logger logRep = Logger.getLogger("reports.file");
    private Logger logQUser = QConfig.cfg().isServer() ? Logger.getLogger("quser.file") : Logger.getLogger("reports.file.info.trace");

    public Logger logRep() {
        return logRep;
    }

    public Logger logQUser() {
        return logQUser;
    }

    private QLog() {
        //run in the parameters, look, it is necessary to carry out the key responsible for logging
        if (QConfig.cfg().isDebug()) {
            switch (loggerType) {
                case 0://server
                    logger = Logger.getLogger("server.file.info.trace");
                    break;
                case 1://клиент
                    logger = Logger.getLogger("client.file.info.trace");
                    break;
                case 2://приемная
                    logger = Logger.getLogger("reception.file.info.trace");
                    break;
                case 3://админка
                    logger = Logger.getLogger("admin.file.info.trace");
                    break;
                case 4://админка
                    logger = Logger.getLogger("welcome.file.info.trace");
                    break;
                case 5://хардварные кнопки
                    logger = Logger.getLogger("user_buttons.file.info.trace");
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            // ключ, отвечающий за логирование
            if (QConfig.cfg().isLogInfo()) {
                switch (loggerType) {
                    case 0://сервер
                        logger = Logger.getLogger("server.file.info");
                        break;
                    case 1://клиент
                        logger = Logger.getLogger("client.file.info");
                        break;
                    case 2://приемная
                        logger = Logger.getLogger("reception.file.info");
                        break;
                    case 3://админка
                        logger = Logger.getLogger("admin.file.info");
                        break;
                    case 4://админка
                        logger = Logger.getLogger("welcome.file.info");
                        break;
                    case 5://хардварные кнопки
                        logger = Logger.getLogger("user_buttons.file.info");
                        break;
                    default:
                        throw new AssertionError();
                }
            } else {
                switch (loggerType) {
                    case 0://сервер
                        logger = Logger.getLogger("server.file");
                        break;
                    case 1://клиент
                        logger = Logger.getLogger("client.file");
                        break;
                    case 2://приемная
                        logger = Logger.getLogger("reception.file");
                        break;
                    case 3://админка
                        logger = Logger.getLogger("admin.file");
                        break;
                    case 4://админка
                        logger = Logger.getLogger("welcome.file");
                        break;
                    case 5://хардварные кнопки
                        logger = Logger.getLogger("user_buttons.file");
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
        if (!QConfig.cfg().isIDE() && SystemUtils.IS_OS_WINDOWS) { // Операционка и бинс
            final Enumeration<Logger> lgs = logger.getLoggerRepository().getCurrentLoggers();
            while (lgs.hasMoreElements()) {
                final Logger lg = lgs.nextElement();
                final Enumeration<Appender> aps = lg.getAllAppenders();
                while (aps.hasMoreElements()) {
                    final Appender ap = aps.nextElement();
                    if (ap instanceof ConsoleAppender) {
                        ((ConsoleAppender) ap).setEncoding("cp866");
                        ((ConsoleAppender) ap).activateOptions();
                    }
                }
            }
        }

        // a key responsible for at launch the pause.
        if (QConfig.cfg().getDelay() > 0) {
            try {
                Thread.sleep(QConfig.cfg().getDelay() * 1000);
            } catch (InterruptedException ex) {
            }
        }

        if ("server.file.info.trace".equalsIgnoreCase(logger.getName())) {
            logRep = Logger.getLogger("reports.file.info.trace");
            logQUser = Logger.getLogger("quser.file.info.trace");
        } else {
            // ключ, отвечающий за логирование
            if ("server.file.info".equalsIgnoreCase(logger.getName())) {
                logRep = Logger.getLogger("reports.file.info");
                logQUser = Logger.getLogger("quser.file.info");
            }
        }
    }

    public static QLog l() {
        return LogerHolder.INSTANCE;
    }
    public static int loggerType = 0; 

    /**
     *
     * @param args
     * @param type 0-server,1-client,2-приемная,3-админка,4-kiosk,5-сервер хардварных кнопок
     * @return
     */
    public static QLog initial(String[] args, int type) {
        loggerType = type;
        QConfig.cfg(type).prepareCLI(args);
        final QLog log = LogerHolder.INSTANCE;
        About.load();
        QLog.l().logger.info("\"QSystem " + About.ver + "\"!  date: " + About.date);
        QLog.l().logger.info("START LOGER. Logger: " + QLog.l().logger().getName());
        if (QConfig.cfg().isServer()) {
            QLog.l().logger.info("Version DB=" + ServerProps.getInstance().getProps().getVersion());
            QLog.l().logRep.info("START LOGGER for reports. Logger: " + QLog.l().logRep().getName());
        }
        QLog.l().logger.info("Mode: " + (QConfig.cfg().isDebug() ? "KEY_DEBUG" : (QConfig.cfg().isDemo() ? "KEY_DEMO" : "FULL")));
        QLog.l().logger.info("Plugins: " + (QConfig.cfg().isNoPlugins() ? "NO" : "YES"));
        if (QConfig.cfg().isUbtnStart()) {
            QLog.l().logger.info("Auto start: YES");
        }

        return log;
    }

    private static class LogerHolder {

        private static final QLog INSTANCE = new QLog();
    }
}
