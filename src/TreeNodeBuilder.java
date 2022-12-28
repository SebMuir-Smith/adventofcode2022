
public class TreeNodeBuilder {

    public TreeNode father;
    public TreeNode siblingFather;

    public int size = 0;

    public String name;
    public boolean isDir;
    public TreeNodeBuilder(TreeNode father, String name, boolean isDir){
        this.father = father;
        this.name = name;
        this.isDir = isDir;
    }

    public TreeNodeBuilder siblingFather(TreeNode siblingFather){
        this.siblingFather = siblingFather;
        return this;
    }
    public TreeNodeBuilder size(int size){
        this.size = size;
        return this;
    }

    public TreeNode build(){
        return new TreeNode(this);
    }


}
