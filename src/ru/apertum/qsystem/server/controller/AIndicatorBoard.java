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
package ru.apertum.qsystem.server.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import ru.apertum.qsystem.common.CustomerState;
import ru.apertum.qsystem.common.model.ATalkingClock;
import ru.apertum.qsystem.common.model.QCustomer;
import ru.apertum.qsystem.server.model.QUser;

/**
 * The base class for output classes. There is implemented the engine management
 * and storage lines and other infoy to display information. With direct output
 * to boards need to call this method markShowed (), to mark the record as the
 * beginning hang.
 *
 * @author Evgeniy Egorov
 */
abstract public class AIndicatorBoard implements IIndicatorBoard {

	/**
	 * The number of lines on the display. Implement a specific board.
	 * 
	 * @return The number of lines on the display.
	 */
	abstract protected Integer getLinesCount();

	/**
	 * Delay update the main board in seconds.
	 */
	private Integer pause = 0;

	public Integer getPause() {
		return pause;
	}

	public void setPause(Integer pause) {
		this.pause = pause;
	}

	// ***********************************************************************
	// *************** Working with storage lines ******************************
	/**
	 * List of lines displayed name of the user who created this line to Display
	 * (This is the identifier string as the name pozzovatelya unique in the
	 * system) - line
	 */
	protected final LinkedHashMap<String, Record> records = new LinkedHashMap<>();

	/**
	 * Adds an entry to display a list of the tail makes it is not yet Display.
	 * Flashing moved in the scoreboard.
	 *
	 * @param record
	 */
	protected void addItem(Record record) {
		records.remove(record.getUserName());
		record.isShowed = false;
		// record.setState(record.getState() == Uses.STATE_INVITED ?
		// Uses.STATE_REDIRECT : Uses.STATE_INVITED);
		records.put(record.getUserName(), record);
	}

	/**
	 * Убрать запись. Кастомер домой ушел.
	 *
	 * @param record
	 */
	protected void removeItem(Record record) {
		records.remove(record.userName);
	}

	/**
	 * Recupera los registros que se han de mostrar
	 * @return
	 */
	protected LinkedList<Record> getShowRecords() {
		ArrayList<Record> arr = new ArrayList<>(records.values());
		// flip the array as written tumbled to the end and remove them first
		for (int i = 0; i < arr.size() / 2; i++) {
			final Record a_i = arr.get(i);
			arr.set(i, arr.get(arr.size() - 1 - i));
			arr.set(arr.size() - 1 - i, a_i);
		}

		int pos = -1; // позиция последнего не отвесевшего.
		for (int i = 0; i < arr.size(); i++) {
			if (!arr.get(i).isShowed()) {
				pos = i;
			}
		}
		final int startPos = (getLinesCount() - 1 > pos) ? 0 : pos - getLinesCount() + 1; // позиция
																							// первой
																							// строки
																							// на
																							// табло.
		final LinkedList<Record> res = new LinkedList<>();
		for (int j = 0; j < arr.size(); j++) {
			if (j >= startPos && j < startPos + getLinesCount()) {
				res.add(arr.get(j));
			}
		}
		return res;
	}

	/**
	 * Class for single row Es el elemento de fila en el main board
	 */
	public class Record implements Comparable<Record> {

		final public String point;
		final public String customerPrefix;
		final public Integer customerNumber;

		/**
		 * To String, identifica el número de cliente y el punto a ir
		 */
		@Override
		public String toString() {
			return customerNumber + "-" + point;
		}

		/**
		 * The name of the user who created this line on the scoreboard. This
		 * identifier strings, because pozzovatelya unique name in the system.
		 */
		final private String userName;

		public String getUserName() {
			return userName;
		}

		final public Integer interval;
		/**
		 * When RS is the address of the device. When the monitor output is a
		 * serial number
		 */
		final public Integer adressRS;
		final public String ext_data;

		/**
		 * I plumb on the scoreboard or not.
		 */
		private boolean isShowed = false;

		/**
		 * Уже показалась сколько надо
		 *
		 * @return
		 */
		public boolean isShowed() {
			return isShowed;
		}

		/**
		 * значения состояния "очередника"
		 */
		private CustomerState state = CustomerState.STATE_INVITED;

		public CustomerState getState() {
			return state;
		}

		public void setState(CustomerState state) {
			this.state = state;
		}

		/**
		 * When you create a line gets to the display list with a sign that has
		 * not otvesela. Timer hovering vklyucheetsya when the line gets on the
		 * scoreboard.
		 *
		 * @param userName
		 * @param point
		 *            Ventanilla donde se llama al cliente
		 * @param customerPrefix
		 * @param customerNumber
		 *            Número de índice del cliente
		 * @param ext_data
		 *            Tercera columna
		 * @param adressRS
		 *            dirección del display del cliente
		 * @param interval
		 *            Obligatory time hovering line on the display in seconds
		 */
		public Record(String userName, String point, String customerPrefix, Integer customerNumber, String ext_data, Integer adressRS, Integer interval) {
			this.ext_data = ext_data;
			this.adressRS = adressRS;
			this.customerPrefix = customerPrefix;
			this.customerNumber = customerNumber;
			this.userName = userName;
			this.point = point;
			this.interval = interval;
			final Record re = this;
			records.put(userName, re);
			showTimer = new ATalkingClock(interval * 1000, 1) {

				@Override
				public void run() {
					isShowed = true;
					show(null);
				}
			};
		}

