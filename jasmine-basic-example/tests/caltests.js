describe("Calculator test", function() {
   
   describe("Add test", function() {
        it("should be able to calculate sum of 3 and 6", function() {
			expect(calc.add(3,6)).toEqual(9);
        });
		
		it("should be able to sub of 6 and 3", function() {
			expect(calc.sub(6,3)).toEqual(3);
        });
   });
   
});