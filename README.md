#                                       Compiler Project

It is a Java code analyzer that produces information on the code coverage of statements and branches.

------



## Project Features

- Producing a modified intermediate java file after analyzing a java file as an input.

- Adding a comment to each block of this code that indicates the block's number.

  - The comment should read as follows:

    // Block number 3

- Determining which code blocks are visited following the execution of the modified intermediate Java file:

  - The file should seem as follows:

    Block number 1 is visited only once

    Block number 2 is visited two times


------



## Project Requirements

<a href="https://github.com/RamziMuhammad"></a>

- <a href="https://www.jetbrains.com/idea/download/#section=windows">**IntelliJ IDEA**</a>
- <a href="https://www.antlr.org/download.html">**ANTLR**</a>
- <a href="https://github.com/antlr/grammars-v4/tree/master/java/java">**Java ANTLR Grammar**</a>

------



### Parse Tree

<p align="center">
  <img src="https://github.com/RamziMuhammad/ANTLR-Project/blob/main/Parse-Trees/Parse-Tree(3).png" style="width:800px;"/>
</p>



------

### Modified Intermediate Java Code

- [x] First, using the following code, we add **comment** to each code block:


```java
@Override
    public Object visitBlock(JavaParser.BlockContext ctx) {
        rewriter.insertAfter(ctx.getStart(), " /* Block number " + blockCount + " */\n" +
                "executedBlocks.add(" + blockCount++ + ");");
        return super.visitBlock(ctx);
    }
```

- [x] We also add that number to a **list** that was declared at the beginning of the class:

```java
String imports = "import java.util.ArrayList;\n" +
            "import java.io.FileWriter;\n" +
            "import java.io.IOException;\n";

@Override
    public Object visitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.getStart(), imports);
        return super.visitCompilationUnit(ctx);
    }
```

- [x] Finally, at the end of the class, we put a block of code that iterates through that list and **creates a file with details about which block was visited**:

```java
String writingOutputFile = "String outputFileName = \"Visited-Blocks.txt\";\n" +
            "try (FileWriter fileWriter = new FileWriter(outputFileName)) {\n" +
            "    int previous = executedBlocks.get(0);\n" +
            "    int count = 1;\n" +
            "    for (int i = 1; i < executedBlocks.size(); i++) {\n" +
            "        if (executedBlocks.get(i) == previous) {\n" +
            "            count++;\n" +
            "        } else {\n" +
            "            if(count == 1){\n" +
            "                fileWriter.write(\"Block number \" + previous + \" is visited only once\\n\");\n" +
            "            } else {\n" +
            "                fileWriter.write(\"Block number \" + previous + \" is visited \" + count + \" times\\n\");\n" +
            "            }\n" +
            "            count = 1;\n" +
            "        }\n" +
            "        previous = executedBlocks.get(i);\n" +
            "    }\n" +
            "    if(count == 1){\n" +
            "        fileWriter.write(\"Block number \" + previous + \" is visited only once\\n\");\n" +
            "    } else {\n" +
            "        fileWriter.write(\"Block number \" + previous + \" is visited \" + count + \" times\\n\");\n" +
            "    }\n" +
            "} catch (IOException e) {\n" +
            "    e.printStackTrace();\n" +
            "}\n";

@Override
    public Object visitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        rewriter.insertBefore(ctx.getStart(), arrayDeclaration);
        rewriter.insertBefore(ctx.getStop(), writingOutputFile);
        return super.visitClassBodyDeclaration(ctx);
    }
```

------



> #### [Intermediate Code](./ANTLR-Project/Intermediate-Code.java)

<p align="center">
  <img src="https://github.com/RamziMuhammad/ANTLR-Project/blob/main/Assets/Intermediate-Code.png" style="width:800px;"/>
</p>





> #### [Analysis Output](./ANTLR-Project/Visited-Blocks.txt)

<p align="center">
  <img src="https://github.com/RamziMuhammad/ANTLR-Project/blob/main/Assets/Visited-Blocks.png" style="width:800px;"/>
</p>





------

### Team Members

This Project was created due to the efforts of all the team members and their indescribable hard work.

<table>
  <tr>
    <td align="center"><a href="https://github.com/RamziMuhammad"><img src="https://avatars.githubusercontent.com/u/66510024?v=4" width="200px;" alt=""/><br /><sub><b><center>Ramzi Muhammad</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/Abdelrahman-Atef-Elsayed"><img src="https://avatars.githubusercontent.com/u/66162676?v=4" width="200px;" alt=""/><br /><sub><b><center>AbdElRahman Atef</b></sub></a><br /></td>
   <td align="center"><a href="https://github.com/Ola-Mohamed"><img src="https://avatars.githubusercontent.com/u/66176966?v=4" width="200px;" alt=""/><br /><sub><b><center>Ola Mohamed</b></sub></a><br /></td>
   <td align="center"><a href="https://github.com/mohamedsalah674"><img src="https://avatars.githubusercontent.com/u/66376551?v=4" width="200px;" alt=""/><br /><sub><b><center>Mohamed Salah</b></sub></a><br /></td>
   <td align="center"><a href="https://github.com/mohamedmahfouz3"><img src="https://avatars.githubusercontent.com/u/66581191?v=4" width="200px;" alt=""/><br /><sub><b><center>Mohamed Mahfouz</b></sub></a><br /></td>
    </tr>
</table>