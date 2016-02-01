/*
 * 2014 Evgeniy Egorov
 * 2016 iProcuratio Consultores
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
import java.util.Properties;
import ru.apertum.qsystem.client.forms.FAbout;
import ru.apertum.qsystem.common.exceptions.ServerException;

/**
 * About class iProcuratio qSystem
 * @author Alfonso Tienda
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

    
    static public void printgirl() {


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
    }
    
}
