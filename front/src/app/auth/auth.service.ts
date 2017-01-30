import { Injectable } from '@angular/core';
import { tokenNotExpired } from 'angular2-jwt';

let Auth0Lock = require('auth0-lock').default;

@Injectable()
export class Auth {
	lock = new Auth0Lock('HGnMAarHI5CXCIZ6WHu4kPB4fgHuyspB', 'mehbat.eu.auth0.com', {});

	constructor() {
		this.lock.on('authenticated', (authResult) => {
			localStorage.setItem('id_token', authResult.idToken)
		});
	}

	public login() {
    this.lock.show();
  }

  public authenticated() {
    return tokenNotExpired();
  }

  public logout() {
    localStorage.removeItem('id_token');
  }
}