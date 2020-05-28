import java.util.*;

// Name : 이원형
// Student ID : 20171672

@SuppressWarnings("unchecked")
class BST <T extends KeyValue> {

    class TreeNode <U extends KeyValue> {
        U data;	// storage for data : in HW 3, T will be Item
        TreeNode<U> leftChild;	// link to the left Child
        TreeNode<U> rightChild;	// link to the right Child

        // constructors come here
        TreeNode() {
            leftChild = rightChild = null;
        }
        TreeNode(U d) {
            // data is given
            data = d;
            // the leftChild and rightChild field are null
            leftChild = rightChild = null;
        }
    };

    TreeNode <T> root;// the reference to the root node

    BST() {
        // BST constructor.
        root = null;
    }

    void Show() {

        System.out.print( "Pre  Order : ");
        PreOrder(root);
        System.out.println("");
        System.out.print("In   Order : ");
        InOrder(root);
        System.out.println("");
        System.out.print("Post Order : ");
        PostOrder(root);
        System.out.println("");
        System.out.print("Count      : ");
        System.out.print( Count(root));
        System.out.println("");
        System.out.print("Height      : ");
        System.out.println( Height(root));
        System.out.println("");
    }


    // IMPLEMENT THE FOLLOWING FUNCTIONS

    boolean  Insert(T item)  {
        // first search the key
        if(root == null) {
            root = new TreeNode<T>(item);
            return true;
        }
        TreeNode<T> ptr, parent;
        ptr = root;
        while(true) {
            if(ptr.data.GetKey()==item.GetKey()) {
                ptr.data = item;
                return false;
            }
            else if(ptr.data.GetKey()>item.GetKey()) {
                parent = ptr;
                if(ptr.leftChild == null) {
                    ptr = new TreeNode<>(item);
                    parent.leftChild = ptr;
                    break;
                }
                else {
                    ptr = ptr.leftChild;
                }
            }
            else if(ptr.data.GetKey() < item.GetKey()) {
                parent = ptr;
                if(ptr.rightChild ==null) {
                    ptr = new TreeNode<>(item);
                    parent.rightChild = ptr;
                    break;
                }
                else {
                    ptr = ptr.rightChild;
                }
            }
        }
        return true;
    }

    T Get(T item)  {
        // use the key field of item and find the node
        // do not use val field of item
        TreeNode<T> ptr;
        ptr = root;
        while(true) {
            if(ptr.data.GetKey()>item.GetKey()) {
                if(ptr.leftChild==null) {
                    return null;
                }
                else {
                    ptr = ptr.leftChild;
                }
            }
            else if(ptr.data.GetKey()<item.GetKey()) {
                if(ptr.rightChild == null) {
                    return null;
                }
                else {
                    ptr = ptr.rightChild;
                }
            }
            else {
                return ptr.data;
            }
        }
    }


