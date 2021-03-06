import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { User } from '../user';


@Injectable({
  providedIn: 'root'
})
export class LoginjwtService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',

      // Authorization: 'my-auth-token',
      // Authorization: 'Basic ' + btoa('username:password'),
    }),
  };

  constructor(private http: HttpClient) { }

  public loginJWT(authRequest: any) {
    return this.http.post("http://localhost:8080/validate", authRequest, { responseType: 'text' as 'json' });
  }

  public welcome(token: any) {
    let tokenStr = token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }
    // return an observable with a user-facing error message
    return throwError('Something bad happened; please try again later.');
  }

  public loggedIn() {
    if (localStorage.getItem('token') != '') {
      return true;
    }
    else {
      return false;
    }
  }
}
