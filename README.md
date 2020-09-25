#### Rest API for TODO Items
___

#### How to use    
Run the application by running Main. Use Postman to interact with the application.   

To **post** a new TODO item provide a JSON object like `{ "summary": "Some summary", "description": "a description"}` 
and post it to `localhost:4567/`.    

To **put** a TODO item already in the app provide a JSON object with a matching UUID to the item you are updating
  `{ "id": "1234-1234-1234-1234", "summary": "Updated summary", "description": "Updated Description"}` to  `localhost:4567/`.     
  
To **get** a specific TODO item give the UUID in the path: `localhost:4567/1234-1234-1234-1234` where 1234-1234-1234-1234 is the UUID.   

To **get** all the TODOs query `localhost:4567/allTodos`.   

To **delete** a TODO give the UUID in the path `localhost:4567/1234-1234-1234-1234` where 1234-1234-1234-1234 is the UUID.         




#### Example   

Insert a new item   
![POST](Screenshots/POST.png?raw=true)   

Get the item   
![GET](Screenshots/Get.png?raw=true)   

Update a item   
![PUT](Screenshots/Update.png?raw=true)   

Get all todos   
![GET](Screenshots/AllTodosBeforeDel.png?raw=true)   

Delete a todo   
![DELETE](Screenshots/Delete.png?raw=true)   

Get all todos after deleting   
![GET](Screenshots/TodosAfterDel.png?raw=true)   