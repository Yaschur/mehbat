import { NgModule } from '@angular/core';
import { HttpModule, Http, RequestOptions } from '@angular/http';
import { provideAuth, AuthHttp, AuthConfig } from 'angular2-jwt';
import { BrowserModule } from '@angular/platform-browser';

import { GameService } from './game/models/game.service';
import { AppComponent } from './app.component';

export function authHttpServiceFactory(http: Http, options: RequestOptions) {
	return new AuthHttp( new AuthConfig({}), http, options);
}

@NgModule({
	declarations: [
		AppComponent
	],
	imports: [
		HttpModule,
		BrowserModule
	],
	providers: [
		{
			provide: AuthHttp,
			useFactory: authHttpServiceFactory,
			deps: [ Http, RequestOptions ]
		},
		GameService
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
