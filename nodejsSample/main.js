var http = require("http");
var express = require("express");
var mongojs = require("mongojs");
var mysqlquery = require("./mysqlquery.js");

var db = mongojs('mytest',['User']);

var app = express();

app.use('/getUsers', mysqlquery);
app.use(express.static(__dirname + "/"));

app.get('/registerUser',function(req,res){
	console.log("Got get Request111");
		db.User.find(function(err,docs){
		res.json(docs);
	});
});

app.get('/addNewUser',function(req,res){
		console.log("Got get Request 222"+req.query.gender);
		db.User.insert({firstName:req.query.firstname,lastName:req.query.lastname,gender:req.query.gender},function(err,docs){
		db.User.find(function(err,docs){
		res.json(docs);
	});
	});
});

app.delete('/removeUser/:firstName',function(req,res){
	console.log("Got get removeUser "+req.params.firstName);
		db.User.remove({firstName :req.params.firstName},function(err,docs){
		db.User.find(function(err,docs){
		res.json(docs);
	});
	});
});

app.get('/updateUser',function(req,res){
	console.log("Got get updateUser "+req.query.firstname);
		db.User.update({firstName: req.query.firstname},{$set: {firstName:req.query.firstname,lastName:req.query.lastname,gender:req.query.gender}},
		function(err, object) {
      if (err){
          console.log(err.message);  
      }else{
			db.User.find(function(err,docs){
			res.json(docs);
	});
      }
  });
});

app.listen(8888);
console.log("Welcome to Node");