import { Component,OnInit } from '@angular/core';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'my-app',
  templateUrl: './app/app.component.html',
  styleUrls: ['./app/employee.component.css']
})
export class AppComponent implements OnInit{ 

employees = [];

constructor(private employeeService: EmployeeService) {
	employeeService.employeeUpdate$.subscribe(
		out => { 
		this.employees = out;
	});
 }

  ngOnInit(){
	  console.log("-------------OnInit ----------------");
      this.employeeService
        .getEmployee()
		.subscribe(res => this.employees = res);
  }
  
    onRemove(fnameInput,lnameInput,gender){
		console.log("-------------Remove btn CLICKED ----------------"+fnameInput.innerText);
		this.employeeService
        .removeEmployee(fnameInput.innerText,lnameInput.innerText,gender.innerText)
		.subscribe(res => this.employees = res);
  }
  
  onModify(fnameInput,lnameInput,gender){
		console.log("-------------onModify btn CLICKED ----------------"+fnameInput.innerText);
		this.employeeService.fieldUpdate(fnameInput.innerText,lnameInput.innerText,gender.innerText);
  }
  

}
