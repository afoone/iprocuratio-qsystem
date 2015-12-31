/*
 * Copyright (C) 2015 Evgeniy Egorov
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
package ru.apertum.qsystem.utils;

/**
 *
 * @author Evgeniy Egorov
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String sf = "123%S456%S789";
        System.out.println(sf);
        sf = String.format(sf, "AAA", "BBB");
        System.out.println(sf);
        
        
        String s = " AФб ä #$!- 800 ? Я";
        s = "abcDEF123 ijk $$";
        System.out.println(s);
        //s = s.replaceAll("[^\\p{L}+\\d]", "");
        s = s.replaceAll("[^\\dabcdefABCDEF]", "");
        System.out.println(s);

        String color = "123456;;;";
        if (color.length() > 6) {
            color = color.substring(0, 6);
        } else {
            if (color.isEmpty()) color = "0";
            color = "00000".substring(color.length() - 1) + color;
        }
        System.out.println(":" + color);
    }

}
