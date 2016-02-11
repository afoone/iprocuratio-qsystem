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
package ru.apertum.qsystem.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 * Network system settings. The class works as with XML, and with hibernate.
 *
 * @author Evgeniy Egorov
 * @author Alfonso Tienda <atienda@iprocuratio.com>
 */
@Entity
@Table(name = "net")
public class QNet implements Serializable {

    public QNet() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /**
     * Server port for receiving commands.
     */
    @Column(name = "server_port")
    private Integer serverPort;

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    /**
     * server port that sends the contents of the web reports.
     */
    @Column(name = "web_server_port")
    private Integer webServerPort;

    public void setWebServerPort(Integer webServerPort) {
        this.webServerPort = webServerPort;
    }

    public Integer getWebServerPort() {
        return webServerPort;
    }

    /**
     * UDP port of the client, which is sending broadcast packets.
     */
    @Column(name = "client_port")
    private Integer clientPort;

    public void setClientPort(Integer clientPort) {
        this.clientPort = clientPort;
    }

    public Integer getClientPort() {
        return clientPort;
    }

    /**
     * The start time of applications for queuing
     */
    @Column(name = "start_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date startTime;

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    /**
     * Completion time of receiving applications for queuing
     */
    @Column(name = "finish_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date finishTime;

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * DB version or configuration file. To determine the compatibility and
     * ardeyta options.
     */
    @Column(name = "version")
    private String version = "Не присвоена";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    /////////////////////////////////////////////////////////
    // Numeration
    /////////////////////////////////////////////////////////
    /**
     * For nuratsiii settings. Here you will imetstso setting for conducting
     * numerirovaniya customers and shaping them for display on the scoreboard.
     */

    /**
     * Limit the maximum possible number.
     */
    @Column(name = "last_number")
    private Integer lastNumber;

    public Integer getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(Integer lastNumber) {
        this.lastNumber = lastNumber;
    }

    /**
     * Number ext. priorities
     */
    @Column(name = "ext_priority")
    private Integer extPriorNumber;

    public Integer getExtPriorNumber() {
        return extPriorNumber;
    }

    public void setExtPriorNumber(Integer extPriorNumber) {
        this.extPriorNumber = extPriorNumber;
    }

    /**
     * Ограничение по минимально возможному номеру.
     */
    @Column(name = "first_number")
    private Integer firstNumber;

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Integer firstNumber) {
        this.firstNumber = firstNumber;
    }

    /**
     * 0 - total numbering, 1 - for each service has its own numbering
     */
    @Column(name = "numering")
    private Boolean numering;

    public Boolean getNumering() {
        return numering;
    }

    public void setNumering(Boolean numering) {
        this.numering = numering;
    }

    /**
     * 0 - office, 1 - box 2 - Front
     */
    @Column(name = "point")
    private Integer point;

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * 0 - No alarm, 1 - only signal 2 - voice signal +
     */
    @Column(name = "sound")
    private Integer sound;

    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    /**
     * 0 - the default, well, etc. on a set of sounds
     */
    @Column(name = "voice")
    private Integer voice = 0;

    public Integer getVoice() {
        return voice;
    }

    public void setVoice(Integer voice) {
        this.voice = voice;
    }

    /**
     * The residence time in the blacklist in minutes. 0 - trapped in the
     * blacklist are not blocked
     */
    @Column(name = "black_time")
    private Integer blackTime = 0;

    public Integer getBlackTime() {
        return blackTime;
    }

    public void setBlackTime(Integer blackTime) {
        this.blackTime = blackTime;
    }

    /**
     * This ID of the branch in which the system is installed. It is necessary
     * to identify the service in the cloud
     */
    @Column(name = "branch_id")
    private Long branchOfficeId;

    public Long getBranchOfficeId() {
        return branchOfficeId;
    }

    public void setBranchOfficeId(Long branchOfficeId) {
        this.branchOfficeId = branchOfficeId;
    }

    /**
     * URL cloud service, which will plug connects to the database Why? Yes, Cho
     * would have been easier to customize, and you will have to somehow
     * separate plugin admin. not all guess.
     */
    @Column(name = "sky_server_url")
    private String skyServerUrl;

    public String getSkyServerUrl() {
        return skyServerUrl;
    }

    public void setSkyServerUrl(String skyServerUrl) {
        this.skyServerUrl = skyServerUrl;
    }

    /**
     * address zone display turn to move the server to which you will plug
     * connects to the database Why? Yes, Cho would have been easier to
     * customize, and you will have to somehow separate plugin admin. not all
     * guess.
     */
    @Column(name = "zone_board_serv_addr")
    private String zoneBoardServAddr;

    public String getZoneBoardServAddr() {
        return zoneBoardServAddr;
    }

    @Transient
    private String[] zbsal = null;

    public String[] getZoneBoardServAddrList() {
        if (zbsal == null || zbsal.length == 0) {
            String l = getZoneBoardServAddr();
            l = l.replaceAll("  ", " ");
            zbsal = l.split(", |; |,|;| ");
        }
        return zbsal;
    }

    public void setZoneBoardServAddr(String zoneBoardServAddr) {
        this.zoneBoardServAddr = zoneBoardServAddr;
    }

    /**
     * 
     */
    @Column(name = "zone_board_serv_port")
    private Integer zoneBoardServPort;

    public Integer getZoneBoardServPort() {
        return zoneBoardServPort;
    }

    public void setZoneBoardServPort(Integer zoneBoardServPort) {
        this.zoneBoardServPort = zoneBoardServPort;
    }

    /**
     * This is the port zone display queue server on which it will receive data
     * necessary to identify the service in the cloud
     */
    @Column(name = "limit_recall")
    private Integer limitRecall;

    public Integer getLimitRecall() {
        return limitRecall;
    }

    public void setLimitRecall(Integer limitRecall) {
        this.limitRecall = limitRecall;
    }

    /**
     * Free arrangement of buttons on the registration point
     */
    @Column(name = "button_free_design")
    private Boolean buttonFreeDesign;

    public Boolean getButtonFreeDesign() {
        return buttonFreeDesign;
    }

    public void setButtonFreeDesign(Boolean buttonFreeDesign) {
        this.buttonFreeDesign = buttonFreeDesign;
    }

}
