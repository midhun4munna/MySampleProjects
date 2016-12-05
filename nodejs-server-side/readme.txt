----------------------------How to put angular 2 files in Nodejs Server Side----------------------------
step1.  intstall nodejs
step2:  create a folder angular2withNode and copy(nodejs server app) from https://github.com/trainingcode/Angular2-week2-backend/tree/Week2_Assignment/Week2_Assignment to this folder.
step3:  get a cmd prompt and navigate to angular2withNode folder.
step4: type:- npm install body-parser ejs express mongojs     (this will download dependencies for nodejs)
step5: create 2 folders, views and client inside angular2withNode folder.
step6: place your index.html in views folder.
step7: go to client folder put our angular2 code here.(Includes: package.json,systemjs.config.js,tsconfig.json and app folder with typescrip files)
step8: cmd prompt and navigate to angular2withNode/client.
step9: type:- npm install  (this will download all angular dependencies)
step10: type: npm start    (this will compile and create js file) 
step11: open a new cmd prompt and navigate to angular2withNode
step12: type:- node main.js   (this will start your application)
step13: in Browser type url  http://localhost:8888/   (this will launch your app)

-----------------------------How to call nodejs services---------------------------------------
For getAll Employee details: use get method with url   (  "/getEmployee"  )
For add new Employee detail:  use post method with url   (  "/addEmployee")           POST sample JSON:{"firstname":"John","lastname":"Chacko","gender":"male"};
For update Employee detail:   use put method with url   ( "/updateEmployee/Raju" )    sample JSON:{"firstname":"John","lastname":"C","gender":"male"}; -- do not update firstname
For delete Employee detail:   use delete method with url   (  "/removeEmployee/"+ Raju " )



Ref:- https://www.youtube.com/watch?v=PFP0oXNNveg

