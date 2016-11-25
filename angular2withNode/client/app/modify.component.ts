import { Component,OnInit } from '@angular/core';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'my-app',
  template: `
  <div id="formInput" style="background-color: #ecb3ff">
	<p>
		<font size="8" color="red">MODIFY EMPLOYEE </font>
	</p>
	<form id="register" name="form" method="POST" class="form-horizontal">
			<font size="5" color="red">First name:</font><br> <input
			type="text" class="form-control txtbox" name="firstname" id="fname1" [value]="fnameVal" #fnameInput> <br> 
			<font size="5" color="red">Last
			name:</font><br> <input type="text" class="form-control txtbox"
			name="lastname" [value]="lnameVal" #lastInput> <br> <font size="5"
			color="red">Gender:</font><br> <input type="radio"
			name="gender" value="male"
			#maleInput> <font size="4" color="#000000">Male</font>
		<input type="radio" name="gender" value="female"
			#femaleInput> <font
			size="4" color="#000000">Female</font> <br>
	</form>
	<button class="btn-clear" (click)=updateUser(fnameInput,lastInput,maleInput,femaleInput)>Submit</button>
</div>
  `
})
export class ModifyComponent implements OnInit{
	fnameVal ;
	lnameVal ;
	emp ;
	gen ;
	
	constructor(private employeeService: EmployeeService) {
	employeeService.employeeUpdate1$.subscribe(
		(emp) => { 
		console.log("--------Got subscription in ModifyComponent---------"+emp);
		this.fnameVal = emp.firstname;
		this.lnameVal = emp.lastname;
	});
	}
	
	updateUser(fnameInput,lastInput,maleInput,femaleInput){
		console.log("-------------Update User Called----------------");
		if(maleInput.checked == true){
			this.gen = "male";
		}else if(femaleInput.checked == true){
			this.gen = "female";
		}
		this.employeeService
        .updateEmployee(fnameInput.value,lastInput.value,this.gen);
	}
	
	 ngOnInit(){
		console.log("-------------OnInit  ModifyComponent ----------------");
		this.emp = this.employeeService.getEmp();
		this.fnameVal = this.emp.firstname;
		this.lnameVal = this.emp.lastname;
		
  }

 }
