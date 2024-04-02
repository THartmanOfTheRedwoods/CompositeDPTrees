# Why Learn Tree Data-Structures, Just Learn the Composite Design Pattern.

The composite design pattern is really all you need to model any type of tree data-structure. Of course, you will need to understand how to write the various implementations of various trees, but every tree data-structure you will ever learn can be broken down (Decomposed) into the Composite Design Pattern.

This repo illustrates how to write a Binary Search Tree utilizing the Composite Design Pattern (CDP).

* [TreeComponent](src/main/java/model/TreeComponent.java): This is the Interface of the CDP
* [TreeNode](src/main/java/model/TreeNode.java): This is the composite class of the CDP.
* In my implementation I chose to not make a **Leaf** node, though that could have been done.
* [BinaryTree](src/main/java/model/BinaryTree.java): This is the client of the CDP.

## Other Cool Things

* This is a SpringBoot Initialized project that provides a simple API Allowing you to **add** and **remove** int nodes from a binary tree.
* [chart.html](src/main/resources/static/chart.html): This provides a simple HTML5 GUI hosted by the internal SrpingBoot Tomcat Server.
    * Javascript is utilized to add event handlers to the buttons that call the SpringBoot API.
    * A custom event is utilized to notify the view to update its graph.
    * Mermaid's API is utilized to display the graph.
* [BTreeController](src/main/java/edu/redwoods/cis18/springboot/controller/BTreeController.java): This is the main controller and where the API endpoints are defined.
    * This controller utilizes [BinaryTreeConfiguration](src/main/java/edu/redwoods/cis18/springboot/model/BinaryTreeConfiguration.java) and the **@Component** annotation on [BinaryTree](src/main/java/model/BinaryTree.java) to create a **Singleton bean** named **binaryTree** for the controller to manipulate.
* [UniqueNameGenerator](src/main/java/edu/redwoods/cis18/springboot/helper/UniqueNameGenerator.java): This class contains a static helper function simply used to generate unique names for the Mermaid graph created by the **binaryTree* bean.

<video width="100%" loop muted src="BinaryTree.mp4" controls>
Your browser does not support the video tag.
</video>
