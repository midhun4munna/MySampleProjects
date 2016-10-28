var http = require("http");
var express = require("express");
var mongojs = require("mongojs");
var db = mongojs('mytest',['User']);

var app = express();


app.use(express.static(__dirname + "/"));

app.get('/registerUser',function(req,res){
	console.log("Got get Request");
		db.User.find(function(err,docs){
		res.json(docs);
	});
});

app.get('/addNewUser',function(req,res){
		console.log("Got get Request"+req.query.gender);
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