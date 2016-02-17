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
package ru.apertum.qsystem.server.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import ru.apertum.qsystem.server.model.ITreeIdGetter;

/**
 * 
 * Stores the responses of a Dialog
 * 
 * @author Alfonso Tienda <atienda@iprocuratio.com>
 * @author Evgeniy Egorov
 */
@Entity
@Table(name = "responses")
public class QRespItem extends DefaultMutableTreeNode implements ITreeIdGetter, Serializable {

    // Versión para el serializador
    private static final long serialVersionUID = -916229912232335044L;

    @Id
    @Column(name = "id")
    @Expose
    @SerializedName("id")
    // @GeneratedValue(strategy = GenerationType.AUTO) авто нельзя, т.к. id
    // нужны для формирования дерева
    private Long id = System.currentTimeMillis();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Hierarchical link for constructing a tree
     */
    @Column(name = "parent_id")
    private Long parentId;

    @Override
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long paremtId) {
        this.parentId = paremtId;
    }

    /**
     * Help Name Node
     */
    @Expose
    @SerializedName("name")
    @Column(name = "name")
    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Text HTML
     */
    @Expose
    @SerializedName("html")
    @Column(name = "text")
    private String htmlText;

    public String getHTMLText() {
        return htmlText;
    }

    public void setHTMLText(String htmlText) {
        this.htmlText = htmlText;
    }

    /**
     * Require or not at the point of registration of input from the client any
     * data queued in front after the service selection.
     */
    @Column(name = "input_required")
    @Expose
    @SerializedName("input_required")
    private Boolean input_required = false;

    public Boolean getInput_required() {
        return input_required;
    }

    public Boolean isInput_required() {
        return input_required;
    }

    public void setInput_required(Boolean input_required) {
        this.input_required = input_required;
    }

    /**
     * The window title when entering at the point of registration by the client
     * of any data queued in front after the service selection. Also printed on
     * the ticket next to the entered data.
     */
    @Column(name = "input_caption")
    @Expose
    @SerializedName("input_caption")
    private String input_caption = "";

    public String getInput_caption() {
        return input_caption;
    }

    public void setInput_caption(String input_caption) {
        this.input_caption = input_caption;
    }

    @Transient
    public String data;

    /**
     * Mark as removed by timestamping
     */
    @Column(name = "deleted")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deleted;

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    // *******************************************************************************************************************
    // *******************************************************************************************************************
    // ********************** Implementation techniques node in the tree
    // ***********************************************************
    
    /**
     * In fact, a group of associations or services koern entire tree. That is what enabled DATA service.
     */
    @Transient
    private QRespItem parentService;
    @Expose
    @SerializedName("child_items")
    @Transient
    private LinkedList<QRespItem> childrenOfService = new LinkedList<>();

    public LinkedList<QRespItem> getChildren() {
        return childrenOfService;
    }

    @Override
    public QRespItem getChildAt(int childIndex) {
        return childrenOfService.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return childrenOfService.size();
    }

    @Override
    public QRespItem getParent() {
        return parentService;
    }

    public int getIndex(QRespItem node) {
        return childrenOfService.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return getChildCount() == 0;
    }

    @Override
    public Enumeration children() {
        return Collections.enumeration(childrenOfService);
    }

    @Override
    public void insert(MutableTreeNode child, int index) {
        child.setParent(this);
        this.childrenOfService.add(index, (QRespItem) child);
    }

    @Override
    public void remove(int index) {
        this.childrenOfService.remove(index);
    }

    @Override
    public void remove(MutableTreeNode node) {
        this.childrenOfService.remove((QRespItem) node);
    }

    @Override
    public void removeFromParent() {
        getParent().remove(getParent().getIndex(this));
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        parentService = (QRespItem) newParent;
        if (parentService != null) {
            setParentId(parentService.id);
        } else {
            parentId = null;
        }
    }

    @Override
    public int getIndex(TreeNode node) {
        return childrenOfService.indexOf(node);
    }

    @Override
    public void addChild(ITreeIdGetter child) {
        childrenOfService.add((QRespItem) child);
    }
}
