package com.task.network.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String value;
    List<Node> childNodes;
    
    public Node(String value) {
        this.value = value;
    }
    
    public void addChild(ArrayList<String> childValues) {
        ArrayList<Node> childNodes = new ArrayList<>();
        
        for (String nodeValue : childValues)
            childNodes.add(new Node(nodeValue));
        
        this.childNodes = childNodes;
    }
    
    public String getValue() {
        return value;
    }
    
    public List<Node> getChildNodes() {
        return childNodes;
    }
}
