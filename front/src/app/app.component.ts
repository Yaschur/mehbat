import { Component } from '@angular/core';
import { Auth } from './auth/auth.service';
import { AuthHttp } from 'angular2-jwt';

@Component({
	selector: 'app-root',
	providers: [Auth],
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent {

	constructor(private auth: Auth, public authHttp: AuthHttp) { }

	title = 'mehbat will be';

	public showToken() {
		this.authHttp.get('http://localhost:8080/game')
			.subscribe(
				data => this.title = data.text(),
				err => console.log(err)
			);
	}
}
