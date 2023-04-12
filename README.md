# Session -5 Assignment 1
## Average Calculator by Future

**Step 1:** Open IntelliJ and create scala files in **assignment-1/src/main/scala/com/knoldus**     
**Step 2:** After that create four scala file   
1. AverageCalculator.scala    
2. Driver.scala   
3. StudentDetails.csv  
4. FileToCheck.scv

**Step 3:** Inside **AverageCalculator.Scala** create a class of name AverageCalculator and inside it define four methods:  
1. **parseCsv :** This method contain a single parameter of csv file path and inside it, we open the file and fetch the studentDetails. After that we fetch the keys and assign the value inside the map.
2. **calculatorStudentAverage :** This method takes the return type of parseCsv, and it is used to calculate the average of student by using map(higher order function). After that calculate the sum of list of marks of each student and pass the tuple (id, average)  
3. **calculateClassAverage :** This method takes return type of calculateStudentAverage, and it is used to find the average of the class. Again in this we have to traverse the data by using map and fetch the studentAverage and calculate the average of class.
4. **calculateGrades:** It takes single parameter of type string (file path) and inside this method we call all three other method inside the for comprehension and in yield return classAverage. Also we can use Await to wait for result from future.
>To handle future if future failed
>recoverWith { case exception =>
>Future.failed(exception)

**Step 4:** Now, create Driver.scala class and inside it create an **Driver Object** and call the calculateGrades method by creating object of Average Calculator and print the average of class by using onComplete method of Future.
>>  result.onComplete {
>>case Success(value) => println(s"Class average: $value")
>>case Failure(exception) => println(exception)
>>}   

**Step 5:** Now create **StudentDetails.csv** file inside this it contains the student details  
**Step 6:** Now create **one more csv file** only **to check if the data is not** there it is handling error or not.
**Step 7:** Inside the build.sbt install libraries for csv file, **scalatest to test with the code** and Sync the project.
**Step 8:** Create a Test Class of name AverageCalculator.scala inside this import the AnyFlatSpec class with Matchers and test with different-different scenario if path is not there, if empty path is passed etc.
**Step 9:** Test the test case by using **scala test.**

**Output:**
**sbt compile & sbt run**
![Screenshot from 2023-04-12 13-42-46](https://user-images.githubusercontent.com/124979629/231395465-7c65d94f-5182-4b51-bfe2-06734166f640.png)

**sbt test**
![Screenshot from 2023-04-12 13-42-54](https://user-images.githubusercontent.com/124979629/231395512-ed5b4dce-1d87-4b9a-882f-b94fe37c26b2.png)

