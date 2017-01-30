import { Component } from '@angular/core';
import { Auth } from './auth/auth.service';

@Component({
	selector: 'app-root',
	providers: [ Auth ],
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent {

	constructor(private auth: Auth) {}

	title = 'mehbat will be';
}
