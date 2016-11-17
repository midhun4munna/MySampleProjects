import { Component } from '@angular/core';
import { SearchBox }   from './searchbox.component';

@Component({
  selector: 'my-app',
    template: `<h1>First Component</h1>
			<search-box [placehdr]="'Custom Placeholder'"></search-box>
			`
})
export class AppComponent { 
onClick(){
		console.log("-----Clicked----------------");
	}
}
