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
package ru.apertum.qsystem.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import ru.apertum.qsystem.common.QLog;
import ru.apertum.qsystem.common.Uses;

/**
 *
 * @author Evgeniy Egorov
 */
public final class Locales {

    private static final ResourceBundle translate = ResourceBundle.getBundle("ru/apertum/qsystem/common/resources/i3-label", Locales.getInstance().getLangCurrent());

    public static String locMes(String key) {
        return translate.getString(key);
    }

    private Locales() {
        config = new PropertiesConfiguration();
        config.setEncoding("utf8");
        File f = new File(configFileName);
        if (f.exists()) {
            config.setFileName(configFileName);
        } else {
            configFileName = "../" + configFileName;
            f = new File(configFileName);
            if (f.exists()) {
                config.setFileName(configFileName);
                config.setEncoding("utf8");
            } else {
                final Exception ex = new FileNotFoundException(configFileName);
                QLog.l().logger().error(ex);
                throw new RuntimeException(ex);
            }
        }

        try {
            config.load();
        } catch (ConfigurationException ex) {
            QLog.l().logger().error(ex);
            throw new RuntimeException(ex);
        }
        config.setAutoSave(true);

        for (Iterator<String> itr = config.getKeys(); itr.hasNext();) {
            String s = itr.next();
            if (s.startsWith("locale")) {
                s = s.substring(s.indexOf(".") + 1);
                if (s.contains(".")) {
                    s = s.substring(0, s.indexOf("."));
                    if (locales.get(s) == null) {
                        final Locale locale = new Locale(config.getString("locale." + s + ".lng"), config.getString("locale." + s + ".country"));
                        locales.put(s, locale);
                        locales_name.put(locale, s);
                        lngs.put(config.getString("locale." + s + ".name"), s);
                        lngs_names.put(s, config.getString("locale." + s + ".name"));
                        lngs_buttontext.put(s, config.getString("locale." + s + ".buttontext"));
                        lngs_welcome.put(s, config.getString("locale." + s + ".welcome", "1"));
                    }
                }
            }
        }
        //System.out.println("- 0 --" + getLangCurrent());
        //System.out.println("- 01 --" + getLangCurrent().getISO3Language());
        isUkr = getLangCurrent().getISO3Language().toLowerCase().startsWith("ukr");
        //System.out.println("- 1 --" + Locale.getDefault());
        //System.out.println("- 2 --" + locales_name.get(Locale.getDefault()));

        //isRuss = getNameOfPresentLocale().toLowerCase().startsWith("ru") && !isUkr;
        isRuss = getLangCurrent().getISO3Language().startsWith("ru") && !isUkr;

        russSymbolDateFormat = new DateFormatSymbols(getLocaleByName("RU"));
        russSymbolDateFormat.setMonths(Uses.RUSSIAN_MONAT);

        ukrSymbolDateFormat = new DateFormatSymbols(getLocaleByName("UA"));
        ukrSymbolDateFormat.setMonths(Uses.UKRAINIAN_MONAT);
    }
    private String configFileName = "config/langs.properties";
    private final PropertiesConfiguration config;
    public final boolean isRuss;
    public final boolean isUkr;
    private final DateFormatSymbols russSymbolDateFormat;
    private final DateFormatSymbols ukrSymbolDateFormat;

    public DateFormatSymbols getRussSymbolDateFormat() {
        return russSymbolDateFormat;
    }

    public DateFormatSymbols getUkrSymbolDateFormat() {
        return ukrSymbolDateFormat;
    }
    /**
     * eng -> Locale(eng)
     */
    private final LinkedHashMap<String, Locale> locales = new LinkedHashMap<>();
    /**
     * Locale(eng)-> eng
     */
    private final LinkedHashMap<Locale, String> locales_name = new LinkedHashMap<>();
    /**
     * English -> eng
     */
    private final LinkedHashMap<String, String> lngs = new LinkedHashMap<>();
    /**
     * eng -> English
     */
    private final LinkedHashMap<String, String> lngs_names = new LinkedHashMap<>();
    /**
     * eng -> buttontext
     */
    private final LinkedHashMap<String, String> lngs_buttontext = new LinkedHashMap<>();
    /**
     * eng -> 1/0
     */
    private final LinkedHashMap<String, String> lngs_welcome = new LinkedHashMap<>();

