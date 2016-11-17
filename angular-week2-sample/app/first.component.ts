import { Component } from '@angular/core';
import { SearchBox }   from './searchbox.component';

@Component({
  selector: 'my-app',
  template: `<h1>First Component</h1>
			<p *ngIf="true">hi</p>
			<input type="submit" value="Submit" (click)="onClick()">
			<search-box [placehdr]="'Custom Placeholder'"></search-box>
			`
})
export class FirstComponent {
	
	onClick(){
		console.log("-----Clicked----------------");
	}

}
