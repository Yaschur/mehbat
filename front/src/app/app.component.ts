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

	title: String = 'mehbat rocks';
	lines: String[] = [];
	canPlay: Boolean = false;

	public showToken() {
		this.authHttp.get('http://localhost:8080/game')
			.subscribe(
				data => {
					var res = data.json();
					this.lines = res.lines;
					this.canPlay = res.canPlay;
				},
				err => console.log(err)
			);
	}
}
