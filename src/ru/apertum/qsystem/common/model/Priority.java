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
package ru.apertum.qsystem.common.model;

import java.util.Arrays;
import ru.apertum.qsystem.common.Uses;

/**
 * @author Evgeniy Egorov The implementation of the priority waiting list.
 *         priority - integer. the higher the number, the higher the priority.
 *         restrictions on possible priority are Uses. The default priority
 *         Uses.PRIORITY_NORMAL;
 *
 */
public final class Priority implements IPriority {

    private int priority;

    public Priority(int priority) {
        set(priority);
    }

    public Priority() {
        // The default priority is 'normal'
        priority = Uses.PRIORITY_NORMAL;
    }

    @Override
    public void set(int priority) {
        if (Arrays.binarySearch(Uses.PRIORITYS, priority) == -1) {
            throw new IllegalArgumentException("Unable to set the priority value." + " Value " + priority + " It does not belong to the allowed values: " + Arrays.toString(Uses.PRIORITYS));
        }
        this.priority = priority;
    }

    @Override
    public int get() {
        return priority;
    }

    /**
     * comparison of two priorities. priority - an integer greater than the number of those
     * выше приоритет
     * 
     * @param priority
     * @return 0 - Priorities are equal 1 - is higher than the priority parameter -1 - lower than the priority setting
     *         
     */
    @Override
    public int compareTo(IPriority priority) {
        int res = 0;
        if (this.get() > priority.get()) {
            res = 1;
        } else if (this.get() < priority.get()) {
            res = -1;
        }
        return res;
    }
}
