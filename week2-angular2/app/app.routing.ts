import { NgModule }      from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddComponent }   from './add.component';
import { ModifyComponent }   from './modify.component';

const routes: Routes = [
	{path: 'add', component: AddComponent},
	{path: 'modify', component: ModifyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]  
})

export class AppRoutingModule {}

export const routingComponents = [AddComponent,ModifyComponent];

