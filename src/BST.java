import java.util.*;

class BSTNode<T extends Comparable<? super T>> {

    protected T el;
    protected BSTNode<T> left, right;

    public BSTNode() {
        left = right = null;
    }

    public BSTNode(T el) {
        this(el, null, null);
    }

    public BSTNode(T el, BSTNode<T> lt, BSTNode<T> rt) {
        this.el = el;
        left = lt;
        right = rt;
    }
}

public class BST<T extends Comparable<? super T>> {

    protected BSTNode<T> root = null;

    public void insert(T el) {
        BSTNode<T> p = root, prev = null;
        while (p != null) {
            prev = p;
            if (el.compareTo(p.el) < 0)
                p = p.left;
            else
                p = p.right;
        }
        if (root == null)
            root = new BSTNode<T>(el);
        else if (el.compareTo(prev.el) < 0)
            prev.left = new BSTNode<T>(el);
        else
            prev.right = new BSTNode<T>(el);
    }

    public BSTNode<T> search(T el) {
        BSTNode<T> p = root;
        while (p != null)
            if (el.equals(p.el))
                return p;
            else if (el.compareTo(p.el) < 0)
                p = p.left;
            else
                p = p.right;
        return null;
    }

    public int countRightChildren() {
        return countRightChildren(root);
    }

    private int countRightChildren(BSTNode<T> node) {
        if (node == null)
            return 0;
        int count = 0;
        if (node.right != null)
            count += 1 + countRightChildren(node.right);
        if (node.left != null)
            count += countRightChildren(node.left);
        return count;
    }

    public int getHeight() {
        return getHeight(root);
    }

    public int getHeight(BSTNode<T> node) {
        if (node == null)
            return -1;
        int count = 0;
        if (node.left == null)
            count = 1 + getHeight(node.right);
        else if (node.right == null)
            count = 1 + getHeight(node.left);
        else {
            if (getHeight(node.right) > getHeight(node.left))
                count = 1 + getHeight(node.right);
            else
                count = 1 + getHeight(node.left);
        }
        return count;
    }

    public boolean checkBalanced() {
        ArrayList<Integer> levels = new ArrayList<>();
        ArrayList<BSTNode<T>> leafs = new ArrayList<>();
        boolean isPB = true;
        getLeafList(root, leafs);
        for (BSTNode<T> leaf : leafs) {
            int l = getLevel(leaf);
            levels.add(l);
        }
        int m = levels.get(0);
        for (Integer level : levels)
            if (level != m)
                isPB = false;
        return isPB;
    }

    public void getLeafList(BSTNode<T> node, ArrayList<BSTNode<T>> leafs) {
        if (node == null)
            return;
        if (node.left == null && node.right == null)
            leafs.add(node);
        if (node.left != null)
            getLeafList(node.left, leafs);
        if (node.right != null)
            getLeafList(node.right, leafs);
    }

    public int getLevel(BSTNode<T> node) {
        BSTNode<T> p = root;
        int level = 1;
        while (p != null)
            if (node.el.equals(p.el))
                return level;
            else if (node.el.compareTo(p.el) < 0) {
                p = p.left;
                level++;
            } else {
                p = p.right;
                level++;
            }
        return -1;
    }

    public boolean checkBST() {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean checkBST(BSTNode<T> node, int min, int max) {
        if (node == null)
            return true;
        if ((Integer) node.el < min || (Integer) node.el > max)
            return false;
        return (checkBST(node.left, min, (Integer) node.el - 1) && checkBST(node.right, (Integer) node.el + 1, max));
    }

    public ArrayList<T> getDescendentList(BSTNode<T> node) {
        BSTNode<T> tmp = search(node.el);
        ArrayList<T> des = new ArrayList<>();
        getDescendentList(tmp, des);
        return des;
    }

    public void getDescendentList(BSTNode<T> node, ArrayList<T> des) {
        if (node == null)
            return;
        if (node.left != null) {
            des.add(node.left.el);
            getDescendentList(node.left, des);
        }
        if (node.right != null) {
            des.add(node.right.el);
            getDescendentList(node.right, des);
        }
    }

    public ArrayList<T> getAncestorsList(BSTNode<T> node) {
        ArrayList<T> ancestorsList = new ArrayList<>();
        getAncestorsList(root, (Integer) node.el, ancestorsList);
        return ancestorsList;
    }

    public boolean getAncestorsList(BSTNode<T> node, int input, ArrayList<T> ans) {
        if (node == null)
            return false;
        if ((Integer) node.el == input)
            return true;
        if (getAncestorsList(node.left, input, ans) || getAncestorsList(node.right, input, ans)) {
            ans.add(node.el);
            return true;
        }
        return false;
    }
}
