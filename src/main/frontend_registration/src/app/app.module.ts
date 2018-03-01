import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AuthModule} from './auth/auth.module';
import { AppComponent } from './app.component';
import { RegistrationComponent} from './registration/registration.component';
import { AppRoutingModule } from './/app-routing.module';
import { LoginComponent } from './login/login.component';
import { VkComponent } from './vk/vk.component';
import {MapComponent} from "./map/map.component";
import {AgmCoreModule} from '@agm/core';
import {UserPageComponent} from './user-page/user-page.component';


@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    VkComponent,
    MapComponent,
    UserPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AuthModule,
    HttpModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyB50v0zwR77xBBodMAILSKbUrqay6txODg',
    }),
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
