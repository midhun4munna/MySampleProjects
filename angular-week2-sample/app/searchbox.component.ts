import { Component,Input } from '@angular/core';

@Component({
  selector: 'search-box',
  template: `
			<h1>{{title}}</h1>
			<input placeholder="{{text}}" #input>
			<button class="btn-clear" (click)=clearInput(input)>Clear</button>
			<button class="btn-clear" (click)=onClick(input)>Submit</button>
			<ul>
				<li *ngFor="let emp of employeeList">{{emp}}</li>
			</ul>
			`
})
export class SearchBox {
	
	title = "Welcome";
	public employeeList = ['Raju','Ramu'];
	@Input('placehdr')
	text = "Type your Search";
	clearInput(input){
	console.log("-------------Cleared Input Value----------------"+input.value);
		input.value='';
	}
	onClick(input){
	console.log("-------------Onclick ----------------"+input.value);
		this.employeeList.push(input.value);
	}

}