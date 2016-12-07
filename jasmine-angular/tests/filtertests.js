describe('Angular test', function(){ //describe your object type
    beforeEach(module('MyApp')); //load module
	
    describe('Filter Test',function(){ //describe your app name
        var rev;
        beforeEach(inject(function($filter){ //initialize your filter
            rev = $filter('reverse',{});
        }));
        it('Should reverse a string', function(){  //write tests
			
            expect(rev('rahil')).toBe('lihar'); //pass
            expect(rev('don')).toBe('nod'); //pass
			expect(rev('don')).toBe('nod'); //pass
            //expect(reverse('jam')).toBe('oops'); // this test should fail
        });
    });
	
	describe('Controller Test',function(){
        var contrlr;
        beforeEach(inject(function($controller){
            contrlr = $controller;
        }));
		
        it('1 + 1 should equal 2 ', function(){ 
			var scp = {};
			var controller = contrlr('CalculatorContoller',{$scope:scp});
			scp.num1 = 1;
			scp.num2 = 2;
			scp.add();
            expect(scp.sum).toBe(3);
        });
		
		it('4 - 1 should equal 3 ', function(){ 
			var scp = {};
			var controller = contrlr('CalculatorContoller',{$scope:scp});
			expect(controller.sub(4,1)).toBe(3);
        });
    });	
	
	describe('Service Test',function(){
        var data = [{"Id":"1","Name":"Sachin"},{"Id":"2","Name":"Kohli"}];
		var playerSer = {};
        beforeEach(inject(function(playerService){
            playerSer = playerService;
        }));
		
        it('should return seach player data', function(){ 
            expect(playerSer.getPlayers()).toEqual(data);
        });
		
    });
	
	describe('Http Mock Test',function(){
		var sampleSer, httpBackend;
        beforeEach(inject(function($httpBackend,SampleService){
            sampleSer = SampleService;
			httpBackend = $httpBackend;
			console.log(httpBackend);
        }));
		
        it('http mocking', function(){ 
			var returnData = {"Id":"1","Name":"Sachin"};
			var sampledata = {"Id":"2","Name":"Kohli"};
			httpBackend.expectGET("http://localhost/8888/players").respond(returnData);
			var returnedPromise = sampleSer.getPlayers();
			var result;
			returnedPromise.then(function(response){
				result = response.data;
				console.log("Result is "+result);
			});
			httpBackend.flush();
            expect(result).toEqual(sampledata);
        });
		
    });
});