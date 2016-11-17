import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }   from './app.component';
import { SearchBox }   from './searchbox.component';

@NgModule({
  imports:      [ BrowserModule ],
  declarations: [ AppComponent,SearchBox ],
  bootstrap:    [ AppComponent ]
})

export class AppModule { }