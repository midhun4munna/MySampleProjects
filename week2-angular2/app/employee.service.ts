import { Injectable,Inject } from '@angular/core';
import { Http,Response,Headers, RequestOptions  } from '@angular/http';
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
	let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
	let empl = {"firstname":fname,"lastname":lname,"gender":gender};
	let body = JSON.stringify(empl);
	this.addurl = "/addNewEmployee";
	return this._http.post(this.addurl,body,options)
		.map((response:Response) => response.json())
		.subscribe(res => {
		console.log("===Employee added===="+res);
		this.employeeUpdateSource.next(res);
		});
  }
  
  updateEmployee(fname,lname,gender){
	console.log("--------Inside updateEmployee---------"+fname);
	let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
	let empl = {"firstname":fname,"lastname":lname,"gender":gender};
	let body = JSON.stringify(empl);
	this.updateurl = "/updateEmployee/" + fname;
	return this._http.put(this.updateurl,body,options)
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