		public Record(CustomerState state, String point, String customerPrefix, Integer customerNumber, String ext_data, Integer adressRS) {
			this.ext_data = ext_data;
			this.customerPrefix = customerPrefix;
			this.customerNumber = customerNumber;
			this.point = point;
			this.state = state;
			this.interval = 0;
			this.adressRS = adressRS;
			this.userName = "noName";
			showTimer = null;
		}

		/**
		 * Таймер время висения на табло.
		 */
		final private ATalkingClock showTimer;

		/**
		 * Запись попала на табло.
		 */
		public void startVisible() {
			if (!showTimer.isActive()) {
				showTimer.start();
			}
		}

		@Override
		public int compareTo(Record o) {
			return (o != null && adressRS.equals(o.adressRS) && customerNumber.equals(o.customerNumber) && point.equals(o.point) && state == o.state) ? 0 : -1;
		}
	}
	// **************************************************************************
	// ************************** Методы взаимодействия
	// *************************

	@Override
	public synchronized void inviteCustomer(QUser user, QCustomer customer) {
		Record rec = records.get(user.getName());
		if (rec == null) {
			rec = new Record(user.getName(), user.getPoint(), customer.getPrefix(), customer.getNumber(), user.getPointExt().replace("###", customer.getFullNumber()).replace("@@@", user.getPoint()), user.getAdressRS(), getPause());
		} else {
			addItem(rec);
		}
		show(rec);
	}

	/**
	 * На табло оператора долженн перестать мигать номер вызываемого клиента
	 *
	 * @param user
	 *            пользователь, который начал работать с клиентом.
	 */
	@Override
	@SuppressWarnings("empty-statement")
	public synchronized void workCustomer(QUser user) {
		Record rec = records.get(user.getName());
		// запись может быть не найдена после рестарта сервера, список номеров
		// на табло не бакапится
		if (rec == null) {
			rec = new Record(user.getName(), user.getPoint(), ((QUser) user).getCustomer().getPrefix(), ((QUser) user).getCustomer().getNumber(), user.getPointExt().replace("###", ((QUser) user).getCustomer().getFullNumber()).replace("@@@", user.getPoint()), user.getAdressRS(), getPause());
		}
		rec.setState(CustomerState.STATE_WORK);
		show(rec);
	}

	/**
	 * На табло по определенному адресу должно отчистиццо табло
	 *
	 * @param user
	 *            пользователь, который удалил клиента.
	 */
	@Override
	public synchronized void killCustomer(QUser user) {
		final Record rec = records.get(user.getName());
		// запись может быть не найдена после рестарта сервера, список номеров
		// на табло не бакапится
		if (rec != null) {
			rec.setState(CustomerState.STATE_DEAD);
			removeItem(rec);
			show(rec);
		}
	}

	/**
	 * Выключить информационное табло.
	 */
	@Override
	public synchronized void close() {
		showOnBoard(new LinkedList<>());
	}

	// **************************************************************************
	// ************************** Другие методы
	// *********************************
	// чтоб отсеч дублирование
	private Record oldRec = null;
	private LinkedList<Record> oldList = new LinkedList<>();

	private boolean compareList(LinkedList<Record> newList) {
		if (oldList.size() != newList.size()) {
			return false;
		}
		final int size = oldList.size();
		final Record[] ol = oldList.toArray(new Record[size]);
		final Record[] nl = newList.toArray(new Record[size]);
		for (int i = 0; i < size; i++) {
			if (ol[i].compareTo(nl[i]) != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Тут вся иллюминация
	 *
	 * @param record
	 */
	protected void show(Record record) {

		LinkedList<Record> newList = getShowRecords();

		// System.out.println("--swow " + record + " records for show: "
		// +newList);
		if (!compareList(newList)) {
			oldList = new LinkedList<>();
			newList.stream().forEach((rec) -> {
				oldList.add(new Record(rec.state, rec.point, rec.customerPrefix, rec.customerNumber, rec.ext_data, rec.adressRS));
			});
			// System.out.println("go to showOnBoard " + newList);
			showOnBoard(newList);
		}
		if (record != null) {
			if (record.compareTo(oldRec) != 0) {
				oldRec = new Record(record.state, record.point, record.customerPrefix, record.customerNumber, record.ext_data, record.adressRS);
				showToUser(record);
			}
		}
	}

	/**
	 * При непосредственным выводом на табло нужно вызвать этот метод, чтоб
	 * промаркировать записи как начавшие висеть.
	 *
	 * @param list
	 *            список выводимых звписей.
	 */
	protected void markShowed(Collection<Record> list) {
		// Записи попадают на табло
		if (list != null) {
			list.stream().filter((rec) -> (!rec.isShowed())).forEach((rec) -> {
				rec.startVisible();
			});
		}
	}

	/**
	 * Высветить записи на общем табло.
	 *
	 * @param records
	 *            Высвечиваемые записи.
	 */
	abstract protected void showOnBoard(LinkedList<Record> records);

	/**
	 * Высветить запись на табло оператора.
	 *
	 * @param record
	 *            Высвечиваемая запись.
	 */
	abstract protected void showToUser(Record record);
}
