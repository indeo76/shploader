import { Routes } from '@angular/router';
import {ShpAnalizerComponent} from './shpanalizer/shp-analizer/shp-analizer.component';
import {MainComponent} from './main/main.component';

export const routes: Routes = [
  {
    path: 'home',
    component: MainComponent
  },
  {
    path: 'shp-analizer',
    component: ShpAnalizerComponent
  }
];
