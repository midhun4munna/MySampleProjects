import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }   from './app.component';
import { AppRoutingModule,routingComponents }   from './app.routing';
import { SearchBox }   from './searchbox.component';

@NgModule({
  imports:      [ BrowserModule,AppRoutingModule ],
  declarations: [ AppComponent,routingComponents,SearchBox ],
  bootstrap:    [ AppComponent ]
})

export class AppModule { }