# Assignment: Package Challenge

# How to use
The project receives a path to a file that contains several lines

# project structur
The project was inspirate on the clean architecture (https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) and is split in four packages:

 - entities: representing the bussines rules, as constrains like maximum value of cost item.
 - exception: representing an error when incorrect parameters are being passed.
 - packer: convert data from external agents to use cases and convert back to return.
 - usecases: representing the bussines rules of the application like read a file, process the file, transform text file to object.

# Process file

First the project create a list of object that represents the file received and then create another list that was filtered and sorted with contrains using the Big-O(log n) complexity and then percorre this list created to return witch indexes can be inside the package


