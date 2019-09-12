# Assignment: Package Challenge

# How to use
The project is used as a library that should be import in a project.
There is a class **Packer** with a method **pack** that receives a path to a file that contains several lines. The file will be validated and will throw **APIException** if incorrect parameters are being passed.

# project structur
The project was inspirate on the clean architecture (https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) and is split in four packages:

 - *entities*: representing the bussines rules, as constrains like maximum value of cost item.
 - *exception*: representing an error when incorrect parameters are being passed.
 - *packer*: convert data from external agents to use cases and convert back to return.
 - *usecases*: representing the bussines rules of the application like read a file, process the file, transform text file to object.

# Process file
First the project create a list of object that represents the file received and then create another list that was filtered and sorted with controls using the Big-O (log n) complexity and then go through this list created to return the indexes can be inside the package received.
