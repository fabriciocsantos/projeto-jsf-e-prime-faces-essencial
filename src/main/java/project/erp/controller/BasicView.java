package project.erp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@ViewScoped
public class BasicView implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeNode treeModel;
	private TreeNode node22;
	private TreeNode node21;
	private TreeNode node23;
	private TreeNode node24;
	
	private TreeNode node14;
	private TreeNode node13;
	private TreeNode node12;
	private TreeNode node11;

    @PostConstruct
    public void init() {
        treeModel = new DefaultTreeNode(new NodeData("Root", false), null);

        TreeNode node1 = new DefaultTreeNode(new NodeData("Node 1", false), treeModel);
        TreeNode node2 = new DefaultTreeNode(new NodeData("Node 2", false), treeModel);

        node11 = new DefaultTreeNode(new NodeData("Node 1.1", false), node1);
        node12 = new DefaultTreeNode(new NodeData("Node 1.2", false), node1);
        node13 = new DefaultTreeNode(new NodeData("Node 1.3", false), node11);
        node14 = new DefaultTreeNode(new NodeData("Node 1.4", false), node12);

        node21 = new DefaultTreeNode(new NodeData("Node 2.1", false), node2);
        node22 = new DefaultTreeNode(new NodeData("Node 2.2", false), node2);
        node23 = new DefaultTreeNode(new NodeData("Node 2.3", false), node21);
        node24 = new DefaultTreeNode(new NodeData("Node 2.4", false), node22);
        
      
    }

    public TreeNode getNode22() {
		return node22;
	}

	public void setNode22(TreeNode node22) {
		this.node22 = node22;
	}

	public TreeNode getNode21() {
		return node21;
	}

	public void setNode21(TreeNode node21) {
		this.node21 = node21;
	}

	public TreeNode getNode12() {
		return node12;
	}

	public void setNode12(TreeNode node12) {
		this.node12 = node12;
	}

	public TreeNode getNode11() {
		return node11;
	}

	public void setNode11(TreeNode node11) {
		this.node11 = node11;
	}

	public TreeNode getTreeModel() {
        return treeModel;
    }

    public TreeNode getNode23() {
		return node23;
	}

	public void setNode23(TreeNode node23) {
		this.node23 = node23;
	}

	public TreeNode getNode24() {
		return node24;
	}

	public void setNode24(TreeNode node24) {
		this.node24 = node24;
	}

	public TreeNode getNode14() {
		return node14;
	}

	public void setNode14(TreeNode node14) {
		this.node14 = node14;
	}

	public TreeNode getNode13() {
		return node13;
	}

	public void setNode13(TreeNode node13) {
		this.node13 = node13;
	}
	
	

	public class NodeData {
        private String label;
        private boolean selected;

        public NodeData(String label, boolean selected) {
            this.label = label;
            this.selected = selected;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
}
