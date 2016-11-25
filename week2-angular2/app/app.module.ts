import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }   from './app.component';
import { AppRoutingModule,routingComponents }   from './app.routing';
import { EmployeeService } from './employee.service';
import { HttpModule }    from '@angular/http';

@NgModule({
  imports:      [ BrowserModule,AppRoutingModule,HttpModule  ],
  declarations: [ AppComponent,routingComponents ],
  bootstrap:    [ AppComponent ],
  providers:    [EmployeeService]
})

export class AppModule { }