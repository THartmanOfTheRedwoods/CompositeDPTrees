package edu.redwoods.cis18.springboot.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BinaryTreeConfiguration {

    @Value("${binaryTree.rootData}") // assuming you have this property defined in your application properties
    private int rootData;

    @Bean
    public BinaryTree binaryTree() {
        return new BinaryTree(rootData);
    }
}
