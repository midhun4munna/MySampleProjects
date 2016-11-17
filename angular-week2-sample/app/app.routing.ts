import { NgModule }      from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FirstComponent }   from './first.component';
import { SecondComponent }   from './second.component';
import { ThirdComponent }   from './third.component';

const routes: Routes = [
	{path: 'first', component: FirstComponent},
	{path: 'second', component: SecondComponent},
	{path: 'third', component: ThirdComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]  
})

export class AppRoutingModule {}

export const routingComponents = [FirstComponent,SecondComponent,ThirdComponent];