    boolean Delete(T item)  {
        if(root == null)
            return false;	// non existing key
        TreeNode<T> current;
        TreeNode<T> parent;
        TreeNode<T> tmp;
        current = root;
        parent = root;
        tmp = root;
        if(item.GetKey() == root.data.GetKey()) {
            if(root.leftChild ==null && root.rightChild == null) {
                root = null;
                return true;

            }
            else if(root.leftChild !=null || root.rightChild !=null) {
                if(root.leftChild == null) {
                    tmp = root.rightChild;
                    root = tmp;
                    return true;
                }
                else if(root.rightChild ==null) {
                    tmp = root.leftChild;
                    root = tmp;
                    return true;
                }
                else {
                    tmp = current.rightChild;
                    if(tmp.leftChild == null) {
                    	TreeNode<T> tmp2;
                    	tmp2 = current.leftChild;
                    	root = tmp;
                    	root.leftChild = tmp2;
                    	return true;
                    	}
                    else {
                    	while(tmp.leftChild !=null) {
                    		parent = tmp;
                    		tmp = parent.leftChild;
                    	}
                    	if(tmp.rightChild!=null) {
                    		parent.leftChild = tmp.rightChild;
                    	}
                    	else {
                    		parent.leftChild = null;
                    	}
                    	TreeNode<T> tmp2;
                    	tmp2 = current.leftChild;
                    	TreeNode<T> tmp3;
                    	tmp3 = current.rightChild;
                    	root = tmp;
                    	root.leftChild = tmp2;
                    	root.rightChild = tmp3;
                    	return true;
                    	}
                }
        }
        }
        while(true) {
            if(current.data.GetKey()<item.GetKey()) {
                if(current.rightChild!=null) {
                    parent = current;
                    current = parent.rightChild;
                    if(current.data.GetKey() == item.GetKey()) {
                        break;
                    }
                }
                else {
                    return false;
                }
            }
            else if(current.data.GetKey()>item.GetKey()) {
                if(current.leftChild!=null) {
                    parent = current;
                    current = parent.leftChild;
                    if(current.data.GetKey()== item.GetKey()) {
                        break;
                    }
                }	
            }
            else {
            	return false;
            }

        }
        if(current.data.GetKey()!= item.GetKey()) {
            return false;
        }
        if(current.leftChild ==null && current.rightChild ==null) {
        	if(current == parent.leftChild) {
        		parent.leftChild = null;
        		return true;
        	}
        	else {
        		parent.rightChild = null;
        		return true;
        	}
        }
        else if((current.leftChild !=null) ||(current.rightChild !=null)) {
            if(current.rightChild==null) {
            	if(current==parent.leftChild) {
            		parent.leftChild = current.leftChild;
            		current = null;
            		return true;
            	}
            	else {
            		parent.rightChild = current.leftChild;
            		current = null;
            		return true;
            	}
            }
            else if(current.leftChild==null) {
            	if(current==parent.rightChild) {
            		parent.rightChild = current.rightChild;
            		current = null;
            		return true;
            	}
            	else {
            		parent.leftChild = current.rightChild;
            		current = null;;
            		return true;
            	}
            }
            else {
            	tmp = current.rightChild;
            	if(tmp.leftChild == null) {
            		TreeNode<T> tmp2;
            		tmp2 = current.leftChild;
            		TreeNode<T> tmp3;
            		tmp3 = tmp.rightChild;
            		if(current == parent.leftChild) {
            			parent.leftChild = tmp;
            		}
            		else {
            			parent.rightChild =tmp;
            		}
            		tmp.leftChild = tmp2;
            		tmp.rightChild = tmp3;
            		return true;
            	}
            	else {
            		TreeNode<T> tmp2;
            		tmp2 = current.leftChild;
            		TreeNode<T> tmp3;
            		tmp3 = current.rightChild;
            		TreeNode<T> tmpparent;
            		tmpparent = root;
            		while(tmp.leftChild!=null) {
            			tmpparent = tmp;
            			tmp = tmpparent.leftChild;
            		}
            		if(tmp.rightChild!=null) {
            			tmpparent.leftChild =  tmp.rightChild;
            		}
            		else {
            			tmpparent.leftChild = null;
            		}
            		if(current == parent.leftChild) {
            			parent.leftChild = tmp;
            		}
            		else {
            			parent.rightChild = tmp;
            		}
            		
            		tmp.leftChild = tmp2;
            		tmp.rightChild = tmp3;
            		return true;
            	}
            }
        }
        return true;
    }

    void  PreOrder(TreeNode<T> t)  {
        if(t != null) {
            System.out.print(t.data.GetKey() + "(" + t.data.GetValue() + ") ");
            if (t.leftChild != null) {
                PreOrder(t.leftChild);
            }
            if (t.rightChild != null) {
                PreOrder(t.rightChild);
            }
        }
    }

    void  InOrder(TreeNode<T> t)  {
        if ( t!= null) {
            if (t.leftChild != null) {
                InOrder(t.leftChild);
            }
            System.out.print(t.data.GetKey() + "(" + t.data.GetValue() + ") ");
            if (t.rightChild != null) {
                InOrder(t.rightChild);
            }
        }
    }

    void  PostOrder(TreeNode<T> t)  {
        if(t != null) {
            if (t.leftChild != null) {
                PostOrder(t.leftChild);
            }
            if (t.rightChild != null) {
                PostOrder(t.rightChild);
            }
            System.out.print(t.data.GetKey() + "(" + t.data.GetValue() + ") ");
        }
    }

    int  Count(TreeNode<T> t)  {
    	if(t!=null) {
        if(t.leftChild!=null) {
            if(t.rightChild!=null) {
                return Count(t.rightChild) + Count(t.leftChild) +1;
            }
            else {
                return Count(t.leftChild)+1;
            }
        }
        else if(t.rightChild!=null) {
            if(t.leftChild!=null) {
                return Count(t.rightChild) + Count(t.leftChild) +1;
            }
            else {
                return Count(t.rightChild) +1;
            }
        }
        else {
            return 1;
        }
    }
    	return 0;
    }

    int  Height(TreeNode<T> t)  {
    	if(t!=null) {
        if(t.leftChild!=null) {
            if(t.rightChild!=null) {
                if(Height(t.leftChild)>Height(t.rightChild)) {
                    return Height(t.leftChild) +1;
                }
                else {
                    return Height(t.rightChild) +1;
                }
            }
            else {
                return Height(t.leftChild) +1;
            }
        }
        else if(t.rightChild!=null) {
            if(t.leftChild!=null) {
                if(Height(t.rightChild)>Height(t.leftChild)) {
                    return Height(t.rightChild) +1;
                }
            }
            else {
                return Height(t.rightChild) +1;
            }
        }
        else {
            return 1;
        }
    }
        return 0;
    }
}