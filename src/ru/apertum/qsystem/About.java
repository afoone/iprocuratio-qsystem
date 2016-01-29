/*
 * Copyright (C) 2014 Evgeniy Egorov
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
package ru.apertum.qsystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.Properties;
import ru.apertum.qsystem.client.forms.FAbout;
import ru.apertum.qsystem.common.exceptions.ServerException;

/**
 *
 * @author Evgeniy Egorov
 */
public class About {

    public static String ver = "";
    public static String date = "";
    public static String db = "";

    public static void load() {
        final Properties settings = new Properties();
        final InputStream inStream = settings.getClass().getResourceAsStream("/ru/apertum/qsystem/common/version.properties");

        try {
            settings.load(inStream);
        } catch (IOException ex) {
            throw new ServerException("Cant read version. " + ex);
        }
        ver = settings.getProperty(FAbout.VERSION);
        date = settings.getProperty(FAbout.DATE);
        db = settings.getProperty(FAbout.VERSION_DB);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        load();
        System.out.println();
        System.out.println();

        GregorianCalendar gc = new GregorianCalendar();
        int i = (int) (Math.random() * 55) + 4;

        if (gc.get(GregorianCalendar.HOUR_OF_DAY) == 0 || (gc.get(GregorianCalendar.MONTH) == 2 && gc.get(GregorianCalendar.DAY_OF_MONTH) == 23)) {
            i = 6 * (gc.get(GregorianCalendar.SECOND) % 2);
        }
        if (gc.get(GregorianCalendar.HOUR_OF_DAY) == 1 || (gc.get(GregorianCalendar.MONTH) == 4 && gc.get(GregorianCalendar.DAY_OF_MONTH) == 22)) {
            i = (gc.get(GregorianCalendar.SECOND) % 2) + 1;
        }
        if ((gc.get(GregorianCalendar.MONTH) == 10 && gc.get(GregorianCalendar.DAY_OF_MONTH) == 31)) {
            i = 3;
        }
        switch (i) {
            case 0:
                System.out.println();
                System.out.println("0000000000000000000000000000000000000000000000000000000000");
                System.out.println("000000000000000000000__00000000000000000000000000000000000");
                System.out.println("0000000000000000000000___000000000000000000000000000000000");
                System.out.println("0000000000000000000000000_____0000000000000000000000000000");
                System.out.println("00000000000000000000000000000_____000000000000000000000000");
                System.out.println("00000000000000000000000000000000_____000000000000000000000");
                System.out.println("0000000000000000000000000000000000_____0000000000000000000");
                System.out.println("00000000000000__________000000000000_____00000000000000000");
                System.out.println("000000000000___________000000000000000_____000000000000000");
                System.out.println("0000000000___________0000000000000000000____00000000000000");
                System.out.println("000000000__________0000000000000000000000_____000000000000");
                System.out.println("0000000___________000000000000000000000000_____00000000000");
                System.out.println("00000___________00__00000000000000000000000_____0000000000");
                System.out.println("0000___________0_____00000000000000000000000_____000000000");
                System.out.println("000000_______0000______0000000000000000000000____000000000");
                System.out.println("0000000____00000000______00000000000000000000_____00000000");
                System.out.println("000000000000000000000______000000000000000000_____00000000");
                System.out.println("0000000000000000000000______00000000000000000_____00000000");
                System.out.println("000000000000000000000000______000000000000000_____00000000");
                System.out.println("00000000000000000000000000______0000000000000_____00000000");
                System.out.println("000000000000000000000000000_______00000000000_____00000000");
                System.out.println("00000000000000000000000000000______000000000_____000000000");
                System.out.println("0000000000000000000000000000000______000000______000000000");
                System.out.println("000000000000000000__0000000000000______000______0000000000");
                System.out.println("0000000000000000______000000000000_____________00000000000");
                System.out.println("000000000000000_________000000000000__00______000000000000");
                System.out.println("0000000000000_____0_________00000000________00000000000000");
                System.out.println("00000000000____000000_____________________00_0000000000000");
                System.out.println("00000000______0000000000________________0_______0000000000");
                System.out.println("0000000______00000000000000000____00000000______0000000000");
                System.out.println("0000000_____00000000000000000000000000000000___00000000000");
                System.out.println("0000000000000000000000000000000000000000000000000000000000");
                System.out.println("0000000000000000000000000000000000000000000000000000000000");
                System.out.println("0000000000000000000000000000000000000000000000000000000000");
                System.out.println("0000000000000000000000000000000000000000000000000000000000");

                break;

            case 1:
                System.out.println();
                System.out.println("                                                                 ");
                System.out.println("                                                                 ");
                System.out.println("                                                                 ");
                System.out.println("                                                                 ");
                System.out.println("                                                                 ");
                System.out.println("                     00                                          ");
                System.out.println("                   000                                           ");
                System.out.println("                  000                                            ");
                System.out.println("                 0000                                            ");
                System.out.println("                00000000                                         ");
                System.out.println("               000000000                                         ");
                System.out.println("              0000000 000                                        ");
                System.out.println("              000000  0                                          ");
                System.out.println("             000000  00                                          ");
                System.out.println("            000000      00                                       ");
                System.out.println("            000000  0  000                                       ");
                System.out.println("           0000000 0  000                                        ");
                System.out.println("           000000000 0000                                        ");
                System.out.println("           0000000000000             00000000       00           ");
                System.out.println("          0000000000000             00000000000   0000           ");
                System.out.println("          00000000                 00     000     0              ");
                System.out.println("          0000000                  00   000000    00             ");
                System.out.println("          000000 00                0000000 0       0             ");
                System.out.println("         000000000                  0    00                      ");
                System.out.println("         0000000000                     000                      ");
                System.out.println("         00000000000   0                 0                       ");
                System.out.println("         000000000000000                                         ");
                System.out.println("         000000000000000                                         ");
                System.out.println("         000000 00   000                        00000            ");
                System.out.println("         000000   00  00                       0000000           ");
                System.out.println("          00000    0000000                     0000000           ");
                System.out.println("          00000    000 000                      0000000          ");
                System.out.println("           00000    000 00                     000000000         ");
                System.out.println("           00000     00  0                    0000000000         ");
                System.out.println("            00000 0   00                   000000000000          ");
                System.out.println("            000000                        0000000000000          ");
                System.out.println("             000 0                       0000000     0           ");
                System.out.println("              000   0    0                000    00000           ");
                System.out.println("              000   000000                0      00000           ");
                System.out.println("               000      000                       0000           ");
                System.out.println("               00        00              0          000          ");
                System.out.println("                0         0000                       000         ");
                System.out.println("                0          0000                 0000 0000        ");
                System.out.println("                00           0000000            00 000000        ");
                System.out.println("                 0            000000000    00000000000000        ");
                System.out.println("                 0              0000000000000000000000000        ");
                System.out.println("                 00                 000000000000000000000        ");
                System.out.println("                 00                  0000000000000000000         ");
                System.out.println("                  00                000000000000                 ");
                System.out.println("                   0                 000 0000000                 ");
                System.out.println("                   00                    000000                  ");
                System.out.println("                    00                   00  00                  ");
                System.out.println("                      0                 00  000                  ");
                System.out.println("                                            0                    ");
                System.out.println("                                          00                     ");
                System.out.println("                                                                 ");
                System.out.println("                                                                 ");
                System.out.println("                                                                 ");
                System.out.println("                                                                 ");
                System.out.println("                                                                 ");
                System.out.println("                                                                 ");
                System.out.println("                                                                 ");

                break;

            case 2:
                System.out.println();
                System.out.println("             `$$+  l$+  ;$$ d$$   ,$'    ,");
                System.out.println("              `$$b,;$$b,$$$$$$; ,d$$   ,$");
                System.out.println("               `$$$b$$$$$$$$$$$$$$$; ,d$'");
                System.out.println("                `$$$*\"\"\"\"~~\"\"^+$$$$$$$$P    ,,");
                System.out.println("                *^              ~^\"$$$$; ,s$'");
                System.out.println("              ,^                    `$$$$$$'     ");
                System.out.println("             ,'                       `$$$'  ,y$\"");
                System.out.println("             '                         `$$,y$$\"");
                System.out.println("            (                           `$$$$'");
                System.out.println("          ,^       ,,,         ,,,yyyyy,,`$$$$$#=-");
                System.out.println("          `,  ,,/'^  ``      `$$$$$$$$$$d;$$$~");
                System.out.println("           /' ,$$*=``-     `$b`?$$$$$$$$$$;$$$b,");
                System.out.println("         ,'   `^*;-=''    `$$$$$$$#$$$$$$$;$$$+$b.");
                System.out.println("        (,,,;,             `+$+\",+y+`?$$$$;$b,");
                System.out.println("         `;``\"                 ]$& $';$$$$d$$$b,");
                System.out.println("        ,yyy,,                •@@&`'d$$$$d$P\"\"+$,");
                System.out.println("        ?`^\"$@by,            ~+>^,$$$$$d$$b,");
                System.out.println("        ,&~`^\"+?~#`        ,yb, ,?$$$$$d$$$$b,");
                System.out.println("       '   $    ,      ,,yd$$$$$=+=$$$d$$~~\"+;");
                System.out.println("      /   '$, $$$yyyyy$$$$$\" ,$$$•$$$d$$$b,");
                System.out.println("     `-=##$$$$$$$$$$$$$$^~  ,$$$$$$$R$'~  ~`");
                System.out.println("           ~~\"$$$$$$$$$'   ,$$$$$$$D$'");
                System.out.println("               `#$$$$$'   ,$$$`,$$Z$$");
                System.out.println("                 `#$$'   ,$@P',$$o$$'");

                break;

            case 3:
                System.out.println();
                System.out.println("                          ooo");
                System.out.println("                         $ o$");
                System.out.println("                        o $$");
                System.out.println("              \"\"$$$    o\" $$ oo \"");
                System.out.println("          \" o$\"$oo$$$\"o$$o$$\"$$$$$ o");
                System.out.println("         $\" \"o$$$$$$o$$$$$$$$$$$$$$o     o");
                System.out.println("      o$\"    \"$$$$$$$$$$$$$$$$$$$$$$o\" \"oo  o");
                System.out.println("     \" \"     o  \"$$$o   o$$$$$$$$$$$oo$$");
                System.out.println("    \" $     \" \"o$$$$$ $$$$$$$$$$$\"$$$$$$$o");
                System.out.println("  o  $       o o$$$$$\"$$$$$$$$$$$o$$\"\"\"$$$$o \" \"");
                System.out.println(" o          o$$$$$\"    \"$$$$$$$$$$ \"\" oo $$   o $");
                System.out.println(" $  $       $$$$$  $$$oo \"$$$$$$$$o o $$$o$$oo o o");
                System.out.println("o        o $$$$$oo$$$$$$o$$$$ \"\"$$oo$$$$$$$$\"  \" \"o");
                System.out.println("\"   o    $ \"\"$$$$$$$$$$$$$$  o  \"$$$$$$$$$$$$   o \"");
                System.out.println("\"   $      \"$$$$$$$$$$$$$$   \"   $$$\"$$$$$$$$o  o");
                System.out.println("$   o      o$\"\"\"\"\"$$$$$$$$    oooo$$ $$$$$$$$\"  \"");
                System.out.println("$      o\"\"o $$o    $$$$$$$$$$$$$$$$$ \"\"  o$$$   $ o");
                System.out.println(" o     \" \"o \"$$$$  $$$$$\"\"\"\"\"\"\"\"\"\"\" $  o$$$$$\"\" o o");
                System.out.println(" \"  \" o  o$o\" $$$$o   \"\"           o  o$$$$$\"   o");
                System.out.println("  $         o$$$$$$$oo            \"oo$$$$$$$\"    o");
                System.out.println("  \"$   o o$o $o o$$$$$\"$$$$oooo$$$$$$$$$$$$$$\"o$o");
                System.out.println("    \"o oo  $o$\"oo$$$$$o$$$$$$$$$$$$\"$$$$$$$$\"o$\"");
                System.out.println("     \"$ooo $$o$   $$$$$$$$$$$$$$$$ $$$$$$$$o\"");
                System.out.println("        \"\" $$$$$$$$$$$$$$$$$$$$$$\" \"\"\"\"");
                System.out.println("                         \"\"\"\"\"\"");

                break;

            case 4:
                System.out.println();
                System.out.println("                         8888888888888");
                System.out.println("                     888888888888888888888888");
                System.out.println("                 8888:::8888888888888888888888888");
                System.out.println("               8888::::::8888888888888888888888888888");
                System.out.println("             88::::::::888:::8888888888888888888888888");
                System.out.println("             88888888::::8:::::::::::88888888888888888888");
                System.out.println("            888 8::888888::::::::::::::::::88888888888   888");
                System.out.println("             88::::88888888::::m::::::::::88888888888    8");
                System.out.println("           888888888888888888:M:::::::::::8888888888888");
                System.out.println("          88888888888888888888::::::::::::M88888888888888");
                System.out.println("          8888888888888888888888:::::::::M8888888888888888");
                System.out.println("           8888888888888888888888:::::::M888888888888888888");
                System.out.println("          8888888888888888::88888::::::M88888888888888888888");
                System.out.println("        88888888888888888:::88888:::::M888888888888888   8888");
                System.out.println("       88888888888888888:::88888::::M::;o*M*o;888888888    88");
                System.out.println("      88888888888888888:::8888:::::M:::::::::::88888888    8");
                System.out.println("     88888888888888888::::88::::::M:;:::::::::::888888888");
                System.out.println("    8888888888888888888:::8::::::M::aAa::::::::M8888888888       8");
                System.out.println("    88   8888888888::88::::8::::M:::::::::::::888888888888888 8888");
                System.out.println("   88  88888888888:::8:::::::::M::::::::::;::88:88888888888888888");
                System.out.println("   8  8888888888888:::::::::::M::\"@@@@@@@\"::::8w8888888888888888");
                System.out.println("    88888888888:888::::::::::M:::::\"@a@\":::::M8i888888888888888");
                System.out.println("   8888888888::::88:::::::::M88:::::::::::::M88z88888888888888888");
                System.out.println("  8888888888:::::8:::::::::M88888:::::::::MM888!888888888888888888");
                System.out.println("  888888888:::::8:::::::::M8888888MAmmmAMVMM888*88888888   88888888");
                System.out.println("  888888 M:::::::::::::::M888888888:::::::MM88888888888888   8888888");
                System.out.println("  8888   M::::::::::::::M88888888888::::::MM888888888888888    88888");
                System.out.println("   888   M:::::::::::::M8888888888888M:::::mM888888888888888    8888");
                System.out.println("    888  M::::::::::::M8888:888888888888::::m::Mm88888 888888   8888");
                System.out.println("     88  M::::::::::::8888:88888888888888888::::::Mm8   88888   888");
                System.out.println("     88  M::::::::::8888M::88888::888888888888:::::::Mm88888    88");
                System.out.println("     8   MM::::::::8888M:::8888:::::888888888888::::::::Mm8     4");
                System.out.println("         8M:::::::8888M:::::888:::::::88:::8888888::::::::Mm    2");
                System.out.println("        88MM:::::8888M:::::::88::::::::8:::::888888:::M:::::M");
                System.out.println("       8888M:::::888MM::::::::8:::::::::::M::::8888::::M::::M");
                System.out.println("      88888M:::::88:M::::::::::8:::::::::::M:::8888::::::M::M");
                System.out.println("     88 888MM:::888:M:::::::::::::::::::::::M:8888:::::::::M:");
                System.out.println("     8 88888M:::88::M:::::::::::::::::::::::MM:88::::::::::::M");
                System.out.println("       88888M:::88::M::::::::::*88*::::::::::M:88::::::::::::::M");
                System.out.println("      888888M:::88::M:::::::::88@@88:::::::::M::88::::::::::::::M");
                System.out.println("      888888MM::88::MM::::::::88@@88:::::::::M:::8::::::::::::::*8");
                System.out.println("      88888  M:::8::MM:::::::::*88*::::::::::M:::::::::::::::::88@@");
                System.out.println("      8888   MM::::::MM:::::::::::::::::::::MM:::::::::::::::::88@@");
                System.out.println("       888    M:::::::MM:::::::::::::::::::MM::M::::::::::::::::*8");
                System.out.println("       888    MM:::::::MMM::::::::::::::::MM:::MM:::::::::::::::M");
                System.out.println("        88     M::::::::MMMM:::::::::::MMMM:::::MM::::::::::::MM");
                System.out.println("         88    MM:::::::::MMMMMMMMMMMMMMM::::::::MMM::::::::MMM");
                System.out.println("          88    MM::::::::::::MMMMMMM::::::::::::::MMMMMMMMMM");
                System.out.println("           88   8MM::::::::::::::::::::::::::::::::::MMMMMM");
                System.out.println("            8   88MM::::::::::::::::::::::M:::M::::::::MM");
                System.out.println("                888MM::::::::::::::::::MM::::::MM::::::MM");
                System.out.println("               88888MM:::::::::::::::MMM:::::::mM:::::MM");
                System.out.println("               888888MM:::::::::::::MMM:::::::::MMM:::M");
                System.out.println("              88888888MM:::::::::::MMM:::::::::::MM:::M");
                System.out.println("             88 8888888M:::::::::MMM::::::::::::::M:::M");
                System.out.println("             8  888888 M:::::::MM:::::::::::::::::M:::M:");
                System.out.println("                888888 M::::::M:::::::::::::::::::M:::MM");
                System.out.println("               888888  M:::::M::::::::::::::::::::::::M:M");
                System.out.println("               888888  M:::::M:::::::::@::::::::::::::M::M");
                System.out.println("               88888   M::::::::::::::@@:::::::::::::::M::M");
                System.out.println("              88888   M::::::::::::::@@@::::::::::::::::M::M");
                System.out.println("             88888   M:::::::::::::::@@::::::::::::::::::M::M");
                System.out.println("            88888   M:::::m::::::::::@::::::::::Mm:::::::M:::M");
                System.out.println("            8888   M:::::M:::::::::::::::::::::::MM:::::::M:::M");
                System.out.println("           8888   M:::::M:::::::::::::::::::::::MMM::::::::M:::M");
                System.out.println("          888    M:::::Mm::::::::::::::::::::::MMM:::::::::M::::M");
                System.out.println("        8888    MM::::Mm:::::::::::::::::::::MMMM:::::::::m::m:::M");
                System.out.println("       888      M:::::M::::::::::::::::::::MMM::::::::::::M::mm:::M");
                System.out.println("    8888       MM:::::::::::::::::::::::::MM:::::::::::::mM::MM:::M:");
                System.out.println("               M:::::::::::::::::::::::::M:::::::::::::::mM::MM:::Mm");
                System.out.println("              MM::::::m:::::::::::::::::::::::::::::::::::M::MM:::MM");
                System.out.println("              M::::::::M:::::::::::::::::::::::::::::::::::M::M:::MM");
                System.out.println("             MM:::::::::M:::::::::::::M:::::::::::::::::::::M:M:::MM");
                System.out.println("             M:::::::::::M88:::::::::M:::::::::::::::::::::::MM::MMM");
                System.out.println("             M::::::::::::8888888888M::::::::::::::::::::::::MM::MM");
                System.out.println("             M:::::::::::::88888888M:::::::::::::::::::::::::M::MM");
                System.out.println("             M::::::::::::::888888M:::::::::::::::::::::::::M::MM");
                System.out.println("             M:::::::::::::::88888M:::::::::::::::::::::::::M:MM");
                System.out.println("             M:::::::::::::::::88M::::::::::::::::::::::::::MMM");
                System.out.println("             M:::::::::::::::::::M::::::::::::::::::::::::::MMM");
                System.out.println("             MM:::::::::::::::::M::::::::::::::::::::::::::MMM");
                System.out.println("              M:::::::::::::::::M::::::::::::::::::::::::::MMM");
                System.out.println("              MM:::::::::::::::M::::::::::::::::::::::::::MMM");
                System.out.println("               M:::::::::::::::M:::::::::::::::::::::::::MMM");
                System.out.println("               MM:::::::::::::M:::::::::::::::::::::::::MMM");
                System.out.println("                M:::::::::::::M::::::::::::::::::::::::MMM");
                System.out.println("                MM:::::::::::M::::::::::::::::::::::::MMM");
                System.out.println("                 M:::::::::::M:::::::::::::::::::::::MMM");
                System.out.println("                 MM:::::::::M:::::::::::::::::::::::MMM");
                System.out.println("                  M:::::::::M::::::::::::::::::::::MMM");
                System.out.println("                  MM:::::::M::::::::::::::::::::::MMM");
                System.out.println("                   MM::::::M:::::::::::::::::::::MMM");
                System.out.println("                   MM:::::M:::::::::::::::::::::MMM");
                System.out.println("                    MM::::M::::::::::::::::::::MMM");
                System.out.println("                    MM:::M::::::::::::::::::::MMM");
                System.out.println("                     MM::M:::::::::::::::::::MMM");
                System.out.println("                     MM:M:::::::::::::::::::MMM");
                System.out.println("                      MMM::::::::::::::::::MMM");
                System.out.println("                      MM::::::::::::::::::MMM");
                System.out.println("                       M:::::::::::::::::MMM");
                System.out.println("                      MM::::::::::::::::MMM");
                System.out.println("                      MM:::::::::::::::MMM");
                System.out.println("                      MM::::M:::::::::MMM:");
                System.out.println("                      mMM::::MM:::::::MMMM");
                System.out.println("                       MMM:::::::::::MMM:M");
                System.out.println("                       mMM:::M:::::::M:M:M");
                System.out.println("                        MM::MMMM:::::::M:M");
                System.out.println("                        MM::MMM::::::::M:M");
                System.out.println("                        mMM::MM::::::::M:M");
                System.out.println("                         MM::MM:::::::::M:M");
                System.out.println("                         MM::MM::::::::::M:m");
                System.out.println("                         MM:::M:::::::::::MM");
                System.out.println("                         MMM:::::::::::::::M:");
                System.out.println("                         MMM:::::::::::::::M:");
                System.out.println("                         MMM::::::::::::::::M");
                System.out.println("                         MMM::::::::::::::::M");
                System.out.println("                         MMM::::::::::::::::Mm");
                System.out.println("                          MM::::::::::::::::MM");
                System.out.println("                          MMM:::::::::::::::MM");
                System.out.println("                          MMM:::::::::::::::MM");
                System.out.println("                          MMM:::::::::::::::MM");
                System.out.println("                          MMM:::::::::::::::MM");
                System.out.println("                           MM::::::::::::::MMM");
                System.out.println("                           MMM:::::::::::::MM");
                System.out.println("                           MMM:::::::::::::MM");
                System.out.println("                           MMM::::::::::::MM");
                System.out.println("                            MM::::::::::::MM");
                System.out.println("                            MM::::::::::::MM");
                System.out.println("                            MM:::::::::::MM");
                System.out.println("                            MMM::::::::::MM");
                System.out.println("                            MMM::::::::::MM");
                System.out.println("                             MM:::::::::MM");
                System.out.println("                             MMM::::::::MM");
                System.out.println("                             MMM::::::::MM");
                System.out.println("                              MM::::::::MM");
                System.out.println("                              MMM::::::MM");
                System.out.println("                              MMM::::::MM");
                System.out.println("                               MM::::::MM");
                System.out.println("                               MM::::::MM");
                System.out.println("                                MM:::::MM");
                System.out.println("                                MM:::::MM:");
                System.out.println("                                MM:::::M:M");
                System.out.println("                                MM:::::M:M");
                System.out.println("                                :M::::::M:");
                System.out.println("                               M:M:::::::M");
                System.out.println("                              M:::M::::::M");
                System.out.println("                             M::::M::::::M");
                System.out.println("                            M:::::M:::::::M");
                System.out.println("                           M::::::MM:::::::M");
                System.out.println("                           M:::::::M::::::::M");
                System.out.println("                           M;:;::::M:::::::::M");
                System.out.println("                           M:m:;:::M::::::::::M");
                System.out.println("                           MM:m:m::M::::::::;:M");
                System.out.println("                            MM:m::MM:::::::;:;M");
                System.out.println("                             MM::MMM::::::;:m:M");
                System.out.println("                              MMMM MM::::m:m:MM");
                System.out.println("                                    MM::::m:MM");
                System.out.println("                                     MM::::MM");
                System.out.println("                                      MM::MM");
                System.out.println("                                        M:M");
                break;
            case 5:
                System.out.println();
                System.out.println("___________o8o_______o8BBB8o");
                System.out.println("_________oBBBBB8o___oBBBBBBB8");
                System.out.println("_____o8BBBBBBBBBBB__BBBBBBBBB8________o88o");
                System.out.println("___o8BBBBBB**8BBBB__BBBBBBBBBB_____oBBBBBBBo");
                System.out.println("__oBBBBBBB*___***___BBBBBBBBBB_____BBBBBBBBBBo");
                System.out.println("_8BBBBBBBBBBooooo___*BBBBBBB8______*BB*_8BBBBBBo");
                System.out.println("_8BBBBBBBBBBBBBBBB8ooBBBBBBB8___________8BBBBBBB8");
                System.out.println("__*BBBBBBBBBBBBBBBBBBBBBBBBBB8_o88BB88BBBBBBBBBBBB");
                System.out.println("____*BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB8");
                System.out.println("______**8BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB*");
                System.out.println("___________*BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB8*");
                System.out.println("____________*BBBBBBBBBBBBBBBBBBBBBBBB8888**");
                System.out.println("_____________BBBBBBBBBBBBBBBBBBBBBBB*");
                System.out.println("_____________*BBBBBBBBBBBBBBBBBBBBB*");
                System.out.println("______________*BBBBBBBBBBBBBBBBBB8");
                System.out.println("_______________*BBBBBBBBBBBBBBBB*");
                System.out.println("________________8BBBBBBBBBBBBBBB8");
                System.out.println("_________________8BBBBBBBBBBBBBBBo");
                System.out.println("__________________BBBBBBBBBBBBBBB8");
                System.out.println("__________________BBBBBBBBBBBBBBBB");
                System.out.println("__________________8BBBBBBBBBBBBBBB8");
                System.out.println("__________________*BBBBBBBBBBBBBBBB");
                System.out.println("__________________8BBBBBBBBBBBBBBBB8");
                System.out.println("_________________oBBBBBBBBBBBBBBBBBB");
                System.out.println("________________oBBBBBBBBBBBBBBBBBBB");
                System.out.println("________________BBBBBBBBBBBBBBBBBBBB");
                System.out.println("_______________8BBBBBBBBBBBBBBBBBBB8");
                System.out.println("______________oBBBBBBBBB88BBBBBBBBB8");
                System.out.println("______________8BBBBBBBBB*8BBBBBBBBB*");
                System.out.println("______________BBBBBBBBB*_BBBBBBBBB8");
                System.out.println("______________BBBBBBBB8_oBBBBBBBBB*");
                System.out.println("______________8BBBBBBB__oBBBBBBBB*");
                System.out.println("______________BBBBBBB*__8BBBBBBB*");
                System.out.println("_____________8BBBBBB*___BBBBBBB*");
                System.out.println("____________8BBBBBB8___oBBBBBB8");
                System.out.println("___________8BBBBBB8____8BBBBBB*");
                System.out.println("__________oBBBBBB8____BBBBBBB8");
                System.out.println("__________BBBBBBB8___BBBBBBBB*");
                System.out.println("_________oBBBBBBB8___BBBBBBBB");
                System.out.println("_________8BBBBBB8____BBBBBBB*");
                System.out.println("_________BBBBBB*_____8BBBBB*");
                System.out.println("________oBBBB8_______BBBBB*");
                System.out.println("________oBBB8________BBBB*");
                System.out.println("________BBBB8_______8BBBBo");
                System.out.println("_______8BBBB*______oBBBBBBo");
                System.out.println("______8BBBB*_______*BBBBBBBB8o");
                System.out.println("______BBBBB*____________*88BBBo");
                break;

            case 6:
                System.out.println();
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶___¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶_____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶_________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶____________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶_______________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶____¶¶_________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶__¶¶¶¶¶¶¶¶____¶¶¶¶¶¶_____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶____¶¶¶¶¶¶¶____¶¶¶¶¶¶¶¶¶__¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶__¶¶__¶¶¶¶¶¶¶¶¶¶¶¶¶¶____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶___¶¶¶¶_______________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶___¶¶¶_______________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶____¶¶____¶¶¶¶¶¶_____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶_____¶¶_____¶¶¶¶__¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶_____¶¶¶_________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶_____¶¶¶¶_______¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶_____¶¶¶¶¶_____¶_____¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶_____¶¶¶¶¶¶¶¶¶¶_____¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶1_____¶¶¶¶¶¶¶_____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶______¶¶¶¶¶¶_____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶¶______¶¶¶______¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶__¶¶¶¶¶_____________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶____¶¶¶¶¶__________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶______¶¶¶_____________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶__________________¶¶¶_¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶_______¶¶¶_____¶_¶_¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶____¶¶¶¶¶______________¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶____________________________¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶_______________________¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶________________¶_______¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶______¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_______¶¶¶¶¶¶_______¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶____________¶¶¶¶¶¶¶________¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶________________¶¶¶¶¶¶¶¶_________¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶______¶¶¶¶¶__¶¶¶¶¶¶¶¶¶¶¶¶___________¶");
                System.out.println("¶¶¶¶¶¶¶¶______¶¶¶¶¶__¶¶¶¶¶¶¶¶¶¶¶¶¶__________¶");
                System.out.println("¶¶¶¶¶¶¶¶________________¶¶¶¶¶¶¶¶¶¶¶________¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶____________¶¶¶¶¶¶¶¶¶¶¶¶_____¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_______¶¶¶¶¶¶¶¶¶¶¶¶¶__¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                System.out.println("¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶");
                break;
            case 7:
                System.out.println();
                System.out.println("                          $$$$$$                         ");
                System.out.println("                        $$$$$$    $$$  $$$$$$$$$$$$$$$   ");
                System.out.println("               $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$         ");
                System.out.println("                 $$$$$$$$$$$$$$$$$$$$$$$$$$$$            ");
                System.out.println("                    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$    ");
                System.out.println("                $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$          ");
                System.out.println("               $$$   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$       ");
                System.out.println("            $$$$$  $$$$$$$$$$$  $$$$$$$$$$$$$$$$$$$      ");
                System.out.println("           $$$$$$$$$$$$$$$ $$    $$$$$$$$$$$$$  $$$$$    ");
                System.out.println("       $$$$$$$$$$$$$$$$$$   $     $$$$$$$$$$$$$   $$$$   ");
                System.out.println("     $$$$$$$$$$$$$$$$$$$         $$$$$$$$$$$$$$$    $$$  ");
                System.out.println("    $$$$$$$$                     $$$$$$$$$$$$$$$$     $$ ");
                System.out.println("     $$$$$                     $$$$$$$$$$$$$$$$$$        ");
                System.out.println("       $                   $$$$$$$$$$$$$$$$$$$$$$$       ");
                System.out.println("             $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$       ");
                System.out.println("               $$$$$$$$$$$$$$$$$$$$$$$$$$$$  $$$$$       ");
                System.out.println("           $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$    $$$$$       ");
                System.out.println("        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$      $$$$       ");
                System.out.println("      $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$         $$$$       ");
                System.out.println("    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$           $$$$       ");
                System.out.println("   $$$$$$$$$$$$$$$$$$$   $$$$$$               $$$        ");
                System.out.println("  $$$$$$$$$$$$$$$$                            $$         ");
                System.out.println(" $$$$$$$$$$$$$$$                 $$                      ");
                System.out.println("$$$$$$$$$$$$$$$                     $$$$                 ");
                System.out.println("$$$$$$$$$$ $$$                        $$$$$              ");
                System.out.println("$$$$$$$$$$  $$                          $$$$$$$          ");
                System.out.println("$$$$$$$$$$$   $            $$$     $$$$   $$$$$$$        ");
                System.out.println("$$$$$$$$$$$$                 $$$     $$$$$$$$$$$$$$      ");
                System.out.println("$$$$$$$$$$$$$$$              $$$$$$$$$$$$$$$$$$$$$$$$    ");
                System.out.println("$ $$$$$$$$$$$$$$$$$         $$$$$$$$$$$$$$$$$$$$$$$$$$$  ");
                System.out.println("$$  $$$$$$$$$$$$         $$$$$$$$$$$$$$$$   $$$$$$$$$$$$ ");
                System.out.println(" $  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$           $$$$$$$$");
                System.out.println("    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$              $$$$$$");
                System.out.println("    $$$$$ $$$$$$$$$$$$$$$$$$  $$$$$$$$              $$$$$");
                System.out.println("     $$$     $$$$$$$$$$$$$     $$$$$$$$$$              $$");
                System.out.println("    $$$        $$$ $$$$$$$$$     $$$$$                 $$");
                System.out.println("    $$$               $$$$$$$       $$$$$$$$            $");
                System.out.println("     $$                   $$$$$                         $");
                System.out.println("      $                    $$$$                          ");
                System.out.println("                            $$$                          ");
                System.out.println("                             $$                          ");

                break;
            case 8:
                System.out.println();
           
                System.out.println("        $                   $           $$");
                System.out.println("        $                   $           $");
                System.out.println("        $                   $           $");
                System.out.println("                            $           $");
                System.out.println("       $                   $$          $$");
                System.out.println("       $                   $$$  $$$$$$ $$");
                System.out.println("       $ $$$$$$$$$$$$$$$$$ $$          $$");
                System.out.println("       $                   $$     $$$$$$$$$$$");
                System.out.println("       $$$$$$        $$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("       $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("       $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("                     $$$$$$$$$$$$$");
                break;

            case 9:
                System.out.println();
                System.out.println("                                     $$$$$$$$$$$");
                System.out.println("                                $$$$$$$$$$$$$$$$$$");
                System.out.println("                             $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("                          $$$$$$$$$$$$$$$$ $      $$$$$$$$$$$$$$$$$$$");
                System.out.println("                        $$$$$$$$$$$               $$ $$$$$$$$$$$$$$$$$$");
                System.out.println("                       $$$$$$$$ $$$                    $$$$$$$$$$$$$$$$$");
                System.out.println("             $$$$$$$  $$$$$$$                   $$$$$  $$$$$$$$$$$$$$$$$$");
                System.out.println("         $$$$$$$$$$$$$$$$$$$        $          $$$$$$$  $$$$$$$$$$$$$$$$$");
                System.out.println("       $$$$$$$$$$$$$$$$$$$$      $$$$$$$       $$$$$$$  $$$$$$$$$$$$$$$$$$");
                System.out.println("     $$$$$$$$$$$$$$$$$$$$$$     $$$$$$$$        $$$$$   $$$$$$$$$$$$$$$$$$");
                System.out.println("    $$$$$$$$$$$$$$$$$$$$$$$      $$$$$$$     $$$        $$$$$$$$$$$$$$$$$$");
                System.out.println("   $$$$$$$$$$$$$$$$$$$$$$$$$        $                  $$$$$$$$$$$$$$$$$$ ");
                System.out.println("  $$$$$$$$$$$$$$$$$$$$$$$$$$                   $      $$$$$$$$$$$$$$$$$$");
                System.out.println(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$                      $$$$$$$$$$$$$$$$$$");
                System.out.println(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$            $$$$$ $$$$$$$$$$$$$$$$$$$");
                System.out.println("  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ $$$$$$$$$$$$$$$$");
                System.out.println("   $$$$$$$$$$$$$$$$$$$$$$$$$$ $$ $$ $  $$$$$$$$$$$$$$$$$$$$$");
                System.out.println("    $$$$$$$$$$$$$$$$$$$$$$$$$   $ $$$$$$$    $$ $$$$$$$$$$$$");
                System.out.println("     $$$$$$$$$$$$$$$$$$$$$   $$$$$$$$      $ $$$$$$$$$$$$$$");
                System.out.println("        $$$$$$$$$$$$$$$     $$$$$$$$$$    $  $$$$$ $$$$$$$");
                System.out.println("              $$$          $$$$ $$$$  $$ $ $$$$$ $$$$$");
                System.out.println("                            $$$$ $$$  $   $ $$ $$$$ $$");
                System.out.println("                            $$$$$$$     $ $ $$$$$$$$$$$");
                System.out.println("                             $$$$$$ $$$  $ $$$$$$$$$$$$");
                System.out.println("                               $$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("                                $$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("                              $$$$$$$$$$$$$$$$$$$$$ $$$$");
                System.out.println("                            $$$$$$$$$$$$$$$$ $$$$$$$$$$$$");
                System.out.println("                           $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("                           $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                break;
            case 10:
                System.out.println();
                System.out.println("              _‡_                                          ");
                System.out.println("               _‡_                                         ");
                System.out.println("               _‡‡‡_                                       ");
                System.out.println("               _‡‡‡_                                       ");
                System.out.println("               _‡‡‡‡_                                      ");
                System.out.println("               _‡‡‡‡‡_                                     ");
                System.out.println("                _‡‡‡‡‡_                                    ");
                System.out.println("                _‡‡‡‡‡‡_                                   ");
                System.out.println("                 _‡‡‡‡‡‡_                               _‡_");
                System.out.println("                 _‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                _‡‡‡‡‡‡‡‡_");
                System.out.println("                  _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_       _‡‡‡‡‡‡‡‡‡‡‡‡‡_");
                System.out.println("                  _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_  _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_ ");
                System.out.println("                    _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_   ");
                System.out.println("                   _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_      ");
                System.out.println("                  _‡   _ ‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_        ");
                System.out.println("           _‡_   _‡‡     ‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_           ");
                System.out.println("         _‡‡‡‡_ _‡‡‡‡   ‡‡‡‡‡‡‡‡‡‡     ‡‡‡‡‡‡_             ");
                System.out.println("        _‡‡‡‡‡‡     ‡‡‡‡‡‡‡‡  ‡‡‡‡     ‡‡‡‡_               ");
                System.out.println("        _‡‡‡‡‡‡     ‡‡‡‡‡‡‡‡  ‡‡‡‡‡    ‡‡‡‡_               ");
                System.out.println("         _‡‡‡‡‡     ‡‡‡‡‡     ‡‡‡‡‡‡‡‡‡   ‡_               ");
                System.out.println("          _‡‡‡‡‡‡   ‡‡‡‡‡‡      ‡‡‡‡‡‡    ‡_               ");
                System.out.println("          _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡      ‡‡‡‡‡‡    ‡_               ");
                System.out.println("          _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡     ‡‡‡‡‡‡‡    ‡_               ");
                System.out.println("           _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡   ‡‡‡‡‡‡‡‡‡  ‡_                ");
                System.out.println("            _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_       ");
                System.out.println("             _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_      ");
                System.out.println("              _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_     ");
                System.out.println("               _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_      ");
                System.out.println("               _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_          ");
                System.out.println("               _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_             ");
                System.out.println("               _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_               ");
                System.out.println("          _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("         _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("      _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("      _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("       _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("       _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("         _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("          _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("           _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("             _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("                     _‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("                   _‡‡‡‡_ _‡‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("                 _‡‡‡‡‡_   _‡‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("                   _‡‡_     _‡‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("              _‡‡‡‡‡‡‡_      _‡‡‡‡‡‡‡‡‡‡‡_                 ");
                System.out.println("              _‡‡‡‡‡‡_        _‡‡‡‡‡‡‡‡‡_                  ");
                System.out.println("               _‡‡‡‡‡_       _‡‡‡‡‡‡‡‡‡_                   ");
                System.out.println("             _‡‡‡‡‡‡_        _‡‡‡‡‡‡‡‡‡‡‡‡_                ");
                System.out.println("          _‡‡‡‡‡‡‡‡‡_         _‡‡‡‡‡‡‡‡‡‡‡_                ");
                break;

            case 11:
                System.out.println();
                System.out.println("                \\,_     __,----. ");
                System.out.println("            ,_,-/_   _,' _,'-\"\"\"\"\"-. ");
                System.out.println("           ( : : ,),' _,'_,--\"\"\"\"\"---.__ ");
                System.out.println("          _,\\ ::',',-'  '_,---\"\"\"\"\"\"---.\\                                       ____ ");
                System.out.println("        ,': :\\:\"'       ' _,''\" ___,---.__                              _____,-'. . \\ ");
                System.out.println("       J : : /          ,'__,--'   _____  \\                        _,--::::::: . . . `. ");
                System.out.println("       L: : /           ,'___,----' ___ `--.                     ,::::::::'   . . . . J ");
                System.out.println("       J :-'   .       --'          --.`. ) \\                 _,:::::'     . . . . . .L ");
                System.out.println("       ,' :   : .  ___----.__ --.___ `.`.\\  '               ,::::'      . . . . . . .J ");
                System.out.println("      / : :  : :  ,--::,,   ,`.--.`.`-.`.)               _,::'       . ..::::. . . . L ");
                System.out.println("      L : ':.::   L::'  ``:/   `. `.\\  `.\\            _,:::'      . ..:::'. . . . . / ");
                System.out.println("     J  .:.':::   F::     J `.   `. \\   \\         ,::::'     . ..::' . .__,-' ..:J ");
                System.out.println("     L,::::::::.  J::     F  ,\"\"\\  \\ LL   \\L     ,-:::'    . . .::' ....:'...  ..::L ");
                System.out.println("  ,-,.''''''''':.  L:    J  J ):.`. `JF    '__,-::::.   . . . .....:;:-'.'    ..::/ ");
                System.out.println(" / . ;;;;;;;''''::.);     \\ J/CD:\\,F  L:-.-:::::'. . . .  ..:;;;:--'.:''   . ..::/ ");
                System.out.println(" L:. _     '''\\' (:'         L:;:JP    .::\\:::::. . . .:;;:-'   ,::''     . ..::/ ");
                System.out.println(" |:.' _,       `._`----.______  \"'      .':L:::::::;;:-'       /:'       . ..::' ");
                System.out.println(" J: .::|      .`. \"-------\"\"      ._,    .:|::;;;-'          ,:'      . . .::/ ");
                System.out.println(" -L:_::J ,    \\`.`.                 (     :J-'             ,:'       . . .::' ");
                System.out.println(" ::,-`.J J J L\\ `.`.   ,- .         ,'\\  .:L             /:      . . ..::/ ");
                System.out.println("  ; ; ,J  L L \\``. `-;,' ,,oo        \"\"'   :J           ,:'      . . ..::' ");
                System.out.println("  :; :  L J\\ \\``.``._/_ =dP                ':L         /\"\"\"\"-.  . . .::/ ");
                System.out.println(" ':::..,J`.L \\`.\\`._J  =dQ                 .:F       ,'       `. ..:::' ");
                System.out.println(" ':::    \\`\\``.`._ F- =dP. . . . . . . . ;:'       /           L.::/ ");
                System.out.println(" ' ; :   `.\\\\_\\`.,-._  ::::::::::::::;:-'        ,'            J::/ ");
                System.out.println("  ;:  `./   `.`.--`._ `-:;:--.;;;;;--'::\\       .--.            |\\/ ");
                System.out.println(" : : __J         J::.`-----@'\\::.. . .'::\\    ,::'. \\           F L ");
                System.out.println(" __::::\\         |:::: J      \\::.. . .'::\\  /:: .'.:L          , J ");
                System.out.println(" J :::::L        F:::: |       L::.. . .'::\\/:: .  :.J          L-' ");
                System.out.println("  L:::::J       J::::: F       J:::.. .  .::\\: .  .: .L         F ");
                System.out.println("  |::::::L      |'':::J      ,-'\"\"\"`-. ..  . ..  .::. J        / ");
                System.out.println("  J::'::.J      F: :::|    ,'         `. . .  ` .::. . L      / ");
                System.out.println("   L: '::.L    J .  ::F   /             `.......:'. . .J     J ");
                System.out.println("   |   :: |    F :  :J   J              J \\''''''. . . .\\    J ");
                System.out.println("   J  ,.::J   J  .  :|   | |             \\ \\. . . . . . .`---.\\__ ");
                System.out.println("    L,  :::L  F :. .:L   | J              L \\. . . . `-.: .      `--.__ ");
                System.out.println("    |  .:::| /. ::.:J    |  L             J  \\. . .'. ':`-..           `-._ ");
                System.out.println("    |   ::;J/: :::::F    |  |              \\  \\       '. . `. .         . dMb ");
                System.out.println("    |  ,::;:L: ::::J     |  |               L  L        . . .`..         .`QJ ");
                System.out.println("    |   ::;:| :::::F     J  J               J   L      . . . . \\:.      . . | ");
                System.out.println("    |   :;::| ::::J       L  L               L  J     . . . . . \\:.. . . . .| ");
                System.out.println("    F  ,';::| ::::F       J  J               J  F. . .,ooo.. . . L::. . . . F ");
                System.out.println("   J  ,  ;::|: . J         L  \\               L/. . ..dM()b . . .J:: . . . J ");
                System.out.println("   | ,  ;:::J . .F         J   \\         __...J: . . .`QMP'. . . J::. . ../ ");
                System.out.println("   |    ;::::L ./           \\   L    _,-'   .::L: . . . . . . . .F:: . .:/ ");
                System.out.println("   |    ;::::|./             \\  J  ,:::.     ::J:. . . . . . . :/::'. ::' ");
                System.out.println("   |   ;:::::|/               L  \\(\\:::       ::L:. . . . . .::/::::::' ");
                System.out.println("   F   ;::.::|                J   `\"L:::      ::J:::.....:::::'::::;'\\ ");
                System.out.println("  J    ;:::::|                 \\    J:::.     .::L:::::::::,,._,,\"    L ");
                System.out.println("  |   ;::.:::F                  \\    \\:::.     .:J`,:,,_,,\"'\"\"\"\" . ; .J ");
                System.out.println("  L   ;::::.J                    L    L::       .:L\"\"\"\"'  .;. .;. ..; J ");
                System.out.println(" J.   ;:.:::|                    J    J::.       :J. . ; . .;. .;.  ;.L ");
                System.out.println(" L   ;::::::|                     \\    \\::.  . . .:L. . ; . ; . ; .;./ ");
                System.out.println("  .  ;:.::.:F                      \\    L::.  . . :J . .;. ;/\\ ; ./\\( ");
                System.out.println(" .  ;::::::J                        \\   J::   :.: .:\\ .;. ; \\/;/\\ \\F ");
                System.out.println("  . ;.:::::|                         \\   \\::  ::.. .:\\;  ./\\/\\ \\ \\/ ");
                System.out.println(" . ;::::.::|                          \\   \\:: .::.  .:\\ \\ \\   \\/  | ");
                System.out.println("  ;:.::::::L                           L   L::. ::.. .:`.\\/       | ");
                System.out.println(" ;:::::::;J                            |   J::   ::.  .::\\        J ");
                System.out.println(" :.:::.::;|                            J    L::   ::.  . :`.      | ");
                System.out.println(" :::::::;.|                             L   J::::.  '   . ::\\     F ");
                System.out.println(" ::::::;. L                             J    \\::::.      . .:\\   J ");
                System.out.println(" :::.::; J                            ,'      \\::::.      . .:\\  | ");
                System.out.println(" :::::; .|                         _,'         `.:::::     . ::L F ");
                System.out.println(" ::::; . L                       ,'    ,         \\::::.     .::JJ ");
                System.out.println(" .:::;. J                      ,'    ,'J    __,   `.:::.     .::\\ ");
                System.out.println(" :::;. .|                    ,'    ,'   L,-','____, \\:::..    .::\\ ");
                System.out.println(" ::;. . L                  ,'    ,'       ,'|        `.::..    .::\\ ");
                System.out.println(" ::; . J                 ,'             ,'  |          \\::..    .::\\ ");
                System.out.println(" :; . .|                ;'--'M-K\"------.____`           `.::.    .::\\ ");
                System.out.println(" ; . . L              ,'                .   `--._         \\::.    .::\\ ");
                System.out.println(" ;. . J              /        ,          . . .:::`--.     /`.:.    .::\\ ");
                System.out.println(" . . .L             /        /            . . .::::::`-.  L  \\. .   .::\\ ");
                System.out.println("  . .J             J        J            . . . . .::::::`.J   \\. .   .::\\ ");
                System.out.println(" . . L             L       .F             . . . . .::::::\\`    \\. .   .::\\ ");
                System.out.println("  . J             J        J             . . . . . .::::::\\     \\. .   . .L ");
                System.out.println(" . .L             F        F. .           . . . . . :::::::\\     \\:.  ..::J ");
                System.out.println("  .J              F      . J . .         . . . . . .::::::::\\     ):  ::::J ");
                System.out.println(" . L              L . . . .:F . .         . . . . . ::: . .::\\   /:  ::::.L ");
                System.out.println("  /               J  . . . .J. . .       . . . . . .:: . . .::\\ /.  ::::: L ");
                System.out.println(" /                 \\. . . . :\\. . . . . . . . . . .:: . . . .::J.  ::::::.L ");
                System.out.println("                    L. . . .::\\. . . . . . . . . .:: . . . . .:L  ::::::: | ");
                System.out.println("                    L ....:::::`.::...... . . . : .       . . :L. :::::::.| ");
                System.out.println("                   /:'. '':::::::`.::::::. ..::. .         . . J :::::: . | ");
                System.out.println("                  J::  . . .:::::::`.:::::::::.             . .,'::::::: .| ");
                System.out.println("                  L:      . . .::::::\\:::::::. .             ,' .'/ ::.:: J ");
                System.out.println("                 /:        . . .::::::\\:::::: . .          ,' .:'/ ::_ ::.J ");
                System.out.println("                J::           . . :::::L:::::. . .       ,'.:''./ ./ / :: :L ");
                System.out.println("                L:           . . .:::::J::::: . . .      '''  ./ ./ / :J :J ");
                System.out.println("               J::          . . . ::::::\\::::: . . .         ,'.:' / :/L :L ");
                System.out.println("               |::           . . .:::::J L::::. . . .      ,'.:'  / :/J :J ");
                System.out.println("               L:           . . . :::::L J:::::. . . .    (_:'   / :/ L :L ");
                System.out.println("              J::          . . . :::::J   \\:::::. . .           / :/ / :/ ");
                System.out.println("              |::           . . .:::::L    \\:::: . . .        .'.:' J :/ ");
                System.out.println("              |::          . . . ::::J      \\:::::. . .      I_:'   L:/ ");
                System.out.println("              L:          . . . .::::L       \\:::: . . .           J JL ");
                System.out.println("             J::           . . .::::J         `.::: . .            L_LJ ");
                System.out.println("             |::          . . . ::::L           \\::::. .           . ::L ");
                System.out.println("             |::           . . .:::J             `.:: . .           . :| ");
                System.out.println("             |::          . . .::::L               \\:::.           . .:J  ");
                break;

            default:
                printdef();

        }

        System.out.println("");
        printver();
        System.out.println("");
        System.out.println(" *** iProcuratio QSystem ver 1.5.2 ***");
        System.out.println("*** Based on QMS Apertum-QSystem ***");
        System.out.println("   version " + ver);
        System.out.println("   date " + date);
        System.out.println("   DB " + db);
        System.out.println("*** *** *** *** *** *** ***");
    }

    static public void printdef() {
        System.out.println();
        System.out.println("############################################################");
        System.out.println("############    iProcuratio QSystem 1.5.2       ############");
        System.out.println("###########################      ###########################");
        System.out.println("#########################          #########################");
        System.out.println("#######################              #######################");
        System.out.println("#####################                  @####################");
        System.out.println("###################                      ###################");
        System.out.println("#################         #A#A#            #################");
        System.out.println("###############          @V@@@V@             ###############");
        System.out.println("#############            @@@D@@@               @############");
        System.out.println("###########               @%%%@                  ###########");
        System.out.println("#########                      #@@##@@#            #########");
        System.out.println("#######        #@@@##@@@@# #@@#@#%  #@@#             @######");
        System.out.println("#####            #@@@##@@@@# #@@#    #@#               @####");
        System.out.println("####                        #@@@@#                      ####");
        System.out.println("####                        #@@@@#                      ####");
        System.out.println("######                      #@@@@@#                   @#####");
        System.out.println("########                  %@@@# #@@#                @#######");
        System.out.println("##########               @@@#    #@@#             @#########");
        System.out.println("############             @@#      @@@@@         @###########");
        System.out.println("##############          #@@=        #@@%      @#############");
        System.out.println("################        #@#                 @###############");
        System.out.println("##################                        @#################");
        System.out.println("####################                    @###################");
        System.out.println("######################                @#####################");
        System.out.println("########################            @#######################");
        System.out.println("##########################        ##########################");
        System.out.println("############################    ############################");
        System.out.println("############################################################");
        System.out.println("############################################################");
    }

    static public void printver() {
        System.out.println();
        System.out.println("       $  $          #    #####    ###         $  $");
        System.out.println("         $  $      ###    ##      ##  #      $  $");
        System.out.println("       $$$$$$$      ##    ####       #      $$$$$$$");
        System.out.println("         $  $       ##        #    ##        $  $");
        System.out.println("       $  $        #### # ####  # #####        $  $");
    }

}
