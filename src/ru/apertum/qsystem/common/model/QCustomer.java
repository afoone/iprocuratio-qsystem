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

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ru.apertum.qsystem.server.model.QService;
import ru.apertum.qsystem.server.model.QUser;
import ru.apertum.qsystem.server.model.results.QResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.Date;
import java.util.ServiceLoader;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.apertum.qsystem.common.QLog;
import ru.apertum.qsystem.common.CustomerState;
import ru.apertum.qsystem.common.QConfig;
import ru.apertum.qsystem.common.exceptions.ServerException;
import ru.apertum.qsystem.extra.IChangeCustomerStateEvent;
import ru.apertum.qsystem.server.Spring;
import ru.apertum.qsystem.server.model.IidGetter;
import ru.apertum.qsystem.server.model.response.QRespEvent;

/**
 * * Client implementation simply "waiting list". Used to organize a simple
 * queue. If you are using the database, the saving takes place when changing
 * state. IMPORTANT! Always change customer status when it changes, especially
 * when it is removed.
 * 
 * @author Alfonso Tienda <atienda@iprocuratio.com>
 * @author Evgeniy Egorov
 */
@Entity
@Table(name = "clients")
public final class QCustomer implements Comparable<QCustomer>, Serializable, IidGetter {

    public QCustomer() {
        id = new Date().getTime();
    }

    /**
     * We create a customer with only his number in the queue. Prefix is
     * defined, because still do not know about the service where to put it.
     * Assign customer service - priority and its attributes.
     *
     * @param number
     *            customer number in the queue
     */
    public QCustomer(int number) {
        this.number = number;
        id = new Date().getTime();
        setStandTime(new Date());
        // Initialization steps when setting all the other properties customer
        // about the service which was put down in the service itself by placing
        // it customer
        QLog.l().logger().debug("Created customer number " + number);
    }

    @Expose
    @SerializedName("id")
    private Long id = new Date().getTime();

    @Id
    @Column(name = "id")
    @Override
    // @GeneratedValue(strategy = GenerationType.AUTO) marked as unique
    // room creation time.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * ATTRIBUTES "waiting" personal number, it is for him the system keeps a
     * record of and manage the waiting room - integer It's the waiting number
     */
    @Expose
    @SerializedName("number")
    private Integer number;

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    @Expose
    @SerializedName("stateIn")
    private Integer stateIn;

    @Column(name = "state_in")
    public Integer getStateIn() {
        return stateIn;
    }

    public void setStateIn(Integer stateIn) {
        this.stateIn = stateIn;
    }

    /**
     * ATTRIBUTES "waiting" state customer, it is for him the system knows what
     * is happening with customer This state change only if customer is already
     * ready for this and all other options had been filled. If data is written
     * to the database, it is only as of completion of the processing on it. So
     * if some iteration is finished and about customer should enter into the
     * database, as well as necessary to expose that customer finish processing,
     * and then change, if necessary, its attributes and change the state, such
     * as REDIREKTENNOGO.
     *
     * client state
     *
     * @see ru.apertum.qsystem.common.Uses
     */
    @Expose
    @SerializedName("state")
    private CustomerState state;

    public void setState(CustomerState state) {
        setState(state, new Long(-1));
    }

