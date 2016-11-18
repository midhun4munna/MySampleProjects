import { Injectable,Inject } from '@angular/core';
import { Http,Response } from '@angular/http';
import 'rxjs/add/operator/map';


@Injectable()
export class HeroService {

	private url : string = "http://localhost:3000/app/samp.html";
	public heroList = [];
	
	constructor(private _http: Http){}
	
  addHero(hero){
    console.log("--------Inside hero service---------"+hero);
	this.heroList.push(hero);
	return  this.heroList;
  }
  
  
  getHeroes(){
  console.log("--------Inside getHeroes---------");
  return this._http.get(this.url)
		.map((response:Response) => console.log("Got res"));
  console.log("--------Inside hero called");
  }
  
  
  
}