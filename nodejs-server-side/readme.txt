----------------------------How to put angular 2 files in Nodejs Server Side----------------------------
step1.  intstall nodejs
step2:  create a folder angular2withNode and copy(nodejs server app) from  https://github.com/midhun4munna/MySampleProjects/tree/master/nodejs-server-side to this folder.
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
For getAll Employee details: use get function with url   (  "/getEmployee"  )
For add new Employee detail:  use get function with url   (  "/addEmployee?firstname=" + Raju + "&lastname=" + M + "&gender="+male  )
For update Employee detail:   use get function with url   ( "/updateEmployee?firstname=" + Raju + "&lastname=" + Ram + "&gender="+male   )
For delete Employee detail:   use delete function with url   (  "/removeEmployee/"+ Raju " )



Ref:- https://www.youtube.com/watch?v=PFP0oXNNveg
