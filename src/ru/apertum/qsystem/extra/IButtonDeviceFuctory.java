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
package ru.apertum.qsystem.extra;

import ru.apertum.qsystem.ub485.core.AddrProp;

/**
 *
 * @author Evgeniy Egorov
 */
public abstract interface IButtonDeviceFuctory extends IExtra {

    public final AddrProp addrProp = AddrProp.getInstance();

    public static interface IButtonDevice {

        /**
         * Приняли от устройства и что-то делаем с этим
         *
         * @param b
         */
        public void doAction(byte b);

        /**
         * Опросить устройство
         */
        public void getFeedback();

        /**
         * Сменить адрес устройству
         */
        public void changeAdress();

        /**
         * Маякнуть
         */
        public void check();

    }

    /**
     * Message from device turn into ID from qub.adr
     *
     * @param bytes data from hardware device for pressing a button
     * @return Some class which ready to do something after receive data from device with ID - look out to qub.adr
     */
    public IButtonDevice getButtonDevice(byte[] bytes);

}
