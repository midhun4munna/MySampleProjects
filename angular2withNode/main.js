var http = require("http");
var express = require("express");
var mongojs = require("mongojs");
var path = require('path');

var db = mongojs('mytest',['Employee']);

var app = express();

app.set('views',path.join(__dirname + "/views"));
app.set('view engine','ejs');
app.engine('html',require('ejs').renderFile);

app.use(express.static(path.join(__dirname + "/client")));

app.get('/',function(req,res,next){
	console.log("Got get Request111");
	res.render("index.html");
	});

app.get('/getEmployee',function(req,res){
	console.log("Got get Employee Request111 ");
		db.Employee.find(function(err,docs){
		res.json(docs);
	});
});

app.get('/addNewEmployee',function(req,res){
		console.log("Got get Request 222"+req.query.gender);
		db.Employee.insert({firstName:req.query.firstname,lastName:req.query.lastname,gender:req.query.gender},function(err,docs){
		db.Employee.find(function(err,docs){
		res.json(docs);
	});
	});
});

app.delete('/removeEmployee/:firstName',function(req,res){
	console.log("Got get removeEmployee "+req.params.firstName);
		db.Employee.remove({firstName :req.params.firstName},function(err,docs){
		db.Employee.find(function(err,docs){
		res.json(docs);
	});
	});
});

app.get('/updateEmployee',function(req,res){
	console.log("Got get updateEmployee "+req.query.firstname);
		db.Employee.update({firstName: req.query.firstname},{$set: {firstName:req.query.firstname,lastName:req.query.lastname,gender:req.query.gender}},
		function(err, object) {
      if (err){
          console.log(err.message);  
      }else{
			db.Employee.find(function(err,docs){
			res.json(docs);
	});
      }
  });
});

app.listen(8888);
console.log("Welcome to Node");