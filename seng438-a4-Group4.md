**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:      | 4               |
| -------------- | --------------- |
| Student Names: | Rayyan Khalil   |
|                | Ammar Elzeftawy |
|                | David San Kim   |
|                | Eduardo Benetti |

# Introduction

The primary goal of this lab is provide a complete understanding about two essential techniques for software testing: Mutation and GUI Testing. Firslty, this report will cover how mutation was initially covered on the material that the team created in Assignment 3, followed by an analysis about 10 functions that the team selected to describe how mutation affected its testing. After analyzing the current coverage the program had, the team tried in many ways to increase its score by creating different JUnit tests to "kill" the mutants, using the PiTest library. On the other hand, the second portion of this lab report, we conducted a GUI analysis using Selenium, a very well known web interface tool to test and compare different outcomes of web pages. Finally, we described how this process went, and the series of challenges and knowledge the team acquired through out the process.

# Analysis of 10 Mutants of the Range class

RANGE CLASS

1. **Negated double local variable number 1 → SURVIVED** --> intersects() (161)  
   This function is currently surviving the mutation testing because it is negating the double b1 argument. With the second function input negated, negative numbers will become positive and positive will become negative. When reacing line 161, where it compares b1 >= b0 it will not make any sense given that the upper bound will always be a number smaller than the lower bound. Since no test case was built for this type of input, it is not being covered, leading to the mutation survival.
   ![image](https://user-images.githubusercontent.com/30624408/225460194-b1fa02b8-4571-4023-a036-22ed2a4b03af.png)

2. **Incremented (a++) double local variable number 1 → SURVIVED** --> Range() (95)  
   This mutation was created inside the Range constructor, where input information is being validated before the creation of the object. At the very end of the constructor, it has the following line of code: _this.lower = lower;_ where it is intended to set the _this_ object into the argument _lower_. However, the implemented mutation change into the following line: _this.lower = lower++;_. In this case, it survives all the test since none of them verifies if the input variable is the same as the object's lower bound
   ![image](https://user-images.githubusercontent.com/30624408/225460277-d48c1e5c-ce6b-45d7-83f6-a54f3d3e5cdf.png)

3. **Decremented (a--) double local variable number 1 → SURVIVED** --> Range() (95)  
   Similar to the previous, inside the Range constructor, the mutation is now decreasing the value of the lower bound object. As expected, there is no JUnit test checking if the information that was passed by argument (as the Range's lower bound attribute) is actually the one being created ( _this.lower = lower--;_ ). With that said, the mutation is surving even with the creation of a Range with a value lower different than the one passed as argument of the constructor.
   ![image](https://user-images.githubusercontent.com/30624408/225460301-da2ce2eb-e8e5-44dc-b1f9-c59c97b4e688.png)

4. **Incremented (a++) integer local variable number 3 → SURVIVED** --> hashCode() (464)  
   Similar to the Range constructor, variable _temp_ was increased by the value of 1. Yet, it differs from the constructor case since this variable was only used locally by that function and not returned, making a harder to write JUnit tests to kill that mutant. The variable was then used to create the result variable (afterwards returned), but since it goes through calculation and then casted back into integer format, the small increase in temp might not be enough to change the final value of _result_. As a consequence of that, no written JUnit was able to kill that mutant, make it survive during the testing process _RangeTest.java_.
   ![image](https://user-images.githubusercontent.com/30624408/225469538-b15e2701-1e1c-4820-8ef2-fe6f21c7355e.png)

5. **Decremented (a--) integer local variable number 3 → SURVIVED** --> hashCode() (464)  
   Once again, this mutant was created on the temp variable used in the the hashCode function, yet since it was not returned and only used locally, it made it complex to test for mutants. Since it being changed within the function with the following line of code: _temp = Double.doubleToLongBits(this.lower);_ , reducing its value by 1, was not enough to change the value of _results_ (being returned by the function). With that said, none of the written JUnit tests were able to spot that mutant, therefore surviving.
   ![image](https://user-images.githubusercontent.com/30624408/225461497-ace38494-99d0-4aef-876e-2227ef287709.png)

6. **Incremented (++a) double local variable number 3 → KILLED** --> Range() (96)
   This mutation also took place in the Range constructors, yet since its doing the calculation before the variable being called, it generates different bahavior which was caught by the JUnits tests. On line 96: _this.upper = upper;_ it was created a mutant, changing it to: _this.upper = ++upper_. But on the designed JUnits tests, we asserted the correct range was created by testing the upper and lower bounds of the Range object. As a consequence of that, the tests are able to spot the mutant and kill it.  
   ![image](https://user-images.githubusercontent.com/30624408/225461638-59994605-7b4b-49e7-a616-e1cf3162dda8.png)

7. **Replaced double subtraction with division → KILLED** --> getLength() (123)  
   In the getLength function, the program should return the subtraction between the upper and lower bounds of the Range object. However, the mutant created is changing _return this.upper - this.lower;_ into a division: _return this.upper/this.lower;_ . This logic will certainly not return the same value in most of the cases. We wrote JUnit tests to test out the length of smaller, bigger and 0 size Ranges, therefore the test suite was able to spot the mutant and kill it accordingly.  
   ![image](https://user-images.githubusercontent.com/30624408/225466272-48a92005-6b61-4ae8-af63-c0f856adb8d1.png)

8. **Replaced return value with null for org/jfree/data/Range::expandToInclude → KILLED** --> expandToInclude() (312)  
   In the line 312 of Range.java, in function expandToInclude, the program is returning the Range object that was passed as an argument. Yet with the creation of the mutant, that same line went from: _return range;_ to _return null_ . In order to spot that mutant, the team created during assignment 3 a JUnit test to check for null returns, since that should not happen given no Range object should be passed as an argument for that function. As a result, the function JUnit was marked as a Killed, since we were able to spot the mutant  
   ![image](https://user-images.githubusercontent.com/30624408/225467183-da2bbc7c-ce23-4aad-ab4a-e0321014c966.png)

9. **Incremented (++a) double field upper → KILLED** --> getUpperBound() (114)  
   Inside the getUpperBound (which should serve as a simples getter function), the function should return the value of the upper bound. On line 114, the function is simply returning it, with the following command: _return this.upper;_ , yet with the addition of the mutant, it transforms into the following line: _return ++this.upper;_ . In this second case, we are firstly incrementing the variable, then retuning it to where it was called. With that said, the mutant will return a upper bound greater than its actual value, which was checked and tested on the test suit by checking if the create Range had exactly the same value for both bounds. With that said, the test suit was able to spot the mutant, and kill it  
   ![image](https://user-images.githubusercontent.com/30624408/225469868-4156e69d-71db-4d86-8c30-0a952032d8da.png)

10. **Substituted 2.0 with 1.0 → KILLED** --> getCentralValue() (132)  
    On function getCentralValue, it should return the value between the two bounds of the Range object. For that to work, the class is dividing both bounds by 2.0 ( _return this.lower / 2.0 + this.upper / 2.0;_ ). Yet with the creation of the mutant, it is now dividing it by 1.0 ( _return this.lower / 1.0 + this.upper / 1.0;_ ), therefore returning the value of the bounds, rather than its midpoint. The team created in assignment 3 a JUnit test to spot this mutant, given it would only require to check if the values passed to the function is actually the midpoint for any Range where lower bound is not equal to upper bound. In that case, there should always be a midpoint. As a result, the JUnit test suite was able to spot the mutant and kill it accordingly.  
    ![image](https://user-images.githubusercontent.com/30624408/225478565-d5627d97-f6c6-4fcf-9c99-16510dfdfc6c.png)

# Report all the statistics and the mutation score for each test class

**Original test scores:** Range.java mutation score of 63% and DataUtilities.java mutation score of 31%
![c547d6c3-ccc5-4d85-825c-2efd5bb0a982](https://user-images.githubusercontent.com/90352983/225778317-151cb5ec-0076-4ca2-8ac5-b4a5ed134007.jpg)
![dd4a94c6-6900-4749-a1ea-0492e8dfe45a](https://user-images.githubusercontent.com/90352983/225778326-ec4f6e0d-b4d9-45e1-8336-08be0f7172d7.jpg)

**After mutation tests:** Range.java mutation score of 83% and DataUtilities.java mutation score of 74%
![1c84dce5-ddd9-48d2-b52c-f2822d9222af](https://user-images.githubusercontent.com/90352983/225778329-5508a84f-f227-42de-9d8a-b68f25b5217c.jpg)
![c67d8bbc-5627-4828-8afd-7f8533993c46](https://user-images.githubusercontent.com/90352983/225778342-6095eb85-13b3-4cea-ae4d-a4d020ec08e9.jpg)

**Example of test added to increase mutation score in Range.java**

shiftWithNoZeroCrossingWhereValueEqualsZero()
This test was create to check the shift(Range base, double delta, boolean allowZeroCrossing) method. First, a Range object with a range from 0 to 0 was created and this object called the shift method with delta being a shift of 11, and with allowZeroCrossing being false, making the helper method shiftWithNoZeroCrossing(double value, double delta) be called. To test the shift method, assertEquals() was called to make suze our range from 0 to 0 increased its upper bound to 11. This test case killed several mutants since our test suite from Assignment 3 did not cover most of the shiftWithNoZeroCrossing method including when the Range object is 0, increasing our overall mutation score for Range.

**Example of test added to increase mutation score in DataUtilities.java**

testEqualArrayNull()
This test was created to check the specific condition inside the equal(double[][] a, double[][] b) method in which the b is null. For the test, two arrays were created, array1 with values and array2 being null. This test was able to kill several mutations since in Assignment 3, the tests our group had did not cover the specific condition where b was null, increasing the overall mutation score for Data Utilities.

Total of tests added on RangeTest.java: 8
Total of tests added on DataUtilities.java: 13

_OBS: The team did not include all the test cases created because there is a lot of them, please refer to the DataUtilitiesTest.java and RangeTest.java under Part 1 folder_

# Analysis drawn on the effectiveness of each of the test classes

The result of our mutation testing show a significant increase in effectiveness for both Range and DataUtilities Classes. The mutation coverage score for the Range class increased by 20%, while the mutation coverage score increased a total of 43%. The results demonstrate the success of the lab, as our tests exceed the required 10% increase specificed at the markdown file. These improvements in mutation testing helps to identify potential defect and issues within the code, which ultimately leads to a more robust and realiable software system. It is also important to note that as the coverage of our mutation testing increases, the mutations become harder to detect. While we have achieved our goal, there is still room for improvement as there are 218 mutations for Range and 182 mutations for DataUtilities left in our system. As we continue to refine our testing methods and techniques, we are able to work towards identifying and addresing even more subtle defects within the code, which increases the overall quality of the software system.

# A discussion on the effect of equivalent mutants on mutation score accuracy

Equivalent mutants are very important for a complete analysis of the testing process of a software. Fundamentally, equivalent mutants are syntactically different but semantically equivalent to the original code. Meaning that, it is possible to create mutants that follow the same exact logic even not being written the same way, by changing inequality signs or with simple mathematical computations. Since mutations are used to simulate possible human errors or simple logical issues, using equivalent mutants will give the developer further information about the quality of his or her test suit. With that, they should be able to spot weaknesses in the unit test and consequently fix them to have a higher-quality final product.  
On the other hand, that mutation score is a simple ratio of the mutations that were implemented in the code and the amounts there were spotted by the test suite. For example, with the creation of 10 mutants (either by the developer or tools like PiTest) where 6 of them are "killed" or tested along the testing process, this would lead to a 6/10 or 60% mutation score. However, as a tradeoff equivalent mutants can inflate or reduce the accuracy of the testing suite if used incorrectly. Since equivalent mutants are different ways of following the same logic in the code, they should be carefully used. Going back to the previous example, lets suppose that all the 10 mutations are equivalent, then 1 test case would be enough to achieve a 100% mutation score, which might represent an extremelly high score and a very well test software. But in reality, not all possible routes were tested and the software is not ready to go into production. Similarly, if all mutations are equivalent and the test suite does not cover the mutations, it can lead to 0% mutation score. To conclude, equivalent mutations are useful tools to test the quality of the current mutations that were implemented, yet it has some drawbacks which should be taken into consideration as it can lead to innacurate mutation scores.

To conclude, the following max function is an example of equivalent mutants:

public int max(int a, int b) {  
 if (a > b) {  
 return a; }  
 else {  
 return b; }  
}

public int max(int a, int b) {  
 if (a <= b) {  
 return a; }  
 else {  
 return b; }  
}

Even been written in a different ways (their if statement conditions) they will always have the same outputs since their logic is the same. However, it can alter the overall mutation score of the system.

# A discussion of what could have been done to improve the mutation score of the test suites

At first, it was challenging to increase the mutation coverage of the system given that the combination of the given JUnit test and the ones created on the previous assignment, the team reached a high mutation score around the 70% mark on both files. Nevertheless, the team went through the PiTest generated summary to analyze exactly which mutants survived the test and based on them we conducted an analysis on how to tackle each. It was certainly a very long process since we had to review one by one. However, the team soon realized that some times, creating a test case would lead to killing more than one mutant, therefore increasing the overall mutation score. In addition to that, we began to focus on functions with less coverage since our chances of increasing the overall mutation score would be higher on those. During this process we were also able to increase it into acceptable scores. In most cases, mutants are very simple changes that we sometimes forget to check, such as a inequality mistake on a if statement condition, yet its not complex to ensure that the formula's outputs are correct in those cases. Lastly, it was challenging to understand some of the messages displayed by PiTest, takign us to their documentation which was not entirely easy to understand, making us sometimes move on without tackling that mutant.

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

Mutation testing is a form of white box testing where the testers are able of closely analyse the components of the program. The main purpose of implementing mutation testing is to increase the effectiveness of the testing process and consequently the quality of the software. With small changes in the source code, we able to analyze the effectivess and coverage of the JUnit tests designed for the program. A high mutation score would represent a very effective test suite, meaning that the software would be covering a large possibilities of inputs and therefore being effectively tested.

ADVANTAGES:

- Ability to ensure the quality of the test suite, and overall source code
- Mutation score give a detailed breakdown of which types of inputs are not being hadled by the test suit
- Early detection of errors to avoid getting into the production phase

DISADVANTAGES:

- Overall a very time consuming process, since its very detailed process, it takes time to review the series of test performed on a software
- Equivalent mutation can cause a series of misunderstading during the testing process
- A very overwhelming process in general due to the huge amount of testing and data that has to extracted in order to turn into useful insights about the program

# Explain your SELENUIM test case design process

The team chose Amazon page to test using Selenium. Firstly, the team tried to login with invalid email, then with invalid password, then with valid email and password in order to test all possible data inputs. Afterwards, the team conducted some testing by searching for valid and invalid items on their platform. Following that testing, the team began to add and remove items from the shopping cart to identify how this was taking place within their system and if there were any faults. In order to enhance that testing, the team created wish lists, which should serve as a list of items users are interested in and wants to save it for later. For a platform as Amazon, the team expected all tests to pass. However when removing some tests as we only needed 8 and some test cases we not performing as expected some of times (we are assuming it's a problem in the software or the laptop performing the process). A readme file was uploaded to navigate through the test cases and further explain the results.

# Explain the use of assertions and checkpoints

We didn't use assertions and checkpoints as Selenium IDE is already tracking the statement it was excuting, it puts a check mark beside each statement that was excuted. Also because the test files were saved in HTML format we didn't have to use them. Assertions and Checkpoints are essential tools for effectively using Selenium. On one hand, assertions allow developers to verify expected outputs by comparing the actual results of a test case with what was expected by the system. This helps to detect issues or bugs in the application code. Selenium's Assert class provides various methods such as assertEquals(), assertTrue(), and assertFalse() to implement assertions. This allows the users (in this case developers) to test out a variety of inptus and forms to test their applications. On the other hand, checkpoints verify the current state of a web page or application during test execution. They are used to verify web page properties such as the presence of a specific web element, the value of a field, or the URL of a web page. WebDriver class methods, including getTitle(), getCurrentUrl(), and getText(), can be used to implement checkpoints. In summary, assertions and checkpoints help ensure reliable and accurate web application testing and assist in identifying and resolving application issues very effectively.

# How did you test each functionality with different test data

For some functionalities (i.e adding items to a wishlist or removing it from the list) it was not possible to test with different test data as there was only one action to be performed with no other altenatives such as SignOut function. On the other hand, some functionalities were possible to be throughly tested. For login functionality for example, the team tested it using invalid email, invalid password, valid email and password. Similarly, for searching for items in the cart, the team tried searching for valid items where it gives us results, and invalid items where it displays no results, as expected.

# Discuss advantages and disadvantages of Selenium vs. Sikulix

When it comes to automated testing tools, Selenium and SikuliX are two popular options, but they have different strengths and weaknesses depending on the type of application being tested. Based on our research, Selenium has a large community and can be used with multiple programming languages, making it versatile and useful for testing web applications across different platforms and browsers. However, it has a steep learning curve, can be slow and brittle, and is limited in testing capabilities for non-web-based applications. On the other hand, SikuliX uses image recognition to interact with the graphical user interface, making it useful for testing desktop applications or those with a custom GUI. It's easy to create and maintain scripts and can be used across different operating systems, but it's not suitable for web-based applications, and image recognition can sometimes lead to inaccuracies and slower testing times. Ultimately, the choice between the two depends on the specific testing requirements, with Selenium being a good choice for web-based applications and SikuliX for desktop applications with complex GUIs.

# How the team work/effort was divided and managed

For simplicity we divided the team based on the past constributions of of the Range and Data Utilities class for the first part of the assignment. With that said, the two pairs created different test cases aiming to increase the mutation coverage of both classes. Similar to the previous assignment, this method worked really well, leading to effective contributions of all team members, learning a lot from each other through out the lab assignment. On the second part of the assignment, using Selenium, we worked on it together since it was a shorter task, having the opportunity to discusss different ideas about the steps of the process to make it more effective. Overall it was very successful process, were we had the involvement of all memebrs to share ideas and collaborate in different manners.

# Difficulties encountered, challenges overcome, and lessons learned

One of the difficulties we faced was setting up the environment for part 1 as we were not sure where to import the test files. This took us some time to have it working before actually beginning the process of runningn PiTest and recording our results. In addition to the setup process, the team encountered some challenges to increase the mutation coverage of the Range and DataUtilities class. After assignment 2 and 3, the JUnit tests had a very high coverage in comparison to other project, which made it hard to reach a desired score.
with regards to the second part of the assignment, the team faced was working with Selenium, for some of the test cases it waited for the user to hover the mouse to a specific place in order to continue with the process, we believe that this might be an issue with the software or the laptop that's performing the process as we repeated the test cases more than once and it still did the same action. Lessons learned was working better as a group, communicating better, and finishing things as early as possible in order to have time to disucss and review any missing portion of the lab assignment.

# Comments/feedback on the lab itself

Overall it was an interesting lab to get to apply mutation testing techniques, rather than just learning from the slides. However, after talking to members of the industry community, many employees mentioned that mutation is not very common on the day to day of software engineers, therefore it might not be very useful for the future students. Perhaps, new tools with similar functionalities as PiTest should be used for students to get a hands on experience with the testing techniques. With regards to the lab instructions, I believe they could be more well explained, maybe a video to get the lab assignment correctly setup would be very useful, rather than trying different ways before actually getting it to work. Many students are not very familiar with Eclipse IDE, making it hard to install all the plugins and dependencies required for this projects.
