package edu.redwoods.cis18.springboot.controller;

import edu.redwoods.cis18.springboot.model.BinaryTree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller // This means that this class is a Controller
@RequestMapping(path="/btree") // This means URLs start with /btree (after Application path)
public class BTreeController {

    private final BinaryTree binaryTree;

    public BTreeController() {
        this.binaryTree = new BinaryTree();
    }
    private Map<String, String> getResponseObj(String key, String value) {
        Map<String, String> response = new HashMap<>();
        response.put(key, value);
        return response;
    }
    @GetMapping(path="/add")
    public @ResponseBody Map<String, String> addData(@RequestParam int data) {
        // I created the root node to put the binaryTree object into a bean container, but if this is the first add
        // let's just swap out the data of the root node so this tree behaves as if it is being created by the user.
        binaryTree.addNode(data);
        return getResponseObj("response", "SUCCESS");
    }

    @GetMapping(path="/remove")
    public @ResponseBody Map<String, String> removeData(@RequestParam int data) {
        if(binaryTree.removeNode(data)) {
            return getResponseObj("response", "SUCCESS");
        }
        return getResponseObj("response", "FAILED");
    }

    @GetMapping(path="/view")
    public @ResponseBody Map<String, String> getTree() {
        Map<String, String> respObj = getResponseObj("graph", binaryTree.treeGraph());
        respObj.put("nodeCount", String.valueOf(binaryTree.getNodeCount()));
        respObj.put("response", "SUCCESS");
        return respObj;
    }

    @GetMapping(path="/reset")
    public @ResponseBody Map<String, String> resetTree() {
        binaryTree.removeAll();
        return getResponseObj("response", "SUCCESS");
    }
}