    /**
     * Especially for redirection and return after a redirect
     *
     * @param state
     * @param newServiceId
     *            - When redirecting and return after a redirect here to ID of
     *            the service which redirects or refund, and service at customer
     *            still the same, ie, way in which the completed work with him
     */
    public void setState(CustomerState state, Long newServiceId) {
        this.state = state;
        stateIn = state.ordinal();

        // it will be possible to follow the shadow kastomer at the user and its
        // changes
        if (getUser() != null) {
            getUser().getShadow().setCustomerState(state);
        }

        switch (state) {
        case STATE_DEAD:
            QLog.l().logger().debug("Status: customer number \"" + getPrefix() + getNumber() + "\" going home by withdrawal");
            getUser().getPlanService(getService()).inkKilled();
            // Direct trans: dick to him, then to keep pochekat nepodoshedshih.
            // kastomer keep in the database only need to put down finish_taym,
            // dick Sneem and start_taym too yadrenbaton
            setStartTime(new Date());
            setFinishTime(new Date());
            saveToSelfDB();
            break;
        case STATE_WAIT:
            QLog.l().logger().debug("Status: customer came and waiting number \"" + getPrefix() + getNumber() + "\"");
            break;
        case STATE_WAIT_AFTER_POSTPONED:
            QLog.l().logger().debug("Status: customer was returned from the pending expiration time and waiting number \"" + getPrefix() + getNumber() + "\"");
            break;
        case STATE_WAIT_COMPLEX_SERVICE:
            QLog.l().logger().debug("Status: customer was again put in place since powerful complex service and waiting number \"" + getPrefix() + getNumber() + "\"");
            break;
        case STATE_INVITED:
            QLog.l().logger().debug("Status: invited customer number \"" + getPrefix() + getNumber() + "\"");
            break;
        case STATE_INVITED_SECONDARY:
            QLog.l().logger().debug("Status: Invited again customer processing chain with the number \"" + getPrefix() + getNumber() + "\"");
            break;
        case STATE_REDIRECT:
            QLog.l().logger().debug("Status: customer redirect or number \"" + getPrefix() + getNumber() + "\"");
            getUser().getPlanService(getService()).inkWorked(new Date().getTime() - getStartTime().getTime());
            // saving customer to database
            saveToSelfDB();
            break;
        case STATE_WORK:
            QLog.l().logger().debug("We started to work with a customer number \"" + getPrefix() + getNumber() + "\"");
            getUser().getPlanService(getService()).upWait(new Date().getTime() - getStandTime().getTime());
            break;
        case STATE_WORK_SECONDARY:
            QLog.l().logger().debug("Status: Further along the chain started to work with a customer number \"" + getPrefix() + getNumber() + "\"");
            break;
        case STATE_BACK:
            QLog.l().logger().debug("Status: customer number \"" + getPrefix() + getNumber() + "\" return to the fron service");
            break;
        case STATE_FINISH:
            QLog.l().logger().debug("Status: customer number \"" + getPrefix() + getNumber() + "\" finished work");
            getUser().getPlanService(getService()).inkWorked(new Date().getTime() - getStartTime().getTime());
            // customer save on database
            saveToSelfDB();
            break;
        case STATE_POSTPONED:
            QLog.l().logger().debug("Status: Customer number \"" + getPrefix() + getNumber() + "\" is waiting for the list of pending");
            getUser().getPlanService(getService()).inkWorked(new Date().getTime() - getStartTime().getTime());
            // saving customer to database
            saveToSelfDB();
            break;
        }

        // Support for plugins
        // Lo que hace es llamar a una clase IChangeCustomerStateEvent tras
        // ejecutar un cambio
        // Algo así como un hook
        for (final IChangeCustomerStateEvent event : ServiceLoader.load(IChangeCustomerStateEvent.class)) {
            QLog.l().logger().info("Call SPI expansion. Description: " + event.getDescription());
            try {
                event.change(this, state, newServiceId);
            } catch (Throwable tr) {
                QLog.l().logger().error("Call SPI expansion will fail. Description: " + tr);
            }
        }
    }

    @Transient
    private final LinkedList<QRespEvent> resps = new LinkedList<>();

    public void addNewRespEvent(QRespEvent event) {
        resps.add(event);
    }

    /**
     * Saving customer to database
     */
    private void saveToSelfDB() {
        // Save customer to database
        final DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = Spring.getInstance().getTxManager().getTransaction(def);
        try {
            if (input_data == null) { // Well here's the devil pulled put of
                                      // constraints on the fact that the data
                                      // entered is not zero, and they rarely
                                      // need this entry
                input_data = "";
            }
            Spring.getInstance().getHt().saveOrUpdate(this);
            // crutch. If kastomer left reviews before you hit the database, ie
            // even during operation with it.
            if (resps.size() > 0) {
                Spring.getInstance().getHt().saveAll(resps);
                resps.clear();
            }
        } catch (Exception ex) {
            Spring.getInstance().getTxManager().rollback(status);
            throw new ServerException("Error Saving Customer to database \n" + ex.toString() + "\n" + Arrays.toString(ex.getStackTrace()));
        }
        Spring.getInstance().getTxManager().commit(status);
        QLog.l().logger().debug("Stored customer.");
    }

