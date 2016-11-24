var express= require('express');
var mysql = require("mysql");
var router = express.Router();

var connection = mysql.createConnection({
	host : 'localhost',
	user : 'root',
	password : 'admin',
	database : 'mytest'

});

connection.connect();
router.get('/',function(req,res){
		console.log("Got get Request in Router addUser"+req.query.gender);
		var name = '"male"';
		var query = connection.query('select * from users2 where Gender = '+name,function(err,result){
			if(err){
				console.error("One error Found" +err);
				return;
			}
			console.error(result);
			res.json(result);
		});
		
	});
	
	router.get('/txn',function(req,res){
		console.log("Starting transaction");
		var user2 = {
			firstname : 'Raju5',
			lastName : 'KK',
			gender : 'male'
		};
		
		var user3 = {
			firstname : 'Raju4',
			lastName : 'KK',
			gender : 'male'
		};
		var query1 = "insert into users2 set";
		var query2 = "update last_update set date=now()";
		var name = '"male"';
		connection.beginTransaction(function(err) {
		 if (err) {
            throw err;
        } 
		connection.query('insert into users2 set?',user2,function(err,result){
			if(err){
				console.error("One error Found" +err);
				return;
			}
			console.error(result);
		});
		connection.query('insert into users2 set?',user3,function(err,result){
		if(err){
				console.error("One error Found" +err);
				return;
			}
			console.error(result);
		});
		});
	});

module.exports =router;
console.log("Welcome to mysqlquery");