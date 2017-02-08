import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Game } from './game.model';

@Injectable()
export class GameService {

	constructor(private aHttp: AuthHttp) { }

	getGame(): Observable<Game> {
		return this.aHttp
			.get(environment.api)
			.map(resp => resp.json() as Game);
	}
}