    /**
     * Devuelve el estado del customer
     * 
     * @return
     */
    @Transient
    public CustomerState getState() {
        return state;
    }

    /**
     * PRIORITY "waiting list"
     */
    @Expose
    @SerializedName("priority")
    private Integer priority;

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Transient
    public IPriority getPriority() {
        return new Priority(priority);
    }

    /**
     * Compare waiting to select the first. Participates priority waiting list.
     * compare the priority, then the time
     *
     * @param customer
     * @returnusing the ratio of "Maintenance later" (the comparison gives an
     *              answer to the question "I cater for at least one parameter?"
     *              )
     * 
     *              (1) - "Maintenance later" than kastomer parameter,
     * 
     *              (- 1) - "Maintenance sooner" than kastomer parameter, (0) -
     *              at the same time
     * 
     * 
     *              (- 1) - be serviced faster than kastomer parameter because
     *              got up before (1) - than kastomer be serviced after the
     *              parameters, since arose later
     */
    @Override
    public int compareTo(QCustomer customer) {
        // (-1) - because higher priority will be serviced

        int resultCmp = -1 * getPriority().compareTo(customer.getPriority());

        if (resultCmp == 0) {
            if (this.getStandTime().before(customer.getStandTime())) {
                resultCmp = -1;
            } else if (this.getStandTime().after(customer.getStandTime())) {
                resultCmp = 1;
            }
        }
        if (resultCmp == 0) {
            QLog.l().logger().warn("Clients may not be equal.");
            resultCmp = -1;
        }
        return resultCmp;
    }

    /**
     * Which service is worth. It is necessary for statistics.
     */
    @Expose
    @SerializedName("to_service")
    private QService service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    public QService getService() {
        return service;
    }

    /**
     * Customer places a service attributes including name, description, prefix.
     * And the prefix is put once and for all. When you add in customer's
     * addCustomer () service comes + exhibited the same prefix, if this
     * attribute is not added to the XML-node customer
     *
     * @param service
     *            not to transfer here NULL
     */
    public void setService(QService service) {
        this.service = service;
        // Prefix for customer bear when it is created, once and for all.
        if (getPrefix() == null) {
            setPrefix(service.getPrefix());
        }
        QLog.l().logger().debug("Client \"" + getFullNumber() + "\" put to the service \"" + service.getName() + "\"");
    }

    /**
     * The result of the user
     */
    private QResult result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    public QResult getResult() {
        return result;
    }

    public void setResult(QResult result) {
        this.result = result;
        if (result == null) {
            QLog.l().logger().debug("Denotes the result of working with kastomer not required");
        } else {
            QLog.l().logger().debug("Denotes the result of working with stadiometer: \"" + result.getName() + "\"");
        }
    }

    /**
     * Who handles. (wich user) It is necessary for statistics.
     */
    @Expose
    @SerializedName("from_user")
    private QUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public QUser getUser() {
        return user;
    }

    public void setUser(QUser user) {
        this.user = user;
        QLog.l().logger().debug("Customer \"" + getPrefix() + getNumber() + (user == null ? " the user is not present, yet he did not cause it\"" : " define user \"" + user.getName() + "\""));
    }

    /**
     * Prefix service which is customer.
     *
     * String prefix
     */
    @Expose
    @SerializedName("prefix")
    private String prefix;

    @Column(name = "service_prefix")
    public String getPrefix() {
        return prefix;
    }

    @Transient()
    public String getFullNumber() {
        return "" + getPrefix() + QConfig.cfg().getNumDivider(getPrefix()) + getNumber();
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? "" : prefix;
    }

    @Expose
    @SerializedName("stand_time")
    private Date standTime;

    @Column(name = "stand_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStandTime() {
        return standTime;
    }

    public void setStandTime(Date date) {
        this.standTime = date;
    }

    @Expose
    @SerializedName("start_time")
    private Date startTime;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    private Date callTime;

    public void setCallTime(Date date) {
        this.callTime = date;
    }

