import { Injectable,Inject } from '@angular/core';
import { Http,Response } from '@angular/http';
import 'rxjs/add/operator/map';
import { Subject }    from 'rxjs/Subject';


@Injectable()
export class EmployeeService {
	private url : string = "/getEmployee";
	public emplList = [];
	private addurl = "";
	private updateurl = "";
	private emp ;
	private employeeUpdateSource = new Subject<any>();
	private employeeUpdateSource1 = new Subject<any>();
	employeeUpdate$ = this.employeeUpdateSource.asObservable();
	employeeUpdate1$ = this.employeeUpdateSource1.asObservable();
	
  constructor(private _http: Http){}
  
  getEmployee(){
	console.log("--------Inside getEmployee---------");
	return this._http.get(this.url)
		.map((response:Response) => response.json());
  }
  
  addEmployee(fname,lname,gender){
	console.log("--------Inside addEmployee---------"+fname);
	this.addurl = "/addNewEmployee?firstname=" + fname + "&lastname=" + lname + "&gender="+gender;
	return this._http.get(this.addurl)
		.map((response:Response) => response.json())
		.subscribe(res => {
		console.log("===Employee added===="+res);
		this.employeeUpdateSource.next(res);
		});
  }
  
  updateEmployee(fname,lname,gender){
	console.log("--------Inside updateEmployee---------"+fname);
	this.updateurl = "/updateEmployee?firstname=" + fname + "&lastname=" + lname + "&gender="+gender;
	return this._http.get(this.updateurl)
		.map((response:Response) => response.json())
		.subscribe(res => {
		console.log("===Employee updated===="+res);
		this.employeeUpdateSource.next(res);
		});
  }
  
  removeEmployee(fname,lname,gender){
	 console.log("--------Inside removeEmployee---------"+fname);
	 return this._http.delete("/removeEmployee/"+ fname)
		.map((response:Response) => response.json());
  }
  
  fieldUpdate(fname,lname,gender){
		console.log("--------Inside fieldUpdate Service---------"+fname);
		this.emp = {firstname:fname,lastname:lname,gen:gender};
		this.employeeUpdateSource1.next(this.emp);
  }
  
  getEmp(){
	console.log("--------Inside  getEmp Service---------");
	return this.emp;
  }
}