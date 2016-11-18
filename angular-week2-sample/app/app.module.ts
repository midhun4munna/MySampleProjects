import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }   from './app.component';
import { AppRoutingModule,routingComponents }   from './app.routing';
import { SearchBox }   from './searchbox.component';
import { HeroService } from './hero.service';
import { HttpModule }    from '@angular/http';

@NgModule({
  imports:      [ BrowserModule,AppRoutingModule,HttpModule  ],
  declarations: [ AppComponent,routingComponents,SearchBox ],
  bootstrap:    [ AppComponent ],
  providers:    [HeroService]
})

export class AppModule { }