    @Transient
    public Date getCallTime() {
        return callTime;
    }

    @Expose
    @SerializedName("finish_time")
    private Date finishTime;

    @Column(name = "finish_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date date) {
        this.finishTime = date;
    }

    @Expose
    @SerializedName("input_data")
    private String input_data = "";

    /**
     * Kastomer entered data at the point of registration.
     *
     * @return
     */
    @Column(name = "input_data")
    public String getInput_data() {
        return input_data;
    }

    public void setInput_data(String input_data) {
        this.input_data = input_data;
    }

    /**
     * The list of services that need to come back after redirect New services
     * are added to return to the top of the list. In return we take the first
     * on the list and delete it.
     */
    private final LinkedList<QService> serviceBack = new LinkedList<>();

    /**
     * When you redirect if there is a return. then add the service to return
     *
     * @param service
     *            at which service need to come back
     */
    public void addServiceForBack(QService service) {
        serviceBack.addFirst(service);
        needBack = !serviceBack.isEmpty();
    }

    /**
     * Where to return when the work is completed by customer redirection
     *
     * @return return to this service
     */
    @Transient
    public QService getServiceForBack() {
        needBack = serviceBack.size() > 1;
        return serviceBack.pollFirst();
    }

    @Expose
    @SerializedName("need_back")
    private boolean needBack = false;

    public boolean needBack() {
        return needBack;
    }

    /**
     * Comment on user's customer when redirecting and sent to the deferred
     */
    @Expose
    @SerializedName("temp_comments")
    private String tempComments = "";

    @Transient
    public String getTempComments() {
        return tempComments;
    }

    public void setTempComments(String tempComments) {
        this.tempComments = tempComments;
    }

    /**
     *
     */
    @Expose
    @SerializedName("post_atatus")
    private String postponedStatus = "";

    @Transient
    public String getPostponedStatus() {
        return postponedStatus;
    }

    public void setPostponedStatus(String postponedStatus) {
        this.postponedStatus = postponedStatus;
    }

    /**
     * Deferred Period in minutes. 0 - indefinitely
     */
    @Expose
    @SerializedName("postpone_period")
    private int postponPeriod = 0;

    @Transient
    public int getPostponPeriod() {
        return postponPeriod;
    }

    /**
     * ID of who sees deferred, NULL for all
     */
    @Expose
    @SerializedName("is_mine")
    private Long isMine = null;

    @Transient
    public Long getIsMine() {
        return isMine;
    }

    public void setIsMine(Long userId) {
        this.isMine = userId;
    }

    /**
     * Number of repeated calls of this customer
     */
    @Expose
    @SerializedName("recall_cnt")
    private Integer recallCount = 0;

    @Transient
    public Integer getRecallCount() {
        return recallCount;
    }

    public void setRecallCount(Integer recallCount) {
        this.recallCount = recallCount;
    }

    public void upRecallCount() {
        this.recallCount++;
    }

    public void setPostponPeriod(int postponPeriod) {
        this.postponPeriod = postponPeriod;
        startPontpone = new Date().getTime();
        finishPontpone = startPontpone + postponPeriod * 1000 * 60;
    }

    private long startPontpone = 0;
    private long finishPontpone = 0;

    @Transient
    public long getFinishPontpone() {
        return finishPontpone;
    }

    /**
     * Returns a string describing kastomer
     */
    @Override
    public String toString() {
        return getFullNumber() + (getInput_data().isEmpty() ? "" : " " + getInput_data()) + (postponedStatus.isEmpty() ? "" : " " + postponedStatus + (postponPeriod > 0 ? " (" + postponPeriod + "min.)" : "") + (isMine != null ? " Private!" : ""));
    }

    @Transient
    @Override
    public String getName() {
        return getFullNumber() + " " + getInput_data();
    }

    @Expose
    @SerializedName("complex_id")
    public LinkedList<LinkedList<LinkedList<Long>>> complexId = new LinkedList<>();

    @Transient
    public LinkedList<LinkedList<LinkedList<Long>>> getComplexId() {
        return complexId;
    }

    public void setComplexId(LinkedList<LinkedList<LinkedList<Long>>> complexId) {
        this.complexId = complexId;
    }

}
