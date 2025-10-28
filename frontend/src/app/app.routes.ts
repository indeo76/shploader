import { Routes } from '@angular/router';
import {ShpAnalizerComponent} from './shpanalizer/shp-analizer/shp-analizer.component';
import {MainComponent} from './main/main.component';
import {ShpImporterComponent} from './shpimporter/shp-importer/shp-importer.component';

export const routes: Routes = [
  {
    path: 'home',
    component: MainComponent
  },
  {
    path: 'shp-analizer',
    component: ShpAnalizerComponent
  },
  {
    path: 'shp-importer',
    component: ShpImporterComponent
  }
];
