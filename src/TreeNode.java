public class TreeNode {
    public TreeNode father;
    public TreeNode firstChild;

    public TreeNode firstSibling;

    public int size = 0;

    public String name;
    public boolean isDir;
    public TreeNode(TreeNodeBuilder tnb){
        this.father = tnb.father;
        this.size = tnb.size;
        this.name = tnb.name;
        this.isDir = tnb.isDir;
        if (tnb.father != null) {
            if (tnb.father.firstChild == null) {
                tnb.father.firstChild = this;
            }
        }
        if (tnb.siblingFather != null){
            tnb.siblingFather.firstSibling = this;
        }
    }
}
