import { Component } from '@angular/core';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'my-app',
  template: `<div id="formInput" style="background-color: #ffcccc">
	<p>
		<font size="8" color="green">ADD NEW EMPLOYEE </font>
	</p>
	<form id="register" name="form" method="POST" class="form-horizontal">
			<font size="5" color="green">First name:</font><br> <input
			type="text" class="form-control txtbox" name="firstname" id="fname1" [value]="fnameVal" #fnameInput> <br> 
			<font size="5" color="green">Last
			name:</font><br> <input type="text" class="form-control txtbox"
			name="lastname" [value]="lnameVal" #lastInput> <br> <font size="5"
			color="green">Gender:</font><br> <input type="radio"
			name="gender" value="male"
			#maleInput> <font size="4" color="#000000">Male</font>
		<input type="radio" name="gender" value="female"
			#femaleInput> <font
			size="4" color="#000000">Female</font> <br>
	</form>
	<button class="btn-clear" (click)=addUser(fnameInput,lastInput,maleInput,femaleInput)>Submit</button>
</div>`
})
export class AddComponent { 

	fnameVal  = "";
	lnameVal  = "";
	gen = "";

	constructor(private employeeService: EmployeeService) { }

	addUser(fnameInput,lastInput,maleInput,femaleInput){
		console.log("-------------AddUser btn CLICKED----------------");
		if(maleInput.checked == true){
			this.gen = "male";
		}else if(femaleInput.checked == true){
			this.gen = "female";
		}
		console.log("gen value is "+this.gen);
		this.employeeService
        .addEmployee(fnameInput.value,lastInput.value,this.gen);
	}
	
}
