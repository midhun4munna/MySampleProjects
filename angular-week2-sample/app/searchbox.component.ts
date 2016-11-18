import { Component,Input,OnInit } from '@angular/core';
import { HeroService } from './hero.service';

@Component({
  selector: 'search-box',
  template: `
			<h1>{{title}}</h1>
			<p>{{name}}</p>
			<input placeholder="{{text}}" #input>
			<button class="btn-clear" (click)=clearInput(input)>Clear</button>
			<button class="btn-clear" (click)=onClick(input)>Submit</button>
			<ul>
				<li *ngFor="let emp of heroList">{{emp}}</li>
			</ul>
			`,
	
})
export class SearchBox implements OnInit{
	
	title = "Add Heros";
	public heroList = [];
	@Input('placehdr')
	text = "Type your Search";
	
	constructor(private heroService: HeroService) { }
	
	clearInput(input){
	console.log("-------------Cleared Input Value----------------"+input.value);
		input.value='';
	}
	
	onClick(input){
	console.log("-------------Onclick ----------------"+input.value);
	this.heroList = this.heroService.addHero(input.value);
	
	}
	

  
  ngOnInit(){
  console.log("-------------OnInit ----------------");
      this.heroService
        .getHeroes()
		.subscribe(res => console.log("Subscribed"));
  }

}