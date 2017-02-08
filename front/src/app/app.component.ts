import { Component } from '@angular/core';
import { Auth } from './auth/auth.service';
import { GameService } from './game/models/game.service';
import { Game } from './game/models/game.model';

import { Observable } from 'rxjs/Observable';

import 'rxjs/add/observable/of';

import 'rxjs/add/operator/catch';


@Component({
	selector: 'app-root',
	providers: [Auth],
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent {

	title: String = 'mehbat rocks';
	game: Game = { lines: [], canPlay: false };

	constructor(public auth: Auth, private gameSrv: GameService) { }

	public showToken() {
		this.gameSrv.getGame()
			.subscribe(
				resp => this.game = resp,
				error => console.error(error)
			);
	}
}
