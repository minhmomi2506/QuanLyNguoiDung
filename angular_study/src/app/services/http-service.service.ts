import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { catchError, Observable, throwError } from 'rxjs';
import { Comments } from '../entity/Comments';
import { Posts } from '../entity/Posts';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  private REST_API = 'http://localhost:3000';

  constructor(private httpClient: HttpClient) { }
  public addPost(post: Posts) {
    const url = `${this.REST_API}/posts`;
    return this.httpClient.post<any>(url, post)
      .pipe(catchError(this.handleError));
  }

  public getPosts(): Observable<any> {
    const url = `${this.REST_API}/posts`;
    return this.httpClient.get<any>(url)
      .pipe(catchError(this.handleError));
  }

  public getProfile(): Observable<any> {
    const url = `${this.REST_API}/profile`;
    return this.httpClient.get<any>(url)
      .pipe(catchError(this.handleError));
  }

  public getStudents(): Observable<any> {
    const url = `${this.REST_API}/students`;
    return this.httpClient.get<any>(url)
      .pipe(catchError(this.handleError));
  }

  public addComment(comment: Comments) {
    const url = `${this.REST_API}/comments`;
    return this.httpClient.post<any>(url, comment)
      .pipe(catchError(this.handleError));
  }

  public getComments(): Observable<any> {
    const url = `${this.REST_API}/comments`;
    return this.httpClient.get<any>(url)
      .pipe(catchError(this.handleError));
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
}