    public static Locales getInstance() {
        return LocalesHolder.INSTANCE;
    }

    private static class LocalesHolder {

        private static final Locales INSTANCE = new Locales();
    }
    private final String WELCOME = "welcome";
    private final String LANG_CURRENT = "locale.current";
    private final String WELCOME_LNG = "welcome.multylangs";
    private final String WELCOME_LNG_POS = "welcome.multylangs.position";
    private final String WELCOME_LNG_BTN_FILL = "welcome.multylangs.areafilled";
    private final String WELCOME_LNG_BTN_BORDER = "welcome.multylangs.border";

    public boolean isWelcomeMultylangs() {
        return config.getString(WELCOME_LNG) == null ? false : "1".equals(config.getString(WELCOME_LNG)) || config.getString(WELCOME_LNG).startsWith("$");
    }

    public void setWelcomeMultylangs(boolean multylangs) {
        if (!config.getString(WELCOME_LNG).startsWith("$")) {
            config.setProperty(WELCOME_LNG, multylangs ? "1" : "0");
        }
    }

    public boolean isIDE() {
        return config.getString(WELCOME_LNG).startsWith("$");
    }

    public boolean isWelcomeFirstLaunch() {
        return config.getString(WELCOME) == null ? false : ("1".equals(config.getString(WELCOME)) && !config.getString(WELCOME_LNG).startsWith("$"));
    }

    public boolean isWelcomeMultylangsButtonsFilled() {
        return config.getString(WELCOME_LNG_BTN_FILL) == null ? true : "1".equals(config.getString(WELCOME_LNG_BTN_FILL));
    }

    public boolean isWelcomeMultylangsButtonsBorder() {
        return config.getString(WELCOME_LNG_BTN_BORDER) == null ? true : "1".equals(config.getString(WELCOME_LNG_BTN_BORDER));
    }

    public int getMultylangsPosition() {
        return config.getString(WELCOME_LNG_POS) == null ? 1 : Integer.parseInt(config.getString(WELCOME_LNG_POS));
    }

    public Locale getLangCurrent() {
        return locales.get(config.getString(LANG_CURRENT)) == null ? Locale.getDefault() : locales.get(config.getString(LANG_CURRENT));
    }

    public Locale getLocaleByName(String name) {
        return locales.get(name) == null ? Locale.getDefault() : locales.get(name);
    }

    public String getLangCurrName() {
        return "".equals(config.getString(LANG_CURRENT)) ? lngs_names.get("eng") : lngs_names.get(config.getString(LANG_CURRENT));
    }

    public String getLangButtonText(String lng) {
        return lngs_buttontext.get(lng);
    }

    public String getLangWelcome(String lng) {
        return lngs_welcome.get(lng);
    }

    public String getNameOfPresentLocale() {
        return locales_name.get(Locale.getDefault());
    }

    /**
     *
     * @param name English к примеру eng
     */
    public void setLangCurrent(String name) {
        config.setProperty(LANG_CURRENT, lngs.get(name));
    }

    public void setWelcome(String count) {
        config.setProperty(WELCOME, count);
    }

    public void setLangWelcome(String name, boolean on) {
        config.setProperty("locale." + name + ".welcome", on ? "1" : "0");
        lngs_welcome.put(name, on ? "1" : "0");
    }

    public ArrayList<String> getAvailableLocales() {
        final ArrayList<String> res = new ArrayList<>(lngs.keySet());
        return res;
    }

    public ArrayList<String> getAvailableLangs() {
        final ArrayList<String> res = new ArrayList<>(lngs_names.keySet());
        return res;
    }
}
